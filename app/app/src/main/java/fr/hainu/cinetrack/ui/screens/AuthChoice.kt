package fr.hainu.cinetrack.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.hainu.cinetrack.R
import fr.hainu.cinetrack.ui.components.ButtonVariant
import fr.hainu.cinetrack.ui.components.CustomButton
import fr.hainu.cinetrack.ui.theme.Gray400
import fr.hainu.cinetrack.ui.theme.Gray900
import fr.hainu.cinetrack.ui.theme.Purple600
import fr.hainu.cinetrack.ui.viewmodels.UserViewModel

@Composable
fun AuthChoiceScreen(
    userViewModel: UserViewModel = viewModel(),
    onLoginSuccess: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onContinueWithoutAccount: () -> Unit = {}
) {


    val isLoggedIn by userViewModel.isLoggedIn.collectAsState()

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            onLoginSuccess()
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
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo and title
            Icon(
                painter = painterResource(id = R.drawable.film),
                contentDescription = null,
                modifier = Modifier.size(112.dp),
                tint = Purple600
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "CineTrack",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Votre compagnon cinéma",
                fontSize = 16.sp,
                color = Gray400
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Buttons
            CustomButton(
                text = "Se connecter",
                onClick = onLoginClick,
                variant = ButtonVariant.PRIMARY
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomButton(
                text = "Créer un compte",
                onClick = onRegisterClick,
                variant = ButtonVariant.SECONDARY
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomButton(
                text = "Continuer sans compte",
                onClick = onContinueWithoutAccount,
                variant = ButtonVariant.TEXT
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AuthChoiceScreenPreview() {
    AuthChoiceScreen()
}
