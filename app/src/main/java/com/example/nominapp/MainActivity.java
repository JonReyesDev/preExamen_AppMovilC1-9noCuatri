package com.example.nominapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNumRecibo, etHorasNormales, etHorasExtra, etPuesto, etPorcentajeImpuesto;
    private TextView tvResultado, tvNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumRecibo = findViewById(R.id.etNumRecibo);
        etHorasNormales = findViewById(R.id.etHorasNormales);
        etHorasExtra = findViewById(R.id.etHorasExtra);
        etPuesto = findViewById(R.id.etPuesto);
        etPorcentajeImpuesto = findViewById(R.id.etPorcentajeImpuesto);
        tvResultado = findViewById(R.id.tvResultado);

        String nombre = getIntent().getStringExtra("nombre");
        tvNombre.setText(nombre);

        Button btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(v -> calcular());
    }

    private void calcular() {
        if (validarInputs()) {
            int numRecibo = Integer.parseInt(etNumRecibo.getText().toString());
            int horasNormales = Integer.parseInt(etHorasNormales.getText().toString());
            int horasExtra = Integer.parseInt(etHorasExtra.getText().toString());
            int puesto = Integer.parseInt(etPuesto.getText().toString());
            double porcentajeImpuesto = Double.parseDouble(etPorcentajeImpuesto.getText().toString());

            ReciboDeNomina recibo = new ReciboDeNomina(numRecibo, nombre, horasNormales, horasExtra, puesto, porcentajeImpuesto);
            double subtotal = recibo.calcularSubtotal();
            double impuesto = recibo.calcularImpuesto();
            double total = recibo.calcularTotal();

            tvResultado.setText(String.format("Subtotal: %.2f\nImpuesto: %.2f\nTotal a Pagar: %.2f", subtotal, impuesto, total));
        }
    }

    private boolean validarInputs() {
        boolean valid = true;

        if (TextUtils.isEmpty(etNumRecibo.getText())) {
            etNumRecibo.setError("Requerido");
            valid = false;
        }
        if (TextUtils.isEmpty(etHorasNormales.getText())) {
            etHorasNormales.setError("Requerido");
            valid = false;
        }
        if (TextUtils.isEmpty(etHorasExtra.getText())) {
            etHorasExtra.setError("Requerido");
            valid = false;
        }
        if (TextUtils.isEmpty(etPuesto.getText())) {
            etPuesto.setError("Requerido");
            valid = false;
        }
        if (TextUtils.isEmpty(etPorcentajeImpuesto.getText())) {
            etPorcentajeImpuesto.setError("Requerido");
            valid = false;
        }

        return valid;
    }
}

