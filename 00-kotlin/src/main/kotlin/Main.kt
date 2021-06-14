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

    //OR AND
    // OR -> ANY  (ALGUNO CUMPLE)
    // AND -> ALL (TODOS CUMPLEN)

    val respuestaAny: Boolean = arregloDinamico
        .any{   valorActual: Int ->
            return@any (valorActual > 5 )
        }

    println(respuestaAny) //true

    val respuestaAll: Boolean = arregloDinamico
        .all{   valorActual: Int ->
            return@all (valorActual > 5 )
        }

    println(respuestaAll) //false

    //REDUCE -> VALOR ACUMULADO
    // 1) DEVUELVE EL ACUMULADO
    // 2) EN QUE VALOR EMPIEZA
    // [1,2,3,4,5]
    // 0 = 0 + 1    -> ITERACION 1
    // 1 = 1 + 2    -> ITERACION 2
    // 3 = 3 + 3    -> ITERACION 3
    // 6 = 6 + 4    -> ITERACION 4
    // 10 = 10 + 5  -> ITERACION 5
    // 15

    val respuestaReduce:  Int = arregloDinamico
        .reduce {//acumulado=0
                    acumulado: Int, valorActual: Int ->
            return@reduce acumulado + valorActual //-> LOGICA DE NEGOCIO
        }

    println(respuestaReduce) //78

    //100
    //[12,15,10,8]

    val arregloDanio = arrayListOf<Int>(12,15,8,10)
    val respuestaReduceFold = arregloDanio
        .fold(100){
            acumulado, valActualIt ->
        return@fold acumulado - valActualIt
    }

    println(respuestaReduceFold)

    val vidaActual: Double = arregloDinamico
        .map { it * 2.3 } //arreglo
        .filter { it > 20 } // arreglo
        .fold(100.00, { acc, i -> acc - i}) // valor
        .also { println(it) }// ejecutar codigo extra

    println("Valor vida actual ${vidaActual}")

    val ejemploUno = Suma(1,2)
    val ejemploDos = Suma(null,2)
    val ejemploTres = Suma(1,null)

    println(ejemploUno.sumar())
    println(Suma.historiaSuma)
    println(ejemploDos.sumar())
    println(Suma.historiaSuma)
    println(ejemploTres.sumar())
    println(Suma.historiaSuma)

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

abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor(
        uno: Int, //parametros requeridos
        dos: Int  //parametros requeridos
    ){
        numeroUno = uno
        numeroDos = dos
        println("Inicializar")
    }
}

abstract class Numero(//constructor primario
    protected var numeroUno: Int,//propiedad
    protected var numeroDos: Int//propiedad
){
    init{
        println("Inicializar")//bloque de inicio de constructor primario
    }
}

class Suma (
    uno: Int,   // Parametros requeridos
    dos: Int    // Parametros requeridos
        ):Numero(uno, dos) {//constructor papa (super)
            init {
                this.numeroUno
                this.numeroDos
                // X -> this.uno -> NO EXISTE
                // X -> this.dos -> NO EXISTE
            }
            constructor(//segundo constructor
                uno: Int?,
                dos: Int
            ): this(//llamada constructor primario
                if (uno == null) 0 else uno,
                dos
            )

            constructor(//tercer constructor
                uno: Int,
                dos: Int?
            ): this(//llamada constructor primario
                uno,
                if (dos == null) 0 else dos
            )

        // public fun sumar(): Int{
        fun sumar(): Int{
            //val total: Int = this.numeroUno + this.numeroDos
            val total: Int = numeroUno + numeroDos
            agregarHistorial(total)
            return total
        }

        // SINGLETON

        companion object{
            val historiaSuma = arrayListOf<Int>()

            fun agregarHistorial (valorNuevoSuma: Int){
                historiaSuma.add(valorNuevoSuma)
                println(historiaSuma)
            }
        }

        }