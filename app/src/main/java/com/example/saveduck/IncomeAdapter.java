package com.example.saveduck;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saveduck.dataBase.Income;
import com.example.saveduck.databinding.IncomeListItemBinding;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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
    public void onBindViewHolder(@NonNull IncomeViewHolder holder, int position) {
        Income income = getItem(position);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.bind(income);
        }
    }

    class IncomeViewHolder extends RecyclerView.ViewHolder{

        private IncomeListItemBinding incomeListItemBinding;

        public IncomeViewHolder(@NonNull IncomeListItemBinding incomeListItemBinding) {
            super(incomeListItemBinding.getRoot());
            this.incomeListItemBinding = incomeListItemBinding;

        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void bind(Income income) {


            OffsetDateTime o = OffsetDateTime.now();
            ZoneOffset l = o.getOffset();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                incomeListItemBinding.textoFechaIngreso.setText(income.getFechaIngreso(l).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + " ");

                incomeListItemBinding.textoIngreso.setText(income.ingresoDinero + "€");
                incomeListItemBinding.textoConcepto.setText(income.conceptoIngreso);

                incomeListItemBinding.executePendingBindings();

            }
        }
    }
}
