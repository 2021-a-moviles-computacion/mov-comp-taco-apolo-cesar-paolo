package classes

import java.io.File

class ControlArchivo {
    val path = "D:\\7mo\\AppMov\\Repos\\mov-comp-taco-apolo-cesar-paolo\\02-Deber01\\src\\Equipo-Jugador.txt"

    fun read() {
        val file = File(path)
        file.forEachLine { println(it) }
        println("------------------------------- FIN ARCHIVO ---------------------------------------")
    }

    fun write(text: String) {
        File(path).appendText(text)
    }
}