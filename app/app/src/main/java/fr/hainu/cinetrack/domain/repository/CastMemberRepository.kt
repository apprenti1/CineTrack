package fr.hainu.cinetrack.domain.repository

import fr.hainu.cinetrack.data.remote.models.CastMemberDto
import fr.hainu.cinetrack.domain.models.CastMemberModel
interface CastMemberRepository {
    suspend fun fetchAll(): List<CastMemberModel>
    suspend fun fetchById(id: Int): CastMemberModel?
    suspend fun add(castMember: CastMemberModel)
    suspend fun remove(castMember: CastMemberModel): Boolean
    suspend fun update(castMember: CastMemberModel)
}