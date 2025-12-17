package fr.hainu.cinetrack.data.remote.models

import com.google.gson.annotations.SerializedName

/**
 * DTO pour la réponse de liste de films TMDB
 */
data class TmdbMovieListResponseDto(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<TmdbMovieDto>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

/**
 * DTO pour un film TMDB (version liste)
 */
data class TmdbMovieDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("overview") val overview: String?
)

/**
 * DTO pour les détails complets d'un film TMDB
 */
data class TmdbMovieDetailsDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("genres") val genres: List<TmdbGenreDto>,
    @SerializedName("videos") val videos: TmdbVideosResponseDto?,
    @SerializedName("credits") val credits: TmdbCreditsDto?
)

/**
 * DTO pour un genre TMDB
 */
data class TmdbGenreDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

/**
 * DTO pour la réponse des vidéos TMDB
 */
data class TmdbVideosResponseDto(
    @SerializedName("results") val results: List<TmdbVideoDto>
)

/**
 * DTO pour une vidéo TMDB
 */
data class TmdbVideoDto(
    @SerializedName("key") val key: String,
    @SerializedName("site") val site: String,
    @SerializedName("type") val type: String,
    @SerializedName("official") val official: Boolean?
)

/**
 * DTO pour les crédits TMDB (cast & crew)
 */
data class TmdbCreditsDto(
    @SerializedName("cast") val cast: List<TmdbCastDto>
)

/**
 * DTO pour un membre du cast TMDB
 */
data class TmdbCastDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("profile_path") val profilePath: String?,
    @SerializedName("character") val character: String?
)
