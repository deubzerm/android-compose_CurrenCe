@file:Suppress("DEPRECATION")

package net.deubzer.app.currence.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

private val DarkColorScheme = darkColorScheme(
    primary = Dark1,
    secondary = Dark2,
    tertiary = Dark3,
    background = Color(0xFF272727)
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = Color(0xFF9E2222)

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun JetpacktutorialTheme(
darkTheme: Boolean = isSystemInDarkTheme(),
content: @Composable () -> Unit
) {

    MaterialTheme(
        //colorScheme = colorScheme,

        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = {
            ProvideTextStyle(
                value = TextStyle(color = Color.Black),
                content = content)
        }
    )
}