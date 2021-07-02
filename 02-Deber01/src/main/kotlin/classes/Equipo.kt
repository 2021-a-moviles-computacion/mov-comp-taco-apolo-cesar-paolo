package classes

import java.util.*
import kotlin.collections.ArrayList

class Equipo {
    val nombre : String
    val titulos : Int?
    val fechaFundacion : Date
    val estado : Boolean //True -> activo | False -> inactivo
    val presupuesto : Double?

    init {
        println("Inicializando Equipo....")
        nombre = readLine().toString()
        titulos = readLine()?.toInt()
        fechaFundacion = Date()
        estado = readLine().toBoolean()
        presupuesto = readLine()?.toDouble()
    }

    fun ingresarFecha(){
        val date : Date

    }

    fun crearEquipo(equipo: Equipo){
        val crearEquipo = ArrayList<Equipo>()
        crearEquipo.add(equipo)
        println(equipo.toString())
    }

    fun leerEquipo(){
        val leerEquipo = ArrayList<Equipo>()


    }

    fun actualizarEquipo(nombreEquipo: String){
        val actualizarEquipo = ArrayList<Equipo>()


    }

    fun eliminarEquipo(){
        val elminarEquipo = ArrayList<Equipo>()


    }
}