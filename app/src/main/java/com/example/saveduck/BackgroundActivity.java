package com.example.saveduck;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class BackgroundActivity extends AppCompatActivity {

    // Declaración de objetos a utilizar:

    // Un objeto de tipo de la base de datos para poder acceder a ella
    public SaveDataBase bd;

    // Un objeto para implementar el Data Binding
    ActivityBackgroundBinding backBinding;

    // Objetos de tipo TextView que van a hacer referencia a los elementos del Layout donde vamos
    // a mostrar la información que traemos de la BBDD
    TextView textoIngresos, textoGastos, textoAhorrado;

    // Un objeto de tipo PieChart (podemos acceder a él gracias a las librerías externas importadas
    // necesarias para poder tener la gráfica)
    PieChart pieChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implementamos DataBinding
        backBinding = ActivityBackgroundBinding.inflate(getLayoutInflater());
        setContentView(backBinding.getRoot());

        // Si pulsamos el botón botonMostrarIn nos movemos a ShowIncomeActivity
        backBinding.botonMostrarIn.setOnClickListener(v -> {
            openShowIncome();
        });

        // Si pulsamos el botón botonMostrarGa nos movemos a ShowSpentActivity
        backBinding.botonMostrarGa.setOnClickListener(v -> {
            openShowSpent();
        });

        // Si pulsamos el botón botonMCorreo enviaremos un correo con el resumen de toda la actividad
        backBinding.botonMCorreo.setOnClickListener(v -> {
            mandarMail();
        });

        // Muy importante esta línea para evitar el error NullPointerException
        bd = SaveDataBase.getDatabase(getApplicationContext());

        // Ininializamos los objetos TextView y PieChart declaramos al comienzo
        textoIngresos = backBinding.textoIngresos;
        textoGastos = backBinding.textoGastos;
        textoAhorrado = backBinding.textoAhorrado;
        pieChart = backBinding.piechart;

        // Invocamos el método que va a colorear las porciones de la gráfica con la información
        // de la BBDD
        colorearGrafica();

    }

    // Este método nos va a permitir coloreas las porciones de la gráfica con la información extraída
    // de la BBDD
    private void colorearGrafica()
    {

        double num = obtenerAhorros();

        // Los ahorros se calculan mediante la difrerencia de los ingresos y los gastos (+ los ingresos
        // iniciales que tenía el usuario a la hora de crearse la cuenta en la App, en caso de que haya
        // indicado alguno), por lo que hay que tener en cuenta que este dato puede ser nulo o negativo a la hora
        // de entrar en la activity. Mediante esta condición evitaremos un error de tipo Null y bugs
        // visuales con la gráfica, ya que esta no sabe cómo interpretar los números negativos
        if(num > 0){

            // Calculamos los ingresos y los gastos y guardamos el valor en variables
            double ingresos = calcularIngresos();
            double gastos = calcularGastos();

            // Mostramos la información de la BBDD en sus respectivos campos
            textoIngresos.setText(Integer.toString((int) ingresos));

            textoGastos.setText(Integer.toString((int) gastos));

            // Si los ahorros son negativo, mostramos la cifra en rojo
            if(obtenerAhorros() < 0){
                textoAhorrado.setText(Integer.toString((int) obtenerAhorros()));
                backBinding.textoAhorrado.setTextColor(getResources().getColor(R.color.color_gastos));
            }

            textoAhorrado.setText(Integer.toString((int) obtenerAhorros()));

            // Este método va a mostrar un toast/mensaje u otro dependiendo de si el balance es positivo o no
            comprobarBalance(ingresos, gastos);

            // Esta parte del programa va a pintar las porciones del gráfico según la información que va a
            // encontrar en los TextView, que a su vez cogen lso datos de la BBDD.
            // La lógica se ha extraido de la documentación de la biblioteca importada
            pieChart.addPieSlice(
                    new PieModel(
                            "Ingresos",
                            Integer.parseInt(textoIngresos.getText().toString()),
                            Color.parseColor("#66BB6A")));
            pieChart.addPieSlice(
                    new PieModel(
                            "Gastos",
                            Integer.parseInt(textoGastos.getText().toString()),
                            Color.parseColor("#EF5350")));
            pieChart.addPieSlice(
                    new PieModel(
                            "Ahorros",
                            Integer.parseInt(textoAhorrado.getText().toString()),
                            Color.parseColor("#29B6F6")));

            // Método que muestra una animación al generar la gráfica
            pieChart.startAnimation();
        }else if(num <= 0){
            // Calculamos los ingresos y los gastos y guardamos el valor en variables
            double ingresos = calcularIngresos();
            double gastos = calcularGastos();

            // Mostramos la información de la BBDD en sus respectivos campos
            textoIngresos.setText(Integer.toString((int) calcularIngresos()));
            textoGastos.setText(Integer.toString((int) calcularGastos()));

            // Si los ahorros son negativo, mostramos la cifra en rojo
            if(obtenerAhorros() < 0){
                textoAhorrado.setText(Integer.toString((int) obtenerAhorros()));
                backBinding.textoAhorrado.setTextColor(ContextCompat.getColor(this, R.color.color_gastos));
            }

            textoAhorrado.setText(Integer.toString((int) obtenerAhorros()));

            // Este método va a mostrar un toast/mensaje u otro dependiendo de si el balance es positivo o no
            comprobarBalance(ingresos, gastos);

            // Esta parte del programa va a pintar las porciones del gráfico según la información que va a
            // encontrar en los TextView, que a su vez cogen lso datos de la BBDD.
            // La lógica se ha extraido de la documentación de la biblioteca importada
            pieChart.addPieSlice(
                    new PieModel(
                            "Ingresos",
                            Integer.parseInt(textoIngresos.getText().toString()),
                            Color.parseColor("#66BB6A")));
            pieChart.addPieSlice(
                    new PieModel(
                            "Gastos",
                            Integer.parseInt(textoGastos.getText().toString()),
                            Color.parseColor("#EF5350")));

            // Al estar en la parte lógica del else, los ingresos van a ser 0 o negativos, por lo que
            // para evitar bugs a la hora de pintar la gráfica con números negativos, hacemos que pinte 0
            pieChart.addPieSlice(
                    new PieModel(
                            "Ahorros",
                            Integer.parseInt(String.valueOf(0)),
                            Color.parseColor("#29B6F6")));

            // Método que muestra una animación al generar la gráfica
            pieChart.startAnimation();
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
        if(income == null || income.ingresoDinero <= 0){

            // Para evitar error con nulos, si no hay ingresos, por defecto cogemos el valor 0
            backBinding.textoIngresos.setText(String.valueOf(totalIngresos));
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
            backBinding.textoGastos.setText(String.valueOf(totalGastos));
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

    // Método para obtener los ahorros del usuario
    public double obtenerAhorros(){
        // Para conseguir los ahorros/ingresos del usuario, declaramos un objeto de tipo UserDao
        // (para acceder a los métodos CRUD) y otro de tipo User
        UserDao userDao = bd.userDao();
        User user = userDao.getAll().get(0);

        double ingresos = user.ingresos;
        backBinding.textoAhorrado.setText(String.valueOf(user.ingresos));
        return ingresos;
    }

    // Este método va a utilizarse nada más abrir el activity y va a permitir 2 posibles toast o mensajes
    // emergentes dependiendo de si el balance de ahorro es positivo o negativo
    public void comprobarBalance(double ingresos, double gastos){
        if(ingresos > gastos){
            Log.d("Quest_view", "¡Enhorabuena! El balance es positivo");
            AppToast.showMessage(this, "¡Enhorabuena! El balance es positivo", Toast.LENGTH_SHORT);
        }else if(gastos > ingresos){
            Log.d("Quest_view", "¡Cuidado! Los gastos superan a los ingresos");
            AppToast.showMessage(this, "¡Cuidado! Los gastos superan a los ingresos", Toast.LENGTH_SHORT);
        }
    }

    // Método que abre el ShowIncomeActivity
    public void openShowIncome() {
        Intent intent = new Intent(this, ShowIncomeActivity.class);

        // Como queremos asegurarnos de que al movernos de este activity no quede ningún proceso ejecutándose,
        // meditante esta instrucción nos aseguramos de ello a la par de que eliminamos el activity.
        // Esto nos permitirá que no quedé ninguna activity guardada para poder salir de la App
        // desde el MainActivity
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(BackgroundActivity.this).toBundle();
            startActivity(intent, bundle);
        }else{
            startActivity(intent);
        }

    }

    // Método que abre el ShowSpentActivity
    public void openShowSpent() {
        Intent intent = new Intent(this, ShowSpentActivity.class);

        // Esta línea va a terminar todos los procesos del activity para evitar procesos o hilos
        // 'zombie' que se ejecuten en segundo plano, consumiendo recursos
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(BackgroundActivity.this).toBundle();
            startActivity(intent, bundle);
        }else{
            startActivity(intent);
        }
    }

    // Este método nos permitirá enviar un correo electrónico utilizando una aplicación externa
    // que tendrá el resumen financiero del usuario. Se enviará desde y hacia el correo introducido
    // a la hora de registrarse el usuario
    public void mandarMail(){
        bd = SaveDataBase.getDatabase(getApplicationContext());

        // Todos los objetos acabados en Dao hacen referencia a la clase en la que están declarado
        // los métodos que van a permitir realizar las operacinoes CRUD de la BBDD
        UserDao userDao = bd.userDao();

        // Instanciamos un objeto de la tabla User y obtenemos todos los datos del usuario (como solo
        // tenemos un usuario en la BBDD, con poner get y la posición 0 nos vale)
        User user = userDao.getAll().get(0);
        String nombre = user.nombre;
        String mail = user.correo;

        // Con esta línea obtenemos la fecha y la hora actuales y lo guardamos en un String
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

        // Mediante Intent, vamos a invocar una aplicación externa de mensajería
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,
                new String[]{mail});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Resumen financiero SaveDuck");
        intent.putExtra(Intent.EXTRA_TEXT, "Hola " + nombre + ", \n\nEste es tu resumen financiero de SaveDuck: " +
               "\nFecha: " + timeStamp + "\nIngresos totales: " + calcularIngresos() + "€" + "\nGastos totales: " +
                calcularGastos() + "€" + "\nDinero ahorrado: " + obtenerAhorros() + "€" + "\n\nGracias por usar SaveDuck," +
                "\nUn saludo.");

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Elije un cliente de correo:"));

    }

}