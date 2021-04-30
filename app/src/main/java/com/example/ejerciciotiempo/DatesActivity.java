package com.example.ejerciciotiempo;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejerciciotiempo.Modelo.City;
import com.example.ejerciciotiempo.Persistencia.Database;

import java.util.List;

public class DatesActivity extends Activity {


    private ImageButton btnBack;
    private TextView txtConsulta;
    City datosCity;

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter; // ahora mAdapter hace referencia a mi clase adaptador.(activity MyAdapter)
    private RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates);

        btnBack = (ImageButton) findViewById(R.id.btnBack);
        txtConsulta = (TextView) findViewById(R.id.txtConsulta);
        btnBack.setOnClickListener(this::back);

        Database db = Room
                .databaseBuilder(DatesActivity.this,
                        Database.class, "Database1").allowMainThreadQueries()
                .build();

        List<City> list = db.cityDaodb().getAll();
//        Toast.makeText(this,"Conexion establecida",Toast.LENGTH_LONG).show();
//        String resultado = "";
//        for (int i=0;i<list.size();i++){
//            resultado+="N°: "+list.get(i).getCityId()+" "+
//                    " Nombre: "+ list.get(i).getName()+
//                    " Temperatura: "+ list.get(i).getTemp()+"°C"+
//                    " Humedad: "+ list.get(i).getHumidity()+"%"+
//                    " Fecha Datos: " + list.get(i).getDate()+"\n";
//        }
//        System.out.println(resultado);
//        txtConsulta.setText(resultado);
//        System.out.println(list.get(0).getTemp());
//        db.close();
//
//        List<City> list = db.cityDaodb().getAll();
//
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MyAdapter(list, R.layout.recycler_view_item) {};
        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }


    public void back(View view){
        Intent intent = new Intent(DatesActivity.this, PrincipalActivity.class);
        startActivity(intent);
    }
}