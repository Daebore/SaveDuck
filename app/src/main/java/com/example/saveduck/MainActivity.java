package com.example.saveduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.saveduck.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implementamos DataBinding
        ActivityMainBinding mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        // Si pulsamos el botonIngresos, invocamos el método que abre el AddMoneyActivity
        mainBinding.botonIngresos.setOnClickListener(v -> {
            openIngresos();
        });

        // Si pulsamos el botonGastos, invocamos el método que abre el SpentMoneyActivity
        mainBinding.botonGastos.setOnClickListener(v -> {
            openGastos();
        });

        // Si pulsamos el botonHome (footer) volvemos al MainActivity
        mainBinding.botonHistorial.setOnClickListener(v -> {
            openHistorial();
        });
    }

    // Función que abre el AddMoneyActivity
    public void openIngresos() {
        Intent intent = new Intent(this, AddMoneyActivity.class);
        startActivity(intent);
    }

    // Función que abre el SpentMoneyActivity
    public void openGastos() {
        Intent intent = new Intent(this, SpentMoneyActivity.class);
        startActivity(intent);
    }

    // Función que abre el BackgroundActivity
    public void openHistorial() {
        Intent intent = new Intent(this, BackgroundActivity.class);
        startActivity(intent);
    }

}