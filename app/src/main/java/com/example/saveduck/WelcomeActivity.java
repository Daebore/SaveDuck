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

// Declaración de objetos a utilizar:

    // Un objeto de tipo de la base de datos para poder acceder a ella
    public SaveDataBase bd;

    // Un objeto para implementar el Data Binding
    ActivityMainBinding binding;

    // Esta clase funciona como pantalla de bienvenida o splash. Va a servir para mostrar
    // el logo de la app únicamente, el eslogan y no se volverá a mostrar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implementamos DataBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_welcome);

        // Muy importante esta línea para evitar el error NullPointerException
        bd = SaveDataBase.getDatabase(getApplicationContext());

        // Cremos un objeto de tipo UserDao para poder acceder a los métodos CRUD de la tabla User
        UserDao userDao = bd.userDao();

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

                    // Si la tabla user no está vacía (es decir, ya existe un usuario registrado),
                    // tras mostrar el splash durante 2 segundos, nos vamos al MainActivity
                    if (!userDao.getAll().isEmpty()){
                        // Invocamos el método para abrir el MainActivity
                        openMain();
                    }else{
                        // Si la tabla user está vacía, ningún usuario se ha registrado aún en la App,
                        // por lo que mostramos el activity para crear usuario
                        openCreate();
                    }

                    // Con finish impedimos que podamos volver a esta pantalla. Solo podremos volver a
                    // verla si cerramos la App y volvemos a entrar
                    finish();
                }
            }, 2000);

        }

    // Método que abre el CreateAccount
    public void openCreate() {
        // En este objeto de tipo intent guardaremos la dirección a la página principal de la App.
        // Lo utilizaremos con otro método para indicarle al programa que nos queremos mover
        // hasta allí
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    // Método que abre el MainAccount
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