package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Listado extends AppCompatActivity {
    private TableLayout tblistado;
    private String[] cabecera = {"Id","Nombre","Apellido"};
    private DynamicTable creaTabla;
    private ArrayList<String[]> datos = new ArrayList<>();
    FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        tblistado = findViewById(R.id.tblistado);
        creaTabla = new DynamicTable(tblistado, getApplicationContext());
        creaTabla.setCabecera(cabecera);
        TraerDatos();
        creaTabla.setDatos(datos);
        creaTabla.crearCabecera();
        creaTabla.crearFilas();
    }

    private void TraerDatos() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.column1,
                FeedReaderContract.FeedEntry.column2
        };
        String sortOrder =
                FeedReaderContract.FeedEntry.column2 + " ASC";
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.nameTable,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        while (cursor.moveToNext()) {
            String[] fila = new String[3];
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.column1));
            String apellido = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.column2));
            fila[0] = itemId + "";
            fila[1] = nombre;
            fila[2] = apellido;
            datos.add(fila);
        }
        db.close();
    }

    public void Regresar(View vista)
    {
        Intent registro = new Intent(this,MainActivity.class);
        startActivity(registro);
    }
}
