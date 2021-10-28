package com.dam2.mariasimonedice

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.nio.channels.spi.AbstractSelectionKey


class MainActivity : AppCompatActivity() {
    var contadorRonda: Int = 0
    var sec = arrayListOf<String>("", "", "", "")
    var comprobacion = arrayListOf<String>("", "", "", "")
    var posicion: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonCom: Button = findViewById(R.id.comprobar)
        botonCom.setVisibility(View.GONE)


        val botonInicio: Button = findViewById(R.id.inicio)
        botonInicio.setOnClickListener() {

            Toast.makeText(applicationContext, "COMIENZA", Toast.LENGTH_SHORT).show()
            botonInicio.setText("NUEVA RONDA")
            empezarJuego()

        }
        val botonRojo: Button = findViewById(R.id.rojo)
        botonRojo.setOnClickListener() {
            Toast.makeText(applicationContext, "Rojo", Toast.LENGTH_SHORT).show()
        }
        val botonVerde: Button = findViewById(R.id.verde)
        botonVerde.setOnClickListener() {
            Toast.makeText(applicationContext, "Verde", Toast.LENGTH_SHORT).show()
        }
        val botonAzul: Button = findViewById(R.id.azul)
        botonAzul.setOnClickListener() {
            Toast.makeText(applicationContext, "Azul", Toast.LENGTH_SHORT).show()
        }
        val botonAmarillo: Button = findViewById(R.id.amarillo)
        botonAmarillo.setOnClickListener() {
            Toast.makeText(applicationContext, "Amarilolo", Toast.LENGTH_SHORT).show()
        }
        val botonReinicio: Button = findViewById(R.id.reinicio)
        botonReinicio.setOnClickListener() {

            Toast.makeText(applicationContext, "REINICIADO", Toast.LENGTH_SHORT).show()
            reinicio()
        }
    }

    private fun empezarJuego() {
        mostrarRonda()
        val secuencia = GlobalScope.launch(Dispatchers.Main) {
            ejecutarSecuencia()
        }
        tuTurno()

        val botonCom: Button = findViewById(R.id.comprobar)
        botonCom.setOnClickListener() {
            compruebo()
        }

    }

    private fun mostrarRonda() {
        contadorRonda = contadorRonda + 1
        val textoRonda: TextView = findViewById(R.id.texto_ronda)
        textoRonda.setText("RONDA:" + "  " + contadorRonda.toString())
        val botonCom: Button = findViewById(R.id.comprobar)
        botonCom.setVisibility(View.GONE)
    }


    suspend fun ejecutarSecuencia() {
        for (i in 0..3) {
            suspend fun azul1() {
                val botonAzul: Button = findViewById(R.id.azul)
                botonAzul.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
                delay(500L)
                botonAzul.setBackgroundColor(Color.parseColor("#00BCD4"))
                delay(500L)
                sec.set(i, "azul")

            }
            suspend fun rojo1() {
                val botonRojo: Button = findViewById(R.id.rojo)
                botonRojo.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
                delay(500L)
                botonRojo.setBackgroundColor(Color.parseColor("#BA0B0B"))
                delay(500L)
                sec.set(i, "rojo")
            }

            suspend fun amarillo1() {
                val botonAmarillo: Button = findViewById(R.id.amarillo)
                botonAmarillo.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
                delay(500L)
                botonAmarillo.setBackgroundColor(Color.parseColor("#CAB81A"))
                delay(500L)
                sec.set(i, "amarillo")
            }

            suspend fun verde1() {
                val botonVerde: Button = findViewById(R.id.verde)
                botonVerde.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
                delay(500L)
                botonVerde.setBackgroundColor(Color.parseColor("#10C617"))
                delay(500L)
                sec.set(i, "verde")
            }

            val cadena = listOf(1, 2, 3, 4)
            val colores = cadena.shuffled().last()
            val dR = when (colores) {
                1 -> amarillo1()
                2 -> verde1()
                3 -> rojo1()
                else -> azul1()
            }
        }
        val texto: TextView = findViewById(R.id.mensajePartida)
        texto.isVisible
        texto.setText("TU TURNO!!!!!!!!")
        val botonCom: Button = findViewById(R.id.comprobar)
        botonCom.setVisibility(View.VISIBLE)


    }

    private fun tuTurno() {

            val botonRojo: Button = findViewById(R.id.rojo)
            botonRojo.setOnClickListener() {

                comprobacion.set(posicion, "rojo")
                posicion = posicion + 1
            }
            val botonVerde: Button = findViewById(R.id.verde)
            botonVerde.setOnClickListener() {

                comprobacion.set(posicion, "verde")
                posicion = posicion + 1
            }
            val botonAzul: Button = findViewById(R.id.azul)
            botonAzul.setOnClickListener() {

                comprobacion.set(posicion, "azul")
                posicion = posicion + 1
            }
            val botonAmarillo: Button = findViewById(R.id.amarillo)
            botonAmarillo.setOnClickListener() {

                comprobacion.set(posicion, "amarillo")
                posicion = posicion + 1

        }
        if(posicion==4){
            posicion = 0
        }
    }

    fun compruebo() {
        val botonCom: Button = findViewById(R.id.comprobar)

        if (comprobacion == sec) {
            Toast.makeText(applicationContext, "GANATE WACHOOOOOOOOO", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(
                applicationContext,
                "DESCANSA AQUI CABALLERO LE VEO CANSADO, INTENTELO LUEGO",
                Toast.LENGTH_SHORT
            ).show()
        }
        println(sec)
        println(comprobacion)

    }

    private fun reinicio() {
        val textoRonda: TextView = findViewById(R.id.texto_ronda)
        textoRonda.setText("RONDA:")
        val botonInicio: Button = findViewById(R.id.inicio)
        botonInicio.setText("INICIO")
        posicion = 0
        val texto: TextView = findViewById(R.id.mensajePartida)
        texto.setText("")
        val botonCom: Button = findViewById(R.id.comprobar)
        botonCom.setVisibility(View.GONE)
        for (i in 0..3) {
            sec.set(i," ")
        }
        for (i in 0..3) {
            comprobacion.set(i," ")
        }
        posicion = 0
    }
}
