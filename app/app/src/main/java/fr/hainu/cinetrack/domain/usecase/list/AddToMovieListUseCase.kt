package fr.hainu.cinetrack.domain.usecase.list

import fr.hainu.cinetrack.domain.models.ListModel
import fr.hainu.cinetrack.domain.repository.ListRepository

class AddToMovieListUseCase(private val repository: ListRepository) {
    suspend operator fun invoke(list: ListModel) {
        repository.addToMovie(list)
    }
}