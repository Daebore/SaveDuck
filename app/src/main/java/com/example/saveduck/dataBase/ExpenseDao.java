package com.example.saveduck.dataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

// Esta clase interfaz va a servir para guardar las operaciones CRUD asignadas a la tabla Expense de la BBDD
@Dao
public interface ExpenseDao {

    @Query("SELECT * FROM Expense")
    List<Expense> getAll();

    @Query("SELECT * FROM Expense WHERE fechaGasto IN (:listaFechaGasto)")
    List<Expense> loadAllByNames(int[] listaFechaGasto);

    @Query("SELECT * FROM Expense WHERE fechaGasto=:fecha")
    Expense findByDate(long fecha);

    @Query("SELECT * FROM Expense WHERE fechaGasto=(SELECT max(fechaGasto) FROM Expense)")
    Expense getLatest();

    @Insert
    void insertAll(Expense... expense);

    @Delete
    void delete(Expense expense);
}
