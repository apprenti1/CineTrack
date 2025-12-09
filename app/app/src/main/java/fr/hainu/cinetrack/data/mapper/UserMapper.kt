package fr.hainu.cinetrack.data.mapper

import fr.hainu.cinetrack.data.remote.models.UserDto
import fr.hainu.cinetrack.domain.models.UserModel

import fr.hainu.cinetrack.data.mapper.mapReviewDtoToReviewModel
import fr.hainu.cinetrack.data.mapper.mapListDtoToListModel

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
        reviews = dto.reviews.map { mapReviewDtoToReviewModel(it) },
        lists = dto.lists.map { mapListDtoToListModel(it) }
    )
}