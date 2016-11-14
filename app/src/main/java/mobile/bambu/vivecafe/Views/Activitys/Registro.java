package mobile.bambu.vivecafe.Views.Activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

import me.drakeet.materialdialog.MaterialDialog;
import mobile.bambu.vivecafe.Models.User;
import mobile.bambu.vivecafe.Networck.RequestUserInformation;
import mobile.bambu.vivecafe.R;
import mobile.bambu.vivecafe.Util.Util;

import static mobile.bambu.vivecafe.Interfaces.Constans.BAT_RESPONSE;

/**
 * Created by Bambu on 03/11/2016.
 */

public class Registro extends AppCompatActivity implements View.OnClickListener,TextWatcher{

    EditText et_nombre,et_apellido,et_direccion,et_telefono,et_email,et_contraseña,et_no_tarjeta;
    Button bt_registrarse;
    public static String TAG = "Registro";
    private FirebaseAuth mAuth;

    private CoordinatorLayout coordinatorLayout;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"Creando Actividad de Registro");
        setContentView(R.layout.activity_registro);
        initDataObjets();
        initUIElements();
        initUIConfiguration();
        initTextWatcher();
        initToolBar();
    }

    private void initToolBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    private void initDataObjets(){
        context = this;
        mAuth = FirebaseAuth.getInstance();
    }
    private void initUIElements(){
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.snackbarPosition);
        et_nombre = (EditText) findViewById(R.id.et_name);
        et_apellido = (EditText) findViewById(R.id.et_apellido);
        et_direccion = (EditText) findViewById(R.id.et_direccion);
        et_telefono = (EditText) findViewById(R.id.et_telefono);
        et_email = (EditText) findViewById(R.id.et_email);
        et_no_tarjeta= (EditText) findViewById(R.id.et_no_tarjeta);
        et_contraseña = (EditText) findViewById(R.id.et_password);
        bt_registrarse = (Button) findViewById(R.id.bt_registrase);

    }
    private void initUIConfiguration(){
        bt_registrarse.setOnClickListener(this);
        bt_registrarse.setAlpha((float) 0.6);
        bt_registrarse.setEnabled(false);
    }

    private void initTextWatcher(){
        et_nombre.addTextChangedListener(this);
        et_apellido.addTextChangedListener(this);
        et_direccion.addTextChangedListener(this);
        et_telefono.addTextChangedListener(this);
        et_email.addTextChangedListener(this);
        et_no_tarjeta.addTextChangedListener(this);
        et_contraseña.addTextChangedListener(this);
        bt_registrarse.addTextChangedListener(this);
    }
    private void fireBaseUserRegister(final User user){
        mAuth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("FireBase","UIID User : "+task.getResult().getUser().getUid());
                            user.uuid = task.getResult().getUser().getUid();
                            fireBaseUserInfo(task,user);
                        }else {
                            fireBaseErrorMesajes(task);
                        }
                    }
                });
    }

    private void fireBaseUserInfo(@NonNull Task<AuthResult> task, final User user){

        FirebaseUser firebaseUser = task.getResult().getUser();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(user.name)
                .build();

        firebaseUser.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            RequestUserInformation.writeNewUserInfo(user,database);
                            startActivity(new Intent(context, Login.class));
                            ((Activity) context).finish();
                            finishAffinity();
                        }
                    }
                });
    }
    private void fireBaseErrorMesajes(@NonNull Task<AuthResult> task){
        Log.e(TAG,"Ocurrio Un Error : "+ task.getException().toString());
        try {
            throw task.getException();
        } catch(FirebaseAuthWeakPasswordException e) {
            dismisDialog("La contraseña es damasiado corta",BAT_RESPONSE);
        } catch(FirebaseAuthInvalidCredentialsException e) {
            dismisDialog("Las credenciales sin invalida",BAT_RESPONSE);
        } catch(FirebaseAuthUserCollisionException e) {
            dismisDialog("Ya existe un usuario con esas credenciales",BAT_RESPONSE);
        } catch(Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }


    private void startSnackBard(String body){
        Snackbar.make(coordinatorLayout, body, Snackbar.LENGTH_LONG)
                .show();
    }

    MaterialDialog md_Notification;
    private void dismisDialog(String response,String type){
        //Bat Response Response
        if (type.equals(BAT_RESPONSE)){
            md_Notification = new MaterialDialog(this)
                    .setTitle("Lo Sentimos Ocurrio un Error")
                    .setMessage(response)
                    .setPositiveButton("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            md_Notification.dismiss();
                        }
                    });
            md_Notification.show();
        }

    }

    private void validateData(){
     //    EditText et_nombre,et_apellido,et_direccion,et_telefono,et_email,et_contraseña,et_no_tarjeta;
        if(Util.isValidEmail(et_email.getText().toString()) &&
            Util.isValidData(et_nombre.getText().toString()) &&
            Util.isValidData(et_apellido.getText().toString()) &&
                Util.isValidData(et_telefono.getText().toString())&&
                Util.isValidPasword(et_contraseña.getText().toString())&&
                Util.isValidData(et_no_tarjeta.getText().toString())) {
                bt_registrarse.setEnabled(true);
                bt_registrarse.setAlpha(1);
        }else {
            bt_registrarse.setEnabled(false);
            bt_registrarse.setAlpha((float) 0.6);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_registrase:
                User usuario = new User();
                usuario.name = Util.notNullValueString(et_nombre.getText().toString());
                usuario.last_name = Util.notNullValueString(et_apellido.getText().toString());
                usuario.direccion = Util.notNullValueString(et_direccion.getText().toString());
                usuario.phone = Util.notNullValueString(et_telefono.getText().toString());
                usuario.email = Util.notNullValueString(et_email.getText().toString());
                usuario.password =Util.notNullValueString(et_contraseña.getText().toString());
                usuario.card_number =Util.notNullValueString(et_no_tarjeta.getText().toString());
                this.fireBaseUserRegister(usuario);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        validateData();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
