package ar.edu.unicen.loverboxd.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ar.edu.unicen.loverboxd.data.repository.MovieRepository

// Los ViewModels necesitan recibir el mismo Repository.
// Una Factory permite crearlos pasándoles ese objeto por el constructor.
class MovieViewModelFactory(
    private val repository: MovieRepository,
    private val movieId: Int? = null
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(PopularMoviesViewModel::class.java) -> {
                PopularMoviesViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MovieDetailViewModel::class.java) && movieId != null -> {
                MovieDetailViewModel(repository, movieId) as T
            }
            else -> throw IllegalArgumentException("ViewModel desconocido: $modelClass")
        }
    }
}
