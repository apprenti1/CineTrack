package fr.hainu.cinetrack.domain.usecase.review

import fr.hainu.cinetrack.domain.models.ReviewModel
import fr.hainu.cinetrack.domain.repository.ReviewRepository

class GetReviewByIdUseCase (private val repository: ReviewRepository) {
    suspend operator fun invoke(id: Int): ReviewModel? = repository.fetchById(id)
}