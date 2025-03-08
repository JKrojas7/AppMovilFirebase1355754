package com.example.appmovil1355754;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class registrarcliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrarcliente);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText dnicliente = findViewById(R.id.txtdnicliente);
        EditText apellidocliente = findViewById(R.id.txtapellidocliente);
        EditText nombrecliente = findViewById(R.id.txtnombrecliente);
        EditText telefonocliente = findViewById(R.id.txttelefonocliente);
        EditText correocliente = findViewById(R.id.txtcorreocliente);
        EditText direccioncliente = findViewById(R.id.txtdireccioncliente);

        Button btnregistarcliente = findViewById(R.id.btnRegistrarCliente);

        String idLogeado = FirebaseAuth.getInstance().getCurrentUser().getUid();

        btnregistarcliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni = dnicliente.getText().toString();
                String apellido = apellidocliente.getText().toString();
                String nombre = nombrecliente.getText().toString();
                String telefono = telefonocliente.getText().toString();
                String correo = correocliente.getText().toString();
                String direccion = direccioncliente.getText().toString();

                if (TextUtils.isEmpty(dni)|| TextUtils.isEmpty(apellido)||TextUtils.isEmpty(nombre)|| TextUtils.isEmpty(telefono)|| TextUtils.isEmpty(correo)|| TextUtils.isEmpty(direccion)){
                    Toast.makeText(registrarcliente.this,"Todos los campos son obligatorios",Toast.LENGTH_SHORT).show();
                }else{
                    DatabaseReference dr = FirebaseDatabase.getInstance().getReference();
                    String idCliente = dr.child("clientes").push().getKey();

                    Map<String, String > client = new HashMap<>();
                    client.put("dni",dni);
                    client.put("apellido",apellido);
                    client.put("nombre",nombre);
                    client.put("telefono",telefono);
                    client.put("correo",correo);
                    client.put("direccion",direccion);

                    dr.child("clientes").child(idLogeado).child(idCliente).setValue(client).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                    Toast.makeText(registrarcliente.this,"Cliente registrado",Toast.LENGTH_SHORT).show();
                                    Intent con2 = new Intent(registrarcliente.this,clientes.class);
                                    startActivity(con2);


                            }else{
                                Toast.makeText(registrarcliente.this,"Error al registrar",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}