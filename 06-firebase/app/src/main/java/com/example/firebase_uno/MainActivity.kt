package com.example.firebase_uno

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    val CODIGO_INICIO_SESION = 102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<Button>(R.id.btn_login)

        btnLogin.setOnClickListener {
            llamarLoginUsuario()
        }


    }

    fun llamarLoginUsuario(){
        val providers = arrayListOf(
            //lista de proveedores
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTosAndPrivacyPolicyUrls(
                    "https://example.com/terms.html",
                "https://example.com/privacy.html"
                )
                .build(),
            CODIGO_INICIO_SESION
        )
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            CODIGO_INICIO_SESION -> {
                if (resultCode == Activity.RESULT_OK){
                    val usuario = IdpResponse.fromResultIntent(data)

                    if (usuario !=  null){
                        if (usuario?.isNewUser == true){
                            Log.i("firebase-login","Nuevo Usuario")
                            registrarUsuarioPrimeraVez(usuario)
                        }else{
                            Log.i("firebase-login","Antiguo Usuario")
                        }
                    }

                }else{
                    Log.i("firebase-login","El usuario cancelo")
                }
            }
        }

    }

    fun registrarUsuarioPrimeraVez(usuario: IdpResponse){
        val userLogueado = FirebaseAuth
            .getInstance()
            .currentUser
        if (usuario.email != null && userLogueado != null){
            //roles: ["usuario", "admin"]
            val db = Firebase.firestore
            val rolesUser = arrayListOf("usuario")
            val nuevoUser = hashMapOf<String, Any>(
                "roles" to rolesUser,
                "uid" to userLogueado.uid
            )
            val idUser = usuario.email

            db.collection("usuario")
                // UID por default del firebase
                // .add(nuevoUser)
                // SETEO POR DEV
                .document(idUser.toString())
                .set(nuevoUser)
                .addOnSuccessListener {
                    Log.i("firebase-firestore","Se creo Usuario")
                }
                .addOnFailureListener {
                    Log.i("firebase-firestore", "falloooo")
                }


        }else{
            Log.i("firebase-login", "ERROR")
        }
    }

}