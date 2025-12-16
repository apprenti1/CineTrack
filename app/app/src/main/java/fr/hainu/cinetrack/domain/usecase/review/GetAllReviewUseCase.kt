package fr.hainu.cinetrack.domain.usecase.review

import fr.hainu.cinetrack.domain.models.ReviewModel
import fr.hainu.cinetrack.domain.repository.ReviewRepository

class GetAllReviewUseCase(private val repository: ReviewRepository) {
    suspend operator fun invoke(): List<ReviewModel> = repository.fetchAll()
}