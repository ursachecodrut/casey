package com.codrutursache.casey.presentation.shopping_list

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.codrutursache.casey.domain.model.ShoppingItemEntity
import com.codrutursache.casey.presentation.base.ProgressBar
import com.codrutursache.casey.util.Response

@Composable
fun ShoppingListScreen(
    response: Response<List<ShoppingItemEntity>>
) {


    when (response) {
        is Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {
            ShoppingListScreenSuccess(response.data!!)
        }

        is Response.Failure -> {
            Text(text = "Something went wrong")
        }
    }
}

@Composable
fun ShoppingListScreenSuccess(
    shoppingList: List<ShoppingItemEntity>
) {

    Column {
        shoppingList.forEach { shoppingItem ->
            Text(text = shoppingItem.name)
        }

    }
}