package cl.mobistore.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Define los colores
val DarkBlue = Color(0xFF252243)
val BrightBlue = Color(0xFF5161F1)
val Cyan = Color(0xFF63E9F8)
val White = Color(0xFFFFFFFF)

// Esquema de colores para modo claro
private val LightColorScheme = lightColorScheme(
    primary = BrightBlue,
    secondary = Cyan,
    background = White,
    surface = White,
    onPrimary = White,
    onSecondary = DarkBlue,
    onBackground = DarkBlue,
    onSurface = DarkBlue
)

// Esquema de colores para modo oscuro
private val DarkColorScheme = darkColorScheme(
    primary = BrightBlue,
    secondary = Cyan,
    background = DarkBlue,
    surface = DarkBlue,
    onPrimary = White,
    onSecondary = DarkBlue,
    onBackground = White,
    onSurface = White
)

@Composable
fun MobiStoreTheme(
    darkTheme: Boolean = false,  // Cambia a `isSystemInDarkTheme()` para usar modo oscuro
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
