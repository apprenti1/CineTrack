package fr.hainu.cinetrack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.hainu.cinetrack.ui.theme.Gray900
import fr.hainu.cinetrack.ui.theme.Pink600
import fr.hainu.cinetrack.ui.theme.Purple600

enum class ButtonVariant {
    PRIMARY,
    SECONDARY,
    OUTLINE
}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: ButtonVariant = ButtonVariant.PRIMARY,
    backgroundColor: Color = Purple600,
    textColor: Color = Color.White,
    height: Dp = 56.dp,
    cornerRadius: Dp = 12.dp,
    enabled: Boolean = true
) {
    val colors = when (variant) {
        ButtonVariant.PRIMARY -> ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor,
            disabledContainerColor = backgroundColor.copy(alpha = 0.5f),
            disabledContentColor = textColor.copy(alpha = 0.5f)
        )
        ButtonVariant.SECONDARY -> ButtonDefaults.buttonColors(
            containerColor = backgroundColor.copy(alpha = 0.2f),
            contentColor = backgroundColor,
            disabledContainerColor = backgroundColor.copy(alpha = 0.1f),
            disabledContentColor = backgroundColor.copy(alpha = 0.3f)
        )
        ButtonVariant.OUTLINE -> ButtonDefaults.outlinedButtonColors(
            contentColor = backgroundColor,
            disabledContentColor = backgroundColor.copy(alpha = 0.3f)
        )
    }

    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        colors = colors,
        shape = RoundedCornerShape(cornerRadius),
        enabled = enabled
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
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
            text = "Primary Button",
            onClick = {},
            variant = ButtonVariant.PRIMARY
        )

        CustomButton(
            text = "Secondary Button",
            onClick = {},
            variant = ButtonVariant.SECONDARY
        )

        CustomButton(
            text = "Outline Button",
            onClick = {},
            variant = ButtonVariant.OUTLINE
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(
            text = "Custom Color",
            onClick = {},
            backgroundColor = Pink600
        )

        CustomButton(
            text = "Disabled Button",
            onClick = {},
            enabled = false
        )
    }
}
