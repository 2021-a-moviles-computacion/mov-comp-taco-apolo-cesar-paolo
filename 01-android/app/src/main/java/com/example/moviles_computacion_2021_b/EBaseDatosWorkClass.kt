package com.example.moviles_computacion_2021_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class EBaseDatosWorkClass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ebase_datos_work_class)

        EBaseDatos.tablaUsuario = ESqliteHelperUsuario(this)
        Log.i("bdd", EBaseDatos.tablaUsuario!!.databaseName.toString())

        val txtNombre = findViewById<EditText>(R.id.txt_nombre)
        val txtDescrip = findViewById<EditText>(R.id.txt_descripcion)
        val txtConsul = findViewById<EditText>(R.id.txt_consulta)

        val btnCrearUsuario = findViewById<Button>(
            R.id.btn_crearUsuario
        )

        val btnConsultarUsuario = findViewById<Button>(
            R.id.btn_consulUsuario
        )

        val btnActualizarUsuario = findViewById<Button>(
            R.id.btn_actualizarUsuario
        )

        val btnEliminarUsuario = findViewById<Button>(
            R.id.btn_eliminarUsuario
        )

        //val btnC = findViewById<Button>(R.id.btn_crear)

        btnCrearUsuario.setOnClickListener{
            if (EBaseDatos.tablaUsuario != null) {
                EBaseDatos.tablaUsuario!!.crearUsuarioFormulario(
                    txtNombre.text.toString(),
                    txtDescrip.text.toString()
                )
                Log.i("bdd",
                    "usuario ${txtNombre.text} creado")
            }
        }

       btnConsultarUsuario.setOnClickListener{
            if (EBaseDatos.tablaUsuario != null){
                EBaseDatos.tablaUsuario!!
                    .consultarUsuarioPorID(txtConsul.text.toString().toInt())
                Log.i("bdd",
                    "id: ${txtConsul.text} Consulta exitosa!!")
            }
        }

        btnActualizarUsuario.setOnClickListener{
            if (EBaseDatos.tablaUsuario != null){
                EBaseDatos.tablaUsuario!!.actualizarUsuarioFormulario(
                    txtNombre.text.toString(),
                    txtDescrip.text.toString(),
                    txtConsul.text.toString().toInt()
                )
                Log.i("bdd",
                    "usuario ${txtNombre.text} actualizado")
            }
        }

        btnEliminarUsuario.setOnClickListener{
            if (EBaseDatos.tablaUsuario != null){
                EBaseDatos.tablaUsuario!!
                    .eliminarUsuarioFormulario(
                        txtConsul.text.toString().toInt()
                    )
                Log.i("bdd",
                    "usuario con id: ${txtConsul.text} eliminado")
            }
        }

    }

}

