package com.example.saveduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.saveduck.databinding.ActivityMainBinding;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    // Esta clase funciona como pantalla de bienvenida o splash. Va a servir para mostrar
    // el logo de la app únicamente, el eslogan y no se volverá a mostrar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_welcome);


        // En este objeto de tipo intent guardaremos la dirección a la página principal de la App.
        // Lo utilizaremos con otro método para indicarle al programa que nos queremos mover
        // hasta allí
        Intent intent = new Intent(this, CreateAccountActivity.class);

        // Este objeto tipo Timer va a funcionar como un sleep, le daremos la duración que
        // queremos que dure la pantalla de bienvenida o splash
        Timer timer = new Timer();

        // Mostraremos el splash y pasados 2 segundos, nos iremos a la pantalla principal.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);

                // Con finish impedimos que podamos volver a esta pantalla. Solo podremos volver a
                // verla si cerramos la App y volvemos a entrar
                finish();
            }
        }, 2000);

        // Con este objeto guardaremos un sonido descargado
        MediaPlayer mp = MediaPlayer.create(this, R.raw.quack);

        // Al abrir la App lo primero que se hará será escuchar el sonido
        mp.start();

    }
}