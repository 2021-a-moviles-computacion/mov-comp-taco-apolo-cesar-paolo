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
                     Elija una opción:
                 1.- Gestión de Equipos
                 2.- Gestión de Jugadores
                 0.- Salir
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
                0 -> println("Saliendo del programa.......")
                else -> println("Ingrese un dato valido ...")
            }
        } catch (e: Exception) {
            println("Error desconocido ......")
        }
    }

}

fun menuEquipo() {

    var equipo: Equipo = Equipo()
    var ctrlArchivo = ControlArchivo()
    var eleccion = -1
    while (eleccion != 0) {
        try {
            println(
                """
                     --- MENÚ DE EQUIPOS ---
        Elija una opción:
     1.- Crear Equipo
     2.- Leer Equipos Existentes
     3.- Actualizar Equipo por nombre
     4.- Eliminar Equipo por nombre
     0.- Regresar al menú principal
                 """.trimIndent()
            )
            val opc = readLine()?.toInt()
            when (opc) {
                1 -> {
                    equipo.crearEquipo(equipo)
                    var salida = equipo.leerEquipo(equipo).toString()
                    ctrlArchivo.write(salida)
                    ctrlArchivo.read()
                }
                2 -> {
                    println("Equipos Existentes en arreglo")
                    equipo.leerEquipo(equipo)
                    println("Equipos Existentes en Archivo")
                    ctrlArchivo.read()
                }
                3 -> {
                    val consulta = readLine().toString()
                }
                4 -> {

                }
                0 -> menu()
                else -> println("Ingrese un dato valido...")
            }
        } catch (e: Exception) {
            println("Error desconocido ....")
        }
    }

}