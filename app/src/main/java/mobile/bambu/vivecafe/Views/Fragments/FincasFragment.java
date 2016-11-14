package mobile.bambu.vivecafe.Views.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

import mobile.bambu.vivecafe.Interfaces.Constans;
import mobile.bambu.vivecafe.Models.Finca;
import mobile.bambu.vivecafe.Models.User;
import mobile.bambu.vivecafe.NavigationDrawer;
import mobile.bambu.vivecafe.R;
import mobile.bambu.vivecafe.Views.Activitys.MapCafe;
import mobile.bambu.vivecafe.Views.Adapters.FincasAdapater;

/**
 * Created by Bambu on 06/11/2016.
 */

public class FincasFragment extends Fragment implements FincasAdapater.FincaSelected,Constans {

    RecyclerView rv_fincas;
    ArrayList<Finca> al_fincas = new ArrayList<>();
    FincasAdapater fincasAdapater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fincas, container, false);
        Log.e("FincasFragment","Fincas Fragmenet");
        this.initDataObjets();
        this.initUIElements(view);
        this.initUIConfiguration();
        return view;
    }


    public void initDataObjets(){
        String [] fincasTitle = getResources().getStringArray(R.array.ar_fincas);
        for(String title:fincasTitle){
            //19.041294, -96.964198
            Finca finca = new Finca(title,"",19.041294f,-96.964198f);
            finca.setUuid("Ixhuacan");
            al_fincas.add(finca);
        }
    }

    public void initUIElements(View view){
        rv_fincas = (RecyclerView) view.findViewById(R.id.rv_fincas);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        rv_fincas.setLayoutManager(mLinearLayoutManager);
    }

    public void initUIConfiguration(){
        fincasAdapater = new FincasAdapater(getContext(),this,al_fincas);
        rv_fincas.setAdapter(fincasAdapater);
    }

    @Override
    public void fincaSelected(Finca finca, int index) {
        Intent intent_finca = new Intent(getContext(), MapCafe.class);
        intent_finca.putExtra(KEY_FINCA,finca);
        intent_finca.putExtra(KEY_UUID,((NavigationDrawer)getActivity()).user);
        getActivity().startActivity(intent_finca);
    }
}
