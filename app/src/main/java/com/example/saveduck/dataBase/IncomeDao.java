package com.example.saveduck.dataBase;

import androidx.room.*;

import java.util.List;


// Esta clase interfaz va a servir para guardar las operaciones CRUD asignadas a la tabla Income de la BBDD
@Dao
public interface IncomeDao {
    @Query("SELECT * FROM Income")
    List<Income> getAll();

    @Query("SELECT * FROM Income WHERE fechaIngreso IN (:listaFechaIngreso)")
    List<Income> loadAllByNames(int[] listaFechaIngreso);

    @Query("SELECT * FROM Income WHERE fechaIngreso=:fecha")
    Income findByDate(long fecha);

    @Query("SELECT * FROM Income WHERE fechaIngreso=(SELECT max(fechaIngreso) FROM Income)")
    Income getLatest();

    @Insert
    void insertAll(Income... income);

    @Delete
    void delete(Income income);

}
