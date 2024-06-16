package com.codrutursache.casey.domain.model

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codrutursache.casey.util.Mass
import com.codrutursache.casey.util.Volume

@Entity
data class ShoppingItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val quantity: Double,
    val unit: String,
    val checked: Boolean = false,
    val userId: String = ""
) {

    private fun standardizeUnit(unit: String): String {
        return when (unit.lowercase()) {
            "tsps" -> "tsp"
            "tbsps" -> "tbsp"
            "cups" -> "cup"
            "pints" -> "pint"
            "quarts" -> "quart"
            "gallons" -> "gallon"
            "L" -> "l"
            "mL" -> "ml"
            else -> unit
        }
    }

    fun changeToBaseUnit(): ShoppingItemEntity {
        Log.d("ShoppingItemEntity", "lowercase unit: ${unit.lowercase()}")
        Log.d("ShoppingItemEntity", "standardized unit: ${standardizeUnit(unit.lowercase())}")

        return when (val cleanUnit = standardizeUnit(unit.lowercase())) {
            "g", "kg", "mg", "tbsp", "tsp" -> {
                val baseUnit = Mass(quantity, cleanUnit).toBaseUnit()
                copy(quantity = baseUnit.value, unit = baseUnit.unit.symbol)
            }

            "ml", "l", "cup", "pint", "quart", "gallon" -> {
                val baseUnit = Volume(quantity, cleanUnit).toBaseUnit()
                copy(quantity = baseUnit.value, unit = baseUnit.unit.symbol)
            }

            else -> this
        }
    }

}