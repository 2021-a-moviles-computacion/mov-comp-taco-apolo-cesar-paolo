package com.example.moviles_computacion_2021_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class ACicloDeVida : AppCompatActivity() {

    var numero = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aciclo_de_vida)
        Log.i("ciclo-vida","onCreate")
        val textViewACicloDeVida = findViewById<TextView>(
            R.id.txv_ciclo_vida
        )

        textViewACicloDeVida.text = numero.toString()

        val buttonACicloDeVida = findViewById<Button>(
            R.id.btn_aumentar_ciclo_vida
        )

        buttonACicloDeVida.setOnClickListener{aumentarNumero()}
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.run {
            // AQUI GUARDAMOS CUALQUIER PRIMITIVO
            // SOLO PRIMITIVOS

            putInt("numero guardado", numero)
        }

        super.onSaveInstanceState(outState)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val numeroRecuperado: Int? = savedInstanceState.getInt("numero guardado")
        if (numeroRecuperado != null){
            numero = numeroRecuperado
            val textViewACicloDeVida = findViewById<TextView>(
                R.id.txv_ciclo_vida
            )
            textViewACicloDeVida.text = numero.toString()
        }

    }

    fun aumentarNumero(){
        numero = numero + 1
        val textViewACicloDeVida = findViewById<TextView>(
            R.id.txv_ciclo_vida
        )
        textViewACicloDeVida.text = numero.toString()
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ciclo-vida","onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo-vida","onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo-vida","onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo-vida","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo-vida","onDestroy")
    }
}