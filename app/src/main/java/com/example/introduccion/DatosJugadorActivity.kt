package com.example.introduccion

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_datos_jugador.*

class DatosJugadorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_jugador)
        setSupportActionBar(toolbar)
//enviar info de jugador
        val numeroJugador = intent.extras?.getString("numeroJugador", "")
        val nombre = intent.extras?.getString("nombre", "")
        val Equipo = intent.extras?.getString("Equipo","")
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Enviar datos de registro seleccionado?", Snackbar.LENGTH_LONG)
                    .setAction("Enviar", View.OnClickListener {
                        EnviarInfo(numeroJugador.toString(),nombre.toString(),Equipo.toString())
                    }).show()
        }



        //Toast.makeText(this, "${numeroJugador} ${nombre}", Toast.LENGTH_LONG).show()
    }
        //implicito
    private fun EnviarInfo(numero:String, nombre:String, Equipo: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Envio registro de Jugador ${nombre} #${numero} de ${Equipo}")
            type = "text/plain"
        }

        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(sendIntent)
        }
    }

}
