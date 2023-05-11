package com.example.saveduck;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saveduck.dataBase.SaveDataBase;
import com.example.saveduck.dataBase.User;
import com.example.saveduck.dataBase.UserDao;
import com.example.saveduck.databinding.ActivityCreateAccountBinding;

public class CreateAccountActivity extends AppCompatActivity {

    // Instanciamos un objeto de la clase de la BBDD y 1 objeto de la tabla User
    public SaveDataBase bd;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implementamos DataBinding
        ActivityCreateAccountBinding createBinding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(createBinding.getRoot());

        // Si pulsamos en el botonRegistrar, creamos una cuenta y guardamos los datos en la BBDD
        createBinding.botonRegistrar.setOnClickListener(v -> {

            // Cogemos todos los datos de sus respectivos campos de texto y las guardamos en variables
            String nombre = createBinding.etNombreUsuario.getText().toString();
            String correo = createBinding.etCorreo.getText().toString();
            String ingresosString = createBinding.etIngresosIni.getText().toString();
            double ingresosDouble;

            // EL usuario puede no tener ingresos iniciales al instalar la App, sin embargo, el campo
            // de la tabla no debe estar nulo para evitar problemas, así que si el usuario no ha indicado
            // ingresos iniciales, los inicializamos a 0
            if(createBinding.etIngresosIni.getText().toString().isEmpty()){
                ingresosDouble = 0;
            }else{
                ingresosDouble = Double.parseDouble(ingresosString);
            }

            if(nombre.isEmpty() && correo.isEmpty()){
                // Este log nos sirve para debuggear. Además, utilizamos una instancia de la clase
                // AppToast que nos va a permitir mostrar invocar el método showMessage que va a
                // permitirnos mostrar mensaje al usuario por pantalla
                Log.d("Create_view", "Los campos Nombre y Dirección de correo no pueden estar vacíos");
                AppToast.showMessage(this, "Los campos Nombre y Dirección de correo no pueden estar vacíos", Toast.LENGTH_SHORT);

            }else if(nombre.isEmpty()){
                // Si solo el nombre está vacío mostramos el mensaje correspondiente
                Log.d("Create_view", "El campo Nombre no puede estar vacío");
                AppToast.showMessage(this, "El campo Nombre no puede estar vacío", Toast.LENGTH_SHORT);

            }else if(correo.isEmpty()){
                // Si solo el correo está vacío mostramos el mensaje correspondiente
                Log.d("Create_view", "El campo Nombre no puede estar vacío");
                AppToast.showMessage(this, "El campo Dirección de correo no puede estar vacío", Toast.LENGTH_SHORT);

            }else if(!createBinding.checkBoxAceptar.isChecked()){
                // Si el el checkBox está vacía mostramos el mensaje correspondiente
                Log.d("Create_view", "Debes aceptar los términos y condiciones de privacidad");
                AppToast.showMessage(this, "Debes aceptar los términos y condiciones de privacidad", Toast.LENGTH_SHORT);

            }else{
                // Mostramos un mensaje indicando que la cuenta se ha creado correctamente
                Log.d("Create_view", "La cuenta ha sido creada");
                AppToast.showMessage(this, "La cuenta ha sido creada", Toast.LENGTH_SHORT);

                guardarEnBD(nombre, correo, ingresosDouble);

                // Si todos los campos obligatorios han sido rellenados, invocamos el método que nos
                // lleva al MainActivity
                openMain();
            }

        });
    }

    public void guardarEnBD(String nombre, String correo, double ingresos) {
        // Inicializamos el objeto de la BBDD y el userDao (el cual nos va a permitir realizar
        // las funciones CRUD)
        bd = SaveDataBase.getDatabase(getApplicationContext());
        userDao = bd.userDao();

        // Insertamos el nuevo usuario en la tabla User
        userDao.insertAll(new User(nombre, correo, ingresos));
    }

    // Función que abre el MainActivity
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(CreateAccountActivity.this).toBundle();
            startActivity(intent, bundle);
        }else{
            startActivity(intent);
        }
        this.finish();
    }

}