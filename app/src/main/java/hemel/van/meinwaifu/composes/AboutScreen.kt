package hemel.van.meinwaifu.composes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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

@Composable
fun AboutScreenCompactContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text("About screen is under construction... :-D")
    }
}

@Composable
fun AboutScreenCompact(
    modifier: Modifier = Modifier,
    navigateToHomeScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            MeinTopAppBar(
                title = stringResource(R.string.screen_about),
                logo = painterResource(R.drawable.main_icon_square),
                logoContentDescription = stringResource(R.string.navigate_to_screen_home),
                logoCallback = navigateToHomeScreen
            )
        }
    ) { paddingValues ->
        AboutScreenCompactContent(modifier = modifier.padding(paddingValues))
    }
}

@Composable
fun AboutScreenMedium(
    modifier: Modifier = Modifier,
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
@Preview(name = "Compact screen", device = "spec:width=411dp,height=891dp")
@Composable
fun AboutScreenCompactPreview() {
    AboutScreenCompact {}
}

@Preview(device = "spec:width=411dp,height=891dp,orientation=landscape")
@Composable
fun AboutScreenMediumPreview() {
    AboutScreenMedium {}
}
