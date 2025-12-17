package fr.hainu.cinetrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import fr.hainu.cinetrack.data.local.UserPreferencesManager
import fr.hainu.cinetrack.data.remote.MovieRemoteDataSource
import fr.hainu.cinetrack.data.remote.UserRemoteDataSource
import fr.hainu.cinetrack.data.repository.MovieRepositoryImpl
import fr.hainu.cinetrack.data.repository.ReviewRepositoryImpl
import fr.hainu.cinetrack.data.repository.UserRepositoryImpl
import fr.hainu.cinetrack.domain.usecase.movie.*
import fr.hainu.cinetrack.domain.usecase.review.*
import fr.hainu.cinetrack.domain.usecase.user.*
import fr.hainu.cinetrack.navigation.NavGraph
import fr.hainu.cinetrack.network.RetrofitInstance
import fr.hainu.cinetrack.ui.theme.CineTrackTheme
import fr.hainu.cinetrack.viewmodels.MoviesViewModel
import fr.hainu.cinetrack.viewmodels.ReviewViewModel
import fr.hainu.cinetrack.viewmodels.UserViewModel

class MainActivity : ComponentActivity() {

    // datastore
    private val userPrefs by lazy { UserPreferencesManager(applicationContext) }

    // datasources
    private val movieRemoteDataSource by lazy { MovieRemoteDataSource() }
    private val userRemoteDataSource by lazy { UserRemoteDataSource(userPrefs) }

    // repos impl
    private val movieRepository by lazy { MovieRepositoryImpl(movieRemoteDataSource) }
    private val userRepository by lazy { UserRepositoryImpl(userRemoteDataSource) }
    private val reviewRepository by lazy {
        val apiWithAuth = RetrofitInstance.getCineTrackApiWithAuth(userPrefs)
        ReviewRepositoryImpl(apiWithAuth.create(fr.hainu.cinetrack.network.CineTrackApi::class.java))
    }

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

    private val reviewUseCases by lazy {
        ReviewUseCase(
            addReview = AddReviewUseCase(reviewRepository),
            getMovieReviews = GetMovieReviewsUseCase(reviewRepository)
        )
    }

    private val moviesViewModel by lazy { MoviesViewModel(movieUseCases) }
    private val userViewModel by lazy { UserViewModel(userUseCases, userPrefs) }
    private val reviewViewModel by lazy { ReviewViewModel(reviewUseCases) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CineTrackTheme {
                NavGraph(
                    moviesViewModel = moviesViewModel,
                    userViewModel = userViewModel,
                    reviewViewModel = reviewViewModel
                )
            }
        }
    }
}