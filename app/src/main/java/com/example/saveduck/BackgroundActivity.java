package com.example.saveduck;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saveduck.databinding.ActivityBackgroundBinding;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class BackgroundActivity extends AppCompatActivity {

    TextView tvR, tvPython, tvCPP;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Implementamos DataBinding
        ActivityBackgroundBinding backBinding = ActivityBackgroundBinding.inflate(getLayoutInflater());
        setContentView(backBinding.getRoot());

        tvR = backBinding.tvR;
        tvPython = backBinding.tvPython;
        tvCPP = backBinding.tvCPP;
        pieChart = backBinding.piechart;

        setData();


        // Si pulsamos el botonHome (footer) volvemos al MainActivity
        backBinding.botonHome.setOnClickListener(v -> {
            openMain();
        });
    }

    private void setData()
    {

        // Set the percentage of language used
        tvR.setText(Integer.toString(10));
        tvPython.setText(Integer.toString(10));
        tvCPP.setText(Integer.toString(10));

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "R",
                        Integer.parseInt(tvR.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Python",
                        Integer.parseInt(tvPython.getText().toString()),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "C++",
                        Integer.parseInt(tvCPP.getText().toString()),
                        Color.parseColor("#29B6F6")));

        // To animate the pie chart
        pieChart.startAnimation();
    }

    // Función que abre el Main
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}