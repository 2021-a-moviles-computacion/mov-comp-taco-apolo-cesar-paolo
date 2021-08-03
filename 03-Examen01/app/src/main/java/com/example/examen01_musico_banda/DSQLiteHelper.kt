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
    private val TABLE_MUSIC_NAME = CConstantesDB.TABLE_NAME_MUSICIAN

    companion object{
        private const val ID = CConstantesDB.B_ID
        private const val NAME = CConstantesDB.B_NAME
        private const val GENRE = CConstantesDB.B_GENRE
        private const val AWARDS = CConstantesDB.B_AWARDS
        private const val YEAR = CConstantesDB.B_YEARCREATION
        private const val FUNDS = CConstantesDB.B_FUNDSPERCONCERT

        private const val ID_M = CConstantesDB.M_ID
        private const val M_NAME = CConstantesDB.M_NAME
        private const val M_BIRTH = CConstantesDB.M_BIRTHDAY
        private const val P_Awards = CConstantesDB.M_PERSONAL_AWARDS
        private const val OCUP = CConstantesDB.M_POSITION
        private const val ACT = CConstantesDB.M_ACTIVITY

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CConstantesDB.CREATE_TABLE_BAND)
        db?.execSQL(CConstantesDB.CREATE_TABLE_MUSICIAN)
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

    fun insertMusico(msc: EMusico): Long{

        val db =  this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, msc.id)
        contentValues.put(M_NAME, msc.name)
        contentValues.put(M_BIRTH, msc.birthday)
        contentValues.put(P_Awards, msc.p_awards)
        contentValues.put(OCUP, msc.ocup)
        contentValues.put(ACT, msc.activity)

        val success = db.insert(TABLE_MUSIC_NAME, null, contentValues)
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

    fun getAllMusicians(): ArrayList<EMusico>{

        val mscList: ArrayList<EMusico> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_MUSIC_NAME"
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
        var birthday: String
        var p_awards: String
        var ocup: String
        var act: String

        if (cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex("ID"))
                name = cursor.getString(cursor.getColumnIndex("NAME"))
                birthday = cursor.getString(cursor.getColumnIndex("BIRTHDAY"))
                p_awards = cursor.getString(cursor.getColumnIndex("PERSONAL_AWARDS"))
                ocup = cursor.getString(cursor.getColumnIndex("POSITION"))
                act = cursor.getString(cursor.getColumnIndex("ACTIVITY"))

                val msc = EMusico(
                    id, name, birthday, p_awards, ocup, act
                )
                mscList.add(msc)
            }while (cursor.moveToNext())
        }

        return mscList

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

    fun updateMusician(msc: EMusico): Int{

        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, msc.id)
        contentValues.put(NAME, msc.name)
        contentValues.put(M_BIRTH, msc.birthday)
        contentValues.put(P_Awards, msc.p_awards)
        contentValues.put(OCUP, msc.ocup)
        contentValues.put(ACT, msc.activity)

        val success = db.update(
            TABLE_MUSIC_NAME,
            contentValues,
            "ID=" + msc.id,
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

    fun deleteMusicianByID(id: Int): Int{

        val db = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(ID, id)

        val success = db.delete(TABLE_MUSIC_NAME, "ID=$id", null)
        db.close()
        return success

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS" + CConstantesDB.TABLE_NAME_BAND)
        db!!.execSQL("DROP TABLE IF EXISTS" + CConstantesDB.TABLE_NAME_MUSICIAN)
        onCreate(db)
    }


}