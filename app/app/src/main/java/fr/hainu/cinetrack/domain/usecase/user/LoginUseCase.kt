package fr.hainu.cinetrack.domain.usecase.user

import fr.hainu.cinetrack.domain.models.UserModel
import fr.hainu.cinetrack.domain.repository.UserRepository

class LoginUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(pseudo: String, password: String): Result<Pair<String, UserModel>> =
        repository.login(pseudo, password)
}
