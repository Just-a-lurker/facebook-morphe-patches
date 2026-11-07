package app.fbpatches.patches.facebook

import app.morphe.patcher.Fingerprint

/**
 * TODO: Placeholder. Decompile the target Facebook APK (jadx / apktool), find the method that
 * decides whether a feed story is sponsored, and fingerprint it using stable string constants,
 * never obfuscated names. Verify it matches exactly one method with Morphe Desktop CLI --validate.
 */
object SponsoredStoryFingerprint : Fingerprint(
    returnType = "Z",
    parameters = listOf("L"),
    filters = listOf(
        // string("..."),
    )
)
