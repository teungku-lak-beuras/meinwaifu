package hemel.van.meinwaifu.reusables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    sideAppBar: @Composable (() -> Unit),
    content: @Composable (() -> Unit)
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.primary,
        content = {
            Surface(
                modifier = Modifier
                    .displayCutoutPadding(),
                color = MaterialTheme.colorScheme.background,
                content = {
                    Row(
                        modifier = Modifier
                            .windowInsetsPadding(WindowInsets.statusBars)
                            .windowInsetsPadding(WindowInsets.navigationBars),
                        content = {
                            Box(content = { sideAppBar.invoke() })
                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .padding(top = 16.dp)
                                    .fillMaxSize(),
                                content = {
                                    content.invoke()
                                }
                            )
                        }
                    )
                }
            )
        }
    )
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
        sideAppBar = {
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
