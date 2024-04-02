package com.codrutursache.casey.domain.usecases

import com.codrutursache.casey.domain.repository.ShoppingListRepository
import javax.inject.Inject

class GetShoppingListUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository
) {
    suspend operator fun invoke() = shoppingListRepository.getShoppingList()
}