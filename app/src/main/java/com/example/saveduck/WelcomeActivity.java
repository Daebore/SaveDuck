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
        Intent intent = new Intent(this, Add_money_activity.class);

        // Este objeto tipo Timer va a funcionar como un sleep, le daremos la duración que
        // queremos que dure la pantalla de bienvenida o splash
        Timer timer = new Timer();

        // Mostraremos el splash y pasados 2 segundos, nos iremos a la pantalla principal.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Invocamos el método para abrir el MainActivity
                openMain(intent);

                // Con finish impedimos que podamos volver a esta pantalla. Solo podremos volver a
                // verla si cerramos la App y volvemos a entrar
                finish();
            }
        }, 2000);

        // Invocamos el método para reproducir el sonido,
        // al abrir la App lo primero que se hará será escuchar el sonido
        sonidoQuach();

    }

    // Función que abre el MainActivity
    public void openMain(Intent intent) {
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Función que reproduce un sonido al abrir la App
    public void sonidoQuach(){
        // Con este objeto guardaremos un sonido descargado
        MediaPlayer mp = MediaPlayer.create(this, R.raw.quack);

        // Reproducimos el audio descargado
        mp.start();
    }
}