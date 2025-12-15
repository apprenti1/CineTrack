package fr.hainu.cinetrack.data.mapper

import fr.hainu.cinetrack.data.remote.models.CastMemberDto
import fr.hainu.cinetrack.domain.models.CastMemberModel
fun mapCastMemberDtoToModel(dto: CastMemberDto): CastMemberModel {
    return CastMemberModel(
        id = dto.id,
        name = dto.name,
        profilePictureUrl = dto.profilePictureUrl
    )
}
fun mapCastMemberDtoToModel(dtos: List<CastMemberDto>): List<CastMemberModel> {
    return dtos.map { mapCastMemberDtoToModel(it) }
}
fun mapCastMemberModelToDto(model: CastMemberModel): CastMemberDto {
    return CastMemberDto(
        id = model.id,
        name = model.name,
        profilePictureUrl = model.profilePictureUrl
    )
}
fun mapCastMemberModelToDto(models: List<CastMemberModel>): List<CastMemberDto> {
    return models.map { mapCastMemberModelToDto(it) }
}
