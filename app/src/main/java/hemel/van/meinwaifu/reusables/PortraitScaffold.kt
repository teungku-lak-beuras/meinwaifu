package hemel.van.meinwaifu.reusables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import hemel.van.meinwaifu.R
import hemel.van.meinwaifu.composes.ContentLoading

@Composable
fun PortraitScaffold(
    topAppBar: @Composable (() -> Unit),
    content: @Composable (() -> Unit)
) {
    Box(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .fillMaxSize(),
        content = {
            content.invoke()
        }
    )
    Box(
        modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
        content = {
            topAppBar.invoke()
        }
    )
}

@Preview(device = "spec:width=411dp,height=891dp", showSystemUi = true)
@Composable
fun PortraitScaffoldPreview() {
    PortraitScaffold(
        topAppBar = {
            MeinTopAppBar(
                title = "Example string",
                logo = painterResource(R.drawable.main_icon_square),
                logoContentDescription = "Example string"
            )
        },
        content = {
            ContentLoading()
        }
    )
}
