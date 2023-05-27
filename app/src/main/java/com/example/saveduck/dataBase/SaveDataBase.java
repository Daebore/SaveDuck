package com.example.saveduck.dataBase;

import android.content.Context;

import androidx.room.*;

@Database(entities = {User.class, Income.class, Expense.class}, version = 2, exportSchema = false)

// La lógica de esta clase ha sido extraída de la documentación oficial de Android y va a encargarse
// de crear la base de datos con cada una de las tablas.
// En nuestro lugar, utilizaremos Room, una librería de SQLite que permite crear bases de datos para
// aplicaciones Android en la propia aplicaciones, sin necesitar conectar la App con una BBDD externa
public abstract class SaveDataBase extends RoomDatabase {

    // Cremos objetos de tipo Dao, que son los que van a contener los métodos CRUD de las tablas
    public abstract UserDao userDao();

    public abstract IncomeDao incomeDao();

    public abstract ExpenseDao expenseDao();
    private static volatile SaveDataBase INSTANCE;

    // Room de SQLite utiliza el patrón de diseño singleton, que permite restringir la creación de objetos
    // a un único objeto. Esto es algo que nos interesa mucho, ya que va a encargarse de crear una instancia
    // de la base de datos y, en caso de que esté creada y ya exista, va a devolverla con return, de
    // este modo nos aseguramos de que la BBDD se cree una sola vez y despúes la devolverá cada vez
    // que se necesite
    public static SaveDataBase getDatabase(final Context context) {

        // Si la base de datos no ha sido instanciada aún, se crea
        if (INSTANCE == null) {
            synchronized (SaveDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    SaveDataBase.class, "saveduck_database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries().build();
                }
            }
        }

        // Si ya existe, se devulve con return
        return INSTANCE;
    }

}