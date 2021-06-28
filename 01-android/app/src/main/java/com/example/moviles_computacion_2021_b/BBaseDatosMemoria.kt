package com.example.moviles_computacion_2021_b

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrandor = arrayListOf<BEntrandor>()
        init {
            arregloBEntrandor
                .add(
                    BEntrandor("Cesar", "c@c,com")
                )
            arregloBEntrandor
                .add(
                    BEntrandor("Mateo", "m@m.com")
                )
            arregloBEntrandor
                .add(
                    BEntrandor("Miriam", "ma@ma.com")
                )
        }
    }

}