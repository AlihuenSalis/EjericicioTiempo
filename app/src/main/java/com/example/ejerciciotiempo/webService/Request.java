package com.example.ejerciciotiempo.webService;


import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ejerciciotiempo.CustomApplication;

import org.json.JSONException;
import org.json.JSONObject;

public class Request {

    private RequestQueue queue;
    private JSONObject json;

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(Object response);
    }


    public void obtenerDatos(String url, CustomApplication app, final VolleyResponseListener listener) {

        queue = Volley.newRequestQueue(app);

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
                        }
//                        Gson gson = new Gson();
//                        City datosCity = gson.fromJson(String.valueOf(json), City.class);

                        listener.onResponse(json);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error.toString());
                        Toast.makeText(app, "Debe ingresar una Ciudad Válida",Toast.LENGTH_LONG).show();

                    }
                }

        );
        queue.add(request);
    }
}