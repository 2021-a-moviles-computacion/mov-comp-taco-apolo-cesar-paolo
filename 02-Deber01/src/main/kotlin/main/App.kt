package main

import classes.ControlArchivo
import classes.Equipo
import classes.Jugador

fun main(){
    menu()
}

fun menu() {

    var select = -1
    while (select != 0) {
        try {
            println(
                """
                    CRUD - EQUIPO - JUGADOR
                 1.- Gestión de Equipos
                 2.- Gestión de Jugadores
                 0.- Salir
                 Elija una opción:
                 """.trimIndent()
            )
            val opc = readLine()?.toInt()
            when (opc) {
                1 -> {
                    menuEquipo()
                }
                2 -> {
                    menuJugador()
                }
                0 -> {
                    println("Saliendo del programa.......")
                    break
                }
                else -> println("Ingrese un dato valido ...")
            }
        } catch (e: Exception) {
            println("Error desconocido ......")
        }
    }

}

fun menuEquipo() {

    var equipo = Equipo()
    var ctrlArchivo = ControlArchivo()
    var eleccion = -1
    while (eleccion != 0) {
        try {
            println(
                """
                     --- MENÚ DE EQUIPOS ---
     1.- Crear Equipo
     2.- Leer Equipos Existentes
     3.- Actualizar Equipo por nombre
     4.- Eliminar Equipo por nombre
     0.- Regresar al menú principal
     Elija una opción:
                 """.trimIndent()
            )
            val opc = readLine()?.toInt()
            when (opc) {

                1 -> {
                    equipo.setEquipo()
                    equipo.crearEquipo(equipo)
                    ctrlArchivo.writeEquipo(equipo)
                }
                2 -> {
                    equipo.leerEquipo(equipo)
                    ctrlArchivo.readEquipo()
                }
                3 -> {
                    println("Listado de Equipos:")
                    ctrlArchivo.readEquipo()
                    println("Ingrese el nombre del equipo a actualizar")
                    val nombre = readLine().toString()
                    println("Ingrese el atributo del Equipo ${nombre} que desea actualizar")
                    val datoAModificar = readLine().toString()
                    println("Ingrese el nuevo valor del atributo ${datoAModificar}")
                    val nuevoValor = readLine().toString()
                    equipo.actualizarEquipo(nombre,datoAModificar,nuevoValor)
                }
                4 -> {
                    //val consulta = readLine().toString()
                    equipo.eliminarEquipo()
                }
                0 -> {
                    println("Regresando menu principal ....")
                    break
                }
                else -> println("Ingrese un dato valido...")
            }
        } catch (e: Exception) {
            println("Error desconocido ....")
        }
    }

}

fun menuJugador() {

    var jugador = Jugador()
    var ctrlArchivo = ControlArchivo()
    var eleccion = -1
    while (eleccion != 0) {
        try {
            println(
                """
                     --- MENÚ DE JUGADORES ---
     1.- Crear Jugador
     2.- Leer Jugadores Existentes
     3.- Actualizar Jugador por nombre
     4.- Eliminar Jugador por nombre
     0.- Regresar al menú principal
     Elija una opción:
                 """.trimIndent()
            )
            val opc = readLine()?.toInt()
            when (opc) {

                1 -> {
                    jugador.setJugador()
                    jugador.crearJugador(jugador)
                    ctrlArchivo.writeJugador(jugador)
                }
                2 -> {
                    jugador.leerJugador(jugador)
                    ctrlArchivo.readJugador()
                }
                3 -> {
                    println("Listado de Jugadores:")
                    ctrlArchivo.readJugador()
                    println("Ingrese el nombre del jugador a actualizar")
                    val nombre = readLine().toString()
                    println("Ingrese el atributo del Jugador ${nombre} que desea actualizar")
                    val datoAModificar = readLine().toString()
                    println("Ingrese el nuevo valor del atributo ${datoAModificar}")
                    val nuevoValor = readLine().toString()
                    jugador.actualizarJugador(nombre,datoAModificar,nuevoValor)
                }
                4 -> {
                    //val consulta = readLine().toString()
                    jugador.eliminarJugador()
                }
                0 -> {
                    println("Regresando menu principal ....")
                    break
                }
                else -> println("Ingrese un dato valido...")
            }
        } catch (e: Exception) {
            println("Error desconocido ....")
        }
    }

}