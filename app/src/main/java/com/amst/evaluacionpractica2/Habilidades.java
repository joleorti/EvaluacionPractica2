package com.amst.evaluacionpractica2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;
import java.util.List;

public class Habilidades extends AppCompatActivity {


    public BarChart graficoBarras;
    private RequestQueue  mQueue = null;
    private String token = "2155149507954454";


    private TextView tvResultados;
    private String busqueda;
    private int totalBusqueda=0;
    private String idHeroe="";

    List<String> listHeroes = new ArrayList<String>();
    ArrayList<SuperHero> superHeroes=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habilidades);

       // idHeroe=getIntent().getStringExtra("busqueda");
        idHeroe="36";

        mQueue = Volley.newRequestQueue(this);
    }


}