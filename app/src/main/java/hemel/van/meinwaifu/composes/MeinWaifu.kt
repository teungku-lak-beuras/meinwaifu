package hemel.van.meinwaifu.composes

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hemel.van.meincore.repository.NekosBestApiRepository
import hemel.van.meinwaifu.viewmodels.MainViewModel
import hemel.van.meinwaifu.viewmodels.factories.MainViewModelFactory

@Composable
fun MeinWaifu(windowSizeClass: WindowSizeClass) {
    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel(
        factory = MainViewModelFactory(
            nekosBestApiRepository = NekosBestApiRepository()
        )
    )
    val waifuEntity by viewModel.waifuEntity.collectAsState()

    NavHost(
        navController = navController,
        startDestination = "home",
        enterTransition = {
            slideInHorizontally(animationSpec = tween(durationMillis = 300)) { fullWidth ->
                fullWidth
            }
        },
        exitTransition = {
            slideOutHorizontally(animationSpec = tween(durationMillis = 300)) { fullWidth ->
                -fullWidth
            }
        },
        popEnterTransition = {
            slideInHorizontally(animationSpec = tween(durationMillis = 300)) { fullWidth ->
                -fullWidth
            }
        },
        popExitTransition = {
            slideOutHorizontally(animationSpec = tween(durationMillis = 300)) { fullWidth ->
                fullWidth
            }
        }
    ) {
        composable("home") {
            HomeScreen(
                windowSizeClass = windowSizeClass,
                waifuEntity = waifuEntity,
                navigateToHelpScreen = {
                    navController.navigate("help")
                },
                navigateToSettingsScreen = {
                    navController.navigate("settings")
                },
                navigateToAboutScreen = {
                    navController.navigate("about")
                },
                contentErrorCallback = {
                    viewModel.getWaifu()
                }
            )
        }
        composable("help") {
            HelpScreen(
                windowSizeClass = windowSizeClass,
                navigateToHomeScreen = {
                    navController.popBackStack()
                }
            )
        }
        composable("settings") {
            SettingsScreen(
                windowSizeClass = windowSizeClass,
                navigateToHomeScreen = {
                    navController.popBackStack()
                }
            )
        }
        composable("about") {
            AboutScreen(
                windowSizeClass = windowSizeClass,
                navigateToHomeScreen = {
                    navController.popBackStack()
                }
            )
        }
    }
}
