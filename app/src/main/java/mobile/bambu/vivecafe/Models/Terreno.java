package mobile.bambu.vivecafe.Models;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mobile.bambu.vivecafe.Interfaces.Constans;

/**
 * Created by Bambu on 08/11/2016.
 */

public class Terreno implements Constans,Serializable{

    public String name;
    public String uuid;
    public String uui_finca;
    public String id_Polygono = "";
    public int state;
    public ArrayList<Map<String,Float>> points;

    public Terreno() {
        this.name = NO_SE_ENCONTRO;
        this.uuid = NO_SE_ENCONTRO;
        this.uui_finca = NO_SE_ENCONTRO;
        this.state = STATE_TERRAIN_OCUPADO;
        this.points = new ArrayList<>();
    }

    public Terreno(String name) {
        this.name = name;
        this.uuid = "name";
        this.uui_finca = "";
        this.state = STATE_TERRAIN_OCUPADO;
        this.points = new ArrayList<>();
    }

    public Terreno(String name, String uuid, String id_Polygono, String uui_finca, int state, ArrayList<Map<String,Float>> points) {
        this.name = name;
        this.uuid = uuid;
        this.uui_finca = uui_finca;
        this.state = state;
        this.points = points;
    }



    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(terreno_name, name);
        result.put(terreno_uid, uuid);
        result.put(terreno_uid_finca,uui_finca);
        result.put(terreno_state, state);
        result.put(terreno_uids_eventos,terreno_uids_eventos);
        result.put(terreno_delimitacion, points);
        return result;
    }
    @Override
    public String toString() {
        return "{" +
                "name:'" + name + '\'' +
                ", uuid:'" + uuid + '\'' +
                ", uui_finca:'" + uui_finca + '\'' +
                ", state:" + state +
                ", eventos:"+"eventos: []"+
                ", delimitacion:[" + points.toString()+"]"+
                '}';
    }
}
