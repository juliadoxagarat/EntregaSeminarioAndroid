package ar.edu.unicen.loverboxd.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ar.edu.unicen.loverboxd.R

@Composable
fun PosterImage(
    posterPath: String?,
    description: String,
    modifier: Modifier = Modifier
) {
    val styled = modifier.clip(RoundedCornerShape(12.dp))

    if (posterPath.isNullOrBlank()) {
        Box(
            modifier = styled.background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Text(stringResource(R.string.no_image))
        }
    } else {
        // TMDB devuelve un path, no una URL completa.
        // Coil descarga y muestra la portada sin bloquear la pantalla.
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500$posterPath",
            contentDescription = description,
            contentScale = ContentScale.Crop,
            modifier = styled
        )
    }
}
