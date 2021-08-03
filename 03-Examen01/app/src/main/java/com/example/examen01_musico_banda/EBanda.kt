package com.example.examen01_musico_banda

class EBanda(
    //var id: Int?,
    var id: Int = getAutoID(),
    var name: String,
    var genre: String,
    var awards: String,
    var year: String,
    var funds: String
) {
    companion object{
        fun getAutoID(): Int{
            val rnd = java.util.Random()
            return rnd.nextInt(1000)
        }
    }
}