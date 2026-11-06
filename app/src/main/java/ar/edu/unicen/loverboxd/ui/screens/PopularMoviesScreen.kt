package ar.edu.unicen.loverboxd.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import ar.edu.unicen.loverboxd.data.model.MovieSummary
import ar.edu.unicen.loverboxd.ui.components.ErrorContent
import ar.edu.unicen.loverboxd.ui.components.LoadingContent
import ar.edu.unicen.loverboxd.ui.components.PosterImage
import ar.edu.unicen.loverboxd.ui.viewmodel.PopularMoviesViewModel
import ar.edu.unicen.loverboxd.ui.viewmodel.UiState
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularMoviesScreen(
    viewModel: PopularMoviesViewModel,
    onMovieClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.popular_title)) })
        }
    ) { innerPadding ->
        // Compose redibuja esta sección cuando cambia el StateFlow del ViewModel.
        when (val currentState = state) {
            UiState.Loading -> LoadingContent(
                message = stringResource(R.string.loading),
                modifier = Modifier.padding(innerPadding)
            )
            is UiState.Failure -> ErrorContent(
                error = currentState.error,
                onRetry = viewModel::loadMovies,
                modifier = Modifier.padding(innerPadding)
            )
            is UiState.Success -> {
                PopularMoviesList(
                    movies = currentState.data,
                    onMovieClick = onMovieClick,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
private fun PopularMoviesList(
    movies: List<MovieSummary>,
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                stringResource(R.string.popular_subtitle),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        if (movies.isEmpty()) {
            item { Text(stringResource(R.string.no_movies)) }
        }

        items(items = movies, key = { it.id }) { movie ->
            Card(
                modifier = Modifier.fillMaxWidth().clickable { onMovieClick(movie.id) }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PosterImage(
                        posterPath = movie.posterPath,
                        description = stringResource(R.string.poster_description, movie.title),
                        modifier = Modifier.size(width = 92.dp, height = 138.dp)
                    )
                    Spacer(Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = movie.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = stringResource(R.string.rating_format, movie.rating),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        item {
            Text(
                text = stringResource(R.string.tmdb_credit),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
