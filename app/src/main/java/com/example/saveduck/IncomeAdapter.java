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

// La lógica de esta clase está sacada de la documentación de Android y su funcionalidad va a ser permitir
// instanciar objetos de tipo adapter que van a tener acceso a una serie de métodos que van a permitir
// crear layouts con información de la BBDD y a su vez estos layouts van a mostrarse según se generen
// en el RecyclerView
public class IncomeAdapter extends ListAdapter<Income, IncomeAdapter.IncomeViewHolder> {

    // Este método es necesario para poder mostrar los layouts en el RecyclerView y va a encargarse
    // de que no haya elementos repetidos que mostrar, aunque en nuestro caso no es algo demasiado
    // importante, ya que la lógica de las PK de la BBDD va a impedir que esto ocurra
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

    // Método constructor
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

    // Este método va a encargarse de mostrar la información en los elementos del Layout
    class IncomeViewHolder extends RecyclerView.ViewHolder{

        // Implementamos Data Binding
        private IncomeListItemBinding incomeListItemBinding;

        public IncomeViewHolder(@NonNull IncomeListItemBinding incomeListItemBinding) {
            super(incomeListItemBinding.getRoot());
            this.incomeListItemBinding = incomeListItemBinding;

        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void bind(Income income) {

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
                incomeListItemBinding.textoFechaIngreso.setText(income.getFechaIngreso(l).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + " ");

                // Mostramos el resto de información de la BBDD
                incomeListItemBinding.textoIngreso.setText(income.ingresoDinero + "€");
                incomeListItemBinding.textoConcepto.setText(income.conceptoIngreso);

                // Esta línea nos permite asegurarnos de que los datos se muestran como queremos
                incomeListItemBinding.executePendingBindings();

            }
        }
    }
}
