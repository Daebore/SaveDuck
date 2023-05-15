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

    public SaveDataBase bd;

    ActivityShowIncomeBinding showIncomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showIncomeBinding = ActivityShowIncomeBinding.inflate(getLayoutInflater());

        setContentView(showIncomeBinding.getRoot());

        showIncomeBinding.incomeRecycler.setLayoutManager(new LinearLayoutManager(this));

        bd = SaveDataBase.getDatabase(getApplicationContext());
        IncomeDao incomeDao = bd.incomeDao();
        Income income = incomeDao.getLatest();

        ArrayList<Income> listaIngresos = (ArrayList<Income>) incomeDao.getAll();
        Collections.reverse(listaIngresos);

        IncomeAdapter adapter = new IncomeAdapter();
        showIncomeBinding.incomeRecycler.setAdapter(adapter);
        adapter.submitList(listaIngresos);

        if (listaIngresos.isEmpty()) {
            showIncomeBinding.emptyView.setVisibility(View.VISIBLE);
        } else {
            showIncomeBinding.emptyView.setVisibility(View.GONE);
        }

    }

}