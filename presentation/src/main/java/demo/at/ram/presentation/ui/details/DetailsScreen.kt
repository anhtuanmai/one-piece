package demo.at.ram.presentation.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.tools.screenshot.PreviewTest
import demo.at.ram.domain.model.Character
import demo.at.ram.domain.model.Crew
import demo.at.ram.domain.model.Fruit
import demo.at.ram.presentation.R
import demo.at.ram.presentation.designsystem.theme.MyAppTheme
import demo.at.ram.presentation.designsystem.view.ImageWithStates
import demo.at.ram.presentation.designsystem.view.RamIconToggleButton
import demo.at.ram.presentation.ui.log.LogCompositions
import demo.at.ram.shared.annotation.ExcludeFromJacocoGeneratedReport

/**
 * @param viewModel ViewModel injected via [DetailsViewModel.Factory]
 */
@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    LogCompositions("DetailsScreen")

    val detailsUiState by viewModel.detailsUiState.collectAsStateWithLifecycle()

    DetailsContent(detailsUiState, viewModel::toggleFavorite)
}

@Composable
internal fun DetailsContent(detailsUiState: DetailsUiState, toggleFavorite: (Boolean) -> Unit) {
    LogCompositions(detailsUiState.toString())

    Box(modifier = Modifier.testTag("details_screen")) {
        when (detailsUiState) {
            is DetailsUiState.Success -> {
                LogCompositions("DetailsUiState.Success")
                val character = detailsUiState.character
                val isFavorite = detailsUiState.isFavorite
                Column(modifier = Modifier.padding(16.dp)) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                        LogCompositions("Box isFavorite")
                        RamIconToggleButton(
                            checked = isFavorite,
                            onCheckedChange = toggleFavorite,
                            icon = { Icon(Icons.Default.FavoriteBorder, null) },
                            checkedIcon = { Icon(Icons.Default.Favorite, null) },
                        )
                    }
                    Character(character = character)
                }
            }

            is DetailsUiState.Loading -> {
                CircularProgressIndicator()
            }

            is DetailsUiState.Error -> {
                Text(detailsUiState.throwable.message ?: "empty error message")
            }
        }
    }
}

@Composable
internal fun Character(character: Character) {
    Column {
        LogCompositions("Column character")

        ImageWithStates(character.fruit?.filename, Modifier.width(200.dp))

        CharacterInfo(
            label = stringResource(R.string.label_name),
            value = character.name,
            testTag = "character_name"
        )
        CharacterInfo(
            label = stringResource(R.string.label_status),
            value = character.status,
            testTag = "character_status"
        )
        CharacterInfo(
            label = "Size",
            value = character.size,
            testTag = "character_size"
        )
        CharacterInfo(
            label = "Age",
            value = character.age,
            testTag = "character_age"
        )
        CharacterInfo(
            label = "Bounty",
            value = character.bounty,
            testTag = "character_bounty"
        )
        CharacterInfo(
            label = "Job",
            value = character.job,
            testTag = "character_job"
        )
        character.crew?.name?.let {
            CharacterInfo(
                label = "Crew",
                value = it,
                testTag = "character_crew"
            )
        }
        character.fruit?.name?.let {
            CharacterInfo(
                label = "Fruit",
                value = it,
                testTag = "character_fruit"
            )
        }
    }
}

@Composable
private fun CharacterInfo(
    label: String,
    value: String?,
    testTag: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("$label: ")
            }
            if (value != null) {
                append(value)
            }
        },
        modifier = modifier
            .testTag(testTag)
            .semantics(mergeDescendants = true) {
                contentDescription = "$label: $value"
            }
    )
}

@ExcludeFromJacocoGeneratedReport
@PreviewTest
@Preview(showBackground = true, device = "id:pixel_7")
@Composable
fun DetailsScreenPreview() {
    MyAppTheme {
        DetailsContent(
            DetailsUiState.Success(
                Character(
                    id = 1,
                    name = "Monkey D Luffy",
                    size = "174cm",
                    age = "19 ans",
                    bounty = "3.000.000.000",
                    job = "Captain",
                    status = "vivant",
                    crew = Crew(id = 1, name = "The Chapeau de Paille crew"),
                    fruit = Fruit(id = 1, name = "Gum-Gum Fruit", filename = "https://images.api-onepiece.com/fruits/5665e89442022d4c0e7684c650dc6d6b.png")
                ),
                true
            )
        ) { isFavorite ->
            println("isFavorite = $isFavorite")
        }
    }
}

@ExcludeFromJacocoGeneratedReport
@Preview(showBackground = true)
@Composable
fun CharacterPreview() {
    MyAppTheme {
        Character(
            Character(
                id = 1,
                name = "Monkey D Luffy",
                size = "174cm",
                age = "19 ans",
                bounty = "3.000.000.000",
                job = "Captain",
                status = "vivant",
                crew = Crew(id = 1, name = "The Chapeau de Paille crew"),
                fruit = Fruit(id = 1, name = "Gum-Gum Fruit", filename = "https://images.api-onepiece.com/fruits/5665e89442022d4c0e7684c650dc6d6b.png")
            )
        )
    }
}
