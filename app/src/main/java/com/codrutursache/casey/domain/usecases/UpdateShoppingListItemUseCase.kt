package com.codrutursache.casey.domain.usecases

import com.codrutursache.casey.domain.model.ShoppingItemEntity
import com.codrutursache.casey.domain.repository.ShoppingListRepository
import javax.inject.Inject

class UpdateShoppingListItemUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository
) {
    suspend operator fun invoke(shoppingItem: ShoppingItemEntity) {
        shoppingListRepository.insertShoppingItem(shoppingItem)
    }
}