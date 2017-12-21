package fr.madera.androidMadera.devis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;

import fr.madera.androidMadera.MainActivity;
import fr.madera.androidMadera.R;
import fr.madera.androidMadera.models.Client;
import fr.madera.androidMadera.models.Devis;
import fr.madera.androidMadera.models.Gamme;
import fr.madera.androidMadera.models.Modele;

public class SelectDevisActivity extends AppCompatActivity {

    private String[] clientNamesArray;
    private String[] gammesNamesArray;
    private String[] modelesNamesArray;
    private String[] devisNamesArray;

    private ArrayList<Devis> arrListDevis;
    private ArrayList<Modele> arrListModeles;
    private ArrayList<Gamme> arrListGammes;
    private ArrayList<Client> arrListClients;

    private int selectedDevis = 0;
    public int selectedClient = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_devis);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Madera - Devis");

        // populate spinners
        populateClientsSpinner();
        populateGammeSpinner();
        populateModeleSpinner();
        // important de le populate apres le spinner client car il est relatif au client ID
        populateDevisSpinner();

        // scale the view down
        float scalingFactor = 0.95f; // scale down to half the size
        View scrollView = findViewById(R.id.scrollView);
        scrollView.setScaleX(scalingFactor);
        scrollView.setScaleY(scalingFactor);

        // listeners for spinners

        // spinner selection des clients
        final Spinner spinClient = (Spinner)findViewById(R.id.spinnerClients);

        spinClient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("debug", "Spinner Clients :"+arrListClients.get(i).getNom());
                selectedClient = i+1;
                populateDevisSpinner();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
        // spinner selection des gammes
        Spinner spinGamme = (Spinner)findViewById(R.id.spinnerGamme);

        spinGamme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("debug", "Spinner Gammes :"+arrListGammes.get(i).getLibelle());
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
        // spinner selection des modèles
        Spinner spinModele = (Spinner)findViewById(R.id.spinnerModele);

        spinModele.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("debug", "Spinner Modèles :"+arrListModeles.get(i).getLibelle());
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
        // spinner selection des devis
        final Spinner spinDevis = (Spinner)findViewById(R.id.spinnerDevis);

        spinDevis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // ici on ne peux pas utiliser l'index car il ne correspond pas forcément avec l'array list
                // on récupérer l'objet String du spinner et on s'en sert pour récup le bon devis

                for (int it = 0; it < arrListDevis.size(); it++) {
                    if(arrListDevis.get(it).getRef().equals(spinDevis.getItemAtPosition(i))){
                        Log.d("debug", "Spinner Devis :"+arrListDevis.get(it).getRef());
                        selectedDevis = it;
                    }
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }

    // radio modele listener
    public void onRadioModeleClicked(View v){
        RadioButton clickedButton = (RadioButton)v;
        // on desactive l'autre
        RadioButton btnSelectDevis=(RadioButton)findViewById(R.id.radioSelectDevis);
        btnSelectDevis.setChecked(false);
        // on active celui ci
        clickedButton.setChecked(true);
    }

    // radio devis clicked
    public void onRadioDevisClicked(View v){
        RadioButton clickedButton = (RadioButton)v;
        // on desactive l'autre
        RadioButton btnSelectMod=(RadioButton)findViewById(R.id.radioSelectMod);
        btnSelectMod.setChecked(false);
        // on active celui ci
        clickedButton.setChecked(true);
    }

    protected void populateClientsSpinner(){
        arrListClients = MainActivity.bdd.loadAllClients();
        clientNamesArray = new String[arrListClients.size()];
        for (int i = 0; i < arrListClients.size(); i++) {
            clientNamesArray[i] = arrListClients.get(i).getNom() + " " +arrListClients.get(i).getPrenom();
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinnerClients);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, clientNamesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // TODO : le spinner doit permettre de set le client id ligne ci dessous en temporaire
        selectedClient = 1;
    }

    protected void populateGammeSpinner(){
        arrListGammes = MainActivity.bdd.loadAllGammes();
        gammesNamesArray = new String[arrListGammes.size()];
        for (int i = 0; i < arrListGammes.size(); i++) {
            gammesNamesArray[i] = arrListGammes.get(i).getLibelle();
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinnerGamme);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, gammesNamesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    protected void populateModeleSpinner(){
        arrListModeles = MainActivity.bdd.loadAllModeles();
        modelesNamesArray = new String[arrListModeles.size()];
        for (int i = 0; i < arrListModeles.size(); i++) {
            modelesNamesArray[i] = arrListModeles.get(i).getLibelle();
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinnerModele);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, modelesNamesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    protected void populateDevisSpinner(){
        arrListDevis = MainActivity.bdd.loadDevisOf(selectedClient);
        devisNamesArray = new String[arrListDevis.size()];
        for (int i = 0; i < arrListDevis.size(); i++) {
            devisNamesArray[i] = arrListDevis.get(i).getRef();
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinnerDevis);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, devisNamesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    // ajout du menu avec le switch de connexion a Madera
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
