package com.codrutursache.casey.data.repository

import android.util.Log
import com.codrutursache.casey.data.model.User
import com.codrutursache.casey.data.response.RecipeResponse
import com.codrutursache.casey.domain.model.UserDetails
import com.codrutursache.casey.domain.repository.ProfileRepository
import com.codrutursache.casey.Constants.USERS_COLLECTION
import com.codrutursache.casey.domain.model.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : ProfileRepository {
    private var _userId: String? = null
    override val userId: String?
        get() = _userId

    private val authStateListener = FirebaseAuth.AuthStateListener {
        _userId = it.currentUser?.uid
        // Potentially trigger a refresh of data, e.g.,
        // refreshSavedRecipes()
    }

    init {
        auth.addAuthStateListener(authStateListener)
    }

    override val userDetails: UserDetails
        get() = UserDetails(
            displayName = auth.currentUser?.displayName,
            photoUrl = auth.currentUser?.photoUrl.toString(),
            email = auth.currentUser?.email
        )


    override suspend fun getSavedRecipes(): Resource<List<RecipeResponse>> = try {
        if (userId.isNullOrEmpty() || userId == "null") {
            Resource.Failure(Exception("User not authenticated"))
        }

        Log.d("ProfileRepositoryImpl", "getSavedRecipes: userId: $userId")

        val userDoc = firestore
            .collection(USERS_COLLECTION)
            .document(userId ?: "")
            .get()
            .await()
            .toObject<User>()

        val recipes = userDoc?.savedRecipes ?: emptyList()

        Resource.Success(recipes)
    } catch (e: Exception) {
        Log.e("ProfileRepositoryImpl", "getSavedRecipes: $e")
        Resource.Failure(e)
    }

}