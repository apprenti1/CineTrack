package fr.hainu.cinetrack.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState as rememberHorizontalScrollState
import fr.hainu.cinetrack.ui.components.BigMovieCard
import fr.hainu.cinetrack.ui.components.BottomNavigationBar
import fr.hainu.cinetrack.ui.components.HomeHeader
import fr.hainu.cinetrack.ui.components.MovieData
import fr.hainu.cinetrack.ui.components.NavItem
import fr.hainu.cinetrack.ui.components.NewReleasesSection
import fr.hainu.cinetrack.ui.components.PopularMoviesSection
import fr.hainu.cinetrack.ui.theme.Gray900
import fr.hainu.cinetrack.ui.theme.Purple600
import fr.hainu.cinetrack.ui.theme.Pink600
import fr.hainu.cinetrack.ui.theme.Blue600
import fr.hainu.cinetrack.ui.theme.Cyan600
import fr.hainu.cinetrack.ui.theme.Red600
import fr.hainu.cinetrack.ui.theme.Orange600

@Composable
fun TrendingSectionWithPager() {
    val trendingMovies = listOf(
        MovieData(
            "Dune: Part Two",
            8.5,
            "https://image.tmdb.org/t/p/w500/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg",
            gradientStart = Purple600,
            gradientEnd = Pink600
        ),
        MovieData(
            "Oppenheimer",
            7.9,
            "https://image.tmdb.org/t/p/w500/nnHd6L3e7YTtUj9wJJYY9WzHvql.jpg",
            gradientStart = Blue600,
            gradientEnd = Cyan600
        ),
        MovieData(
            "Barbie",
            8.2,
            "https://image.tmdb.org/t/p/w500/0sWsQgIvXvPCqhXOfRhaWJstandy.jpg",
            gradientStart = Red600,
            gradientEnd = Orange600
        )
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
                .horizontalScroll(rememberHorizontalScrollState())
                .padding(horizontal = 16.dp),
        ) {
            trendingMovies.forEach { movie ->
                BigMovieCard(
                    title = movie.title,
                    rating = movie.rating,
                    posterUrl = movie.posterUrl,
                    gradientStart = movie.gradientStart,
                    gradientEnd = movie.gradientEnd,
                    onClick = { /* Navigate to movie details */ }
                )
                Spacer(modifier = Modifier.width(12.dp))
            }
        }
    }
}

@Composable
fun HomeScreen() {
    val activeNav = remember { mutableStateOf(NavItem.HOME) }
    val searchText = remember { mutableStateOf("") }

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
            HomeHeader(searchText)

            Spacer(modifier = Modifier.height(16.dp))

            if (searchText.value.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                ) {
                    TrendingSectionWithPager()

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
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                ) {}
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(androidx.compose.ui.Alignment.BottomCenter)
        ) {
            BottomNavigationBar(
                activeItem = activeNav.value,
                onItemClick = { activeNav.value = it }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
