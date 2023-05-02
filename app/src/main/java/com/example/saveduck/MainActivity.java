package com.example.saveduck;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saveduck.dataBase.IncomeDao;
import com.example.saveduck.dataBase.SaveDataBase;
import com.example.saveduck.dataBase.User;
import com.example.saveduck.dataBase.UserDao;
import com.example.saveduck.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public SaveDataBase bd;

    UserDao userDao;

    IncomeDao incomeDao;
    ActivityMainBinding mainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implementamos DataBinding
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        recogerDatosBD();


        // Si pulsamos el botonIngresos, invocamos el método que abre el AddMoneyActivity
        mainBinding.botonIngresos.setOnClickListener(v -> {
            openIngresos();
        });

        // Si pulsamos el botonGastos, invocamos el método que abre el SpentMoneyActivity
        mainBinding.botonGastos.setOnClickListener(v -> {
            openGastos();
        });

        // Si pulsamos el botonHome (footer) volvemos al MainActivity
        mainBinding.botonHistorial.setOnClickListener(v -> {
            openHistorial();
        });
    }

    public void recogerDatosBD() {
        // Obtener objetos BD.
        bd = SaveDataBase.getDatabase(getApplicationContext());
        userDao = bd.userDao();

        incomeDao = bd.incomeDao();

        User user = userDao.getAll().get(0);

        // Mostrar los datos en sus respectivos campos.
        mainBinding.textoSaludoMain.setText(mainBinding.textoSaludoMain.getText() + " " + user.nombre);

        mainBinding.textoIngresosDinero.setText(String.valueOf(user.ingresosIni));

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