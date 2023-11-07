package com.example.certamen4_jhosef_escobar_francisco_gonzalez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.net.IDN;

public class MainActivity extends AppCompatActivity {
    private EditText idAuto,marca,modelo,sede,fecha;
    private CheckBox seguro;
    private Button agregar,actualizar,vertabla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idAuto = findViewById(R.id.EDidAuto);
        marca= findViewById(R.id.EDMarca);
        modelo= findViewById(R.id.EDModelo);
        sede = findViewById(R.id.EDSede);
        fecha = findViewById(R.id.EDFecha);
        seguro = findViewById(R.id.CBSeguro);
        agregar = findViewById(R.id.btnAgregar);
        actualizar = findViewById(R.id.btnActualizar);
        vertabla = findViewById(R.id.btnTabla);

    }
    public void AgregarLista(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Autos", null, 5);
        SQLiteDatabase BaseAutos = admin.getWritableDatabase();
        String Idpantente = idAuto.getText().toString();
        String Marca = marca.getText().toString();
        String Modelo = modelo.getText().toString();
        String Sede = sede.getText().toString();
        String Fecha = fecha.getText().toString();
        Boolean ischecked = seguro.isChecked();

        if (!Idpantente.isEmpty() && !Marca.isEmpty() && !Modelo.isEmpty() && !Sede.isEmpty() && !Fecha.isEmpty()){
            ContentValues DatosAuto = new ContentValues();

            DatosAuto.put("Patente", Idpantente);
            DatosAuto.put("Marca", Marca);
            DatosAuto.put("Modelo", Modelo);
            DatosAuto.put("Sede", Sede);
            DatosAuto.put("Fecha", Fecha);
            DatosAuto.put("Seguro", ischecked ? 1 : 0);

            long resultado = BaseAutos.insert("tablaAutos", null, DatosAuto);
            BaseAutos.close();

            if (resultado != -1) {
                Toast.makeText(this, "Auto registrado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al registrar el auto", Toast.LENGTH_SHORT).show();
            }

            idAuto.setText("");
            marca.setText("");
            modelo.setText("");
            sede.setText("");
            fecha.setText("");
            seguro.setChecked(false);
        } else {
            Toast.makeText(this, "No pueden haber campos vacios", Toast.LENGTH_SHORT).show();
        }
    }

    public void Vertabla(View view){
        Intent Tabla = new Intent(this,Tabla.class);
        startActivity(Tabla);
    }
    public void Actualizar(View view){
        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Produccion", null, 5);
            SQLiteDatabase BaseDatos = admin.getWritableDatabase();
            String IDAuto = idAuto.getText().toString();
            String Marca = marca.getText().toString();
            String Modelo = modelo.getText().toString();
            String Sede = sede.getText().toString();
            String Fecha = fecha.getText().toString();
            Boolean ischecked = seguro.isChecked();

            if (!Marca.isEmpty() && !Modelo.isEmpty() && !Sede.isEmpty() && !Fecha.isEmpty()){
                ContentValues DatosAuto = new ContentValues();
                DatosAuto.put("Marca", Marca);
                DatosAuto.put("Modelo", Modelo);
                DatosAuto.put("Sede", Sede);
                DatosAuto.put("Fecha", Fecha);
                DatosAuto.put("Seguro", ischecked ? 1 : 0);
                Log.d("IDAuto", IDAuto);
                int Cantidad = BaseDatos.update("tablaAutos", DatosAuto, "Patente=?", new String[]{IDAuto});
                BaseDatos.close();

                if (Cantidad == 1){
                    Toast.makeText(this, "El registro se actualizó correctamente",
                            Toast.LENGTH_SHORT).show();
                    idAuto.setText("");
                    marca.setText("");
                    modelo.setText("");
                    sede.setText("");
                    fecha.setText("");
                } else {
                    Toast.makeText(this, "No se encontró el ID ingresado", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "No pueden haber campos vacíos", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al actualizar el registro", Toast.LENGTH_SHORT).show();
        }
    }
}