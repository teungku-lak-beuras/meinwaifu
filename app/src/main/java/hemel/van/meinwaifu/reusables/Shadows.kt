package hemel.van.meinwaifu.reusables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.dropShadowLight(shape: Shape): Modifier {
    return this.dropShadow(
        shape = shape,
        shadow = Shadow(
            radius = 4.dp,
            spread = 2.dp,
            offset = DpOffset(2.dp, 2.dp),
            alpha = 0.3f,
            color = MaterialTheme.colorScheme.outline
        )
    )
}
