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
        reviews = mapReviewDtoToReviewModel(dto.reviews),
        lists = mapListDtoToListModel(dto.lists)
    )
}
fun mapUserDtoToUserModel(dtos: List<UserDto>): List<UserModel> {
    return dtos.map { mapUserDtoToUserModel(it) }
}

fun mapUserModelToUserDto(model: UserModel): UserDto {
    return UserDto(
        id = model.id,
        pseudo = model.pseudo,
        email = model.email,
        password = model.password,
        watchlist = model.watchlist,
        likes = model.likes,
        createdAt = model.createdAt,
        updatedAt = model.updatedAt,
        reviews = mapReviewModelToReviewDto(model.reviews),
        lists = mapListModelToListDto(model.lists)
    )
}
fun mapUserModelToUserDto(models: List<UserModel>): List<UserDto> {
    return models.map { mapUserModelToUserDto(it) }
}
