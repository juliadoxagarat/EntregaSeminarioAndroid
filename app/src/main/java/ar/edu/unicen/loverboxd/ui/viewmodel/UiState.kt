package ar.edu.unicen.loverboxd.ui.viewmodel

import ar.edu.unicen.loverboxd.data.repository.MissingTmdbTokenException
import java.io.IOException
import retrofit2.HttpException

// Cada pantalla está en uno de estos 3 estados: carga, éxito o error.
sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Failure(val error: UiError) : UiState<Nothing>
}

enum class UiError {
    NO_CONNECTION,
    TOKEN_MISSING,
    TOKEN_INVALID,
    GENERIC
}

// Separa las causas técnicas de los mensajes que verá la persona.
fun errorFrom(exception: Exception): UiError = when (exception) {
    is MissingTmdbTokenException -> UiError.TOKEN_MISSING
    is HttpException -> if (exception.code() == 401 || exception.code() == 403) {
        UiError.TOKEN_INVALID
    } else {
        UiError.GENERIC
    }
    is IOException -> UiError.NO_CONNECTION
    else -> UiError.GENERIC
}
