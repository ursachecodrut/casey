package com.codrutursache.casey.domain.repository

import com.codrutursache.casey.data.response.RecipeResponse
import com.codrutursache.casey.domain.model.UserDetails
import com.codrutursache.casey.domain.model.Resource

interface ProfileRepository {
    val userId: String?
    val userDetails: UserDetails

    suspend fun getSavedRecipes(): Resource<List<RecipeResponse>>
}