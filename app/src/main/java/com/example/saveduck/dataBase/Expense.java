package com.example.saveduck.dataBase;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

// Esta clase de tipo entidad va a servir para definir la estructura de la tabla Expense de la BBDD
@Entity
public class Expense {

    // El campo de tipo long fechaGasto va a ser la Primary Key de la tabla
    @PrimaryKey
    @NonNull
    public long fechaGasto;

    // El campo de tipo double gastoDinero
    public double gastoDinero;

    // El campo de tipo String conceptoGasto
    public String conceptoGasto;

    // Este método va a permitirnos meter una fecha en la tabla, teniendo en cuenta los segundos para
    // que no pueda haber 2 registros con la misma fecha, lo que daría error por repetir la PK
    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDateTime getFechaIngreso(ZoneOffset zoffset){
        return LocalDateTime.ofEpochSecond(fechaGasto, 0, zoffset);
    }

    public Expense() {
        super();
    }

    // Método constructor de la clase (para crear objetos de la misma e introducirlos en la BBDD
    // mediante un método que va a equivaler a una función CRUD)
    public Expense(@NonNull long fechaGasto, double gastoDinero, String conceptoGasto) {
        this.fechaGasto = fechaGasto;
        this.gastoDinero = gastoDinero;
        this.conceptoGasto = conceptoGasto;
    }
}
