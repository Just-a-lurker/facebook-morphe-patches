package app.fbpatches.patches.facebook

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.patch
import app.fbpatches.patches.shared.Constants.COMPATIBILITY_FACEBOOK

private const val EXTENSION_CLASS = "Lapp/fbpatches/extension/facebook/HideAdsPatch;"

@Suppress("unused")
val hideAdsPatch = patch(
    name = "Hide ads",
    description = "Hides sponsored posts and ads in Facebook.",
    default = true
) {
    compatibleWith(COMPATIBILITY_FACEBOOK)

    extendWith("extensions/extension.mpe")

    execute {
        // TODO: Adjust once the real fingerprint exists. Early return of the extension's result.
        SponsoredStoryFingerprint.method.addInstructions(
            0,
            """
                invoke-static {}, $EXTENSION_CLASS;->isSponsored()Z
                move-result v0
                return v0
            """
        )
    }
}
