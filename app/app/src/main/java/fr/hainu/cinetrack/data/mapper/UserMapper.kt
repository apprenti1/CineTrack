package fr.hainu.cinetrack.data.mapper

import fr.hainu.cinetrack.data.remote.models.UserDto
import fr.hainu.cinetrack.ui.models.UserModel

fun mapUserDtoToUserModel(dto: UserDto): UserModel {
    return UserModel(
        id = dto.id,
        pseudo = dto.pseudo,
        email = dto.email,
        password = dto.password,
        watchlist = dto.watchlist,
        likes = dto.likes,
        createdAt = dto.createdAt,
        updatedAt = dto.updatedAt,
        reviews = dto.reviews,
        lists = dto.lists
    )
}