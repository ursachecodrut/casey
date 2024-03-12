package com.codrutursache.casey.data.remote.repository

import android.util.Log
import com.codrutursache.casey.data.remote.response.RecipeInformationResponse
import com.codrutursache.casey.data.remote.response.RecipeListResponse
import com.codrutursache.casey.data.remote.response.RecipeResponse
import com.codrutursache.casey.data.remote.service.SpoonacularService
import com.codrutursache.casey.domain.repository.ProfileRepository
import com.codrutursache.casey.domain.repository.RecipesRepository
import com.codrutursache.casey.util.Constants
import com.codrutursache.casey.util.Constants.SAVED_RECIPES_FIELD
import com.codrutursache.casey.util.Constants.USERS_COLLECTION
import com.codrutursache.casey.util.Response
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val api: SpoonacularService,
    private val firestore: FirebaseFirestore,
    private val profileRepository: ProfileRepository,
) : RecipesRepository {
    override suspend fun getRecipes(number: Int, offset: Int): Response<RecipeListResponse> =
        try {
            val response = api.complexSearch(number = number, offset = offset)
            Response.Success(response)
        } catch (e: Exception) {
            Response.Failure(e)
        }

    override suspend fun getRecipeInformation(id: Int): Response<RecipeInformationResponse> =
        try {
            val response = api.getRecipeInformation(id)
            Response.Success(response)
        } catch (e: Exception) {
            Response.Failure(e)
        }

    override suspend fun saveRecipe(recipeShort: RecipeResponse): Response<Boolean> = try {
        val docRef = firestore.collection(USERS_COLLECTION).document(profileRepository.userId ?: "")
        docRef.update(
            SAVED_RECIPES_FIELD, FieldValue.arrayUnion(
                mapOf(
                    "id" to recipeShort.id,
                    "title" to recipeShort.title,
                    "image" to recipeShort.image,
                    "imageType" to recipeShort.imageType
                )
            )
        ).await()
        Response.Success(true)
    } catch (e: Exception) {
        Response.Failure(e)
    }

}