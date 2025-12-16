package fr.hainu.cinetrack.domain.usecase.movie

data class MovieUseCase (
    val getAllMovie: GetAllMovieUseCase,
    val getMovieById: GetMovieByIdUseCase,
    val addMovie: AddMovieUseCase,
    val updateMovie: UpdateMovieUseCase,
    val removeMovie: RemoveMovieUseCase,
)