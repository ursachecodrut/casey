package com.codrutursache.casey.domain.usecases

import com.codrutursache.casey.domain.repository.ProfileRepository
import com.codrutursache.casey.domain.repository.RecipesRepository
import javax.inject.Inject

class GetSavedRecipesUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke() = profileRepository.getSavedRecipesIds()
}