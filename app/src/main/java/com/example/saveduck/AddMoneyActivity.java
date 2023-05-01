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
import com.example.saveduck.databinding.ActivityAddMoneyBinding;

import java.time.Instant;


public class AddMoneyActivity extends AppCompatActivity {

    public SaveDataBase bd;

    IncomeDao incomeDao;
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

            String ingresoDinero = addBinding.etIngresos.getText().toString();
            String conceptoIngreso = addBinding.etConceptoIng.getText().toString();
            double ingresosDouble = Double.parseDouble(ingresoDinero);

            guardarEnBD(ingresosDouble, conceptoIngreso);

            // Este log nos sirve para debuggear. Además, añadimos un toast para mostrar al usuario
            // un mensaje indicándole que el registro se ha realizado correctamente
            Log.d("Quest_view", "Ingreso registrado");
            AppToast.showMessage(this, "Ingreso registrado", Toast.LENGTH_SHORT);

            openMain();
        });

        // Si pulsamos el botonHome (footer) volvemos al MainActivity
        addBinding.botonHome.setOnClickListener(v -> {
            openMain();
        });
    }

    public void guardarEnBD(double ingresosDouble, String conceptoIngreso) {
        bd = SaveDataBase.getDatabase(getApplicationContext());
        incomeDao = bd.incomeDao();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
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