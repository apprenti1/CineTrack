package fr.hainu.cinetrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import fr.hainu.cinetrack.data.local.UserPreferencesManager
import fr.hainu.cinetrack.data.remote.MovieRemoteDataSource
import fr.hainu.cinetrack.data.remote.UserRemoteDataSource
import fr.hainu.cinetrack.data.repository.MovieRepositoryImpl
import fr.hainu.cinetrack.data.repository.UserRepositoryImpl
import fr.hainu.cinetrack.domain.usecase.movie.*
import fr.hainu.cinetrack.domain.usecase.user.*
import fr.hainu.cinetrack.navigation.NavGraph
import fr.hainu.cinetrack.ui.theme.CineTrackTheme
import fr.hainu.cinetrack.ui.viewmodels.MoviesViewModel
import fr.hainu.cinetrack.ui.viewmodels.UserViewModel

class MainActivity : ComponentActivity() {

    // UserPreferencesManager (DataStore)
    private val userPrefs by lazy { UserPreferencesManager(applicationContext) }

    // Data Sources
    private val movieRemoteDataSource by lazy { MovieRemoteDataSource() }
    private val userRemoteDataSource by lazy { UserRemoteDataSource(userPrefs) }

    // Repositories
    private val movieRepository by lazy { MovieRepositoryImpl(movieRemoteDataSource) }
    private val userRepository by lazy { UserRepositoryImpl(userRemoteDataSource) }

    // Movie UseCases
    private val movieUseCases by lazy {
        MovieUseCase(
            getTrendingWeek = GetTrendingWeekUseCase(movieRepository),
            getPopular = GetPopularUseCase(movieRepository),
            getNowPlaying = GetNowPlayingUseCase(movieRepository),
            searchMovies = SearchMoviesUseCase(movieRepository),
            getMovieDetails = GetMovieDetailsUseCase(movieRepository),
            getMoviesByIds = GetMoviesByIdsUseCase(movieRepository),
            getSimilarMovies = GetSimilarMoviesUseCase(movieRepository)
        )
    }

    // User UseCases
    private val userUseCases by lazy {
        UserUseCase(
            register = RegisterUseCase(userRepository),
            login = LoginUseCase(userRepository),
            getProfile = GetProfileUseCase(userRepository),
            addToWatchlist = AddToWatchlistUseCase(userRepository),
            removeFromWatchlist = RemoveFromWatchlistUseCase(userRepository),
            addToLikes = AddToLikesUseCase(userRepository),
            removeFromLikes = RemoveFromLikesUseCase(userRepository),
            addToWatched = AddToWatchedUseCase(userRepository),
            removeFromWatched = RemoveFromWatchedUseCase(userRepository)
        )
    }

    // ViewModels
    private val moviesViewModel by lazy { MoviesViewModel(movieUseCases) }
    private val userViewModel by lazy { UserViewModel(userUseCases, userPrefs) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CineTrackTheme {
                NavGraph(
                    moviesViewModel = moviesViewModel,
                    userViewModel = userViewModel
                )
            }
        }
    }
}