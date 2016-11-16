package mobile.bambu.vivecafe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.icu.text.CompactDecimalFormat;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.zopim.android.sdk.prechat.ZopimChatActivity;

import java.util.ArrayList;

import mobile.bambu.vivecafe.Interfaces.Constans;
import mobile.bambu.vivecafe.Models.Pago;
import mobile.bambu.vivecafe.Models.User;
import mobile.bambu.vivecafe.Views.Activitys.Login;
import mobile.bambu.vivecafe.Views.Activitys.MapCafe;
import mobile.bambu.vivecafe.Views.Fragments.AjustesFragment;
import mobile.bambu.vivecafe.Views.Fragments.ContactoFragment;
import mobile.bambu.vivecafe.Views.Fragments.FincasFragment;
import mobile.bambu.vivecafe.Views.Fragments.FundacionFragment;
import mobile.bambu.vivecafe.Views.Fragments.ProductosFragment;
import mobile.bambu.vivecafe.Views.Fragments.ViveCafeFragment;

public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,Constans {
    public static String TAG = "NavigationDrawer";
    FrameLayout fl_principalContainer;
    public  User user;
    public static ArrayList<Pago> al_pagos =  new ArrayList<>();

    private DatabaseReference dbr_RefenceUserInformation;
    private DatabaseReference dbr_Pagos;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.initObjects();
        this.iniUIElements();
        this.initToolBar();
        this.initNavigationDrawer(toolbar);
        this.initUIConfiguration();
        this.initUserInformationListener();
        this.initPagosItemsListener();
    }

    private void initObjects(){
        this.user = (User) getIntent().getSerializableExtra(KEY_UUID);
        Log.e(TAG,"initObjects : "+this.user);
    }

    private void initToolBar(){
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.fondo_verde));
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.colorWite));
    }

    private void iniUIElements(){
        fl_principalContainer = (FrameLayout) findViewById(R.id.frame_content_ver_flujo);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }
    private void initUIConfiguration(){
        setFragment(new ViveCafeFragment());
    }

    private void initNavigationDrawer(Toolbar toolbar){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void initUserInformationListener(){

        ValueEventListener vel_userInformation = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, "Value is: " + dataSnapshot.getValue());
                user = dataSnapshot.getValue(User.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "loadPost:onCancelled", databaseError.toException());
           }
        };
        Log.e(TAG,"Route : "+fb_userRoot+"/"+user.uuid+"/"+fb_userInfo);
        dbr_RefenceUserInformation = FirebaseDatabase.getInstance().getReference().child(fb_userRoot).child(user.uuid).child(fb_userInfo);
        dbr_RefenceUserInformation.addValueEventListener(vel_userInformation);
    }

    private void initPagosItemsListener(){
        dbr_Pagos =  FirebaseDatabase.getInstance().getReference().child(fb_pagos);
        ChildEventListener cel_pagos = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.e(TAG,"initPagosItemsListener onChildAdded  :  "+dataSnapshot.getValue());
                try {
                    Pago pago = dataSnapshot.getValue(Pago.class);
                    al_pagos.add(pago);
                }catch (Exception e){
                    Log.e(TAG,"initPagosItemsListener onChildAdded  :  Es null " +  e.toString());
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Pago pago = dataSnapshot.getValue(Pago.class);


            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Pago pago = dataSnapshot.getValue(Pago.class);
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };


        Query recentPostsQuery = dbr_Pagos.orderByChild(pago_key_uid_cliente);
        recentPostsQuery.equalTo(user.uuid).addChildEventListener(cel_pagos);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void setFragment(Fragment fragment){
        if(fragment != null){
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_content_ver_flujo, fragment)
                    .commit();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_vive_cafe:
                setFragment(new ViveCafeFragment());
                break;
            case R.id.nav_fincas:
                setFragment(new FincasFragment());
                break;
            case R.id.nav_fundacion:
                setFragment(new FundacionFragment());
                break;
            case R.id.nav_productos:
                setFragment(new ProductosFragment());
                break;
            case R.id.nav_contacto:
                setFragment(new ContactoFragment());
                break;
            case R.id.nav_chat:
                startActivity(new Intent(this, ZopimChatActivity.class));
                break;
            case R.id.nav_ajustes:
                setFragment(new AjustesFragment());
                break;
        }
        return true;
    }
}
