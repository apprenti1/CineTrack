package fr.hainu.cinetrack.data.mapper

import fr.hainu.cinetrack.data.remote.models.MovieDto
import fr.hainu.cinetrack.data.mapper.mapReviewDtoToReviewModel
import fr.hainu.cinetrack.domain.models.MovieModel

fun mapMovieDtoToMovie(dto: MovieDto): MovieModel {
    return MovieModel(
        id = dto.id,
        title = dto.title,
        rating = dto.rating,
        posterUrl = dto.posterUrl,
        year = dto.year,
        genres = dto.genres,
        ratingCoef = dto.ratingCoef,
        duration = dto.duration,
        synopsis = dto.synopsis,
        trailerUrl = dto.trailerUrl,
        internalCommentsAndRatings = dto.internalCommentsAndRatings.map { mapReviewDtoToReviewModel(it) }
    )
}
