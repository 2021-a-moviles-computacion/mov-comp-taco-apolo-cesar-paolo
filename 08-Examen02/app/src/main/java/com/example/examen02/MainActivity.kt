package com.example.examen02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnIrABandaActivity = findViewById<Button>(
            R.id.btn_ir_banda
        )

        btnIrABandaActivity.setOnClickListener {
            abrirActividad(BIngresarDatosBanda::class.java)
        }

        val btnIrMusicoActivity = findViewById<Button>(
            R.id.btn_ir_musico
        )

        btnIrMusicoActivity.setOnClickListener {
            abrirActividad(BIngresaDatosMusico::class.java)
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

//    fun abrirActividadConParametros(clase : Class<*>){
//        val intentExplicito = Intent(
//            this,
//            clase
//        )
//        intentExplicito.putExtra("nombre","Cesar")
//        intentExplicito.putExtra("apellido","Taco")
//        intentExplicito.putExtra("edad",25)
//        intentExplicito.putExtra("entrenador",
//            BEntrandor("Cesar", "Taco"))
//
//        startActivityForResult(intentExplicito,
//            CODIGO_RESPUESTA_INTENT_EXPLICITO)
/*
registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
    when(it.resultCode){
        Activity.RESULT_OK-> {
            //Ejecutar codigo OK
            it.data?.getStringExtra("nombreModofocado")
            it.data?.getIntExtra("edadModificada",0)
            it.data?.getParcelableExtra<BEntrenador>("entrenadorModificado")
        }
    }
}
*/

//    }
}