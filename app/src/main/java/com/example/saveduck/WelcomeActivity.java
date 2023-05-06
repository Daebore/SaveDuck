package com.example.saveduck;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saveduck.dataBase.SaveDataBase;
import com.example.saveduck.dataBase.UserDao;
import com.example.saveduck.databinding.ActivityMainBinding;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {


    public SaveDataBase bd;
    UserDao userDao;

    // Esta clase funciona como pantalla de bienvenida o splash. Va a servir para mostrar
    // el logo de la app únicamente, el eslogan y no se volverá a mostrar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_welcome);

        bd = SaveDataBase.getDatabase(getApplicationContext());
        userDao = bd.userDao();

        // Invocamos el método para reproducir el sonido,
        // al abrir la App lo primero que se hará será escuchar el sonido
        sonidoQuack();

            // Este objeto tipo Timer va a funcionar como un sleep, le daremos la duración que
            // queremos que dure la pantalla de bienvenida o splash
            Timer timer = new Timer();

            // Mostraremos el splash y pasados 2 segundos, nos iremos a la pantalla principal.
            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                    if (!userDao.getAll().isEmpty()){
                        // Invocamos el método para abrir el MainActivity
                        openMain();

                    }else{
                        // Invocamos el método para abrir el MainActivity
                        openCreate();
                    }

                    // Con finish impedimos que podamos volver a esta pantalla. Solo podremos volver a
                    // verla si cerramos la App y volvemos a entrar
                    finish();
                }
            }, 2000);

        }
        
    // Función que abre el CreateAccount
    public void openCreate() {
        // En este objeto de tipo intent guardaremos la dirección a la página principal de la App.
        // Lo utilizaremos con otro método para indicarle al programa que nos queremos mover
        // hasta allí
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    // Función que abre el MainAccount
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Función que reproduce un sonido al abrir la App
    public void sonidoQuack(){
        // Con este objeto guardaremos un sonido descargado
        MediaPlayer mp = MediaPlayer.create(this, R.raw.quack);

        // Reproducimos el audio descargado
        mp.start();
    }
}