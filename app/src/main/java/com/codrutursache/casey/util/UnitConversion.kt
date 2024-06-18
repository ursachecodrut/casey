package com.codrutursache.casey.util

import android.util.Log
import kotlin.reflect.full.memberProperties


/**
 * A simple unit conversion library that allows you to convert between different units
 */
interface HasBaseUnit<T> {
    fun toBaseUnit(): T
}

/**
 * A class that represents a unit
 *
 * @property symbol the symbol of the unit
 * @property toBaseUnitFactor the factor to convert the unit to the base unit
 * @constructor Creates a new Unit
 */
sealed class Unit(open val symbol: String, open val toBaseUnitFactor: Double)


/**
 * -------------------------------------------------------------------------------------
 * ----------------------  Mass Units and Mass Conversion  -----------------------------
 * -------------------------------------------------------------------------------------
 */

/**
 * Mass units
 *
 * @property symbol the symbol of the unit
 * @property toBaseUnitFactor the factor to convert the unit to the base unit (grams)
 * @constructor Creates a new MassUnit
 * @see [Mass]
 */

sealed class MassUnit(symbol: String, toBaseUnitFactor: Double) : Unit(symbol, toBaseUnitFactor) {
    data object GRAM : MassUnit("g", 1.0) // base unit

    data object KILOGRAM : MassUnit("kg", 1000.0)

    data object MILLIGRAM : MassUnit("mg", 0.001)

    data object TBSP : MassUnit("tbsp", 15.0)

    data object TSP : MassUnit("tsp", 5.0)
}

/**
 * A class that represents a mass value and its unit
 *
 * - implements [HasBaseUnit] interface
 * - provides a way to convert between different units
 *
 * @property value the value of the mass
 * @property unit the unit of the mass
 * @constructor Creates a new Mass
 * @see [MassUnit]
 * @see [HasBaseUnit]
 */
data class Mass(val value: Double, val unit: MassUnit) : HasBaseUnit<Mass> {

    constructor(value: Number, symbol: String) : this(
        value.toDouble(),
        MassUnit::class.sealedSubclasses.first {
            it.objectInstance?.symbol == symbol
        }.objectInstance
            ?: throw IllegalArgumentException("No MassUnit with symbol $symbol found")
    )

    infix fun to(newUnit: MassUnit): Mass {
        val newValue = this.value * this.unit.toBaseUnitFactor / newUnit.toBaseUnitFactor
        return Mass(newValue, newUnit)
    }

    override fun toBaseUnit(): Mass {
        return to(MassUnit.GRAM)
    }


    override fun toString(): String {
        return "$value ${unit.symbol}"
    }

    operator fun plus(other: Mass): Mass {
        val otherValueInThisUnit =
            other.value * other.unit.toBaseUnitFactor / this.unit.toBaseUnitFactor
        return Mass(this.value + otherValueInThisUnit, this.unit)
    }
}

val Number.g get() = Mass(this.toDouble(), MassUnit.GRAM)
val Number.kg get() = Mass(this.toDouble(), MassUnit.KILOGRAM)
val Number.mg get() = Mass(this.toDouble(), MassUnit.MILLIGRAM)
val Number.tbsp get() = Mass(this.toDouble(), MassUnit.TBSP)
val Number.tsp get() = Mass(this.toDouble(), MassUnit.TSP)


/**
 * -------------------------------------------------------------------------------------
 * ----------------------  Volume Units and Volume Conversion  -------------------------
 * -------------------------------------------------------------------------------------
 */

sealed class VolumeUnit(symbol: String, toBaseUnitFactor: Double) : Unit(symbol, toBaseUnitFactor) {
    data object LITER : VolumeUnit("l", 1000.0)

    data object MILLILITER : VolumeUnit("ml", 1.0) // base unit

    data object GALLON : VolumeUnit("gal", 3785.41)

    data object QUART : VolumeUnit("qt", 946.353)

    data object PINT : VolumeUnit("pt", 473.176)

    data object CUP : VolumeUnit("cup", 236.588)

    data object FL_OZ : VolumeUnit("fl oz", 29.5735)
}

data class Volume(val value: Double, val unit: VolumeUnit) : HasBaseUnit<Volume> {
    constructor(value: Number, symbol: String) : this(
        value.toDouble(),
        VolumeUnit::class.sealedSubclasses.first {
            it.objectInstance?.symbol == symbol
        }.objectInstance
            ?: throw IllegalArgumentException("No VolumeUnit with symbol $symbol found")
    )

    infix fun to(newUnit: VolumeUnit): Volume {
        val newValue = this.value * this.unit.toBaseUnitFactor / newUnit.toBaseUnitFactor
        return Volume(newValue, newUnit)
    }

    override fun toBaseUnit(): Volume {
        return to(VolumeUnit.MILLILITER)
    }

    override fun toString(): String {
        return "$value ${unit.symbol}"
    }

    operator fun plus(other: Volume): Volume {
        val otherValueInThisUnit =
            other.value * other.unit.toBaseUnitFactor / this.unit.toBaseUnitFactor
        return Volume(this.value + otherValueInThisUnit, this.unit)
    }
}

val Number.L get() = Volume(this.toDouble(), VolumeUnit.LITER)
val Number.mL get() = Volume(this.toDouble(), VolumeUnit.MILLILITER)
val Number.gal get() = Volume(this.toDouble(), VolumeUnit.GALLON)
val Number.qt get() = Volume(this.toDouble(), VolumeUnit.QUART)
val Number.pt get() = Volume(this.toDouble(), VolumeUnit.PINT)
val Number.cup get() = Volume(this.toDouble(), VolumeUnit.CUP)
val Number.fl_oz get() = Volume(this.toDouble(), VolumeUnit.FL_OZ)

fun convertToBaseUnit(amount: Double, unit: String): Double {
    return when (unit) {
        "g", "kg", "mg", "tbsp", "tsp" -> {
            val mass = Mass(amount, unit)
            mass.toBaseUnit().value
        }

        "l", "ml", "gal", "qt", "pt", "cup", "fl oz" -> {
            val volume = Volume(amount, unit)
            volume.toBaseUnit().value
        }

        else -> throw IllegalArgumentException("Invalid unit")
    }

}

fun main() {
    // write me an example that showcases the usage of the classes and functions defined above
    val mass1 = 12.tbsp
    val mass2 = 100.g

    val mass1InGrams = mass1 to MassUnit.GRAM
    val total = mass1 + mass2



}