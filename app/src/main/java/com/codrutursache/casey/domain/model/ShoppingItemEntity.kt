package com.codrutursache.casey.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShoppingItemEntity(
    val name: String,
    @PrimaryKey val id: String,
)