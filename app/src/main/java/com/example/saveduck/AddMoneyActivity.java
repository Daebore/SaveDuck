package com.example.saveduck;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saveduck.dataBase.Income;
import com.example.saveduck.dataBase.IncomeDao;
import com.example.saveduck.dataBase.SaveDataBase;
import com.example.saveduck.dataBase.UserDao;
import com.example.saveduck.databinding.ActivityAddMoneyBinding;

import java.time.Instant;


public class AddMoneyActivity extends AppCompatActivity {

    // Instanciamos un objeto de la clase de la BBDD y 2 objetos, 1 de la tabla User y otro de Income
    public SaveDataBase bd;

    IncomeDao incomeDao;

    UserDao userDao;

    // Instanciamos este objeto para poder implementar Data Binding
    ActivityAddMoneyBinding addBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implementamos DataBinding
        addBinding = ActivityAddMoneyBinding.inflate(getLayoutInflater());
        setContentView(addBinding.getRoot());

        // Si pulsamos el botonIngresos, invocamos al método que reproduce el audio
        addBinding.botonIngresos.setOnClickListener(v -> {
            sonidoMonedaMario();

            // Cogemos todos los datos de sus respectivos campos de texto y las guardamos en variables
            String ingresoDinero = addBinding.etIngresos.getText().toString();
            String conceptoIngreso = addBinding.etConceptoIng.getText().toString();

            if(ingresoDinero.isEmpty()){
                AppToast.showMessage(this, "No puede estar vacío", Toast.LENGTH_SHORT);
            }else{
                // Casteamos los ingresos a double
                double ingresosDouble = Double.parseDouble(ingresoDinero);

                // Invocamos el método que nos va a permitir actualizar el salario del User y añadir
                // un registro nuevo a la tabla Income
                guardarEnBD(ingresosDouble, conceptoIngreso);

                // Este log nos sirve para debuggear. Además, añadimos un toast para mostrar al usuario
                // un mensaje indicándole que el registro se ha realizado correctamente
                Log.d("Quest_view", "Ingreso registrado");
                AppToast.showMessage(this, "Ingreso registrado", Toast.LENGTH_SHORT);

                // Una vez realizado el ingreso, volvemos al MainActivity (aparte de por diseño, se hace
                // para evitar errores con la bbdd por si el usuario decide pulsar varias veces seguidas
                // el botón de añadir ingresos)
                openMain();
            }

        });

        // Si pulsamos el botonHome (footer) volvemos al MainActivity
        addBinding.botonHome.setOnClickListener(v -> {
            openMain();
        });
    }

    public void guardarEnBD(double ingresosDouble, String conceptoIngreso) {

        // Inicializamos la instancia de la base de datos
        bd = SaveDataBase.getDatabase(getApplicationContext());

        // Inicializamos el objeto de tipo userDao (nos va a permitir acceder a los métodos de la
        // tabla User que nos permitirá realizar las funcinoes CRUD)
        userDao = bd.userDao();

        // Actualizamos el campo de ingresos de la tabla User
        userDao.update(ingresosDouble);

        // Inicializamos el objeto de tipo incomeDao (nos va a permitir acceder a los métodos de la
        // tabla Income que nos permitirá realizar las funcinoes CRUD)
        incomeDao = bd.incomeDao();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Hacemos el insert del nuevo registro, con la fecha del mismo (PK) y el ingreso
            incomeDao.insertAll(new Income(Instant.now().getEpochSecond(), ingresosDouble, conceptoIngreso));
        }

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