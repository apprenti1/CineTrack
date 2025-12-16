package fr.hainu.cinetrack.domain.usecase.review

data class ReviewUseCase (
    val getAllReview: GetAllReviewUseCase,
    val getMovieAllReview: GetAllMovieReviewUseCase,
    val getAllUserReview: GetAllUserReviewUseCase,
    val getReviewById: GetReviewByIdUseCase,
    val addReview: AddReviewUseCase,
    val updateReview: UpdateReviewUseCase,
    val removeReview: RemoveReviewUseCase,
)