package com.example.firebase_uno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DRestaurante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drestaurante)

        val btnCrearRest = findViewById<Button>(R.id.btn_crear_restaurante)
        btnCrearRest.setOnClickListener { crearRestaurante() }

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