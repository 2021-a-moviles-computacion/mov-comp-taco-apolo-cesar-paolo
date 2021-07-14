package com.example.moviles_computacion_2021_b

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FRecyclerViewAdaptadorNombreCedula(
    private val contexto: GRecyclerView,
    private val listaEntrenador: List<BEntrandor>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<FRecyclerViewAdaptadorNombreCedula.MyViewHolder>() {
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        val nombreTextView: TextView
        val cedulaTextView: TextView
        val likesTextView: TextView
        val accionBtn: Button
        var numeroLikes = 0

        init {
            nombreTextView = view.findViewById(R.id.tv_nombre)
            cedulaTextView = view.findViewById(R.id.tv_cedula)
            likesTextView = view.findViewById(R.id.tv_likes)
            accionBtn = view.findViewById(R.id.btn_darLike)
            accionBtn.setOnClickListener{
                this.anadirLike()
            }
        }

        fun anadirLike(){
            numeroLikes = numeroLikes + 1
            likesTextView.text = numeroLikes.toString()
            contexto.aumentarTotalLikes()
        }

    }
    //setear el layout que se va a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_vista,//Definimos la vista del recycler view
                parent,
                false
            )
        return MyViewHolder(itemView)
    }
    //setear los items de cada iteracion del arreglo
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entrenador = listaEntrenador[position]
        holder.nombreTextView.text = entrenador.nombre
        holder.cedulaTextView.text = entrenador.descripcion
        holder.accionBtn.text = "Like ${entrenador.nombre}"
        holder.likesTextView.text = "0"
    }
    //tamaño del arreglo
    override fun getItemCount(): Int {
        return listaEntrenador.size
    }
}