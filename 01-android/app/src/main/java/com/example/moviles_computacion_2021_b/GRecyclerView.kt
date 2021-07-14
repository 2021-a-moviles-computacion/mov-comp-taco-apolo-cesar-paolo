package com.example.moviles_computacion_2021_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GRecyclerView : AppCompatActivity() {
    var totalLikes = 0
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

        val recyclerViewEntrenador = findViewById<RecyclerView>(
            R.id.rv_entrenadores
        )

        iniciarRecyclerView(
            listaEntrenador,
            this,
            recyclerViewEntrenador
        )

    }

    fun iniciarRecyclerView(
        lista: List<BEntrandor>,
        activity: GRecyclerView,
        recyclerView: RecyclerView
    ){
        val adapter = FRecyclerViewAdaptadorNombreCedula(
            activity,
            lista,
            recyclerView
        )
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(activity)
        adapter.notifyDataSetChanged()
    }

    fun aumentarTotalLikes(){
        totalLikes = totalLikes + 1
        val textView = findViewById<TextView>(R.id.tv_total_likes)
        textView.text = totalLikes.toString()
    }
}