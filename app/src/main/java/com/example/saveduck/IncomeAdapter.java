package com.example.saveduck;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saveduck.dataBase.Income;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IncomeAdapter extends ListAdapter<Income, IncomeAdapter.IncomeViewHolder> {

    public static final DiffUtil.ItemCallback<Income> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Income>() {
        @Override
        public boolean areItemsTheSame(@NonNull Income oldItem, @NonNull Income newItem) {
            if(oldItem.fechaIngreso == newItem.fechaIngreso){
                return false;
            }
            return true;
        }
        @Override
        public boolean areContentsTheSame(@NonNull Income oldItem, @NonNull Income newItem) {
            if(oldItem.fechaIngreso == newItem.fechaIngreso){
                return false;
            }
            return true;
        }
    };

    protected IncomeAdapter() {
        super(DIFF_CALLBACK);
    }


    @NonNull
    @Override
    public IncomeAdapter.IncomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_list_item, parent, false);

        return new IncomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeAdapter.IncomeViewHolder holder, int position) {
        Income income = getItem(position);

        holder.bind(income);
    }

    class IncomeViewHolder extends RecyclerView.ViewHolder{

        private TextView textoFechaIngreso;
        private TextView textoIngreso;
        private TextView textoConcepto;

        public IncomeViewHolder(@NonNull View itemView) {
            super(itemView);

            textoFechaIngreso = itemView.findViewById(R.id.textoFechaIngreso);
            textoIngreso = itemView.findViewById(R.id.textoIngreso);
            textoConcepto = itemView.findViewById(R.id.textoConcepto);
        }

        public void bind(Income income) {

            String dateTime = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                dateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy, hh:mm:ss a")
                        .format(LocalDateTime.now());
            }

            textoFechaIngreso.setText(dateTime);
            textoIngreso.setText(String.valueOf(income.ingresoDinero) + "€");
            textoConcepto.setText(income.conceptoIngreso);
        }
    }
}
