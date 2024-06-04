package com.example.nominapp;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNumRecibo, etHorasNormales, etHorasExtra;
    private EditText etSubtotal, etImpuesto, etTotalPagar;
    private TextView tvNombre;
    private RadioGroup rgPuesto;
    private RadioButton rbAuxiliar, rbAlbanil, rbIngObra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumRecibo = findViewById(R.id.etNumRecibo);
        etHorasNormales = findViewById(R.id.etHorasNormales);
        etHorasExtra = findViewById(R.id.etHorasExtra);
        etSubtotal = findViewById(R.id.etSubtotal);
        etImpuesto = findViewById(R.id.etImpuesto);
        etTotalPagar = findViewById(R.id.etTotalPagar);
        tvNombre = findViewById(R.id.tvNombre);

        rgPuesto = findViewById(R.id.rgPuesto);
        rbAuxiliar = findViewById(R.id.rbAuxiliar);
        rbAlbanil = findViewById(R.id.rbAlbanil);
        rbIngObra = findViewById(R.id.rbIngObra);

        String nombre = getIntent().getStringExtra("nombre");
        tvNombre.setText(nombre);

        Button btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(v -> calcular());

        Button btnLimpiar = findViewById(R.id.btnLimpiar);
        btnLimpiar.setOnClickListener(v -> limpiar());

        Button btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(v -> regresar());
    }

    private void calcular() {
        if (validarInputs()) {
            int numRecibo = Integer.parseInt(etNumRecibo.getText().toString());
            int horasNormales = Integer.parseInt(etHorasNormales.getText().toString());
            int horasExtra = Integer.parseInt(etHorasExtra.getText().toString());
            int puesto = obtenerPuestoSeleccionado();

            ReciboDeNomina recibo = new ReciboDeNomina(numRecibo, tvNombre.getText().toString(), horasNormales, horasExtra, puesto);
            double subtotal = recibo.calcularSubtotal();
            double impuesto = recibo.calcularImpuesto();
            double total = recibo.calcularTotal();

            etSubtotal.setText(String.format("%.2f", subtotal));
            etImpuesto.setText(String.format("%.2f", impuesto));
            etTotalPagar.setText(String.format("%.2f", total));
        }
    }

    private int obtenerPuestoSeleccionado() {
        int selectedId = rgPuesto.getCheckedRadioButtonId();
        if (selectedId == R.id.rbAuxiliar) {
            return 1;
        } else if (selectedId == R.id.rbAlbanil) {
            return 2;
        } else if (selectedId == R.id.rbIngObra) {
            return 3;
        }
        return 0;
    }

    private void limpiar() {
        etNumRecibo.setText("");
        etHorasNormales.setText("");
        etHorasExtra.setText("");
        rgPuesto.clearCheck();
        etSubtotal.setText("");
        etImpuesto.setText("");
        etTotalPagar.setText("");
    }

    private void regresar() {
        finish();
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
        if (rgPuesto.getCheckedRadioButtonId() == -1) {
            rbAuxiliar.setError("Requerido");
            valid = false;
        }

        return valid;
    }
}
