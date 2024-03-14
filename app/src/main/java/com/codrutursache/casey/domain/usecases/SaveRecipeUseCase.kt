package com.codrutursache.casey.domain.usecases

import com.codrutursache.casey.data.remote.response.RecipeResponse
import com.codrutursache.casey.domain.repository.RecipesRepository
import javax.inject.Inject

class SaveRecipeUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository
) {
    suspend operator fun invoke(recipeShort: RecipeResponse) =
        recipesRepository.saveRecipe(recipeShort)
}