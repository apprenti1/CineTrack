package fr.hainu.cinetrack.domain.usecase.user

import fr.hainu.cinetrack.domain.models.UserModel
import fr.hainu.cinetrack.domain.repository.UserRepository

class RemoveUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(castMember : UserModel): Boolean = repository.remove(castMember)
}