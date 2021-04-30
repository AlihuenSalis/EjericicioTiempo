package com.example.ejerciciotiempo.Modelo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity
public class City {
    @PrimaryKey(autoGenerate = true)
    private int cityId;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "temp")
    private Double temp;
    @Ignore
    private double feels_like;
    @Ignore
    private double temp_min;
    @Ignore
    private double temp_max;
    @Ignore
    private double pressure;
    @ColumnInfo(name = "humidity")
    private int humidity;
    @ColumnInfo(name = "date")
    private String date;

    public City() { }

    public City(int cityId, String name, Double temp, int humidity, String date) {
        this.cityId = cityId;
        this.name = name;
        this.temp = temp;
        this.humidity = humidity;
        this.date = date;
    }

    public City(int cityId, String name, Double temp,
                double feels_like, double temp_min,
                double temp_max, double pressure,
                int  humidity, String date) {
        this.cityId = cityId;
        this.name = name;
        this.temp = temp;
        this.feels_like = feels_like;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.pressure = pressure;
        this.humidity = humidity;
        this.date = date;
    }

    public int getCityId() { return cityId; }

    public void setCityId(int cityId) { this.cityId = cityId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Double getTemp() { return temp; }

    public void setTemp(Double temp) { this.temp = temp; }

    public double getFeels_like() { return feels_like; }

    public void setFeels_like(double feels_like) { this.feels_like = feels_like; }

    public double getTemp_min() { return temp_min; }

    public void setTemp_min(double temp_min) { this.temp_min = temp_min; }

    public double getTemp_max() { return temp_max; }

    public void setTemp_max(double temp_max) { this.temp_max = temp_max; }

    public double getPressure() { return pressure; }

    public void setPressure(double pressure) { this.pressure = pressure; }

    public int getHumidity() { return humidity; }

    public void setHumidity(int humidity) { this.humidity = humidity; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }
}