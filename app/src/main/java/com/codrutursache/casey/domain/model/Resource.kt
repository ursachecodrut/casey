package com.codrutursache.casey.domain.model

sealed class Resource<out T> {
    data object Loading : Resource<Nothing>()
    data class Success<out T>(val data: T?) : Resource<T>()
    data class Failure(val e: Throwable) : Resource<Nothing>()
}
