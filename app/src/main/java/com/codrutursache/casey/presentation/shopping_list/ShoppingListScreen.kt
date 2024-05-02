package com.codrutursache.casey.presentation.shopping_list

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codrutursache.casey.domain.model.ShoppingItemEntity
import com.codrutursache.casey.presentation.components.BottomBar
import com.codrutursache.casey.presentation.navigation.Route
import com.codrutursache.casey.presentation.recipe_information.components.formatAmountValue
import com.codrutursache.casey.presentation.shopping_list.components.ShoppingListTopBar
import com.codrutursache.casey.domain.model.Resource
import com.codrutursache.casey.presentation.components.LoadingScreen

@Composable
fun ShoppingListScreen(
    navigateTo: (String) -> Unit,
    resource: Resource<List<ShoppingItemEntity>>,
    toggleItem: (Int, Boolean) -> Unit,
    clearShoppingList: () -> Unit,
) {
    Scaffold(
        topBar = {
            ShoppingListTopBar(
                clearShoppingList = clearShoppingList,
            )
        },
        bottomBar = {
            BottomBar(
                currentRoute = Route.ShoppingListRoute.route,
                navigateTo = navigateTo,
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            when (resource) {
                is Resource.Loading -> {
                    LoadingScreen()
                }

                is Resource.Success -> {
                    ShoppingListScreenSuccess(
                        resource.data!!,
                        toggleItem = toggleItem
                    )
                }

                is Resource.Failure -> {
                    Text(text = resource.e.toString())
                }
            }
        }
    }
}

@Composable
fun ShoppingListScreenSuccess(
    shoppingList: List<ShoppingItemEntity>,
    toggleItem: (Int, Boolean) -> Unit
) {

    if (shoppingList.isEmpty()) {
        Text(text = "No items in the shopping list")
        return
    }

    Column(
       verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        Text(
            text = "Number of items: ${shoppingList.size}",
            style = MaterialTheme.typography.bodyLarge,
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(shoppingList.sortedBy { it.name }) { shoppingItem ->
                ShoppingListItem(
                    item = shoppingItem,
                    onCheckedChange = { checked ->
                        toggleItem(shoppingItem.id, checked)
                    },
                )
            }
        }
    }

}


@Composable
fun ShoppingListItem(
    item: ShoppingItemEntity,
    onCheckedChange: (Boolean) -> Unit
) {

    var checked by remember { mutableStateOf(item.checked) }

    ListItem(
        headlineContent = { Text(text = item.name) },
        supportingContent = {
            val amount = item.quantity
            val unit = item.unit

            Text(text = "${amount.formatAmountValue()} $unit")
        },
        trailingContent = {
            Checkbox(
                checked = checked,
                onCheckedChange = {
                    checked = it
                    onCheckedChange(it)
                }
            )
        },
        tonalElevation = 10.dp,
        modifier = Modifier
            .clip(RoundedCornerShape(10))
    )
}


@Preview(showBackground = true)
@Composable
fun ShoppingListScreenSuccessPreview() {

    val items = listOf(
        ShoppingItemEntity(
            id = 1,
            name = "Apple",
            quantity = 2.0,
            unit = "kg",
            checked = false
        ),
        ShoppingItemEntity(
            id = 2,
            name = "Banana",
            quantity = 3.0,
            unit = "kg",
            checked = true
        ),
        ShoppingItemEntity(
            id = 3,
            name = "Orange",
            quantity = 4.0,
            unit = "kg",
            checked = false
        ),
    )

    ShoppingListScreen(
        resource = Resource.Success(items),
        clearShoppingList = {},
        navigateTo = { },
        toggleItem = { _, _ -> }
    )
}


@Preview("Empty list", showBackground = true)
@Composable
fun ShoppingListScreenSuccessPreviewEmptyList() {

    ShoppingListScreen(
        resource = Resource.Success(emptyList()),
        clearShoppingList = {},
        navigateTo = { },
        toggleItem = { _, _ -> }
    )
}

@Preview(showBackground = true)
@Composable
fun ShoppingListScreenLoadingPreview() {
    ShoppingListScreen(
        resource = Resource.Loading,
        clearShoppingList = {},
        navigateTo = { },
        toggleItem = { _, _ -> }
    )
}
