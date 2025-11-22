package hemel.van.meinwaifu.composes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import coil3.compose.rememberConstraintsSizeResolver
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.util.DebugLogger
import hemel.van.meincore.entitity.WaifuEntityV1
import hemel.van.meincore.repository.utilities.ApiState
import hemel.van.meinwaifu.R
import hemel.van.meinwaifu.reusables.ButtonPrimary
import hemel.van.meinwaifu.reusables.LandscapeScaffold
import hemel.van.meinwaifu.reusables.MeinSideAppBar
import hemel.van.meinwaifu.reusables.MeinTopAppBar
import hemel.van.meinwaifu.reusables.PortraitScaffold
import hemel.van.meinwaifu.reusables.borderSmall
import hemel.van.meinwaifu.reusables.cornerMedium
import hemel.van.meinwaifu.reusables.dropShadowLight
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * =================================================================================================
 * Drop down
 * =================================================================================================
 */
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
        shape = cornerMedium,
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

/**
 * =================================================================================================
 * Items
 * =================================================================================================
 */
@Composable
fun LoadingItem(loadingText: String = stringResource(R.string.loading)) {
    Image(
        modifier = Modifier.size(72.dp),
        contentDescription = stringResource(R.string.loading),
        painter = painterResource(R.drawable.alternative_icon_1)
    )
    Text(
        modifier = Modifier.padding(top = 16.dp),
        style = MaterialTheme.typography.bodyLarge,
        text = loadingText
    )
}

@Composable
fun ErrorItem(additionalText: String? = null) {
    Image(
        modifier = Modifier.size(72.dp),
        contentDescription = stringResource(R.string.loading),
        painter = painterResource(R.drawable.alternative_icon_2),
    )
    Text(
        modifier = Modifier.padding(top = 16.dp),
        style = MaterialTheme.typography.bodyLarge,
        text = stringResource(R.string.error)
    )
    if (additionalText != null) {
        Text(
            modifier= Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            text = additionalText
        )
    }
}

@Composable
fun WaifuItem(waifuEntity: WaifuEntityV1) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .dropShadowLight(shape = cornerMedium)
            .borderSmall(shape = cornerMedium)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = cornerMedium
            )
            .clip(shape = cornerMedium)
            .clickable(onClick = {}),
        content = {
            val imageLoader = ImageLoader.Builder(LocalContext.current)
                .logger(DebugLogger())
                .build()
            val imageRequest = ImageRequest.Builder(LocalContext.current)
                .data(waifuEntity.url)
                .crossfade(true)
                .build()
            var trigger by remember { mutableStateOf(false) }

            key(trigger) {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(256.dp)
                        .then(rememberConstraintsSizeResolver()),
                    model = imageRequest,
                    filterQuality = FilterQuality.Medium,
                    imageLoader = imageLoader,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Waifu drawn by ${waifuEntity.artistName}",
                    loading = {
                        Box(
                            contentAlignment = Alignment.Center,
                            content = {
                                ContentLoading()
                                CircularProgressIndicator()
                            }
                        )
                    },
                    success = {
                        SubcomposeAsyncImageContent()
                    },
                    error = {
                        ContentError(
                            additionalText = stringResource(R.string.retry_in_3s)
                        )
                        LaunchedEffect(
                            key1 = Unit,
                            block = {
                                launch(Dispatchers.Default) {
                                    delay(3000)
                                    trigger = !trigger
                                }
                            }
                        )
                    }
                )
            }
            HorizontalDivider()
            Text(
                modifier = Modifier.padding(16.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge,
                text = waifuEntity.artistName
            )
        }
    )
}

/**
 * =================================================================================================
 * Contents
 * =================================================================================================
 */
@Composable
fun ContentLoading(loadingText: String = stringResource(R.string.loading)) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoadingItem(loadingText = loadingText)
    }
}

@Composable
fun ContentError(
    additionalText: String? = null,
    errorCallback: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {
            ErrorItem(additionalText = additionalText)
            if (errorCallback != null) {
                ButtonPrimary(
                    text = stringResource(R.string.retry),
                    callback = {
                        errorCallback.invoke()
                    }
                )
            }
        }
    )
}

@Composable
fun ContentSuccess(
    contentPadding: PaddingValues,
    waifuEntity: List<WaifuEntityV1>
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = contentPadding,
        columns = GridCells.Adaptive(128.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            items(
                items = waifuEntity,
                itemContent = { item ->
                    WaifuItem(item)
                }
            )
        }
    )
}

@Composable
fun Content(
    contentPadding: PaddingValues = PaddingValues(
        top = 16.dp,
        bottom = 16.dp,
        start = 16.dp,
        end = 16.dp
    ),
    waifuEntity: ApiState<List<WaifuEntityV1>>,
    contentErrorCallback: () -> Unit
) {
    when (waifuEntity) {
        is ApiState.Loading -> {
            ContentLoading(
                loadingText = stringResource(R.string.loading)
            )
        }
        is ApiState.Success<List<WaifuEntityV1>> -> {
            ContentSuccess(
                contentPadding = contentPadding,
                waifuEntity = waifuEntity.data
            )
        }
        is ApiState.Error -> {
            ContentError(
                errorCallback = contentErrorCallback
            )
        }
    }
}

/**
 * =================================================================================================
 * Screen types
 * =================================================================================================
 */
@Composable
fun HomeScreenCompact(
    content: @Composable (() -> Unit),
    navigateToHelpScreen: () -> Unit,
    navigateToSettingsScreen: () -> Unit,
    navigateToAboutScreen: () -> Unit
) {
    PortraitScaffold(
        topAppBar = {
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
        },
        content = {
            content.invoke()
        }
    )
}

@Composable
fun HomeScreenMedium(
    content: @Composable (() -> Unit),
    navigateToHelpScreen: () -> Unit,
    navigateToSettingsScreen: () -> Unit,
    navigateToAboutScreen: () -> Unit
) {
    LandscapeScaffold(
        sideAppBar = {
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
            content.invoke()
        }
    )
}

@Composable
fun HomeScreenExpanded() {
}

/**
 * =================================================================================================
 * Root screen
 * =================================================================================================
 *
 * Magic numbers:
 * 1. 88.dp represents the height of MeinTopAppBar's height (72.dp) + the content padding itself
 *    (16.dp) for better UI consitency.
 */
@Composable
fun HomeScreen(
    windowSizeClass: WindowSizeClass,
    waifuEntity: ApiState<List<WaifuEntityV1>>,
    navigateToHelpScreen: () -> Unit,
    navigateToSettingsScreen: () -> Unit,
    navigateToAboutScreen: () -> Unit,
    contentErrorCallback: () -> Unit
) {
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            HomeScreenCompact(
                content = {
                    Content(
                        contentPadding = PaddingValues(
                            top = 88.dp,
                            bottom = 16.dp,
                            start = 16.dp,
                            end = 16.dp
                        ),
                        waifuEntity = waifuEntity,
                        contentErrorCallback = contentErrorCallback
                    )
                },
                navigateToHelpScreen = navigateToHelpScreen,
                navigateToSettingsScreen = navigateToSettingsScreen,
                navigateToAboutScreen = navigateToAboutScreen
            )
        }
        WindowWidthSizeClass.Medium -> {
            HomeScreenMedium(
                content = {
                    Content(
                        waifuEntity = waifuEntity,
                        contentErrorCallback = contentErrorCallback
                    )
                },
                navigateToHelpScreen = navigateToHelpScreen,
                navigateToSettingsScreen = navigateToSettingsScreen,
                navigateToAboutScreen = navigateToAboutScreen
            )
        }
        WindowWidthSizeClass.Expanded -> {
            HomeScreenMedium(
                content = {
                    Content(
                        waifuEntity = waifuEntity,
                        contentErrorCallback = contentErrorCallback
                    )
                },
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
fun getWaifus(): List<WaifuEntityV1> {
    val waifus = mutableListOf<WaifuEntityV1>()

    waifus.add(WaifuEntityV1("abc.com", "abc", "best waifu", "best waifu"))
    waifus.add(WaifuEntityV1("cde.com", "cde", "meh waifu", "meh waifu"))
    waifus.add(WaifuEntityV1("efg.com", "efg", "hmm waifu", "hmm waifu"))
    waifus.add(WaifuEntityV1("ghi.com", "ghi", "owh waifu", "owh waifu"))
    waifus.add(WaifuEntityV1("abc.com", "abc", "best waifu", "best waifu"))
    waifus.add(WaifuEntityV1("cde.com", "cde", "meh waifu", "meh waifu"))
    waifus.add(WaifuEntityV1("efg.com", "efg", "hmm waifu", "hmm waifu"))
    waifus.add(WaifuEntityV1("ghi.com", "ghi", "owh waifu", "owh waifu"))
    return waifus
}

@Preview(
    name = "Compact screen",
    device = "spec:width=411dp,height=891dp",
    showSystemUi = true
)

@Composable
fun HomeScreenCompactPreview() {
    HomeScreenCompact(
        content = {
            Content(
                contentPadding = PaddingValues(
                    top = 88.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                ),
                waifuEntity = ApiState.Success(data = getWaifus()),
                contentErrorCallback = {}
            )
        },
        {},
        {},
        {}
    )
}

@Preview(
    name = "Medium screen",
    device = "spec:width=411dp,height=891dp,orientation=landscape",
    showSystemUi = true
)
@Composable
fun HomeScreenMediumPreview() {
    HomeScreenMedium(
        content = {
            Content(
                waifuEntity = ApiState.Success(data = getWaifus()),
                contentErrorCallback = {}
            )
        },
        {},
        {},
        {}
    )
}

@Preview
@Composable
fun HomeScreenCompactContentLoadingPreview() {
    ContentLoading()
}

@Preview
@Composable
fun HomeScreenCompactContentErrorPreview() {
    ContentError()
}
