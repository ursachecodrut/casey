package com.codrutursache.casey.presentation.shopping_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.codrutursache.casey.R
import com.codrutursache.casey.domain.model.ShoppingItemEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateShoppingItemBottomSheet(
    sheetState: SheetState,
    closeSheet: () -> Unit,
    addItem: (ShoppingItemEntity) -> Unit,
    shoppingItem: ShoppingItemEntity
) {
    var name by rememberSaveable { mutableStateOf(shoppingItem.name) }
    var amount by rememberSaveable { mutableDoubleStateOf(shoppingItem.quantity) }
    var unit by rememberSaveable { mutableStateOf(shoppingItem.unit) }

    val close = {
        addItem(
            shoppingItem.copy(
                name = name,
                quantity = amount,
                unit = unit
            )
        )
        closeSheet()
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = close
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(
                horizontal = 32.dp,
                vertical = 8.dp
            )
        ) {
            TextField(
                label = { Text(stringResource(R.string.name)) },
                value = name,
                onValueChange = { name = it },
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                label = { Text(stringResource(R.string.amount)) },
                value = amount.toString(),
                onValueChange = { amount = it.toDouble() },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                label = { Text(stringResource(R.string.unit)) },
                value = unit,
                onValueChange = { unit = it },
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = close) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Close sheet")
                }
            }
        }
    }
}
