package com.codrutursache.casey.presentation.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codrutursache.casey.domain.model.Response
import com.codrutursache.casey.domain.repository.AuthRepository
import com.codrutursache.casey.domain.repository.RevokeAccessResponse
import com.codrutursache.casey.domain.repository.SignOutResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    var signOutResponse by mutableStateOf<SignOutResponse>(Response.Success(false))
    var revokeAccessResponse by mutableStateOf<RevokeAccessResponse>(Response.Success(false))

    fun signOut() = viewModelScope.launch {
        signOutResponse = Response.Loading
        signOutResponse = authRepository.signOut()
    }

    fun revokeAccess() = viewModelScope.launch {
        revokeAccessResponse = Response.Loading
        revokeAccessResponse = authRepository.deleteAccount()
    }
}