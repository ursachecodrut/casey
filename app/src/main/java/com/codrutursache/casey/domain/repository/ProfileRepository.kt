package com.codrutursache.casey.domain.repository

import com.codrutursache.casey.domain.model.UserDetails

interface ProfileRepository {
    val userDetails: UserDetails
}