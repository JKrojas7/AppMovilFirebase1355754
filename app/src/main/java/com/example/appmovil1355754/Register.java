package com.example.appmovil1355754;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText txtApellidoNombre = findViewById(R.id.txtApellidoNombreRegister);
        EditText txtCorreo = findViewById(R.id.txtCorreoRegister);
        EditText txtTelefono = findViewById(R.id.txtTelefonoRegister);
        EditText txtContraseña = findViewById(R.id.txtContraseñaRegister);
        Button btnRegistrar = findViewById(R.id.btnRegistrarRegister);
        Button btnCancelarregister = findViewById(R.id.btnCancelarRegister);
        Button btnLimpiar = findViewById(R.id.btnLimpiarRegister);


        //TEXTVIEW tnCancelarregister debe abrir view -> Login
        btnCancelarregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent conectar = new Intent(Register.this,MainActivity.class);
                startActivity(conectar);
            }
        });
    }
}