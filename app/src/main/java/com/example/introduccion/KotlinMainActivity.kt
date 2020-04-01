package com.example.introduccion

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_kotlin_main.*

class KotlinMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)

        rgMoneda.setOnCheckedChangeListener { group, checkedId ->
            if (edtCantidad.text.toString().isEmpty()) {
                edtCantidad.setText("1")
            }
            if (edtTipoCambio.text.toString().isEmpty()) {
                edtTipoCambio.setText("1")
            }
            var dblCantidad = 0.0
            var dblTipoCambio = 0.0
            var dblResultado = 0.0
            dblCantidad = edtCantidad.text.toString().toDouble()
            dblTipoCambio = edtTipoCambio.text.toString().toDouble()
            when (checkedId) {
                R.id.rbPesoDolares -> {
                    dblResultado = dblCantidad / dblTipoCambio
                    ivBanderas.setImageResource(R.drawable.pesos_dolares)
                }
                else -> {
                    dblResultado = dblCantidad * dblTipoCambio
                    ivBanderas.setImageResource(R.drawable.dolares_pesos)
                }
            }
            tvResultado.text = "Resultado: $dblResultado"
        }

        cbMostrarImagen.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                ivBanderas.visibility = View.VISIBLE
            } else {
                ivBanderas.visibility = View.GONE
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }
}