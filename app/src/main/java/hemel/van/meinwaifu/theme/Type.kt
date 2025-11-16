package hemel.van.meinwaifu.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import hemel.van.meinwaifu.R

val fontFamily = FontFamily(
    Font(
        resId = R.font.balsamiq_sans_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.balsamiq_sans_bold,
        weight = FontWeight.Bold
    ),
    Font(
        resId = R.font.balsamiq_sans_italic,
        weight = FontWeight.Normal,
        style = FontStyle.Italic
    ),
    Font(
        resId = R.font.balsamiq_sans_bolditalic,
        weight = FontWeight.Bold,
        style = FontStyle.Italic
    )
)

val Typography = Typography(
    titleSmall = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    titleMedium = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    bodySmall = TextStyle(
        fontFamily = fontFamily,
        fontSize = 11.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = fontFamily,
        fontSize = 12.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = fontFamily,
        fontSize = 14.sp
    ),
)
