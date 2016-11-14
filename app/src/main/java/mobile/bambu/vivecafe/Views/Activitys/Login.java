package mobile.bambu.vivecafe.Views.Activitys;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import me.drakeet.materialdialog.MaterialDialog;
import mobile.bambu.vivecafe.Interfaces.Constans;
import mobile.bambu.vivecafe.Models.User;
import mobile.bambu.vivecafe.NavigationDrawer;
import mobile.bambu.vivecafe.R;
import mobile.bambu.vivecafe.Util.Util;

import static mobile.bambu.vivecafe.Interfaces.Constans.fb_userInfo;
import static mobile.bambu.vivecafe.Interfaces.Constans.fb_userRoot;

/**
 * Created by Bambu on 03/11/2016.
 */

public class Login extends AppCompatActivity implements View.OnClickListener,Constans{


    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    Button bt_login;
    TextInputLayout et_correo;
    TextInputLayout et_contrasena;
    TextView tv_olv_contrasena;
    TextView tv_registrarse;
    private ValueEventListener vel_userInformation;
    MaterialDialog md_Loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.e(TAG,"Creando Actividad de Login");
        this.initDataObjets();
        this.initUIElements();
        this.initUIConfiguration();
        this.initTextWhatChert();
        this.validatPlayServices();
    }

    private void validatPlayServices(){
        final int playServicesStatus = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if(playServicesStatus != ConnectionResult.SUCCESS){
            //If google play services in not available show an error dialog and return
            final Dialog errorDialog = GoogleApiAvailability.getInstance().getErrorDialog(this, playServicesStatus, 0, null);
            errorDialog.show();
            return;
        }
    }
    private void initDataObjets(){
        mAuth = FirebaseAuth.getInstance();
    }
    private void initUIElements(){
        bt_login = (Button) findViewById(R.id.bt_inicar_sesion);
        et_contrasena  = (TextInputLayout) findViewById(R.id.et_password);
        et_correo = (TextInputLayout) findViewById(R.id.et_email);
        tv_olv_contrasena = (TextView) findViewById(R.id.tv_olvidarpassword);
        tv_registrarse = (TextView) findViewById(R.id.tv_registrate);
    }

    private void initUIConfiguration(){
        bt_login.setOnClickListener(this);
        bt_login.setEnabled(false);
        bt_login.setAlpha((float)0.6);
        tv_olv_contrasena.setOnClickListener(this);
        tv_registrarse.setOnClickListener(this);

    }



    private void readUserInformation(String uuid){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference db_ref=  database.getReference(fb_userRoot).child(uuid).child(fb_userInfo);

        vel_userInformation = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Ingreso", "Value is: " + dataSnapshot.getValue());
                User user = dataSnapshot.getValue(User.class);
                startFlujo(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Ingreso", "loadPost:onCancelled", databaseError.toException());
                dismisDialog(getResources().getString(R.string.error_403)+ "recuperar la informacion del usuario",BAT_RESPONSE);
            }
        };
        db_ref.addValueEventListener(vel_userInformation);

    }

    private void startFlujo(User user){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(USER_LOGIN, true);
        JSONObject obj = new JSONObject();
        try {
            obj = new JSONObject(user.toMap());
        }catch (Exception e){
            Log.e(TAG,"Parce JSON "+e.toString());
        }
        editor.putString(USER_MODEL_JSON,obj.toString());
        editor.commit();
        Intent intent = new Intent(this, NavigationDrawer.class);
        intent.putExtra(KEY_UUID,user);
        startActivity(intent);
        finishAffinity();

    }
    private void fireBaseUserLogin(String email ,String password ){
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            dismisDialog("Sesion Iniciada Con Exito",OK_RESPONSE);
                            FirebaseUser firebaseUser = task.getResult().getUser();
                            Log.d(TAG,"FireBaseUser:"+firebaseUser.getUid());
                            readUserInformation(task.getResult().getUser().getUid());
                        }else{
                            dismisDialog(getResources().getString(R.string.error_404)+ " al usuario ",BAT_RESPONSE);
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                        }
                    }
                });
    }

    private void initTextWhatChert(){
        et_contrasena.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateData();
                validateDataPaswword();
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
        et_correo.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                validateData();
                validateDataEmail();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
    }
    private void validateDataPaswword(){
        boolean validPasword = et_contrasena.getEditText().getText().toString().length() >= 6;
        et_contrasena.setErrorEnabled(!validPasword);
        if (!validPasword){
            et_contrasena.setError(getResources().getString(R.string.error_passwort_short));
        }
    }
    private void validateData(){
        boolean validEmail = Util.isValidEmail(et_correo.getEditText().getText().toString());
        boolean validPasword = et_contrasena.getEditText().getText().toString().length() >= 6;
        if( validEmail && validPasword){
            bt_login.setEnabled(true);
            bt_login.setAlpha(1);
        }else {
            bt_login.setEnabled(false);
            bt_login.setAlpha((float) 0.6);
        }
    }
    private void validateDataEmail(){
        boolean validEmail = Util.isValidEmail(et_correo.getEditText().getText().toString());
        et_correo.setErrorEnabled(!validEmail);
        if(!validEmail) {
            et_correo.setError(getResources().getString(R.string.error_email_format));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_inicar_sesion:
                startDialog("Inicar Sesion","Iniciando session ...");
                fireBaseUserLogin(et_correo.getEditText().getText().toString(),et_contrasena.getEditText().getText().toString());
                break;

            case R.id.tv_olvidarpassword:
                Intent intent_recuperar = new Intent(this, RecuperarCuenta.class);
                startActivity(intent_recuperar);
                break;

            case R.id.tv_registrate:
                Intent intent_registro = new Intent(this, Registro.class);
                startActivity(intent_registro);
                break;
        }
    }
    MaterialDialog md_Notification;
    private void dismisDialog(String response,String type){
        if(md_Loading != null){
            md_Loading.dismiss();
        }
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
    private void  startDialog(String title,String description){
        if(md_Loading == null){
           md_Loading = new MaterialDialog(this)
                    .setTitle(title)
                    .setMessage(description)
                    .setCanceledOnTouchOutside(false);
            md_Loading.show();
        }
    }
}
