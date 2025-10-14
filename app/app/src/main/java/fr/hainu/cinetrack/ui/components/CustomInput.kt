package fr.hainu.cinetrack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.hainu.cinetrack.R
import fr.hainu.cinetrack.ui.theme.Gray400
import fr.hainu.cinetrack.ui.theme.Gray800
import fr.hainu.cinetrack.ui.theme.Gray900
import fr.hainu.cinetrack.ui.theme.Purple600
import fr.hainu.cinetrack.ui.theme.Red600

@Composable
fun CustomInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    maxLines: Int = 1,
    minLines: Int = 1,
    enabled: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Gray400,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = Gray400.copy(alpha = 0.6f)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Gray800,
                unfocusedContainerColor = Gray800,
                disabledContainerColor = Gray800,
                errorContainerColor = Gray800,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                disabledTextColor = Gray400,
                errorTextColor = Color.White,
                focusedBorderColor = if (isError) Red600 else Purple600,
                unfocusedBorderColor = if (isError) Red600 else Color.Transparent,
                disabledBorderColor = Color.Transparent,
                errorBorderColor = Red600,
                cursorColor = Purple600
            ),
            shape = RoundedCornerShape(12.dp),
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            maxLines = maxLines,
            minLines = minLines,
            enabled = enabled,
            singleLine = maxLines == 1,
            isError = isError,
            trailingIcon = if (isPassword) {
                {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painterResource(
                                id = if (passwordVisible) R.drawable.filled_visibility else R.drawable.filled_visibility_off
                            ),
                            contentDescription = if (passwordVisible) "Masquer le mot de passe" else "Afficher le mot de passe",
                            tint = Gray400,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            } else null
        )

        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                fontSize = 12.sp,
                color = Red600,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomInputPreview() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .background(Gray900)
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
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

        CustomInput(
            value = password,
            onValueChange = { password = it },
            label = "Mot de passe",
            placeholder = "••••••••",
            isPassword = true
        )

        CustomInput(
            value = comment,
            onValueChange = { comment = it },
            label = "Votre avis (optionnel)",
            placeholder = "Qu'avez-vous pensé de ce film ?",
            maxLines = 3,
            minLines = 3
        )

        CustomInput(
            value = "Disabled input",
            onValueChange = {},
            label = "Disabled",
            placeholder = "Placeholder",
            enabled = false
        )
    }
}
