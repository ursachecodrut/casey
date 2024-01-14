package com.codrutursache.casey.data.repository

import com.codrutursache.casey.domain.repository.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    val auth: FirebaseAuth,
) : ProfileRepository {
    override val displayName = auth.currentUser?.displayName
    override val photoUrl = auth.currentUser?.photoUrl.toString()
}