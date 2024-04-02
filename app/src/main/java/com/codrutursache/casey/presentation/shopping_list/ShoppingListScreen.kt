package com.codrutursache.casey.presentation.shopping_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codrutursache.casey.domain.model.ShoppingItemEntity
import com.codrutursache.casey.presentation.base.ProgressBar
import com.codrutursache.casey.presentation.base.SwipeToDismissContainer
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

    var programmingLanguages = remember {
        mutableStateListOf(
            "Kotlin",
            "Java",
            "Swift",
            "Dart",
            "JavaScript",
            "Python",
            "Ruby",
        )
    }

    LazyColumn() {
        items(
            items = programmingLanguages,
            key = { language -> language }
        ) { language ->
            SwipeToDismissContainer(item = language,
                onDelete = {
                    programmingLanguages -= language
                }
            ) {
                Text(
                    text = language,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = androidx.compose.ui.graphics.Color.Gray)
                        .padding(16.dp)
                )
            }
        }
    }


}