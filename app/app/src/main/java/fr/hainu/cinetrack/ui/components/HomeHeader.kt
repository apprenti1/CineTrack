package fr.hainu.cinetrack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.hainu.cinetrack.R
import fr.hainu.cinetrack.ui.theme.Gray400
import fr.hainu.cinetrack.ui.theme.Gray800
import fr.hainu.cinetrack.ui.theme.Gray900
import fr.hainu.cinetrack.ui.theme.Purple500
import kotlin.String

@Composable
fun HomeHeader(
    searchText: MutableState<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray900)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "CineTrack",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        SearchBar(searchText)
    }
}

@Composable
fun SearchBar(
    searchText: MutableState<String>,
    onSearchChange: (String) -> Unit = {}
) {


    TextField(
        value = searchText.value,
        onValueChange = {
            searchText.value = it
            onSearchChange(it)
        },
        modifier = Modifier
            .fillMaxWidth(),
        placeholder = {
            Text(
                "Rechercher un film, série...",
                fontSize = 14.sp,
                color = Gray400
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Search",
                modifier = Modifier.size(25.dp),
                tint = Gray400
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Gray800,
            unfocusedContainerColor = Gray800,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = Purple500,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedPlaceholderColor = Gray400,
            unfocusedPlaceholderColor = Gray400
        ),
        shape = RoundedCornerShape(12.dp),
        singleLine = true,

        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                // Handle search action
            }
        )
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF111827)
@Composable
fun HomeHeaderPreview() {
    val searchText = remember { mutableStateOf("") }
    HomeHeader(searchText)
}

@Preview(showBackground = true, backgroundColor = 0xFF111827)
@Composable
fun SearchBarPreview() {
    Column(
        modifier = Modifier
            .background(Gray900)
            .padding(16.dp)
    ) {
        val searchText = remember { mutableStateOf("") }
        SearchBar(searchText)
    }
}
