package fr.hainu.cinetrack.data.repository

import fr.hainu.cinetrack.data.remote.models.CastMemberDto
import fr.hainu.cinetrack.data.remote.CastMemberRemoteDataSource
import fr.hainu.cinetrack.domain.repository.CastMemberRepository
import fr.hainu.cinetrack.domain.models.CastMemberModel

class CastMemberRepositoryImpl(
    val remote: CastMemberRemoteDataSource = CastMemberRemoteDataSource()
) : CastMemberRepository {


    override suspend fun fetchAll(): List<CastMemberDto> {
        return remote.fetchAllCastMembers()
    }

    override suspend fun fetchById(id: Int): CastMemberModel? {
        val castMemberData = remote.fetchCastMemberById(id)

        return CastMemberModel(
            castMemberData.id,
            castMemberData.name,
            castMemberData.profilePictureUrl
        )
    }

    override suspend fun add(castMember: CastMemberModel) {
        val dto = CastMemberDto(
            id = castMember.id,
            name = castMember.name,
            profilePictureUrl = castMember.profilePictureUrl
        )
        remote.postCastMember(dto)
    }

    override suspend fun remove(castMember: CastMemberModel): Boolean {
        remote.removeCastMember(castMember.id)
        return true
    }

    override suspend fun update(castMember: CastMemberModel) {
        val dto = CastMemberDto(
            id = castMember.id,
            name = castMember.name,
            profilePictureUrl = castMember.profilePictureUrl
        )
        remote.updateCastMember(dto.id, dto)

    }
}