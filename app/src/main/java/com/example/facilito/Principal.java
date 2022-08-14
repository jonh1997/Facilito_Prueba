package com.example.facilito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity {
    private Button btnext,btn_ingreso;
    private EditText txt_correo,txt_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        txt_correo=findViewById(R.id.txt_usuario);
        txt_pass=findViewById(R.id.txt_pass);
       btnext=(Button) findViewById(R.id.btn2);
       btn_ingreso=findViewById(R.id.btn1);

    }
    public void Registrar(View view){
        Intent intent=new Intent(this, Registro.class);
        startActivity(intent);
    }
    public void buscar(View view){
        AdminBD admin=new AdminBD(this, "administracion",null, 1);
        SQLiteDatabase BaseDeDatos=admin.getWritableDatabase();
        String correo_recuperado=txt_correo.getText().toString().trim();
        String password=txt_pass.getText().toString().trim();
      //tv_prueva.setText(correo_recuperado);
        if (!correo_recuperado.isEmpty() && !password.isEmpty()){
            Cursor dato=BaseDeDatos.rawQuery(
                    "select nombre,correo from usuarios where correo ='"+correo_recuperado+"' and password='"+password+"'", null);
            if(dato.moveToFirst()){
                String nameQuery=(dato.getString(0));
                String correoQuery=(dato.getString(1));
                Intent intent=new Intent(this, usuario.class);
                intent.putExtra("nombre",nameQuery);
                intent.putExtra("correo",correoQuery);
                startActivity(intent);
                BaseDeDatos.close();
            }else {
                Toast.makeText(this,"Usuario o Contraseña invalidos",Toast.LENGTH_LONG).show();
                BaseDeDatos.close();
            }

        }else{
            Toast.makeText(this,"llene los campos",Toast.LENGTH_LONG).show();
        }

    }
    public void limpiar(){

    }
}