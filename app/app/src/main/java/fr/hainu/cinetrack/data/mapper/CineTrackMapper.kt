package fr.hainu.cinetrack.data.mapper

import fr.hainu.cinetrack.data.remote.models.ReviewResponseDto
import fr.hainu.cinetrack.data.remote.models.UserResponseDto
import fr.hainu.cinetrack.domain.models.ReviewModel
import fr.hainu.cinetrack.domain.models.UserModel

/**
 * Mapper pour convertir UserResponseDto en UserModel
 */
fun mapUserResponseDtoToModel(dto: UserResponseDto): UserModel {
    return UserModel(
        id = dto.id,
        pseudo = dto.pseudo,
        email = dto.email,
        password = "", // Ne jamais stocker le password
        watchlist = dto.watchlist,
        likes = dto.likes,
        watched = dto.watched
    )
}

/**
 * Mapper pour convertir ReviewResponseDto en ReviewModel
 */
fun mapReviewResponseDtoToModel(dto: ReviewResponseDto): ReviewModel {
    return ReviewModel(
        id = dto.id.hashCode(), // Convertir String ID en Int
        comment = dto.comment,
        rating = dto.rating,
        refUser = dto.userId,
        userName = dto.user.pseudo,
        refMovie = dto.filmId,
        createdAt = dto.createdAt
    )
}
