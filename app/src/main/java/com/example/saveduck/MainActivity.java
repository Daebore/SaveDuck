package com.example.saveduck;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.saveduck.dataBase.Expense;
import com.example.saveduck.dataBase.ExpenseDao;
import com.example.saveduck.dataBase.Income;
import com.example.saveduck.dataBase.IncomeDao;
import com.example.saveduck.dataBase.SaveDataBase;
import com.example.saveduck.dataBase.User;
import com.example.saveduck.dataBase.UserDao;
import com.example.saveduck.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public SaveDataBase bd;

    UserDao userDao;

    ActivityMainBinding mainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Implementamos DataBinding
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        recogerDatosBD();


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

    public void recogerDatosBD() {
        // Obtener objetos BD.
        bd = SaveDataBase.getDatabase(getApplicationContext());
        userDao = bd.userDao();

        User user = userDao.getAll().get(0);

        // Mostrar los datos en sus respectivos campos.
        mainBinding.textoSaludoMain.setText(mainBinding.textoSaludoMain.getText() + " " + user.nombre);

        mainBinding.textoIngresosDinero.setText(String.valueOf(calcularIngresos()));

        mainBinding.textoGastosDinero.setText(String.valueOf(calcularGastos()));

        mainBinding.textoBlance.setText(String.valueOf(user.ingresos));

    }

    public double calcularIngresos(){
        IncomeDao incomeDao = bd.incomeDao();
        Income income = incomeDao.getLatest();

        double totalIngresos = 0;
        if(income == null){
            mainBinding.textoIngresosDinero.setText(String.valueOf(totalIngresos));
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
            mainBinding.textoGastosDinero.setText(String.valueOf(totalGastos));
        }else{
            ArrayList<Expense> listaGastos = (ArrayList<Expense>) expenseDao.getAll();

            for (int i = 0; i < listaGastos.size(); i++) {
                totalGastos += listaGastos.get(i).gastoDinero;
            }
        }
        return totalGastos;
    }

    // Función que abre el AddMoneyActivity
    public void openIngresos() {
        Intent intent = new Intent(this, AddMoneyActivity.class);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
            startActivity(intent, bundle);
        }else{
            startActivity(intent);
        }
    }

    // Función que abre el SpentMoneyActivity
    public void openGastos() {
        Intent intent = new Intent(this, SpentMoneyActivity.class);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
            startActivity(intent, bundle);
        }else{
            startActivity(intent);
        }
    }

    // Función que abre el BackgroundActivity
    public void openHistorial() {
        Intent intent = new Intent(this, BackgroundActivity.class);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
            startActivity(intent, bundle);
        }else{
            startActivity(intent);
        }
    }

    private void cerrarApp() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.logosf)
                .setTitle("¿Realmente desea salir de SaveDuck?")
                .setCancelable(false)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> android.os.Process.killProcess(android.os.Process.myPid())).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            cerrarApp();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}