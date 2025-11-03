package hemel.van.meinwaifu.reusables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
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
 * This is Mein Waifu's app bar. Currently programmed for compact screen only and does not accept
 * modifier.
 */
@Composable
fun MeinTopAppBar(
    title: String,
    logo: Painter,
    logoContentDescription: String,
    logoCallback: (() -> Unit) = {},
    dropDown: @Composable (() -> Unit) = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .size(72.dp),
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 16.dp,
            bottomEnd = 16.dp
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(56.dp)
                    .background(color = Color.Transparent, shape = CircleShape)
                    .align(Alignment.CenterVertically)
                    .clip(CircleShape)
                    .clickable { logoCallback.invoke() }
            ) {
                Image(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(40.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.Crop,
                    contentDescription = logoContentDescription,
                    painter = logo
                )
            }
            Text(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .paddingFromBaseline(top = 48.dp, bottom = 24.dp),
                style = MaterialTheme.typography.titleMedium,
                text = title
            )
            Row(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(56.dp)
                    .background(color = Color.Transparent, shape = CircleShape)
                    .align(Alignment.CenterVertically)
            ) {
                dropDown.invoke()
            }
        }
    }
}

@Preview(name = "Without dropdwon", device = "spec:width=411dp,height=891dp")
@Composable
fun MeinTopAppBarWithoutDropdownPreview() {
    MeinTopAppBar(
        title = "Example string",
        logo = painterResource(R.drawable.main_icon_square),
        logoContentDescription = "Example content description"
    )
}

@Preview(name = "With dropdwon", device = "spec:width=411dp,height=891dp")
@Composable
fun MeinTopAppBarWithDropdownPreview() {
    MeinTopAppBar(
        title = "Example string",
        logo = painterResource(R.drawable.main_icon_square),
        logoContentDescription = "Example content description",
        dropDown = {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
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
        }
    )
}
