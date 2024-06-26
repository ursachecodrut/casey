package com.codrutursache.casey.presentation.shopping_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.codrutursache.casey.R
import com.codrutursache.casey.domain.model.ShoppingItemEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingItemBottomSheet(
    sheetState: SheetState,
    closeSheet: () -> Unit,
    updateItem: (ShoppingItemEntity) -> Unit,
    deleteItem: (Int) -> Unit,
    addItem: (ShoppingItemEntity) -> Unit,
    shoppingItem: ShoppingItemEntity? = null
) {
    val isUpdateMode = shoppingItem != null

    var name by rememberSaveable { mutableStateOf(shoppingItem?.name ?: "Name") }
    var amount by rememberSaveable { mutableDoubleStateOf(shoppingItem?.quantity ?: 0.0) }
    var unit by rememberSaveable { mutableStateOf(shoppingItem?.unit ?: "unit") }

    val onSave = {
        if (isUpdateMode) {
            updateItem(shoppingItem!!.copy(name = name, quantity = amount, unit = unit))
        } else {
            addItem(
                ShoppingItemEntity(
                    name = name,
                    quantity = amount,
                    unit = unit
                )
            )
        }
        closeSheet()
    }

    val saveKeyboardAction = KeyboardActions(onDone = { onSave() })
    val doneKeyBoardOption = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
    val doneWithNumberKeyboardOption =
        KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Number)



    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            if (isUpdateMode) {
                updateItem(shoppingItem!!)
            }
            closeSheet()
        },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(
                    horizontal = 32.dp,
                    vertical = 8.dp
                )
                .padding(bottom = 32.dp)
        ) {
            TextField(
                label = { Text(stringResource(R.string.name)) },
                value = name,
                onValueChange = { name = it },
                keyboardActions = saveKeyboardAction,
                keyboardOptions = doneKeyBoardOption,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                label = { Text(stringResource(R.string.amount)) },
                value = amount.toString(),
                onValueChange = { amount = it.toDoubleOrNull() ?: 0.0 },
                keyboardActions = saveKeyboardAction,
                keyboardOptions = doneWithNumberKeyboardOption,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                label = { Text(stringResource(R.string.unit)) },
                value = unit,
                onValueChange = { unit = it },
                keyboardActions = saveKeyboardAction,
                keyboardOptions = doneKeyBoardOption,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (isUpdateMode) {
                    IconButton(onClick = {
                        closeSheet()
                        deleteItem(shoppingItem!!.id)
                    }) {
                        Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete item")
                    }
                }

                IconButton(onClick = onSave) {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = "Save item")
                }
            }
        }
    }
}