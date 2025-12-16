package fr.hainu.cinetrack.domain.usecase.list

import fr.hainu.cinetrack.domain.models.ListModel
import fr.hainu.cinetrack.domain.repository.ListRepository

class GetAllUserListUseCase(private val repository: ListRepository) {
    suspend operator fun invoke(id: Int): List<ListModel> = repository.fetchFromUser(id)
}