package com.example.saveduck.dataBase;

import androidx.room.*;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE nombre IN (:usuarioNombres)")
    List<User> loadAllByNames(int[] usuarioNombres);

    @Query("SELECT * FROM user WHERE nombre=:name")
    User findByName(String name);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}