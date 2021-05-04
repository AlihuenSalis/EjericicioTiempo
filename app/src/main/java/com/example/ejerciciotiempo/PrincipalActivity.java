package com.example.ejerciciotiempo;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.room.Room;

import com.example.ejerciciotiempo.Persistencia.Database;
import com.example.ejerciciotiempo.webService.Request;
import com.example.ejerciciotiempo.Modelo.City;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PrincipalActivity extends Activity implements View.OnClickListener, View.OnLongClickListener{

    private EditText et;
    private TextView tv;
    private TextView tv1;
    private ImageButton btn;
    private Button saveBtn;
    private Button nextPtn;
    private Button saveCityName;
    City datosCity;
    CustomApplication cnt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        cnt = ((CustomApplication) getApplicationContext());
        setUI();
        cargarCityName();
        btn.setOnClickListener(this::onClick);
        btn.setOnLongClickListener(this);
        saveBtn.setOnClickListener(this::saveDates);
        nextPtn.setOnClickListener(this::nextPnt);
        saveCityName.setOnClickListener(this::guardarPreferencia);
    }


    private void setUI(){
        et = (EditText) findViewById(R.id.editTextCityName);
        tv = (TextView) findViewById(R.id.textView);
        tv1 = (TextView) findViewById(R.id.textView1);
        btn = (ImageButton) findViewById(R.id.btnSearch);
        saveBtn = (Button) findViewById(R.id.saveButton);
        nextPtn = (Button) findViewById(R.id.btnSigPnt);
        saveCityName = (Button) findViewById(R.id.btnSaveCityName);
    }

    public void onClick(View view){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(et.getWindowToken(), 0);

        String datos = et.getText().toString();
        Request req = new Request();
        if (datos != "") {
            String url = "http://api.openweathermap.org/data/2.5/weather?q="+datos+"&appid=8e9da05bd1c51babfa6a7fd3a60dc632";
            req.obtenerDatos(url, cnt, new Request.VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    tv.setText("");
                    tv1.setText("");
                }
                @Override
                public void onResponse(Object response) {
                    Gson gson = new Gson();
                    datosCity = gson.fromJson(String.valueOf(response), City.class);
                    datosCity.setName(datos);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                    Date date = new Date();
                    String fecha = dateFormat.format(date);
                    datosCity.setDate(fecha);
                    Double kelvin = datosCity.getTemp();
                    Double temp = kelvin - 273;
                    DecimalFormat formato2 = new DecimalFormat("##");
                    String tem = formato2.format(temp);
                    datosCity.setTemp(temp);
                    tv1.setText("");
                    tv.setText("La temperatura en "+ datos + " es de: " + tem + "°");
                }
            });
        }
    }

    public boolean onLongClick(View view){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(et.getWindowToken(), 0);

        String datos = et.getText().toString();
        Request req = new Request();
        if (datos != "") {
            String url = "http://api.openweathermap.org/data/2.5/weather?q="+datos+"&appid=8e9da05bd1c51babfa6a7fd3a60dc632";
            req.obtenerDatos(url, cnt, new Request.VolleyResponseListener() {
                @Override
                public void onError(String message) {
                }
                @Override
                public void onResponse(Object response) {
                    Gson gson = new Gson();
                    City datosCity = gson.fromJson(String.valueOf(response), City.class);
                    datosCity.setName(datos);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                    Date date = new Date();
                    String fecha = dateFormat.format(date);
                    datosCity.setDate(fecha);
                    tv.setText("");
                    tv1.setText("La Humedad en "+ datos + " es de: " + datosCity.getHumidity()+ "%");
                }
            });
        }
        return true;
    }

    public void saveDates(View view){
        Database db = Room
                .databaseBuilder(PrincipalActivity.this,
                        Database.class, "Database1").allowMainThreadQueries()
                .build();

        City city = new City(datosCity.getCityId(),datosCity.getName(),
                 datosCity.getTemp(), datosCity.getHumidity(), datosCity.getDate());
        db.cityDaodb().insert(city);
        Toast.makeText(this, "Datos Guardados Correctamente",Toast.LENGTH_LONG).show();
//        db.close();
    }

    public void nextPnt(View view){
        Intent intent = new Intent(PrincipalActivity.this, DatesActivity.class);
        startActivity(intent);
    }

    private void guardarPreferencia(View view){
        SharedPreferences preferencia = getSharedPreferences
                ("CityPreference", Context.MODE_PRIVATE);

        String cityPreference = et.getText().toString();

        SharedPreferences.Editor editor = preferencia.edit();
        editor.putString("cityName", cityPreference);

        et.setText(cityPreference);

        editor.commit();
    }

    private void cargarCityName(){
        SharedPreferences preferencia = getSharedPreferences
                ("CityPreference", Context.MODE_PRIVATE);

        String cityName = preferencia.getString("cityName", "");
        et.setText(cityName);
    }


}