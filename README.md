# 🧩 Facebook Morphe Patches

Morphe patches for the Facebook Android app (`com.facebook.katana`) that remove every ad.

## ❓ About

Ad-removal patches for Facebook: sponsored posts in the feed and on profiles, ads in Stories,
Reels and Watch (including mid-roll ads and banners), search results, Marketplace and Instant
Games, affiliate shop cards, plus background ad prefetching, ad telemetry and Audience Network.

### Scope: removing ads and stability, nothing else

This repo is deliberately not feature-rich. It does exactly two things: remove every ad, and
keep Facebook working reliably — including being installable alongside Meta's own Messenger.
No downloads, themes, chat tweaks, navigation changes or other extras: if it isn't about
removing ads or keeping the app stable, it is out of scope.

All patches are on by default and have no settings. They target Facebook 585.0.0.47.54. The
approach is inspired by [Hushfacebook](https://github.com/SysAdminDoc/Hushfacebook)'s work;
[NOTICE](NOTICE) credits the upstream projects.

### Which APK to use

Use the Facebook 585.0.0.47.54 `.apkm` bundle labelled **Android 11+** on APKMirror (for example
the arm64-v8a build). The Android 9+ bundle keeps almost all of Facebook's code in a compressed
store that the patcher can't read, so every patch fails on it.

### How to use these patches

Click the button below, or manually add this repo url to Morphe Manager: https://github.com/Just-a-lurker/facebook-morphe-patches

<a href="https://morphe.software/add-source?github=Just-a-lurker/facebook-morphe-patches">
  <img src=".github/assets/add-to-morphe.svg" alt="Add to Morphe" height="48">
</a>

Then open Facebook on Morphe Manager's **Apps** tab, tap **Patch**, and install the result.

## 🩹 Patches list

<!-- PATCHES_START EXPANDED -->

<!-- Do not modify this section by hand. The patch list is generated when release.yml creates a new release.
     
     If you wish for the patches list to be collapsed, then remove the word 'EXPANDED' from the comment tag above. -->

#### A list of your patches will automatically be shown here after your first patches release is created.

<!-- PATCHES_END -->

## 🛠️ Building locally

- Run `./gradlew buildAndroid`
- The built patches `.mpp` file is found in `patches/build/libs/patches-*.mpp`
- Patch the mpp file using [Morphe-Desktop](https://github.com/MorpheApp/morphe-desktop)
  like any other patch bundle.

See the [Morphe documentation](https://github.com/MorpheApp/morphe-documentation) for more information.

## 🧑‍💻 Contributing

Commit conventions and the publishing/release process live in [CONTRIBUTING.md](CONTRIBUTING.md).
All work happens on `dev`; releases are cut by semantic-release only.

## 📜 License

Facebook Morphe Patches contributors Patches are licensed under the [GNU General Public License v3.0](LICENSE).
Facebook, Messenger and Meta are trademarks of Meta Platforms, Inc.
