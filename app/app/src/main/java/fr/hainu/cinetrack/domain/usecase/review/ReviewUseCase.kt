package fr.hainu.cinetrack.domain.usecase.review

data class ReviewUseCase(
    val addReview: AddReviewUseCase,
    val getMovieReviews: GetMovieReviewsUseCase
)
