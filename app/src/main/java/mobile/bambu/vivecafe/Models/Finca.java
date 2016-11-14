package mobile.bambu.vivecafe.Models;

import java.io.Serializable;
import java.net.PortUnreachableException;

/**
 * Created by Bambu on 06/11/2016.
 */

public class Finca implements Serializable{

    private String uuid;
    private String nombre;
    private String image_name;
    private float latitud;
    private float longitud;

    public Finca(String nombre) {
        this.nombre = nombre;
        this.image_name = "";
        this.latitud = 0;
        this.longitud = 0;
        this.uuid = "Ixhuacan";
    }

    public Finca(String nombre, String image_name, float latitud, float longitud) {
        this.nombre = nombre;
        this.image_name = image_name;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public String getUUID(){return uuid;}
    public float getLatitud(){return latitud;}
    public float getLongitud(){return longitud;}
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Finca{" +
                "uuid='" + uuid + '\'' +
                ", nombre='" + nombre + '\'' +
                ", image_name='" + image_name + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }
}
