package hemel.van.meinwaifu.composes

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MeinWaifu(isLandscape: Boolean, windowSizeClass: WindowSizeClass) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                isLandscape = isLandscape,
                windowSizeClass = windowSizeClass
            )
        }
    }
}
