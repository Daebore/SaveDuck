package com.example.saveduck;

import androidx.appcompat.app.AppCompatActivity;

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

        spentBinding.botonGastos.setOnClickListener(v -> {
            sonidoSonicRings();
        });
    }

    // Función que reproduce un sonido
    public void sonidoSonicRings(){
        // Con este objeto guardaremos un sonido descargado
        MediaPlayer mp = MediaPlayer.create(this, R.raw.sonicrings);

        // Reproducimos el audio descargado
        mp.start();
    }
}