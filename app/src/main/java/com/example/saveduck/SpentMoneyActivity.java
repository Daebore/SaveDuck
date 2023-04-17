package com.example.saveduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.example.saveduck.databinding.ActivitySpentMoneyBinding;

public class SpentMoneyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implementamos DataBinding
        ActivitySpentMoneyBinding spentBinding = ActivitySpentMoneyBinding.inflate(getLayoutInflater());
        setContentView(spentBinding.getRoot());

        // Si pulsamos el botonGastos, invocamos al método que reproduce el audio
        spentBinding.botonGastos.setOnClickListener(v -> {
            sonidoSonicRings();
        });

        // Si pulsamos el botonHome (footer) volvemos al MainActivity
        spentBinding.botonHome.setOnClickListener(v -> {
            openMain();
        });
    }

    // Función que reproduce un sonido
    public void sonidoSonicRings(){
        // Con este objeto guardaremos un sonido descargado
        MediaPlayer mp = MediaPlayer.create(this, R.raw.sonicrings);

        // Reproducimos el audio descargado
        mp.start();
    }

    // Función que abre el Main
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}