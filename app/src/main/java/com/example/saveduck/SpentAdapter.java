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

// La lógica de esta clase está sacada de la documentación de Android y su funcionalidad va a ser permitir
// instanciar objetos de tipo adapter que van a tener acceso a una serie de métodos que van a permitir
// crear layouts con información de la BBDD y a su vez estos layouts van a mostrarse según se generen
// en el RecyclerView
public class SpentAdapter extends ListAdapter<Expense, SpentAdapter.SpentViewHolder> {

    // Este método es necesario para poder mostrar los layouts en el RecyclerView y va a encargarse
    // de que no haya elementos repetidos que mostrar, aunque en nuestro caso no es algo demasiado
    // importante, ya que la lógica de las PK de la BBDD va a impedir que esto ocurra
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

    // Método constructor
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

    // Este método va a encargarse de mostrar la información en los elementos del Layout
    class SpentViewHolder extends RecyclerView.ViewHolder{

        // Implementamos Data Binding
        private SpentListItemBinding spentListItemBinding;

        public SpentViewHolder(@NonNull SpentListItemBinding spentListItemBinding) {
            super(spentListItemBinding.getRoot());
            this.spentListItemBinding = spentListItemBinding;

        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void bind(Expense expense) {

            // Para poder mostrar las fecha en el formato europeo que deseamos, primero creamos un
            // objeto de tipo OffsetDateTime y sacamos la fecha actual con .now()
            OffsetDateTime o = OffsetDateTime.now();

            // Después creamos un objeto de tipo ZoneOffset, pasándole el objeto anterior y utilizando
            // el método getOffset
            ZoneOffset l = o.getOffset();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                // No mostramos la fecha en la que se hizo el ingreso en la BBDD directamente, invocamos
                // el método getFechaIngreso que nos pide por parámetro un ZoneOffset con el contexto
                // horario de nuestra región. Una vez creado y pasado el objeto, podemos elegir el formato
                // en el que queremos mostrar la fecha
                spentListItemBinding.textoFechaIngreso.setText(expense.getFechaIngreso(l).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + " ");

                // Mostramos el resto de información de la BBDD
                spentListItemBinding.textoIngreso.setText(expense.gastoDinero + "€");
                spentListItemBinding.textoConcepto.setText(expense.conceptoGasto);

                // Esta línea nos permite asegurarnos de que los datos se muestran como queremos
                spentListItemBinding.executePendingBindings();

            }
        }
    }
}
