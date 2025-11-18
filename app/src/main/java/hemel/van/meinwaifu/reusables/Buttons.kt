package hemel.van.meinwaifu.reusables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ButtonPrimary(
    enabled: Boolean = true,
    text: String,
    callback: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val clicked by interactionSource.collectIsPressedAsState()
    val buttonColor by animateColorAsState(
        targetValue = if (clicked) MaterialTheme.colorScheme.surface
            else MaterialTheme.colorScheme.primary,
        animationSpec = spring()
    )
    val textColor by animateColorAsState(
        targetValue = if (clicked) MaterialTheme.colorScheme.onSurface
            else MaterialTheme.colorScheme.surface,
        animationSpec = spring()
    )

    Box(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .height(64.dp)
            .width(256.dp)
            .clip(shape = cornerMedium)
            .dropShadowLight(shape = cornerMedium)
            .borderSmall(shape = cornerMedium)
            .background(color = buttonColor)
            .clickable(
                interactionSource = interactionSource,
                onClick = callback
            ),
        contentAlignment = Alignment.Center,
        content = {
            Text(
                color = textColor,
                text = text
            )
        }
    )
}

@Preview
@Composable
fun ButtonPrimaryPreview() {
    ButtonPrimary(text = "Example button") {}
}
