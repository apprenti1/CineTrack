package fr.hainu.cinetrack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.hainu.cinetrack.R
import fr.hainu.cinetrack.ui.theme.Gray400
import fr.hainu.cinetrack.ui.theme.Gray800
import fr.hainu.cinetrack.ui.theme.Purple600

enum class NavItem(val title: String, val icon: Int) {
    HOME("Accueil", R.drawable.home),
    EXPLORE("Explorer", R.drawable.compass),
    COLLECTION("Collection", R.drawable.bookmark),
    PROFILE("Profil", R.drawable.person)
}

@Composable
fun BottomNavigationBar(
    activeItem: NavItem = NavItem.HOME,
    onItemClick: (NavItem) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray800)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavItem.entries.forEach { item ->
            NavigationButton(
                item = item,
                isActive = activeItem == item,
                onClick = { onItemClick(item) }
            )
        }
    }
}

@Composable
private fun NavigationButton(
    item: NavItem,
    isActive: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.title,
            modifier = Modifier.size(24.dp),
            tint = if (isActive) Purple600 else Gray400
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = item.title,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            color = if (isActive) Purple600 else Gray400
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF111827)
@Composable
fun BottomNavigationBarPreview() {
    Column {
        BottomNavigationBar(activeItem = NavItem.HOME)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF111827)
@Composable
fun BottomNavigationBarExplorePreview() {
    Column {
        BottomNavigationBar(activeItem = NavItem.EXPLORE)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF111827)
@Composable
fun BottomNavigationBarCollectionPreview() {
    Column {
        BottomNavigationBar(activeItem = NavItem.COLLECTION)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF111827)
@Composable
fun BottomNavigationBarProfilePreview() {
    Column {
        BottomNavigationBar(activeItem = NavItem.PROFILE)
    }
}
