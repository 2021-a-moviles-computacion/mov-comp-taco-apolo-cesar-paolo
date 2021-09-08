package com.example.firebase_uno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DRestaurante : AppCompatActivity() {

    var query: Query? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drestaurante)

        val btnCrearRest = findViewById<Button>(R.id.btn_crear_restaurante)
        btnCrearRest.setOnClickListener { crearRestaurante() }

        val btndatosPrueba = findViewById<Button>(R.id.btn_datos_prueba)
        btndatosPrueba.setOnClickListener { transaccion() }

        val btnConsultar = findViewById<Button>(R.id.btn_comsultar)
        btnConsultar.setOnClickListener { consultar() }

    }

    fun crearDatosPrueba(){
        val db = Firebase.firestore
        val cities = db.collection("cities")

        val data1 = hashMapOf(
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal")
        )
        cities.document("SF").set(data1)

        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal")
        )
        cities.document("LA").set(data2)

        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast")
        )
        cities.document("DC").set(data3)

        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu")
        )
        cities.document("TOK").set(data4)

        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei")
        )
        cities.document("BJ").set(data5)

    }

    fun transaccion(){
        val db = Firebase.firestore
        val refCities = db.collection("cities").document("BJ")
        db.runTransaction { transaction ->
            val actualDocu = transaction.get(refCities)
            val population = actualDocu.getDouble("population")
            if (population != null){
                val newPopu = population + 1
                transaction.update(refCities, "population", newPopu)
            }
        }
            .addOnSuccessListener { Log.i("consultas","Transaccion Completada") }
            .addOnFailureListener { Log.i("consultas", "ERROR") }
    }

    fun consultar(){

        /*
        OPERADORES
        < menor que
        <= menor o igual que
        == igual que
        > mayor que
        >= mayor que o igual que
        != no igual a
        array-contains
        array-contains-any
        in
        not-in
        */

        val db = Firebase.firestore
        // Create a reference to the cities collection
        val citiesRef = db.collection("cities")
            .orderBy("population")
            .limit(2)

        var tarea: Task<QuerySnapshot>? = null
        if (query == null){
            tarea = citiesRef.get()
        }else{
            tarea = query!!.get()
        }
        if (tarea != null){
            tarea
                .addOnSuccessListener { documentSnapshots ->
                    guardarQuery(documentSnapshots, citiesRef)
                    for (ciudad in documentSnapshots){
                        Log.i("consultas", "${ciudad.data}")
                    }
                }
                .addOnFailureListener {
                    Log.i("consultas", "ERROR ${it}")
                }
        }

        /*CONSULTAS
        //BUSQUEDA POR CIERTO CRITERIO -> campo en especial
        citiesRef
            .whereEqualTo("country", "China")
            .get()
            .addOnSuccessListener {
                Log.i("consultas", "${it.documents}")
                for (ciudad in it) {
                    Log.i("consultas", "${ciudad.data}")
                    Log.i("consultas", "${ciudad.id}")
                }
            }
        //BUSQUEDA POR DOCUMENTO
        citiesRef
            .document("BJ") // ID
            .get()
            .addOnSuccessListener {
                Log.i("consultas", "${it.data}")
            }
        //BUSCAR POR DOS O MAS ELEMENTOS COMO '==' 'array-contains'
        citiesRef
            .whereEqualTo("capital", false)
            .whereArrayContainsAny("regions", arrayListOf("socal", "norcal")).get()
            .addOnSuccessListener {
                for(ciudad in it){
                    Log.i("consultas", "array contains ==> ${ciudad.data}")
                }
            }

         //BUSCAR POR DO SO MAS CAMPOS '==' '>='
        citiesRef
            .whereEqualTo("capital",true)
            .whereGreaterThanOrEqualTo("population",1000000)
            .get()
            .addOnSuccessListener {
                for(ciudad in it){
                    Log.i("consultas", ">= && ==  ==> ${ciudad.data}")
                }
            }

        //BUSQUEDA POR ORDENAMIENTO
        citiesRef
            .whereEqualTo("capital", false)
            .whereLessThanOrEqualTo("population", 4000000)
            .orderBy("population", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener {
                for (ciudad in it){
                    Log.i("consultas", "-> ${ciudad.data}")
                }
            }
        //PAGINA DE UNA CONSULTA
        citiesRef
            .limit(1)
            .get()
            .addOnSuccessListener {

            }
            */



    }

    fun guardarQuery(documentSnapshot: QuerySnapshot, citiesRef: Query){

        if (documentSnapshot.size() > 0){
            val lastDocu = documentSnapshot.documents[documentSnapshot.size()-1]
            query = citiesRef
                .startAfter(lastDocu)
        }else{
            Log.i("consultas", "ERROR en fun guardarQuery")
        }

    }

    fun crearRestaurante(){
        val editTextNombre = findViewById<EditText>(R.id.et_nombreRest)
        val nuevorest = hashMapOf<String, Any>(
            "nombre" to editTextNombre.text.toString()
        )

        val db = Firebase.firestore
        val ref = db.collection("restaurante")
        ref
            .add(nuevorest)
            .addOnSuccessListener {
                editTextNombre.text.clear()
                Toast.makeText(
                    this,
                    "Restaurante Ingresado",
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