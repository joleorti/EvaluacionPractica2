package com.amst.evaluacionpractica2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Habilidades extends AppCompatActivity {


    public BarChart graficoBarras;
    private RequestQueue mQueue = null;
    private String token = "2155149507954454";


    private TextView tvName;
    private TextView tvFullName;
    private ImageView imvHeroe;
    private Bitmap loadedImage;

    private String idHeroe = "";

    SuperHero superHero;

    List<String> listHeroes = new ArrayList<String>();
    ArrayList<SuperHero> superHeroes = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habilidades);

        // idHeroe=getIntent().getStringExtra("busqueda");
        idHeroe = "36";

        tvName=(TextView) findViewById(R.id.tvName);
        tvFullName=(TextView) findViewById(R.id.tvFullName);
        imvHeroe=(ImageView) findViewById(R.id.imvHeroe);

        mQueue = Volley.newRequestQueue(this);

        /* GRAFICO */
        iniciarGrafico();
        getHero();
    }


    public void iniciarGrafico() {
        graficoBarras = findViewById(R.id.barChart);
        graficoBarras.getDescription().setEnabled(false);
        graficoBarras.setMaxVisibleValueCount(60);
        graficoBarras.setPinchZoom(false);
        graficoBarras.setDrawBarShadow(false);
        graficoBarras.setDrawGridBackground(true);

        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("intelligence");
        xAxisLabel.add("strength");
        xAxisLabel.add("speed");
        xAxisLabel.add("durability");
        xAxisLabel.add("power");
        xAxisLabel.add("combat");


        XAxis xAxis = graficoBarras.getXAxis();
        xAxis.setGridColor(Color.rgb(255,255,255));
        xAxis.setTextColor(Color.rgb(255,255,255));

        graficoBarras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));



        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        graficoBarras.getAxisLeft().setDrawGridLines(false);
        graficoBarras.animateY(1500);
        graficoBarras.getLegend().setEnabled(false);


    }
    private void actualizarGrafico(SuperHero superHero){


        ArrayList<BarEntry> datos = new ArrayList<>();
        try
        {

            float intelligence = Float.parseFloat(superHero.getIntelligence());
            float strength = Float.parseFloat(superHero.getStrength());
            float speed = Float.parseFloat(superHero.getSpeed());
            float durability = Float.parseFloat(superHero.getDurability());
            float power = Float.parseFloat(superHero.getPower());
            float combat = Float.parseFloat(superHero.getCombat());



            datos.add(new BarEntry(0, intelligence));
            datos.add(new BarEntry(1, strength));
            datos.add(new BarEntry(2, speed));
            datos.add(new BarEntry(3, durability));
            datos.add(new BarEntry(4, power));
            datos.add(new BarEntry(5, combat));


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error2");
        }
        System.out.println(datos);
        llenarGrafico(datos);
    }

    private void llenarGrafico(ArrayList<BarEntry> dato_hero){

        BarDataSet heroeDataSet;
        if ( graficoBarras.getData() != null && graficoBarras.getData().getDataSetCount() > 0) {
            heroeDataSet = (BarDataSet)
            graficoBarras.getData().getDataSetByIndex(0);
            heroeDataSet.setValues(dato_hero);
            graficoBarras.getData().notifyDataChanged();
            graficoBarras.notifyDataSetChanged();
        } else {
            heroeDataSet = new BarDataSet(dato_hero, "Data Set");
            heroeDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
            heroeDataSet.setDrawValues(true);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(heroeDataSet);
            BarData data = new BarData(dataSets);
            graficoBarras.setData(data);
            graficoBarras.setFitBars(true);
        }
        graficoBarras.invalidate();

 }



    private void getHero(){

        String url=" https://www.superheroapi.com/api.php/"+token+"/"+idHeroe;

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {


                            SuperHero superHero = new SuperHero();
                            superHero.setId(response.getString("id"));
                            superHero.setName(response.getString("name"));
                            superHero.setFullname(((JSONObject)response.get("biography")).getString("full-name"));

                          //  getImage(((JSONObject)response.get("image")).getString("url"));

                            JSONObject powerstats= (JSONObject) response.get("powerstats");

                            superHero.setIntelligence(powerstats.getString("intelligence"));
                            superHero.setStrength(powerstats.getString("strength"));
                            superHero.setSpeed(powerstats.getString("speed"));
                            superHero.setDurability(powerstats.getString("durability"));
                            superHero.setPower(powerstats.getString("power"));
                            superHero.setCombat(powerstats.getString("combat"));


                            tvName.setText(superHero.getName());
                            tvFullName.setText(superHero.getFullname());

                            actualizarGrafico(superHero);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "JWT " + token);
                System.out.println(token);
                return params;
            }
        };;
        mQueue.add(request);

    }
    void getImage(String imageHttpAddress) {
        URL imageUrl = null;
        imageHttpAddress = imageHttpAddress.replace("\\", "");
        try {
            imageUrl = new URL(imageHttpAddress);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();
            loadedImage = BitmapFactory.decodeStream(conn.getInputStream());
            imvHeroe.setImageBitmap(loadedImage);
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Error cargando la imagen: "+e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}