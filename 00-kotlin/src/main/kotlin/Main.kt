import java.util.*
import kotlin.collections.ArrayList

fun main(){
    println("Hola Mundo")
    // tipo nombre = valor
    // int edad = 12;
    var edadProfesor: Int = 32
    // var edadProfesor: Int = 32
    var sueldoProfesor = 1.23
    edadProfesor = 23
    sueldoProfesor = 2.33
    //Duck Typing

    // Mutables / Inmutables
    // Mutables (Re asignar "=")
    var edadCachorro = 2
    edadCachorro = 13
    edadCachorro = 12

    // Inmutables (NO Re asignar "=")
    val numeroCedula = 1722488671
    //numeroCedula = 17254465

    // TIPOS DE VARIABLES (JAVA) INT, BOOLEAN, DOUBLE, FLOAT, CHAR
    val nombreProfesor1: String = "César Taco"
    val sueldo: Double = 1.23
    val estadoCivil: Char = 'S'
    val fechaNacimiento: Date = Date()

    // Condicionales
    if (true) {
        // verdadero
    }else{
        //falso
    }

    // switch Estado -> S -> C -> ::
    val estadoCivilWhen: String = "S"
    when (estadoCivilWhen){
        ("S") -> { //función flecha
            println("Acercarse")
        }
        "C" -> {
            println("Alejarse")
        }
        "UN" -> println("Hablar")
        else -> println("No Reconocido")
    }

    val coqueteo = if (estadoCivilWhen == "S") "SI" else "NO"
    // condicion ? Bloque true : bloque false

    imprimirNombre ("Cesar")

    calcularSueldo(100.0)
    calcularSueldo(100.0, 14.0)

    //Named Paramters
    calcularSueldo(
        sueldo = 150.0,
        //tasa = 12.0
        bonoEspecial = 15.0,
    )

    // Tipos de arreglos
    // Arreglo estatico
    val arregloEstatico: Array<Int> = arrayOf(1, 2, 3)
    //arreglosEstatico.add(12) -> NO SE PUEDE

    // Arreglo Dinamico
    val arregloDinamico: ArrayList<Int> = arrayListOf(1,2,3,4,5,6,7,8,9,10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    // Operadores -> tanto para estaticos y dinamicos

    // for each -> UNIT (AYUDA A ITERAR UN ARREGLO)
    val res: Unit = arregloDinamico
        .forEach(){ valorActual: Int ->
            println("Valor Actual: ${valorActual}")
        }

    println(res)

    val res1: Unit = arregloDinamico
        .forEachIndexed(){ indice: Int, valorActual: Int ->
            println("Valor Actual: ${valorActual} Indice ${indice}")
        }

    println(res1)

    // MAP -> Muta el arreglo (Cambia el arreglo)
    // 1) Enviamos el nuevo valor de la iteracion
    // 2) Nos devuelve en un NUEVO ARREGLO con los valores modificados.

    val respuestaMap: List<Double> = arregloDinamico
        .map{ valorActual: Int ->
            return@map valorActual.toDouble() + 100.0
        }

    arregloDinamico.map { it + 15 } // Reduzco el codigo en una sola linea
        //.map{ valorActual: Int ->
        //    return@map valorActual + 15
        //}

    println(respuestaMap)

    // Filter -> filtra el arreglo
    // 1) Devuelve una expresion (TRUE o FALSE)
    // 2) Nuevo arreglo filtrado

    val respuestaFilter: List<Int> = arregloDinamico
        .filter{ valorActual: Int ->
            val mayoresACinco: Boolean = valorActual < 5
            return@filter mayoresACinco
        }

    val respuestaFilterDos = arregloDinamico.filter{ it >= 5 } // Reduccion de codigo a una sola linea

    println(respuestaFilter)
    println(respuestaFilterDos)

} // FIN BLOQUE MAIN

fun imprimirNombre (nombre: String): Unit {// se puede omitir el UNIT
    println("Nombre: ${nombre}")
}

fun calcularSueldo (
    sueldo: Double, //Requerido
    tasa: Double = 12.0, //Opcional (defecto)
    bonoEspecial: Double? = null//Opcional (puede ser nulo)
): Double{
    if(bonoEspecial == null){
        return sueldo * (100 / tasa)
    }else {
        return sueldo * (100 / tasa) + bonoEspecial
    }
}

// int -> INT?
// double -> Double?
// Date -> Date?

