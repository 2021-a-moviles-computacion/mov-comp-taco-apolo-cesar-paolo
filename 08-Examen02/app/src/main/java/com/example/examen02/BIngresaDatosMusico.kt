package com.example.examen02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BIngresaDatosMusico : AppCompatActivity() {
    //private lateinit var db: DSQLiteHelper

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

    var latitud = 0.0
    var longitud = 0.0
    var ciudadSeleccionada = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bingresa_datos_musico)

        initView()
        initRecyclerView()

        //SIPINNER CIUDADES
        val ciudades = arrayOf(
            "Amsterdam",
            "Berlin",
            "Bogotá",
            "Lisboa",
            "Londres",
            "Miami",
            "Madrid",
            "México DC",
            "New York",
            "Paris",
            "Quito",
            "Rio de Janeiro",
            "Tokyo",
            "Vienna",
            "Zürich")
        val latitudes = arrayOf(
            52.35573356081986,
            52.5192880232342,
            4.708507195958197,
            51.509162838374365,
            115.1500000,
            -100.6381900,
            40.41800636913152,
            19.432671298400965,
            40.73378562762449,
            48.85901815928653,
            -0.21922483476220822,
            -22.938658330236088,
            35.68614253218143,
            48.217399805696736,
            47.36894099516527
        )
        val longitudes = arrayOf(
            4.881766688070693,
            13.409614318972272,
            -74.05425716885155,
            -0.12767985390655445,
            38.7166700,
            35.6914300,
            -3.7077094446139935,
            -99.12723505493796,
            -73.99320893340935,
            2.296522110492033,
            -78.51152304364815,
            -43.228146943991,
            139.78434424093086,
            16.399427792363813,
            8.550536094895179)

        val spinnerUbicacion = findViewById<Spinner>(R.id.spUbicacion)

        val adaptadorUbicacion = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            ciudades
        )

        spinnerUbicacion.adapter = adaptadorUbicacion

        spinnerUbicacion.onItemSelectedListener = object :
            AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                ciudadSeleccionada = parent?.getItemAtPosition(position) as String
                latitud = latitudes[position]
                longitud = longitudes[position]
                Log.i("ciudades", "$position")
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }
        }

        //db = DSQLiteHelper(this)

        btnIngresarMusicInfo = findViewById(R.id.addMusicButton)

        btnIngresarMusicInfo.setOnClickListener {
            addMusician()
            this.finish()
            this.startActivity(
                Intent(
                    this,
                    BIngresaDatosMusico::class.java
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
            birthdayEt.setText(it.birthday)
            p_awardsEt.setText(it.p_awards)
            ocupacionEt.setText(it.ocup)
            activityEt.setText(it.activity_m)

            msc = it

        }

        adapater?.setOnClickDeleteItem {

            deleteMusician(it.id.toString())
            Log.i("musico", "${it.id}")

        }

        btnUpdateMusicBand = findViewById(R.id.updateMusicButton)

        btnUpdateMusicBand.setOnClickListener { updateMusician() }

        adapater?.setOnUbicationItem {
            openUbication(it)
            Log.i("musico", "objeto Ubicacion: ${it.id}")}

        val btnMapa = findViewById<Button>(R.id.btnUbicacion)

//        btnMapa.setOnClickListener {
//            val itemId = intent.putExtra("id")
//            val intent = Intent(this, FMapsActivity::class.java)
//            startActivity(intent)
//        }

    }

    private fun openUbication(it: EMusico) {
//        val itemId = intent.putExtra("id")
        val intent = Intent(this, FMapsActivity::class.java).apply {
            putExtra("objMusico", it)
        }
        startActivity(intent)
    }

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
            val nuevoMusico = hashMapOf<String, Any>(
                "nombre" to name,
                "fecha_nacimiento" to birthday,
                "premios_personales" to p_awards.toInt(),
                "ocupacion" to ocup,
                "actividad" to act,
                "latitud" to latitud,
                "longitud" to longitud
            )
            val db = Firebase.firestore
            val ref = db.collection("musico")
            ref
                .add(nuevoMusico)
                .addOnSuccessListener {
                    clearEditText()
                    Toast.makeText(
                        this,
                        "Musico ingresado",
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

    private fun updateMusician() {
        val name = nameEt.text.toString()
        val birthday = birthdayEt.text.toString()
        val p_awards = p_awardsEt.text.toString()
        val ocup = ocupacionEt.text.toString()
        val act = activityEt.text.toString()

        if (name == msc?.name && birthday == msc?.birthday && p_awards == msc?.p_awards &&
            ocup == msc?.ocup && act == msc?.activity_m && latitud == msc?.latitud
            && longitud == msc?.longitud){
            Toast.makeText(this, "Registro sin alterar", Toast.LENGTH_SHORT).show()
            return
        }
//
//        if (bnd == null) return
//
        val msc = EMusico(
            msc!!.id,
            name,
            birthday,
            p_awards,
            ocup,
            act,
            latitud,
            longitud
        )
        Log.i("bdd", "msc= $msc")
//
//        val status = dbSQL.updateBand(bnd)
//        if (status > -1){
//            clearEditText()
//            getBands()
//        }else{
//            Toast.makeText(this, "Actualización fallida", Toast.LENGTH_SHORT).show()
//        }
        Log.i("bdd", "id: ${msc.id}, nombre: ${msc.name}")
        val db = Firebase.firestore
        val ref = db.collection("musico")
        if (ref != null) {
            if (msc != null) {

                ref.document(msc.id.toString()).update(
                    "nombre", name,
                    "fecha_nacimiento", birthday,
                    "premios_personales", p_awards.toInt(),
                    "ocupacion", ocup,
                    "actividad", act,
                    "latitud",latitud,
                    "longitud",longitud,
                )
                Log.i("bdd", "id: ${ref.id}")

                Toast.makeText(this, "Musico actualizado exitosamente", Toast.LENGTH_SHORT)
                    .show()
                Log.i("bdd", "Banda actualizada")
                Log.i("bdd", "id: ${msc.id}, nombre: ${msc.name}")
                getMusicians()
            } else {
                //abrirActividad(MateriasActivity::class.java)
                Toast.makeText(this, "Error en actualización", Toast.LENGTH_SHORT)
                    .show()
                Log.i("bdd", "Musico Error")
                //this.finish()
            }
            getMusicians()
            clearEditText()
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



    private fun getMusicians() {

//        db = DSQLiteHelper(this)
//
//        val mscList = db.getAllMusicians()
//        Log.i("musico","arrayDim: ${mscList.size}")
//
//        adapater?.addItems(mscList)
        var musicoDocu: (MutableList<DocumentSnapshot>)
        val arrayMusico = ArrayList<EMusico>()
        val db = Firebase.firestore
        val ref = db.collection("musico")

        ref.get().addOnSuccessListener {
            musicoDocu = it.documents
            musicoDocu.forEach { iteracion ->

                val objMusico = iteracion.toObject(EMusico::class.java)
                //NO OLVIDAR PASAR EL PUTO ID DEL DOCUMENTO
                objMusico!!.id = iteracion.id
                objMusico.name = iteracion.get("nombre").toString()
                objMusico.birthday = iteracion.get("fecha_nacimiento").toString()
                objMusico.p_awards = iteracion.get("premios_personales").toString()
                objMusico.ocup = iteracion.get("ocupacion").toString()
                objMusico.activity_m = iteracion.get("actividad").toString()
                objMusico.latitud = iteracion.get("latitud") as Double?
                objMusico.longitud = iteracion.get("longitud") as Double?

                arrayMusico.add(objMusico)

            }
            adapater?.addItems(arrayMusico)
//            val idItem = arrayBand[adapater.]
        }

    }

    private fun clearEditText(){
        nameEt.setText("")
        birthdayEt.setText("")
        p_awardsEt.setText("")
        ocupacionEt.setText("")
        activityEt.setText("")
        //nameEt.requestFocus()
    }

    private fun deleteMusician(id: String){

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Estas seguro de que deseas eliminar el registro?")
        builder.setCancelable(true)
        builder.setPositiveButton("SI") {
                dialog, _ ->
            deleteMById(id)
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

    private fun deleteMById(id: String) {
        val db = Firebase.firestore
        val ref = db.collection("musico")
        ref.document(id.toString())
            .delete()
            .addOnCompleteListener {
                Toast.makeText(this, "Registro Eliminado!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error...", Toast.LENGTH_SHORT).show()
            }
    }
}