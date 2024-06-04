package com.example.nominapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AccessActivity extends AppCompatActivity {

    private EditText etNombreAcceso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        etNombreAcceso = findViewById(R.id.etNombreAcceso);
        Button btnIngresar = findViewById(R.id.btnIngresar);
        Button btnSalir = findViewById(R.id.btnSalir);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etNombreAcceso.getText())) {
                    Intent intent = new Intent(AccessActivity.this, MainActivity.class);
                    intent.putExtra("nombre", etNombreAcceso.getText().toString());
                    startActivity(intent);
                } else {
                    etNombreAcceso.setError("Requerido");
                }
            }
        });
        btnSalir.setOnClickListener(v -> finishAffinity());
    }
}



