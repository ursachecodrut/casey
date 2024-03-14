package com.codrutursache.casey.domain.repository

import com.codrutursache.casey.data.remote.response.RecipeResponse
import com.codrutursache.casey.domain.model.UserDetails
import com.codrutursache.casey.util.Response

interface ProfileRepository {
    val userId: String?
    val userDetails: UserDetails

    suspend fun getSavedRecipesIds(): Response<List<RecipeResponse>>
}