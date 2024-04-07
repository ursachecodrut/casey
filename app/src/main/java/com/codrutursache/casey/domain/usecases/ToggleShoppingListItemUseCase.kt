package com.codrutursache.casey.domain.usecases

import com.codrutursache.casey.domain.model.ShoppingItemEntity
import com.codrutursache.casey.domain.repository.ShoppingListRepository
import javax.inject.Inject

class ToggleShoppingListItemUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository
) {
    suspend operator fun invoke(shoppingItemId: Int, checked: Boolean) {
        shoppingListRepository.toggleShoppingListItem(shoppingItemId, checked)
    }
}