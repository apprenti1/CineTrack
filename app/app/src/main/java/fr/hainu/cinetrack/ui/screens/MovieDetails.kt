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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.ui.components.*
import fr.hainu.cinetrack.ui.getMockMovies
import fr.hainu.cinetrack.ui.theme.*

@Composable
fun MovieDetailsScreen(
    movie: MovieModel,
    onBackClick: () -> Unit = {},
    onRateClick: () -> Unit = {}
) {
    val context = LocalContext.current

    // Utiliser les données du film passé en paramètre
    val similarMovies = getMockMovies().filter { it.id != movie.id }.take(3)

    // Convertir les reviews internes en format Review pour l'affichage
    val reviews = movie.reviews.map { review ->
        Review(
            userName = "Utilisateur ${review.refUser}",
            rating = review.rating,
            comment = review.comment,
            timeAgo = "Le ${review.createdAt}",
            likes = 0,
        )
    }

    // Séparer les genres s'ils sont sous forme de string
    val genresList = movie.genres.split(",").map { it.trim() }

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
                title = movie.title,
                year = movie.year,
                backdropUrl = movie.posterUrl,
                onBackClick = onBackClick,
                onShareClick = {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, "${movie.title} (${movie.year}) - ${movie.synopsis}")
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Partager"))
                },
            )

            MoviePosterInfo(
                title = movie.title,
                year = movie.year,
                duration = movie.duration,
                rating = movie.rating,
                posterUrl = movie.posterUrl,
                modifier = Modifier.padding(16.dp).offset(y = (-0).dp)
            )

            Spacer(modifier = Modifier.height((-64).dp))

            GenreChips(
                genres = genresList
            )

            Spacer(modifier = Modifier.height(16.dp))

            ActionButtons(
                isOnFavorite = movie.isOnFavorite,
                isOnWatchlist = movie.isOnWatchlist,
                isOnWatched = movie.isOnWatched,
                isRated = movie.isRated,
                onFavoriteClick = { movie.switchFavoriteState() },
                onWatchlistClick = { movie.switchWatchlistState() },
                onWatchedClick = { movie.switchWatchedState() },
                onRateClick = onRateClick
            )

            Spacer(modifier = Modifier.height(24.dp))

            SynopsisSection(
                synopsis = movie.synopsis
            )

            Spacer(modifier = Modifier.height(24.dp))

            CastSection(cast = movie.cast)

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
private fun MovieDetailsScreenPreview(
    modifier: Modifier = Modifier.height(6000.dp)
) {
    MovieDetailsScreen(getMockMovies()[0])
}
