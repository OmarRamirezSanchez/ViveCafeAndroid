package mobile.bambu.vivecafe.Views.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.CompactDecimalFormat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mobile.bambu.vivecafe.Interfaces.Constans;
import mobile.bambu.vivecafe.Models.Cafe;
import mobile.bambu.vivecafe.Models.Finca;
import mobile.bambu.vivecafe.Models.Membrecia;
import mobile.bambu.vivecafe.Models.Terreno;
import mobile.bambu.vivecafe.Models.User;
import mobile.bambu.vivecafe.R;

/**
 * Created by Bambu on 10/11/2016.
 */

public class TiposDeGranos extends AppCompatActivity implements Constans,View.OnClickListener{

    RelativeLayout rb_arabigo,rb_robusto,rb_colombiana;
    Toolbar toolbar;
    ImageView im_grano_cafe;
    TextView tv_grano_cafe,tv_grano_cafe_description;

    Membrecia membrecia;
    Finca finca;
    Terreno terreno;
    User user;
    Cafe cafe = new Cafe();

    public static TiposDeGranos tiposDeGranosInstans = null;

    ArrayList<RelativeLayout> al_cafes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiposgrano);
        this.initDatObjects();
        this.iniUIElements();
        this.initUIConfiguration();
        this.initListeners();
    }
    public void initDatObjects(){
        finca = (Finca) getIntent().getExtras().getSerializable(KEY_FINCA);
        this.user = (User) getIntent().getExtras().getSerializable(KEY_UUID);
        membrecia = (Membrecia) getIntent().getExtras().getSerializable(KEY_MEMBRECIA);
        this.terreno = (Terreno) getIntent().getExtras().getSerializable(KEY_TERRENO);
        this.tiposDeGranosInstans = this;
        cafe.typo = cafe_type_arabigo;
        cafe.uid = cafe_type_arabigo;
    }
    private void initUIConfiguration(){
        setSupportActionBar(toolbar);
        al_cafes.add(rb_arabigo);
        al_cafes.add(rb_colombiana);
        al_cafes.add(rb_robusto);
        initToolBar();
        tv_grano_cafe_description.setText(getResources().getString(R.string.cafe_grano_colombiano));
    }

    private void iniUIElements(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rb_arabigo = (RelativeLayout) findViewById(R.id.rl_cafe_arabigo);
        rb_colombiana= (RelativeLayout) findViewById(R.id.rl_cafe_colombiano);
        rb_robusto = (RelativeLayout) findViewById(R.id.rl_cafe_robusto);
        im_grano_cafe = (ImageView) findViewById(R.id.im_cafetype);
        tv_grano_cafe = (TextView) findViewById(R.id.textView9);
        tv_grano_cafe_description = (TextView) findViewById(R.id.tv_cafe_grano_descripcion);
    }

    private void initToolBar(){
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWite));
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.colorAccent));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initListeners(){
        rb_robusto.setOnClickListener(this);
        rb_colombiana.setOnClickListener(this);
        rb_arabigo.setOnClickListener(this);
    }



    private void startCalendario(){
        Intent tipoDeGrano = new Intent(this,CalendarioFinca.class);
        tipoDeGrano.putExtra(KEY_FINCA,finca);
        tipoDeGrano.putExtra(KEY_UUID,user);
        startActivity(tipoDeGrano);
    }

    private void startSuscription(){
        Intent intnetSuscription = new Intent(this,SuscriptionTerrain.class);
        intnetSuscription.putExtra(KEY_FINCA,finca);
        intnetSuscription.putExtra(KEY_CAFE,cafe);
        intnetSuscription.putExtra(KEY_MEMBRECIA,membrecia);
        intnetSuscription.putExtra(KEY_UUID,user);
        intnetSuscription.putExtra(KEY_TERRENO,terreno);
        startActivity(intnetSuscription);
    }

    @Override
    public void finish() {
        super.finish();
        tiposDeGranosInstans = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_done, menu);
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
            this.startSuscription();
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
            case R.id.rl_cafe_arabigo:
                setCafeSelectes(v.getId());
                tv_grano_cafe.setText("Café Arabigo");
                tv_grano_cafe_description.setText(getResources().getString(R.string.cafe_grano_arabigo));
                im_grano_cafe.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.arabiga));
                cafe.typo = cafe_type_arabigo;
                cafe.uid = cafe_type_arabigo;
                break;

            case R.id.rl_cafe_colombiano:
                setCafeSelectes(v.getId());
                tv_grano_cafe.setText("Café Colombiano");
                tv_grano_cafe_description.setText(getResources().getString(R.string.cafe_grano_colombiano));
                im_grano_cafe.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.colombiano));
                cafe.typo = cafe_type_colombiano;
                cafe.uid = cafe_type_colombiano;
                break;

            case R.id.rl_cafe_robusto:
                setCafeSelectes(v.getId());
                tv_grano_cafe.setText("Café Robusto");
                tv_grano_cafe_description.setText(getResources().getString(R.string.cafe_grano_robusto));
                im_grano_cafe.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.robusta));
                cafe.typo = cafe_type_robusto;
                cafe.uid = cafe_type_robusto;
                break;
        }
    }

    private void setCafeSelectes(int index_id){
        for (RelativeLayout relativeLayout:al_cafes){
            if (relativeLayout.getId() ==  index_id){
                relativeLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
            }else {
                relativeLayout.setBackgroundColor(Color.WHITE);
            }
        }
    }
}
