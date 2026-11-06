package ar.edu.unicen.loverboxd.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColors = darkColorScheme(
    primary = CinemaRedLight,
    onPrimary = CinemaBlack,
    background = CinemaBlack,
    onBackground = Color.White,
    surface = CinemaBlack,
    onSurface = Color.White,
    surfaceVariant = CinemaDarkCard
)

private val LightColors = lightColorScheme(
    primary = CinemaRed,
    onPrimary = Color.White,
    background = CinemaOffWhite,
    onBackground = CinemaBlack,
    surface = CinemaOffWhite,
    onSurface = CinemaBlack,
    surfaceVariant = Color(0xFFF4E8EB)
)

@Composable
fun LoverboxdTheme(content: @Composable () -> Unit) {
    // Adapta los colores según el modo del sistema: claro u oscuro.
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) DarkColors else LightColors,
        content = content
    )
}
