package com.example.saveduck.dataBase;

import android.content.Context;

import androidx.room.*;

@Database(entities = {User.class, Income.class}, version = 2, exportSchema = false)

public abstract class SaveDataBase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract IncomeDao incomeDao();

    private static volatile SaveDataBase INSTANCE;

    public static SaveDataBase getDatabase(final Context context) {
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
        return INSTANCE;
    }

}