package classes

import java.util.*
import kotlin.collections.ArrayList

class Equipo {
    val nombre : String
    val titulos : Int?
    val fechaRegistro : Date
    val estado : Boolean //True -> activo | False -> inactivo
    val presupuesto : Double?
    val arregloEquipo: ArrayList<Equipo> = arrayListOf()

    init {
        println("Inicializando Equipo....")
        println("Ingrese nombre del equipo: ")
        nombre = readLine().toString()
        println("Ingrese el # de titulos que tiene el equipo ${nombre}: ")
        titulos = readLine()?.toInt()!!
        fechaRegistro = Date()
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
        //println(arregloEquipo)
        //equipo.toString()
    }

    fun leerEquipo(equipo: Equipo){
        if (arregloEquipo != null){
            arregloEquipo.forEach(){
                    equipo: Equipo -> println(equipo)
            }
        }else
            println("No existen equipos creados aún....")

        //println(equipo)
    }

    fun actualizarEquipo(equipo: Equipo, nombreEquipo: String){
        val actualizarEquipo = ArrayList<Equipo>()
        val filteredList = ArrayList<Equipo>()

        arregloEquipo.forEach(){ equipo: Equipo ->
            if (equipo.nombre ==  nombreEquipo){
                filteredList.add(equipo)
                println(filteredList)
            }else{
                println("Equipo no encontrado")
            }
        }
    }

    fun eliminarEquipo(){
        val elminarEquipo = ArrayList<Equipo>()


    }

    override fun toString(): String {
        return "\nEquipo: ${nombre} \nTitulos: ${titulos}\nFecha Registro: ${fechaRegistro}" +
                "\nActivo: ${estado} \nPresupuesto: ${presupuesto}\n"
    }
}