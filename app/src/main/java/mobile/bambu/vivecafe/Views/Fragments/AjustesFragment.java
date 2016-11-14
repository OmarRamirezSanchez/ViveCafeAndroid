package mobile.bambu.vivecafe.Views.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.FirebaseDatabase;

import mobile.bambu.vivecafe.Interfaces.Constans;
import mobile.bambu.vivecafe.Models.User;
import mobile.bambu.vivecafe.NavigationDrawer;
import mobile.bambu.vivecafe.Networck.RequestUserInformation;
import mobile.bambu.vivecafe.R;
import mobile.bambu.vivecafe.Views.Activitys.Login;

/**
 * Created by Bambu on 06/11/2016.
 */

public class AjustesFragment extends Fragment implements Constans ,View.OnClickListener{

    TextInputLayout til_nombre,til_apellidos,til_telefono,til_tarjeta,til_dierccion;
    Button bt_cerrarsesion,bt_save;
    User user;

    public void  AjustesFragment(User user){
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ajustes, container, false);
        initUIElements(view);
        initUIConfiguration();
        initListeners();
        return view;
    }


    private void initUIElements(View view){
        bt_cerrarsesion = (Button)view.findViewById(R.id.bt_ajustes_cerrarsesion);
        bt_save = (Button)view.findViewById(R.id.bt_save);
        til_nombre = (TextInputLayout) view.findViewById(R.id.til_ajustes_name);
        til_apellidos = (TextInputLayout) view.findViewById(R.id.til_ajustes_last_name);
        til_telefono = (TextInputLayout) view.findViewById(R.id.til_ajustes_phone);
        til_tarjeta = (TextInputLayout) view.findViewById(R.id.til_ajustes_cardnumber);
        til_dierccion = (TextInputLayout) view.findViewById(R.id.til_ajustes_direccion);
     }

    private void initListeners(){
        bt_cerrarsesion.setOnClickListener(this);
        bt_save.setOnClickListener(this);
    }

    private void initUIConfiguration(){
        if (null != user){
            til_nombre.getEditText().setText(user.name);
            til_apellidos.getEditText().setText(user.last_name);
            til_tarjeta.getEditText().setText(user.card_number);
            til_telefono.getEditText().setText(user.phone);
            til_dierccion.getEditText().setText(user.direccion);
        }
    }

    private void cerrarSesion() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(USER_LOGIN, false);
        editor.putString(USER_MODEL_JSON,"{}");
        editor.commit();
        startActivity(new Intent(getContext(), Login.class));
        NavigationDrawer.al_pagos.clear();
        getActivity().finish();
    }

    private void updateUser(){
        user.name = til_nombre.getEditText().getText().toString();
        user.last_name =  til_apellidos.getEditText().getText().toString();
        user.card_number = til_tarjeta.getEditText().getText().toString();
        user.phone = til_telefono.getEditText().getText().toString();
        user.direccion = til_dierccion.getEditText().getText().toString();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_ajustes_cerrarsesion:
                cerrarSesion();
                break;
            case R.id.bt_save:
                if(null != user){
                    updateUser();
                    RequestUserInformation.updateUserInfo(user, FirebaseDatabase.getInstance());
                }
                break;
        }
    }
}
