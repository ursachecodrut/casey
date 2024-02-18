package com.codrutursache.casey.data.remote.model

import com.google.firebase.firestore.FieldValue

data class User(
    val displayName: String?,
    val email: String?,
    val photoUrl: String,
    val createdAt: FieldValue
)
