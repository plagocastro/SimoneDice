package com.dam2.mariasimonedice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.nio.channels.spi.AbstractSelectionKey



class MainActivity : AppCompatActivity() {var contadorRonda:Int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonInicio:Button = findViewById(R.id.inicio)
        botonInicio.setOnClickListener(){

            Toast.makeText(applicationContext,"COMIENZA", Toast.LENGTH_SHORT).show()
            botonInicio.setText("NUEVA RONDA")
            empezarJuego()

        }
        val botonRojo:Button = findViewById(R.id.rojo)
        botonRojo.setOnClickListener(){
            Toast.makeText(applicationContext,"Rojo", Toast.LENGTH_SHORT).show()
        }
        val botonVerde:Button = findViewById(R.id.verde)
        botonVerde.setOnClickListener(){
            Toast.makeText(applicationContext,"Verde", Toast.LENGTH_SHORT).show()
        }
        val botonAzul:Button = findViewById(R.id.azul)
        botonAzul.setOnClickListener(){
            Toast.makeText(applicationContext,"Azul", Toast.LENGTH_SHORT).show()
        }
        val botonAmarillo:Button = findViewById(R.id.amarillo)
        botonAmarillo.setOnClickListener(){
            Toast.makeText(applicationContext,"Amarilolo", Toast.LENGTH_SHORT).show()
        }
        val botonReinicio:Button = findViewById(R.id.reinicio)
        botonReinicio.setOnClickListener(){

            Toast.makeText(applicationContext,"REINICIADO", Toast.LENGTH_SHORT).show()
            reinicio()
        }
    }
    private fun empezarJuego(){
        mostrarRonda()
        ejecutarSecuencia()
    }
    private fun mostrarRonda(){
        contadorRonda = contadorRonda + 1
        val textoRonda:TextView = findViewById(R.id.texto_ronda)
        textoRonda.setText("RONDA:"+"  "+contadorRonda.toString())
        }
    private fun ejecutarSecuencia(){
        val azul = Toast.makeText(applicationContext,"Azul", Toast.LENGTH_SHORT).show()
        val verde = Toast.makeText(applicationContext,"Verde", Toast.LENGTH_SHORT).show()
        val amarillo = Toast.makeText(applicationContext,"Amarilolo", Toast.LENGTH_SHORT).show()
        val rojo = Toast.makeText(applicationContext,"Rojo", Toast.LENGTH_SHORT).show()
        val cadena = listOf(azul,verde,rojo,amarillo)
        val colores = cadena.shuffled()
        }
    private fun mensajeusuario(key:Int){

    }
    private fun reinicio(){
        val textoRonda:TextView = findViewById(R.id.texto_ronda)
        textoRonda.setText("RONDA:")
        val botonInicio:Button = findViewById(R.id.inicio)
        botonInicio.setText("INICIO")
    }
    }

