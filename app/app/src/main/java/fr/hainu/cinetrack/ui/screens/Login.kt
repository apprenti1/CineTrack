package fr.hainu.cinetrack.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.hainu.cinetrack.R
import fr.hainu.cinetrack.ui.components.ButtonVariant
import fr.hainu.cinetrack.ui.components.CustomButton
import fr.hainu.cinetrack.ui.components.CustomInput
import fr.hainu.cinetrack.ui.theme.Gray400
import fr.hainu.cinetrack.ui.theme.Gray900
import fr.hainu.cinetrack.ui.theme.Purple400

@Composable
fun LoginScreen(
    onBackClick: () -> Unit = {},
    onLogin: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray900)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Header with back button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = "Retour",
                        tint = Color.White
                    )
                }
            }

            // Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp)
                    .padding(bottom = 32.dp)
            ) {
                Text(
                    text = "Bon retour !",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Connectez-vous pour continuer",
                    fontSize = 16.sp,
                    color = Gray400
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Form
                CustomInput(
                    value = email,
                    onValueChange = {
                        email = it
                        isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches() || it.isEmpty()
                    },
                    label = "Email",
                    placeholder = "votre@email.com",
                    keyboardType = KeyboardType.Email,
                    isError = !isEmailValid,
                    errorMessage = if (!isEmailValid) "Adresse email invalide" else null
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomInput(
                    value = password,
                    onValueChange = { password = it },
                    label = "Mot de passe",
                    placeholder = "••••••••",
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Forgot password link
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    TextButton(onClick = onForgotPasswordClick) {
                        Text(
                            text = "Mot de passe oublié ?",
                            fontSize = 14.sp,
                            color = Purple400
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Login button
                CustomButton(
                    text = "Se connecter",
                    onClick = onLogin,
                    variant = ButtonVariant.PRIMARY
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Register link
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Pas encore de compte ? ",
                        fontSize = 14.sp,
                        color = Gray400
                    )
                    TextButton(onClick = onRegisterClick) {
                        Text(
                            text = "S'inscrire",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Purple400
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}
