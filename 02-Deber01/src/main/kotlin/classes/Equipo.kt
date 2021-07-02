package classes

import java.util.*
import kotlin.collections.ArrayList

class Equipo {
    val nombre : String
    val titulos : Int?
    val fechaFundacion : Date
    val estado : Boolean //True -> activo | False -> inactivo
    val presupuesto : Double?
    val arregloEquipo: ArrayList<Equipo> = arrayListOf()

    init {
        println("Inicializando Equipo....")
        println("Ingrese nombre del equipo: ")
        nombre = readLine().toString()
        println("Ingrese el # de titulos que tiene el equipo ${nombre}: ")
        titulos = readLine()?.toInt()!!
        println("Ingrese la fecha de fundacion del equipo ${nombre}: ")
        fechaFundacion = Date()
        println("Ingrese (TRUE) si el equipo ${nombre} sigue activo competitivamente, (FALSE) caso contrario")
        estado = readLine().toBoolean()
        println("Ingrese el presupuesto anual del equipo ${nombre}: ")
        presupuesto = readLine()?.toDouble()!!

    }

    fun ingresarFecha(){
        val date : Date

    }

    fun crearEquipo(equipo: Equipo){
        arregloEquipo.add(equipo)
        arregloEquipo.toString()
    }

    fun leerEquipo(equipo: Equipo){
        arregloEquipo.forEach(){
            equipo: Equipo -> equipo.toString()
        }
    }

    fun actualizarEquipo(nombreEquipo: String){
        val actualizarEquipo = ArrayList<Equipo>()


    }

    fun eliminarEquipo(){
        val elminarEquipo = ArrayList<Equipo>()


    }

    override fun toString(): String {
        return super.toString()
    }
}