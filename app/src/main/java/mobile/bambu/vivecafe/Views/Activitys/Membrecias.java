package mobile.bambu.vivecafe.Views.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.PreferenceImageView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import mobile.bambu.vivecafe.Interfaces.Constans;
import mobile.bambu.vivecafe.Models.Finca;
import mobile.bambu.vivecafe.Models.Membrecia;
import mobile.bambu.vivecafe.Models.Terreno;
import mobile.bambu.vivecafe.Models.User;
import mobile.bambu.vivecafe.R;

/**
 * Created by Bambu on 10/11/2016.
 */

public class Membrecias extends AppCompatActivity implements Constans,View.OnClickListener{

    CheckBox rb_membresia_1,rb_membresia_2;
    RelativeLayout ll_membrecia_1,ll_membrecia_2;
    Toolbar toolbar;
    Finca finca;
    Terreno terreno;
    User user;
    Membrecia membrecia = new Membrecia();

    /**
     *
     * @param savedInstanceState
     */
    public static Membrecias membreciasInstans = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membresias);
        this.initDatObjects();
        this.iniUIElements();
        this.initUIConfiguration();
        this.initUIListeners();
    }
    public void initDatObjects(){
        finca = (Finca) getIntent().getExtras().getSerializable(KEY_FINCA);
        this.user = (User) getIntent().getExtras().getSerializable(KEY_UUID);
        this.terreno = (Terreno) getIntent().getExtras().getSerializable(KEY_TERRENO);
        membreciasInstans = this;
        membrecia.uuiid = "Membresia 1";
        membrecia.descripcion = "Paquete Uno de 1 Kg";
        membrecia.kg_mensuales = "1 Kg";
        membrecia.temporalidad = "6 Meses";
    }
    private void initUIConfiguration(){
        setSupportActionBar(toolbar);
        initToolBar();
    }

    private void iniUIElements(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rb_membresia_1 = (CheckBox) findViewById(R.id.rb_membrecia_1);
        rb_membresia_2 = (CheckBox) findViewById(R.id.rb_membrecia_2);
        ll_membrecia_1 = (RelativeLayout) findViewById(R.id.rl_paquete_1);
        ll_membrecia_2 = (RelativeLayout) findViewById(R.id.rl_paquete_2);
    }

    private void initUIListeners(){
        ll_membrecia_2.setOnClickListener(this);
        ll_membrecia_1.setOnClickListener(this);
        rb_membresia_1.setOnClickListener(this);
        rb_membresia_2.setOnClickListener(this);
    }

    private void initToolBar(){
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWite));
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.colorAccent));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void startTypoDeGrano(){
        Intent tipoDeGrano = new Intent(this,TiposDeGranos.class);
        tipoDeGrano.putExtra(KEY_FINCA,finca);
        tipoDeGrano.putExtra(KEY_UUID,user);
        tipoDeGrano.putExtra(KEY_MEMBRECIA,membrecia);
        tipoDeGrano.putExtra(KEY_TERRENO,terreno);
        startActivity(tipoDeGrano);
    }

    @Override
    public void finish() {
        super.finish();
        membreciasInstans = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_done, menu);
//        menu.getItem(0).setEnabled(false);
//        menu.getItem(0).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.aceptar) {
            startTypoDeGrano();
            return true;
        }
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.rl_paquete_1:
                rb_membresia_1.setChecked(true);
                rb_membresia_2.setChecked(false);
                membrecia.uuiid = "Membresia 1";
                membrecia.descripcion = "Paquete Uno de 1 Kg";
                membrecia.kg_mensuales = "1 Kg";
                membrecia.temporalidad = "6 Meses";
                break;

            case R.id.rl_paquete_2:
                rb_membresia_2.setChecked(true);
                rb_membresia_1.setChecked(false);
                membrecia.uuiid = "Membresia 2";
                membrecia.descripcion = "Paquete Dos de 1 Kg";
                membrecia.kg_mensuales = "1 Kg";
                membrecia.temporalidad = "12 Meses";
                break;
            case R.id.rb_membrecia_1:
                rb_membresia_1.setChecked(true);
                rb_membresia_2.setChecked(false);
                membrecia.uuiid = "Membresia 1";
                membrecia.descripcion = "Paquete Uno de 1 Kg";
                membrecia.kg_mensuales = "1 Kg";
                membrecia.temporalidad = "6 Meses";
                break;
            case R.id.rb_membrecia_2:
                rb_membresia_2.setChecked(true);
                rb_membresia_1.setChecked(false);
                membrecia.uuiid = "Membresia 2";
                membrecia.descripcion = "Paquete Dos de 1 Kg";
                membrecia.kg_mensuales = "1 Kg";
                membrecia.temporalidad = "12 Meses";
                break;
        }
    }
}
