package com.example.saveduck.dataBase;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
public class Income {

    @PrimaryKey
    @NonNull
    public long fechaIngreso;

    public double ingresoDinero;

    public String conceptoIngreso;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDateTime getFechaIngreso(ZoneOffset zoffset){
        return LocalDateTime.ofEpochSecond(fechaIngreso, 0, zoffset);
    }

    public Income() {
        super();
    }

    public Income(@NonNull long fechaIngreso, double ingresoDinero, String conceptoIngreso) {
        this.fechaIngreso = fechaIngreso;
        this.ingresoDinero = ingresoDinero;
        this.conceptoIngreso = conceptoIngreso;
    }

}
