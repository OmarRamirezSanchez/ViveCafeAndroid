package mobile.bambu.vivecafe.Views.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import mobile.bambu.vivecafe.Interfaces.Constans;
import mobile.bambu.vivecafe.Models.Finca;
import mobile.bambu.vivecafe.R;

/**
 * Created by Bambu on 14/11/2016.
 */

public class YouTubeViewActivity extends YouTubeBaseActivity  implements Constans,YouTubePlayer.OnInitializedListener{

    private static final int RECOVERY_DIALOG_REQUEST = 1;
    // YouTube player view
    private YouTubePlayerView youTubeView;
    private String videoCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_youtube_video);
        this.initDataObject();
        youTubeView = (YouTubePlayerView) findViewById(R.id.vy_video);
        youTubeView.initialize(DEVELOPER_KEY, this);
    }

    private void initDataObject(){
        videoCode = getIntent().getExtras().getString(YOUTUBE_VIDEO_CODE);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    getString(R.string.error_player), errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            player.loadVideo(videoCode);
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(DEVELOPER_KEY, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.vy_video);
    }

}
