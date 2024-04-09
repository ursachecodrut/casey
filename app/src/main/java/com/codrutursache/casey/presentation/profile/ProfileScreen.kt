package com.codrutursache.casey.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codrutursache.casey.R
import com.codrutursache.casey.data.response.RecipeResponse
import com.codrutursache.casey.presentation.components.BottomBar
import com.codrutursache.casey.presentation.navigation.Route
import com.codrutursache.casey.presentation.profile.components.ProfileBottomSheet
import com.codrutursache.casey.presentation.profile.components.ProfileTopBar
import com.codrutursache.casey.presentation.theme.Typography
import com.codrutursache.casey.Constants.MEDIUM_FIREBASE_IMAGE_TAG
import com.codrutursache.casey.Constants.SMALL_FIREBASE_IMAGE_TAG
import com.codrutursache.casey.domain.model.Resource
import com.codrutursache.casey.presentation.components.LoadingScreen
import com.codrutursache.casey.presentation.profile.components.SavedRecipeList
import com.codrutursache.casey.util.mock.Mocks

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navigateTo: (String) -> Unit,
    displayName: String?,
    photoUrl: String?,
    recipes: Resource<List<RecipeResponse>>,
    navigateToRecipeInformation: (Int, String?, String?, String?) -> Unit,
    navigateToSettings: () -> Unit,
) {

    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by remember { mutableStateOf(false) }
    val openProfileBottomSheet = { isSheetOpen = true }
    val closeProfileBottomSheet = { isSheetOpen = false }

    Scaffold(
        topBar = {
            ProfileTopBar(
                openProfileBottomSheet = openProfileBottomSheet,
            )
        },
        bottomBar = {
            BottomBar(
                currentRoute = Route.ProfileRoute.route,
                navigateTo = navigateTo,
            )
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(
                            photoUrl?.replace(
                                SMALL_FIREBASE_IMAGE_TAG,
                                MEDIUM_FIREBASE_IMAGE_TAG
                            )
                        )
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.profile_picture),
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
            }

            Text(
                text = displayName ?: stringResource(R.string.no_name),
                fontWeight = FontWeight.Bold,
                fontSize = Typography.bodyMedium.fontSize,
                letterSpacing = Typography.bodyMedium.letterSpacing,
                lineHeight = Typography.bodyMedium.lineHeight
            )

            Divider()

            when (recipes) {
                is Resource.Loading -> {
                    LoadingScreen()
                }

                is Resource.Success -> {
                    recipes.data?.let { recipes ->
                        SavedRecipeList(
                            recipes = recipes,
                            navigateToRecipeInformation = navigateToRecipeInformation
                        )
                    }
                }

                is Resource.Failure -> {
                    Text(text = stringResource(R.string.something_went_wrong))
                }
            }

        }

        if (isSheetOpen) {
            ProfileBottomSheet(
                sheetState = sheetState,
                closeSheet = closeProfileBottomSheet,
                navigateToSettings = navigateToSettings,
            )
        }
    }
}

@Preview
@Composable
fun ProfileScreenSuccessPreview(
) {
    ProfileScreen(
        navigateTo = {},
        displayName = "John Doe",
        photoUrl = "",
        recipes = Resource.Success(listOf(
            Mocks.recipeResponse,
            Mocks.recipeResponse,
            Mocks.recipeResponse
        )),
        navigateToRecipeInformation = { _, _, _, _ -> },
        navigateToSettings = {}
    )
}

@Preview
@Composable
fun ProfileScreenLoadingPreview(
) {
    ProfileScreen(
        navigateTo = {},
        displayName = "John Doe",
        photoUrl = "",
        recipes = Resource.Loading,
        navigateToRecipeInformation = { _, _, _, _ -> },
        navigateToSettings = {}
    )
}