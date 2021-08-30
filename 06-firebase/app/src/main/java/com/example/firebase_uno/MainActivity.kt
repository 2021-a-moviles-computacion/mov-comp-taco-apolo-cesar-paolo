package com.example.firebase_uno

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.firebase_uno.dto.FirestoreUsuarioDTO
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

        val btnLogout = findViewById<Button>(R.id.btn_logout)

        btnLogout.setOnClickListener {
            logOut()
        }

        val btnCProducto = findViewById<Button>(R.id.btn_producto)

        btnCProducto.setOnClickListener {
            val intent = Intent(this, CProducto::class.java)
            startActivity(intent)
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
                            setearUsuarioFirebase()
                        }
                    }

                }else{
                    Log.i("firebase-login","El usuario cancelo")
                }
            }
        }

    }

    fun setearUsuarioFirebase(){
        val instanceAuth = FirebaseAuth.getInstance()
        val usrLocal = instanceAuth.currentUser
        if (usrLocal != null){
            if (usrLocal.email != null){

                val db = Firebase.firestore

                val referencia = db
                    .collection("usuario")
                    .document(usrLocal.email.toString())// /usuario/a@....com

                referencia
                    .get()
                    .addOnSuccessListener {
                        val usrCargado: FirestoreUsuarioDTO? =
                            it.toObject(FirestoreUsuarioDTO::class.java)
                        if (usrCargado != null){
                          BAuthUsuario.usuario = BUsuarioFirebase(
                              usrCargado.uid,
                              usrCargado.email,
                              usrCargado.roles
                          )
                            setearBienvenida()
                        }
                        //BAuthUsuario.usuario = usrCargado
                        Log.i("firebase-firestore", "Usuario cargado")

                    }
                    .addOnFailureListener{
                        Log.i("firebsae-firestore", "Fallo cargar usuario")
                    }

            }
        }
    }

    fun registrarUsuarioPrimeraVez(usuario: IdpResponse){
        val userLogueado = FirebaseAuth
            .getInstance()
            .currentUser

        val idUser = usuario.email

        if (usuario.email != null && userLogueado != null){
            //roles: ["usuario", "admin"]
            val db = Firebase.firestore
            val rolesUser = arrayListOf("usuario")
            val nuevoUser = hashMapOf<String, Any>(
                "roles" to rolesUser,
                "uid" to userLogueado.uid,
                "email" to idUser.toString()
            )


            db.collection("usuario")
                // UID por default del firebase
                // .add(nuevoUser)
                // SETEO POR DEV
                .document(idUser.toString())
                .set(nuevoUser)
                .addOnSuccessListener {
                    Log.i("firebase-firestore","Se creo Usuario")
                    setearUsuarioFirebase()
                }
                .addOnFailureListener {
                    Log.i("firebase-firestore", "falloooo")
                }


        }else{
            Log.i("firebase-login", "ERROR")
        }
    }

    fun setearBienvenida(){
        val tv_bienvenida = findViewById<TextView>(R.id.tv_bienvienida)
        val btnLOGIN = findViewById<Button>(R.id.btn_login)
        val btnLOGOUT = findViewById<Button>(R.id.btn_logout)
        val btnProducto = findViewById<Button>(R.id.btn_producto)

        if (BAuthUsuario.usuario != null){
            tv_bienvenida.text = "Bienvenido ${BAuthUsuario.usuario?.email}"
            btnLOGIN.visibility = View.INVISIBLE
            btnLOGOUT.visibility = View.VISIBLE
            btnProducto.visibility = View.VISIBLE
        }else{
            tv_bienvenida.text = "Ingresa al aplicativo"
            btnLOGIN.visibility = View.VISIBLE
            btnLOGOUT.visibility = View.INVISIBLE
            btnProducto.visibility = View.INVISIBLE
        }

    }

    fun logOut(){
        AuthUI
            .getInstance()
            .signOut(this)
            .addOnCompleteListener{
                BAuthUsuario.usuario = null
                setearBienvenida()
            }
    }



}