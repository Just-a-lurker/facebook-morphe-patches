# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this is
Morphe patches for Facebook (`com.facebook.katana`), built on the Morphe patches template. The output is a `.mpp` patch bundle. Users apply it with Morphe Manager or Morphe Desktop.

## Commands
- Build the bundle: `./gradlew buildAndroid` (`gradlew.bat buildAndroid` on Windows). Output: `patches/build/libs/patches-*.mpp`.
- Compile check (same as CI): `./gradlew :patches:buildAndroid clean --no-daemon`
- Regenerate `patches-list.json`: `./gradlew generatePatchesList`. Release runs this; don't commit the result by hand.
- There are no unit tests or lint tasks. To validate, apply the `.mpp` to the APK with Morphe Desktop (CLI `--validate` checks that each fingerprint matches exactly one method).
- Gradle needs a GitHub PAT to resolve the Morphe plugin from GitHub Packages: set `gpr.user`/`gpr.key` in `gradle.properties`, or the `GITHUB_ACTOR`/`GITHUB_TOKEN` env vars. `gradle.properties` holds the token and is git-ignored.

## Architecture
Two Gradle modules, wired by the `app.morphe.patches` settings plugin (`settings.gradle.kts`):

- `patches/`: Kotlin patch definitions (package `app.fbpatches.patches`).
  - `facebook/ads/<surface>/`: one user-visible `bytecodePatch(name=…, default=true)` per ad surface (`feed`, `profile`, `stories`, `reels`, `search`, `marketplace`, `games`, `affiliate`, `prefetch`, `telemetry`, `audiencenetwork`), each with its own fingerprints/anchors. Find targets by stable strings, kept class names (`GraphQLFeedUnitEdge`, `GraphQLStory`, ...), GraphQL field keys (`treeFieldKey`) or method shape, never by obfuscated names. Most patches fail with a `PatchException` when an anchor is missing; a few optional hooks only `patchLog.warning`.
  - `facebook/shared/`: `Support.kt` (register helpers, `handleTargets`, `neuterOrReason`, `freeLocalsAt`), `StoryModels.kt` (GraphQL model accessors, the `fbpatchesIsSponsored` helper added to `GraphQLStory`), `ExtensionPatch.kt` (`extensionPatch`, which does `extendWith("extensions/extension.mpe")`; patches that call the extension `dependsOn` it).
  - `util/RegisterLiveness.kt`: control flow and register liveness, used to pick free registers mid-method.
  - `shared/Constants.kt`: `COMPATIBILITY_FACEBOOK`, which holds the package name, APK file type and target versions.
  - `util/PatchListGenerator.kt`: template code that writes `patches-list.json` from the built `.mpp`.
- `extensions/extension/`: Java code (package `app.fbpatches.extension`) compiled into `extensions/extension.mpe` and merged into the patched app. Runtime logic is in `facebook/ads/*.java`. Injected smali calls its static methods, and the descriptor must match exactly, e.g. `Lapp/fbpatches/extension/facebook/ads/FeedAds;->shouldHide(Ljava/lang/Object;)Z`. Some extension methods are stubs whose bodies the patch fills (e.g. `SearchAds.isModule`, `ReelsAds.itemStory`). Every hook fails open: on any error, Facebook's own behavior.

Most of the ad patches are inspired by Hushfacebook's work (GPL-3.0, same Facebook version). Keep the "Forked from" headers and the NOTICE credits.

Typical flow: find an anchor, hook its method from a patch, then put the runtime logic in a static method in the extension.

## Validating without a device
Use Morphe Desktop CLI (`morphe-desktop-*-all.jar patch -p patches/build/libs/patches-1.0.0.mpp --unsigned --continue-on-error -r result.json <apkm>`). It must be the **Android 11+** `.apkm`, because the Android 9+ build hides its dex in a superpack store. Applying the bundle does not prove the hooks are right, so also check that each extension call site in the patched dex resolves (no `NoSuchMethodError`) and run d8 over the new dex. `--verify-with-sdk` crashes d8 on Facebook's own `classes2.dex`, a known false positive.

## Reverse-engineering workspace (untracked)
`apks/` holds the target APKs (Facebook 585.0.0.47.54 `.apkm` bundles from APKMirror; `com.facebook.katana_585.0.0.47.54-480dpi_arm64_v8a.apkm` is the Android 11+ one the patches apply to). The repo root also has local copies of jadx (`jadx-1.5.6/`, `jadx-gui-1.5.6-with-jre-win/`) and apktool (`apktool.bat`/`apktool.jar`) for decompiling it. Don't commit any of these. `Constants.kt` is set to `ApkFileType.APKM` and target version `585.0.0.47.54` (experimental).

## Current state
11 ad patches, all default-on and always on (no settings screen). All of them apply to the arm64 Android 11+ 585.0.0.47.54 build. On 2026-11-08 the patched build passed full ART verification on the `fbroot` emulator, launched, and the user confirmed it worked when logged in. Thorough per-surface testing by the user is still pending; see `handoff.md`. Out of scope by the user's decision: the items in `extra.md` (suggested posts/stories, upsells, promo notifications, the Commission eligible label).

The emulator AVD `fbroot` (Android 14 x86_64 with arm64 translation, `E:\AndroidSDK`) can run the arm64 build. A patched APK has to be signed with the SDK's `apksigner` (`zipalign -p 4` first), because the CLI's built-in signer fails with "JCE cannot authenticate the provider BC".

## Git / release rules
- Do all work on `dev`. Merge `dev` into `main` for a stable release, using a plain merge (no squash).
- Commit messages are semantic and drive semantic-release (`.releaserc`): `feat:` makes a minor release, `fix:`/`perf:`/`bump:` make a patch release, `chore:` makes none. Releases from `dev` are prereleases.
- Never hand-edit or commit generated files: `patches-list.json`, `patches-bundle.json`, `CHANGELOG.md`, the README patches section between `PATCHES_START`/`PATCHES_END`, or the version in `gradle.properties`. `release.yml` updates them.
- Don't create releases by hand, don't force-push release commits, and don't write new release scripts. If release behavior has to change, edit the existing `release.yml`/`.releaserc`.
- Never commit `morphe-signing.key`.
