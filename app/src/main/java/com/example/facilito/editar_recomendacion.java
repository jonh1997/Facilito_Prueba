package com.example.facilito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class editar_recomendacion extends AppCompatActivity {
    TextView tv_prueba,tv_correo;
    TextInputEditText nombre,dir,tel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_recomendacion);
        nombre=findViewById(R.id.txt_nombre_recom);
        dir=findViewById(R.id.txt_dir_recom);
        tel=findViewById(R.id.txt_tele_recom);
        tv_prueba=findViewById(R.id.tv_recome);
        tv_correo=findViewById(R.id.tv_correo);
        String id=getIntent().getStringExtra("id");
        String correo=getIntent().getStringExtra("correo");
        tv_prueba.setText(id);
        tv_correo.setText(correo);

        editar_cliente();

    }
    public void editar_cliente(){

        AdminBD admin=new AdminBD(this, "recomendados",null, 1);
        SQLiteDatabase BaseDeDatos=admin.getWritableDatabase();
        String id=tv_prueba.getText().toString();
        String correo=tv_correo.getText().toString();
        Cursor dato=BaseDeDatos.rawQuery(
                "select nombre,direccion,telefono from recomendados where  id='"+id+"'", null);

       try {
            if (dato.moveToFirst()){

                String nombreQ=(dato.getString(0));
                String dirQ=(dato.getString(1));
                String telefonoQ=(dato.getString(2));
                nombre.setText(nombreQ);
                dir.setText(dirQ);
                tel.setText(telefonoQ);
                BaseDeDatos.close();
            }
        }catch (Exception e){
            Toast.makeText(this,"Algo salio mal",Toast.LENGTH_LONG).show();
        }
    }
    public  void guardar_Cliente(View view){
        AdminBD admin=new AdminBD(this,"recomendados",null,1);
        SQLiteDatabase BaseDeDatos=admin.getWritableDatabase();
        String id=tv_prueba.getText().toString();
        String correo=tv_correo.getText().toString();
        String nombre_edit=nombre.getText().toString();
        String direcion_edit=dir.getText().toString();
        String telefono_edit=tel.getText().toString();
   try {

        if (!nombre_edit.isEmpty() && !direcion_edit.isEmpty() && !telefono_edit.isEmpty() && !correo.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("correo", correo);
            registro.put("nombre", nombre_edit);
            registro.put("direccion", direcion_edit);
            registro.put("telefono", telefono_edit);

            int cant = BaseDeDatos.update("recomendados", registro, "correo='" + correo + "' and id='"+id+"'", null);
            BaseDeDatos.close();
            if (cant == 1) {
                Toast.makeText(this, "Modificacion correcta", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "El dato no existe", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Debe de llenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }catch (Exception e){
        Toast.makeText(this,"Vaya algo salio mal",Toast.LENGTH_LONG).show();
    }
    }
    public void cerrar_edit(View v){
        finish();
    }
}