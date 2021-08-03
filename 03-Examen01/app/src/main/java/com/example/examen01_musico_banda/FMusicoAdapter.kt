package com.example.examen01_musico_banda

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FMusicoAdapter: RecyclerView.Adapter<FMusicoAdapter.MusicianViewHolder>() {

    private var mscList: ArrayList<EMusico> = ArrayList()
    private var onClickItemView: ((EMusico) -> Unit)? = null
    private var onClickDeleteItemView: ((EMusico) -> Unit)? = null

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

    class MusicianViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        //private var id = view.findViewById<TextView>(R.id.rv_name)
        private var name = view.findViewById<TextView>(R.id.tv_nameMusician)
        private var birthday = view.findViewById<TextView>(R.id.tv_birthday)
        private var p_awards = view.findViewById<TextView>(R.id.tv_p_awards)
        private var ocup = view.findViewById<TextView>(R.id.tv_ocup)
        private var activity = view.findViewById<TextView>(R.id.tv_funds)
        var deleteButton = view.findViewById<Button>(R.id.deleteMusicianButton)

        fun bindMusicView(msc: EMusico) {
            name.text = msc.name
            birthday.text = msc.birthday
            p_awards.text = msc.p_awards
            ocup.text = msc.ocup
            activity.text = msc.activity
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicianViewHolder {
        return MusicianViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.band_recyclerview_list, parent, false)
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

//        holder.itemView.setOnLongClickListener {
//
//        }

    }

    override fun getItemCount(): Int {
        return mscList.size
    }

}