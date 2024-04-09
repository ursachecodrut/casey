package com.codrutursache.casey.presentation.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codrutursache.casey.domain.repository.SignOutResponse
import com.codrutursache.casey.domain.usecases.SignOutUseCase
import com.codrutursache.casey.domain.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
) : ViewModel() {

    var signOutResource by mutableStateOf<SignOutResponse>(Resource.Success(false))
//    var revokeAccessResponse by mutableStateOf<RevokeAccessResponse>(Response.Success(false))

    fun signOut() = viewModelScope.launch {
        signOutResource = Resource.Loading
        signOutResource = signOutUseCase()
    }

//    fun revokeAccess() = viewModelScope.launch {
//        revokeAccessResponse = Response.Loading
//        revokeAccessResponse = authRepository.deleteAccount()
//    }
}