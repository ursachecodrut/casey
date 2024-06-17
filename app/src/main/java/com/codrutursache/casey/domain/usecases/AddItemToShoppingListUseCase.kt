package com.codrutursache.casey.domain.usecases

import com.codrutursache.casey.domain.model.ShoppingItemEntity
import com.codrutursache.casey.domain.repository.ShoppingListRepository
import javax.inject.Inject

class AddItemToShoppingListUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
) {
    suspend operator fun invoke(item: ShoppingItemEntity) {
        shoppingListRepository.insertShoppingItem(item)
    }
}