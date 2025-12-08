package fr.hainu.cinetrack.data.mapper

import fr.hainu.cinetrack.data.remote.models.ListDto
import fr.hainu.cinetrack.ui.models.ListModel

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