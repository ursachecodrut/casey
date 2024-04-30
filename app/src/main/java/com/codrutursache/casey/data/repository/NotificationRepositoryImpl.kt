package com.codrutursache.casey.data.repository

import com.codrutursache.casey.data.data_source.SpoonacularService
import com.codrutursache.casey.data.response.RandomFoodTriviaResponse
import com.codrutursache.casey.domain.model.Resource
import com.codrutursache.casey.domain.repository.NotificationRepository
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val api: SpoonacularService
) : NotificationRepository {
    override suspend fun getRandomFoodTrivia(): Resource<RandomFoodTriviaResponse> {
        return try {
            val response = api.getRandomFoodTrivia()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}