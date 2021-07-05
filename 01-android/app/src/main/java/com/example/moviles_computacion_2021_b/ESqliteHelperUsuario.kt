package com.example.moviles_computacion_2021_b

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ESqliteHelperUsuario(
    contexto: Context?
): SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {

        val scriptCrearTablaUsuario = """
            CREATE TABLE USUARIO(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion VARCHAR(50)              
        """.trimIndent()

        Log.i("bdd", "Creando Tabla Usuario")
        db?.execSQL(scriptCrearTablaUsuario)
    }

    fun crearUsuarioFormulario(
        nombre: String,
        descripcion: String
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresGuardar = ContentValues()
        valoresGuardar.put("nombre", nombre)
        valoresGuardar.put("descricpion", descripcion)
        val resEscritura: Long = conexionEscritura
            .insert(
                "USUARIO",
                null,
                valoresGuardar
            )
        conexionEscritura.close()
        return if (resEscritura.toInt() == -1) false else true
    }

    fun consultarUsuarioPorID(id: Int): EUsuarioBDD{
        val scripConsultarUsuarioID = "SELECT * FROM USUARIO WHERE ID = ${id}"
        val bddConsulta = readableDatabase
        val resConsultaLectura = bddConsulta.rawQuery(
            scripConsultarUsuarioID,
            null
        )
        val existeUsario = resConsultaLectura.moveToFirst()
        //val arregloUsuario = ArrayListOf<EUsuarioBDD>()
        val usuarioEcontrado = EUsuarioBDD(0, "", "")
        if (existeUsario){
            do {
                val id = resConsultaLectura.getInt(0)
                val nombre = resConsultaLectura.getString(1)
                val descripcion = resConsultaLectura.getString(2)
                if (id!=null){
                    usuarioEcontrado.id = id
                    usuarioEcontrado.nombre = nombre
                    usuarioEcontrado.descripcion = descripcion
                    //arregloUsuario.add(usuarioEncontrado)
                }
            }while (resConsultaLectura.moveToNext())
        }
        resConsultaLectura.close()
        bddConsulta.close()
        return usuarioEcontrado

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {



    }
}