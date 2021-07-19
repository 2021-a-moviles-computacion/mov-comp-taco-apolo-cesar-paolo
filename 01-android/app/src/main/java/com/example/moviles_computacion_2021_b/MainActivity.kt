package com.example.moviles_computacion_2021_b

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import com.google.android.material.appbar.AppBarLayout

class MainActivity : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 201
    val CODIGO_RESPUESTA_INTENT_IMPLICITO = 202

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //EBaseDatos.tablaUsuario = ESqliteHelperUsuario(this)

        val btnBDD = findViewById<Button>(
            R.id.btn_bdd
        )

        btnBDD
            .setOnClickListener{
                abrirActividad(EBaseDatosWorkClass::class.java)
            }

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

        val btnIrIntent = findViewById<Button>(
            R.id.btn_ir_intent
        )

        btnIrIntent
            .setOnClickListener {
                abrirActividadConParametros(CIntentExplicitoParametros::class.java)
            }

        val btnAbriIntentImplicito = findViewById<Button>(
            R.id.btn_ir_intent_implicito
        )

        btnAbriIntentImplicito
            .setOnClickListener{
                val intentConRespuesta = Intent(
                    Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone
                        .CONTENT_URI
                )
                startActivityForResult(intentConRespuesta,
                CODIGO_RESPUESTA_INTENT_IMPLICITO)
            }

        val bntAbriRecView = findViewById<Button>(
            R.id.btn_recycler_view
        )

        bntAbriRecView
            .setOnClickListener{
                abrirActividad(GRecyclerView::class.java)
            }

        val btnHTTP = findViewById<Button>(
            R.id.btn_ir_http
        )

        btnHTTP
            .setOnClickListener{
                abrirActividad(HHttpActivity::class.java)
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

    fun abrirActividadConParametros(clase : Class<*>){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("nombre","Cesar")
        intentExplicito.putExtra("apellido","Taco")
        intentExplicito.putExtra("edad",25)
        intentExplicito.putExtra("entrenador",
            BEntrandor("Cesar", "Taco"))

        startActivityForResult(intentExplicito,
                               CODIGO_RESPUESTA_INTENT_EXPLICITO)
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

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode){
            CODIGO_RESPUESTA_INTENT_EXPLICITO -> {
                if (resultCode == RESULT_OK){
                    Log.i("intent-explicito",
                        "SI actualizo los datos")
                    if (data != null){
                        val nombre = data.getStringExtra(
                            "nombreModificado"
                        )
                        val edad = data.getIntExtra(
                            "edadModificado",
                            0
                        )
                        val entrenador = data
                            .getParcelableExtra<BEntrandor>(
                            "entrenadorModificado"
                        )
                        Log.i("intent-explicito",
                            "${nombre}")
                        Log.i("intent-explicito",
                            "${edad}")
                        Log.i("intent-explicito",
                            "${entrenador}")
                    }
                }

            }
            CODIGO_RESPUESTA_INTENT_IMPLICITO -> {
                if (resultCode == RESULT_OK){
                    if ( data != null){
                        if (data.data != null){
                            val uri: Uri = data.data!!
                            val cursor = contentResolver.query(
                                uri,
                                null,
                                null,
                                null,
                                null,
                                null
                            )
                            cursor?.moveToFirst()
                            val indiceTelefono = cursor?.
                                    getColumnIndex(
                                        ContactsContract.CommonDataKinds.Phone.NUMBER
                                    )
                            val telefono = cursor?.getString(
                                indiceTelefono!!
                            )
                            cursor?.close()
                            Log.i("resultado","Telefono: ${telefono}")
                        }
                    }
                }
            }
        }
    }

}