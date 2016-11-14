package mobile.bambu.vivecafe.Models;

/**
 * Created by Bambu on 03/11/2016.
 */

import com.google.firebase.database.Exclude;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import mobile.bambu.vivecafe.Interfaces.Constans;

/**
 * Created by omarsanchez on 16/09/16.
 */
public class User implements Serializable,Constans {



    public String email;
    public String name;
    public String last_name;
    public String phone;
    public String password;
    public String uuid;
    public String direccion;
    public String card_number;


    public User(){
        this.email = "";
        this.name = "";
        this.last_name = "";
        this.phone = "";
        this.password = "";
        this.uuid = "";
        this.direccion = "";
        this.card_number = "";
    }

    public User(String email, String name, String last_name, String phone, String password, String direccion, String card_number) {
        this.email = email;
        this.name = name;
        this.last_name = last_name;
        this.phone = phone;
        this.password = password;
        this.uuid = "";
        this.direccion = direccion;
        this.card_number = card_number;
    }

    public User(JSONObject jsonObject) throws JSONException {
        this.name = jsonObject.getString(user_key_name);
        this.last_name = jsonObject.getString(user_key_last_name);
        this.direccion =jsonObject.getString(user_key_direccion);
        this.phone =jsonObject.getString(user_key_phone);
        this.email = jsonObject.getString(user_key_mail);
        this.card_number = jsonObject.getString(user_key_card_number);
        this.password = jsonObject.getString(user_key_password);
        this.uuid = jsonObject.getString(user_key_uuid);
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(user_key_name, name);
        result.put(user_key_last_name, last_name);
        result.put(user_key_direccion, direccion);
        result.put(user_key_phone, phone);
        result.put(user_key_mail, email);
        result.put(user_key_card_number, card_number);
        result.put(user_key_password, password);
        result.put(user_key_uuid, uuid);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", uuid='" + uuid + '\'' +
                ", direccion='" + direccion + '\'' +
                ", card_number='" + card_number + '\'' +
                '}';
    }
}

