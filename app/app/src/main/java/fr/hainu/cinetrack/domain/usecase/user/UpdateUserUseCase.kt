package fr.hainu.cinetrack.domain.usecase.user

import fr.hainu.cinetrack.domain.models.UserModel
import fr.hainu.cinetrack.domain.repository.UserRepository

class UpdateUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(castMember: UserModel) {
        repository.update(castMember)
    }
}