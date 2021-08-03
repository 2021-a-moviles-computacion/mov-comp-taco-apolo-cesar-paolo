package com.example.examen01_musico_banda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BIngresaDatosMusico : AppCompatActivity() {
    private lateinit var db: DSQLiteHelper

    private lateinit var nameEt: EditText
    private lateinit var birthdayEt: EditText
    private lateinit var p_awardsEt: EditText
    private lateinit var ocupacionEt: EditText
    private lateinit var activityEt: EditText

    private lateinit var recyclerView: RecyclerView
    private var adapater: FMusicoAdapter? = null
    private var msc: EMusico? = null

    private lateinit var btnIngresarMusicInfo: Button
    private lateinit var btnUpdateMusicBand: Button

//    private var name: String? = ""
//    private var genre: String? = ""
//    private var awards: String? = ""
//    private var year: String? = ""
//    private var funds: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bingresa_datos_musico)

        initView()
        initRecyclerView()

        db = DSQLiteHelper(this)

        btnIngresarMusicInfo = findViewById(R.id.addMusicButton)

        btnIngresarMusicInfo.setOnClickListener { addMusician() }

        adapater?.setOnClickItem {
            Toast.makeText(
                this,
                it.name,
                Toast.LENGTH_SHORT
            ).show()

            nameEt.setText(it.name)
            birthdayEt.setText(it.birthday)
            p_awardsEt.setText(it.p_awards)
            ocupacionEt.setText(it.ocup)
            activityEt.setText(it.activity_m)

            msc = it

        }

        adapater?.setOnClickDeleteItem {

            deleteMusician(it.id)

        }

        btnUpdateMusicBand = findViewById(R.id.updateMusicButton)

        btnUpdateMusicBand.setOnClickListener { updateMusician() }

    }

    private fun updateMusician() {
        val name = nameEt.text.toString()
        val birthday = birthdayEt.text.toString()
        val p_awards = p_awardsEt.text.toString()
        val ocup = ocupacionEt.text.toString()
        val act = activityEt.text.toString()

        if (name == msc?.name && birthday == msc?.birthday && p_awards == msc?.p_awards &&
            ocup == msc?.ocup && act == msc?.activity_m){
            Toast.makeText(this, "Registro sin alterar", Toast.LENGTH_SHORT).show()
            return
        }

        if (msc == null) return

        val msc = EMusico(
            msc!!.id,
            name,
            birthday,
            p_awards,
            ocup,
            act
        )

        val status = db.updateMusician(msc)
        if (status > -1){
            clearEditText()
            getMusicians()
        }else{
            Toast.makeText(this, "Actualización fallida", Toast.LENGTH_SHORT).show()
        }

    }

//    override fun onResume() {
//        super.onResume()
//        initView()
//        initRecyclerView()
//    }

    private fun initView() {

        nameEt = findViewById(R.id.MusicoName)
        birthdayEt = findViewById(R.id.musico_cumple)
        p_awardsEt = findViewById(R.id.PersonalAwards)
        ocupacionEt = findViewById(R.id.ocupacion)
        activityEt = findViewById(R.id.actividad)
        recyclerView = findViewById(R.id.rv_MusicoInfo)

    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapater = FMusicoAdapter()
        recyclerView.adapter = adapater
        getMusicians()

    }

//    private fun inputData(){
//
//        name = nameEt.text.toString().trim()
//        genre = genreEt.text.toString().trim()
//        awards = awardsEt.text.toString().trim()
//        year = yearEt.text.toString().trim()
//        funds = fundsEt.text.toString().trim()
//
//        val id = db.insertBand()
//
//        Toast.makeText(
//            this,
//            "Información Ingresada con Éxito",
//            Toast.LENGTH_LONG
//        ).show()
//
//    }

    private fun addMusician(){

        val name = nameEt.text.toString()
        val birthday = birthdayEt.text.toString()
        val p_awards = p_awardsEt.text.toString()
        val ocup = ocupacionEt.text.toString()
        val act = activityEt.text.toString()

        if (name.isEmpty() || birthday.isEmpty() || p_awards.isEmpty() || ocup.isEmpty() ||
            act.isEmpty()
        ) {
            Toast.makeText(
                this,
                "Ingrese toda la información...",
                Toast.LENGTH_LONG
            ).show()
        } else {
            val msc = EMusico(
                name = name,
                birthday = birthday,
                p_awards = p_awards,
                ocup = ocup,
                activity_m = act
            )
            val status = db.insertMusico(msc)

            if (status > -1) {
                Toast.makeText(
                    this,
                    "Musico Ingresado...",
                    Toast.LENGTH_LONG
                ).show()
                clearEditText()
                getMusicians()
            } else
                Toast.makeText(
                    this,
                    "Registro no guardado...",
                    Toast.LENGTH_LONG,
                ).show()
            Log.i("musician","ID-ARRAY: ${msc.id}")
        }

    }

    private fun getMusicians() {

        db = DSQLiteHelper(this)

        val mscList = db.getAllMusicians()
        Log.i("musico","arrayDim: ${mscList.size}")

        adapater?.addItems(mscList)

    }

    private fun clearEditText(){
        nameEt.setText("")
        birthdayEt.setText("")
        p_awardsEt.setText("")
        ocupacionEt.setText("")
        activityEt.setText("")
        //nameEt.requestFocus()
    }

    private fun deleteMusician(id: Int){

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Estas seguro de que deseas eliminar el registro?")
        builder.setCancelable(true)
        builder.setPositiveButton("SI") {
                dialog, _ ->
            db.deleteMusicianByID(id)
            getMusicians()
            dialog.dismiss()
        }
        builder.setNegativeButton("NO") {
                dialog, _ ->
            dialog.dismiss()
        }

        val alert = builder.create()
        alert.show()

    }
}