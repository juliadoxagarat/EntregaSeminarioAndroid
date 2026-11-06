package ar.edu.unicen.loverboxd.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unicen.loverboxd.data.model.MovieSummary
import ar.edu.unicen.loverboxd.data.repository.MovieRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PopularMoviesViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _state = MutableStateFlow<UiState<List<MovieSummary>>>(UiState.Loading)
    val state: StateFlow<UiState<List<MovieSummary>>> = _state.asStateFlow()

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                // suspend: no bloquea el hilo de la interfaz mientras espera la API.
                _state.value = UiState.Success(repository.getPopularMovies())
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _state.value = UiState.Failure(errorFrom(e))
            }
        }
    }
}
