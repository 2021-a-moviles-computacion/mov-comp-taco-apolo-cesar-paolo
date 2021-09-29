package com.plcoding.spotifycloneyt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_principal)
        val navegacion = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val firstFragment = Home()
        val secondFragment = Search()
        val thirdFragment = Library()
        establecerFragmento(firstFragment)
        if (navegacion != null) {
            navegacion.setOnNavigationItemSelectedListener {
                when (it.itemId) {
//                    R.id.fragment_home -> establecerFragmento(firstFragment)
//                    R.id.fragment_search -> establecerFragmento(secondFragment)
//                    R.id.fragment_library -> establecerFragmento(thirdFragment)
                }
                true
            }
//            navegacion.setOnItemSelectedListener() {
//
//            }
        } else {
            Log.i("menu", "esta vacio")
        }
    }

    fun establecerFragmento(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_container, fragment).commit()
        }
}