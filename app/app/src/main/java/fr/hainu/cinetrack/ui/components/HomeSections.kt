package fr.hainu.cinetrack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.ui.theme.Gray900
import fr.hainu.cinetrack.ui.theme.Purple400



@Composable
fun TrendingSection(
    movies: List<MovieModel> = emptyList(),
    onMovieClick: (MovieModel) -> Unit = {}
) {
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
            movies.forEach { movie ->
                BigMovieCard(
                    title = movie.title,
                    rating = movie.rating,
                    posterUrl = movie.posterUrl,
                    modifier = Modifier.width(160.dp),
                    onClick = {
                        onMovieClick(movie)
                    }
                )
                Spacer(modifier = Modifier.width(12.dp))
            }
        }
    }
}

@Composable
fun PopularMoviesSection(
    movies: List<MovieModel> = emptyList(),
    onMovieClick: (MovieModel) -> Unit = {},
    onSeeAllClick: () -> Unit = {}
) {
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
                color = Purple400,
                modifier = Modifier.clickable { onSeeAllClick() }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            repeat((movies.size + 2) / 3) { rowIndex ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    repeat(3) { colIndex ->
                        val itemIndex = rowIndex * 3 + colIndex
                        if (itemIndex < movies.size) {
                            val movie = movies[itemIndex]
                            MovieCard(
                                title = movie.title,
                                rating = movie.rating,
                                posterUrl = movie.posterUrl,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(4.dp),
                                onClick = { onMovieClick(movie) }
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
    movies: List<MovieModel> = emptyList(),
    onMovieClick: (MovieModel) -> Unit = {}
) {
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
            movies.take(3).forEachIndexed { index, movie ->
                MovieCardHorizontal(
                    title = movie.title,
                    year = movie.year,
                    genres = movie.genres,
                    rating = movie.rating,
                    posterUrl = movie.posterUrl,
                    onClick = { onMovieClick(movie) }
                )
                if (index < movies.take(3).size - 1) {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun HomeSection() {
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
