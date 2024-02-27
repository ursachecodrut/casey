package com.codrutursache.casey.data.remote.repository

import com.codrutursache.casey.domain.model.UserDetails
import com.codrutursache.casey.domain.repository.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    val auth: FirebaseAuth,
) : ProfileRepository {
    override val userDetails: UserDetails
        get() = UserDetails(
            displayName = auth.currentUser?.displayName,
            photoUrl = auth.currentUser?.photoUrl.toString()
        )
}