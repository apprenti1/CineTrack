package fr.hainu.cinetrack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.hainu.cinetrack.ui.theme.Amber600
import fr.hainu.cinetrack.ui.theme.Blue600
import fr.hainu.cinetrack.ui.theme.Cyan600
import fr.hainu.cinetrack.ui.theme.Emerald600
import fr.hainu.cinetrack.ui.theme.Gray900
import fr.hainu.cinetrack.ui.theme.Green600
import fr.hainu.cinetrack.ui.theme.Indigo600
import fr.hainu.cinetrack.ui.theme.Orange600
import fr.hainu.cinetrack.ui.theme.Pink600
import fr.hainu.cinetrack.ui.theme.Purple400
import fr.hainu.cinetrack.ui.theme.Purple600
import fr.hainu.cinetrack.ui.theme.Red600
import fr.hainu.cinetrack.ui.theme.Rose600

data class MovieData(
    val title: String,
    val rating: Double,
    val posterUrl: String? = null,
    val year: String = "",
    val genres: String = "",
    val gradientStart: Color = Purple600,
    val gradientEnd: Color = Pink600
)

@Composable
fun TrendingSection(
    onMovieClick: (String) -> Unit = {}
) {
    val trendingMovies = listOf(
        MovieData("Dune: Part Two", 8.5, "https://fr.web.img2.acsta.net/c_310_420/pictures/24/01/26/10/18/5392835.jpg", gradientStart = Green600, gradientEnd = Emerald600),
        MovieData("Inception",  8.5, gradientStart = Purple600, gradientEnd = Pink600),
        MovieData("Oppenheimer", 7.9, gradientStart = Blue600, gradientEnd = Cyan600),
        MovieData("Barbie", 8.2, gradientStart = Red600, gradientEnd = Orange600)
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Tendances de la semaine",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            trendingMovies.forEach { movie ->
                BigMovieCard(
                    title = movie.title,
                    rating = movie.rating,
                    posterUrl = movie.posterUrl,
                    gradientStart = movie.gradientStart,
                    gradientEnd = movie.gradientEnd,
                    modifier = Modifier.width(160.dp),
                    onClick = { onMovieClick(movie.title) }
                )
                Spacer(modifier = Modifier.width(12.dp))
            }
        }
    }
}

@Composable
fun PopularMoviesSection(
    onMovieClick: (String) -> Unit = {}
) {
    val popularMovies = listOf(
        MovieData("The Matrix", 8.0, gradientStart = Green600, gradientEnd = Emerald600),
        MovieData("Inception", 8.1, gradientStart = Amber600, gradientEnd = Orange600),
        MovieData("Interstellar", 8.7, gradientStart = Indigo600, gradientEnd = Purple600),
        MovieData("Avatar", 7.9, gradientStart = Blue600, gradientEnd = Cyan600),
        MovieData("Blade Runner 2049", 8.0, gradientStart = Red600, gradientEnd = Orange600),
        MovieData("Tenet", 7.4, gradientStart = Blue600, gradientEnd = Cyan600)
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Films populaires",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Voir tout",
                fontSize = 12.sp,
                color = Purple400
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            repeat((popularMovies.size + 2) / 3) { rowIndex ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    repeat(3) { colIndex ->
                        val itemIndex = rowIndex * 3 + colIndex
                        if (itemIndex < popularMovies.size) {
                            val movie = popularMovies[itemIndex]
                            MovieCard(
                                title = movie.title,
                                rating = movie.rating,
                                posterUrl = movie.posterUrl,
                                gradientStart = movie.gradientStart,
                                gradientEnd = movie.gradientEnd,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(4.dp)
                            )
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NewReleasesSection(
    onMovieClick: (String) -> Unit = {}
) {
    val newReleases = listOf(
        MovieData(
            "Poor Things",
            8.0,
            "2024",
            "2024",
            genres = "Comédie, Drame",
            gradientStart = Pink600,
            gradientEnd = Rose600
        ),
        MovieData(
            "The Zone of Interest",
            7.6,
            "2024",
            "Drame, Guerre",
            gradientStart = Blue600,
            gradientEnd = Cyan600
        )
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Sorties récentes",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            newReleases.forEachIndexed { index, movie ->
                MovieCardHorizontal(
                    title = movie.title,
                    year = movie.year,
                    genres = movie.genres,
                    rating = movie.rating,
                    posterUrl = movie.posterUrl,
                    gradientStart = movie.gradientStart,
                    gradientEnd = movie.gradientEnd,
                    onClick = { onMovieClick(movie.title) }
                )
                if (index < newReleases.size - 1) {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun HomeSection(
    onMovieClick: (String) -> Unit = {}
) {
    Column {

        TrendingSection(
            onMovieClick = { /* Navigate to movie details */ }
        )

        Spacer(modifier = Modifier.height(24.dp))

        PopularMoviesSection(
            onMovieClick = { /* Navigate to movie details */ }
        )

        Spacer(modifier = Modifier.height(24.dp))

        NewReleasesSection(
            onMovieClick = { /* Navigate to movie details */ }
        )

        Spacer(modifier = Modifier.height(120.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF111827)
@Composable
fun TrendingSectionPreview() {
    Column(
        modifier = Modifier
            .background(Gray900)
            .padding(vertical = 16.dp)
    ) {
        TrendingSection()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF111827)
@Composable
fun PopularMoviesSectionPreview() {
    Column(
        modifier = Modifier
            .background(Gray900)
            .padding(16.dp)
    ) {
        PopularMoviesSection()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF111827)
@Composable
fun NewReleasesSectionPreview() {
    Column(
        modifier = Modifier
            .background(Gray900)
            .padding(16.dp)
    ) {
        NewReleasesSection()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF111827)
@Composable
fun HomeSectionPreview() {
    Column(
        modifier = Modifier
            .background(Gray900)
            .padding(16.dp)
    ) {
        HomeSection()
    }
}
