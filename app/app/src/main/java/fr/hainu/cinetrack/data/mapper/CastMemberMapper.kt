package fr.hainu.cinetrack.data.mapper

import fr.hainu.cinetrack.data.remote.models.CastMemberDto
import fr.hainu.cinetrack.domain.models.CastMemberModel
fun mapCastMemberDtoToModel(dto: CastMemberDto): CastMemberModel {
    return CastMemberModel(
        name = dto.name,
        profilePictureUrl = dto.profilePictureUrl
    )
}