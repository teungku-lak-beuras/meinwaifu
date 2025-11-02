package hemel.van.meinwaifu.reusables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hemel.van.meinwaifu.R

@Composable
fun MeinTopAppBar(
    @StringRes title: Int,
    @DrawableRes leftIconId: Int,
    @StringRes leftIconContentDescriptionId: Int? = null,
    @StringRes rightIconContentDescription: Int? = null,
    leftIconCallback: (() -> Unit)? = null,
    rightIconCallback: (() -> Unit)? = null
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
                    .clickable {}
            ) {
                Image(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(40.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.Crop,
                    contentDescription = if (leftIconContentDescriptionId != null)
                        stringResource(leftIconContentDescriptionId)
                        else null,
                    painter = painterResource(leftIconId)
                )
            }
            Text(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .paddingFromBaseline(top = 48.dp, bottom = 24.dp),
                style = MaterialTheme.typography.titleMedium,
                text = stringResource(title)
            )
            if (rightIconContentDescription != null) {
                Box(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(56.dp)
                        .background(color = Color.Transparent, shape = CircleShape)
                        .align(Alignment.CenterVertically)
                        .clip(CircleShape)
                        .clickable {}
                ) {
                    Icon(
                        modifier = Modifier.align(Alignment.Center),
                        contentDescription = stringResource(rightIconContentDescription),
                        tint = MaterialTheme.colorScheme.surface,
                        imageVector = Icons.Filled.Menu
                    )
                }
            }
        }
    }
}

@Preview(device = "spec:width=411dp,height=891dp")
@Composable
fun MeinTopAppBarPreview() {
    MeinTopAppBar(
        title = R.string.app_name,
        leftIconId = R.drawable.main_icon_square,
        leftIconContentDescriptionId = R.string.app_bar_navigation_icon,
        rightIconContentDescription = R.string.app_bar_menu_more
    )
}
