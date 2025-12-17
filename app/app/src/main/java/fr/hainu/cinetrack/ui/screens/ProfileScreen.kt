package fr.hainu.cinetrack.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.hainu.cinetrack.R
import fr.hainu.cinetrack.ui.theme.*
import fr.hainu.cinetrack.viewmodels.UserViewModel
import fr.hainu.cinetrack.domain.models.UserModel
import fr.hainu.cinetrack.ui.components.CustomInput
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ProfileScreen(
    userViewModel: UserViewModel,
    onNavigateBack: () -> Unit,
    onLogout: () -> Unit
) {
    val currentUser by userViewModel.currentUser.collectAsState()
    val errorMessage by userViewModel.errorMessage.collectAsState()
    val context = LocalContext.current
    var isEditing by remember { mutableStateOf(false) }
    var editPseudo by remember { mutableStateOf("") }
    var editEmail by remember { mutableStateOf("") }

    LaunchedEffect(currentUser) {
        currentUser?.let {
            editPseudo = it.pseudo
            editEmail = it.email
        }
    }

    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            userViewModel.clearError()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray900),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {
            // Profile Header
            ProfileHeader(
                currentUser = currentUser,
                isEditing = isEditing,
                onEditClick = { isEditing = true },
                onCancelEdit = {
                    isEditing = false
                    currentUser?.let {
                        editPseudo = it.pseudo
                        editEmail = it.email
                    }
                },
                onLogout = {
                    userViewModel.logout()
                    onLogout()
                }
            )

            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Spacer(modifier = Modifier.height(24.dp))

                if (isEditing) {
                    // Edit Profile Form
                    EditProfileSection(
                        pseudo = editPseudo,
                        email = editEmail,
                        onPseudoChange = { editPseudo = it },
                        onEmailChange = { editEmail = it },
                        onSave = {
                            // TODO: Add updateProfile method to UserViewModel when backend endpoint is ready
                            Toast.makeText(context, "Fonctionnalité en cours de développement", Toast.LENGTH_SHORT).show()
                            isEditing = false
                        }
                    )
                } else {
                    // User Info Display
                    UserInfoCard(currentUser = currentUser)

                    Spacer(modifier = Modifier.height(12.dp))

                    // Main Stats
                    StatsSection(currentUser = currentUser)
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun ProfileHeader(
    currentUser: UserModel?,
    isEditing: Boolean,
    onEditClick: () -> Unit,
    onCancelEdit: () -> Unit,
    onLogout: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // User Info
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Profile",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        // Action buttons
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            if (isEditing) {
                IconButton(onClick = onCancelEdit) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Annuler",
                        tint = Gray400
                    )
                }
            } else {
//                IconButton(onClick = onEditClick) {
//                    Icon(
//                        imageVector = Icons.Default.Edit,
//                        contentDescription = "Modifier le profil",
//                        tint = Purple400
//                    )
//                }
                IconButton(onClick = onLogout) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "Déconnexion",
                        tint = Color(0xFFEF4444)
                    )
                }
            }
        }
    }
}

@Composable
fun EditProfileSection(
    pseudo: String,
    email: String,
    onPseudoChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onSave: () -> Unit
) {
    Column {
        Text(
            text = "Modifier le profil",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Pseudo field
        CustomInput(
            value = pseudo,
            onValueChange = onPseudoChange,
            label = "Pseudo",
            placeholder = "Votre pseudo"
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Email field
        CustomInput(
            value = email,
            onValueChange = onEmailChange,
            label = "Email",
            placeholder = "votre@email.com"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Save button
        Button(
            onClick = onSave,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Purple600),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Enregistrer les modifications",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun UserInfoCard(currentUser: UserModel?) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Gray800)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Informations du compte",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Pseudo
            Column {
                Text(
                    text = "Pseudo",
                    fontSize = 12.sp,
                    color = Gray400
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = currentUser?.pseudo ?: "Non défini",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(color = Gray600, thickness = 1.dp)

            Spacer(modifier = Modifier.height(16.dp))

            // Email
            Column {
                Text(
                    text = "Email",
                    fontSize = 12.sp,
                    color = Gray400
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = currentUser?.email ?: "Non défini",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun StatsSection(currentUser: UserModel?) {
    val watchlistCount = currentUser?.watchlist?.size ?: 0
    val watchedCount = currentUser?.watched?.size ?: 0
    val likesCount = currentUser?.likes?.size ?: 0

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Watchlist
        StatCard(
            modifier = Modifier.weight(1f),
            iconRes = R.drawable.bookmark,
            count = watchlistCount,
            label = "À voir",
            gradientColors = listOf(Color(0xFF7C3AED), Color(0xFF9333EA))
        )

        // Watched
        StatCard(
            modifier = Modifier.weight(1f),
            iconRes = R.drawable.check_circle,
            count = watchedCount,
            label = "Vus",
            gradientColors = listOf(Color(0xFF059669), Color(0xFF10B981))
        )

        // Likes
        StatCard(
            modifier = Modifier.weight(1f),
            iconRes = R.drawable.heart,
            count = likesCount,
            label = "J'aime",
            gradientColors = listOf(Color(0xFFDC2626), Color(0xFFEF4444))
        )
    }
}

@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    iconRes: Int,
    count: Int,
    label: String,
    gradientColors: List<Color>
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
                .padding(12.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.9f),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = count.toString(),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = label,
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileHeaderPreview() {
    val mockUser = UserModel(
        id = "1",
        pseudo = "JohnDoe",
        email = "john.doe@example.com",
        password = "",
        watchlist = listOf(550, 551, 552, 553, 554),
        likes = listOf(238, 240, 242)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray900)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProfileHeader(
                currentUser = mockUser,
                isEditing = false,
                onEditClick = {},
                onCancelEdit = {},
                onLogout = {}
            )

            UserInfoCard(currentUser = mockUser)

            StatsSection(currentUser = mockUser)
        }
    }
}
