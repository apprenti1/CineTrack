package fr.hainu.cinetrack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.hainu.cinetrack.ui.theme.Gray400
import fr.hainu.cinetrack.ui.theme.Gray800
import fr.hainu.cinetrack.ui.theme.Gray900
import fr.hainu.cinetrack.ui.theme.Purple600

enum class ButtonVariant {
    PRIMARY,
    SECONDARY,
    TEXT
}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: ButtonVariant = ButtonVariant.PRIMARY,
    enabled: Boolean = true
) {
    when (variant) {
        ButtonVariant.TEXT -> {
            TextButton(
                onClick = onClick,
                modifier = modifier.fillMaxWidth(),
                enabled = enabled
            ) {
                Text(
                    text = text,
                    fontSize = 14.sp,
                    color = if (enabled) Gray400 else Gray400.copy(alpha = 0.5f)
                )
            }
        }
        else -> {
            val colors = when (variant) {
                ButtonVariant.PRIMARY -> ButtonDefaults.buttonColors(
                    containerColor = Purple600,
                    contentColor = Color.White,
                    disabledContainerColor = Purple600.copy(alpha = 0.5f),
                    disabledContentColor = Color.White.copy(alpha = 0.5f)
                )
                ButtonVariant.SECONDARY -> ButtonDefaults.buttonColors(
                    containerColor = Gray800,
                    contentColor = Color.White,
                    disabledContainerColor = Gray800.copy(alpha = 0.5f),
                    disabledContentColor = Color.White.copy(alpha = 0.5f)
                )
                else -> ButtonDefaults.buttonColors()
            }

            Button(
                onClick = onClick,
                modifier = modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = colors,
                shape = RoundedCornerShape(12.dp),
                enabled = enabled
            ) {
                Text(
                    text = text,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomButtonPreview() {
    Column(
        modifier = Modifier
            .background(Gray900)
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CustomButton(
            text = "Se connecter",
            onClick = {},
            variant = ButtonVariant.PRIMARY
        )

        CustomButton(
            text = "Créer un compte",
            onClick = {},
            variant = ButtonVariant.SECONDARY
        )

        CustomButton(
            text = "Créer un compte",
            onClick = {},
            variant = ButtonVariant.PRIMARY,
            enabled = false
        )

        CustomButton(
            text = "Créer un compte",
            onClick = {},
            variant = ButtonVariant.SECONDARY,
            enabled = false
        )

        CustomButton(
            text = "Continuer sans compte",
            onClick = {},
            variant = ButtonVariant.TEXT
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CustomButton(
                text = "Annuler",
                onClick = {},
                variant = ButtonVariant.SECONDARY,
                modifier = Modifier.weight(1f)
            )
            CustomButton(
                text = "Valider",
                onClick = {},
                variant = ButtonVariant.PRIMARY,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
