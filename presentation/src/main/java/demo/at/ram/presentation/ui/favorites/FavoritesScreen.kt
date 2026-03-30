package demo.at.ram.presentation.ui.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import demo.at.ram.presentation.ui.home.CharacterCardList
import demo.at.ram.presentation.ui.log.LogCompositions
import demo.at.ram.shared.R

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
) {
    LogCompositions("FavoritesScreen")

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    FavoritesContent(
        uiState = uiState,
        unsetFavorite = viewModel::unsetFavorite,
    )
}

@Composable
fun FavoritesContent(uiState: FavoritesUiState, unsetFavorite: (Long) -> Unit) {
    LogCompositions("FavoritesContent")

    when (uiState) {
        is FavoritesUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is FavoritesUiState.Empty -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.no_content),
                    contentDescription = "no favorites"
                )
            }
        }

        is FavoritesUiState.Success -> {
            CharacterCardList(
                characters = uiState.characters,
                onCharacterClick = unsetFavorite
            )
        }

        is FavoritesUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("${uiState.throwable.message ?: "Unknown error"}")
            }

        }
    }
}