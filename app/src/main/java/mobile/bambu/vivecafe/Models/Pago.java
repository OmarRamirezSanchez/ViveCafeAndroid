package mobile.bambu.vivecafe.Models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import mobile.bambu.vivecafe.Interfaces.Constans;

/**
 * Created by Bambu on 11/11/2016.
 */

public class Pago implements Serializable,Constans{


    public String uuid  = NO_SE_ENCONTRO;
    public String name = NO_SE_ENCONTRO;
    public String uid_cliente = NO_SE_ENCONTRO;
    public String uid_membrecia = NO_SE_ENCONTRO;
    public String uid_cafe = NO_SE_ENCONTRO;
    public String uid_terreno = NO_SE_ENCONTRO;
    public String monto = NO_SE_ENCONTRO;
    public String fecha_inicio = NO_SE_ENCONTRO;
    public String estado = NO_SE_ENCONTRO;
    public Cafe cafe;

    public Pago(){
        this.uuid =  NO_SE_ENCONTRO;
        this.name = NO_SE_ENCONTRO;
        this.uid_cliente = NO_SE_ENCONTRO;
        this.uid_membrecia = NO_SE_ENCONTRO;
        this.uid_terreno = NO_SE_ENCONTRO;
        this.monto = "0";
        this.fecha_inicio = NO_SE_ENCONTRO;
        this.estado = NO_SE_ENCONTRO;
    }

    public Pago(User user,Terreno terreno,Membrecia membrecia,Cafe cafe){
        this.uid_cliente =  user.uuid;
        this.uid_terreno =  terreno.uuid;
        this.uid_membrecia =  membrecia.uuiid;
        this.uid_cafe =  cafe.uid;
        this.cafe =  cafe;
        this.name = user.email;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(pago_key_name, name);
        result.put(pago_key_uuid, uuid);
        result.put(pago_key_uid_cliente, uid_cliente);
        result.put(pago_key_uid_membrecia, uid_membrecia);
        result.put(pago_key_uid_cafe, uid_cafe);
        result.put(pago_key_terreno,uid_terreno);
        result.put(pago_key_monto, monto);
        result.put(pago_key_fecha_inicio, fecha_inicio);
        result.put(pago_key_estado, estado);
        result.put(pago_key_cafe,cafe);
        return result;
    }
}
