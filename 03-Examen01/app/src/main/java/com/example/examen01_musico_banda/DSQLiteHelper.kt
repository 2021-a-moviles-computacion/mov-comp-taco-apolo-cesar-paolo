package com.example.examen01_musico_banda

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.Exception
import kotlin.collections.ArrayList

class DSQLiteHelper(context: Context?
): SQLiteOpenHelper(
    context,
    CConstantesDB.DB_NAME,
    null,
    CConstantesDB.DB_VERSION
) {
    private val TABLE_NAME = CConstantesDB.TABLE_NAME_BAND

    companion object{
        private const val ID = CConstantesDB.B_ID
        private const val NAME = CConstantesDB.B_NAME
        private const val GENRE = CConstantesDB.B_GENRE
        private const val AWARDS = CConstantesDB.B_AWARDS
        private const val YEAR = CConstantesDB.B_YEARCREATION
        private const val FUNDS = CConstantesDB.B_FUNDSPERCONCERT
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CConstantesDB.CREATE_TABLE_BAND)
        Log.i("banda", "Creando Tabla BANDA")
    }

    fun insertBand(bnd: EBanda): Long{

        val db =  this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, bnd.id)
        contentValues.put(NAME, bnd.name)
        contentValues.put(GENRE, bnd.genre)
        contentValues.put(AWARDS, bnd.awards)
        contentValues.put(YEAR, bnd.year)
        contentValues.put(FUNDS, bnd.funds)

        val success = db.insert(TABLE_NAME, null, contentValues)
        db.close()

        return success
    }

    fun getAllBands(): ArrayList<EBanda>{

        val bndList: ArrayList<EBanda> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var genre: String
        var awards: String
        var year: String
        var funds: String

        if (cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex("ID"))
                name = cursor.getString(cursor.getColumnIndex("NAME"))
                genre = cursor.getString(cursor.getColumnIndex("GENRE"))
                awards = cursor.getString(cursor.getColumnIndex("AWARDS"))
                year = cursor.getString(cursor.getColumnIndex("YEAR_CREATION"))
                funds = cursor.getString(cursor.getColumnIndex("FUNDS_PER_CONCERT"))

                val std = EBanda(
                    id, name, genre, awards, year, funds
                )
                bndList.add(std)
            }while (cursor.moveToNext())
        }

        return bndList

    }

    fun updateBand(bnd: EBanda): Int{

        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, bnd.id)
        contentValues.put(NAME, bnd.name)
        contentValues.put(GENRE, bnd.genre)
        contentValues.put(AWARDS, bnd.awards)
        contentValues.put(YEAR, bnd.year)
        contentValues.put(FUNDS, bnd.funds)

        val success = db.update(
            TABLE_NAME,
            contentValues,
            "ID=" + bnd.id,
            null
        )
        db.close()

        return success

    }

    fun deleteBandByID(id: Int): Int{

        val db = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(ID, id)

        val success = db.delete(TABLE_NAME, "ID=$id", null)
        db.close()
        return success

    }

//    fun consultarUsuarioPorID(id: Int): EBanda{
//        val scripConsultarUsuarioID = "SELECT * FROM BANDA_INFO_TABLE WHERE ID = ${id}"
//        val bddConsulta = readableDatabase
//        val resConsultaLectura = bddConsulta.rawQuery(
//            scripConsultarUsuarioID,
//            null
//        )
//        val existeUsario = resConsultaLectura.moveToFirst()
//        //val arregloUsuario = ArrayListOf<EUsuarioBDD>()
//        val bandEcontrada = EBanda(0, "", "","","","")
//        if (existeUsario){
//            do {
//                val id = resConsultaLectura.getInt(0)
//                val nombre = resConsultaLectura.getString(1)
//                val genero = resConsultaLectura.getString(2)
//                if (id!=null){
//                    bandEcontrada.id = id
//                    bandEcontrada.name = nombre
//                    bandEcontrada.genre = genero
//                    //arregloUsuario.add(usuarioEncontrado)
//                }
//            }while (resConsultaLectura.moveToNext())
//        }
//        resConsultaLectura.close()
//        bddConsulta.close()
//        return bandEcontrada
//
//    }
//
//    fun eliminarUsuarioFormulario(id: Int): Boolean{
//        val conexionEscritura = writableDatabase
//        val resultadoEliminacion = conexionEscritura
//            .delete(
//                "BANDA_INFO_TABLE",
//                "id=?",
//                arrayOf(
//                    id.toString()
//                )
//            )
//        conexionEscritura.close()
//        return if (resultadoEliminacion.toInt() == -1) false else true
//    }
//
//    fun actualizarUsuarioFormulario(
//        nombre: String,
//        descripcion: String,
//        idActualizar: Int
//    ):Boolean{
//        val conexionEscritura = writableDatabase
//        val valoresActualizar = ContentValues()
//        valoresActualizar.put("nombre", nombre)
//        valoresActualizar.put("descripcion", descripcion)
//        val resActualizacion = conexionEscritura
//            .update(
//                "USUARIO",
//                valoresActualizar,
//                "id=?",
//                arrayOf(
//                    idActualizar.toString()
//                )
//            )
//        conexionEscritura.close()
//        return if (resActualizacion == -1) false else true
//    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS" + CConstantesDB.TABLE_NAME_BAND)
        db!!.execSQL("DROP TABLE IF EXISTS" + CConstantesDB.TABLE_NAME_MUSICIAN)
        onCreate(db)
    }

//    fun insertBandInfo (
//        name: String,
//        genre: String,
//        awards: String,
//        year_creation: String,
//        fundsPerConcert: String
//        ):Long{
//
//        val db = this.writableDatabase
//        val values = ContentValues()
//
//        values.put(CConstantesDB.B_NAME, name)
//        values.put(CConstantesDB.B_GENRE, genre)
//        values.put(CConstantesDB.B_AWARDS, awards)
//        values.put(CConstantesDB.B_YEARCREATION, year_creation)
//        values.put(CConstantesDB.B_FUNDSPERCONCERT, fundsPerConcert)
//
//        val id = db.insert(CConstantesDB.TABLE_NAME_BAND, null, values)
//        db.close()
//        Log.i("banda","insercion dato ${id}")
//        return id
//    }

//    fun getBandData(orderBy: String):ArrayList<EBanda>{
//
//        val bandaList = ArrayList<EBanda>()
//        val selectQuery = "SELECT * FROM ${CConstantesDB.TABLE_NAME_BAND} ORDER BY $orderBy"
//        val db = this.writableDatabase
//        val cursor = db.rawQuery(selectQuery, null)
//
//        if(cursor.moveToNext()){
//            do{
//                val banda = EBanda(
//                    cursor.getInt(cursor.getColumnIndex(CConstantesDB.B_ID)),
//                    ""+cursor.getString(cursor.getColumnIndex(CConstantesDB.B_NAME)),
//                    ""+cursor.getString(cursor.getColumnIndex(CConstantesDB.B_GENRE)),
//                    ""+cursor.getString(cursor.getColumnIndex(CConstantesDB.B_AWARDS)),
//                    ""+cursor.getString(cursor.getColumnIndex(CConstantesDB.B_YEARCREATION)),
//                    ""+cursor.getString(cursor.getColumnIndex(CConstantesDB.B_FUNDSPERCONCERT))
//                )
//                bandaList.add(banda)
//            }while (cursor.moveToNext())
//        }
//        db.close()
//
//        return  bandaList
//    }
//
//    fun searchBandData(query: String):ArrayList<EBanda>{
//
//        val bandaList = ArrayList<EBanda>()
//        val selectQuery = "SELECT * FROM ${CConstantesDB.TABLE_NAME_BAND} WHERE ${CConstantesDB.B_NAME} LIKE '%$query%'"
//        val db = this.writableDatabase
//        val cursor = db.rawQuery(selectQuery, null)
//
//        if(cursor.moveToNext()){
//            do{
//                val banda = EBanda(
//                    cursor.getInt(cursor.getColumnIndex(CConstantesDB.B_ID)),
//                    ""+cursor.getString(cursor.getColumnIndex(CConstantesDB.B_NAME)),
//                    ""+cursor.getString(cursor.getColumnIndex(CConstantesDB.B_GENRE)),
//                    ""+cursor.getString(cursor.getColumnIndex(CConstantesDB.B_AWARDS)),
//                    ""+cursor.getString(cursor.getColumnIndex(CConstantesDB.B_YEARCREATION)),
//                    ""+cursor.getString(cursor.getColumnIndex(CConstantesDB.B_FUNDSPERCONCERT))
//                )
//                bandaList.add(banda)
//            }while (cursor.moveToNext())
//        }
//        db.close()
//
//        return  bandaList
//    }

}