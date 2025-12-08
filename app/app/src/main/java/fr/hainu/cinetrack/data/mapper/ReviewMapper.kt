package fr.hainu.cinetrack.data.mapper

import fr.hainu.cinetrack.data.remote.models.ReviewDto
import fr.hainu.cinetrack.ui.models.ReviewModel

fun mapReviewDtoToReviewModel(dto: ReviewDto): ReviewModel {
    return ReviewModel(
        id = dto.id,
        userId = dto.userId,
        filmId = dto.filmId,
        rating = dto.rating,
        comment = dto.comment,
        createdAt = dto.createdAt,
        updatedAt = dto.updatedAt
    )
}