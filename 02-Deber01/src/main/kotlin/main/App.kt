package main

import classes.Equipo

fun main(){

    println("CRUD -- EQUIPO - JUGADOR")
    var equipo = Equipo()
    equipo.crearEquipo(equipo)
    equipo.leerEquipo(equipo)
}