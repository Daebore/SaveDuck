package com.example.saveduck;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saveduck.dataBase.SaveDataBase;
import com.example.saveduck.dataBase.User;
import com.example.saveduck.dataBase.UserDao;
import com.example.saveduck.databinding.ActivityCreateAccountBinding;

public class CreateAccountActivity extends AppCompatActivity {

// Declaración de objetos a utilizar:

    // Un objeto de tipo de la base de datos para poder acceder a ella
    public SaveDataBase bd;

    // Un objeto para implementar el Data Binding
    ActivityCreateAccountBinding createBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implementamos DataBinding
        createBinding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(createBinding.getRoot());

        // Si pulsamos en el botonRegistrar, creamos una cuenta y guardamos los datos en la BBDD
        createBinding.botonRegistrar.setOnClickListener(v -> {

            // Cogemos todos los datos de sus respectivos campos de texto y las guardamos en variables
            String nombre = createBinding.etNombreUsuario.getText().toString();
            String correo = createBinding.etCorreo.getText().toString();

            // Aunque los ingresos estén declarados como double en la BBDD, al tratarse de un campo
            // de texto, tenemos que recogerlos como String para posteriormente castearlos
            String ingresosString = createBinding.etIngresosIni.getText().toString();

            // En esta variable guardaremos los ingresos casteados a double
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
            }else if(nombre.isEmpty()) {
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
            }else if(nombre.length() > 10){
                // Si el nombre es demasiado largo el mensaje correspondiente
                Log.d("Create_view", "El tamaño del nombre no puede superar los 10 caracteres");
                AppToast.showMessage(this, "El tamaño del nombre no puede superar los 10 caracteres", Toast.LENGTH_SHORT);
            }else if(nombre.toLowerCase().contains("select") || nombre.toLowerCase().contains("delete") || nombre.toLowerCase().contains("drop")
                    || nombre.toLowerCase().contains("insert") || nombre.toLowerCase().contains("update")){
                // Para evitar SQL Injections
                Log.d("Create_view", "Nombre no permitido");
                AppToast.showMessage(this, "Nombre no permitido", Toast.LENGTH_SHORT);
            }else if(correo.length() > 35){
                // Si solo el correo es demasiado largo el mensaje correspondiente
                Log.d("Create_view", "El tamaño del correo no puede superar los 35 caracteres");
                AppToast.showMessage(this, "El tamaño del correo no puede superar los 35 caracteres", Toast.LENGTH_SHORT);
            }else if(correo.toLowerCase().contains("select") || correo.toLowerCase().contains("delete") || correo.toLowerCase().contains("drop")
                    || correo.toLowerCase().contains("insert") || correo.toLowerCase().contains("update")){
                // Para evitar SQL Injections o formato de correo no válido
                Log.d("Create_view", "Nombre de correo no permitido");
                AppToast.showMessage(this, "Nombre de correo no permitido", Toast.LENGTH_SHORT);
            }else if(!correo.toLowerCase().contains("gmail.com") && !correo.toLowerCase().contains("gmail.es")
                    && !correo.toLowerCase().contains("hotmail.com") && !correo.toLowerCase().contains("hotmail.es")
                    && !correo.toLowerCase().contains("outlook.com") && !correo.toLowerCase().contains("outlook.es")){
                // Para que asegurar que el formato de correo es válido
                Log.d("Create_view", "Formato de correo no válido");
                AppToast.showMessage(this, "Formato de correo no válido", Toast.LENGTH_SHORT);
            }else if(ingresosDouble != 0 && ingresosDouble > 1000000){
                // Para evitar desbordar la variable
                Log.d("Create_view", "Los ingresos iniciales no pueden superar los 1000000€");
                AppToast.showMessage(this, "Los ingresos iniciales no pueden superar los 1000000€", Toast.LENGTH_SHORT);
            }else{
                // Mostramos un mensaje indicando que la cuenta se ha creado correctamente
                Log.d("Create_view", "La cuenta ha sido creada");
                AppToast.showMessage(this, "La cuenta ha sido creada", Toast.LENGTH_SHORT);

                // Si todas las condiciones impuestas anteriormente se han complido, invocamos el método
                // que va a crear un User y lo va a guardar en la BBDD
                guardarEnBD(nombre, correo, ingresosDouble);

                // Si todos los campos obligatorios han sido rellenados, invocamos el método que nos
                // lleva al MainActivity
                openMain();
            }

        });
    }

    // Este métodos nos va a permitir crear un objeto de tipo User y guardarlo en la BBDD
    public void guardarEnBD(String nombre, String correo, double ingresos) {
        // Inicializamos el objeto de la BBDD y el userDao (el cual nos va a permitir realizar
        // las funciones CRUD)
        bd = SaveDataBase.getDatabase(getApplicationContext());
        UserDao userDao = bd.userDao();

        // Insertamos el nuevo usuario en la tabla User
        userDao.insertAll(new User(nombre, correo, ingresos));
    }

    // Función que abre el MainActivity
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

}