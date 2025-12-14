package fr.hainu.cinetrack.data.repository

import fr.hainu.cinetrack.data.mapper.mapCastMemberDtoToModel
import fr.hainu.cinetrack.data.mapper.mapCastMemberModelToDto
import fr.hainu.cinetrack.data.remote.CastMemberRemoteDataSource
import fr.hainu.cinetrack.domain.repository.CastMemberRepository
import fr.hainu.cinetrack.domain.models.CastMemberModel

class CastMemberRepositoryImpl(
    val remote: CastMemberRemoteDataSource = CastMemberRemoteDataSource()
) : CastMemberRepository {


    override suspend fun fetchAll(): List<CastMemberModel> {
        val dto = remote.fetchAllCastMembers()
        return mapCastMemberDtoToModel(dto)
    }

    override suspend fun fetchById(id: Int): CastMemberModel? {
        val dto = remote.fetchCastMemberById(id)
        return mapCastMemberDtoToModel(dto)
    }

    override suspend fun add(castMember: CastMemberModel) {
        val dto = mapCastMemberModelToDto(castMember)
        remote.postCastMember(dto)

    }

    override suspend fun remove(castMember: CastMemberModel): Boolean {
        remote.removeCastMember(castMember.id)
        return true
    }

    override suspend fun update(castMember: CastMemberModel) {
        val dto = mapCastMemberModelToDto(castMember)
        remote.updateCastMember(dto.id, dto)

    }
}