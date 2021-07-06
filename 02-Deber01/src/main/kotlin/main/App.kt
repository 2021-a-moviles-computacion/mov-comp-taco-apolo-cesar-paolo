package main

import classes.ControlArchivo
import classes.Equipo

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
                    //menuJugador()
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
                    val consulta = readLine().toString()
                    equipo.actualizarEquipo(equipo,consulta)
                }
                4 -> {
                    val consulta = readLine().toString()
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