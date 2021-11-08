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
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE
import java.nio.channels.spi.AbstractSelectionKey


class MainActivity : AppCompatActivity() {
    var contadorRonda: Int = 0
    var sec = arrayListOf<String>()
    var comprobacion = arrayListOf<String>()
    var retraso = 1000L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pollo:ImageView= findViewById(R.id.indice)
        pollo.setVisibility(GONE)

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
        val botonAzul: Button = findViewById(R.id.azul)
        val botonRojo: Button = findViewById(R.id.rojo)
        val botonAmarillo: Button = findViewById(R.id.amarillo)
        val botonVerde: Button = findViewById(R.id.verde)
        for (i in 1..contadorRonda) {
            suspend fun azul1() {
                botonAzul.setBackgroundColor(Color.parseColor("#18C4DA"))
                delay(retraso)
                botonAzul.setBackgroundColor(Color.parseColor("#06525C"))
                delay(retraso)
                sec.add("azul")
            }
            suspend fun rojo1() {
                botonRojo.setBackgroundColor(Color.parseColor("#BA0B0B"))
                delay(retraso)
                botonRojo.setBackgroundColor(Color.parseColor("#630404"))
                delay(retraso)
                sec.add("rojo")
            }
            suspend fun amarillo1() {
                botonAmarillo.setBackgroundColor(Color.parseColor("#FDE403"))
                delay(retraso)
                botonAmarillo.setBackgroundColor(Color.parseColor("#A19206"))
                delay(retraso)
                sec.add("amarillo")
            }
            suspend fun verde1() {
                botonVerde.setBackgroundColor(Color.parseColor("#0CDC14"))
                delay(retraso)
                botonVerde.setBackgroundColor(Color.parseColor("#046A07"))
                delay(retraso)
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
        retraso = retraso - contadorRonda - 100
        botonAzul.setEnabled(true)
        botonVerde.setEnabled(true)
        botonAmarillo.setEnabled(true)
        botonRojo.setEnabled(true)
        delay(500L)
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
        botonAmarillo.setEnabled(false);
        botonAzul.setEnabled(false);
        botonVerde.setEnabled(false);
        botonRojo.setEnabled(false);
    }
    fun compruebo() {
        val botonCom: Button = findViewById(R.id.comprobar)
        if (comprobacion == sec) {
            Toast.makeText(applicationContext, "PASATE DE RONDITA AHORA A LA SIGUIENTE ROLA :)", Toast.LENGTH_SHORT).show()
            if (contadorRonda==5){
                val premios = GlobalScope.launch(Dispatchers.Main) {
                    premio()
                    }
            }
            if (contadorRonda==10){
                val premios = GlobalScope.launch(Dispatchers.Main) {
                    premio2()
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
        val botonInicio: Button = findViewById(R.id.inicio)
        botonInicio.setVisibility(View.GONE)
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
        botonInicio.setVisibility(View.VISIBLE)
        premio.setVisibility(GONE)
    }
    suspend fun premio2() {
        val botonInicio: Button = findViewById(R.id.inicio)
        botonInicio.setVisibility(View.GONE)
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
        val premio: ImageView = findViewById(R.id.indice)
        premio.setVisibility(VISIBLE)
        delay(8000L)
        botonAzul.setVisibility(View.VISIBLE)
        botonAmarillo.setVisibility(View.VISIBLE)
        botonVerde.setVisibility(View.VISIBLE)
        botonRojo.setVisibility(View.VISIBLE)
        botonInicio.setVisibility(View.VISIBLE)
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
