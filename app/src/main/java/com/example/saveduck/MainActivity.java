package com.example.saveduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.saveduck.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implementamos DataBinding
        ActivityMainBinding mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mainBinding.botonIngresos.setOnClickListener(v -> {
            openIngresos();
        });

        mainBinding.botonGastos.setOnClickListener(v -> {
            openGastos();
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
}