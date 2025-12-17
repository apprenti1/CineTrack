package fr.hainu.cinetrack.domain.usecase.user

import fr.hainu.cinetrack.domain.models.UserModel
import fr.hainu.cinetrack.domain.repository.UserRepository

class RegisterUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(pseudo: String, email: String, password: String): Result<Pair<String, UserModel>> =
        repository.register(pseudo, email, password)
}
