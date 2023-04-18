package com.example.saveduck;

import android.content.Context;
import android.widget.Toast;

// Esta clase nos va a servir para poder crear objetos que funcinoen como Toast (mensajes emergentes que podemos mostrar
// a los usuarios), diseñando nuestros própios métodos para ello
public class AppToast {

        public static Toast toast = null;

        // Este método va a permitirnos mostrar los mensajes a los usuarios. Los parámetros que
        // debemos pasarle son los siguientes: el contexto donde va a usarse (en nuestro caso,
        // el activity, si es el mismo se usará .this), el mensaje a mostrar y el tiempo
        // que va a estar visible el mensaje
        public static void showMessage(final Context context, CharSequence text, int duration){
            if(toast != null){
                toast.cancel();
                toast = null;
                showMessage(context, text, duration);
            }else{
                toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    // Usaremos esta clase para mostrar los mensajes en lugar de objetos de tipo Toast, ya que no
    // congelan la aplicación como los recién nombrados y evitan errores en pruebas de estrés
}
