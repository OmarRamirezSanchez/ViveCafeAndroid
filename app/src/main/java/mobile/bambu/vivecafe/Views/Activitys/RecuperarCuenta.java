package mobile.bambu.vivecafe.Views.Activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import me.drakeet.materialdialog.MaterialDialog;
import mobile.bambu.vivecafe.Interfaces.Constans;
import mobile.bambu.vivecafe.R;
import mobile.bambu.vivecafe.Util.Util;

import static mobile.bambu.vivecafe.Interfaces.Constans.BAT_RESPONSE;

/**
 * Created by Bambu on 03/11/2016.
 */

public class RecuperarCuenta extends AppCompatActivity implements View.OnClickListener,Constans{

    TextInputLayout et_correo;
    Button bt_login;
    MaterialDialog md_Loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_password);
        initUIElements();
        initTextWhatChert();
        initListenes();

    }

    private void initUIElements(){
        et_correo = (TextInputLayout) findViewById(R.id.et_email);
        bt_login = (Button) findViewById(R.id.bt_recuperar);
    }
    private void initListenes(){
        bt_login.setOnClickListener(this);
        bt_login.setEnabled(false);
        bt_login.setAlpha((float) 0.6);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initTextWhatChert(){
        et_correo.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateDataEmail();
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
    }
    private void validateDataEmail(){
        boolean validEmail = Util.isValidEmail(et_correo.getEditText().getText().toString());
        et_correo.setErrorEnabled(!validEmail);
        if(!validEmail) {
            et_correo.setError(getResources().getString(R.string.error_email_format));
            bt_login.setEnabled(false);
            bt_login.setAlpha((float) 0.6);
        }else {
            bt_login.setEnabled(true);
            bt_login.setAlpha(1);
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
        if (type.equals(OK_RESPONSE)){
            md_Notification = new MaterialDialog(this)
                    .setTitle("Exito")
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
    private void recoverPassword(String emailAddress){
        startDialog("Recuperando Contraseña","Por favor espera estamos recuperando tu cuenta");
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){
                            dismisDialog("Se a enviado un correo con las instrucciones",OK_RESPONSE);
                        }else{
                            dismisDialog(getResources().getString(R.string.error_404)+ "al usuario ",BAT_RESPONSE);
                        }


                    }
                });
    }
    @Override
    public void onClick(View v) {
        recoverPassword(et_correo.getEditText().getText().toString());
    }
}
