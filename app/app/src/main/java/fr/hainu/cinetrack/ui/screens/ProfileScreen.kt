package fr.hainu.cinetrack.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.hainu.cinetrack.ui.theme.*
import fr.hainu.cinetrack.viewmodels.UserViewModel
import fr.hainu.cinetrack.domain.models.UserModel

@Composable
fun ProfileScreen(
    userViewModel: UserViewModel,
    onNavigateBack: () -> Unit,
    onLogout: () -> Unit
) {
    val currentUser by userViewModel.currentUser.collectAsState()
    val errorMessage by userViewModel.errorMessage.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            userViewModel.clearError()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray900)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Profile Header
            ProfileHeader(
                currentUser = currentUser,
                onLogout = {
                    userViewModel.logout()
                    onLogout()
                }
            )

            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Spacer(modifier = Modifier.height(24.dp))

                // Main Stats (Films vus / Séries vues)
                MainStatsSection(currentUser = currentUser)

                Spacer(modifier = Modifier.height(12.dp))

                // Additional Stats Card
                AdditionalStatsCard(currentUser = currentUser)

                Spacer(modifier = Modifier.height(24.dp))

                // Genres Favoris
                FavoriteGenresSection()

                Spacer(modifier = Modifier.height(24.dp))

                // Badges
                BadgesSection(currentUser = currentUser)

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun ProfileHeader(
    currentUser: UserModel?,
    onLogout: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Avatar with gradient
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Purple600, Color(0xFFEC4899))
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = Color.White
            )
        }

        // User Info
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = currentUser?.pseudo ?: "Utilisateur",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Cinéphile depuis 2024",
                fontSize = 14.sp,
                color = Gray400
            )
        }

        // Logout button
        IconButton(onClick = onLogout) {
            Icon(
                Icons.Default.ExitToApp,
                contentDescription = "Déconnexion",
                tint = Gray400,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Composable
fun MainStatsSection(currentUser: UserModel?) {
    val watchedCount = currentUser?.watched?.size ?: 0
    val seriesCount = 0 // TODO: Add series when implemented

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Films vus
        GradientStatCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Default.Star,
            count = watchedCount,
            label = "Films vus",
            gradientColors = listOf(Color(0xFF581C87), Color(0xFF7E22CE)),
            iconTint = Color(0xFFD8B4FE)
        )

        // Séries vues (placeholder)
        GradientStatCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Default.PlayArrow,
            count = seriesCount,
            label = "Séries vues",
            gradientColors = listOf(Color(0xFF831843), Color(0xFFC026D3)),
            iconTint = Color(0xFFF9A8D4)
        )
    }
}

@Composable
fun GradientStatCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    count: Int,
    label: String,
    gradientColors: List<Color>,
    iconTint: Color
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(colors = gradientColors)
                )
                .padding(16.dp)
        ) {
            Column {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = count.toString(),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = label,
                    fontSize = 14.sp,
                    color = iconTint
                )
            }
        }
    }
}

@Composable
fun AdditionalStatsCard(currentUser: UserModel?) {
    val watchedCount = currentUser?.watched?.size ?: 0
    val averageRating = 7.8f // TODO: Calculate from actual ratings

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Gray800)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Temps de visionnage (approximatif: 2h par film)
            StatRow(
                label = "Temps de visionnage",
                value = "${watchedCount * 2}h"
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Note moyenne
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Note moyenne",
                    fontSize = 14.sp,
                    color = Gray400
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFBBF24),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = averageRating.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Films cette année
            StatRow(
                label = "Films cette année",
                value = watchedCount.toString()
            )
        }
    }
}

@Composable
fun StatRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Gray400
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

@Composable
fun FavoriteGenresSection() {
    Column {
        Text(
            text = "Genres favoris",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Genre bars with progress
        GenreBar(genre = "Sci-Fi", percentage = 0.85f, color = Purple600)
        Spacer(modifier = Modifier.height(8.dp))
        GenreBar(genre = "Action", percentage = 0.70f, color = Color(0xFF3B82F6))
        Spacer(modifier = Modifier.height(8.dp))
        GenreBar(genre = "Drame", percentage = 0.60f, color = Color(0xFFEC4899))
        Spacer(modifier = Modifier.height(8.dp))
        GenreBar(genre = "Comédie", percentage = 0.45f, color = Color(0xFF10B981))
    }
}

@Composable
fun GenreBar(genre: String, percentage: Float, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Progress bar
        Box(
            modifier = Modifier
                .weight(1f)
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Gray800)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(percentage)
                    .background(color)
            )
        }

        // Genre name
        Text(
            text = genre,
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier.width(80.dp)
        )
    }
}

@Composable
fun BadgesSection(currentUser: UserModel?) {
    val watchedCount = currentUser?.watched?.size ?: 0

    Column {
        Text(
            text = "Badges",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BadgeItem(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Star,
                label = "100 Films",
                gradientColors = listOf(Color(0xFFD97706), Color(0xFFEA580C)),
                isUnlocked = watchedCount >= 100
            )
            BadgeItem(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Edit,
                label = "Critique",
                gradientColors = listOf(Purple600, Color(0xFFEC4899)),
                isUnlocked = false // TODO: Check if user has written reviews
            )
            BadgeItem(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.DateRange,
                label = "1 An",
                gradientColors = listOf(Color(0xFF2563EB), Color(0xFF06B6D4)),
                isUnlocked = false // TODO: Check account age
            )
            BadgeItem(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Warning,
                label = "Marathon",
                gradientColors = listOf(Color(0xFF059669), Color(0xFF10B981)),
                isUnlocked = false // TODO: Check for viewing marathons
            )
        }
    }
}

@Composable
fun BadgeItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    gradientColors: List<Color>,
    isUnlocked: Boolean
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(
                    brush = if (isUnlocked) {
                        Brush.linearGradient(colors = gradientColors)
                    } else {
                        Brush.linearGradient(colors = listOf(Gray800, Gray800))
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isUnlocked) Color.White else Gray600,
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.White
        )
    }
}
