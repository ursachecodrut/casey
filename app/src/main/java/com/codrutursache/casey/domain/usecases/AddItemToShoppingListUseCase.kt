package com.codrutursache.casey.domain.usecases

import com.codrutursache.casey.domain.model.ShoppingItemEntity
import com.codrutursache.casey.domain.repository.ShoppingListRepository
import javax.inject.Inject

class AddItemToShoppingListUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
) {
    suspend operator fun invoke(
        name: String,
        quantity: Double,
        unit: String,
        isChecked: Boolean
    ) {
        val shoppingItem = ShoppingItemEntity(
            name = name,
            quantity = quantity,
            unit = unit,
            checked = isChecked,
        )
        shoppingListRepository.insertShoppingItem(shoppingItem)
    }
}