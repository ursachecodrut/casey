package com.codrutursache.casey.domain.usecases

import com.codrutursache.casey.domain.repository.ProfileRepository
import javax.inject.Inject

class GetProfileDetailsUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {

    operator fun invoke() = profileRepository.userDetails
}