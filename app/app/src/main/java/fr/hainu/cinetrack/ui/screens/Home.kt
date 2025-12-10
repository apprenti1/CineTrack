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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.ui.components.BottomNavigationBar
import fr.hainu.cinetrack.ui.components.HomeHeader
import fr.hainu.cinetrack.ui.components.NavItem
import fr.hainu.cinetrack.ui.components.NewReleasesSection
import fr.hainu.cinetrack.ui.components.PopularMoviesSection
import fr.hainu.cinetrack.ui.components.TrendingSection
import fr.hainu.cinetrack.ui.theme.Gray900

@Composable
fun HomeScreen(onMovieClick: (MovieModel) -> Unit = {}) {
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
                    TrendingSection(onMovieClick = onMovieClick)

                    Spacer(modifier = Modifier.height(24.dp))

                    PopularMoviesSection(
                        onMovieClick = onMovieClick
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    NewReleasesSection(
                        onMovieClick = onMovieClick
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
        )
        {
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
