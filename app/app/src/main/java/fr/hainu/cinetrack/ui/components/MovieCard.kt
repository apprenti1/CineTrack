package fr.hainu.cinetrack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.drawBehind
import coil.compose.AsyncImage
import fr.hainu.cinetrack.R
import fr.hainu.cinetrack.ui.theme.Gray400
import fr.hainu.cinetrack.ui.theme.Gray800
import fr.hainu.cinetrack.ui.theme.Pink600
import fr.hainu.cinetrack.ui.theme.Purple600
import fr.hainu.cinetrack.ui.theme.Yellow400

@Composable
fun BigMovieCard(
    title: String,
    rating: Double,
    posterUrl: String? = null,
    modifier: Modifier = Modifier,
    gradientStart: Color = Purple600,
    gradientEnd: Color = Pink600,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .width(160.dp)
            .height(240.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(gradientStart, gradientEnd)
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            if (posterUrl != null) {
                AsyncImage(
                    model = posterUrl,
                    contentDescription = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
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

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(80.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0f),
                            Color.Black.copy(alpha = 0.9f)
                        )
                    )
                )
                .padding(12.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = Yellow400
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = rating.toString(),
                        fontSize = 12.sp,
                        color = Yellow400,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    maxLines = 2
                )
            }
        }
    }
}

@Composable
fun MovieCard(
    title: String,
    rating: Double,
    withRating: Boolean = false,
    posterUrl: String? = null,
    modifier: Modifier = Modifier,
    gradientStart: Color = Purple600,
    gradientEnd: Color = Pink600,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .height(160.dp)
                .width(106.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(gradientStart, gradientEnd)
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            if (posterUrl != null) {
                AsyncImage(
                    model = posterUrl,
                    contentDescription = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
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

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(4.dp))

        if (withRating) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = null,
                    modifier = Modifier.size(12.dp),
                    tint = Yellow400
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = rating.toString(),
                    fontSize = 12.sp,
                    color = Yellow400,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun MovieCardHorizontal(
    title: String,
    year: String,
    genres: String,
    rating: Double,
    posterUrl: String? = null,
    modifier: Modifier = Modifier,
    gradientStart: Color = Purple600,
    gradientEnd: Color = Pink600,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Gray800)
            .padding(12.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(112.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(gradientStart, gradientEnd)
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            if (posterUrl != null) {
                AsyncImage(
                    model = posterUrl,
                    contentDescription = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.film),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "$year • $genres",
                fontSize = 12.sp,
                color = Gray400
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = Yellow400
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = rating.toString(),
                    fontSize = 12.sp,
                    color = Yellow400,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF111827)
@Composable
fun MovieCardPreview() {
    Column {

        BigMovieCard(
            title = "Sans URL",
            rating = 7.8,
            gradientStart = Purple600,
            gradientEnd = Pink600,
            modifier = Modifier
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        MovieCard(
            title = "Sans URL",
            rating = 7.8,
            modifier = Modifier
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        MovieCardHorizontal(
            title = "Sans URL",
            year = "2024",
            genres = "Drame, Guerre",
            rating = 7.6,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}
