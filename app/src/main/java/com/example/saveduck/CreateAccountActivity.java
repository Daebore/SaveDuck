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

            String nombre = createBinding.etNombreUsuario.getText().toString();
            String correo = createBinding.etCorreo.getText().toString();
            String ingresosIniString = createBinding.etIngresosIni.getText().toString();
            int ingresosIniInt = Integer.parseInt(ingresosIniString);

            if(nombre.isEmpty() && correo.isEmpty()){
                // Este log nos sirve para debuggear. Además, utilizamos una instancia de la clase
                // AppToast que nos va a permitir mostrar invocar el método showMessage que va a
                // permitirnos mostrar mensaje al usuario por pantalla
                Log.d("Quest_view", "Los campos Nombre y Dirección de correo no pueden estar vacíos");
                AppToast.showMessage(this, "Los campos Nombre y Dirección de correo no pueden estar vacíos", Toast.LENGTH_SHORT);

            }else if(nombre.isEmpty()){
                // Si solo el nombre está vacío mostramos el mensaje correspondiente
                Log.d("Quest_view", "El campo Nombre no puede estar vacío");
                AppToast.showMessage(this, "El campo Nombre no puede estar vacío", Toast.LENGTH_SHORT);

            }else if(correo.isEmpty()){
                // Si solo el correo está vacío mostramos el mensaje correspondiente
                Log.d("Quest_view", "El campo Nombre no puede estar vacío");
                AppToast.showMessage(this, "El campo Dirección de correo no puede estar vacío", Toast.LENGTH_SHORT);

            }else if(!createBinding.checkBoxAceptar.isChecked()){
                // Si el el checkBox está vacía mostramos el mensaje correspondiente
                Log.d("Quest_view", "Debes aceptar los términos y condiciones de privacidad");
                AppToast.showMessage(this, "Debes aceptar los términos y condiciones de privacidad", Toast.LENGTH_SHORT);

            }else{
                // Mostramos un mensaje indicando que la cuenta se ha creado correctamente
                Log.d("Quest_view", "La cuenta ha sido creada");
                AppToast.showMessage(this, "La cuenta ha sido creada", Toast.LENGTH_SHORT);

                guardarEnBD(nombre, correo, ingresosIniInt);

                // Si todos los campos obligatorios han sido rellenados, invocamos el método que nos
                // lleva al MainActivity
                openMain();
            }

        });
    }

    public void guardarEnBD(String nombre, String correo, double ingresosIniInt) {
        userDao.insertAll(new User(nombre, correo, ingresosIniInt));
    }

    // Función que abre el MainActivity
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}