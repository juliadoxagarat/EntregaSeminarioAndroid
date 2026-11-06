package ar.edu.unicen.loverboxd.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ar.edu.unicen.loverboxd.R
import ar.edu.unicen.loverboxd.data.model.MovieDetails
import ar.edu.unicen.loverboxd.ui.components.ErrorContent
import ar.edu.unicen.loverboxd.ui.components.LoadingContent
import ar.edu.unicen.loverboxd.ui.components.PosterImage
import ar.edu.unicen.loverboxd.ui.viewmodel.MovieDetailViewModel
import ar.edu.unicen.loverboxd.ui.viewmodel.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(viewModel: MovieDetailViewModel, onBack: () -> Unit) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.detail_title)) },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text(stringResource(R.string.back))
                    }
                }
            )
        }
    ) { innerPadding ->
        when (val currentState = state) {
            UiState.Loading -> LoadingContent(
                message = stringResource(R.string.loading_details),
                modifier = Modifier.padding(innerPadding)
            )
            is UiState.Failure -> ErrorContent(
                error = currentState.error,
                onRetry = viewModel::loadDetails,
                modifier = Modifier.padding(innerPadding)
            )
            is UiState.Success -> MovieDetailContent(
                movie = currentState.data,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
private fun MovieDetailContent(movie: MovieDetails, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Column(modifier = Modifier.widthIn(max = 720.dp).fillMaxWidth()) {
                val genresText = movie.genres.orEmpty().joinToString(", ") { it.name }
                val synopsisText = movie.overview.orEmpty()

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    PosterImage(
                        posterPath = movie.posterPath,
                        description = stringResource(R.string.poster_description, movie.title),
                        modifier = Modifier.width(132.dp).height(198.dp)
                    )
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = movie.title,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(12.dp))
                        Text(
                            text = stringResource(R.string.rating_format, movie.rating),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                Spacer(Modifier.height(24.dp))
                Text(
                    text = stringResource(R.string.genres_label),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = if (genresText.isBlank()) stringResource(R.string.no_genres) else genresText,
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(Modifier.height(24.dp))
                Text(
                    text = stringResource(R.string.synopsis_label),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = if (synopsisText.isBlank()) stringResource(R.string.no_synopsis) else synopsisText,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(Modifier.height(24.dp))
                Text(
                    text = stringResource(R.string.tmdb_credit),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
