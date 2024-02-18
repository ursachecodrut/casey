package com.codrutursache.casey.domain.repository

import android.content.Intent
import com.codrutursache.casey.util.Response
import com.google.android.gms.auth.api.identity.BeginSignInResult

typealias OneTapSignInResponse = Response<BeginSignInResult>
typealias SignInWithIntentResponse = Response<Boolean>
typealias SignOutResponse = Response<Boolean>
typealias RevokeAccessResponse = Response<Boolean>


interface AuthRepository {
    val isUserAuthenticatedInFirebase: Boolean
    suspend fun googleOneTapSignIn(): OneTapSignInResponse
    suspend fun signInWithIntent(intent: Intent): SignInWithIntentResponse
    suspend fun signOut(): SignOutResponse
    suspend fun deleteAccount(): RevokeAccessResponse
}