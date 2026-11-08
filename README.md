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

Releases are signed. Morphe Manager remembers the signing key and refuses a bundle that was
changed or swapped after publication.

## 🩹 Patches list

<!-- PATCHES_START EXPANDED -->
> **[v1.0.2](https://github.com/Just-a-lurker/facebook-morphe-patches/releases/tag/v1.0.2)**&nbsp;&nbsp;•&nbsp;&nbsp;`main`&nbsp;&nbsp;•&nbsp;&nbsp;12 patches total
<details open>
<summary>📦 Facebook&nbsp;&nbsp;•&nbsp;&nbsp;12 patches</summary>
<br>

**Package name:** `com.facebook.katana`

**🎯 Supported versions:**

| 585.0.0.47.54 |
| :---: |

| 💊&nbsp;Patch | 📜&nbsp;Description | ⚙️&nbsp;Options |
|----------|----------------|-----------|
| [Block Instant Games ads](#block-instant-games-ads) | Games you play inside Facebook show no ads. Rewarded ads give no reward. |  |
| [Block ad telemetry](#block-ad-telemetry) | Stops ad screenshot detection, ad install attribution and several ways Facebook sends your advertising ID. |  |
| [Block background ad prefetch](#block-background-ad-prefetch) | Stops the background jobs that download ads and the ad-ranking model before you see them. |  |
| [Disable Audience Network](#disable-audience-network) | Stops Facebook serving its ads inside other apps on your phone. |  |
| [Hide affiliate product links](#hide-affiliate-product-links) | Removes affiliate shopping cards from reels, feed posts and comments, Shop now cards on reels, and the shop banner on a profile opened from one. |  |
| [Hide sponsored Marketplace listings](#hide-sponsored-marketplace-listings) | Removes ads and boosted listings from Marketplace's feed and search results. |  |
| [Hide sponsored posts](#hide-sponsored-posts) | Removes sponsored and promoted posts from the news feed, including ads swapped in later. |  |
| [Hide sponsored profile posts](#hide-sponsored-profile-posts) | Removes the ads between posts on someone's profile or a Page. |  |
| [Hide sponsored reels](#hide-sponsored-reels) | Removes ads from Reels and Watch, including product banners over reels and mid-roll ads. |  |
| [Hide sponsored search results](#hide-sponsored-search-results) | Removes the ads from Facebook's search results. |  |
| [Hide sponsored stories](#hide-sponsored-stories) | Removes ad cards from the story viewer. |  |
| [Install beside Meta's apps](#install-beside-meta-s-apps) | Lets stock Messenger, Facebook Lite, Business Suite and Workplace install and update beside this re-signed Facebook. Without it, Android refuses one of them because they share two permissions with Facebook. Works as soon as you patch it in, with no switch. |  |

</details>

<!-- PATCHES_END -->

## 🛠️ Building locally

- Run `./gradlew buildAndroid`
- The built patches `.mpp` file is found in `patches/build/libs/patches-*.mpp`
- Patch the mpp file using [Morphe-Desktop](https://github.com/MorpheApp/morphe-desktop)
  like any other patch bundle.
- Gradle resolves the Morphe plugin from GitHub Packages. Put a classic PAT with `read:packages`
  in your Gradle user home (`~/.gradle/gradle.properties`), not in this repository's
  `gradle.properties`, which is tracked.

See the [Morphe documentation](https://github.com/MorpheApp/morphe-documentation) for more information.

## 🧑‍💻 Contributing

Commit conventions and the publishing/release process live in [CONTRIBUTING.md](CONTRIBUTING.md).
All work happens on `dev`; releases are cut by semantic-release only.

## 📜 License

Facebook Morphe Patches contributors Patches are licensed under the [GNU General Public License v3.0](LICENSE).
Facebook, Messenger and Meta are trademarks of Meta Platforms, Inc.
