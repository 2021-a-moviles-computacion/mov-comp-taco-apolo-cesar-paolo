package com.example.moviles_computacion_2021_b

class BEntrandor(
    val nombre: String,
    val descripcion: String
) {

    override fun toString(): String {
        return "${nombre} - ${descripcion}"
    }
}