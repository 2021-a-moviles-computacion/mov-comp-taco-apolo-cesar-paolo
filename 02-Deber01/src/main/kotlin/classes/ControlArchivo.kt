package classes

import java.io.File

class ControlArchivo {
    val pathEquipo = "D:\\7mo\\AppMov\\Repos\\mov-comp-taco-apolo-cesar-paolo\\02-Deber01\\src\\main\\resources\\Equipo.txt"
    val pathJugador = "D:\\7mo\\AppMov\\Repos\\mov-comp-taco-apolo-cesar-paolo\\02-Deber01\\src\\main\\resources\\Jugador.txt"

    fun readEquipo() {
        val file = File(pathEquipo)
        println("Equipos existentes en archivo:")
        file.forEachLine { println(it) }
        println("------------------------------- FIN ARCHIVO ---------------------------------------")
    }

    fun readJugador() {
        val file = File(pathJugador)
        println("Jugadores existentes en archivo:")
        file.forEachLine { println(it) }
        println("------------------------------- FIN ARCHIVO ---------------------------------------")
    }

    fun writeEquipo(equipo: Equipo) {
        File(pathEquipo).appendText(equipo.toString())
    }

    fun writeJugador(jugador: Jugador){
        File(pathJugador).appendText(jugador.toString())
    }
}