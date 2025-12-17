package fr.hainu.cinetrack.data.mapper

import fr.hainu.cinetrack.data.remote.models.UserResponseDto
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
