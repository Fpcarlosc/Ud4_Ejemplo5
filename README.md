# Ud4_Ejemplo5
_Ejemplo 5 de la Unidad 4._ 

En este ejemplo vamos a implementar la misma aplicación que en [Ud4_Ejemplo4](https://github.com/Fpcarlosc/Ud4_Ejemplo4) pero 
en este caso creando una interfaz.

Los únicos cambios con respecto al ejemplo anterior son (los ficheros _activity_main.xml_, _elementos_lista.xml_ y _Vehiculo.java_ son idénticos):

## _VehiculoAdater.java_

En esta clase deberemos:
1. Crear una interfaz llamada _OnVehiculoClickListener_.
2. Crear un nuevo atributo de tipo _OnVehiculoClickListener_ llamado _onVehiculoClickListener_.
3. En la clase _MiViewHolder_:
    + Crear el atributo _onVehiculoClickListener_ de tipo _OnVehiculoClickListener_.
    + Incluir un parámtero más en el constructor y llamar al método _setOnClickListener_.
    + Implementar la insterfaz _View.OnClickListener_ en la clase _MiViewHolder_ y sobrescribir el método _onClick_ que llamará al 
método _onVehiculoClick_ pasándole la posición del elemento obtenida con el método _getAdapterPosition_.
```
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
```
## _MainActivity.java_

En la clase principal deberemos:
1. Implementar la interfaz creada _OnVehiculoClickListener_ y sobrescribir el método _onVehiculoClick_ que será el que obtenga el elemento y muestre el mensaje por pantalla.
2. Modificar la llamada al constructor de la clase _Vehiculoadapter_ pasándole la propia clase.
```
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
```

Recordad que para poder utilizar la clase _RecyclerView_ deberemos incluir la dependencia _com.android.support:recyclerview-v7:28.0.0_ en el fichero _build.gradle(Module:app)_.
