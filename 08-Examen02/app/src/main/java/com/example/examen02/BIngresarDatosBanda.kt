package com.example.examen02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*

class BIngresarDatosBanda : AppCompatActivity() {

    //private lateinit var dbSQL: DSQLiteHelper

    private val db1 = Firebase.firestore.collection("banda")


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bingresar_datos_banda)

        initView()
        initRecyclerView()

        val bandId = intent.getParcelableExtra<EBanda>("id")
        Log.i("bdd", "id: $bandId")


        //dbSQL = DSQLiteHelper(this)

        btnIngresarInfo = findViewById(R.id.addButton)

        btnIngresarInfo.setOnClickListener {
            addBand()
            this.finish()
            this.startActivity(
                Intent(
                    this,
                    BIngresarDatosBanda::class.java
                )
            )
        }

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

//            val item = bndList[holder.adapterPosition]
//            adapater.onBindViewHolder()
            deleteBand(it.id.toString())
            Log.i("banda", "${it.id}")

        }

        btnUpdateBand = findViewById(R.id.updateButton)

        btnUpdateBand.setOnClickListener {
//            val oldBand = getOldBand()
//            val newBandMap = getNewBandMap()
//            updateBandFinal(oldBand, newBandMap)
            val name = nameEt.text.toString()
            val genre = genreEt.text.toString()
            val awards = awardsEt.text.toString()
            val year = yearEt.text.toString()
            val funds = fundsEt.text.toString()

            if (name == bnd?.name && genre == bnd?.genre && awards == bnd?.awards &&
                year == bnd?.year && funds == bnd?.funds
            ) {
                Toast.makeText(this, "Registro sin alterar", Toast.LENGTH_SHORT).show()
            }else if (name.isEmpty() || genre.isEmpty() || awards.isEmpty() || year.isEmpty() ||
                funds.isEmpty()
            ) {
                Toast.makeText(
                    this,
                    "Seleccione una banda...",
                    Toast.LENGTH_LONG
                ).show()
            }
//
//        if (bnd == null) return
//
            val bnd = EBanda(
                bnd!!.id,
                name,
                genre,
                awards,
                year,
                funds
            )
            Log.i("bdd", "bnd= $bnd")
//
//        val status = dbSQL.updateBand(bnd)
//        if (status > -1){
//            clearEditText()
//            getBands()
//        }else{
//            Toast.makeText(this, "Actualización fallida", Toast.LENGTH_SHORT).show()
//        }
            Log.i("bdd", "id: ${bnd.id}, nombre: ${bnd.name}")
            val db = Firebase.firestore
            val ref = db.collection("banda")
            if (ref != null) {
                if (bnd != null) {

                    ref.document(bnd.id.toString()).update(
                        "fecha_creacion", year,
                        "genero", genre,
                        "ingresos", funds,
                        "nombre", name,
                        "premios", awards,
                    )
                    Log.i("bdd", "id: ${ref.id}")

                    Toast.makeText(this, "Banda actualizada exitosamente", Toast.LENGTH_SHORT)
                        .show()
                    Log.i("bdd", "Banda actualizada")
                    Log.i("bdd", "id: ${bnd.id}, nombre: ${bnd.name}")
                    getBands()
                } else {
                    //abrirActividad(MateriasActivity::class.java)
                    Toast.makeText(this, "Error en actualización", Toast.LENGTH_SHORT)
                        .show()
                    Log.i("bdd", "Banda Error")
                    //this.finish()
                }
                getBands()
                clearEditText()
            }
        }

    }

    private fun getOldBand(): EBanda {
        val name = nameEt.text.toString()
        val genre = genreEt.text.toString()
        val awards = awardsEt.text.toString()
        val year = yearEt.text.toString()
        val funds = fundsEt.text.toString()
        return EBanda(name, genre, awards, year, funds)
    }

    private fun getNewBandMap(): Map<String, Any> {
        val name = nameEt.text.toString()
        val genre = genreEt.text.toString()
        val awards = awardsEt.text.toString()
        val year = yearEt.text.toString()
        val funds = fundsEt.text.toString()
        val map = mutableMapOf<String, Any>()
        if(year.isNotEmpty()) {
            map["fecha_creacion"] = year
        }
        if(genre.isNotEmpty()) {
            map["genero"] = genre
        }
        if(awards.isNotEmpty()) {
            map["premios"] = awards.toInt()
        }
        if(name.isNotEmpty()) {
            map["nombre"] = name
        }
        if(funds.isNotEmpty()) {
            map["ingresos"] = funds
        }
        return map
    }

    private fun updateBandFinal(band: EBanda,
                                newPersonMap: Map<String,
                                     Any>)= CoroutineScope(Dispatchers.IO).launch {
        val bandQuery = db1
            .whereEqualTo("nombre", band.name)
            .whereEqualTo("genero", band.genre)
            .whereEqualTo("fecha_creacion", band.year)
            .whereEqualTo("ingresos", band.funds)
            .whereEqualTo("premios", band.awards)
            .get()

//        if(bandQuery.documents.isNotEmpty()) {
//            for(document in bandQuery) {
//                try {
//                    //personCollectionRef.document(document.id).update("age", newAge).await()
//                    db1.document(document.id).set(
//                        newPersonMap,
//                        SetOptions.merge()
//                    ).await()
//                } catch (e: Exception) {
//                    withContext(Dispatchers.Main) {
//                        Toast.makeText(this@BIngresarDatosBanda, e.message, Toast.LENGTH_LONG).show()
//                    }
//                }
//            }
//        } else {
//            withContext(Dispatchers.Main) {
//                Toast.makeText(this@BIngresarDatosBanda, "No bands matched the query.", Toast.LENGTH_LONG).show()
//            }
//        }
    }

    private fun updateBand() {
        val name = nameEt.text.toString()
        val genre = genreEt.text.toString()
        val awards = awardsEt.text.toString()
        val year = yearEt.text.toString()
        val funds = fundsEt.text.toString()

        if (name == bnd?.name && genre == bnd?.genre && awards == bnd?.awards &&
            year == bnd?.year && funds == bnd?.funds
        ) {
            Toast.makeText(this, "Registro sin alterar", Toast.LENGTH_SHORT).show()
            return
        }else if (name.isEmpty() || genre.isEmpty() || awards.isEmpty() || year.isEmpty() ||
                    funds.isEmpty()
                    ) {
                Toast.makeText(
                    this,
                    "Seleccione una banda...",
                    Toast.LENGTH_LONG
                ).show()
        }
//
//        if (bnd == null) return
//
        val bnd = EBanda(
            bnd!!.id,
            name,
            genre,
            awards,
            year,
            funds
        )
//
//        val status = dbSQL.updateBand(bnd)
//        if (status > -1){
//            clearEditText()
//            getBands()
//        }else{
//            Toast.makeText(this, "Actualización fallida", Toast.LENGTH_SHORT).show()
//        }
        Log.i("bdd", "id: ${bnd.id}, nombre: ${bnd.name}")
        val db = Firebase.firestore
        val ref = db.collection("banda")
        if (ref != null) {
            if (bnd != null) {

                //ref.document(bandId.toString()).update(
//                    "fecha_creacion", year,
//                    "genero", genre,
//                    "ingresos", funds,
//                    "nombre", name,
//                    "premios", awards,
//                )
                Toast.makeText(this, "Banda actualizada exitosamente", Toast.LENGTH_SHORT)
                    .show()
                Log.i("bdd", "Banda actualizada")
                Log.i("bdd", "id: ${bnd.id}, nombre: ${bnd.name}")
                getBands()
            } else {
                //abrirActividad(MateriasActivity::class.java)
                Toast.makeText(this, "Error en actualización", Toast.LENGTH_SHORT)
                    .show()
                Log.i("bdd", "Banda Error")
                //this.finish()
            }
            getBands()
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

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapater = FBandaAdapter()
        recyclerView.adapter = adapater
//        val flag = adapater!!.notifyDataSetChanged()
//        while (flag == Unit){
//            getBands()
//        }
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

    private fun addBand() {

        val name = nameEt.text.toString()
        val genre = genreEt.text.toString()
        val awards = awardsEt.text.toString()
        val year = yearEt.text.toString()
        val funds = fundsEt.text.toString()
//
        if (name.isEmpty() || genre.isEmpty() || awards.isEmpty() || year.isEmpty() ||
            funds.isEmpty()
        ) {
            Toast.makeText(
                this,
                "Ingrese toda la información...",
                Toast.LENGTH_LONG
            ).show()
        } else {
//            val bnd = EBanda(
//                name = name,
//                genre = genre,
//                awards = awards,
//                year = year,
//                funds = funds
//            )
//            val status = dbSQL.insertBand(bnd)
//
//            if (status > -1) {
//                Toast.makeText(
//                    this,
//                    "BandaIngresada...",
//                    Toast.LENGTH_LONG
//                ).show()
//                clearEditText()
//                getBands()
//            } else
//                Toast.makeText(
//                    this,
//                    "Registro no guardado...",
//                    Toast.LENGTH_LONG,
//                ).show()
//            Log.i("banda","ID-ARRAY: ${bnd.id}")

            val nuevaBanda = hashMapOf<String, Any>(
                "nombre" to name,
                "genero" to genre,
                "premios" to awards.toInt(),
                "fecha_creacion" to year,
                "ingresos" to funds.toDouble()
            )
            val db = Firebase.firestore
            val ref = db.collection("banda")
            ref
                .add(nuevaBanda)
                .addOnSuccessListener {
                    clearEditText()
                    Toast.makeText(
                        this,
                        "Banda ingresada",
                        Toast.LENGTH_SHORT
                    ).show()

                }
                .addOnFailureListener {
                    Toast.makeText(
                        this,
                        "Error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }


    private fun getBands() {

//        dbSQL = DSQLiteHelper(this)
//
//        val bndList = dbSQL.getAllBands()
//        Log.i("banda","arrayDim: ${bndList.size}")
//
//        adapater?.addItems(bndList)
        var bandDocu: (MutableList<DocumentSnapshot>)
        val arrayBand = ArrayList<EBanda>()
        val db = Firebase.firestore
        val ref = db.collection("banda")

        ref.get().addOnSuccessListener {
            bandDocu = it.documents
            bandDocu.forEach { iteracion ->

                val objBand = iteracion.toObject(EBanda::class.java)
                //NO OLVIDAR PASAR EL PUTO ID DEL DOCUMENTO
                objBand!!.id = iteracion.id
                objBand!!.name = iteracion.get("nombre").toString()
                objBand.genre = iteracion.get("genero").toString()
                objBand.awards = iteracion.get("premios").toString()
                objBand.year = iteracion.get("fecha_creacion").toString()
                objBand.funds = iteracion.get("ingresos").toString()

                arrayBand.add(objBand)

            }
            adapater?.addItems(arrayBand)
//            val idItem = arrayBand[adapater.]
        }


    }

    private fun clearEditText() {
        nameEt.setText("")
        genreEt.setText("")
        awardsEt.setText("")
        yearEt.setText("")
        fundsEt.setText("")
        //nameEt.requestFocus()
    }

    private fun deleteBand(id: String) {

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Estas seguro de que deseas eliminar el registro?")
        builder.setCancelable(true)
        builder.setPositiveButton("SI") { dialog, _ ->
            deleteById(id)
            getBands()
            dialog.dismiss()
        }
        builder.setNegativeButton("NO") { dialog, _ ->
            dialog.dismiss()
        }

        val alert = builder.create()
        alert.show()

    }

    private fun deleteById(id: String) {
        val db = Firebase.firestore
        val ref = db.collection("banda")
        ref.document(id.toString())
            .delete()
            .addOnCompleteListener {
                Toast.makeText(this, "Registro Eliminado!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error...", Toast.LENGTH_SHORT).show()
            }
    }

//    private fun deleteBandByID() {
//        val band = intent.getParcelableExtra<EBanda>("id")
//        val db = Firebase.firestore
//        val ref = db.collection("banda")
//        if (ref != null) {
//                ref.document(band?.id.toString()).delete()
//                Toast.makeText(this, "Banda Eliminada Exitosamente", Toast.LENGTH_SHORT)
//                    .show()
//                Log.i("bdd", "Banda elminada")
//
//        } else {
//            //abrirActividad(MateriasActivity::class.java)
//            Toast.makeText(this, "Error en Elminar", Toast.LENGTH_SHORT)
//                .show()
//            Log.i("bdd", "Banda Error")
//            //this.finish()
//        }
//    }
}
