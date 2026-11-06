package ar.edu.unicen.loverboxd.data.model

import com.google.gson.annotations.SerializedName

// TMDB devuelve más campos, pero solo necesitamos estos para aprobar.
data class MovieDetails(
    val id: Int,
    val title: String,
    @SerializedName("poster_path") val posterPath: String?,
    val overview: String?,
    val genres: List<MovieGenre>?,
    @SerializedName("vote_average") val rating: Double
)

data class MovieGenre(
    val id: Int,
    val name: String
)
