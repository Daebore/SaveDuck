package com.example.saveduck.dataBase;

import androidx.room.*;

import java.util.List;


// Esta clase interfaz va a servir para guardar las operaciones CRUD asignadas a la tabla User de la BBDD
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE nombre IN (:usuarioNombres)")
    List<User> loadAllByNames(int[] usuarioNombres);

    @Query("SELECT * FROM user WHERE nombre=:name")
    User findByName(String name);

    @Query("UPDATE user SET ingresos = :price + ingresos")
    void update(double price);

    @Insert
    void insertAll(User... users);

    @Update
    void updateUsers(User... users);

    @Delete
    void delete(User user);
}