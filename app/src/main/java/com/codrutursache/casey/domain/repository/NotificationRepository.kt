package com.codrutursache.casey.domain.repository

import com.codrutursache.casey.data.response.RandomFoodTriviaResponse
import com.codrutursache.casey.domain.model.Resource

interface NotificationRepository {

    suspend fun getRandomFoodTrivia(): Resource<RandomFoodTriviaResponse>
}