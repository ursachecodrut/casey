package com.codrutursache.casey.domain.usecases

import com.codrutursache.casey.data.response.ExtendedIngredientResponse
import com.codrutursache.casey.domain.model.ShoppingItemEntity
import com.codrutursache.casey.domain.repository.ShoppingListRepository
import javax.inject.Inject

class AddIngredientsToShoppingListUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
) {
    suspend operator fun invoke(
        ingredients: List<ExtendedIngredientResponse>,
        numberOfServings: Int
    ) {
        val shoppingItems = ingredients.map { it.toShoppingListItem(numberOfServings) }
        shoppingListRepository.insertBatchShoppingItems(shoppingItems)
    }
}

fun ExtendedIngredientResponse.toShoppingListItem(numberOfServings: Int): ShoppingItemEntity {
    return ShoppingItemEntity(
        id = id,
        name = name,
        quantity = measuresResponse.metricResponse.amount * numberOfServings,
        unit = measuresResponse.metricResponse.unitShort
    )
}
