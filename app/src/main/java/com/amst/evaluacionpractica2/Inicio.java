package com.amst.evaluacionpractica2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Inicio extends AppCompatActivity {
Button btnBuscar;
EditText edtBusqueda;
String busqueda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        btnBuscar = findViewById(R.id.btnBuscar);
        edtBusqueda = findViewById(R.id.edtBusqueda);
    }

    public void Buscar(View v) {
        Intent i= new Intent(Inicio.this, Resultados.class);
        busqueda=edtBusqueda.getText().toString();
        i.putExtra("busqueda",busqueda);
        startActivity(i);
        finish();

    }


    @Override
    public void onBackPressed() {

    }

}