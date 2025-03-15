package com.example.appmovil1355754;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class clientes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clientes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        FloatingActionButton btnRegistrarClienteir = findViewById(R.id.btnRegistrarclientes);

        String CurrentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        ArrayList<Clientess> listClientes = new ArrayList<>();

        ClientesAdapter adclientes = new ClientesAdapter(listClientes);

        LinearLayoutManager lm = new LinearLayoutManager(this);

        RecyclerView rvClientes = findViewById(R.id.rcClientes);

        rvClientes.setLayoutManager(lm);

        rvClientes.setAdapter(adclientes);

        FirebaseDatabase.getInstance().getReference().child("clientes").child(CurrentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    listClientes.clear();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                        Clientess cliente = dataSnapshot.getValue(Clientess.class);
                        listClientes.add(cliente);
                        adclientes.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnRegistrarClienteir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent conecc = new Intent(clientes.this,registrarcliente.class);
                startActivity(conecc);
            }
        });
    }
}