package com.dam2.mariasimonedice

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ImageView
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
    var sec = arrayListOf<String>()
    var comprobacion = arrayListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonCom: Button = findViewById(R.id.comprobar)
        botonCom.setVisibility(View.GONE)

        val premio: ImageView = findViewById(R.id.mono)
        premio.setVisibility(GONE)

        val botonInicio: Button = findViewById(R.id.inicio)
        botonInicio.setOnClickListener() {

            Toast.makeText(applicationContext, "COMIENZA", Toast.LENGTH_SHORT).show()
            botonInicio.setText("NUEVA RONDA")
            empezarJuego()
            botonInicio.setVisibility(GONE)
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
            botonCom.setVisibility(GONE)

        }
        sec.clear()
        comprobacion.clear()
    }

    private fun mostrarRonda() {
        contadorRonda = contadorRonda + 1
        val textoRonda: TextView = findViewById(R.id.texto_ronda)
        textoRonda.setText("RONDA:" + "  " + contadorRonda.toString())
        val botonCom: Button = findViewById(R.id.comprobar)
        botonCom.setVisibility(View.GONE)
    }


    suspend fun ejecutarSecuencia() {

        for (i in 1..contadorRonda) {
            suspend fun azul1() {
                val botonAzul: Button = findViewById(R.id.azul)
                botonAzul.setBackgroundColor(Color.parseColor("#18C4DA"))
                delay(500L)
                botonAzul.setBackgroundColor(Color.parseColor("#06525C"))
                delay(500L)
                sec.add("azul")

            }
            suspend fun rojo1() {
                val botonRojo: Button = findViewById(R.id.rojo)
                botonRojo.setBackgroundColor(Color.parseColor("#BA0B0B"))
                delay(500L)
                botonRojo.setBackgroundColor(Color.parseColor("#630404"))
                delay(500L)
                sec.add("rojo")
            }

            suspend fun amarillo1() {
                val botonAmarillo: Button = findViewById(R.id.amarillo)
                botonAmarillo.setBackgroundColor(Color.parseColor("#FDE403"))
                delay(500L)
                botonAmarillo.setBackgroundColor(Color.parseColor("#A19206"))
                delay(500L)
                sec.add("amarillo")
            }

            suspend fun verde1() {
                val botonVerde: Button = findViewById(R.id.verde)
                botonVerde.setBackgroundColor(Color.parseColor("#0CDC14"))
                delay(500L)
                botonVerde.setBackgroundColor(Color.parseColor("#046A07"))
                delay(500L)
                sec.add("verde")
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
        Toast.makeText(applicationContext, "TU TURNO", Toast.LENGTH_SHORT).show()
        val botonCom: Button = findViewById(R.id.comprobar)
        botonCom.setVisibility(View.VISIBLE)


    }

    private fun tuTurno() {

            val botonRojo: Button = findViewById(R.id.rojo)
            botonRojo.setOnClickListener() {

                comprobacion.add("rojo")
            }
            val botonVerde: Button = findViewById(R.id.verde)
            botonVerde.setOnClickListener() {

                comprobacion.add("verde")
            }
            val botonAzul: Button = findViewById(R.id.azul)
            botonAzul.setOnClickListener() {

                comprobacion.add("azul")
            }
            val botonAmarillo: Button = findViewById(R.id.amarillo)
            botonAmarillo.setOnClickListener() {

                comprobacion.add("amarillo")
        }
    }

    fun compruebo() {
        val botonCom: Button = findViewById(R.id.comprobar)

        if (comprobacion == sec) {
            Toast.makeText(applicationContext, "GANATE WACHOOOOOOOOO", Toast.LENGTH_SHORT).show()
            if (contadorRonda==5){
            val premio = GlobalScope.launch(Dispatchers.Main) {
                premio()
            }
            }
            val botonInicio: Button = findViewById(R.id.inicio)
            botonInicio.setVisibility(VISIBLE)
        } else {
            Toast.makeText(
                applicationContext,
                "DESCANSA AQUI CABALLERO LE VEO CANSADO, INTENTELO LUEGO",Toast.LENGTH_SHORT).show()
                 val botonInicio: Button = findViewById(R.id.inicio)
                botonInicio.setVisibility(View.GONE)
        }
        println(sec)
        println(comprobacion)
    }
    suspend fun premio() {
        val botonCom: Button = findViewById(R.id.comprobar)
        botonCom.setVisibility(View.GONE)
        val botonRojo: Button = findViewById(R.id.rojo)
        botonRojo.setVisibility(View.GONE)
        val botonVerde: Button = findViewById(R.id.verde)
        botonVerde.setVisibility(View.GONE)
        val botonAmarillo: Button = findViewById(R.id.amarillo)
        botonAmarillo.setVisibility(View.GONE)
        val botonAzul: Button = findViewById(R.id.azul)
        botonAzul.setVisibility(View.GONE)
        val premio: ImageView = findViewById(R.id.mono)
        premio.setVisibility(VISIBLE)
        delay(8000L)
        botonAzul.setVisibility(View.VISIBLE)
        botonAmarillo.setVisibility(View.VISIBLE)
        botonVerde.setVisibility(View.VISIBLE)
        botonRojo.setVisibility(View.VISIBLE)
        botonCom.setVisibility(View.VISIBLE)
        premio.setVisibility(GONE)
    }

    private fun reinicio() {
        val textoRonda: TextView = findViewById(R.id.texto_ronda)
        textoRonda.setText("RONDA:")
        val botonInicio: Button = findViewById(R.id.inicio)
        botonInicio.setVisibility(VISIBLE)
        botonInicio.setText("INICIO")
        val texto: TextView = findViewById(R.id.mensajePartida)
        texto.setText("")
        val botonCom: Button = findViewById(R.id.comprobar)
        botonCom.setVisibility(View.GONE)
        sec.clear()
        comprobacion.clear()
        contadorRonda=0
    }
}
