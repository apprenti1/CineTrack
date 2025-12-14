package fr.hainu.cinetrack.domain.repository

import fr.hainu.cinetrack.data.remote.models.ReviewDto
import fr.hainu.cinetrack.domain.models.ReviewModel

interface ReviewRepository {
    suspend fun fetchAll(): List<ReviewDto>
    suspend fun fetchById(id: Int): ReviewModel?
    fun add(review: ReviewModel)
    fun remove(review: ReviewModel): Boolean
    fun update(updateReview: ReviewModel)
}