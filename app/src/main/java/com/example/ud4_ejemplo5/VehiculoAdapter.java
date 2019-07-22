package com.example.ud4_ejemplo5;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class VehiculoAdapter extends RecyclerView.Adapter<VehiculoAdapter.MiViewHolder> {

    private ArrayList<Vehiculo> lista;
    private OnVehiculoClickListener onVehiculoClickListener;


    public static class MiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nombretextView;
        public TextView apariciontextView;
        public OnVehiculoClickListener onVehiculoClickListener;

        public MiViewHolder(View view, OnVehiculoClickListener onVehiculoClickListener) {
            super(view);

            this.nombretextView = itemView.findViewById(R.id.nombreTextView);
            this.apariciontextView = itemView.findViewById(R.id.aparicionTextView);
            this.onVehiculoClickListener = onVehiculoClickListener;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onVehiculoClickListener.onVehiculoClick(getAdapterPosition()); // Con getAdepaterPsoticion obtenemos la posicion del elemento
        }
    }

    public VehiculoAdapter(ArrayList<Vehiculo> lista, OnVehiculoClickListener onVehiculoClickListener) {
        this.lista = lista;
        this.onVehiculoClickListener = onVehiculoClickListener;
    }

    @NonNull
    @Override
    public VehiculoAdapter.MiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View view = inflater.inflate(R.layout.elementos_lista, viewGroup, false);

        MiViewHolder miViewHolder = new MiViewHolder(view, onVehiculoClickListener);

        return miViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VehiculoAdapter.MiViewHolder holder, int position) {

        final String nombre = lista.get(position).getNombre();

        holder.nombretextView.setText(nombre);

        final String aparicion = lista.get(position).getAparicion();

        holder.apariciontextView.setText(aparicion);
    }

    @Override
    public int getItemCount() {
        if (lista == null)
            return 0;
        else
            return lista.size();
    }

    public interface  OnVehiculoClickListener {

        void onVehiculoClick(int posicion);
    }
}

