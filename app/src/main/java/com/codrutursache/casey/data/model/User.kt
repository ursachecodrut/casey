package com.codrutursache.casey.data.model

import com.codrutursache.casey.data.response.RecipeResponse
import com.google.errorprone.annotations.Keep
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ServerTimestamp
import com.google.type.Date
import java.io.Serializable

data class User(
    val displayName: String?,
    val email: String?,
    val photoUrl: String,
    val createdAt: Timestamp = Timestamp.now(),
    val savedRecipes: List<RecipeResponse> = emptyList()
) {
    constructor() : this("", "", "")
}
