package ar.edu.unicen.loverboxd.data.repository

import ar.edu.unicen.loverboxd.BuildConfig
import ar.edu.unicen.loverboxd.data.model.MovieDetails
import ar.edu.unicen.loverboxd.data.model.MovieSummary
import ar.edu.unicen.loverboxd.data.remote.TmdbApi
import java.util.Locale

class MissingTmdbTokenException : Exception()

// La UI nunca consulta Retrofit directamente: siempre pasa por el repositorio.
class MovieRepository(private val api: TmdbApi) {

    suspend fun getPopularMovies(): List<MovieSummary> {
        checkToken()
        return api.getPopularMovies(deviceLanguage()).results
    }

    suspend fun getMovieDetails(id: Int): MovieDetails {
        checkToken()
        return api.getMovieDetails(id, deviceLanguage())
    }

    private fun checkToken() {
        if (BuildConfig.TMDB_TOKEN.isBlank()) throw MissingTmdbTokenException()
    }

    // El enunciado exige probar español e inglés. El idioma de la API
    // acompaña el idioma general del teléfono sin hacer una pantalla extra.
    private fun deviceLanguage(): String {
        return if (Locale.getDefault().language == "es") "es-ES" else "en-US"
    }
}
