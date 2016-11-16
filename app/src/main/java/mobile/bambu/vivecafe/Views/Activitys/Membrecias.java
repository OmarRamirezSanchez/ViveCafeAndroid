package mobile.bambu.vivecafe.Views.Activitys;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TextView;

import java.util.ArrayList;

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

    Toolbar toolbar;
    Finca finca;
    Terreno terreno;
    User user;
    Membrecia membrecia = new Membrecia();
    TextView tv_title_membrecia,tv_description_membrecias;
    ImageView im_membrecia;
    RelativeLayout rb_membrecia_uno,rb_membrecia_dos,rb_membrecia_tres;
    /**
     * @param savedInstanceState
     */
    ArrayList<RelativeLayout> al_Membrecias = new ArrayList<>();
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
    }
    private void initUIConfiguration(){
        setSupportActionBar(toolbar);
        initToolBar();
        al_Membrecias.add(rb_membrecia_uno);
        al_Membrecias.add(rb_membrecia_dos);
        al_Membrecias.add(rb_membrecia_tres);
        tv_title_membrecia.setText("Membrecia Uno");
        tv_description_membrecias.setText("Compra de Café por KG: $350");
        membrecia.nombre = "M01";
        membrecia.uuiid = "M01";
        membrecia.descripcion = "Compra de Café por KG: $350";
    }

    private void iniUIElements(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rb_membrecia_uno = (RelativeLayout) findViewById(R.id.rl_membrecia_uno);
        rb_membrecia_dos = (RelativeLayout) findViewById(R.id.rl_membrecia_dos);
        rb_membrecia_tres =  (RelativeLayout) findViewById(R.id.rl_membrecia_tres);
        tv_title_membrecia = (TextView) findViewById(R.id.tv_membrecia_title);
        tv_description_membrecias = (TextView) findViewById(R.id.tv_membrecia_description);
        im_membrecia = (ImageView) findViewById(R.id.im_membrecia);
    }

    private void initUIListeners() {
        rb_membrecia_uno.setOnClickListener(this);
        rb_membrecia_dos.setOnClickListener(this);
        rb_membrecia_tres.setOnClickListener(this);
    }

    private void initToolBar(){
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
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
        /**
         *
         *
         */
        switch (v.getId()){
            case R.id.rl_membrecia_uno:
                setMembreciaSelectes(v.getId());
                im_membrecia.setImageResource(R.drawable.imagen_ixhuatlan);
                tv_title_membrecia.setText("Membrecia Uno");
                tv_description_membrecias.setText("Compra de Café por KG: $350");
                membrecia.nombre = "M01";
                membrecia.uuiid = "M01";
                membrecia.descripcion = "Compra de Café por KG: $350";
                break;

            case R.id.rl_membrecia_dos:
                setMembreciaSelectes(v.getId());
                im_membrecia.setImageResource(R.drawable.imagen_pluma);
                tv_title_membrecia.setText("Membrecia Dos");
                tv_description_membrecias.setText("6 meses de Café + 1 mes gratis: $1800");
                membrecia.nombre = "M02";
                membrecia.uuiid= "M02";
                membrecia.descripcion = "6 meses de Café + 1 mes gratis: $1800";
                break;

            case R.id.rl_membrecia_tres:
                setMembreciaSelectes(v.getId());
                im_membrecia.setImageResource(R.drawable.imagen_sancristobal);
                tv_title_membrecia.setText("Vive Café Experience");
                tv_description_membrecias.setText("Vive Café Experience 1 Noche Hotel Boutique día de “Siembra” + 1 Noche Gratis en día de\n" +"Cosecha y Producción”: …. ¡ESPÉRALO!");
                membrecia.nombre = "M03";
                membrecia.descripcion = "Vive Café Experience";
                break;
        }
    }
    private void setMembreciaSelectes(int index_id){
        for (RelativeLayout relativeLayout:al_Membrecias){
            if (relativeLayout.getId() ==  index_id){
                relativeLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
            }else {
                relativeLayout.setBackgroundColor(Color.WHITE);
            }
        }
    }
}
