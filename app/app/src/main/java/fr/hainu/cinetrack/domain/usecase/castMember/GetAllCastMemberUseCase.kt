package fr.hainu.cinetrack.domain.usecase.castMember

import fr.hainu.cinetrack.domain.models.CastMemberModel
import fr.hainu.cinetrack.domain.repository.CastMemberRepository

class GetAllCastMemberUseCase(private val repository: CastMemberRepository) {
    suspend operator fun invoke(): List<CastMemberModel> = repository.fetchAll()
}