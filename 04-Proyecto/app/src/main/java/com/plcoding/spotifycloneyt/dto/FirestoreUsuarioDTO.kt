package com.plcoding.spotifycloneyt.dto

class FirestoreUsuarioDTO(
    var uid : String = "",
    var email : String = "",
    var roles : ArrayList<String> = arrayListOf()
) {
}