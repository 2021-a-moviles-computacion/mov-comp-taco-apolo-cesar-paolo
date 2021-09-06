package com.example.firebase_uno.dto

class FirestoreProductoDTO (
    var uid:String = "",
    var nombre:String = "",
    var precio: Double? = null

){

    override fun toString():String{
        return "$${precio.toString()} \t\t $nombre"
    }
}