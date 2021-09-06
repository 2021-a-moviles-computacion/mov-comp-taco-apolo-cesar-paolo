package com.example.firebase_uno.dto

import java.text.NumberFormat
import java.util.*

class OrdenDTO(
    val nameProd: String,
    val unitPrice: Double,
    val amount: Int,

    ){
        override fun toString(): String {
            val basePrice: NumberFormat = NumberFormat.getCurrencyInstance()
            basePrice
                .setMaximumFractionDigits(2)
            basePrice
                .setCurrency(Currency.getInstance("USD"))

            return "${nameProd}       " +
                    "${basePrice.format(unitPrice)}             " +
                    "${amount}         " +
                    "${basePrice.format(calculateTotalOrder())} "
        }

        fun calculateTotalOrder():Double{
            return unitPrice * amount.toDouble()
        }
}