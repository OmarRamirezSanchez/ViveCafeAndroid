package mobile.bambu.vivecafe.Views.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import mobile.bambu.vivecafe.Interfaces.Constans;
import mobile.bambu.vivecafe.Models.Evento;
import mobile.bambu.vivecafe.Models.Finca;
import mobile.bambu.vivecafe.R;
import mobile.bambu.vivecafe.Util.Util;
import mobile.bambu.vivecafe.Views.Adapters.EventosAdapter;
import mobile.bambu.vivecafe.Views.Adapters.FincasAdapater;

/**
 * Created by Bambu on 06/11/2016.
 */

public class CalendarioFinca extends AppCompatActivity implements EventosAdapter.EventoSelected,Constans,View.OnClickListener {
    public static String TAG = "CalendarioFinca";
    /**
     * UI
     */
    Button bt_verVideo;

    /**
     * Models
     */
    Finca finca;
    RecyclerView rv_eventos;
    Toolbar toolbar;
    ArrayList<Evento> al_eventos = new ArrayList<>();
    EventosAdapter eventoAdapter;
    DatabaseReference dbr_eventosListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_calendario_finca);
        this.initDatObjects();
        this.initUIElements();
        this.initUIConfiguration();
        this.initFincasEventListener();
        this.initListener();
        calendarConfiguration();
        Log.e(TAG,"onCreate : Inciando View");
    }

    public void initDatObjects(){
        finca = (Finca) getIntent().getExtras().getSerializable(KEY_FINCA);
    }
    CompactCalendarView compactCalendarView;
    public void calendarConfiguration(){

        compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendarView.getEvents(dateClicked);
                Log.d(TAG, "Day was clicked: " + dateClicked + " with events " + events);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
            }
        });
    }
    private void initToolBar(){
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWite));
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.colorAccent));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    public void initFincasEventListener(){
        ChildEventListener cel_Enventos = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.e(TAG,"onChildAdded : "+dataSnapshot.getValue());
                Evento evento = dataSnapshot.getValue(Evento.class);
                try {
                    compactCalendarView.addEvent(new Event(Color.GREEN,Util.getTimeStamp(evento.getFecha_inicio()), "Muajajaja"), false);
                }catch (Exception e){}
                al_eventos.add(evento);
                eventoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        dbr_eventosListener = FirebaseDatabase.getInstance().getReference().child(fb_fincas).child(finca.getUUID()).child(fb_fincas_eventos);
        dbr_eventosListener.addChildEventListener(cel_Enventos);
    }

    public void initUIElements(){
        rv_eventos = (RecyclerView) findViewById(R.id.rv_calendar);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        rv_eventos.setLayoutManager(mLinearLayoutManager);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bt_verVideo = (Button) findViewById(R.id.bt_calendario_ver);
    }

    public void initUIConfiguration(){
        eventoAdapter = new EventosAdapter(this,this,al_eventos);
        rv_eventos.setAdapter(eventoAdapter);
        setSupportActionBar(toolbar);
        initToolBar();
    }

    public void initListener(){
        bt_verVideo.setOnClickListener(this);
    }

    @Override
    public void eventoSelected(Evento evento, int index) {

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.aceptar) {
            return true;
        }
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        /*
        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/watch?v=qbvICvih99U"));
                        startActivity(browserIntent);
       */
        VideoView video =(VideoView)findViewById(R.id.vv_video);
        video.setVisibility(View.VISIBLE);
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(video);
        Uri uri=Uri.parse("rtsp://r7---sn-4g57kue6.googlevideo.com/Ck0LENy73wIaRAmk3cJBg-iaXhMYDSANFC3u0pRWMOCoAUIJbXYtZ29vZ2xlSARSBXdhdGNoYIaluaTkzciOVooBCzVxRjNraG5XcXdnDA==/D693A8E7577C3A29E60C292B42C9C87D7C25A565.762A63DC4CA0A028DA83256C6A79E5F160CBEDA3/yt6/1/video.3gp");
        video.setMediaController(mediaController);
        video.setVideoURI(uri);
        video.requestFocus();
        video.start();
         /*   */
    }
}
