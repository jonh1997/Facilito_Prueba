package com.example.facilito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class usuario extends AppCompatActivity {
    private TextView tv1,tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        tv1=findViewById(R.id.tv_nombre_usuario);
        tv2=findViewById(R.id.txt_ver_correo);

        String nombre_Completo=getIntent().getStringExtra("nombre");
        String correo_recuperado=getIntent().getStringExtra("correo");
        tv1.setText(nombre_Completo);
        tv2.setText(correo_recuperado);
    }
    public void editar_usuario(View v){
        AdminBD admin=new AdminBD(this, "administracion",null, 1);
        SQLiteDatabase BaseDeDatos=admin.getWritableDatabase();
        String usuario=tv1.getText().toString();
        String correo=tv2.getText().toString();

        Cursor dato=BaseDeDatos.rawQuery(
                "select * from usuarios where nombre ='"+usuario+"' and correo='"+correo+"'", null);
        try {
            if (dato.moveToFirst()){
                String nameQuery=(dato.getString(0));
                String DireccionQ=(dato.getString(1));
                String edadQ=(dato.getString(2));
                String telefonoQ=(dato.getString(3));
                String correoQ=(dato.getString(4));
                String passQ=(dato.getString(5));

                Intent intent=new Intent(this, editar.class);
                intent.putExtra("nombre",nameQuery);
                intent.putExtra("direccion",DireccionQ);
                intent.putExtra("edad",edadQ);
                intent.putExtra("telefono",telefonoQ);
                intent.putExtra("correo",correoQ);
                intent.putExtra("contra",passQ);
                startActivity(intent);
                BaseDeDatos.close();
            }
        }catch (Exception e){
            Toast.makeText(this,"Algo salio mal",Toast.LENGTH_LONG).show();
        }
    }

    public void recomendar(View view){
       /* AdminBD admin=new AdminBD(this, "recomendados",null, 1);
        SQLiteDatabase BaseDeDatos=admin.getWritableDatabase();
        */
        String usuario=tv2.getText().toString();
        Intent intent=new Intent(this, recomendar.class);
        intent.putExtra("nombre",usuario);
        startActivity(intent);
    }
    public void ver_clientes (View v){
        String usuario=tv2.getText().toString();
        Intent i=new Intent(this,verRecomendados.class);
        i.putExtra("nombre",usuario);
        startActivity(i);
    }
    public void cerrar (View v){
        finish();

    }

}