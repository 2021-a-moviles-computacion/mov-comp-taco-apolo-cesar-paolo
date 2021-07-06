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

    fun actualizarJugador(jugador: Jugador, nombreJugador: String){
        val actualizarJugador = ArrayList<Jugador>()
        val filteredList = ArrayList<Jugador>()

        arregloJugador.forEach(){ jugador: Jugador ->
            if (jugador.nombre ==  nombreJugador){
                filteredList.add(jugador)
                println(filteredList)

            }else{
                println("Equipo no encontrado")
            }
        }
    }

    fun eliminarJugador(){
        val elminarJugador = ArrayList<Jugador>()


    }

    override fun toString(): String {
        return "Jugador: ${nombre};Salario: ${salario};Fecha Registro: ${fechaRegistro}" +
                ";Activo: ${estado};Logros Personales: ${logrosPersonales}"
    }
}