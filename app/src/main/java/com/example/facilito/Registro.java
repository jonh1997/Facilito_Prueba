package com.example.facilito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {
     TextInputEditText ed_nombre;
     TextInputEditText ed_direccion;
    TextInputEditText ed_edad;
     TextInputEditText ed_telefono;
     TextInputEditText ed_correo;
     TextInputEditText ed_clave;
    Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Button cancelar_registro=findViewById(R.id.btn_cancelar_edit);
        ed_nombre=findViewById(R.id.txt_nombre_edit);
        ed_direccion=findViewById(R.id.txt_dir_edit);
        ed_edad=findViewById(R.id.txt_edad_edit);
        ed_telefono=findViewById(R.id.txt_phone_edit);
        ed_correo=findViewById(R.id.txt_correo);
        ed_clave=findViewById(R.id.txt_password_edit);
        btn_register=findViewById(R.id.btn_editar);


        cancelar_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

    private void mensaje() {
        Toast.makeText(this,"LLenaste todos los campos",Toast.LENGTH_LONG).show();
    }

    private void register() {
        AdminBD admin=new AdminBD(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos=admin.getWritableDatabase();
        String nombre=ed_nombre.getText().toString().trim();
        String direccion=ed_direccion.getText().toString().trim();
        String edad=ed_edad.getText().toString().trim();
        String telefono_String=ed_telefono.getText().toString().trim();
        String correo_String=ed_correo.getText().toString().trim();
        String clave_String=ed_clave.getText().toString().trim();

        if (!nombre.isEmpty() && !direccion.isEmpty() && !edad.isEmpty() && !telefono_String.isEmpty() && !correo_String.isEmpty() && !clave_String.isEmpty()){
            if (EmailValidacion(correo_String)){
                ContentValues registro=new ContentValues();
                registro.put("nombre",nombre);
                registro.put("direccion",direccion);
                registro.put("edad",edad);
                registro.put("telefono",telefono_String);
                registro.put("correo",correo_String);
                registro.put("password",clave_String);
                BaseDeDatos.insert("usuarios", null, registro);
                BaseDeDatos.close();
                Toast.makeText(this,"Ingresado",Toast.LENGTH_LONG).show();
                finish();

            }else{
                Toast.makeText(this,"LLenaste todos los campos, correo invalido",Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(this,"LLene todos los campos",Toast.LENGTH_LONG).show();
        }
    }

    public boolean EmailValidacion(String email){
        String expreciones= "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern= Pattern.compile(expreciones,Pattern.CASE_INSENSITIVE);
        Matcher matcher=pattern.matcher(email);
        return matcher.matches();
    }

}