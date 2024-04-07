package com.codrutursache.casey.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShoppingItemEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val quantity: Double,
    val unit: String,
    val checked: Boolean = false,
)