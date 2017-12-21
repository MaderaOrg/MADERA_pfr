package fr.madera.androidMadera;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import fr.madera.androidMadera.dbLoad.dbLoad;
import fr.madera.androidMadera.devis.SelectDevisActivity;

public class MainActivity extends AppCompatActivity {
    public static dbLoad bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Madera Mobile");
        bdd = new dbLoad(this,"madera",null,1);
        bdd.open();
        bdd.close();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void buttonGestionDevis(View view) {
        Log.d("debug","clic sur boutton gestion devis");
        Intent intent = new Intent(this, SelectDevisActivity.class);
        startActivity(intent);
    }
}