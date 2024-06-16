package com.codrutursache.casey.data.model

import android.net.Uri
import com.codrutursache.casey.data.response.RecipeResponse
import com.google.firebase.Timestamp

data class User(
    val displayName: String?,
    val email: String?,
    val photoUrl: String? = null,
    val createdAt: Timestamp = Timestamp.now(),
    val savedRecipes: List<RecipeResponse> = emptyList()
) {
    constructor() : this("", "")
}
