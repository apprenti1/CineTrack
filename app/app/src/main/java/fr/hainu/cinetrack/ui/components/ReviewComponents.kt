package fr.hainu.cinetrack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import fr.hainu.cinetrack.R
import fr.hainu.cinetrack.ui.theme.*

data class Review(
    val userName: String,
    val userAvatarUrl: String? = null,
    val rating: Int,
    val comment: String,
    val timeAgo: String,
    val likes: Int = 0,
)

@Composable
fun ReviewItem(
    review: Review,
    onLikeClick: () -> Unit = {},
    onReplyClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Gray800, RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Indigo600, Purple700)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (review.userAvatarUrl != null) {
                        AsyncImage(
                            model = review.userAvatarUrl,
                            contentDescription = review.userName,
                            modifier = Modifier.size(40.dp),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.person),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = review.userName,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )

                        Text(
                            text = review.timeAgo,
                            fontSize = 11.sp,
                            color = Gray400
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(5) { index ->
                            Icon(
                                painter = painterResource(
                                    id = if (index < review.rating) R.drawable.star else R.drawable.star_outline
                                ),
                                contentDescription = null,
                                modifier = Modifier.size(14.dp),
                                tint = if (index < review.rating) Yellow400 else Gray600
                            )
                            if (index < 4) {
                                Spacer(modifier = Modifier.width(2.dp))
                            }
                        }

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = "${review.rating}/5",
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = review.comment,
                fontSize = 13.sp,
                color = Gray300,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.clickable(onClick = onLikeClick),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.thumb_up),
                        contentDescription = "J'aime",
                        modifier = Modifier.size(16.dp),
                        tint = Gray400
                    )
                    Text(
                        text = review.likes.toString(),
                        fontSize = 12.sp,
                        color = Gray400
                    )
                }

                Row(
                    modifier = Modifier.clickable(onClick = onReplyClick),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.chat),
                        contentDescription = "Répondre",
                        modifier = Modifier.size(16.dp),
                        tint = Gray400
                    )
                    Text(
                        text = "Répondre",
                        fontSize = 12.sp,
                        color = Gray400
                    )
                }
            }
        }
    }
}

@Composable
fun ReviewsSection(
    reviews: List<Review>,
    onAddReviewClick: () -> Unit = {},
    onLoadMoreClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Avis des utilisateurs",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )

            Row(
                modifier = Modifier.clickable(onClick = onAddReviewClick),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_circle),
                    contentDescription = "Ajouter",
                    modifier = Modifier.size(16.dp),
                    tint = Purple400
                )
                Text(
                    text = "Ajouter",
                    fontSize = 12.sp,
                    color = Purple400
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            reviews.forEach { review ->
                ReviewItem(review = review)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Gray800)
                    .clickable(onClick = onLoadMoreClick)
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Voir plus d'avis",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Purple400
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF111827)
@Composable
fun ReviewItemPreview() {
    Column(
        modifier = Modifier
            .background(Gray900)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ReviewItem(
            review = Review(
                userName = "Marie Dubois",
                rating = 5,
                comment = "Un chef-d'œuvre absolu ! Denis Villeneuve a réussi à surpasser le premier opus. Les visuels sont à couper le souffle et la bande-son de Hans Zimmer est magistrale.",
                timeAgo = "Il y a 2 jours",
                likes = 24,
            )
        )

        ReviewItem(
            review = Review(
                userName = "Thomas Laurent",
                rating = 4,
                comment = "Excellent film, même si j'ai trouvé le rythme un peu lent par moments. Les performances des acteurs sont remarquables.",
                timeAgo = "Il y a 5 jours",
                likes = 12,
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF111827)
@Composable
fun ReviewsSectionPreview() {
    ReviewsSection(
        reviews = listOf(
            Review(
                userName = "Marie Dubois",
                rating = 5,
                comment = "Un chef-d'œuvre absolu ! Denis Villeneuve a réussi à surpasser le premier opus.",
                timeAgo = "Il y a 2 jours",
                likes = 24,
            ),
            Review(
                userName = "Thomas Laurent",
                rating = 4,
                comment = "Excellent film, même si j'ai trouvé le rythme un peu lent par moments.",
                timeAgo = "Il y a 5 jours",
                likes = 12,
            )
        ),
        modifier = Modifier
            .background(Gray900)
            .padding(vertical = 24.dp)
    )
}
