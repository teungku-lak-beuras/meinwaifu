package hemel.van.meinwaifu.composes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import hemel.van.meinwaifu.R
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
    navigateToHomeScreen: () -> Unit = {}
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
fun HelpScreen(
    navigateToHomeScreen: () -> Unit = {},
    isLandscape: Boolean,
    windowSizeClass: WindowSizeClass
) {
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            if (isLandscape) {
                HelpScreenCompact()
            }
            else {
                HelpScreenCompact(
                    navigateToHomeScreen = navigateToHomeScreen
                )
            }
        }
        WindowWidthSizeClass.Medium -> {
            HelpScreenCompact()
        }
        WindowWidthSizeClass.Expanded -> {
            HelpScreenCompact()
        }
    }
}

/**
 * Prikitiws
 */
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(device = "spec:width=411dp,height=891dp")
@Composable
fun HelpScreenCompactPreview() {
    HelpScreen(
        isLandscape = false,
        windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(411.dp, 891.dp))
    )
}
