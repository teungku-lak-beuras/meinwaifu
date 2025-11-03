package hemel.van.meinwaifu.composes

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MeinWaifu(isLandscape: Boolean, windowSizeClass: WindowSizeClass) {
    val navController = rememberNavController()

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
                isLandscape = isLandscape,
                windowSizeClass = windowSizeClass,
                navigateToHelpScreen = {
                    navController.navigate("help")
                }
            )
        }
        composable("help") {
            HelpScreen(
                isLandscape = isLandscape,
                windowSizeClass = windowSizeClass,
                navigateToHomeScreen = {
                    navController.popBackStack()
                }
            )
        }
    }
}
