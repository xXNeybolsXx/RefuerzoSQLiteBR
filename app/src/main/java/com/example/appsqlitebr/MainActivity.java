package com.example.appsqlitebr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Instanciar La Clase clsBD
    clsDb ohDb = new clsDb(this,"dbLibrary",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanciar Y Referenciar Los ID's Del Archivo Xml (activity_main.xml)

        EditText fullname = findViewById(R.id.etfullname);
        EditText email = findViewById(R.id.etemail);
        EditText password = findViewById(R.id.etpassword);
        ImageButton btnsave = findViewById(R.id.btnsave);
        ImageButton btnsearch = findViewById(R.id.btnsearch);
        ImageButton btnupdate = findViewById(R.id.btnupdate);
        ImageButton btndelete = findViewById(R.id.btndelete);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser(fullname.getText().toString(), email.getText().toString(), password.getText().toString());
                
            }
        });

    }

    private void saveUser(String sfullname, String semail, String spassword) {

        //Instanciar La Clase clsBD
        //clsDb ohDb = new clsDb(this,"dbLibrary",null,1);
        //Instanciar La Base De Datos En Modo De Escritura (INSERT, UPDATE, DELETE):
        SQLiteDatabase db = ohDb.getWritableDatabase();

        //try Sirve Para El Manejo De Excepciones
        try{

            //Crear Un Objeto De ContentValues Para Que Contenga Los Mismos Campos De Tabla Tabla User
            ContentValues cvUser = new ContentValues();
            cvUser.put("fullname",sfullname);
            cvUser.put("email",semail);
            cvUser.put("Password",spassword);
            //Guardar En La Tabla user Lo Que Contiene cvUser
            db.insert("user",null,cvUser);
            db.close();
            //db.close(); //Para Cerrar La Base De Datos
            Toast.makeText(getApplicationContext()," Usuario Guardado Exitosamente.... ",Toast.LENGTH_LONG).show();

        }
        catch (Exception e){

            Toast.makeText(getApplicationContext()," Error: " + e.getMessage(),Toast.LENGTH_LONG).show();

        }


    }
}