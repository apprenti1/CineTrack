package fr.hainu.cinetrack.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.ui.components.BottomNavigationBar
import fr.hainu.cinetrack.ui.components.NavItem
import fr.hainu.cinetrack.ui.components.NewReleasesSection
import fr.hainu.cinetrack.ui.components.PopularMoviesSection
import fr.hainu.cinetrack.ui.components.TrendingSection
import fr.hainu.cinetrack.ui.theme.Gray900
import fr.hainu.cinetrack.ui.viewmodels.MoviesViewModel

@Composable
fun HomeScreen(
    viewModel: MoviesViewModel = viewModel(),
    onMovieClick: (MovieModel) -> Unit = {},
    activeNavItem: NavItem = NavItem.HOME,
    onNavItemClick: (NavItem) -> Unit = {},
    hideBottomNav: Boolean = false,
    onSeeAllClick: () -> Unit = {}
) {
    val trendingMoviesWeek: List<MovieModel> by viewModel.trendingMoviesWeek.collectAsState()
    val trendingMovies: List<MovieModel> by viewModel.trendingMovies.collectAsState()
    val recentMovies: List<MovieModel> by viewModel.recentMovies.collectAsState()


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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                TrendingSection(
                    movies = trendingMoviesWeek,
                    onMovieClick = onMovieClick
                )

                Spacer(modifier = Modifier.height(24.dp))

                PopularMoviesSection(
                    movies = trendingMovies,
                    onMovieClick = onMovieClick,
                    onSeeAllClick = onSeeAllClick
                )

                Spacer(modifier = Modifier.height(24.dp))

                NewReleasesSection(
                    movies = recentMovies,
                    onMovieClick = onMovieClick
                )

                Spacer(modifier = Modifier.height(120.dp))
            }
        }

        if (!hideBottomNav) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(androidx.compose.ui.Alignment.BottomCenter)
            )
            {
                BottomNavigationBar(
                    activeItem = activeNavItem,
                    onItemClick = onNavItemClick
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
