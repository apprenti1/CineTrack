package fr.hainu.cinetrack.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.hainu.cinetrack.R
import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.ui.components.BottomNavigationBar
import fr.hainu.cinetrack.ui.components.HomeHeader
import fr.hainu.cinetrack.ui.components.MovieCard
import fr.hainu.cinetrack.ui.components.NavItem
import fr.hainu.cinetrack.ui.theme.Gray400
import fr.hainu.cinetrack.ui.theme.Gray800
import fr.hainu.cinetrack.ui.theme.Gray900
import fr.hainu.cinetrack.ui.theme.Purple400
import fr.hainu.cinetrack.ui.theme.Purple500
import fr.hainu.cinetrack.ui.theme.Purple600
import fr.hainu.cinetrack.ui.viewmodels.MoviesViewModel

enum class ExploreTab {
    FILMS, SERIES, ALL
}

@Composable
fun ExploreScreen(
    viewModel: MoviesViewModel = viewModel(),
    onMovieClick: (MovieModel) -> Unit = {},
    activeNavItem: NavItem = NavItem.EXPLORE,
    onNavItemClick: (NavItem) -> Unit = {},
    hideBottomNav: Boolean = false,
) {
    val selectedTab = remember { mutableStateOf(ExploreTab.FILMS) }
    val selectedGenre = remember { mutableStateOf<String?>(null) }

    val trendingMovies: List<MovieModel> by viewModel.trendingMovies.collectAsState()

    val searchMovies: List<MovieModel> by viewModel.moviesWithSearch.collectAsState()

    val genres = listOf("Action", "Comédie", "Drame", "Sci-Fi", "Thriller", "Romance")

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

            // Tabs
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TabButton(
                    text = "Films",
                    isSelected = selectedTab.value == ExploreTab.FILMS,
                    onClick = { selectedTab.value = ExploreTab.FILMS }
                )
                TabButton(
                    text = "Séries",
                    isSelected = selectedTab.value == ExploreTab.SERIES,
                    onClick = { selectedTab.value = ExploreTab.SERIES }
                )
                TabButton(
                    text = "Tout",
                    isSelected = selectedTab.value == ExploreTab.ALL,
                    onClick = { selectedTab.value = ExploreTab.ALL }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Filters
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Filter button
                FilterChip(
                    selected = false,
                    onClick = { /* Open filter dialog */ },
                    label = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.filter),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(16.dp)
                                    .height(16.dp)
                            )
                            Text("Filtres", fontSize = 12.sp)
                        }
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = Gray800,
                        labelColor = androidx.compose.ui.graphics.Color.White,
                        selectedContainerColor = Purple600,
                        selectedLabelColor = androidx.compose.ui.graphics.Color.White
                    )
                )

                // Genre chips
                genres.forEach { genre ->
                    FilterChip(
                        selected = selectedGenre.value == genre,
                        onClick = {
                            selectedGenre.value = if (selectedGenre.value == genre) null else genre
                        },
                        label = {
                            Text(genre, fontSize = 12.sp)
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = Gray800,
                            labelColor = Purple400,
                            selectedContainerColor = Purple600,
                            selectedLabelColor = androidx.compose.ui.graphics.Color.White
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                repeat((searchMovies.size + 2) / 3) { rowIndex ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        repeat(3) { colIndex ->
                            val itemIndex = rowIndex * 3 + colIndex
                            if (itemIndex < searchMovies.size) {
                                val movie = searchMovies[itemIndex]
                                MovieCard(
                                    title = movie.title,
                                    rating = movie.rating,
                                    posterUrl = movie.posterUrl,
                                    modifier = Modifier.weight(1f),
                                    onClick = { onMovieClick(movie) }
                                )
                            } else {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(100.dp))
        }

        if (!hideBottomNav) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                BottomNavigationBar(
                    activeItem = activeNavItem,
                    onItemClick = onNavItemClick
                )
            }
        }
    }
}

@Composable
fun TabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    androidx.compose.material3.Button(
        onClick = onClick,
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Purple600 else Gray800,
            contentColor = if (isSelected) androidx.compose.ui.graphics.Color.White else Purple400
        ),
        shape = RoundedCornerShape(50),
        modifier = Modifier.height(36.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ExploreScreenPreview() {
    ExploreScreen()
}