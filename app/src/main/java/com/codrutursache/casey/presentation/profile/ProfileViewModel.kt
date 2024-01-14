package com.codrutursache.casey.presentation.profile

import androidx.lifecycle.ViewModel
import com.codrutursache.casey.domain.repository.AuthRepository
import com.codrutursache.casey.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
) : ViewModel() {
    val displayName get() = profileRepository.displayName
    val photoUrl get() = profileRepository.photoUrl


}