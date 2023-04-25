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

    public double ingresosIni;

    public User() {
        super();
    }

    public User(@NonNull String nombre, String correo, double ingresosIni) {
        this.nombre = nombre;
        this.correo = correo;
        this.ingresosIni = ingresosIni;
    }
}