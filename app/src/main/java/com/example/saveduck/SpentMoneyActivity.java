package com.example.saveduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

            // Este log nos sirve para debuggear. Además, añadimos un toast para mostrar al usuario
            // un mensaje indicándole que el registro se ha realizado correctamente
            Log.d("Quest_view", "Gasto registrado");
            AppToast.showMessage(this, "Gasto registrado", Toast.LENGTH_SHORT);
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