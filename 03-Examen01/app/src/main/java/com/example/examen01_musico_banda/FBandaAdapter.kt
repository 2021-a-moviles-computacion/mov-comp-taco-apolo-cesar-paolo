package com.example.examen01_musico_banda

import android.content.ComponentCallbacks
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FBandaAdapter: RecyclerView.Adapter<FBandaAdapter.BandViewHolder>() {

    private var bndList: ArrayList<EBanda> = ArrayList()
    private var onClickItemView: ((EBanda) -> Unit)? = null
    private var onClickDeleteItemView: ((EBanda) -> Unit)? = null

    fun addItems(items: ArrayList<EBanda>){
        this.bndList = items
        notifyDataSetChanged()
    }

    fun setOnClickItem(callback: (EBanda) -> Unit){
        onClickItemView = callback
    }

    fun setOnClickDeleteItem(callback: (EBanda) -> Unit){
        onClickDeleteItemView = callback
    }

    class BandViewHolder (var view: View): RecyclerView.ViewHolder(view){
        //private var id = view.findViewById<TextView>(R.id.rv_name)
        private var name = view.findViewById<TextView>(R.id.tv_name)
        private var genre = view.findViewById<TextView>(R.id.tv_genre)
        private var awards = view.findViewById<TextView>(R.id.tv_awards)
        private var year = view.findViewById<TextView>(R.id.tv_year)
        private var funds = view.findViewById<TextView>(R.id.tv_funds)
        var deleteButton = view.findViewById<Button>(R.id.deleteButton)

        fun bindView(bnd: EBanda){
            name.text = bnd.name
            genre.text = bnd.genre
            awards.text = bnd.awards
            year.text = bnd.year
            funds.text = bnd.funds
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BandViewHolder {
        return BandViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.band_recyclerview_list, parent, false))
    }

    override fun onBindViewHolder(holder: BandViewHolder, position: Int) {
        val bnd = bndList[position]
        holder.bindView(bnd)

        holder.itemView.setOnClickListener {
            Log.i("banda","clic en item list de rv")
            onClickItemView?.invoke(bnd)
        }

        holder.deleteButton.setOnClickListener { onClickDeleteItemView?.invoke(bnd) }

//        holder.itemView.setOnLongClickListener {
//
//        }

    }

    override fun getItemCount(): Int {
        return bndList.size
    }
}