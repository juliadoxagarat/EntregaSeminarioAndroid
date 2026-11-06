package ar.edu.unicen.loverboxd.data.remote

import ar.edu.unicen.loverboxd.data.model.MovieDetails
import ar.edu.unicen.loverboxd.data.model.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Retrofit convierte estas funciones en solicitudes HTTP a TMDB.
interface TmdbApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String
    ): PopularMoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): MovieDetails
}
