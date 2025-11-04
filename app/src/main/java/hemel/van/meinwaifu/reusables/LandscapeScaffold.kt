package hemel.van.meinwaifu.reusables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hemel.van.meinwaifu.R
import hemel.van.meinwaifu.composes.HomeScreenCompactContent

/**
 * When in landscape mode and you are not being able to change screen's cutout color, use this
 * container.
 *
 * `statusBarPadding` will leave the status bar (top) alone.
 * `navigationBarsPadding` will leave the system navigation (bottom) alone.
 *
 * Hence left only the system's cutout (that notch bar that is notoriously difficult to customise).
 */
@Composable
fun LandscapeScaffold(
    sideBar: @Composable (() -> Unit),
    content: @Composable (() -> Unit)
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.primary,
    ) {
        Surface(
            modifier = Modifier
                .displayCutoutPadding(),
            color = MaterialTheme.colorScheme.background
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .statusBarsPadding()
                        .navigationBarsPadding()
                ) {
                    sideBar.invoke()
                }
                Column(
                    modifier = Modifier
                        .statusBarsPadding()
                        .navigationBarsPadding()
                        .fillMaxSize()
                ) {
                    content.invoke()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(
    name = "Medium screen",
    device = "spec:width=411dp,height=891dp,orientation=landscape",
    showSystemUi = true
)
@Composable
fun LandscapeScaffoldPreview() {
    LandscapeScaffold(
        sideBar = {
            MeinSideAppBar(
                logo = painterResource(R.drawable.main_icon_square),
                logoContentDescription = stringResource(R.string.app_bar_navigation_icon),
                dropDown = {
                }
            )
        },
        content = {
            HomeScreenCompactContent()
        }
    )
}
