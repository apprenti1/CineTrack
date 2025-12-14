package fr.hainu.cinetrack.data.mapper

import fr.hainu.cinetrack.data.remote.models.ListDto
import fr.hainu.cinetrack.domain.models.ListModel

fun mapListDtoToListModel(dto: ListDto): ListModel {
    return ListModel(
        id = dto.id,
        name = dto.name,
        userId = dto.userId,
        filmIds = dto.filmIds,
        createdAt = dto.createdAt,
        updatedAt = dto.updatedAt
    )
}

fun mapListDtoToListModel(dtos: List<ListDto>): List<ListModel> {
    return dtos.map { mapListDtoToListModel(it) }
}
fun mapListModelToListDto(model: ListModel): ListDto {
    return ListDto(
        id = model.id,
        name = model.name,
        userId = model.userId,
        filmIds = model.filmIds,
        createdAt = model.createdAt,
        updatedAt = model.updatedAt
    )
}
fun mapListModelToListDto(models: List<ListModel>): List<ListDto> {
    return models.map { mapListModelToListDto(it) }
}
