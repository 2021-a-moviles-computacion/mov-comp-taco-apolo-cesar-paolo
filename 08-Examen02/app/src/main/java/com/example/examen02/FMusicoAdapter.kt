package com.example.examen02

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class FMusicoAdapter: RecyclerView.Adapter<FMusicoAdapter.MusicianViewHolder>() {

    private var mscList: ArrayList<EMusico> = ArrayList()
    private var onClickItemView: ((EMusico) -> Unit)? = null
    private var onClickDeleteItemView: ((EMusico) -> Unit)? = null
    private var onClickUbicactionItemView: ((EMusico) -> Unit)? = null

    fun addItems(items: ArrayList<EMusico>) {
        this.mscList = items
        notifyDataSetChanged()
    }

    fun setOnClickItem(callback: (EMusico) -> Unit) {
        onClickItemView = callback
    }

    fun setOnClickDeleteItem(callback: (EMusico) -> Unit) {
        onClickDeleteItemView = callback
    }

    fun setOnUbicationItem(callback: (EMusico) -> Unit){
        onClickUbicactionItemView = callback
    }

    class MusicianViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        //private var id = view.findViewById<TextView>(R.id.rv_name)
        private var name = view.findViewById<TextView>(R.id.tv_nameMusician)
        private var birthday = view.findViewById<TextView>(R.id.tv_birthday)
        private var p_awards = view.findViewById<TextView>(R.id.tv_p_awards)
        private var ocup = view.findViewById<TextView>(R.id.tv_ocup)
        private var activity_M = view.findViewById<TextView>(R.id.tv_activity)
//        private var ubicacion = view.findViewById<TextView>(R.id.tv_ubicacion)
        var deleteButton = view.findViewById<Button>(R.id.deleteMusicianButton)
        var btnUbicacion = view.findViewById<Button>(R.id.btnUbicacion)

        fun bindMusicView(msc: EMusico) {
            name.text = msc.name
            birthday.text = msc.birthday
            p_awards.text = msc.p_awards
            ocup.text = msc.ocup
            activity_M.text = msc.activity_m
//            ubicacion.text = msc.latitud.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicianViewHolder {
        return MusicianViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.musician_recyclerview_list, parent, false)
        )
    }


    override fun onBindViewHolder(holder: MusicianViewHolder, position: Int) {
        val msc = mscList[position]
        holder.bindMusicView(msc)

        holder.itemView.setOnClickListener {
            Log.i("musico", "clic en item list de rv")
            onClickItemView?.invoke(msc)
        }

        holder.deleteButton.setOnClickListener { onClickDeleteItemView?.invoke(msc) }

        holder.btnUbicacion.setOnClickListener {
            onClickUbicactionItemView?.invoke(msc)
//            val intent = Intent(this, FMapsActivity::class.java)
//            startActivity(intent)

        }
//        holder.itemView.setOnLongClickListener {
//
//        }

    }

    override fun getItemCount(): Int {
        return mscList.size
    }

}