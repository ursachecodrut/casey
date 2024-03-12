package com.codrutursache.casey.data.remote.repository

import android.util.Log
import com.codrutursache.casey.data.remote.model.User
import com.codrutursache.casey.data.remote.response.RecipeInformationResponse
import com.codrutursache.casey.data.remote.response.RecipeResponse
import com.codrutursache.casey.data.remote.service.SpoonacularService
import com.codrutursache.casey.domain.model.UserDetails
import com.codrutursache.casey.domain.repository.ProfileRepository
import com.codrutursache.casey.util.Constants.LOGGING_INTERCEPTOR_TAG
import com.codrutursache.casey.util.Constants.SAVED_RECIPES_FIELD
import com.codrutursache.casey.util.Constants.USERS_COLLECTION
import com.codrutursache.casey.util.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import okhttp3.internal.wait
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : ProfileRepository {
    override val userId = auth.currentUser?.uid
    override val userDetails: UserDetails
        get() = UserDetails(
            displayName = auth.currentUser?.displayName,
            photoUrl = auth.currentUser?.photoUrl.toString()
        )


    override suspend fun getSavedRecipesIds(): Response<List<RecipeResponse>> = try {
        val recipe = firestore
            .collection(USERS_COLLECTION)
            .document(userId ?: "")
            .get()
            .await()
            .toObject<User>()
            ?.savedRecipes
        Response.Success(recipe)
    } catch (e: Exception) {
        Response.Failure(e)
    }


}