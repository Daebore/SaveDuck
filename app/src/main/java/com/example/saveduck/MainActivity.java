package com.example.saveduck;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saveduck.dataBase.SaveDataBase;
import com.example.saveduck.dataBase.User;
import com.example.saveduck.dataBase.UserDao;
import com.example.saveduck.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public SaveDataBase BD;
    UserDao UserDao;
    ActivityMainBinding MainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implementamos DataBinding
        MainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(MainBinding.getRoot());

        recogerDatosBD();


        // Si pulsamos el botonIngresos, invocamos el método que abre el AddMoneyActivity
        MainBinding.botonIngresos.setOnClickListener(v -> {
            openIngresos();
        });

        // Si pulsamos el botonGastos, invocamos el método que abre el SpentMoneyActivity
        MainBinding.botonGastos.setOnClickListener(v -> {
            openGastos();
        });

        // Si pulsamos el botonHome (footer) volvemos al MainActivity
        MainBinding.botonHistorial.setOnClickListener(v -> {
            openHistorial();
        });
    }

    public void recogerDatosBD() {
        // Obtener objetos BD.
        BD = SaveDataBase.getDatabase(getApplicationContext());
        UserDao = BD.userDao();

        User user = UserDao.getAll().get(0);

        // Mostrar los datos en sus respectivos campos.
        MainBinding.textoSaludoMain.setText(MainBinding.textoSaludoMain.getText() + " " + user.nombre);

        MainBinding.textoIngresosDinero.setText(String.valueOf(user.ingresosIni));

    }

    // Función que abre el AddMoneyActivity
    public void openIngresos() {
        Intent intent = new Intent(this, AddMoneyActivity.class);
        startActivity(intent);
    }

    // Función que abre el SpentMoneyActivity
    public void openGastos() {
        Intent intent = new Intent(this, SpentMoneyActivity.class);
        startActivity(intent);
    }

    // Función que abre el BackgroundActivity
    public void openHistorial() {
        Intent intent = new Intent(this, BackgroundActivity.class);
        startActivity(intent);
    }

}