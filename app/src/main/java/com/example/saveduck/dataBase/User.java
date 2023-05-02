package com.example.saveduck.dataBase;

import androidx.annotation.NonNull;
import androidx.room.*;

// Esta clase de tipo entidad va a servir para definir la estructura de la tabla User de la BBDD
@Entity
public class User {

    // El campo de tipo String nombre va a ser la Primary Key de la tabla
    @PrimaryKey
    @NonNull
    public String nombre;

    // El campo de tipo String correo (not null)
    @NonNull
    public String correo;

    // El campo de tipo dobule ingresos
    public double ingresos;

    public User() {
        super();
    }

    // Método constructor de la clase (para crear objetos de la misma e introducirlos en la BBDD
    // mediante un método que va a equivaler a una función CRUD)
    public User(@NonNull String nombre, String correo, double ingresos) {
        this.nombre = nombre;
        this.correo = correo;
        this.ingresos = ingresos;
    }
}