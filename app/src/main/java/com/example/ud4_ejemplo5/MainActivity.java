package com.example.ud4_ejemplo5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements VehiculoAdapter.OnVehiculoClickListener {

    private ArrayList<Vehiculo> vehiculos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creamos el array de vehiculos.
        vehiculos.add(new Vehiculo("Ecto1", "Los cazafantasmas"));
        vehiculos.add(new Vehiculo("DeLorean", "Regreso al futuro"));
        vehiculos.add(new Vehiculo("Kitt", "El coche fantástico"));
        vehiculos.add(new Vehiculo("Halcón Milenario", "Star Wars"));
        vehiculos.add(new Vehiculo("Planet Express", "Futurama"));
        vehiculos.add(new Vehiculo("TARDIS", "Doctor Who"));
        vehiculos.add(new Vehiculo("USS Enterprise", "Star Trek"));
        vehiculos.add(new Vehiculo("Nabucodonosor", "Matrix"));
        vehiculos.add(new Vehiculo("Odiseus", "Ulises 31"));
        vehiculos.add(new Vehiculo("Nostromo", "Alien"));

        RecyclerView recycler = findViewById(R.id.recyclerView);

        recycler.setHasFixedSize(true);

        recycler.addItemDecoration(new DividerItemDecoration(this, 1));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(linearLayoutManager);

        VehiculoAdapter adapter = new VehiculoAdapter(vehiculos, this);

        recycler.setAdapter(adapter);

    }

    @Override
    public void onVehiculoClick(int posicion) {
        Vehiculo vehiculo = vehiculos.get(posicion);
        Toast.makeText(MainActivity.this, vehiculo.getNombre() + "\n" + vehiculo.getAparicion(), Toast.LENGTH_SHORT).show();
    }
}