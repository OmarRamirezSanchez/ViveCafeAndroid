package mobile.bambu.vivecafe.Views.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.camera2.CameraManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.database.ChildEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mobile.bambu.vivecafe.Interfaces.Constans;
import mobile.bambu.vivecafe.Models.Finca;
import mobile.bambu.vivecafe.Models.Pago;
import mobile.bambu.vivecafe.Models.Terreno;
import mobile.bambu.vivecafe.Models.User;
import mobile.bambu.vivecafe.NavigationDrawer;
import mobile.bambu.vivecafe.R;
import mobile.bambu.vivecafe.Util.TerrenoUtil;
import mobile.bambu.vivecafe.Util.Util;

import static mobile.bambu.vivecafe.R.id.cancel_action;
import static mobile.bambu.vivecafe.R.id.map;
import static mobile.bambu.vivecafe.R.id.search_go_btn;
import static mobile.bambu.vivecafe.R.id.thing_proto;

public class MapCafe extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMapClickListener,Constans,View.OnClickListener,GoogleMap.OnPolygonClickListener,GoogleMap.OnMarkerClickListener{

    public static String TAG = "MapCafe";
    private GoogleMap mMap;
    ImageView img_close;
    ArrayList<Terreno> al_terrenos =  new ArrayList<>();
    Finca finca;
    User user;
    Toolbar toolbar;
    private DatabaseReference dbr_Terrains;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initDataObjects();
        setContentView(R.layout.activity_map_cafe);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
        this.initUIElements();
        this.initUIConfiguration();
        this.initListeners();
        this.initTerrainItemsListener();
        this.initToolBar();
        this.initPagosItemsListener();
    }
    private void initDataObjects(){
        this.finca = (Finca) getIntent().getExtras().getSerializable(KEY_FINCA);
        this.user = (User) getIntent().getExtras().getSerializable(KEY_UUID);
    }
    private void initToolBar(){
        getSupportActionBar().hide();
    }

    private void initUIElements(){
        //toolbar  = (Toolbar) findViewById(R.id.toolbar);
        img_close = (ImageView) findViewById(R.id.imv_map_cafe_close);
    }

    private void initUIConfiguration(){
    }

    private void initListeners(){
        img_close.setOnClickListener(this);
    }


    /**
     * TODO Esta parte pued estar como estatica en el actividad principal
     *
     */
    private void initPagosItemsListener(){
        DatabaseReference dbr_Pagos =  FirebaseDatabase.getInstance().getReference().child(fb_pagos);
        ChildEventListener cel_pagos = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //actualizarMapa();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //actualizarMapa();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //actualizarMapa();
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

    private void initTerrainItemsListener(){

        dbr_Terrains =  FirebaseDatabase.getInstance().getReference().child(fb_terrainsDefault);
        ChildEventListener cel_terrains = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Terreno terreno = dataSnapshot.getValue(Terreno.class);
                Log.e(TAG,"Terreno : "+terreno);
                if (TerrenoUtil.terrenoIsPayment(terreno, NavigationDrawer.al_pagos)){
                    terreno.state = STATE_TERRAIN_ADQUIRIDO;
                }
                al_terrenos.add(terreno);
                setSegmentTerrain(terreno);
                /**
                 * TODO Mejorar esta parte para actualizar los terrenos en tiempo real
                 */
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Terreno terreno = dataSnapshot.getValue(Terreno.class);
                /**
                 * TODO Mejorar esta parte para actualizar los terrenos en tiempo real
                 */
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Terreno terreno = dataSnapshot.getValue(Terreno.class);
                /**
                 * TODO Mejorar esta parte para actualizar los terrenos en tiempo real
                 */
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        dbr_Terrains.addChildEventListener(cel_terrains);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setOnPolygonClickListener(this);
        mMap.setOnMarkerClickListener(this);
        LatLng fincaLocation = new LatLng(finca.getLatitud(), finca.getLongitud());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fincaLocation, 10));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17), 5000, null);
        this.initUITerrainConfiguration();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imv_map_cafe_close:
                finish();
                break;
        }
    }

    private void startMembrecias(Terreno terreno){
       Intent intent = new Intent(this,Membrecias.class);
        intent.putExtra(KEY_UUID,user);
        intent.putExtra(KEY_TERRENO,terreno);
        intent.putExtra(KEY_FINCA,finca);
        startActivity(intent);
    }
    private void startCalendario(Finca finca,Terreno terreno){
        Intent tipoDeGrano = new Intent(this,CalendarioFinca.class);
        tipoDeGrano.putExtra(KEY_UUID,user);
        tipoDeGrano.putExtra(KEY_FINCA,finca);
        tipoDeGrano.putExtra(KEY_TERRENO,terreno);
        startActivity(tipoDeGrano);
    }
    private void initUITerrainConfiguration(){
        for (Terreno terreno:al_terrenos){
            setSegmentTerrain(terreno);
        }
    }

    private void setSegmentTerrain(Terreno terrain){

        PolygonOptions polygonOptions = new PolygonOptions();
        for(Map<String,Float> latLng:terrain.points){
            LatLng latLng1 =  new LatLng(latLng.get(LATITUD),latLng.get(LONGITUD));
            polygonOptions.add(latLng1);
        }
        Polygon poligonMap = mMap.addPolygon(polygonOptions);
        terrain.id_Polygono = poligonMap.getId();

        switch (terrain.state){
            case STATE_TERRAIN_ADQUIRIDO:
                poligonMap.setFillColor(ContextCompat.getColor(this,R.color.terreno_adquirido));
                addMarkers(centroid(polygonOptions.getPoints()),terrain.uuid);
                break;
            case STATE_TERRAIN_DISPONIBLE:
                poligonMap.setFillColor(ContextCompat.getColor(this,R.color.terreno_disponible));
                break;
            case STATE_TERRAIN_OCUPADO:
                poligonMap.setFillColor(ContextCompat.getColor(this,R.color.terreno_ocupado));
                addMarkers(centroid(polygonOptions.getPoints()),terrain.uuid);
                break;
        }
        poligonMap.setStrokeWidth(2);
        poligonMap.setClickable(true);
    }

    public static LatLng centroid(List<LatLng> points) {
        double[] centroid = { 0.0, 0.0 };

        for (int i = 0; i < points.size(); i++) {
            centroid[0] += points.get(i).latitude;
            centroid[1] += points.get(i).longitude;
        }

        int totalPoints = points.size();
        centroid[0] = centroid[0] / totalPoints;
        centroid[1] = centroid[1] / totalPoints;

        return new LatLng(centroid[0],centroid[1]);
    }

    private void addMarkers(LatLng latLng,String title){
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.maker_grano_cafe)));
    }

    private void startVideo(String videoCode){
        Intent intentVideo = new Intent(this,YouTubeViewActivity.class);
        intentVideo.putExtra(YOUTUBE_VIDEO_CODE,videoCode);
        startActivity(intentVideo);
    }
    @Override
    public void onPolygonClick(Polygon polygon) {
        Terreno tr_selectes = TerrenoUtil.terrenoByShapeID(polygon.getId(),al_terrenos);

        if (!TerrenoUtil.terrenoIsPayment(tr_selectes, NavigationDrawer.al_pagos)){
            startMembrecias(tr_selectes);
        }else {
            startCalendario(finca,tr_selectes);
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Log.e("MapCafe","Position : {"+latLng.toString()+"}");
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.e("MapCafe","Select Marker : "+ marker.getPosition());
        startVideo("OcXxv4eR7dU");
        return false;
    }
}
