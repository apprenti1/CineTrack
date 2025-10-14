package fr.hainu.cinetrack.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
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
import fr.hainu.cinetrack.ui.theme.Purple600

@Composable
fun RegisterScreen(
    onBackClick: () -> Unit = {},
    onRegister: () -> Unit = {},
    onLoginClick: () -> Unit = {}
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var termsAccepted by remember { mutableStateOf(false) }
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
                    text = "Créer un compte",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Rejoignez la communauté CineTrack",
                    fontSize = 16.sp,
                    color = Gray400
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Form
                CustomInput(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = "Nom complet",
                    placeholder = "John Doe"
                )

                Spacer(modifier = Modifier.height(16.dp))

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

                CustomInput(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = "Confirmer le mot de passe",
                    placeholder = "••••••••",
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Terms checkbox
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = termsAccepted,
                        onCheckedChange = { termsAccepted = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Purple600,
                            uncheckedColor = Gray400
                        )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Gray400)) {
                                append("J'accepte les ")
                            }
                            withStyle(style = SpanStyle(color = Purple400)) {
                                append("conditions d'utilisation")
                            }
                            withStyle(style = SpanStyle(color = Gray400)) {
                                append(" et la ")
                            }
                            withStyle(style = SpanStyle(color = Purple400)) {
                                append("politique de confidentialité")
                            }
                        },
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Register button
                CustomButton(
                    text = "Créer mon compte",
                    onClick = onRegister,
                    variant = ButtonVariant.PRIMARY
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Login link
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Déjà un compte ? ",
                        fontSize = 14.sp,
                        color = Gray400
                    )
                    TextButton(onClick = onLoginClick) {
                        Text(
                            text = "Se connecter",
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
private fun RegisterScreenPreview() {
    RegisterScreen()
}
