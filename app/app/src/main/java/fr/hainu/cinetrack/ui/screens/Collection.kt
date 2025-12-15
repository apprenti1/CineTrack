package fr.hainu.cinetrack.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.ui.mock.getMockMovies
import fr.hainu.cinetrack.ui.theme.*

enum class CollectionTab {
    WATCHLIST, WATCHED, FAVORITES, COLLECTIONS
}

@Composable
fun CollectionScreen(
    onMovieClick: (MovieModel) -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf(CollectionTab.WATCHLIST) }

    // Mock data - à remplacer par les vraies données
    val watchlistMovies = remember { getMockMovies().take(5) }
    val watchedMovies = remember { getMockMovies().take(10) }
    val favoriteMovies = remember { getMockMovies().take(3) }

    val watchlistCount = watchlistMovies.size
    val watchedCount = watchedMovies.size
    val favoritesCount = favoriteMovies.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray900)
    ) {
        // Header
        Text(
            text = "Ma Collection",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(24.dp).padding(top = 16.dp)
        )

        // Tabs
        CollectionTabs(
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it },
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Stats Overview
        StatsOverview(
            watchlistCount = watchlistCount,
            watchedCount = watchedCount,
            favoritesCount = favoritesCount,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Movie List
        val currentMovies = when (selectedTab) {
            CollectionTab.WATCHLIST -> watchlistMovies
            CollectionTab.WATCHED -> watchedMovies
            CollectionTab.FAVORITES -> favoriteMovies
            CollectionTab.COLLECTIONS -> emptyList()
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            items(currentMovies) { movie ->
                CollectionMovieItem(
                    movie = movie,
                    onPlayClick = { onMovieClick(movie) },
                    onDeleteClick = { /* TODO: Implement delete */ }
                )
            }
        }
    }
}

@Composable
fun CollectionTabs(
    selectedTab: CollectionTab,
    onTabSelected: (CollectionTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CollectionTab.entries.forEach { tab ->
            val isSelected = selectedTab == tab
            val tabText = when (tab) {
                CollectionTab.WATCHLIST -> "Watchlist"
                CollectionTab.WATCHED -> "Vus"
                CollectionTab.FAVORITES -> "Favoris"
                CollectionTab.COLLECTIONS -> "Collections"
            }

            Button(
                onClick = { onTabSelected(tab) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) Purple600 else Gray800,
                    contentColor = if (isSelected) Color.White else Gray400
                ),
                shape = RoundedCornerShape(24.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                modifier = Modifier.height(36.dp)
            ) {
                Text(
                    text = tabText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun StatsOverview(
    watchlistCount: Int,
    watchedCount: Int,
    favoritesCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCard(
            count = watchlistCount,
            label = "À voir",
            color = Purple400,
            modifier = Modifier.weight(1f)
        )
        StatCard(
            count = watchedCount,
            label = "Vus",
            color = Color(0xFF4ADE80),
            modifier = Modifier.weight(1f)
        )
        StatCard(
            count = favoritesCount,
            label = "Favoris",
            color = Color(0xFFFBBF24),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun StatCard(
    count: Int,
    label: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Gray800),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = count.toString(),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                fontSize = 12.sp,
                color = Gray400
            )
        }
    }
}

@Composable
fun CollectionMovieItem(
    movie: MovieModel,
    onPlayClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Gray800),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Poster
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                modifier = Modifier
                    .width(80.dp)
                    .height(112.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Gray900),
                contentScale = ContentScale.Crop
            )

            // Movie Info
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = movie.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${movie.year} • ${movie.genres.split(",").take(2).joinToString(", ")}",
                        fontSize = 14.sp,
                        color = Gray400
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(
                        onClick = onPlayClick,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Play",
                            tint = Purple400
                        )
                    }
                    IconButton(
                        onClick = onDeleteClick,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Rose500
                        )
                    }
                }
            }

            // Drag handle (visual only for now)
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Reorder",
                tint = Gray600,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CollectionScreenPreview() {
    CollectionScreen()
}
