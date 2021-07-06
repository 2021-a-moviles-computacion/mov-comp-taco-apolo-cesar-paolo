package classes

import java.util.*
import kotlin.collections.ArrayList

class Equipo {
    var nombre : String = ""
    var titulos : Int? = 0
    var fechaRegistro : Date = Date()
    var estado : Boolean = false//True -> activo | False -> inactivo
    var presupuesto : Double? = 0.0
    var arregloEquipo: ArrayList<Equipo> = arrayListOf()

    init {
        println("Inicializando Equipo....")
    }

    fun setEquipo(){
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

    fun crearEquipo(equipo: Equipo){
        arregloEquipo.add(equipo)
        //println(arregloEquipo)
        //equipo.toString()
    }

    fun leerEquipo(equipo: Equipo){
        println("Equipos existentes en arreglo")
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
        return "Equipo: ${nombre};Titulos: ${titulos};Fecha Registro: ${fechaRegistro}" +
                ";Activo: ${estado};Presupuesto: ${presupuesto}"
    }
}