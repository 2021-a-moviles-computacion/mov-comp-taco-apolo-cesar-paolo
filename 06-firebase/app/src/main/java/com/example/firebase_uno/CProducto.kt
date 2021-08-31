package com.example.firebase_uno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cproducto)
        val btnCrear = findViewById<Button>(R.id.btn_crear_producto)
        btnCrear.setOnClickListener { crearProducto() }
    }

    fun crearProducto(){

        val editTextNombre = findViewById<EditText>(R.id.et_producto_nombre)
        val editTextPrecio = findViewById<EditText>(R.id.et_precioProd)
        val nuevoProducto = hashMapOf<String, Any>(
            "nombre" to editTextNombre.text.toString(),
            "precio" to editTextPrecio.text.toString()
        )
        val db = Firebase.firestore
        val referencia = db.collection("producto")
        referencia
            .add(nuevoProducto)
            .addOnSuccessListener {
                editTextNombre.text.clear()
                editTextPrecio.text.clear()
                Toast.makeText(
                    this,
                    "Producto ingresado exitosamente",
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