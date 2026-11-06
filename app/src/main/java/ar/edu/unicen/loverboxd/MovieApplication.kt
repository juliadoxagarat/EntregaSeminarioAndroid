package ar.edu.unicen.loverboxd

import android.app.Application
import ar.edu.unicen.loverboxd.data.remote.RetrofitClient
import ar.edu.unicen.loverboxd.data.repository.MovieRepository

// Se crea una sola vez mientras está abierto el proceso de nuestra app.
class MovieApplication : Application() {
    val repository: MovieRepository by lazy {
        MovieRepository(RetrofitClient.api)
    }
}
