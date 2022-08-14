package com.example.facilito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class editar extends AppCompatActivity {

    TextInputEditText nombre,direcion,edad,telefono,correo,contra;
    Button btEditar,btCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        nombre=findViewById(R.id.txt_nombre_edit);
        direcion=findViewById(R.id.txt_dir_edit);
        edad=findViewById(R.id.txt_edad_edit);
        telefono=findViewById(R.id.txt_phone_edit);
        correo=findViewById(R.id.txt_correo_edit);
        contra=findViewById(R.id.txt_password_edit);
        btEditar=findViewById(R.id.btn_editar);
        btCancelar=findViewById(R.id.btn_cancelar_edit);
        cargar();
        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar();
            }
        });
btCancelar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        finish();
    }
});
    }
    public void cargar(){
        String nombre_edit=getIntent().getStringExtra("nombre");
        String direcion_edit=getIntent().getStringExtra("direccion");
        String edad_edit=getIntent().getStringExtra("edad");
        String telefono_edit=getIntent().getStringExtra("telefono");
        String Correo_edit=getIntent().getStringExtra("correo");
        String contra_edit=getIntent().getStringExtra("contra");

        nombre.setText(nombre_edit);
        direcion.setText(direcion_edit);
        edad.setText(edad_edit);
        telefono.setText(telefono_edit);
        correo.setText(Correo_edit);
        contra.setText(contra_edit);

    }
    public  void editar(){
        AdminBD admin=new AdminBD(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos=admin.getWritableDatabase();

        String nombre_edit=nombre.getText().toString();
        String direcion_edit=direcion.getText().toString();
        String edad_edit=edad.getText().toString();
        String telefono_edit=telefono.getText().toString();
        String correo_edit=correo.getText().toString();
        String contra_edit=contra.getText().toString();

        if (!nombre_edit.isEmpty() && !direcion_edit.isEmpty() && !edad_edit.isEmpty() && !telefono_edit.isEmpty() && !contra_edit.isEmpty()){
            ContentValues registro= new ContentValues();
            registro.put("nombre",nombre_edit);
            registro.put("direccion",direcion_edit);
            registro.put("edad",edad_edit);
            registro.put("telefono",telefono_edit);
            registro.put("correo",correo_edit);
            registro.put("password",contra_edit);
            int  cant=BaseDeDatos.update("usuarios",registro,"correo='"+correo_edit+"'",null);
            BaseDeDatos.close();
            if (cant==1){
                Toast.makeText(this,"Modificacion correcta",Toast.LENGTH_LONG).show();
                finish();
            }else{
                Toast.makeText(this,"El dato no existe",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,"Debe de llenar todos los campos",Toast.LENGTH_LONG).show();
        }
    }


}