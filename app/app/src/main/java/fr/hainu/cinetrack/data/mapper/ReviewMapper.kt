package fr.hainu.cinetrack.data.mapper

import fr.hainu.cinetrack.data.remote.models.ReviewDto
import fr.hainu.cinetrack.domain.models.ReviewModel

fun mapReviewDtoToReviewModel(dto: ReviewDto): ReviewModel {
    return ReviewModel(
        id = dto.id,
        comment = dto.comment,
        rating = dto.rating,
        refUser = dto.refUser,
        refMovie = dto.refMovie,
        userModel = dto.userDto?.let { mapUserDtoToUserModel(it) },
        movieModel = dto.movieDto?.let { mapMovieDtoToMovieModel(it) },
        createdAt = dto.createdAt,
        updatedAt = dto.updatedAt
    )
}
fun mapReviewDtoToReviewModel(dtos: List<ReviewDto>): List<ReviewModel> {
    return dtos.map { mapReviewDtoToReviewModel(it) }
}
fun mapReviewModelToReviewDto(model: ReviewModel): ReviewDto {
    return ReviewDto(
        id = model.id,
        comment = model.comment,
        rating = model.rating,
        refUser = model.refUser,
        refMovie = model.refMovie,
        userDto = model.userModel?.let { mapUserModelToUserDto(it) },
        movieDto = model.movieModel?.let { mapMovieModelToMovieDto(it) },
        createdAt = model.createdAt,
        updatedAt = model.updatedAt
    )
}
fun mapReviewModelToReviewDto(models: List<ReviewModel>): List<ReviewDto> {
    return models.map { mapReviewModelToReviewDto(it) }
}

