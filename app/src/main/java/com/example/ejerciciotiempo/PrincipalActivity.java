package com.example.ejerciciotiempo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.ejerciciotiempo.webService.Request;
import com.example.ejerciciotiempo.Modelo.City;
import com.google.gson.Gson;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener{

    private EditText et;
    private TextView tv;
    private TextView tv1;
    private ImageButton btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        setUI();

        btn.setOnClickListener(this);
        btn.setOnLongClickListener(this);

    }

    private void setUI(){
        et = (EditText) findViewById(R.id.editTextCityName);
        tv = (TextView) findViewById(R.id.textView);
        tv1 = (TextView) findViewById(R.id.textView1);
        btn = (ImageButton) findViewById(R.id.btnSearch);
    }

    public void onClick(View view){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(et.getWindowToken(), 0);

        String datos = et.getText().toString();
        Request req = new Request();
        if (datos != "") {
            String url = "http://api.openweathermap.org/data/2.5/weather?q="+datos+"&appid=8e9da05bd1c51babfa6a7fd3a60dc632";
            req.obtenerDatos(url, this, new Request.VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    tv.setText("");
                    tv1.setText("");
                }
                @Override
                public void onResponse(Object response) {
                    Gson gson = new Gson();
                    City datosCity = gson.fromJson(String.valueOf(response), City.class);
                    int kelvin = (int) datosCity.getTemp();
                    int temp = kelvin - 273;
                    tv1.setText("");
                    tv.setText("La temperatura en "+ datos + " es de: " + temp + "°");
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
            req.obtenerDatos(url, this, new Request.VolleyResponseListener() {
                @Override
                public void onError(String message) {
                }
                @Override
                public void onResponse(Object response) {
                    Gson gson = new Gson();
                    City datosCity = gson.fromJson(String.valueOf(response), City.class);
                    tv.setText("");
                    tv1.setText("La Humedad en "+ datos + " es de: " + (int) datosCity.getHumidity()+ "%");
                }
            });
        }
        return true;
    }
}