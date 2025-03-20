package com.example.bryanseleccion;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText etDocumento, etTipoDocumento;
    private Button btnFecha, btnGuardar;
    private TextView tvFechaSeleccion;
    private ListView listViewModos;
    private String[] modosAprendizaje = {"Visual", "Auditivo", "Kinestico"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        etDocumento = findViewById(R.id.etDocumento);
        etTipoDocumento = findViewById(R.id.etTipoDocumento);
        btnFecha = findViewById(R.id.btnFecha);
        tvFechaSeleccion = findViewById(R.id.tvFechaSeleccion);
        listViewModos = findViewById(R.id.listViewModos);
        btnGuardar = findViewById(R.id.btnGuardar);


        if (listViewModos != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, modosAprendizaje);
            listViewModos.setAdapter(adapter);
        }


        btnFecha.setOnClickListener(v -> mostrarDatePicker());


        btnGuardar.setOnClickListener(v -> guardarSeleccion());
    }


    private void mostrarDatePicker() {
        Calendar calendario = Calendar.getInstance();
        int anio = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
            tvFechaSeleccion.setText(fecha);
        }, anio, mes, dia);

        datePickerDialog.show();
    }


    private void guardarSeleccion() {
        String documento = etDocumento.getText().toString().trim();
        String tipoDocumento = etTipoDocumento.getText().toString().trim();
        String fecha = tvFechaSeleccion.getText().toString();

        if (documento.isEmpty() || tipoDocumento.isEmpty() || fecha.equals("Fecha no seleccionada")) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            String mensaje = "Datos guardados:\nDocumento: " + documento + "\nTipo: " + tipoDocumento + "\nFecha: " + fecha;
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
        }
    }
}

//SQLiteOpenHelper clase para gestionar la base de datos SQLite, contiene metodos onCreate() y onUpgrade().
//ContentValues (Para insertar datos) Permite agregar registros a una tabla sin escribir SQL directamente.
// Cursor (Para leer datos) Se usa para recuperar datos de la base de datos.
