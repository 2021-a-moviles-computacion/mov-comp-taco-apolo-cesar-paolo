package com.example.examen02

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class FMapsActivity : AppCompatActivity() {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fmaps)
        val itemId = intent.getParcelableExtra<EMusico>("objMusico")

        val textViewNombreUbicacion = findViewById<TextView>(R.id.textView3)
        textViewNombreUbicacion.text = "Ubicación de " + itemId?.name

        solicitarPermisos()

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync {
            map=it
            establecerConfiguracionMapa()
//            val latitud = -0.145522
//            val longitud = -78.503101
            val latitud = itemId?.latitud!!.toDouble()
            val longitud = itemId?.longitud!!.toDouble()

            val ubicacion = LatLng(latitud, longitud)
            val zoom = 12f
            anadirMarcador(ubicacion, itemId?.name.toString())
            moverCamaraConZoom(ubicacion, zoom)
        }

        val btnRegresar = findViewById<Button>(R.id.btn_regresar_actividad_musico)
        btnRegresar
            .setOnClickListener {
                abrirActividad(BIngresaDatosMusico::class.java)
            }
    }

    fun anadirMarcador(latLng: LatLng, title: String) {
        map.addMarker(MarkerOptions().position(latLng).title(title))
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom: Float) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))
    }

    fun establecerConfiguracionMapa() {
        val contexto = this.applicationContext
        with(map) {
            val permisosFineLocation = ContextCompat.checkSelfPermission(contexto, android.Manifest.permission.ACCESS_FINE_LOCATION)
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos) {
                map.isMyLocationEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }
    }

    fun solicitarPermisos() {
        val contexto = this.applicationContext
        val permisosFineLocation = ContextCompat.checkSelfPermission(contexto, android.Manifest.permission.ACCESS_FINE_LOCATION)
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
        if (!tienePermisos) {
            ActivityCompat.requestPermissions(
                this, arrayOf(//Arreglo Permisos
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ), 1 //cODIGO DE PETICION DE LOS PERMISOS
            )
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