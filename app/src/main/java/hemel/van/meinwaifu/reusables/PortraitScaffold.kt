package hemel.van.meinwaifu.reusables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hemel.van.meinwaifu.R

@Composable
fun PortraitScaffold(
    topAppBar: @Composable (() -> Unit),
    content: @Composable (() -> Unit)
) {
    Box(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.statusBars),
        content = {
            topAppBar.invoke()
        }
    )
    Box(
        modifier = Modifier
            .padding(top = 72.dp)
            .padding(horizontal = 16.dp)
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars),
        content = {
            content.invoke()
            ScaffoldDefaults.contentWindowInsets
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
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text("This is example text.")
            }
        }
    )
}
