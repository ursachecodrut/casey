package com.codrutursache.casey.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * A utility function that debounces the execution of a function
 *
 * This function delays the execution of the `destinationFunction` until
 * a specified wait time has passed since the last invocation. It is useful
 * for scenarios like handling user input in search bars to avoid making
 * excessive API requests.
 *

 *
 * @param T The type of the parameter that the `destinationFunction` accepts.
 * @param waitMs The time to wait in milliseconds before executing the `destinationFunction`.
 *               Default is 300 milliseconds.
 * @param coroutineScope The coroutine scope in which the debounce logic will be executed.
 *                       This allows the debounce function to handle asynchronous delays and cancellations.
 * @param destinationFunction The function to be executed after the debounce period.
 *                            This is the actual function that will be called once the specified wait time
 *                            has passed without any new invocations.
 *
 * @return A lambda function that wraps the `destinationFunction` with debounce logic.
 *         This lambda function should be called with the input parameter that the `destinationFunction` expects.
 *
 * @sample
 * ```
 * val debouncedSearch = debounce<String>(
 *     waitMs = 500L,
 *     coroutineScope = viewModelScope
 * ) { query ->
 *     // Perform search with the query
 * }
 *
 * fun onSearchQueryChanged(query: String) {
 *     debouncedSearch(query)
 * }
 * ```
 */
fun <T> debounce(
    waitMs: Long = 300L,
    coroutineScope: CoroutineScope,
    destinationFunction: (T) -> kotlin.Unit
): (T) -> kotlin.Unit {
    var debounceJob: Job? = null
    return { param: T ->
        debounceJob?.cancel()
        debounceJob = coroutineScope.launch {
            delay(waitMs)
            destinationFunction(param)
        }
    }
}