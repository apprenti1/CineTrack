package fr.hainu.cinetrack.domain.usecase.castMember

import fr.hainu.cinetrack.data.remote.models.CastMemberDto
import fr.hainu.cinetrack.domain.repository.CastMemberRepository

class AddCastMemberUseCase(private val repository: CastMemberRepository) {
    suspend operator fun invoke() {
        repository.fetchAll()
    }
}