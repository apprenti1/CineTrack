package fr.hainu.cinetrack.domain.usecase.movie

data class MovieUseCase (
    val getTrendingWeek: GetTrendingWeekUseCase,
    val getPopular: GetPopularUseCase,
    val getNowPlaying: GetNowPlayingUseCase,
    val searchMovies: SearchMoviesUseCase,
    val getMovieDetails: GetMovieDetailsUseCase,
    val getMoviesByIds: GetMoviesByIdsUseCase,
    val getSimilarMovies: GetSimilarMoviesUseCase
)