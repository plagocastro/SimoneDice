package com.dam2.mariasimonedice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.nio.channels.spi.AbstractSelectionKey

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonInicio:Button = findViewById(R.id.inicio)
        botonInicio.setOnClickListener(){
            empezarJuego()
        }
        val botonRojo:Button = findViewById(R.id.rojo)
        val botonVerde:Button = findViewById(R.id.verde)
        val botonAzul:Button = findViewById(R.id.azul)
        val botonAmarillo:Button = findViewById(R.id.amarillo)
    }
    private fun empezarJuego(){
        mostrarRonda()
        ejecutarSecuencia()
    }
    private fun mostrarRonda(){
        val contadorRonda:Int =0

        }
    private fun ejecutarSecuencia(){
        }
    private fun mensajeusuario(key:Int){

    }
    }

