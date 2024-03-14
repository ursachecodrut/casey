package com.codrutursache.casey.data.remote.repository

import android.content.Intent
import com.codrutursache.casey.data.remote.model.User
import com.codrutursache.casey.domain.repository.AuthRepository
import com.codrutursache.casey.domain.repository.OneTapSignInResponse
import com.codrutursache.casey.domain.repository.RevokeAccessResponse
import com.codrutursache.casey.domain.repository.SignInWithIntentResponse
import com.codrutursache.casey.domain.repository.SignOutResponse
import com.codrutursache.casey.util.Constants.USERS_COLLECTION
import com.codrutursache.casey.util.Response
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FieldValue.serverTimestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val oneTapClient: SignInClient,
    private val signInRequest: BeginSignInRequest,
    private val firestore: FirebaseFirestore,
    private val googleSignInClient: GoogleSignInClient,
) : AuthRepository {

    override val isUserAuthenticatedInFirebase = auth.currentUser != null

    override suspend fun googleOneTapSignIn(): OneTapSignInResponse =
        try {
            val signInResult = oneTapClient.beginSignIn(signInRequest).await()
            Response.Success(signInResult)
        } catch (e: Exception) {
            Response.Failure(e)
        }

    override suspend fun signInWithIntent(intent: Intent): SignInWithIntentResponse {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredential = GoogleAuthProvider.getCredential(googleIdToken, null)

        return try {
            val authResult = auth.signInWithCredential(googleCredential).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            if (isNewUser) {
                addUserToFirestore()
            }
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            Response.Failure(e)
        }
    }

    override suspend fun signOut(): SignOutResponse {
        return try {
            oneTapClient.signOut().await()
            auth.signOut()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun deleteAccount(): RevokeAccessResponse {
        return try {
            auth.currentUser?.apply {
                firestore.collection(USERS_COLLECTION).document(uid).delete().await()
                googleSignInClient.revokeAccess().await()
                oneTapClient.signOut().await()
                delete().await()
            }
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    private suspend fun addUserToFirestore() {
        auth.currentUser?.apply {
            val user = User(
                email = email,
                displayName = displayName,
                photoUrl = photoUrl.toString(),
                createdAt = Timestamp.now()
            )
            firestore.collection(USERS_COLLECTION).document(uid).set(user).await()
        }
    }
}
