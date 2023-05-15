package com.example.saveduck;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saveduck.dataBase.Income;
import com.example.saveduck.databinding.IncomeListItemBinding;

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
       IncomeListItemBinding incomeListItemBinding = IncomeListItemBinding.inflate(LayoutInflater.from(parent.getContext()));

        return new IncomeViewHolder(incomeListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeAdapter.IncomeViewHolder holder, int position) {
        Income income = getItem(position);

        holder.bind(income);
    }

    class IncomeViewHolder extends RecyclerView.ViewHolder{

        private IncomeListItemBinding incomeListItemBinding;

        public IncomeViewHolder(@NonNull IncomeListItemBinding incomeListItemBinding) {
            super(incomeListItemBinding.getRoot());
            this.incomeListItemBinding = incomeListItemBinding;

        }

        public void bind(Income income) {

            String dateTime = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                dateTime = DateTimeFormatter.ofPattern("dd/MM/yy, hh:mm:ss a")
                        .format(LocalDateTime.now());
            }

            incomeListItemBinding.textoFechaIngreso.setText(dateTime);
            incomeListItemBinding.textoIngreso.setText(income.ingresoDinero + "€");
            incomeListItemBinding.textoConcepto.setText(income.conceptoIngreso);

            incomeListItemBinding.executePendingBindings();

        }
    }
}
