package fr.hainu.cinetrack.domain.usecase.castMember

import fr.hainu.cinetrack.domain.models.CastMemberModel
import fr.hainu.cinetrack.domain.repository.CastMemberRepository

class GetCastMemberByIdUseCase (private val repository: CastMemberRepository) {
    suspend operator fun invoke(id: Int): CastMemberModel? = repository.fetchById(id)
}