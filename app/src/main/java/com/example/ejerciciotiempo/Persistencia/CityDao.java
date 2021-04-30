package com.example.ejerciciotiempo.Persistencia;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ejerciciotiempo.Modelo.City;

import java.util.List;

@Dao
public interface CityDao {

    @Query("SELECT * FROM city ORDER BY name")
        List<City> getAll();

    @Query("SELECT * FROM city WHERE cityId IN (:id)")
        City findById(int id);

    @Query("SELECT cityId, `temp`, humidity, date FROM City")
        City findDates();

    @Insert
        void insert(City city);
}
