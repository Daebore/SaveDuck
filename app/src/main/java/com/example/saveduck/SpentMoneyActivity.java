package com.example.saveduck;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saveduck.dataBase.Expense;
import com.example.saveduck.dataBase.ExpenseDao;
import com.example.saveduck.dataBase.SaveDataBase;
import com.example.saveduck.dataBase.UserDao;
import com.example.saveduck.databinding.ActivitySpentMoneyBinding;

import java.time.Instant;

public class SpentMoneyActivity extends AppCompatActivity {

    // Instanciamos un objeto de la clase de la BBDD y 2 objetos, 1 de la tabla User y otro de Expense
    public SaveDataBase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implementamos DataBinding
        ActivitySpentMoneyBinding spentBinding = ActivitySpentMoneyBinding.inflate(getLayoutInflater());
        setContentView(spentBinding.getRoot());

        // Si pulsamos el botonGastos, invocamos al método que reproduce el audio
        spentBinding.botonGastos.setOnClickListener(v -> {

            // Cogemos todos los datos de sus respectivos campos de texto y las guardamos en variables
            String gastoDinero = spentBinding.etGastos.getText().toString();
            String conceptoIGasto = spentBinding.etConceptoGas.getText().toString();

            if(gastoDinero.isEmpty()){
                Log.d("Spent_view", "El campo Gastos no puede estar vacío");
                AppToast.showMessage(this, "El campo Gastos no puede estar vacío", Toast.LENGTH_SHORT);
            }else{
                // Casteamos los ingresos a double
                double gastoDouble = Double.parseDouble(gastoDinero);

                sonidoSonicRings();

                // Invocamos el método que nos va a permitir actualizar el salario del User y añadir
                // un registro nuevo a la tabla Income
                guardarEnBD(gastoDouble, conceptoIGasto);

                // Este log nos sirve para debuggear. Además, añadimos un toast para mostrar al usuario
                // un mensaje indicándole que el registro se ha realizado correctamente
                Log.d("Quest_view", "Gasto registrado");
                AppToast.showMessage(this, "Gasto registrado", Toast.LENGTH_SHORT);

                // Una vez registrado el gasto, volvemos al MainActivity (aparte de por diseño, se hace
                // para evitar errores con la bbdd por si el usuario decide pulsar varias veces seguidas
                // el botón de añadir ingresos)
                openMain();
            }
        });

    }

    public void guardarEnBD(double gastosDouble, String conceptoGasto) {

        // Inicializamos la instancia de la base de datos
        bd = SaveDataBase.getDatabase(getApplicationContext());

        // Inicializamos el objeto de tipo userDao (nos va a permitir acceder a los métodos de la
        // tabla User que nos permitirá realizar las funcinoes CRUD)
        UserDao userDao = bd.userDao();

        // Actualizamos el campo de ingresos de la tabla User
        userDao.updateExpense(gastosDouble);

        // Inicializamos el objeto de tipo ExpenseDao (nos va a permitir acceder a los métodos de la
        // tabla Expense que nos permitirá realizar las funcinoes CRUD)
        ExpenseDao expenseDao = bd.expenseDao();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Hacemos el insert del nuevo registro, con la fecha del mismo (PK) y el gasto
            expenseDao.insertAll(new Expense(Instant.now().getEpochSecond(), gastosDouble, conceptoGasto));
        }

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
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        /*
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(SpentMoneyActivity.this).toBundle();
            startActivity(intent, bundle);
        }else{
            startActivity(intent);
        }

        */

    }
}