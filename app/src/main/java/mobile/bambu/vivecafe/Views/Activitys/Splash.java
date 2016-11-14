package mobile.bambu.vivecafe.Views.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.json.JSONException;
import org.json.JSONObject;

import mobile.bambu.vivecafe.Interfaces.Constans;
import mobile.bambu.vivecafe.Models.User;
import mobile.bambu.vivecafe.NavigationDrawer;
import mobile.bambu.vivecafe.R;

/**
 * Created by Bambu on 04/11/2016.
 */

public class Splash extends AppCompatActivity implements Constans{
    public static final String TAG = Splash.class.toString();

    private Context context;
    private int segundos = 3;
    private int millisecons = segundos * 800;
    View v;
    boolean isLogin;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inside your activity (if you did not enable transitions in your theme)
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fullScreenMode();
        setContentView(R.layout.activity_splash);
        context = this;



        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        isLogin =sharedPreferences.getBoolean(USER_LOGIN,false);
        String userModel =  sharedPreferences.getString(USER_MODEL_JSON,"nil");

        if(!userModel.equals("nil")) {
            try {
                JSONObject jObject = new JSONObject(userModel);
                user = new User(jObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        startCount();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    /**
     * Use immersiveMode for fullscreen
     */
    public void fullScreenMode() {


        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;

        boolean isImmersiveModeEnabled =
                ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);


        if (Build.VERSION.SDK_INT >= 14) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }


        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }
        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
    }

    /**
     * el splash y la cuenta regresiva para llamar a la ectividad SesionActivyt
     */
    private void startCount() {

        new CountDownTimer(millisecons, 800) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                if (isLogin) {
                    Intent intent = new Intent(Splash.this, NavigationDrawer.class);
                    intent.putExtra(KEY_UUID,user);
                    startActivity(intent);
                    finish();
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Intent intent = new Intent(Splash.this, Login.class);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(Splash.this, findViewById(R.id.im_logo_login), "logo");
                    startActivity(intent, options.toBundle());
                    Splash.this.finishAfterTransition();
                } else {
                    startActivity(new Intent(Splash.this, Login.class));
                    finish();
                }


            }
        }.start();
    }


}
