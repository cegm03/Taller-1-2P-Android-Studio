package com.example.myapplication;

import android.content.Context;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class DynamicTable {
    private TableLayout tabla;
    private Context contexto;
    private String[] cabecera;
    private ArrayList<String[]> datos;
    private TableRow fila;
    private TextView celda;

    public DynamicTable(TableLayout tabla, Context contexto) {
        this.tabla = tabla;
        this.contexto = contexto;
    }

    public void setCabecera(String[] cabecera) {
        this.cabecera = cabecera;
    }

    public void setDatos(ArrayList<String[]> datos) {
        this.datos = datos;
    }

    public void NuevaFila() {
        fila = new TableRow(contexto);
    }

    public void nuevaCelda() {
        celda = new TextView(contexto);
        celda.setGravity(Gravity.CENTER);
        celda.setTextSize(14);
    }

    public void crearCabecera() {
        NuevaFila();
        for (String titulo : cabecera) {
            nuevaCelda();
            celda.setText(titulo);
            celda.setTextSize(24);
            celda.setBackgroundColor(3);
            fila.addView(celda, parametrosCelda());
        }
        tabla.addView(fila);
    }

    public void crearFilas() {
        for (String[] datosfila : datos) {
            NuevaFila();
            for (String dato : datosfila) {
                nuevaCelda();
                celda.setText(dato);
                celda.setTextSize(18);
                fila.addView(celda, parametrosCelda());
            }
            tabla.addView(fila);
        }
    }

    private TableRow.LayoutParams parametrosCelda() {
        TableRow.LayoutParams parametros = new TableRow.LayoutParams();
        parametros.setMargins(1, 1, 1, 1);
        parametros.weight = 1;
        return parametros;
    }
}
