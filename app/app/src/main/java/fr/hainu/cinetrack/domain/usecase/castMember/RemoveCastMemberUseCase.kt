package fr.hainu.cinetrack.domain.usecase.castMember

import fr.hainu.cinetrack.data.remote.models.CastMemberDto
import fr.hainu.cinetrack.domain.models.CastMemberModel
import fr.hainu.cinetrack.domain.repository.CastMemberRepository

class RemoveCastMemberUseCase(private val repository: CastMemberRepository) {
    suspend operator fun invoke(castMember : CastMemberModel): Boolean = repository.remove(castMember)
}