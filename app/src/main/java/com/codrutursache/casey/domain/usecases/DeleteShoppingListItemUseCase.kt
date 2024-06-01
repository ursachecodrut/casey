package com.codrutursache.casey.domain.usecases

import com.codrutursache.casey.domain.repository.ShoppingListRepository
import javax.inject.Inject

class DeleteShoppingListItemUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository
) {
    suspend operator fun invoke(shoppingItemId: Int) {
        shoppingListRepository.deleteShoppingItem(shoppingItemId)
    }

}
