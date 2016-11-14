package mobile.bambu.vivecafe.Models;

import java.io.Serializable;

import mobile.bambu.vivecafe.Interfaces.Constans;

/**
 * Created by Bambu on 11/11/2016.
 */

public class Membrecia implements Serializable,Constans {

    public String uuiid;
    public String nombre;
    public String descripcion;
    public String temporalidad;
    public String kg_mensuales;

    public Membrecia(){
        this.uuiid = "";
        this.nombre = NO_SE_ENCONTRO;
        this.descripcion = NO_SE_ENCONTRO;
        this.temporalidad = NO_SE_ENCONTRO;
        this.kg_mensuales = NO_SE_ENCONTRO;
    }
}
