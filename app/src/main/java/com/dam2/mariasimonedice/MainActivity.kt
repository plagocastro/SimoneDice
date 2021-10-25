package com.dam2.mariasimonedice

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
    private fun empezarJuego() {
        mostrarRonda()
        val secuencia = GlobalScope.launch(Dispatchers.Main) {
            ejecutarSecuencia()
        }
    }
    private fun mostrarRonda(){
        contadorRonda = contadorRonda + 1
        val textoRonda:TextView = findViewById(R.id.texto_ronda)
        textoRonda.setText("RONDA:"+"  "+contadorRonda.toString())
        }


    suspend fun ejecutarSecuencia(){


        for(i in 1..4){

            suspend fun azul1(){
                val botonAzul:Button = findViewById(R.id.azul)
                botonAzul.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
                delay(500L)
                botonAzul.setBackgroundColor(Color.parseColor("#00BCD4"))
                delay(500L)
            }
            suspend fun rojo1(){
                val botonRojo:Button = findViewById(R.id.rojo)
                botonRojo.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
                delay(500L)
                botonRojo.setBackgroundColor(Color.parseColor("#BA0B0B"))
                delay(500L)
            }
            suspend fun amarillo1(){
                val botonAmarillo:Button = findViewById(R.id.amarillo)
                botonAmarillo.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
                delay(500L)
                botonAmarillo.setBackgroundColor(Color.parseColor("#CAB81A"))
                delay(500L)
            }
            suspend fun verde1(){
                val botonVerde:Button = findViewById(R.id.verde)
                botonVerde.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
                delay(500L)
                botonVerde.setBackgroundColor(Color.parseColor("#10C617"))
                delay(500L)
            }
            val cadena = listOf(1,2,3,4)
            val colores = cadena.shuffled().last()
            val dR = when (colores) {
                1 -> amarillo1()
                2 -> verde1()
                3 -> rojo1()
                else -> azul1()
            }
        }
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

/*

    }


 */
