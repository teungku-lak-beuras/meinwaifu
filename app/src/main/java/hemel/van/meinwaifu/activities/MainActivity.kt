package hemel.van.meinwaifu.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import hemel.van.meinwaifu.composes.MeinWaifu
import hemel.van.meinwaifu.theme.AppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            // Important: don't forget to return false or else the splash screen would be eternal.
            setKeepOnScreenCondition {
                // Delete the sleep code below and replace with your heavy lifting code here.
                // Don't forget to return false (last line in this block of code), cause if you
                // don't then the app will stuck in the splash screen for eternity.
                //Thread.sleep(200)
                false
            }
        }
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            AppTheme {
                val windowSizeClass = calculateWindowSizeClass(this)

                MeinWaifu(windowSizeClass)
            }
        }
    }
}
