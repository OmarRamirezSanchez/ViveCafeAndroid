package mobile.bambu.vivecafe.Views.Adapters;

import android.app.Activity;
import android.content.Context;
import android.nfc.Tag;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import mobile.bambu.vivecafe.Interfaces.Constans;
import mobile.bambu.vivecafe.Models.Evento;
import mobile.bambu.vivecafe.Models.Finca;
import mobile.bambu.vivecafe.R;
import mobile.bambu.vivecafe.Util.Util;
import mobile.bambu.vivecafe.Views.Activitys.CalendarioFinca;
import mobile.bambu.vivecafe.Views.Fragments.FincasFragment;

/**
 * Created by Bambu on 06/11/2016.
 */

public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.EventoViewHolder> implements Constans {
    private ArrayList<Evento> eventos;
    private Context mContext;
    private EventoSelected listenerEventosSelected;

    public EventosAdapter(Context context, AppCompatActivity fragment, ArrayList<Evento> eventos) {
        this.eventos = eventos;
        this.mContext = context;
        listenerEventosSelected = (CalendarioFinca) fragment;
    }

    @Override
    public EventoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evento, null);
        EventoViewHolder viewHolder = new EventoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventoViewHolder holder, final int position) {
        Evento evento = eventos.get(position);
        holder.tv_name.setText(evento.getNombre());
        holder.tv_description.setText(evento.getDecripcion());
        holder.tv_date.setText(Util.getHoraString(Util.strintToDate(evento.getFecha_inicio())));
        holder.tv_dia.setText(getDiaString(Util.strintToDate(evento.getFecha_inicio()),mContext));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerEventosSelected.eventoSelected(eventos.get(position),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != eventos ? eventos.size() : 0);

    }
    public  String getDiaString(Date date, Context context){
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
       return calendar.get(calendar.DAY_OF_MONTH) + "/" +context.getResources().getStringArray(R.array.ar_meses)[calendar.get(calendar.MONTH)-1];
    }
    class EventoViewHolder extends RecyclerView.ViewHolder{
        protected TextView tv_name,tv_description,tv_date,tv_dia;

        public EventoViewHolder(View view) {
            super(view);
            this.tv_name = (TextView) view.findViewById(R.id.tv_item_evento_title);
            this.tv_description = (TextView) view.findViewById(R.id.tv_item_evento_description);
            this.tv_date = (TextView) view.findViewById(R.id.tv_item_evento_hour);
            this.tv_dia = (TextView)view.findViewById(R.id.tv_item_evento_dia);
        }
    }

    public interface EventoSelected {
        void eventoSelected(Evento evento, int index);
    }
}