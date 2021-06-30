package com.example.moviles_computacion_2021_b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class CIntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicito_parametros)

        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getIntExtra("edad", 0)
        val entrenador = intent.getParcelableExtra<BEntrandor>(
            "entrenador"
        )

        Log.i("intent-explicito","${nombre}")
        Log.i("intent-explicito","${apellido}")
        Log.i("intent-explicito","${edad}")
        Log.i("intent-explicito","${entrenador}")

        val botonDevolverRes = findViewById<Button>(
            R.id.btn_devolver_respuesta)

        botonDevolverRes
            .setOnClickListener{
                val intentDevolverParameter = Intent()
                intentDevolverParameter.putExtra(
                    "nombreModificado", "Paolo")
                intentDevolverParameter.putExtra(
                    "edadModificado", 30)

                intentDevolverParameter.putExtra(
                    "entrenadorModificado",
                    BEntrandor("Paolo", "Apolo"))

                setResult(
                    RESULT_OK,
                    intentDevolverParameter
                )

                finish()
            }
    }
}