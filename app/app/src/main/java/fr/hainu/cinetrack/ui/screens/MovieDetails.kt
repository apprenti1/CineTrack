package fr.hainu.cinetrack.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.ui.components.*
import fr.hainu.cinetrack.ui.theme.*
import fr.hainu.cinetrack.ui.viewmodels.MoviesViewModel
import fr.hainu.cinetrack.ui.viewmodels.UserViewModel
import androidx.core.net.toUri

fun extractVideoId(ytUrl: String): String? {
    return try {
        val uri = ytUrl.toUri()
        uri.getQueryParameter("v")
    } catch (_: Exception) {
        null
    }
}

// format date il ya tant de jours/mois/année
fun formatTimeAgo(isoDate: String): String {
    return try {
        // parser date (format: 2024-01-15T10:30:00.000Z)
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val date = sdf.parse(isoDate) ?: return isoDate

        val now = Date()
        val diffInMillis = now.time - date.time

        val seconds = diffInMillis / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val months = days / 30
        val years = days / 365

        when {
            years > 0 -> "Il y a $years an${if (years > 1) "s" else ""}"
            months > 0 -> "Il y a $months mois"
            days > 0 -> "Il y a $days jour${if (days > 1) "s" else ""}"
            hours > 0 -> "Il y a $hours heure${if (hours > 1) "s" else ""}"
            minutes > 0 -> "Il y a $minutes minute${if (minutes > 1) "s" else ""}"
            else -> "À l'instant"
        }
    } catch (e: Exception) {
        e.printStackTrace()
        "indeterminé"
    }
}

@Composable
fun MovieDetailsScreen(
    moviesViewModel: MoviesViewModel,
    userViewModel: UserViewModel,
    reviewViewModel: fr.hainu.cinetrack.ui.viewmodels.ReviewViewModel,
    movie: MovieModel,
    onBackClick: () -> Unit = {}
) {
    val viewModel = moviesViewModel
    val currentMovie by viewModel.currentMovieDetails.collectAsState()
    val isLoggedIn by userViewModel.isLoggedIn.collectAsState()
    val currentUser by userViewModel.currentUser.collectAsState()
    val movieReviews by reviewViewModel.movieReviews.collectAsState()
    val reviewSubmitted by reviewViewModel.reviewSubmitted.collectAsState()
    var showTrailer by remember { mutableStateOf(false) }
    var showRatingModal by remember { mutableStateOf(false) }

    LaunchedEffect(movie.id) {
        viewModel.loadMovieDetails(movie)
        reviewViewModel.loadMovieReviews(movie.id)
    }

    // Recharger les reviews et fermer le modal après soumission
    LaunchedEffect(reviewSubmitted) {
        if (reviewSubmitted) {
            showRatingModal = false
            reviewViewModel.resetReviewSubmitted()
        }
    }

    val context = LocalContext.current

    // Use currentMovie if available, otherwise use the parameter
    val displayMovie = currentMovie ?: movie

    // Determine movie states based on user data - recalculate on every composition when lists change
    val isOnWatchlist = currentUser?.watchlist?.contains(displayMovie.id) ?: false
    val isOnFavorite = currentUser?.likes?.contains(displayMovie.id) ?: false
    val isOnWatched = currentUser?.watched?.contains(displayMovie.id) ?: false

    // Log.d("MovieDetails", "Movie: ${displayMovie.title} (${displayMovie.id})")
    // Log.d("MovieDetails", "CurrentUser: ${currentUser?.pseudo}, isLoggedIn: $isLoggedIn")
    // Log.d("MovieDetails", "isOnWatchlist: $isOnWatchlist, isOnFavorite: $isOnFavorite, isOnWatched: $isOnWatched")
    // Log.d("MovieDetails", "User watchlist: ${currentUser?.watchlist}")
    // Log.d("MovieDetails", "User likes: ${currentUser?.likes}")
    // Log.d("MovieDetails", "User watched: ${currentUser?.watched}")

    // Utiliser les films similaires chargés depuis l'API
    val similarMovies = displayMovie.similarMovies

    // Convertir les reviews de l'API en format Review pour l'affichage
    val reviews = movieReviews.map { review ->
        Review(
            userName = review.userName,
            rating = review.rating,
            comment = review.comment,
            timeAgo = formatTimeAgo(review.createdAt),
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
                    if (!displayMovie.trailerUrl.isNullOrBlank()) {
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
                        // Log.d("MovieDetails", "Favorite button clicked, isOnFavorite: $isOnFavorite, movieId: ${displayMovie.id}")
                        if (isOnFavorite) {
                            userViewModel.removeFromLikes(displayMovie.id)
                        } else {
                            userViewModel.addToLikes(displayMovie.id)
                        }
                    },
                    onWatchlistClick = {
                        // Log.d("MovieDetails", "Watchlist button clicked, isOnWatchlist: $isOnWatchlist, movieId: ${displayMovie.id}")
                        if (isOnWatchlist) {
                            userViewModel.removeFromWatchlist(displayMovie.id)
                        } else {
                            userViewModel.addToWatchlist(displayMovie.id)
                        }
                    },
                    onWatchedClick = {
                        // Log.d("MovieDetails", "Watched button clicked, isOnWatched: $isOnWatched, movieId: ${displayMovie.id}")
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
                synopsis = displayMovie.synopsis ?: ""
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
                        factory = { context ->
                            YouTubePlayerView(context).apply {
                                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                                    override fun onReady(youTubePlayer: YouTubePlayer) {
                                        val videoId = displayMovie.trailerUrl?.let { url -> extractVideoId(url) }
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
                                            displayMovie.trailerUrl?.let { url ->
                                                val playIntent = Intent(Intent.ACTION_VIEW, url.toUri())
                                                context.startActivity(playIntent)
                                            }
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
                if (isLoggedIn) {
                    reviewViewModel.addReview(displayMovie.id, rating, comment)
                } else {
                    showRatingModal = false
                    Toast.makeText(
                        context,
                        "Vous devez être connecté pour noter un film",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }
}

// Preview removed - requires real ViewModels
