package fr.hainu.cinetrack.ui.mock

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.hainu.cinetrack.domain.models.MovieModel

@Composable
fun MovieListDebug(movies: List<MovieModel>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        movies.forEachIndexed { index, movie ->
            Text(
                text = """
                    [$index] ━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                    id: ${movie.id}
                    title: ${movie.title}
                    rating: ${movie.rating}
                    ratingCoef: ${movie.ratingCoef}
                    posterUrl: ${movie.posterUrl}
                    year: ${movie.year}
                    genres: ${movie.genres}
                    duration: ${movie.duration}
                    trailerUrl: ${movie.trailerUrl}
                    synopsis: ${movie.synopsis}
                    cast: ${movie.cast.size} membres
                    reviews: ${movie.reviews.size} commentaires
                    isOnFavorite: ${movie.isOnFavorite}
                    isOnWatchlist: ${movie.isOnWatchlist}
                    isOnWatched: ${movie.isOnWatched}
                    isRated: ${movie.isRated}

                """.trimIndent(),
                color = Color.White,
                fontSize = 12.sp,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF111827, showSystemUi = true)
@Composable
fun MovieListDebugPreview() {
    val repository = MockMovieRepository()
    var movies by remember { mutableStateOf<List<MovieModel>>(emptyList()) }

    LaunchedEffect(Unit) {
        //movies = repository.getMovies(MockMovieRepository.MovieType.TREND)
        //movies[0].pullMoreDetails()
        movies = repository.getMoviesByIds(listOf(798645))
    }

    MovieListDebug(movies)
}