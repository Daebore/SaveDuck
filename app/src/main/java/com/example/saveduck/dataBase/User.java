package com.example.saveduck.dataBase;

import androidx.annotation.NonNull;
import androidx.room.*;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    public String nombre;
    @NonNull
    public String correo;

    public double ingresos;

    public User() {
        super();
    }

    public User(@NonNull String nombre, String correo, double ingresos) {
        this.nombre = nombre;
        this.correo = correo;
        this.ingresos = ingresos;
    }
}