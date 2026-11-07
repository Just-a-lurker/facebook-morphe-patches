package app.fbpatches.patches.shared

import app.morphe.patcher.patch.ApkFileType
import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

object Constants {
    val COMPATIBILITY_FACEBOOK = Compatibility(
        name = "Facebook",
        packageName = "com.facebook.katana",
        // TODO: Verify against the file type listed on APKMirror for the chosen version.
        apkFileType = ApkFileType.APK,
        appIconColor = 0x1877F2,
        targets = listOf(
            // TODO: Replace with the exact Facebook version used for development.
            AppTarget(version = null, isExperimental = true)
        )
    )
}
