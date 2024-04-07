package com.codrutursache.casey.domain.usecases

import com.codrutursache.casey.domain.repository.ShoppingListRepository
import javax.inject.Inject

class ClearShoppingListUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
) {
    suspend operator fun invoke() = shoppingListRepository.deleteAllShoppingItems()

}