package com.example.facilito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class recomendar extends AppCompatActivity {
    TextInputEditText nombre_recom,dire_recom,tele_recom;
    TextView tv_prueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendar);
        nombre_recom=findViewById(R.id.txt_nombre_recom);
        dire_recom=findViewById(R.id.txt_dir_recom);
        tele_recom=findViewById(R.id.txt_tele_recom);
        tv_prueba=findViewById(R.id.tv_prueba);
        String user=getIntent().getStringExtra("nombre");
        tv_prueba.setText(user);

    }
    public void recomendar(View v){
        String nombre_Original=tv_prueba.getText().toString();
       AdminBD admin=new AdminBD(this, "recomendados", null, 1);
        SQLiteDatabase BaseDeDatos=admin.getWritableDatabase();

        String nombre=nombre_recom.getText().toString();
        String direccion=dire_recom.getText().toString();
        String telefono=tele_recom.getText().toString();

        if (!nombre_Original.isEmpty() && !nombre.isEmpty() && !direccion.isEmpty() && !telefono.isEmpty()){
            ContentValues registro=new ContentValues();
            registro.put("correo",nombre_Original);
            registro.put("nombre",nombre);
            registro.put("direccion",direccion);
            registro.put("telefono",telefono);
            BaseDeDatos.insert("recomendados", null, registro);
            Toast.makeText(this, "Persona Agregada",Toast.LENGTH_LONG).show();
            BaseDeDatos.close();
            finish();
        }else   {
            Toast.makeText(this,"Llene todos los campos",Toast.LENGTH_LONG).show();
        }

    }

}