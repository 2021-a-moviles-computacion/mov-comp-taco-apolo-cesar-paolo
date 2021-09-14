package com.example.firebase_uno

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment

class FMapsActivity : AppCompatActivity() {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fmaps)
        val fragmentMap = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        fragmentMap.getMapAsync{ googleMap ->
            if(googleMap != null){
                map = googleMap
                establecerConfiguracionMapa()
            }
        }
    }

    fun establecerConfiguracionMapa(){

        val context = this.applicationContext
        with(map){
            val permisosFindLocation = ContextCompat
                .checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)
            val tienePermisos = permisosFindLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos){
                map.isMyLocationEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }

    }
}