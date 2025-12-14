package fr.hainu.cinetrack.data.mapper

import fr.hainu.cinetrack.data.remote.models.MovieDto
import fr.hainu.cinetrack.data.mapper.mapReviewDtoToReviewModel
import fr.hainu.cinetrack.domain.models.MovieModel

fun mapMovieDtoToMovieModel(dto: MovieDto): MovieModel {
    return MovieModel(
        id = dto.id,
        title = dto.title,
        rating = dto.rating,
        posterUrl = dto.posterUrl,
        backdropUrl = dto.backdropUrl,
        year = dto.year,
        genres = dto.genres,
        ratingCoef = dto.ratingCoef,
        duration = dto.duration,
        synopsis = dto.synopsis,
        trailerUrl = dto.trailerUrl,
        cast = mapCastMemberDtoToModel(dto.cast),
        reviews = mapReviewDtoToReviewModel(dto.reviews),
        isOnFavorite = dto.isOnFavorite,
        isOnWatchlist = dto.isOnWatchlist,
        isOnWatched = dto.isOnWatched,
        isRated = dto.isRated,
        isDetailed = dto.isDetailed
    )
}
fun mapMovieDtoToMovieModel(dtos: List<MovieDto>): List<MovieModel> {
    return dtos.map { mapMovieDtoToMovieModel(it) }
}
fun mapMovieModelToMovieDto(model: MovieModel): MovieDto {
    return MovieDto(
        id = model.id,
        title = model.title,
        rating = model.rating,
        posterUrl = model.posterUrl,
        backdropUrl = model.backdropUrl,
        year = model.year,
        genres = model.genres,
        ratingCoef = model.ratingCoef,
        duration = model.duration,
        synopsis = model.synopsis,
        trailerUrl = model.trailerUrl,
        cast = mapCastMemberModelToDto(model.cast),
        reviews = mapReviewModelToReviewDto(model.reviews),
        isOnFavorite = model.isOnFavorite,
        isOnWatchlist = model.isOnWatchlist,
        isOnWatched = model.isOnWatched,
        isRated = model.isRated,
        isDetailed = model.isDetailed
    )
}
fun mapMovieModelToMovieDto(models: List<MovieModel>): List<MovieDto> {
    return models.map { mapMovieModelToMovieDto(it) }
}

