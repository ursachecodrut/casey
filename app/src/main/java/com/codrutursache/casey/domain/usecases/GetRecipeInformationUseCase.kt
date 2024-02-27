package com.codrutursache.casey.domain.usecases

import com.codrutursache.casey.domain.repository.RecipesRepository
import javax.inject.Inject

class GetRecipeInformationUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository
) {
    suspend operator fun invoke(id: Int) = recipesRepository.getRecipeInformation(id)
}