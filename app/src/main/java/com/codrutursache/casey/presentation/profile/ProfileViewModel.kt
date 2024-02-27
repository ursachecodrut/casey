package com.codrutursache.casey.presentation.profile

import androidx.lifecycle.ViewModel
import com.codrutursache.casey.domain.usecases.GetProfileDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileDetailsUseCase: GetProfileDetailsUseCase
) : ViewModel() {
    val displayName get() = getProfileDetailsUseCase().displayName
    val photoUrl get() = getProfileDetailsUseCase().photoUrl


}