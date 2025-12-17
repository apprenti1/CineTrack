package fr.hainu.cinetrack.domain.usecase.user

data class UserUseCase (
    val register: RegisterUseCase,
    val login: LoginUseCase,
    val getProfile: GetProfileUseCase,
    val addToWatchlist: AddToWatchlistUseCase,
    val removeFromWatchlist: RemoveFromWatchlistUseCase,
    val addToLikes: AddToLikesUseCase,
    val removeFromLikes: RemoveFromLikesUseCase,
    val addToWatched: AddToWatchedUseCase,
    val removeFromWatched: RemoveFromWatchedUseCase
)