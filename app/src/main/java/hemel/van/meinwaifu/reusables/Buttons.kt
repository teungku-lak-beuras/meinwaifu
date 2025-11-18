package hemel.van.meinwaifu.reusables

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonPrimary(
    enabled: Boolean = true,
    text: String,
    callback: () -> Unit
) {
    Button(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .height(64.dp)
            .width(256.dp)
            .dropShadowLight(shape = cornerMedium)
            .borderSmall(shape = cornerMedium),
        shape = cornerMedium,
        enabled = enabled,
        onClick = callback,
        content = {
            Text(text)
        }
    )
}
