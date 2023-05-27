package com.example.saveduck;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.saveduck.dataBase.Expense;
import com.example.saveduck.dataBase.ExpenseDao;
import com.example.saveduck.dataBase.SaveDataBase;
import com.example.saveduck.databinding.ActivityShowSpentBinding;

import java.util.ArrayList;
import java.util.Collections;

public class ShowSpentActivity extends AppCompatActivity {

    // Instanciamos un objeto de la clase de la BBDD
    public SaveDataBase bd;

    // Instanciamos un objeto para poder implementar Data Binding
    ActivityShowSpentBinding showSpentBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implementamos DataBinding

        showSpentBinding = ActivityShowSpentBinding.inflate(getLayoutInflater());
        setContentView(showSpentBinding.getRoot());

        // Este layout tiene un apartado llamado RyclerView, el cual va a mostrar layouts externos
        // que van a rellenarse con la información de la BBDD, a más registros en la tabla Expense, más layouts
        // externos aparecerán en el RyclerView (como si fuera un ArrayList de layouts)
        showSpentBinding.incomeRecycler2.setLayoutManager(new LinearLayoutManager(this));

        // Muy importante esta línea para evitar el error NullPointerException
        bd = SaveDataBase.getDatabase(getApplicationContext());

        // Instanciamos un objeto de tipo ExpenseDao par apoder acceder a los métodos CRUD de la tabla
        // Expense
        ExpenseDao expenseDao = bd.expenseDao();

        // Creamos un ArrayList de tipo Expense que va a recoger todos los registros de la tabla
        ArrayList<Expense> listaGastos = (ArrayList<Expense>) expenseDao.getAll();

        // Revertimos el orden de la lista, para mostrar los registros más actuales los primeros
        Collections.reverse(listaGastos);

        // Cremos un objeto auxiliar Adapter que va a permitir mostrar los layouts externos en el RecyclerView
        SpentAdapter adapter = new SpentAdapter();
        showSpentBinding.incomeRecycler2.setAdapter(adapter);

        // La información mostrada en el RecyclerView se completará con la información de la BBDD
        // (guardadao anteriormente en un ArrayList)
        adapter.submitList(listaGastos);

        // Si la lista de tipo Income está vacía (no hay nada que mostrar en el RyclerView porque no hay
        // ningín ingreso), cambiamos la visibilidad de un mensaje que indique al usuario que
        // todavía no se ha registrado ningún ingreso
        if (listaGastos.isEmpty()) {
            showSpentBinding.emptyView2.setVisibility(View.VISIBLE);
        } else {
            // Si hay algún registro en la tabla, ocultamos el mensaje
            showSpentBinding.emptyView2.setVisibility(View.GONE);
        }


    }
}