package com.example.saveduck.dataBase;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


// Esta clase de tipo entidad va a servir para definir la estructura de la tabla Income de la BBDD
@Entity
public class Income {

    // El campo de tipo long fechaIngreso va a ser la Primary Key de la tabla

    @PrimaryKey
    @NonNull
    public long fechaIngreso;

    // El campo de tipo double ingresoDinero
    public double ingresoDinero;

    // El campo de tipo String conceptoIngreso
    public String conceptoIngreso;

    // Este método va a permitirnos meter una fecha en la tabla, teniendo en cuenta lso segundos para
    // que no pueda haber 2 registros con la misma fecha, lo que daría error por repetir la PK
    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDateTime getFechaIngreso(ZoneOffset zoffset){
        return LocalDateTime.ofEpochSecond(fechaIngreso, 0, zoffset);
    }

    public Income() {
        super();
    }

    // Método constructor de la clase (para crear objetos de la misma e introducirlos en la BBDD
    // mediante un método que va a equivaler a una función CRUD)
    public Income(@NonNull long fechaIngreso, double ingresoDinero, String conceptoIngreso) {
        this.fechaIngreso = fechaIngreso;
        this.ingresoDinero = ingresoDinero;
        this.conceptoIngreso = conceptoIngreso;
    }

}
