package ar.edu.unicen.loverboxd.data.model

import com.google.gson.annotations.SerializedName

// Datos mínimos que TMDB devuelve para cada película popular.
data class MovieSummary(
    val id: Int,
    val title: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("vote_average") val rating: Double
)

data class PopularMoviesResponse(
    val results: List<MovieSummary>
)
