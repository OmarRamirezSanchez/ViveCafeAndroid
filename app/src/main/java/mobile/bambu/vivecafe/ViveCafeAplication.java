package mobile.bambu.vivecafe;

import android.app.Application;
import android.util.Log;

import com.zopim.android.sdk.api.ZopimChat;
import com.zopim.android.sdk.model.VisitorInfo;

import mobile.bambu.vivecafe.Interfaces.Constans;

/**
 * Created by Bambu on 06/11/2016.
 */

public class ViveCafeAplication extends Application {
    public ViveCafeAplication() {
       this.initZoopim();
    }

    public void initZoopim(){
        ZopimChat.init(Constans.ACCOUNT_ZOOPIN_KEY);
        VisitorInfo emptyVisitorInfo = new VisitorInfo.Builder().build();
        ZopimChat.setVisitorInfo(emptyVisitorInfo);
        Log.v("Zopim Chat Sample", "Visitor info erased.");
    }
}
