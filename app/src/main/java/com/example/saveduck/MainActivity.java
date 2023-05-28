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

// Declaración de objetos a utilizar:

    // Un objeto de tipo de la base de datos para poder acceder a ella
    public SaveDataBase bd;

    // Un objeto para implementar el Data Binding
    ActivityMainBinding mainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implementamos DataBinding
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        // Invocamos el método que va a permitir pintar el activity con los datos de la BBDD
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

    // Este método nos va a permitir recoger los datos de la BBDD
    public void recogerDatosBD() {
        // Muy importante esta línea para evitar el error NullPointerException
        bd = SaveDataBase.getDatabase(getApplicationContext());

        // Todos los objetos acabados en Dao hacen referencia a la clase en la que están declarado
        // los métodos que van a permitir realizar las operacinoes CRUD de la BBDD
        UserDao userDao = bd.userDao();

        // Instanciamos un objeto de la tabla User y obtenemos todos los datos del usuario (como solo
        // tenemos un usuario en la BBDD, con poner get y la posición 0 nos vale)
        User user = userDao.getAll().get(0);

        // Mostrar los datos en sus respectivos campos:
        mainBinding.textoSaludoMain.setText(mainBinding.textoSaludoMain.getText() + " " + user.nombre);

        mainBinding.textoIngresosDinero.setText(calcularIngresos() + "€");

        mainBinding.textoGastosDinero.setText(calcularGastos() + "€");

        if(user.ingresos < 0){
            mainBinding.textoBlance.setTextColor(getResources().getColor(R.color.color_gastos));
            mainBinding.textoBlance.setText(user.ingresos + "€");
        }else{
            mainBinding.textoBlance.setText(user.ingresos + "€");
        }

    }

    // Este método nos va a permitir calcular todos los ingresos registrados en la BBDD
    public double calcularIngresos(){
        // Instanciamos un objeto de tipo IncomeDao para poder acceder a los métodos CRUD de la tabla
        // Income
        IncomeDao incomeDao = bd.incomeDao();

        // Instanciamos un objeto de la clase Income y obtenemos el último registro
        Income income = incomeDao.getLatest();

        double totalIngresos = 0;
        if(income == null){
            // Para evitar error con nulos, si no hay ingresos, por defecto cogemos el valor 0
            mainBinding.textoIngresosDinero.setText(totalIngresos + "€");
        }else{
            // Si no es nulo (hay algún ingreso registrado, cogemos todos los ingresos y los guardamos
            // en una lista)
            ArrayList<Income> listaIngresos = (ArrayList<Income>) incomeDao.getAll();

            // Recorremos la lista y vamos sumando el valor total en una variable
            for (int i = 0; i < listaIngresos.size(); i++) {
                totalIngresos += listaIngresos.get(i).ingresoDinero;
            }
        }
        return totalIngresos;
    }

    // Este método nos va a permitir calcular todos los gastos registrados en la BBDD
    public double calcularGastos(){
        // Instanciamos un objeto de tipo ExpenseDao para poder acceder a los métodos CRUD de la tabla
        // Expense
        ExpenseDao expenseDao = bd.expenseDao();

        // Instanciamos un objeto de la clase Expense
        Expense expense = expenseDao.getLatest();

        double totalGastos = 0;
        if(expense == null){

            // Para evitar error con nulos, si no hay gastos, por defecto cogemos el valor 0
            mainBinding.textoGastosDinero.setText(totalGastos + "€");
        }else{

            // Si no es nulo (hay algún gasto registrado, cogemos todos los gastos y los guardamos
            // en una lista)
            ArrayList<Expense> listaGastos = (ArrayList<Expense>) expenseDao.getAll();

            // Recorremos la lista y vamos sumando el valor total en una variable
            for (int i = 0; i < listaGastos.size(); i++) {
                totalGastos += listaGastos.get(i).gastoDinero;
            }
        }
        return totalGastos;
    }

    // Método que abre el AddMoneyActivity
    public void openIngresos() {
        Intent intent = new Intent(this, AddMoneyActivity.class);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
            startActivity(intent, bundle);
        }else{
            startActivity(intent);
        }
    }

    // Método que abre el SpentMoneyActivity
    public void openGastos() {
        Intent intent = new Intent(this, SpentMoneyActivity.class);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            // Este objeto de tipo Bundle va a permitir implementar una animación que no va a ser
            // la predeterminada (esta animación, fade, se encuentra guardada en styles.xml)
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
            startActivity(intent, bundle);
        }else{
            startActivity(intent);
        }
    }

    // Método que abre el BackgroundActivity
    public void openHistorial() {
        Intent intent = new Intent(this, BackgroundActivity.class);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
            startActivity(intent, bundle);
        }else{
            startActivity(intent);
        }
    }

    // Este método nos permite cerrar la App mostrando un mensaje de confirmación y matando a todos
    // los procesos que pueden quedar pendientes en la App para que no entre en un bucle
    // de cerrar continuamente (solo se cierra la App si todos los activities y sus procesos
    // han terminado)
    private void cerrarApp() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.logosf)
                .setTitle("¿Realmente desea salir de SaveDuck?")
                .setCancelable(false)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> android.os.Process.killProcess(android.os.Process.myPid())).show();
    }

    // Este método va a ejecutarse cuando pulsemos el botón de ir hacia atrás en el teléfodo, el cual
    // va a invocar el método cerrarApp() y va a devolver un boolean (porque exige devolver un
    // valor boolean)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            cerrarApp();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}