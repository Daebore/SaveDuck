package com.example.saveduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.saveduck.databinding.ActivityAddMoneyBinding;


public class AddMoneyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implementamos DataBinding
        ActivityAddMoneyBinding addBinding = ActivityAddMoneyBinding.inflate(getLayoutInflater());
        setContentView(addBinding.getRoot());

        // Si pulsamos el botonIngresos, invocamos al método que reproduce el audio
        addBinding.botonIngresos.setOnClickListener(v -> {
            sonidoMonedaMario();

            // Este log nos sirve para debuggear. Además, añadimos un toast para mostrar al usuario
            // un mensaje indicándole que el registro se ha realizado correctamente
            Log.d("Quest_view", "Ingreso registrado");
            AppToast.showMessage(this, "Ingreso registrado", Toast.LENGTH_SHORT);
        });

        // Si pulsamos el botonHome (footer) volvemos al MainActivity
        addBinding.botonHome.setOnClickListener(v -> {
            openMain();
        });
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