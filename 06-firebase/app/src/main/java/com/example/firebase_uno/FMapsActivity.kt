package com.example.firebase_uno

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class FMapsActivity : AppCompatActivity() {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fmaps)

        val btnCarolina = findViewById<Button>(R.id.btn_carolina)
        btnCarolina.setOnClickListener {
            val carolina = LatLng(-0.18288452555103193, -78.48449971346241)
            val zoom = 17f
            val titulo = "Carolina"
            moverCamaraConZoom(carolina, zoom)
            anadirMarcador(carolina, titulo)
        }

        val fragmentMap = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        fragmentMap.getMapAsync{ googleMap ->
            if(googleMap != null){
                map = googleMap
                establecerConfiguracionMapa()
                val quicentro = LatLng(-0.176125, -78.480208)
                val titulo = "Quicentro"
                val zoom = 17f
                anadirMarcador(quicentro, titulo)
                moverCamaraConZoom(quicentro, zoom)

                //LINEA
                val poligonoLineaUno = googleMap
                    .addPolyline(
                        PolylineOptions()
                            .clickable(true)
                            .add(
                                LatLng(-0.1759187040647396, -78.48506472421384),
                                LatLng(-0.17632468492901104, -78.48265589308046),
                                LatLng(-0.17746143130181483, -78.4770533307815)
                            )
                    )
                poligonoLineaUno.tag = "Linea1" //ID

                //POLIGONO
                val poligonoUno = googleMap
                    .addPolygon(
                        PolygonOptions()
                            .clickable(true)
                            .add(
                                LatLng(-0.1771546902239471, -78.48344981495214),
                                LatLng(-0.17968981486125768, -78.48269198043828),
                                LatLng(-0.17710958124147777, -78.48142892291516)
                            )
                    )
                poligonoUno.fillColor = -0xc771c4
                poligonoUno.tag = "poligono-2" // ID

                escucharListeners()

            }
        }
    }

    fun escucharListeners(){
        map.setOnPolygonClickListener {
            Log.i("mapa", "PolygonClickListener ${it}")
        }
        map.setOnPolylineClickListener {
            Log.i("mapa", "PolyLineClickListener ${it}")
        }
        map.setOnMarkerClickListener {
            Log.i("mapa", "MarkerClickListener ${it}")
            return@setOnMarkerClickListener true
        }
        map.setOnCameraMoveListener {
            Log.i("mapa", "CameraMoveListener")
        }
        map.setOnCameraMoveStartedListener {
            Log.i("mapa", "CameraMoveStartedListener ${it}")
        }
        map.setOnCameraIdleListener {
            Log.i("mapa", "CameraIdleListener")
        }
    }

    fun anadirMarcador(LatLng: LatLng, title: String){
        map.addMarker(
            MarkerOptions()
                .position(LatLng)
                .title(title)
        )
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom: Float = 10f){
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))
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