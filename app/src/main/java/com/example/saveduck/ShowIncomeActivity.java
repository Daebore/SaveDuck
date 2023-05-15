package com.example.saveduck;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
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

        if(listaIngresos.isEmpty()){
            showIncomeBinding.emptyView.setVisibility(View.VISIBLE);
        }else{
            showIncomeBinding.emptyView.setVisibility(View.GONE);
        }

    }

    // Método para mostrar animación Fade al ir hacia atrás
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent = new Intent(this, BackgroundActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(ShowIncomeActivity.this).toBundle();
            startActivity(intent, bundle);
        }else{
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}