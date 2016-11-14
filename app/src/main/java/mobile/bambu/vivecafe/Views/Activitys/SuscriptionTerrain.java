package mobile.bambu.vivecafe.Views.Activitys;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.FirebaseDatabase;

import mobile.bambu.vivecafe.Interfaces.Constans;
import mobile.bambu.vivecafe.Models.Cafe;
import mobile.bambu.vivecafe.Models.Finca;
import mobile.bambu.vivecafe.Models.Membrecia;
import mobile.bambu.vivecafe.Models.Pago;
import mobile.bambu.vivecafe.Models.Terreno;
import mobile.bambu.vivecafe.Models.User;
import mobile.bambu.vivecafe.Networck.RequestePago;
import mobile.bambu.vivecafe.R;

/**
 * Created by Bambu on 11/11/2016.
 */

public class SuscriptionTerrain extends AppCompatActivity implements View.OnClickListener,Constans{

    Button bt_suscripcion;
    Toolbar toolbar;

    Finca finca;
    Terreno terreno;
    User user;
    Pago pago;
    Membrecia membrecia;
    Cafe cafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suscription);
        this.initDataObjects();
        this.initUIElments();
        this.initUIConfiguration();
    }

    private void initDataObjects(){

        user = (User) getIntent().getExtras().getSerializable(KEY_UUID);
        finca = (Finca) getIntent().getExtras().getSerializable(KEY_FINCA);
        terreno = (Terreno) getIntent().getExtras().getSerializable(KEY_TERRENO);
        membrecia = (Membrecia) getIntent().getExtras().getSerializable(KEY_MEMBRECIA);
        cafe = (Cafe) getIntent().getExtras().getSerializable(KEY_CAFE);
        pago =  new Pago(user,terreno,membrecia,cafe);
    }


    private void initUIElments(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bt_suscripcion = (Button) findViewById(R.id.bt_pagar_suscripcion);
    }

    private void initToolBar(){
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWite));
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.colorAccent));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initUIConfiguration(){
        setSupportActionBar(toolbar);
        bt_suscripcion.setOnClickListener(this);
        initToolBar();
    }

    private void startCalendario(){
        deletLastActivitys();
        Intent calendario = new Intent(this,CalendarioFinca.class);
        calendario.putExtra(KEY_FINCA,finca);
        startActivity(calendario);
        finish();
    }
    private void deletLastActivitys(){
        try {
            if(Membrecias.membreciasInstans != null) {
                Membrecias.membreciasInstans.finish();
            }
            if (TiposDeGranos.tiposDeGranosInstans != null){
                TiposDeGranos.tiposDeGranosInstans.finish();
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_done, menu);
        menu.getItem(0).setEnabled(false);
        menu.getItem(0).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        RequestePago.writeNewPago(pago, FirebaseDatabase.getInstance());
        startCalendario();
    }
}
