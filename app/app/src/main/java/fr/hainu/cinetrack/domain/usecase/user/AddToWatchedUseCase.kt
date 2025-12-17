package fr.hainu.cinetrack.domain.usecase.user

import fr.hainu.cinetrack.domain.models.UserModel
import fr.hainu.cinetrack.domain.repository.UserRepository

class AddToWatchedUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(filmId: Int): Result<UserModel> = repository.addToWatched(filmId)
}
