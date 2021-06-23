package com.example.moviles_computacion_2021_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class BListView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val arregloNumeros = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)

        val adaptador = ArrayAdapter(
            this,//CONTEXTO
            android.R.layout.simple_list_item_1,//LAYOUT (VISUAL)
            arregloNumeros

        )

        val listViewEjemplo = findViewById<ListView>(R.id.ltv_ejemplo)
        listViewEjemplo.adapter = adaptador

        val btnAnadirNumero = findViewById<Button>(R.id.btn_anadir_numero)

        btnAnadirNumero.setOnClickListener{ anadirItemsAlListView(
            1,
            arregloNumeros,
            adaptador
        )}


        /*
        listViewEjemplo
            .setOnItemLongClickListener{
                adapterView, view, posicion, id ->
                Log.i("list-view", "Dio clic en ${posicion}")

                return@setOnItemLongClickListener true
            }
        */

        registerForContextMenu(listViewEjemplo)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        //val info = menuInfo as AdapterView.AdapterContextMenuInfo
        //val id = info.position
        //posicionItemSeleccionado = id
    }

    fun anadirItemsAlListView(
        valor: Int,
        arreglo: ArrayList<Int>,
        adaptador: ArrayAdapter<Int>
    ){
        arreglo.add(valor)
        adaptador.notifyDataSetChanged()//actualiza la interfaz
    }
}