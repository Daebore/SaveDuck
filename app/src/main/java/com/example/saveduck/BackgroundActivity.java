package com.example.saveduck;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saveduck.dataBase.Expense;
import com.example.saveduck.dataBase.ExpenseDao;
import com.example.saveduck.dataBase.Income;
import com.example.saveduck.dataBase.IncomeDao;
import com.example.saveduck.dataBase.SaveDataBase;
import com.example.saveduck.dataBase.User;
import com.example.saveduck.dataBase.UserDao;
import com.example.saveduck.databinding.ActivityBackgroundBinding;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class BackgroundActivity extends AppCompatActivity {

    TextView tvR, tvPython, tvCPP;

    PieChart pieChart;

    ActivityBackgroundBinding backBinding;

    public SaveDataBase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implementamos DataBinding
        backBinding = ActivityBackgroundBinding.inflate(getLayoutInflater());
        setContentView(backBinding.getRoot());

        bd = SaveDataBase.getDatabase(getApplicationContext());

        tvR = backBinding.tvR;
        tvPython = backBinding.tvPython;
        tvCPP = backBinding.tvCPP;
        pieChart = backBinding.piechart;

        setData();

    }

    private void setData()
    {

        // Set the percentage of language used

        tvR.setText(Integer.toString((int) calcularIngresos()));

        tvPython.setText(Integer.toString((int) calcularGastos()));

        tvCPP.setText(Integer.toString((int) obtenerIngresos()));


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

    public double calcularIngresos(){
        IncomeDao incomeDao = bd.incomeDao();
        Income income = incomeDao.getLatest();

        double totalIngresos = 0;
        if(income == null || income.ingresoDinero <= 0){
            backBinding.tvR.setText(String.valueOf(totalIngresos));
        }else{
            ArrayList<Income> listaIngresos = (ArrayList<Income>) incomeDao.getAll();

            for (int i = 0; i < listaIngresos.size(); i++) {
                totalIngresos += listaIngresos.get(i).ingresoDinero;
            }
        }
        return totalIngresos;
    }

    public double calcularGastos(){
        ExpenseDao expenseDao = bd.expenseDao();
        Expense expense = expenseDao.getLatest();

        double totalGastos = 0;
        if(expense == null){
            backBinding.tvPython.setText(String.valueOf(totalGastos));
        }else{
            ArrayList<Expense> listaGastos = (ArrayList<Expense>) expenseDao.getAll();

            for (int i = 0; i < listaGastos.size(); i++) {
                totalGastos += listaGastos.get(i).gastoDinero;
            }
        }
        return totalGastos;
    }

    public double obtenerIngresos(){
        UserDao userDao = bd.userDao();
        User user = userDao.getAll().get(0);

        double ingresos = user.ingresos;
        backBinding.tvCPP.setText(String.valueOf(user.ingresos));
        return ingresos;
    }

    // Función que abre el Main
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}