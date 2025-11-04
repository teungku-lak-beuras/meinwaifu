package hemel.van.meinwaifu.composes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hemel.van.meinwaifu.R
import hemel.van.meinwaifu.reusables.LandscapeScaffold
import hemel.van.meinwaifu.reusables.MeinSideAppBar
import hemel.van.meinwaifu.reusables.MeinTopAppBar

@Composable
fun HomeScreenDropDown(
    navigateToHelpScreen: () -> Unit,
    navigateToSettingsScreen: () -> Unit,
    navigateToAboutScreen: () -> Unit
) {
    var dropDownExpanded by remember { mutableStateOf(false) }

    IconButton(
        modifier = Modifier.fillMaxSize(),
        onClick = { dropDownExpanded = !dropDownExpanded }
    ) {
        Icon(
            contentDescription = stringResource(R.string.app_bar_menu_more),
            imageVector = Icons.Filled.Menu
        )
    }
    DropdownMenu(
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        expanded = dropDownExpanded,
        onDismissRequest = { dropDownExpanded = false }
    ) {
        DropdownMenuItem(
            text = { Text(stringResource(R.string.screen_help)) },
            onClick = { dropDownExpanded = false; navigateToHelpScreen.invoke() }
        )
        HorizontalDivider()
        DropdownMenuItem(
            text = { Text(stringResource(R.string.screen_settings)) },
            onClick = { dropDownExpanded = false; navigateToSettingsScreen.invoke() }
        )
        HorizontalDivider()
        DropdownMenuItem(
            text = { Text(stringResource(R.string.screen_about)) },
            onClick = { dropDownExpanded = false; navigateToAboutScreen.invoke() }
        )
    }
}

@Composable
fun HomeScreenCompactContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text("Hello world!")
    }
}

@Composable
fun HomeScreenCompact(
    navigateToHelpScreen: () -> Unit = {},
    navigateToSettingsScreen: () -> Unit = {},
    navigateToAboutScreen: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            MeinTopAppBar(
                title = stringResource(R.string.screen_home),
                logo = painterResource(R.drawable.main_icon_square),
                logoContentDescription = stringResource(R.string.app_bar_navigation_icon),
                dropDown = {
                    HomeScreenDropDown(
                        navigateToHelpScreen = navigateToHelpScreen,
                        navigateToSettingsScreen = navigateToSettingsScreen,
                        navigateToAboutScreen = navigateToAboutScreen
                    )
                }
            )
        }
    ) { paddingValues ->
        HomeScreenCompactContent(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun HomeScreenMedium(
    navigateToHelpScreen: () -> Unit = {},
    navigateToSettingsScreen: () -> Unit = {},
    navigateToAboutScreen: () -> Unit = {}
) {
    LandscapeScaffold(
        sideBar = {
            MeinSideAppBar(
                logo = painterResource(R.drawable.main_icon_square),
                logoContentDescription = stringResource(R.string.app_bar_navigation_icon),
                dropDown = {
                    HomeScreenDropDown(
                        navigateToHelpScreen = navigateToHelpScreen,
                        navigateToSettingsScreen = navigateToSettingsScreen,
                        navigateToAboutScreen = navigateToAboutScreen
                    )
                }
            )
        },
        content = {
            HomeScreenCompactContent()
        }
    )
}

@Composable
fun HomeScreenExpanded() {
}

@Composable
fun HomeScreen(
    windowSizeClass: WindowSizeClass,
    navigateToHelpScreen: () -> Unit,
    navigateToSettingsScreen: () -> Unit,
    navigateToAboutScreen: () -> Unit
) {
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            HomeScreenCompact(
                navigateToHelpScreen = navigateToHelpScreen,
                navigateToSettingsScreen = navigateToSettingsScreen,
                navigateToAboutScreen = navigateToAboutScreen
            )
        }
        WindowWidthSizeClass.Medium -> {
            HomeScreenMedium(
                navigateToHelpScreen = navigateToHelpScreen,
                navigateToSettingsScreen = navigateToSettingsScreen,
                navigateToAboutScreen = navigateToAboutScreen
            )
        }
        WindowWidthSizeClass.Expanded -> {
            HomeScreenMedium(
                navigateToHelpScreen = navigateToHelpScreen,
                navigateToSettingsScreen = navigateToSettingsScreen,
                navigateToAboutScreen = navigateToAboutScreen
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
fun HomeScreenCompactPreview() {
    HomeScreenCompact()
}

@Preview(
    name = "Medium screen",
    device = "spec:width=411dp,height=891dp,orientation=landscape",
    showSystemUi = true
)
@Composable
fun HomeScreenMediumPreview() {
    HomeScreenMedium()
}
