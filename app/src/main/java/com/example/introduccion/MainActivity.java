package com.example.introduccion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etCantidad;
    EditText etTipoCambio;
    TextView tvResultado;
    RadioGroup rgMoneda;
    ImageView ivBanderas;
    CheckBox cbMostrarImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ctrl + P -> VER PARÁMETROS
        //MANDA EL MENSAJE AL LOGCAT
        Log.e("TAG","Paso por onCreate");

        etCantidad = findViewById(R.id.edtCantidad);
        etTipoCambio = findViewById(R.id.edtTipoCambio);
        tvResultado = findViewById(R.id.tvResultado);
        ivBanderas = findViewById(R.id.ivBanderas);
        rgMoneda = findViewById(R.id.rgMoneda);
        cbMostrarImagen = findViewById(R.id.cbMostrarImagen);

        rgMoneda.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (etCantidad.getText().toString().isEmpty()) {
                    etCantidad.setText("1");
                }
                if (etTipoCambio.getText().toString().isEmpty()) {
                    etTipoCambio.setText("1");
                }
                double dblCantidad = 0;
                double dblTipoCambio = 0;
                double dblResultado = 0;

                dblCantidad = Double.parseDouble(etCantidad.getText().toString());
                dblTipoCambio = Double.parseDouble(etTipoCambio.getText().toString());

                switch (checkedId) {
                    case R.id.rbPesoDolares:
                        dblResultado = dblCantidad / dblTipoCambio;
                        ivBanderas.setImageResource(R.drawable.pesos_dolares);
                        break;

                    default:
                        dblResultado = dblCantidad * dblTipoCambio;
                        ivBanderas.setImageResource(R.drawable.dolares_pesos);
                        break;
                }
                tvResultado.setText("Resultado: " + dblResultado);
            }
        });

        cbMostrarImagen.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ivBanderas.setVisibility(View.VISIBLE);
                } else {
                    ivBanderas.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.e("TAG","Paso por onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.e("TAG","Paso por onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.e("TAG","Paso por onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.e("TAG","Paso por onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.e("TAG","Paso por onDestroy");
    }
}