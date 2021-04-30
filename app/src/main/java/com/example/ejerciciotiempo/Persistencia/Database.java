package com.example.ejerciciotiempo.Persistencia;


import androidx.room.RoomDatabase;

import com.example.ejerciciotiempo.Modelo.City;

@androidx.room.Database(
    entities = {City.class},
    version = 1
)
public abstract class Database extends RoomDatabase {
    public abstract CityDao cityDaodb();
}
