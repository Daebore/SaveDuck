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

    public SaveDataBase bd;

    ActivityShowSpentBinding showSpentBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showSpentBinding = ActivityShowSpentBinding.inflate(getLayoutInflater());

        setContentView(showSpentBinding.getRoot());

        showSpentBinding.incomeRecycler2.setLayoutManager(new LinearLayoutManager(this));

        bd = SaveDataBase.getDatabase(getApplicationContext());
        ExpenseDao expenseDao = bd.expenseDao();
        Expense expense = expenseDao.getLatest();

        ArrayList<Expense> listaGastos = (ArrayList<Expense>) expenseDao.getAll();
        Collections.reverse(listaGastos);

        SpentAdapter adapter = new SpentAdapter();
        showSpentBinding.incomeRecycler2.setAdapter(adapter);
        adapter.submitList(listaGastos);

        if (listaGastos.isEmpty()) {
            showSpentBinding.emptyView2.setVisibility(View.VISIBLE);
        } else {
            showSpentBinding.emptyView2.setVisibility(View.GONE);
        }


    }
}