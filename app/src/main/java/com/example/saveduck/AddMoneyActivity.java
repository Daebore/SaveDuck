package com.example.saveduck;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saveduck.dataBase.Income;
import com.example.saveduck.dataBase.IncomeDao;
import com.example.saveduck.dataBase.SaveDataBase;
import com.example.saveduck.databinding.ActivityAddMoneyBinding;


public class AddMoneyActivity extends AppCompatActivity {

    public SaveDataBase BD;

    IncomeDao IncomeDao;
    ActivityAddMoneyBinding AddBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implementamos DataBinding
        AddBinding = ActivityAddMoneyBinding.inflate(getLayoutInflater());
        setContentView(AddBinding.getRoot());

        // Si pulsamos el botonIngresos, invocamos al método que reproduce el audio
        AddBinding.botonIngresos.setOnClickListener(v -> {
            sonidoMonedaMario();

            // Este log nos sirve para debuggear. Además, añadimos un toast para mostrar al usuario
            // un mensaje indicándole que el registro se ha realizado correctamente
            Log.d("Quest_view", "Ingreso registrado");
            AppToast.showMessage(this, "Ingreso registrado", Toast.LENGTH_SHORT);
        });

        // Si pulsamos el botonHome (footer) volvemos al MainActivity
        AddBinding.botonHome.setOnClickListener(v -> {
            openMain();
        });
    }

    public void guardarEnBD(long fechaIngreso, double ingresoDinero) {
        IncomeDao.insertAll(new Income(fechaIngreso, ingresoDinero));
    }

    // Función que reproduce un sonido
    public void sonidoMonedaMario(){
        // Con este objeto guardaremos un sonido descargado
        MediaPlayer mp = MediaPlayer.create(this, R.raw.mariobros);

        // Reproducimos el audio descargado
        mp.start();
    }

    // Función que abre el Main
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}