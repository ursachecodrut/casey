package com.codrutursache.casey.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InfiniteGridScroll(
    modifier: Modifier = Modifier,
    columns: Int = 2,
    itemsCount: Int,
    isLazy: Boolean = true,
    loadMoreItems: () -> Unit = {},
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(8.dp),
    content: @Composable (index: Int) -> Unit,
) {
    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(columns),
        verticalArrangement = verticalArrangement,
        horizontalArrangement = horizontalArrangement,
        modifier = modifier,
    ) {
        items(itemsCount) { index ->
            content(index)

            if (isLazy && index == itemsCount - 1) {
                loadMoreItems()
            }
        }
    }
}



