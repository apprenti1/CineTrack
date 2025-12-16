package fr.hainu.cinetrack.data.mapper

import fr.hainu.cinetrack.data.remote.models.UserDto
import fr.hainu.cinetrack.domain.models.UserModel

import fr.hainu.cinetrack.data.mapper.mapReviewDtoToModel
import fr.hainu.cinetrack.data.mapper.mapListDtoToModel

fun mapUserDtoToModel(dto: UserDto): UserModel {
    return UserModel(
        id = dto.id,
        pseudo = dto.pseudo,
        email = dto.email,
        password = dto.password,
        watchlist = dto.watchlist,
        likes = dto.likes,
        createdAt = dto.createdAt,
        updatedAt = dto.updatedAt,
        reviews = mapReviewDtoToModel(dto.reviews),
        lists = mapListDtoToModel(dto.lists)
    )
}
fun mapUserDtoToModel(dtos: List<UserDto>): List<UserModel> {
    return dtos.map { mapUserDtoToModel(it) }
}

fun mapUserModelToDto(model: UserModel): UserDto {
    return UserDto(
        id = model.id,
        pseudo = model.pseudo,
        email = model.email,
        password = model.password,
        watchlist = model.watchlist,
        likes = model.likes,
        createdAt = model.createdAt,
        updatedAt = model.updatedAt,
        reviews = mapReviewModelToDto(model.reviews),
        lists = mapListModelToDto(model.lists)
    )
}
fun mapUserModelToDto(models: List<UserModel>): List<UserDto> {
    return models.map { mapUserModelToDto(it) }
}
