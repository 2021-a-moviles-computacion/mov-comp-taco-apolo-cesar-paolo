package com.example.examen01_musico_banda

class EMusico(
    var id: Int = getAutoID(),
    var name: String,
    var birthday: String,
    var p_awards: String,
    var ocup: String,
    var activity_m: String
) {
    companion object{
        fun getAutoID(): Int{
            val rnd = java.util.Random()
            return rnd.nextInt(1000)
        }
    }
}