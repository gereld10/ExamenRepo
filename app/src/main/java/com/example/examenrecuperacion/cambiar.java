package com.example.examenrecuperacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class cambiar extends AppCompatActivity {


   // EditText nombre3,telefono3,longitud3,latitud3,url;
    Button btnmodificar;
    ArrayAdapter lis;
    getter getter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar);

        btnmodificar =  findViewById(R.id.btnModificar);

        EditText nombre13 = findViewById(R.id.txtnombre3);
        EditText telefono13 = findViewById(R.id.txttelefono3);
        EditText latitud13 = findViewById(R.id.txtlongitud3);
        EditText longitud13 = findViewById(R.id.txtlatitud3);
        EditText urlimg13 = findViewById(R.id.txturl);


        getter = (getter) getIntent().getExtras().getSerializable("itemDetalle");



        nombre13.setText(getter.getNombre());
        telefono13.setText(getter.getTelefono());
        latitud13.setText(getter.getLongitud());
        longitud13.setText(getter.getLatitud());
        urlimg13.setText(getter.getUrl());





        btnmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<>();
                map.put("nombre", nombre13.getText().toString());
                map.put("telefono", telefono13.getText().toString());
                map.put("latitud", latitud13.getText().toString());
                map.put("longitud", longitud13.getText().toString());
                map.put("url", urlimg13.getText().toString());



                FirebaseDatabase.getInstance().getReference().child("Usuarios").child(getter.getKey())
                        .updateChildren(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(cambiar.this, "actualizada Correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(cambiar.this, "Error al actualizar", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });




    }
}