package mobile.bambu.vivecafe.Networck;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

import mobile.bambu.vivecafe.Interfaces.Constans;
import mobile.bambu.vivecafe.Models.User;

/**
 * Created by Bambu on 03/11/2016.
 */

public class RequestUserInformation implements Constans{

    public static void writeNewUserInfo(User user, FirebaseDatabase database ) {
        DatabaseReference db_ref=  database.getReference(fb_userRoot).child(user.uuid).child(fb_userInfo);
        Map<String, Object> userValues = user.toMap();
        db_ref.setValue(userValues);
    }
    public static void updateUserInfo(User user,FirebaseDatabase database){
        DatabaseReference db_reference = database.getReference(fb_userRoot).child(user.uuid).child(fb_userInfo);
        db_reference.updateChildren(user.toMap());
    }

}
