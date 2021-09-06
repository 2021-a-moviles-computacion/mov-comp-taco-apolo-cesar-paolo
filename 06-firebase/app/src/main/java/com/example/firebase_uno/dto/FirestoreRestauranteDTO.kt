package com.example.firebase_uno.dto

class FirestoreRestauranteDTO (
    var uid:String? = null,
    var nombre:String? = null
) {
    override fun toString():String{
        return nombre!!
    }
}