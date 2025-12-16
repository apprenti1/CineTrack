package fr.hainu.cinetrack.domain.usecase.user

import fr.hainu.cinetrack.domain.models.UserModel
import fr.hainu.cinetrack.domain.repository.UserRepository

class GetUserByIdUseCase (private val repository: UserRepository) {
    suspend operator fun invoke(id: Int): UserModel? = repository.fetchById(id)
}