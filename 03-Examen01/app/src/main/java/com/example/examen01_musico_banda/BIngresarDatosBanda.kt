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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BIngresarDatosBanda : AppCompatActivity() {

    private lateinit var dbSQL: DSQLiteHelper

    private lateinit var nameEt: EditText
    private lateinit var genreEt: EditText
    private lateinit var awardsEt: EditText
    private lateinit var yearEt: EditText
    private lateinit var fundsEt: EditText

    private lateinit var recyclerView: RecyclerView
    private var adapater: FBandaAdapter? = null
    private var bnd: EBanda? = null

    private lateinit var btnIngresarInfo: Button
    private lateinit var btnUpdateBand: Button

//    private var name: String? = ""
//    private var genre: String? = ""
//    private var awards: String? = ""
//    private var year: String? = ""
//    private var funds: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bingresar_datos_banda)

        initView()
        initRecyclerView()

        dbSQL = DSQLiteHelper(this)

        btnIngresarInfo = findViewById(R.id.addButton)

        btnIngresarInfo.setOnClickListener { addBand() }

        adapater?.setOnClickItem {
            Toast.makeText(
                this,
                it.name,
                Toast.LENGTH_SHORT
            ).show()

            nameEt.setText(it.name)
            genreEt.setText(it.genre)
            awardsEt.setText(it.awards)
            yearEt.setText(it.year)
            fundsEt.setText(it.funds)

            bnd = it

        }

        adapater?.setOnClickDeleteItem {

            deleteBand(it.id)

        }

        btnUpdateBand = findViewById(R.id.updateButton)

        btnUpdateBand.setOnClickListener { updateBand() }

    }

    private fun updateBand() {
        val name = nameEt.text.toString()
        val genre = genreEt.text.toString()
        val awards = awardsEt.text.toString()
        val year = yearEt.text.toString()
        val funds = fundsEt.text.toString()

        if (name == bnd?.name && genre == bnd?.genre && awards == bnd?.awards &&
                year == bnd?.year && funds == bnd?.funds){
            Toast.makeText(this, "Registro sin alterar", Toast.LENGTH_SHORT).show()
            return
        }

        if (bnd == null) return

        val bnd = EBanda(
            bnd!!.id,
            name,
            genre,
            awards,
            year,
            funds
        )

        val status = dbSQL.updateBand(bnd)
        if (status > -1){
            clearEditText()
            getBands()
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

        nameEt = findViewById(R.id.bandName)
        genreEt = findViewById(R.id.bandGenre)
        awardsEt = findViewById(R.id.bandAwards)
        yearEt = findViewById(R.id.bandCreationDate)
        fundsEt = findViewById(R.id.bandFundsPerConcert)
        recyclerView = findViewById(R.id.rv_BandInfo)

    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapater = FBandaAdapter()
        recyclerView.adapter = adapater
        getBands()

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

    private fun addBand(){

        val name = nameEt.text.toString()
        val genre = genreEt.text.toString()
        val awards = awardsEt.text.toString()
        val year = yearEt.text.toString()
        val funds = fundsEt.text.toString()

        if (name.isEmpty() || genre.isEmpty() || awards.isEmpty() || year.isEmpty() ||
            funds.isEmpty()
        ) {
            Toast.makeText(
                this,
                "Ingrese toda la información...",
                Toast.LENGTH_LONG
            ).show()
        } else {
            val bnd = EBanda(
                name = name,
                genre = genre,
                awards = awards,
                year = year,
                funds = funds
            )
            val status = dbSQL.insertBand(bnd)

            if (status > -1) {
                Toast.makeText(
                    this,
                    "BandaIngresada...",
                    Toast.LENGTH_LONG
                ).show()
                clearEditText()
                getBands()
            } else
                Toast.makeText(
                    this,
                    "Registro no guardado...",
                    Toast.LENGTH_LONG,
                ).show()
            Log.i("banda","ID-ARRAY: ${bnd.id}")

//            val nuevaBanda = hashMapOf<String, Any>(
//                "nombre" to name,
//                "genero" to genre,
//                "premios" to awards.toInt(),
//                "fecha_crecion" to year,
//                "ingresos" to funds.toDouble()
//            )
//            val db = Firebase.firestore
//            val ref = db.collection("producto")
//            ref
//                .add(nuevaBanda)
//                .addOnSuccessListener {
//                    clearEditText()
//                    Toast.makeText(
//                        this,
//                        "Banda ingresada",
//                        Toast.LENGTH_SHORT
//                    ).show()
//
//                }
//                .addOnFailureListener {
//                    Toast.makeText(
//                        this,
//                        "Error",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
        }

    }

    private fun getBands() {

        dbSQL = DSQLiteHelper(this)

        val bndList = dbSQL.getAllBands()
        Log.i("banda","arrayDim: ${bndList.size}")

        adapater?.addItems(bndList)

    }

    private fun clearEditText(){
        nameEt.setText("")
        genreEt.setText("")
        awardsEt.setText("")
        yearEt.setText("")
        fundsEt.setText("")
        //nameEt.requestFocus()
    }

    private fun deleteBand(id: Int){

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Estas seguro de que deseas eliminar el registro?")
        builder.setCancelable(true)
        builder.setPositiveButton("SI") {
            dialog, _ ->
            dbSQL.deleteBandByID(id)
            getBands()
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