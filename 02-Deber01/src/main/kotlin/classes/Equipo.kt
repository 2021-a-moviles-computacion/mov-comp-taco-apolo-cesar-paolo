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

    fun actualizarEquipo(
            nombre: String,
            datoEditar: String,
            nuevoDato: String
        ) {

            val indice = indice(nombre)
            val equipo = indice > -1
            if (equipo) {
                when (datoEditar) {
                    "nombre" -> {
                        arregloEquipo[indice].nombre = nuevoDato
                    }
                    "titulos" -> {
                        arregloEquipo[indice].titulos = nuevoDato.toInt()
                    }
                    "estado" -> {
                        arregloEquipo[indice].estado = nuevoDato.toBoolean()
                    }
                    "presupuesto" -> {
                        arregloEquipo[indice].presupuesto = nuevoDato.toDouble()
                    }
                    else -> {
                    println("No se encontro el Equipo ${datoEditar}")
                    }
                }
            }
            println("Equipos Actualizados:")
            println("$arregloEquipo")
    }

    fun indice(nombre: String): Int {
        val respuesta = arregloEquipo.filter { equipo: Equipo ->
            return@filter equipo.nombre == nombre
        }
        val equipo = respuesta.size > 0
        if (!equipo) {
            println("No existe el Equipo ${nombre}")
            return -1
        }
        return arregloEquipo.indexOf(respuesta[0])
    }

    fun eliminarEquipo(){
        val elminarEquipo = ArrayList<Equipo>()
        println("Listado de Equipos: \n$arregloEquipo")
        println("Ingrese el nombre del Equipo a eliminar:")
        val consulta = readLine().toString()
        arregloEquipo.forEach { equipo ->
            if (equipo.nombre == consulta) {
                arregloEquipo.remove(equipo)
                println("Equipos actualizados")
                println("$arregloEquipo")
            } else if (equipo.nombre != consulta) {
                println("No se encuentra el Equipo ${nombre}")
            }
        }
    }

    override fun toString(): String {
        return "\nEquipo: ${nombre};Titulos: ${titulos};Fecha Registro: ${fechaRegistro}" +
                ";Activo: ${estado};Presupuesto: ${presupuesto}"
    }
}