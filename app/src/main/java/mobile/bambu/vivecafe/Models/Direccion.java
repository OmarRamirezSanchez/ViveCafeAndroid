package mobile.bambu.vivecafe.Models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import mobile.bambu.vivecafe.Interfaces.Constans;

/**
 * Created by Bambu on 15/11/2016.
 */

public class Direccion implements Serializable,Constans {
    public String nombre;
    public String direccion_uno;
    public String direccion_dos;
    public String ciudad;
    public String estado;
    public String codigo_postal;

    public void Direccion(){
        nombre =  NO_SE_ENCONTRO;
        direccion_uno = NO_SE_ENCONTRO;
        direccion_dos = NO_SE_ENCONTRO;
        ciudad = NO_SE_ENCONTRO;
        estado  = NO_SE_ENCONTRO;
        codigo_postal = NO_SE_ENCONTRO;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(direccion_key_nombre, nombre);
        result.put(direccion_key_direccion_uno, direccion_uno);
        result.put(direccion_key_direccion_dos, direccion_dos);
        result.put(direccion_key_ciudad, ciudad);
        result.put(direccion_key_estado, estado);
        result.put(direccion_key_codigo_postal,codigo_postal);
        return result;
    }
}
