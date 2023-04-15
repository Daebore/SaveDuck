package com.example.saveduck;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.saveduck.databinding.ActivityAddMoneyBinding;


public class AddMoneyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implementamos DataBinding
        ActivityAddMoneyBinding addBinding = ActivityAddMoneyBinding.inflate(getLayoutInflater());
        setContentView(addBinding.getRoot());

        addBinding.botonIngresos.setOnClickListener(v -> {
            sonidoMonedaMario();
        });
    }

    // Función que reproduce un sonido
    public void sonidoMonedaMario(){
        // Con este objeto guardaremos un sonido descargado
        MediaPlayer mp = MediaPlayer.create(this, R.raw.mariobros);

        // Reproducimos el audio descargado
        mp.start();
    }
}