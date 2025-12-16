package fr.hainu.cinetrack.domain.usecase.list

import fr.hainu.cinetrack.domain.models.ListModel
import fr.hainu.cinetrack.domain.repository.ListRepository

class RemoveListUseCase(private val repository: ListRepository) {
    suspend operator fun invoke(list : ListModel): Boolean = repository.remove(list)
}