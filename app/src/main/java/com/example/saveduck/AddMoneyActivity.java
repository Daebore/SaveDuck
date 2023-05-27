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

    // Instanciamos un objeto de la clase de la BBDD
    public SaveDataBase bd;

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

            // Cogemos todos los datos de sus respectivos campos de texto y las guardamos en variables
            String ingresoDinero = addBinding.etIngresos.getText().toString();
            String conceptoIngreso = addBinding.etConceptoIng.getText().toString();

            // Si el campo de añadir ingresos está vacío, mostramos al user un mensaje emergente/toast
            // indicando que lo rellene
            if(ingresoDinero.isEmpty()) {
                Log.d("Add_view", "El campo Ingresos no puede estar vacío");
                AppToast.showMessage(this, "El campo Ingresos no puede estar vacío", Toast.LENGTH_SHORT);
            }else {

                // Casteamos los ingresos a double
                double ingresoDouble = Double.parseDouble(ingresoDinero);
                if (ingresoDouble > 1000000) {
                    // Para evitar desbordar la variable
                    Log.d("Create_view", "Cada nuevo ingreso registrado no puede superar los 1000000€");
                    AppToast.showMessage(this, "Cada nuevo ingreso registrado no puede superar los 1000000€", Toast.LENGTH_SHORT);
                } else if (!conceptoIngreso.isEmpty() && conceptoIngreso.length() > 30) {
                    // Si el concepto no está vacío y es demasiado largo, mostramos el mensaje correspondiente
                    Log.d("Create_view", "El tamaño del concepto no puede superar los 30 caracteres");
                    AppToast.showMessage(this, "El tamaño del concepto no puede superar los 30 caracteres", Toast.LENGTH_SHORT);
                } else if (conceptoIngreso.toLowerCase().contains("select") || conceptoIngreso.toLowerCase().contains("delete")
                        || conceptoIngreso.toLowerCase().contains("drop") || conceptoIngreso.toLowerCase().contains("insert")
                        || conceptoIngreso.toLowerCase().contains("update")) {
                    // Para evitar SQL Injections
                    Log.d("Create_view", "El concepto contiene palabras no permitidas");
                    AppToast.showMessage(this, "El concepto contiene palabras no permitidas", Toast.LENGTH_SHORT);

                } else {
                    //Si no está vacío, reproducimos el sonido descargado, guardamos los datos en la BBDD
                    // mostramos un toast indicando el éxito de la operación y regresamos al Main
                    sonidoMonedaMario();
                    // Casteamos los ingresos a double
                    double ingresosDouble = Double.parseDouble(ingresoDinero);

                    // Invocamos el método que nos va a permitir actualizar el salario del User y añadir
                    // un registro nuevo a la tabla Income
                    guardarEnBD(ingresosDouble, conceptoIngreso);

                    // Este log nos sirve para debuggear. Además, añadimos un toast para mostrar al usuario
                    // un mensaje indicándole que el registro se ha realizado correctamente
                    Log.d("Add_view", "Ingreso registrado");
                    AppToast.showMessage(this, "Ingreso registrado", Toast.LENGTH_SHORT);

                    // Una vez registrado el ingreso, volvemos al MainActivity (aparte de por diseño, se hace
                    // para evitar errores con la bbdd por si el usuario decide pulsar varias veces seguidas
                    // el botón de añadir ingresos)
                    openMain();
                }
            }
        });

    }

    // Este métodos nos va a permitir crear un objeto de tipo Income y guardarlo en la BBDD
    public void guardarEnBD(double ingresosDouble, String conceptoIngreso) {

        // Inicializamos la instancia de la base de datos
        bd = SaveDataBase.getDatabase(getApplicationContext());

        // Inicializamos el objeto de tipo userDao (nos va a permitir acceder a los métodos de la
        // tabla User que nos permitirá realizar las funcinoes CRUD)
        UserDao userDao = bd.userDao();

        // Actualizamos el campo de ingresos de la tabla User
        userDao.update(ingresosDouble);

        // Inicializamos el objeto de tipo IncomeDao (nos va a permitir acceder a los métodos de la
        // tabla Income que nos permitirá realizar las funcinoes CRUD)
        IncomeDao incomeDao = bd.incomeDao();

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

        // Esta línea va a terminar todos los procesos del activity para evitar procesos o hilos
        // 'zombie' que se ejecuten en segundo plano, consumiendo recursos
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}