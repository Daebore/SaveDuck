package com.example.saveduck;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saveduck.dataBase.Expense;
import com.example.saveduck.databinding.SpentListItemBinding;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class SpentAdapter extends ListAdapter<Expense, SpentAdapter.SpentViewHolder> {

    public static final DiffUtil.ItemCallback<Expense> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Expense>() {
                @Override
                public boolean areItemsTheSame(@NonNull Expense oldItem, @NonNull Expense newItem) {
                    if(oldItem.fechaGasto == newItem.fechaGasto){
                        return false;
                    }
                    return true;
                }
                @Override
                public boolean areContentsTheSame(@NonNull Expense oldItem, @NonNull Expense newItem) {
                    if(oldItem.fechaGasto == newItem.fechaGasto){
                        return false;
                    }
                    return true;
                }
            };

    protected SpentAdapter() {
        super(DIFF_CALLBACK);
    }


    @NonNull
    @Override
    public SpentAdapter.SpentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SpentListItemBinding spentListItemBinding = SpentListItemBinding.inflate(LayoutInflater.from(parent.getContext()));

        return new SpentViewHolder(spentListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SpentViewHolder holder, int position) {
        Expense expense = getItem(position);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.bind(expense);
        }
    }

    class SpentViewHolder extends RecyclerView.ViewHolder{

        private SpentListItemBinding spentListItemBinding;

        public SpentViewHolder(@NonNull SpentListItemBinding spentListItemBinding) {
            super(spentListItemBinding.getRoot());
            this.spentListItemBinding = spentListItemBinding;

        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void bind(Expense expense) {


            OffsetDateTime o = OffsetDateTime.now();
            ZoneOffset l = o.getOffset();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                spentListItemBinding.textoFechaIngreso.setText(expense.getFechaIngreso(l).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + " ");

                spentListItemBinding.textoIngreso.setText(expense.gastoDinero + "€");
                spentListItemBinding.textoConcepto.setText(expense.conceptoGasto);

                spentListItemBinding.executePendingBindings();

            }
        }
    }
}
