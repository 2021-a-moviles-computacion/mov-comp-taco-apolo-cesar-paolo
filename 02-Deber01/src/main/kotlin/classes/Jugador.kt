package classes

import java.util.*
import kotlin.collections.ArrayList

class Jugador {
    var nombre : String = ""
    var salario : Double? = 0.0
    var fechaRegistro : Date = Date()
    var estado : Boolean = false//True -> activo | False -> inactivo
    var logrosPersonales : Int = 0
    var arregloJugador: ArrayList<Jugador> = arrayListOf()

    init {
        println("Inicializando Jugador....")
    }

    fun setJugador(){
        println("Ingrese nombre del jugador: ")
        nombre = readLine().toString()
        println("Ingrese el salario del jugador ${nombre}: ")
        salario = readLine()?.toDouble()!!
        fechaRegistro = Date()
        println("Ingrese (TRUE) si el juagdor ${nombre} sigue activo competitivamente, (FALSE) caso contrario")
        estado = readLine().toBoolean()
        println("Ingrese el # de logros personales del jugador ${nombre}: ")
        logrosPersonales = readLine()?.toInt()!!
    }

    fun crearJugador(jugador: Jugador){
        arregloJugador.add(jugador)
        //println(arregloEquipo)
        //equipo.toString()
    }

    fun leerJugador(jugador: Jugador){
        println("Equipos existentes en arreglo")
        val archivo = ControlArchivo()
        val res = archivo.readJugador().toString()
        if (arregloJugador != null){
            arregloJugador.forEach(){
                    jugador: Jugador -> println(jugador)
            }
        }else
            println("No existen jugadores creados aún....")

        //println(equipo)
    }

    fun actualizarJugador(
        nombre: String,
        datoEditar: String,
        nuevoDato: String
    ) {

        val indice = indice(nombre)
        val jugador = indice > -1
        if (jugador) {
            when (datoEditar) {
                "nombre" -> {
                    arregloJugador[indice].nombre = nuevoDato
                }
                "salario" -> {
                    arregloJugador[indice].salario = nuevoDato.toDouble()
                }
                "estado" -> {
                    arregloJugador[indice].estado = nuevoDato.toBoolean()
                }
                "logrosPersonales" -> {
                    arregloJugador[indice].logrosPersonales = nuevoDato.toInt()
                }
                else -> {
                    println("No se encontro el Jugador ${datoEditar}")
                }
            }
        }
        println("Equipos Actualizados:")
        println("$arregloJugador")
    }

    fun indice(nombre: String): Int {
        val respuesta = arregloJugador.filter { jugador: Jugador ->
            return@filter jugador.nombre == nombre
        }
        val jugador = respuesta.isNotEmpty()
        if (!jugador) {
            println("No existe el Jugador ${nombre}")
            return -1
        }
        return arregloJugador.indexOf(respuesta[0])
    }

    fun eliminarJugador(){
        val elminarJugador = ArrayList<Jugador>()
        println("Listado de Jugadores: \n$arregloJugador")
        println("Ingrese el nombre del Equipo a eliminar:")
        val consulta = readLine().toString()
        arregloJugador.forEach { jugador ->
            if (jugador.nombre == consulta) {
                arregloJugador.remove(jugador)
                println("Equipos actualizados")
                println("$arregloJugador")
            } else if (jugador.nombre != consulta) {
                println("No se encuentra el jugador ${nombre}")
            }
        }
    }

    override fun toString(): String {
        return "Jugador: ${nombre};Salario: ${salario};Fecha Registro: ${fechaRegistro}" +
                ";Activo: ${estado};Logros Personales: ${logrosPersonales}"
    }
}