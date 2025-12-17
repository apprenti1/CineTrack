package fr.hainu.cinetrack.data.mapper

import fr.hainu.cinetrack.BuildConfig
import fr.hainu.cinetrack.data.remote.models.TmdbCastDto
import fr.hainu.cinetrack.data.remote.models.TmdbMovieDetailsDto
import fr.hainu.cinetrack.data.remote.models.TmdbMovieDto
import fr.hainu.cinetrack.domain.models.CastMemberModel
import fr.hainu.cinetrack.domain.models.MovieModel

private const val IMAGE_BASE_URL = BuildConfig.TMDB_IMAGE_URL
private const val POSTER_SIZE = BuildConfig.TMDB_IMAGE_POSTERSIZE
private const val BACKDROP_SIZE = BuildConfig.TMDB_IMAGE_BACKDROPSIZE

/**
 * Mapper pour convertir TmdbMovieDto (liste) en MovieModel
 */
fun mapTmdbMovieDtoToModel(dto: TmdbMovieDto): MovieModel {
    return MovieModel(
        id = dto.id,
        title = dto.title,
        rating = dto.voteAverage,
        posterUrl = dto.posterPath?.let { "$IMAGE_BASE_URL$POSTER_SIZE$it" },
        backdropUrl = dto.backdropPath?.let { "$IMAGE_BASE_URL$BACKDROP_SIZE$it" },
        year = dto.releaseDate?.substringBefore("-") ?: "",
        ratingCoef = dto.voteCount,
        synopsis = dto.overview,
        isDetailed = false
    )
}

fun mapTmdbMovieDtosToModels(dtos: List<TmdbMovieDto>): List<MovieModel> {
    return dtos.map { mapTmdbMovieDtoToModel(it) }
}

/**
 * Mapper pour convertir TmdbMovieDetailsDto en MovieModel
 */
fun mapTmdbMovieDetailsDtoToModel(dto: TmdbMovieDetailsDto): MovieModel {
    // Genres formatés en string
    val genresString = dto.genres.joinToString(", ") { it.name }
    
    // Durée formatée
    val durationString = dto.runtime?.let { "${it / 60}h ${it % 60}min" } ?: ""
    
    // Cast mappé
    val castList = dto.credits?.cast?.map { mapTmdbCastDtoToModel(it) } ?: emptyList()
    
    // Trouver la bande-annonce YouTube
    val trailerUrl = dto.videos?.results
        ?.find { it.site == "YouTube" && it.type == "Trailer" }
        ?.let { "https://www.youtube.com/watch?v=${it.key}" } ?: ""
    
    return MovieModel(
        id = dto.id,
        title = dto.title,
        rating = dto.voteAverage,
        posterUrl = dto.posterPath?.let { "$IMAGE_BASE_URL$POSTER_SIZE$it" },
        backdropUrl = dto.backdropPath?.let { "$IMAGE_BASE_URL$BACKDROP_SIZE$it" },
        year = dto.releaseDate?.substringBefore("-") ?: "",
        genres = genresString,
        ratingCoef = dto.voteCount,
        duration = durationString,
        synopsis = dto.overview,
        trailerUrl = trailerUrl,
        cast = castList,
        isDetailed = true
    )
}

fun mapTmdbMovieDetailsDtosToModels(dtos: List<TmdbMovieDetailsDto>): List<MovieModel> {
    return dtos.map { mapTmdbMovieDetailsDtoToModel(it) }
}

/**
 * Mapper pour convertir TmdbCastDto en CastMemberModel
 */
fun mapTmdbCastDtoToModel(dto: TmdbCastDto): CastMemberModel {
    return CastMemberModel(
        name = dto.name,
        profilePictureUrl = dto.profilePath?.let { "${IMAGE_BASE_URL}w185$it" } ?: ""
    )
}
