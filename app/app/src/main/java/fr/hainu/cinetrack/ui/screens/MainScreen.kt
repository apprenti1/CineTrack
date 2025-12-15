package fr.hainu.cinetrack.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.ui.components.BottomNavigationBar
import fr.hainu.cinetrack.ui.components.HomeHeader
import fr.hainu.cinetrack.ui.components.NavItem
import fr.hainu.cinetrack.ui.theme.Gray900
import fr.hainu.cinetrack.ui.viewmodels.MoviesViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    viewModel: MoviesViewModel = viewModel(),
    onMovieClick: (MovieModel) -> Unit = {}
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 4 })
    val currentPage = remember { mutableStateOf(NavItem.HOME) }
    val coroutineScope = rememberCoroutineScope()
    val searchText = remember { mutableStateOf("") }

    viewModel.fetchMoviesBySearch(searchText.value)

    LaunchedEffect(pagerState.currentPage) {
        currentPage.value = when (pagerState.currentPage) {
            0 -> NavItem.HOME
            1 -> NavItem.EXPLORE
            2 -> NavItem.COLLECTION
            3 -> NavItem.PROFILE
            else -> NavItem.HOME
        }
    }

    val navigateToExploreWithSearch: (String) -> Unit = { text ->
        searchText.value = text
        currentPage.value = NavItem.EXPLORE
        coroutineScope.launch {
            pagerState.animateScrollToPage(1)
        }
    }

    val showSearchBar = currentPage.value == NavItem.HOME || currentPage.value == NavItem.EXPLORE

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(
                visible = showSearchBar,
                enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
            ) {
                HomeHeader(
                    searchText = searchText,
                    onSearchFocus = navigateToExploreWithSearch
                )
            }

            HorizontalPager(
                state = pagerState,
                userScrollEnabled = true,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) { page ->
                when (page) {
                    0 -> HomeScreenContent(
                        viewModel = viewModel,
                        onMovieClick = onMovieClick,
                        onSeeAllClick = { navigateToExploreWithSearch("") }
                    )
                    1 -> ExploreScreenContent(
                        viewModel = viewModel,
                        onMovieClick = onMovieClick
                    )
                    2 -> CollectionScreen(
                        onMovieClick = onMovieClick
                    )
                    3 -> PlaceholderScreenContent(title = "Profil")
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            BottomNavigationBar(
                activeItem = currentPage.value,
                onItemClick = { navItem ->
                    currentPage.value = navItem
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(
                            when (navItem) {
                                NavItem.HOME -> 0
                                NavItem.EXPLORE -> 1
                                NavItem.COLLECTION -> 2
                                NavItem.PROFILE -> 3
                            }
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun HomeScreenContent(
    viewModel: MoviesViewModel,
    onMovieClick: (MovieModel) -> Unit,
    onSeeAllClick: () -> Unit = {}
) {
    HomeScreen(
        viewModel = viewModel,
        onMovieClick = onMovieClick,
        activeNavItem = NavItem.HOME,
        onNavItemClick = {},
        hideBottomNav = true,
        onSeeAllClick = onSeeAllClick
    )
}

@Composable
fun ExploreScreenContent(
    viewModel: MoviesViewModel,
    onMovieClick: (MovieModel) -> Unit
) {
    ExploreScreen(
        viewModel = viewModel,
        onMovieClick = onMovieClick,
        activeNavItem = NavItem.EXPLORE,
        onNavItemClick = {},
        hideBottomNav = true
    )
}

@Composable
fun PlaceholderScreenContent(title: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray900)
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}