package com.amst.evaluacionpractica2;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ImageButton;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;


public class Resultados extends AppCompatActivity {

    private ListView simpleList;
    private TextView tvResultados;
    private String busqueda;
    List<String> listParadas = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        simpleList = (ListView)findViewById(R.id.listResultados);
        tvResultados = (TextView) findViewById(R.id.tvResultados);

       // busqueda=getIntent().getStringExtra("busqueda");

        busqueda="bat";







        /*

        db_reference.child("Rutas").child("Alban Borja").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    listParadas.add(snapshot.getKey());
                }

                if(listParadas!=null || listParadas.size()!=0) {
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Parada.this, R.layout.activity_listview, R.id.textView, listParadas);
                    simpleList.setAdapter(arrayAdapter);
                }
                else {
                    Toast.makeText(Parada.this, "Lista vacía", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(Parada.this, "Error al obtener lista", Toast.LENGTH_SHORT).show();
            }
        });*/




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





    @Override
    public void onBackPressed() {
/*
        Intent intent = new Intent(Parada.this, Mapa.class);
        startActivity(intent);
        finish();

 */
    }


}