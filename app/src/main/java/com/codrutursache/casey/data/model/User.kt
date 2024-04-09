package com.codrutursache.casey.data.model

import com.codrutursache.casey.data.response.RecipeResponse
import com.google.firebase.Timestamp

data class User(
    val displayName: String?,
    val email: String?,
    val photoUrl: String,
    val createdAt: Timestamp = Timestamp.now(),
    val savedRecipes: List<RecipeResponse> = emptyList()
) {
    constructor() : this("", "", "")
}
