package mobile.bambu.vivecafe.Models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import mobile.bambu.vivecafe.Interfaces.Constans;

/**
 * Created by Bambu on 11/11/2016.
 */

public class Cafe implements Serializable,Constans {


    public String uid =  NO_SE_ENCONTRO;
    public String nombre =  NO_SE_ENCONTRO;
    public String typo = NO_SE_ENCONTRO;
    public String descripcion = NO_SE_ENCONTRO;
    public String nivel_cafeina = NO_SE_ENCONTRO;
    public String tipo_tostado  = NO_SE_ENCONTRO;
    public String tipo_molido = NO_SE_ENCONTRO;


    public void Cafe(){
       uid =  NO_SE_ENCONTRO;
       nombre =  NO_SE_ENCONTRO;
       typo = NO_SE_ENCONTRO;
       descripcion = NO_SE_ENCONTRO;
       nivel_cafeina = NO_SE_ENCONTRO;
       tipo_tostado  = NO_SE_ENCONTRO;
       tipo_molido = NO_SE_ENCONTRO;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(cafe_key_nombre, nombre);
        result.put(cafe_key_uuid, uid);
        result.put(cafe_key_typo, typo);
        result.put(cafe_key_decripcion, descripcion);
        result.put(cafe_key_nivel_cafeina, nivel_cafeina);
        result.put(cafe_key_tipo_tostado,tipo_tostado);
        result.put(cafe_key_tipo_molido, tipo_molido);
        return result;
    }
}
