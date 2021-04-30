package com.example.ejerciciotiempo;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejerciciotiempo.Modelo.City;

import java.text.DecimalFormat;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<City> listCity;
    private int layout;


    public MyAdapter(List<City> listCity, int layout){
        this.listCity = listCity;
        this.layout = layout;
    }

    public void setNumbers(List<City> listCity) {
        this.listCity = listCity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(layoutParams);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        DecimalFormat formato2 = new DecimalFormat("##");
        Double temperatura = listCity.get(position).getTemp();
        String tem = formato2.format(temperatura);
        holder.txtNombre.setText(listCity.get(position).getName().toString());
        holder.txtTemp.setText(String.valueOf(tem).toString());
        holder.txtHume.setText((String.valueOf(listCity.get(position).getHumidity())).toString());
        holder.txtFecha.setText(listCity.get(position).getDate().toString());


        if(listCity.get(position).getTemp() < 10){
            holder.txtNombre.setBackgroundColor(Color.parseColor("#90CAF9"));
            holder.txtTemp.setBackgroundColor(Color.parseColor("#90CAF9"));
            holder.txtHume.setBackgroundColor(Color.parseColor("#90CAF9"));
            holder.txtFecha.setBackgroundColor(Color.parseColor("#90CAF9"));
        } else {
            holder.txtNombre.setBackgroundColor(Color.parseColor("#EF5350"));
            holder.txtTemp.setBackgroundColor(Color.parseColor("#EF5350"));
            holder.txtHume.setBackgroundColor(Color.parseColor("#EF5350"));
            holder.txtFecha.setBackgroundColor(Color.parseColor("#EF5350"));
        }
//        if(position%2 == 0){
//            holder.textView.setBackgroundColor(Color.parseColor("#90CAF9"));
//        } else {
//            holder.textView.setBackgroundColor(Color.parseColor("#E3F2FD"));
//
//        }
    }

    @Override
    public int getItemCount() {
        return listCity.size();
    }

    // VIEW HOLDER
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtNombre, txtTemp, txtHume, txtFecha;

        //CONSTRUCTOR
        public ViewHolder(View itemView) {
            super(itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.txtNombre);
            txtTemp = (TextView) itemView.findViewById(R.id.txtTemp);
            txtHume = (TextView) itemView.findViewById(R.id.txtHume);
            txtFecha = (TextView) itemView.findViewById(R.id.txtFecha);
        }
    }

}
