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
fun HelpScreenCompactContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text("This screen is under construction... :-D")
    }
}

@Composable
fun HelpScreenCompact(
    modifier: Modifier = Modifier,
    navigateToHomeScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            MeinTopAppBar(
                title = stringResource(R.string.screen_help),
                logo = painterResource(R.drawable.main_icon_square),
                logoContentDescription = stringResource(R.string.navigate_to_screen_home),
                logoCallback = navigateToHomeScreen
            )
        }
    ) { paddingValues ->
        HelpScreenCompactContent(modifier = modifier.padding(paddingValues))
    }
}

@Composable
fun HelpScreenMedium(
    modifier: Modifier = Modifier,
    navigateToHomeScreen: () -> Unit
) {
    LandscapeScaffold(
        sideBar = {
            MeinSideAppBar(
                logo =  painterResource(R.drawable.main_icon_square),
                logoContentDescription = stringResource(R.string.screen_home),
                logoCallback = navigateToHomeScreen
            )
        },
        content = {
            HelpScreenCompactContent()
        }
    )
}

@Composable
fun HelpScreen(
    navigateToHomeScreen: () -> Unit = {},
    windowSizeClass: WindowSizeClass
) {
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            HelpScreenCompact(
                navigateToHomeScreen = navigateToHomeScreen
            )
        }
        WindowWidthSizeClass.Medium -> {
            HelpScreenMedium(
                navigateToHomeScreen = navigateToHomeScreen
            )
        }
        WindowWidthSizeClass.Expanded -> {
            HelpScreenMedium(
                navigateToHomeScreen = navigateToHomeScreen
            )
        }
    }
}

/**
 * Prikitiws
 */
@Preview(
    name = "Compact screen",
    device = "spec:width=411dp,height=891dp",
    showSystemUi = true
)
@Composable
fun HelpScreenCompactPreview() {
    HelpScreenCompact {}
}

@Preview(
    name = "Medium screen",
    device = "spec:width=891dp,height=411dp",
    showSystemUi = true
)
@Composable
fun HelpScreenMediumPreview() {
    HelpScreenMedium {}
}
