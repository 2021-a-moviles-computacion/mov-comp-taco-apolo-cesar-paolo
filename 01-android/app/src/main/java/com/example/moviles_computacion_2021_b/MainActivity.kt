package com.example.moviles_computacion_2021_b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIrCicloVida = findViewById<Button>(
            R.id.btn_ciclo_vida
        )

        btnIrCicloVida
            .setOnClickListener {
                abrirCicloVida()
            }
    }

    fun abrirCicloVida(){
        val intentExplicito = Intent(
            this,
            ACicloDeVida::class.java
        )
        startActivity(intentExplicito)
    }
}