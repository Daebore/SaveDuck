package com.example.saveduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.saveduck.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mainBinding.botonIngresos.setOnClickListener(v -> {
            openIngresos();
        });
    }

    // Función que abre el MainActivity
    public void openIngresos() {
        Intent intent = new Intent(this, Add_money_activity.class);
        startActivity(intent);
    }
}