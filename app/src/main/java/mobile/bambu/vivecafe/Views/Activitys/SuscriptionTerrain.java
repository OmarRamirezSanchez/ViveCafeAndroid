package mobile.bambu.vivecafe.Views.Activitys;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

import mobile.bambu.vivecafe.Interfaces.Constans;
import mobile.bambu.vivecafe.Models.Cafe;
import mobile.bambu.vivecafe.Models.Direccion;
import mobile.bambu.vivecafe.Models.Finca;
import mobile.bambu.vivecafe.Models.Membrecia;
import mobile.bambu.vivecafe.Models.Pago;
import mobile.bambu.vivecafe.Models.Terreno;
import mobile.bambu.vivecafe.Models.User;
import mobile.bambu.vivecafe.Networck.RequestePago;
import mobile.bambu.vivecafe.R;
import mobile.bambu.vivecafe.Util.Util;

/**
 * Created by Bambu on 11/11/2016.
 */

public class SuscriptionTerrain extends AppCompatActivity implements View.OnClickListener,Constans,TextWatcher{

    Button bt_suscripcion;
    EditText et_nombre,et_direccion,et_departamento,et_ciudad,et_provincia,et_codigopostal;
    Toolbar toolbar;

    Finca finca;
    Terreno terreno;
    User user;
    Pago pago;
    Membrecia membrecia;
    Cafe cafe;
    Direccion direccion = new Direccion();

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

    }


    private void initUIElments(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bt_suscripcion = (Button) findViewById(R.id.bt_pagar_suscripcion);
        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_direccion = (EditText) findViewById(R.id.et_direccion);
        et_departamento = (EditText) findViewById(R.id.et_departamento);
        et_ciudad = (EditText) findViewById(R.id.et_ciudad);
        et_codigopostal = (EditText) findViewById(R.id.et_zipCode);
    }

    private void initToolBar(){
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initUIConfiguration(){
        setSupportActionBar(toolbar);
        et_nombre.addTextChangedListener(this);
        et_direccion.addTextChangedListener(this);
        et_departamento.addTextChangedListener(this);
        et_ciudad.addTextChangedListener(this);
        et_codigopostal.addTextChangedListener(this);
        bt_suscripcion.setOnClickListener(this);
        bt_suscripcion.setEnabled(false);
        bt_suscripcion.setAlpha(0.6f);
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
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void validateText(){
        if (Util.isValidData(et_nombre) && Util.isValidData(et_direccion)&&
                Util.isValidData(et_departamento)&& Util.isValidData(et_ciudad)&& Util.isValidData(et_codigopostal)){
            bt_suscripcion.setAlpha(1);
            bt_suscripcion.setEnabled(true);
        }else {
            bt_suscripcion.setAlpha(0.6f);
            bt_suscripcion.setEnabled(false);
        }
    }
    private void loadDireccion(){
        direccion.nombre = et_nombre.getText().toString();
        direccion.direccion_uno = et_direccion.getText().toString();
        direccion.direccion_dos = et_departamento.getText().toString();
        direccion.ciudad = et_codigopostal.getText().toString();
        direccion.codigo_postal = et_codigopostal.getText().toString();
    }
    @Override
    public void onClick(View v) {
        loadDireccion();
        pago =  new Pago(user,terreno,membrecia,cafe,direccion);
        RequestePago.writeNewPago(pago, FirebaseDatabase.getInstance());
        startCalendario();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        validateText();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
