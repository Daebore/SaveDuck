package com.example.saveduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.saveduck.databinding.ActivityCreateAccountBinding;
import com.example.saveduck.databinding.ActivityMainBinding;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implementamos DataBinding
        ActivityCreateAccountBinding createBinding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(createBinding.getRoot());

        createBinding.botonRegistrar.setOnClickListener(v -> {
            openMain();
        });
    }

    // Función que abre el MainActivity
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}