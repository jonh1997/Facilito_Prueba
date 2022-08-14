package com.example.facilito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class verRecomendados extends AppCompatActivity {
    private ListView lista;
    TextView tv_prueba,tv4,ver;
    String[] arreglo;
    public int selecteditem=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_recomendados);
        lista=findViewById(R.id.lista);
        tv4=findViewById(R.id.textView4);
        ver=findViewById(R.id.ver);
        tv_prueba=findViewById(R.id.tv_recomendadas);
        String user=getIntent().getStringExtra("nombre");
        tv_prueba.setText(user);
        listar();

    }
    /*private void consultar(){
        String nombre_Original=tv_prueba.getText().toString();
        ArrayList<String> personas=new ArrayList<>();
        ArrayList<String> ids=new ArrayList<>();
        AdminBD admin=new AdminBD(this, "recomendados", null, 1);
        SQLiteDatabase BaseDeDatos=admin.getWritableDatabase();

        Cursor dato=BaseDeDatos.rawQuery(
                "select * from recomendados where correo= '"+nombre_Original+"'", null);
        if (dato.moveToFirst()){
            do {
                personas.add(dato.getString(2));

            }while (dato.moveToNext());
        }
        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,personas);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String p=personas.get(i);
                //int id=Integer.parseInt(areglo[selecteditem].split(" ")[0]);
                tv4.setText(p);
                editar_reco();
            }
        });

    }*/
    private void listar(){
        String nombre_Original=tv_prueba.getText().toString();
        AdminBD admin=new AdminBD(this, "recomendados", null, 1);
        SQLiteDatabase BaseDeDatos=admin.getReadableDatabase();


            Cursor c=BaseDeDatos.rawQuery(
                    "select * from recomendados where correo= '"+nombre_Original+"'", null);

            int cantidad=c.getCount();
            int i=0;
             arreglo=new String[cantidad];

            if (c.moveToFirst()){
               do {
                    String linea =c.getString(0)+ " " + c.getString(2);

                   arreglo[i]=linea;
                    i++;
                }while (c.moveToNext());

            }
            ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arreglo);
            lista.setAdapter(adaptador);
            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    selecteditem=i;
                    int idPerdona=Integer.parseInt(arreglo[selecteditem].split(" ")[0]);
                    tv4.setText(String.valueOf(idPerdona));
                    editar_reco();

                }
            });

    }

    public void editar_reco(){
        Intent i=new Intent(this, editar_recomendacion.class);
        String id=tv4.getText().toString();
        String correo=tv_prueba.getText().toString();
        i.putExtra("id",id);
        i.putExtra("correo",correo);
        startActivity(i);
    }
}