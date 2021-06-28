package com.example.moviles_computacion_2021_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.android.material.appbar.AppBarLayout
import java.io.BufferedWriter

class BListView : AppCompatActivity() {
    var posicionItemSeleccionado = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val arregloNumeros = BBaseDatosMemoria.arregloBEntrandor

        val adaptador = ArrayAdapter(
            this,//CONTEXTO
            android.R.layout.simple_list_item_1,//LAYOUT (VISUAL)
            arregloNumeros

        )

        val listViewEjemplo = findViewById<ListView>(R.id.ltv_ejemplo)
        listViewEjemplo.adapter = adaptador

        val btnAnadirNumero = findViewById<Button>(R.id.btn_anadir_numero)

        btnAnadirNumero.setOnClickListener{ anadirItemsAlListView(
            BEntrandor("Cesar", "pa@pa.com"),
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

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionItemSeleccionado = id
        Log.i("list-view", "ListView ${posicionItemSeleccionado}")
        Log.i("list-view", "Entrenador ${BBaseDatosMemoria.arregloBEntrandor[id]}")
    }

    fun anadirItemsAlListView(
        valor: BEntrandor,
        arreglo: ArrayList<BEntrandor>,
        adaptador: ArrayAdapter<BEntrandor>
    ){
        arreglo.add(valor)
        adaptador.notifyDataSetChanged()//actualiza la interfaz
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item?.itemId){
            //EDITAR
                R.id.mi_ditar -> {
                    Log.i("list-view", "Editar ${BBaseDatosMemoria.arregloBEntrandor[posicionItemSeleccionado]}")
                    return true
                }

            //ELIMINAR
                R.id.mi_eliminar -> {
                    Log.i("list-view", "Eliminar ${BBaseDatosMemoria.arregloBEntrandor[posicionItemSeleccionado]}")
                    return true
                }

            else -> super.onContextItemSelected(item)
        }
    }
}