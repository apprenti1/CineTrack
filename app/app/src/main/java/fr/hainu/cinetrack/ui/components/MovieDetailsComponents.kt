package fr.hainu.cinetrack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import fr.hainu.cinetrack.R
import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.ui.getMockMovies
import fr.hainu.cinetrack.ui.models.CastMemberModel
import fr.hainu.cinetrack.ui.theme.*


@Composable
fun MovieDetailsHeader(
    title: String = "",
    year: String = "",
    backdropUrl: String? = null,
    onBackClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(256.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(256.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Indigo600, Purple700)
                    )
                )
        ) {
            if (backdropUrl != null) {
                AsyncImage(
                    model = backdropUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(256.dp),
                    contentScale = ContentScale.Crop
                )
            }

            IconButton(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(64.dp)
                    .background(Color.Black.copy(alpha = 0.6f), CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.play),
                    contentDescription = "Lire la bande-annonce",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(128.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0f),
                            Color.Black.copy(alpha = 0.9f)
                        )
                    )
                )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Black.copy(alpha = 0.5f), CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "Retour",
                    tint = Color.White
                )
            }

            IconButton(
                onClick = onShareClick,
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Black.copy(alpha = 0.5f), CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.share),
                    contentDescription = "Partager",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun MoviePosterInfo(
    title: String,
    year: String,
    duration: String,
    rating: Double,
    posterUrl: String? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .width(128.dp)
                .height(192.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Indigo600, Purple700)
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            if (posterUrl != null) {
                AsyncImage(
                    model = posterUrl,
                    contentDescription = title,
                    modifier = Modifier
                        .width(128.dp)
                        .height(192.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.film),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = Color.White
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 64.dp)
        ) {
            Text(
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = year,
                    fontSize = 13.sp,
                    color = Gray400
                )
                Text(
                    text = "•",
                    fontSize = 13.sp,
                    color = Gray400
                )
                Text(
                    text = duration,
                    fontSize = 13.sp,
                    color = Gray400
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = Yellow400
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = rating.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Text(
                    text = "/10",
                    fontSize = 13.sp,
                    color = Gray400
                )
            }
        }
    }
}

@Composable
fun GenreChips(
    genres: List<String>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        genres.forEach { genre ->
            Box(
                modifier = Modifier
                    .background(Gray800, RoundedCornerShape(16.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = genre,
                    fontSize = 11.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun ActionButtons(
    onFavoriteClick: () -> Unit = {},
    onWatchlistClick: () -> Unit = {},
    onWatchedClick: () -> Unit = {},
    onRateClick: () -> Unit = {},
    isOnFavorite: Boolean = false,
    isOnWatchlist: Boolean = false,
    isOnWatched: Boolean = false,
    isRated: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ActionButton(
            icon = R.drawable.heart,
            label = "Favoris",
            onClick = onFavoriteClick,
            backgroundColor = if (isOnFavorite) Purple600 else Gray800,
            modifier = Modifier.weight(1f)
        )
        ActionButton(
            icon = R.drawable.bookmark,
            label = "Watchlist",
            onClick = onWatchlistClick,
            backgroundColor = if (isOnWatchlist) Purple600 else Gray800,
            modifier = Modifier.weight(1f)
        )
        ActionButton(
            icon = R.drawable.check_circle,
            label = "Vu",
            onClick = onWatchedClick,
            backgroundColor = if (isOnWatched) Purple600 else Gray800,
            modifier = Modifier.weight(1f)
        )
        ActionButton(
            icon = R.drawable.star,
            label = "Noter",
            onClick = onRateClick,
            backgroundColor = if (isRated) Purple600 else Gray800,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun ActionButton(
    icon: Int,
    label: String,
    onClick: () -> Unit,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = label,
            modifier = Modifier.size(20.dp),
            tint = Color.White
        )
        Text(
            text = label,
            fontSize = 10.sp,
            color = Color.White
        )
    }
}

@Composable
fun SynopsisSection(
    synopsis: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Synopsis",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = synopsis,
            fontSize = 13.sp,
            color = Gray400,
            lineHeight = 20.sp
        )
    }
}

@Composable
fun CastSection(
    cast: List<CastMemberModel>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Casting",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            cast.forEach { member ->
                CastMemberItem(member)
            }
        }
    }
}

@Composable
private fun CastMemberItem(
    member: CastMemberModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.width(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Indigo600, Purple700)
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            if (member.profilePictureUrl != null) {
                AsyncImage(
                    model = member.profilePictureUrl,
                    contentDescription = member.name,
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.person),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp),
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = member.name,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            textAlign = TextAlign.Center,
            maxLines = 2
        )
    }
}

@Composable
fun SimilarMoviesSection(
    movies: List<MovieModel>,
    onMovieClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Films similaires",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            repeat((movies.size + 2) / 3) { rowIndex ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    repeat(3) { colIndex ->
                        val itemIndex = rowIndex * 3 + colIndex
                        if (itemIndex < movies.size) {
                            val movie = movies[itemIndex]
                            MovieCard(
                                title = movie.title,
                                rating = movie.rating,
                                posterUrl = movie.posterUrl,
                                modifier = Modifier.weight(1f),
                                onClick = { onMovieClick(movie.title) }
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

data class CastMember(
    val name: String,
    val photoUrl: String? = null,
)

@Preview(showBackground = true, backgroundColor = 0xFF111827)
@Composable
fun MovieDetailsComponentsPreview() {
    Column(
        modifier = Modifier
            .background(Gray900)
            .padding(bottom = 16.dp)
    ) {
        MovieDetailsHeader(
            title = "Dune: Part Two",
            year = "2024"
        )

        Spacer(modifier = Modifier.height(16.dp))

        MoviePosterInfo(
            title = "Dune: Part Two",
            year = "2024",
            duration = "2h 46min",
            rating = 8.5
        )

        Spacer(modifier = Modifier.height(16.dp))

        GenreChips(
            genres = listOf("Action", "Sci-Fi", "Aventure")
        )

        Spacer(modifier = Modifier.height(16.dp))

        ActionButtons(isOnFavorite = true)

        Spacer(modifier = Modifier.height(24.dp))

        SynopsisSection(
            synopsis = "Paul Atreides s'unit à Chani et aux Fremen pour mener la révolte contre ceux qui ont détruit sa famille. Hanté par de sombres prémonitions, il se trouve confronté au plus grand des dilemmes..."
        )

        Spacer(modifier = Modifier.height(24.dp))

        CastSection(
            cast = getMockMovies()[0].cast
        )
    }
}
