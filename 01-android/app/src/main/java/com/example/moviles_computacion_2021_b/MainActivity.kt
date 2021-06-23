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
                abrirActividad(ACicloDeVida::class.java)
            }

        val btnIrListView = findViewById<Button>(
            R.id.btn_ir_list_view
        )

        btnIrListView
            .setOnClickListener {
                abrirActividad(BListView::class.java)
            }
    }

    fun abrirActividad(
        clase : Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }
}