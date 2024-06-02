package com.codrutursache.casey.domain.usecases

import com.codrutursache.casey.domain.repository.RecipesRepository
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository
) {
    suspend operator fun invoke(number: Int, offset: Int, recipeName: String = "") =
        recipesRepository.getRecipes(number = number, offset = offset, recipeName)
}