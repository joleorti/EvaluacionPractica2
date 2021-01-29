package com.amst.evaluacionpractica2;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ImageButton;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonObjectRequest;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;


public class Resultados extends AppCompatActivity {

    private ListView simpleList;
    private TextView tvResultados;
    private String busqueda;
    List<String> listParadas = new ArrayList<String>();
    private RequestQueue mQueue = null;
    private String token = "2155149507954454";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        simpleList = (ListView)findViewById(R.id.listResultados);
        tvResultados = (TextView) findViewById(R.id.tvResultados);

       // busqueda=getIntent().getStringExtra("busqueda");

        busqueda="bat";

        mQueue = Volley.newRequestQueue(this);

        buscarHeroe();

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                /*
                if (userType==0){
                    String parada = adapterView.getItemAtPosition(i).toString();
                    Intent intent = new Intent(Parada.this, newParada.class);
                    intent.putExtra("isNew",false);
                    intent.putExtra("parada",parada);
                    startActivity(intent);
                    finish();
                }
*/
            }
        });


    }


    private void buscarHeroe(){
        Map<String, String> params = new HashMap();
        params.put("t", "");
        JSONObject parametros = new JSONObject(params);

        String url="https://www.superheroapi.com/api.php/"+token+"/search/"+busqueda;

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST, url, parametros,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {
                            /*
                            token = response.getString("token");
                            Intent menuPrincipal = new
                                    Intent(getBaseContext(), menu.class);
                            menuPrincipal.putExtra("token", token);
                            startActivity(menuPrincipal);

                             */

                            Toast.makeText(Resultados.this, response.toString(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                AlertDialog alertDialog = new
                        AlertDialog.Builder(Resultados.this).create();
                alertDialog.setTitle("Alerta");
                alertDialog.setMessage("Busqueda incorrecta");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int
                                    which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }

        });
        mQueue.add(request);
    }




    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Resultados.this, Inicio.class);
        startActivity(intent);
        finish();


    }


}