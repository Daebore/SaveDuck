package com.example.saveduck;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

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

        // Si pulsamos el botonHome (footer) volvemos al MainActivity
        backBinding.botonMostrarIn.setOnClickListener(v -> {
            openShowIncome();
        });


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

        double num = obtenerAhorros();

        if(num > 0){
            tvR.setText(Integer.toString((int) calcularIngresos()));

            tvPython.setText(Integer.toString((int) calcularGastos()));

            tvCPP.setText(Integer.toString((int) obtenerAhorros()));

            if(calcularIngresos() > calcularGastos()){
                Log.d("Quest_view", "¡Enhorabuena! El balance es positivo");
                AppToast.showMessage(this, "¡Enhorabuena! El balance es positivo", Toast.LENGTH_SHORT);
            }else if(calcularIngresos() < calcularGastos()){
                Log.d("Quest_view", "¡Cuidado! El balance es negativo");
                AppToast.showMessage(this, "¡Cuidado! El balance es negativo", Toast.LENGTH_SHORT);
            }

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
        }else{
            tvR.setText(Integer.toString((int) calcularIngresos()));

            tvPython.setText(Integer.toString((int) calcularGastos()));


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
                            Integer.parseInt(String.valueOf(0)),
                            Color.parseColor("#29B6F6")));

            // To animate the pie chart
            pieChart.startAnimation();
        }

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

    public double obtenerAhorros(){
        UserDao userDao = bd.userDao();
        User user = userDao.getAll().get(0);

        double ingresos = user.ingresos;
        backBinding.tvCPP.setText(String.valueOf(user.ingresos));
        return ingresos;
    }

    public void openShowIncome() {
        Intent intent = new Intent(this, ShowIncomeActivity.class);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(BackgroundActivity.this).toBundle();
            startActivity(intent, bundle);
        }else{
            startActivity(intent);
        }
    }

    // Método para mostrar animación Fade al ir hacia atrás
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(BackgroundActivity.this).toBundle();
            startActivity(intent, bundle);
        }else{
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }

}