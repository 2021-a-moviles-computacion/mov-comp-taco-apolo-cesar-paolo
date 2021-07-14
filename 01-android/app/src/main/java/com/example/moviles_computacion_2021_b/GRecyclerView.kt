package com.example.moviles_computacion_2021_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class GRecyclerView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view)

        val listaEntrenador = arrayListOf<BEntrandor>()
        val ligaPokemon = DLiga("Kanto", "liga Kanto")

        listaEntrenador
            .add(
                BEntrandor(
                    "Cesar",
                    "1722488671",
                    ligaPokemon
                )
            )
        listaEntrenador
            .add(
                BEntrandor(
                    "Paolo",
                    "11071654851",
                    ligaPokemon
                )
            )

    }
}