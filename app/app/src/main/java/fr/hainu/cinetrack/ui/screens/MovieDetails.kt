package fr.hainu.cinetrack.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.ui.components.*
import fr.hainu.cinetrack.ui.mock.getMockMovies
import fr.hainu.cinetrack.ui.mock.MockMovieRepository
import fr.hainu.cinetrack.ui.theme.*
import fr.hainu.cinetrack.ui.viewmodels.MoviesViewModel
import fr.hainu.cinetrack.ui.viewmodels.UserViewModel

fun extractVideoId(ytUrl: String): String? {
    return try {
        val uri = Uri.parse(ytUrl)
        uri.getQueryParameter("v")
    } catch (e: Exception) {
        null
    }
}

@Composable
fun MovieDetailsScreen(
    movie: MovieModel,
    viewModel: MoviesViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    onRateClick: () -> Unit = {}
) {
    val currentMovie by viewModel.currentMovieDetails.collectAsState()
    val isLoggedIn by userViewModel.isLoggedIn.collectAsState()
    val currentUser by userViewModel.currentUser.collectAsState()
    var showTrailer by remember { mutableStateOf(false) }
    var showRatingModal by remember { mutableStateOf(false) }

    LaunchedEffect(movie.id) {
        viewModel.loadMovieDetails(movie)
    }

    val context = LocalContext.current

    // Use currentMovie if available, otherwise use the parameter
    val displayMovie = currentMovie ?: movie

    // Determine movie states based on user data
    val isOnWatchlist = currentUser?.watchlist?.contains(displayMovie.id) ?: false
    val isOnFavorite = currentUser?.likes?.contains(displayMovie.id) ?: false
    val isOnWatched = currentUser?.watched?.contains(displayMovie.id) ?: false

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
                onPlayClick = {
                    if (displayMovie.trailerUrl.isNotBlank()) {
                        showTrailer = true
                    } else {
                        Toast.makeText(context, "Bande-annonce non disponible", Toast.LENGTH_SHORT).show()
                    }
                }

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

            if (isLoggedIn) {
                ActionButtons(
                    isOnFavorite = isOnFavorite,
                    isOnWatchlist = isOnWatchlist,
                    isOnWatched = isOnWatched,
                    isRated = displayMovie.isRated,
                    onFavoriteClick = {
                        if (isOnFavorite) {
                            userViewModel.removeFromLikes(displayMovie.id)
                        } else {
                            userViewModel.addToLikes(displayMovie.id)
                        }
                    },
                    onWatchlistClick = {
                        if (isOnWatchlist) {
                            userViewModel.removeFromWatchlist(displayMovie.id)
                        } else {
                            userViewModel.addToWatchlist(displayMovie.id)
                        }
                    },
                    onWatchedClick = {
                        if (isOnWatched) {
                            userViewModel.removeFromWatched(displayMovie.id)
                        } else {
                            userViewModel.addToWatched(displayMovie.id)
                        }
                    },
                    onRateClick = { showRatingModal = true }
                )

                Spacer(modifier = Modifier.height(24.dp))
            }

            SynopsisSection(
                synopsis = displayMovie.synopsis
            )

            Spacer(modifier = Modifier.height(24.dp))

            CastSection(cast = displayMovie.cast)

            Spacer(modifier = Modifier.height(24.dp))

            SimilarMoviesSection(movies = similarMovies)

            Spacer(modifier = Modifier.height(24.dp))

            if (isLoggedIn) {
                ReviewsSection(
                    reviews = reviews,
                    onAddReviewClick = { showRatingModal = true }
                )

                Spacer(modifier = Modifier.height(48.dp))
            }
        }

        if (showTrailer) {
            Dialog(onDismissRequest = { showTrailer = false }) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AndroidView(
                        factory = {
                            YouTubePlayerView(it).apply {
                                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                                    override fun onReady(youTubePlayer: YouTubePlayer) {
                                        val videoId = extractVideoId(displayMovie.trailerUrl)
                                        if (videoId != null) {
                                            youTubePlayer.loadVideo(videoId, 0f)
                                        } else {
                                            showTrailer = false
                                            Toast.makeText(context, "URL de bande-annonce invalide", Toast.LENGTH_SHORT).show()
                                        }
                                    }

                                    override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                                        if (error == PlayerConstants.PlayerError.VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER) {
                                            showTrailer = false
                                            Toast.makeText(context, "Lecture impossible dans l'app, ouverture sur YouTube", Toast.LENGTH_LONG).show()
                                            val playIntent = Intent(Intent.ACTION_VIEW, Uri.parse(displayMovie.trailerUrl))
                                            context.startActivity(playIntent)
                                        } else {
                                            showTrailer = false
                                            Toast.makeText(context, "Erreur de lecture: $error", Toast.LENGTH_LONG).show()
                                        }
                                    }
                                })
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16 / 9f)
                    )
                    IconButton(
                        onClick = { showTrailer = false },
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Fermer",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
    if (showRatingModal) {
        RatingModal(
            movie = displayMovie,
            onDismiss = { showRatingModal = false },
            onSubmit = { rating, comment ->
                // TODO: Implement rating submission
                showRatingModal = false
                android.widget.Toast.makeText(
                    context,
                    "Note enregistrée : $rating/5",
                    android.widget.Toast.LENGTH_SHORT
                ).show()
            }
        )
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
