package com.codrutursache.casey.presentation.shopping_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codrutursache.casey.R
import com.codrutursache.casey.domain.model.ShoppingItemEntity
import com.codrutursache.casey.presentation.components.BottomBar
import com.codrutursache.casey.presentation.navigation.Route
import com.codrutursache.casey.presentation.recipe_information.components.formatAmountValue
import com.codrutursache.casey.presentation.shopping_list.components.ShoppingListTopBar
import com.codrutursache.casey.domain.model.Resource
import com.codrutursache.casey.presentation.components.LoadingScreen
import com.codrutursache.casey.presentation.shopping_list.components.ShoppingItemBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(
    navigateTo: (String) -> Unit,
    resource: Resource<List<ShoppingItemEntity>>,
    toggleItem: (Int, Boolean) -> Unit,
    updateShoppingListItem: (ShoppingItemEntity) -> Unit,
    deleteShoppingListItem: (Int) -> Unit,
    addShoppingItem: (ShoppingItemEntity) -> Unit,
    clearShoppingList: () -> Unit,
) {

    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by remember { mutableStateOf(false) }
    val openSheet = { isSheetOpen = true }
    val closeSheet = { isSheetOpen = false }
    var selectedShoppingItem by remember { mutableStateOf<ShoppingItemEntity?>(null) }
    val selectItem = { item: ShoppingItemEntity -> selectedShoppingItem = item }

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
        },
        floatingActionButton = {
            if (resource is Resource.Success) {
                SmallFloatingActionButton(
                    onClick = {
                        selectedShoppingItem = null
                        openSheet()
                    },
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add new item")
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
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
                        toggleItem = toggleItem,
                        updateItem = updateShoppingListItem,
                        deleteItem = deleteShoppingListItem,
                        addItem = addShoppingItem,
                        sheetState = sheetState,
                        openSheet = openSheet,
                        closeSheet = closeSheet,
                        isSheetOpen = isSheetOpen,
                        selectedShoppingItem = selectedShoppingItem,
                        selectItem = selectItem
                    )
                }

                is Resource.Failure -> {
                    Text(text = resource.e.toString())
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreenSuccess(
    shoppingList: List<ShoppingItemEntity>,
    toggleItem: (Int, Boolean) -> Unit,
    updateItem: (ShoppingItemEntity) -> Unit,
    deleteItem: (Int) -> Unit,
    addItem: (ShoppingItemEntity) -> Unit,
    sheetState: SheetState,
    openSheet: () -> Unit,
    closeSheet: () -> Unit,
    isSheetOpen: Boolean,
    selectedShoppingItem: ShoppingItemEntity?,
    selectItem: (ShoppingItemEntity) -> Unit
) {

    if (shoppingList.isEmpty()) {
        Text(text = stringResource(R.string.no_items_in_the_shopping_list))
        return
    }



    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        Text(
            text = stringResource(R.string.number_of_items, shoppingList.size),
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
                    openSheet = {
                        selectItem(shoppingItem)
                        openSheet()
                    }
                )
            }
        }
    }
    if (isSheetOpen) {
        ShoppingItemBottomSheet(
            sheetState = sheetState,
            closeSheet = closeSheet,
            updateItem = updateItem,
            deleteItem = deleteItem,
            addItem = addItem,
            shoppingItem = selectedShoppingItem,
        )
    }

}


@Composable
fun ShoppingListItem(
    item: ShoppingItemEntity,
    onCheckedChange: (Boolean) -> Unit,
    openSheet: () -> Unit
) {

    var checked by remember { mutableStateOf(item.checked) }

    ListItem(
        headlineContent = {
            Text(
                text = item.name,
                textDecoration = if (checked) TextDecoration.LineThrough else TextDecoration.None,
                color = if (checked) Color.Gray else LocalContentColor.current
            )
        },
        supportingContent = {
            val amount = item.quantity
            val unit = item.unit

            Text(
                text = "${amount.formatAmountValue()} $unit",
                textDecoration = if (checked) TextDecoration.LineThrough else TextDecoration.None,
                color = if (checked) Color.Gray else LocalContentColor.current
            )
        },
        trailingContent = {
            Checkbox(
                checked = checked,
                onCheckedChange = {
                    checked = it
                    onCheckedChange(it)
                },
            )
        },
        tonalElevation = if (checked) 3.dp else 10.dp,
        modifier = Modifier
            .clip(RoundedCornerShape(10))
            .clickable { openSheet() }
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
        toggleItem = { _, _ -> },
        updateShoppingListItem = { },
        deleteShoppingListItem = { },
        addShoppingItem = {}
    )
}


@Preview("Empty list", showBackground = true)
@Composable
fun ShoppingListScreenSuccessPreviewEmptyList() {

    ShoppingListScreen(
        resource = Resource.Success(emptyList()),
        clearShoppingList = {},
        navigateTo = { },
        toggleItem = { _, _ -> },
        updateShoppingListItem = { },
        deleteShoppingListItem = { },
        addShoppingItem = { }
    )
}

@Preview(showBackground = true)
@Composable
fun ShoppingListScreenLoadingPreview() {
    ShoppingListScreen(
        resource = Resource.Loading,
        clearShoppingList = {},
        navigateTo = { },
        toggleItem = { _, _ -> },
        deleteShoppingListItem = { },
        updateShoppingListItem = { },
        addShoppingItem = {}
    )
}
