package com.example.appmovil1355754;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClientesAdapter extends RecyclerView.Adapter<ClientesAdapter.ViewHolderClientes> {

    List<Clientess> listaclientes;
    public ClientesAdapter(List<Clientess> clientess){
        this.listaclientes = clientess;
    }

    @NonNull
    @Override
    public ClientesAdapter.ViewHolderClientes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_recycler,parent,false);
        ViewHolderClientes holder = new ViewHolderClientes(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClientesAdapter.ViewHolderClientes holder, int position) {

        Clientess clientess = listaclientes.get(position);

        holder.apellidos.setText(clientess.getApellido());
        holder.nombres.setText(clientess.getNombre());
        holder.telefono.setText(clientess.getTelefono());
        holder.correo.setText(clientess.getCorreo());
    }

    @Override
    public int getItemCount() {
        return listaclientes.size();
    }
    public class ViewHolderClientes extends RecyclerView.ViewHolder{

        TextView apellidos, nombres, telefono, correo;
        public ViewHolderClientes(@NonNull View itemView){
            super(itemView);
            apellidos = itemView.findViewById(R.id.txtApellidolista);
            nombres = itemView.findViewById(R.id.txtNombrelista);
            telefono = itemView.findViewById(R.id.txtTelefonolista);
            correo = itemView.findViewById(R.id.txtCorreolista);
        }
    }
}
