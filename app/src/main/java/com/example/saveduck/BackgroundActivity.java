package com.example.saveduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.saveduck.databinding.ActivityBackgroundBinding;
import com.example.saveduck.databinding.ActivityCreateAccountBinding;

public class BackgroundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implementamos DataBinding
        ActivityBackgroundBinding backBinding = ActivityBackgroundBinding.inflate(getLayoutInflater());
        setContentView(backBinding.getRoot());

        // Si pulsamos el botonHome (footer) volvemos al MainActivity
        backBinding.botonHome.setOnClickListener(v -> {
            openMain();
        });
    }

    // Función que abre el Main
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}