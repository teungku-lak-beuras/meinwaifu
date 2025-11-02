package hemel.van.meinwaifu.composes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import hemel.van.meinwaifu.reusables.MeinTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarSmall() {
    var dropDownExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.surface
        ),
        navigationIcon = {
            IconButton(
                onClick = {}
            ) {
                Image(
                    contentDescription = stringResource(R.string.app_bar_navigation_icon),
                    painter = painterResource(R.drawable.main_icon_square)
                )
            }
        },
        actions = {
            IconButton(
                onClick = { dropDownExpanded = !dropDownExpanded }
            ) {
                Icon(
                    contentDescription = stringResource(R.string.app_bar_menu_more),
                    imageVector = Icons.Filled.Menu
                )
            }
            DropdownMenu(
                expanded = dropDownExpanded,
                onDismissRequest = { dropDownExpanded = false },
                modifier = Modifier
                    .padding(vertical = 0.dp),
                shape = MaterialTheme.shapes.large,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
            ) {
                DropdownMenuItem(
                    onClick = {},
                    text = { Text(stringResource(R.string.app_bar_menu_help)) }
                )
                HorizontalDivider()
                DropdownMenuItem(
                    onClick = {},
                    text = { Text(stringResource(R.string.app_bar_menu_settings)) }
                )
                HorizontalDivider()
                DropdownMenuItem(
                    onClick = {},
                    text = { Text(stringResource(R.string.app_bar_menu_about)) }
                )
            }
        },
        title = {
            Text(stringResource(R.string.app_name))
        }
    )
}

@Composable
fun HomeScreenCompactContent(
    modifier: Modifier = Modifier
) {
    Column {
        Text("Hello world!")
    }
}

@Composable
fun HomeScreenCompact(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            MeinTopAppBar(
                title = R.string.app_name,
                leftIconId = R.drawable.main_icon_square,
                leftIconContentDescriptionId = R.string.app_bar_navigation_icon,
                rightIconContentDescription = R.string.app_bar_menu_more
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier.padding(paddingValues)
        ) {
            HomeScreenCompactContent()
        }
    }
}

@Composable
fun HomeScreenMedium() {

}

@Composable
fun HomeScreenExpanded() {
}

@Composable
fun HomeScreen(
    isLandscape: Boolean,
    windowSizeClass: WindowSizeClass
) {
    if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
        HomeScreenCompact()
    }
    if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Medium || isLandscape) {
        HomeScreenMedium()
    }
    else if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded) {
        HomeScreenExpanded()
    }
    else {
        HomeScreenCompact()
    }
}

/* ==========
 * Prikitiws
 * ========== */
@Preview
@Composable
fun HomeScreenCompactPreview() {
    HomeScreenCompact()
}
