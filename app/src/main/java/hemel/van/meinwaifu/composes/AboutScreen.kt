package hemel.van.meinwaifu.composes

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hemel.van.meinwaifu.R
import hemel.van.meinwaifu.reusables.LandscapeScaffold
import hemel.van.meinwaifu.reusables.MeinSideAppBar
import hemel.van.meinwaifu.reusables.MeinTopAppBar
import hemel.van.meinwaifu.reusables.PortraitScaffold

@Composable
fun AboutScreenCompactContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        content = {
            Text("About screen is under construction... :-D")
        }
    )
}

@Composable
fun AboutScreenCompact(
    navigateToHomeScreen: () -> Unit
) {
    PortraitScaffold(
        topAppBar = {
            MeinTopAppBar(
                title = stringResource(R.string.screen_about),
                logo = painterResource(R.drawable.main_icon_square),
                logoContentDescription = stringResource(R.string.navigate_to_screen_home),
                logoCallback = navigateToHomeScreen
            )
        },
        content = {
            AboutScreenCompactContent()
        }
    )
}

@Composable
fun AboutScreenMedium(
    navigateToHomeScreen: () -> Unit
) {
    LandscapeScaffold(
        sideAppBar = {
            MeinSideAppBar(
                logo = painterResource(R.drawable.main_icon_square),
                logoContentDescription = stringResource(R.string.navigate_to_screen_home),
                logoCallback = navigateToHomeScreen
            )
        },
        content = {
            AboutScreenCompactContent()
        }
    )
}

@Composable
fun AboutScreen(
    windowSizeClass: WindowSizeClass,
    navigateToHomeScreen: () -> Unit
) {
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            AboutScreenCompact(
                navigateToHomeScreen = navigateToHomeScreen
            )
        }
        WindowWidthSizeClass.Medium -> {
            AboutScreenMedium(
                navigateToHomeScreen = navigateToHomeScreen
            )
        }
        WindowWidthSizeClass.Expanded -> {
            AboutScreenMedium(
                navigateToHomeScreen = navigateToHomeScreen
            )
        }
    }
}

/**
 * Prikitiws.
 */
@Preview(
    name = "Compact screen",
    device = "spec:width=411dp,height=891dp",
    showSystemUi = true
)
@Composable
fun AboutScreenCompactPreview() {
    AboutScreenCompact {}
}

@Preview(
    name = "Medium screen",
    device = "spec:width=411dp,height=891dp,orientation=landscape",
    showSystemUi = true
)
@Composable
fun AboutScreenMediumPreview() {
    AboutScreenMedium {}
}
