package hemel.van.meinwaifu.reusables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hemel.van.meinwaifu.R

/**
 * Mein Waifu's app bar for landscape screen.
 */
@Composable
fun MeinSideAppBar(
    title: String,
    logo: Painter,
    logoContentDescription: String,
    logoCallback: (() -> Unit) = {},
    dropDown: @Composable (() -> Unit) = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .width(72.dp),
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 0.dp,
            bottomEnd = 24.dp
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(56.dp)
                    .background(color = Color.Transparent, shape = CircleShape)
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                dropDown.invoke()
            }
            Box(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .size(56.dp)
                    .background(color = Color.Transparent, shape = CircleShape)
                    .clip(CircleShape)
                    .clickable { logoCallback.invoke() },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(40.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = logoContentDescription,
                    painter = logo
                )
            }
        }
    }
}

@Preview(name = "Without dropdwon", device = "spec:width=411dp,height=891dp,orientation=landscape")
@Composable
fun MeinSideAppBarWithoutDropdownPreview() {
    MeinSideAppBar(
        title = "Example string",
        logo = painterResource(R.drawable.main_icon_square),
        logoContentDescription = "Example string",
    )
}

@Preview(name = "With dropdwon", device = "spec:width=411dp,height=891dp,orientation=landscape")
@Composable
fun MeinSideAppBarWithDropdownPreview() {
    MeinSideAppBar(
        title = "Example string",
        logo = painterResource(R.drawable.main_icon_square),
        logoContentDescription = "Example string",
        dropDown = {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    contentDescription = stringResource(R.string.app_bar_menu_more),
                    imageVector = Icons.Filled.Menu
                )
            }
            DropdownMenu(
                expanded = true,
                onDismissRequest = {},
                shape = MaterialTheme.shapes.large,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
            ) {
                DropdownMenuItem(
                    onClick = {},
                    text = { Text(stringResource(R.string.screen_help)) }
                )
                HorizontalDivider()
                DropdownMenuItem(
                    onClick = {},
                    text = { Text(stringResource(R.string.screen_settings)) }
                )
                HorizontalDivider()
                DropdownMenuItem(
                    onClick = {},
                    text = { Text(stringResource(R.string.screen_about)) }
                )
            }
        }
    )
}
