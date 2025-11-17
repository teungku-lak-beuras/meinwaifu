package hemel.van.meinwaifu.reusables

import androidx.compose.foundation.border
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.borderSmall(shape: Shape): Modifier {
    return this.border(
        width = 1.dp,
        color = MaterialTheme.colorScheme.outline,
        shape = shape
    )
}
