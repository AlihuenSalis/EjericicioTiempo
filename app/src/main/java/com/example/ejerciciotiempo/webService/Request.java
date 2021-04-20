package com.example.ejerciciotiempo.webService;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ejerciciotiempo.Modelo.City;
import com.example.ejerciciotiempo.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class Request extends AppCompatActivity {

    private RequestQueue queue;
    private JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        // ME FALTA ESTO EN EL METODO, POR ESO NO ANDA.
//        queue = Volley.newRequestQueue(this);
    }


    public void obtenerDatos(String url) {
        System.out.println("llega ?");
        queue = Volley.newRequestQueue(this);
        System.out.println("URL EN METODO" );
//        String url = "http://api.openweathermap.org/data/2.5/weather?q=London&appid=8e9da05bd1c51babfa6a7fd3a60dc632";
//        String url1 = "https://samples.openweathermap.org/data/2.5/forecast?id=524901&appid=8e9da05bd1c51babfa6a7fd3a60dc632";
        JsonObjectRequest request = new JsonObjectRequest(
                com.android.volley.Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject jsonObject1 = response.getJSONObject("main");
                            json = jsonObject1;
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Request.this, "ERROR",Toast.LENGTH_LONG).show();

                        }
                        Gson gson = new Gson();
                        City datosCity = gson.fromJson(String.valueOf(json), City.class);
                        System.out.println("la temp en el metodo es: " + datosCity.getTemp());
                        System.out.println("la hum en el metodo es: " + datosCity.getHumidity());
//                        Toast.makeText(Request.this, "no hizo nada ? ",Toast.LENGTH_LONG).show();

//                        try {
//                            JSONObject jsonObject1 = response.getJSONObject("main");
////                            JSONObject jsonObject = jsonObject1.getJSONObject("humidity");
//                            String humedad = jsonObject1.getString("humidity");
//                            Toast.makeText(PrincipalActivity.this, "la humedad es: "+humedad,Toast.LENGTH_LONG).show();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(PrincipalActivity.this, "ERROR",Toast.LENGTH_LONG).show();
//
//                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Request.this, "el error es: "+error,Toast.LENGTH_LONG).show();
                        System.out.println("El error es: " + error);

                    }
                }

        );
        queue.add(request);
//        System.out.println("la reques es:" + request);
    }



}