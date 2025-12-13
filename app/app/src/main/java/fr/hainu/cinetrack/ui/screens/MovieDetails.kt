package fr.hainu.cinetrack.ui.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.ui.components.*
import fr.hainu.cinetrack.ui.mock.MockMovieRepository
import fr.hainu.cinetrack.ui.mock.getMockMovies
import fr.hainu.cinetrack.ui.theme.*
import fr.hainu.cinetrack.ui.viewmodels.MoviesViewModel

@Composable
fun MovieDetailsScreen(
    movie: MovieModel,
    viewModel: MoviesViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    onRateClick: () -> Unit = {}
) {
    val currentMovie by viewModel.currentMovieDetails.collectAsState()

    LaunchedEffect(movie.id) {
        viewModel.loadMovieDetails(movie)
    }

    val context = LocalContext.current

    // Use currentMovie if available, otherwise use the parameter
    val displayMovie = currentMovie ?: movie

    // Utiliser les données du film passé en paramètre
    val similarMovies = getMockMovies().filter { it.id != displayMovie.id }.take(3)

    // Convertir les reviews internes en format Review pour l'affichage
    val reviews = displayMovie.reviews.map { review ->
        Review(
            userName = "Utilisateur ${review.refUser}",
            rating = review.rating,
            comment = review.comment,
            timeAgo = "Le ${review.createdAt}",
            likes = 0,
        )
    }

    // Séparer les genres s'ils sont sous forme de string
    val genresList = displayMovie.genres.split(",").map { it.trim() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray900)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            MovieDetailsHeader(
                backdropUrl = displayMovie.posterUrl,
                onBackClick = onBackClick,
                onShareClick = {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, "${displayMovie.title} (${displayMovie.year}) - ${displayMovie.synopsis}")
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Partager"))
                },
            )

            MoviePosterInfo(
                title = displayMovie.title,
                year = displayMovie.year,
                duration = displayMovie.duration,
                rating = displayMovie.rating,
                posterUrl = displayMovie.posterUrl,
                modifier = Modifier.padding(16.dp).offset(y = (-0).dp)
            )

            Spacer(modifier = Modifier.height((-64).dp))

            GenreChips(
                genres = genresList
            )

            Spacer(modifier = Modifier.height(16.dp))

            ActionButtons(
                isOnFavorite = displayMovie.isOnFavorite,
                isOnWatchlist = displayMovie.isOnWatchlist,
                isOnWatched = displayMovie.isOnWatched,
                isRated = displayMovie.isRated,
                onFavoriteClick = { displayMovie.switchFavoriteState() },
                onWatchlistClick = { displayMovie.switchWatchlistState() },
                onWatchedClick = { displayMovie.switchWatchedState() },
                onRateClick = onRateClick
            )

            Spacer(modifier = Modifier.height(24.dp))

            SynopsisSection(
                synopsis = displayMovie.synopsis
            )

            Spacer(modifier = Modifier.height(24.dp))

            CastSection(cast = displayMovie.cast)

            Spacer(modifier = Modifier.height(24.dp))

            SimilarMoviesSection(movies = similarMovies)

            Spacer(modifier = Modifier.height(24.dp))

            ReviewsSection(reviews = reviews)

            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MovieDetailsScreenPreview() {
    val movie = remember { mutableStateOf<MovieModel?>(null) }
    LaunchedEffect(Unit) {
        val loadedMovie = MockMovieRepository().getMovies(MockMovieRepository.MovieType.TREND)[0]
        loadedMovie.pullMoreDetails()
        movie.value = loadedMovie
    }

    movie.value?.let {
        MovieDetailsScreen(it)
    }
}
