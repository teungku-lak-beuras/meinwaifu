package hemel.van.meinwaifu.composes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import coil3.compose.rememberConstraintsSizeResolver
import coil3.imageLoader
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.util.DebugLogger
import hemel.van.meincore.entitity.NekosBestWaifuEntity
import hemel.van.meincore.repository.NekosBestApiRepository
import hemel.van.meincore.repository.utilities.ApiState
import hemel.van.meinwaifu.R
import hemel.van.meinwaifu.reusables.LandscapeScaffold
import hemel.van.meinwaifu.reusables.MeinSideAppBar
import hemel.van.meinwaifu.reusables.MeinTopAppBar
import hemel.van.meinwaifu.reusables.PortraitScaffold
import hemel.van.meinwaifu.viewmodels.MainViewModel
import hemel.van.meinwaifu.viewmodels.factories.MainViewModelFactory

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
fun ContentLoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(128.dp),
            painter = painterResource(R.drawable.main_icon),
            contentDescription = stringResource(R.string.loading)
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.bodyLarge,
            text = stringResource(R.string.loading)
        )
    }
}

@Composable
fun ContentError() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(128.dp),
            painter = painterResource(R.drawable.main_icon),
            contentDescription = stringResource(R.string.loading)
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.bodyLarge,
            text = stringResource(R.string.error)
        )
    }
}

@Composable
fun WaifuItem(nekosBestWaifuEntity: NekosBestWaifuEntity) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        shadowElevation = 2.dp,
        shape = RoundedCornerShape(24.dp),
        content = {
            Column(
                content = {
                    val imageLoader = LocalContext.current.imageLoader.newBuilder()
                        .logger(DebugLogger())
                        .build()
                    val sizeResolver = rememberConstraintsSizeResolver()

                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .then(sizeResolver)
                            .fillMaxWidth()
                            .height(256.dp),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(nekosBestWaifuEntity.url)
                            .crossfade(true)
                            .build(),
                        filterQuality = FilterQuality.Medium,
                        imageLoader = imageLoader,
                        contentScale = ContentScale.Crop,
                        contentDescription = "A waifu drawn by ${nekosBestWaifuEntity.artistName}",
                        content = {
                            val state by painter.state.collectAsState()

                            when (state) {
                                is AsyncImagePainter.State.Loading -> {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        content = {
                                            ContentLoading()
                                            CircularProgressIndicator()
                                        }
                                    )
                                }
                                is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
                                else -> ContentError()
                            }
                        }
                    )
                    Text(
                        modifier = Modifier.padding(16.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge,
                        text = nekosBestWaifuEntity.artistName
                    )
                }
            )
        }
    )
}

@Composable
fun ContentSuccess(
    nekosBestApiEntity: List<NekosBestWaifuEntity>
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp),
        columns = GridCells.Adaptive(128.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            items(nekosBestApiEntity) { item ->
                WaifuItem(item)
            }
        }
    )
}

@Composable
fun HomeScreenCompactContent(
    nekosBestApiEntity: ApiState<List<NekosBestWaifuEntity>>
) {
    when (nekosBestApiEntity) {
        is ApiState.Loading -> ContentLoading()
        is ApiState.Success -> ContentSuccess(
            nekosBestApiEntity = nekosBestApiEntity.data
        )
        is ApiState.Error -> ContentError()
    }
}

@Composable
fun HomeScreenCompact(
    nekosBestApiEntity: ApiState<List<NekosBestWaifuEntity>> = ApiState.Loading,
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
            HomeScreenCompactContent(
                nekosBestApiEntity = nekosBestApiEntity
            )
        }
    )
}

@Composable
fun HomeScreenMedium(
    nekosBestApiEntity: ApiState<List<NekosBestWaifuEntity>> = ApiState.Loading,
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
            HomeScreenCompactContent(nekosBestApiEntity = nekosBestApiEntity)
        }
    )
}

@Composable
fun HomeScreenExpanded() {
}

@Composable
fun HomeScreen(
    viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(nekosBestApiRepository = NekosBestApiRepository())),
    windowSizeClass: WindowSizeClass,
    navigateToHelpScreen: () -> Unit,
    navigateToSettingsScreen: () -> Unit,
    navigateToAboutScreen: () -> Unit
) {
    val nekosBestApiEntity by viewModel.nekosBestWaifus.collectAsState()

    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            HomeScreenCompact(
                nekosBestApiEntity = nekosBestApiEntity,
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
fun getWaifus(): List<NekosBestWaifuEntity> {
    val waifus = mutableListOf<NekosBestWaifuEntity>()

    waifus.add(NekosBestWaifuEntity("abc.com", "abc", "best waifu", "best waifu"))
    waifus.add(NekosBestWaifuEntity("cde.com", "cde", "meh waifu", "meh waifu"))
    waifus.add(NekosBestWaifuEntity("efg.com", "efg", "hmm waifu", "hmm waifu"))
    waifus.add(NekosBestWaifuEntity("ghi.com", "ghi", "owh waifu", "owh waifu"))
    return waifus
}

@Preview(
    name = "Compact screen",
    device = "spec:width=411dp,height=891dp",
    showSystemUi = true
)

@Composable
fun HomeScreenCompactPreview() {
    HomeScreenCompact(ApiState.Success(data = getWaifus()), {}, {}, {})
}

@Preview(
    name = "Medium screen",
    device = "spec:width=411dp,height=891dp,orientation=landscape",
    showSystemUi = true
)
@Composable
fun HomeScreenMediumPreview() {
    HomeScreenMedium(ApiState.Success(data = getWaifus()), {}, {}, {})
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
