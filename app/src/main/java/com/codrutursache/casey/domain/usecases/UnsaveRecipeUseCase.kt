package com.codrutursache.casey.domain.usecases

import com.codrutursache.casey.domain.repository.RecipesRepository
import javax.inject.Inject

class UnsaveRecipeUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository
) {
    suspend operator fun invoke(recipeId: Int) =
        recipesRepository.unsaveRecipe(recipeId)

}