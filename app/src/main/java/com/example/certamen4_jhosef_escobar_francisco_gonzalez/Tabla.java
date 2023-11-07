package com.example.certamen4_jhosef_escobar_francisco_gonzalez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Tabla extends AppCompatActivity {
    private Button buscar,cargar,volver,eliminar;
    private EditText buscarid;
    private ListView tabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla);
        tabla = findViewById(R.id.ListaAutos);
        buscar = findViewById(R.id.btnBuscar);
        cargar = findViewById(R.id.btnCargar);
        volver = findViewById(R.id.btnVolver);
        eliminar = findViewById(R.id.btnEliminar);
        buscarid = findViewById(R.id.EDBuscar);

    }
    public void Volver (View view){
        Intent Volver = new Intent(this, MainActivity.class);
        startActivity(Volver);
    }

    public void eliminar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"Autos",null,5);
        SQLiteDatabase BaseAutos = admin.getWritableDatabase();
        String Patente = buscarid.getText().toString();
        if (!Patente.isEmpty()){
            int Eliminar = BaseAutos.delete("tablaAutos", "Patente=?", new String[]{Patente});
            BaseAutos.close();

            if (Eliminar ==1){
                Toast.makeText(this, "El registro se elimino correctamente", Toast.LENGTH_SHORT).show();
                buscarid.setText("");
            }else{
                Toast.makeText(this, "La patente que intento eliminar no existe", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "El campo patente no puede estar vacio", Toast.LENGTH_SHORT).show();
        }
    }

    public void CargaAuto(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"Autos",null,5);
        SQLiteDatabase BaseAutos = admin.getWritableDatabase();
        Cursor fila = BaseAutos.rawQuery("select * from tablaAutos",null);
        ArrayList<String> ListaAutos = new ArrayList<>();
        if(fila.moveToFirst()){
            do{
                String idpantente = fila.getString(0);
                String XDMarca = fila.getString(1);
                String XDModelo = fila.getString(2);
                String XDSede = fila.getString(3);
                String XDFecha = fila.getString(4);
                Boolean XDseguro = Boolean.valueOf(fila.getString(5));
                String Autoinfo = "Patente: " +idpantente+ "Marca:" +XDMarca+
                        "Modelo: "+XDModelo+ "Sede: " +XDSede+
                        "Fecha: " +XDFecha+"Seguro: " + XDseguro;
                ListaAutos.add(Autoinfo);
            }while(fila.moveToNext());
        }
        BaseAutos.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,ListaAutos);
        tabla.setAdapter(adapter);
    }
}