package fr.hainu.cinetrack.data.mapper

import fr.hainu.cinetrack.data.remote.models.ReviewDto
import fr.hainu.cinetrack.domain.models.ReviewModel

fun mapReviewDtoToModel(dto: ReviewDto): ReviewModel {
    return ReviewModel(
        id = dto.id,
        comment = dto.comment,
        rating = dto.rating,
        refUser = dto.refUser,
        refMovie = dto.refMovie,
        userModel = dto.userDto?.let { mapUserDtoToModel(it) },
        movieModel = dto.movieDto?.let { mapMovieDtoToModel(it) },
        createdAt = dto.createdAt,
        updatedAt = dto.updatedAt
    )
}
fun mapReviewDtoToModel(dtos: List<ReviewDto>): List<ReviewModel> {
    return dtos.map { mapReviewDtoToModel(it) }
}
fun mapReviewModelToDto(model: ReviewModel): ReviewDto {
    return ReviewDto(
        id = model.id,
        comment = model.comment,
        rating = model.rating,
        refUser = model.refUser,
        refMovie = model.refMovie,
        userDto = model.userModel?.let { mapUserModelToDto(it) },
        movieDto = model.movieModel?.let { mapMovieModelToDto(it) },
        createdAt = model.createdAt,
        updatedAt = model.updatedAt
    )
}
fun mapReviewModelToDto(models: List<ReviewModel>): List<ReviewDto> {
    return models.map { mapReviewModelToDto(it) }
}

