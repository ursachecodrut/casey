package com.codrutursache.casey.domain.repository

import android.content.Intent
import com.codrutursache.casey.domain.model.Resource
import com.google.android.gms.auth.api.identity.BeginSignInResult

typealias OneTapSignInResponse = Resource<BeginSignInResult>
typealias SignInWithIntentResponse = Resource<Boolean>
typealias SignOutResponse = Resource<Boolean>
typealias RevokeAccessResponse = Resource<Boolean>


interface AuthRepository {
    val isUserAuthenticatedInFirebase: Boolean
    suspend fun googleOneTapSignIn(): OneTapSignInResponse
    suspend fun signInWithIntent(intent: Intent): SignInWithIntentResponse
    suspend fun signOut(): SignOutResponse
    suspend fun deleteAccount(): RevokeAccessResponse
}