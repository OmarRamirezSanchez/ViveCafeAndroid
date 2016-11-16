package mobile.bambu.vivecafe.Views.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;

import mobile.bambu.vivecafe.Interfaces.Constans;
import mobile.bambu.vivecafe.Models.Finca;
import mobile.bambu.vivecafe.R;
import mobile.bambu.vivecafe.Util.Util;
import mobile.bambu.vivecafe.Views.Activitys.MapCafe;
import mobile.bambu.vivecafe.Views.Fragments.FincasFragment;

/**
 * Created by Bambu on 06/11/2016.
 */

public class FincasAdapater extends RecyclerView.Adapter<FincasAdapater.FincaViewHolder> implements Constans{
    private ArrayList<Finca> fincas;
    private Context mContext;
    private FincaSelected listenerFincaSelected;

    public FincasAdapater(Context context, Fragment fragment, ArrayList<Finca> fincas) {
        this.fincas = fincas;
        this.mContext = context;
        listenerFincaSelected = (FincasFragment) fragment;
    }

    @Override
    public FincaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fincas, null);
        FincaViewHolder viewHolder = new FincaViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FincaViewHolder holder, final int position) {
        Finca finca = fincas.get(position);
        holder.im_name.setText(finca.getNombre());
        if( position%2 == 0){
            holder.im_filtro.setBackgroundColor(ContextCompat.getColor(mContext,R.color.im_flitro_rojo));
        }else {
            holder.im_filtro.setBackgroundColor(ContextCompat.getColor(mContext,R.color.im_flitro_verde));
        }
        holder.im_back_gound.setImageResource(Util.getImageFinca(finca));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerFincaSelected.fincaSelected(fincas.get(position),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != fincas ? fincas.size() : 0);
    }

    class FincaViewHolder extends RecyclerView.ViewHolder{
        protected ImageView im_back_gound;
        protected ImageView im_filtro;
        protected TextView im_name;

        public FincaViewHolder(View view) {
            super(view);
            this.im_back_gound = (ImageView) view.findViewById(R.id.item_finca_im_background);
            this.im_name = (TextView) view.findViewById(R.id.item_finca_title);
            this.im_filtro = (ImageView) view.findViewById(R.id.item_im_fitro);
        }
    }

    public interface FincaSelected {
        void fincaSelected(Finca finca,int index);
    }
}
