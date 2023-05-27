package com.example.saveduck;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.saveduck.dataBase.Income;
import com.example.saveduck.dataBase.IncomeDao;
import com.example.saveduck.dataBase.SaveDataBase;
import com.example.saveduck.databinding.ActivityShowIncomeBinding;

import java.util.ArrayList;
import java.util.Collections;

public class ShowIncomeActivity extends AppCompatActivity {

    // Instanciamos un objeto de la clase de la BBDD
    public SaveDataBase bd;

    // Instanciamos un objeto para poder implementar Data Binding
    ActivityShowIncomeBinding showIncomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implementamos DataBinding
        showIncomeBinding = ActivityShowIncomeBinding.inflate(getLayoutInflater());
        setContentView(showIncomeBinding.getRoot());

        // Este layout tiene un apartado llamado RyclerView, el cual va a mostrar layouts externos
        // que van a rellenarse con la información de la BBDD, a más registros en la tabla Income, más layouts
        // externos aparecerán en el RyclerView (como si fuera un ArrayList de layouts)
        showIncomeBinding.incomeRecycler.setLayoutManager(new LinearLayoutManager(this));

        // Muy importante esta línea para evitar el error NullPointerException
        bd = SaveDataBase.getDatabase(getApplicationContext());

        // Instanciamos un objeto de tipo IncomeDao par apoder acceder a los métodos CRUD de la tabla
        // Income
        IncomeDao incomeDao = bd.incomeDao();

        // Creamos un ArrayList de tipo Income que va a recoger todos los registros de la tabla
        ArrayList<Income> listaIngresos = (ArrayList<Income>) incomeDao.getAll();

        // Revertimos el orden de la lista, para mostrar los registros más actuales los primeros
        Collections.reverse(listaIngresos);

        // Cremos un objeto auxiliar Adapter que va a permitir mostrar los layouts externos en el RecyclerView
        IncomeAdapter adapter = new IncomeAdapter();
        showIncomeBinding.incomeRecycler.setAdapter(adapter);

        // La información mostrada en el RecyclerView se completará con la información de la BBDD
        // (guardadao anteriormente en un ArrayList)
        adapter.submitList(listaIngresos);

        // Si la lista de tipo Income está vacía (no hay nada que mostrar en el RyclerView porque no hay
        // ningín ingreso), cambiamos la visibilidad de un mensaje que indique al usuario que
        // todavía no se ha registrado ningún ingreso
        if (listaIngresos.isEmpty()) {
            showIncomeBinding.emptyView.setVisibility(View.VISIBLE);
        } else {
            // Si hay algún registro en la tabla, ocultamos el mensaje
            showIncomeBinding.emptyView.setVisibility(View.GONE);
        }

    }

}