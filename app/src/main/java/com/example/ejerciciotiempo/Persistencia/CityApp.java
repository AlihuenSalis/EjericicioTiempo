package com.example.ejerciciotiempo.Persistencia;

import androidx.room.Room;

import com.example.ejerciciotiempo.CustomApplication;

class CityApp extends CustomApplication {

    Database db = Room
            .databaseBuilder(getApplicationContext(), Database.class, "Database1")
            .build();
}
