package mobile.bambu.vivecafe.Models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import mobile.bambu.vivecafe.Interfaces.Constans;

/**
 * Created by Bambu on 06/11/2016.
 */

public class Evento implements Serializable,Constans{
    String nombre;
    String fecha_inicio;
    String fecha_final;
    String descripcion;
    String estado;
    String video_code;
    String uuid;

    public Evento() {
        this.nombre = "";
        this.fecha_inicio = "";
        this.fecha_final = "";
        this.descripcion = "";
        this.estado = "";
        this.uuid = "";
        this.video_code = "";
    }
    public Evento(String nombre) {
        this.nombre = nombre;
    }

    public Evento(String nombre, String fecha_inicio, String fecha_final, String decripcion, String estado) {
        this.nombre = nombre;
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
        this.descripcion = decripcion;
        this.estado = estado;
        this.video_code = "";
        this.uuid = "";
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(evento_key_nombre, nombre);
        result.put(evento_key_decripcion, descripcion);
        result.put(evento_key_estado, estado);
        result.put(evento_key_fecha_inicio, fecha_inicio);
        result.put(evento_key_fecha_final, fecha_final);
        result.put(evento_key_uuid,uuid);
        result.put(evento_key_video_code,video_code);
        return result;
    }

    public void updateFrom(Evento evento){
        this.nombre = evento.nombre;
        this.fecha_inicio = evento.fecha_inicio;
        this.fecha_final = evento.fecha_final;
        this.descripcion = evento.descripcion;
        this.estado = evento.estado;
        this.video_code =  evento.video_code;
    }

    public void setVideo_code(String video_code) {
        this.video_code = video_code;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVideo_code() {
        return video_code;
    }

    public String getUuid() {
        return uuid;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public String getFecha_final() {
        return fecha_final;
    }

    public String getDecripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setFecha_final(String fecha_final) {
        this.fecha_final = fecha_final;
    }

    public void setDecripcion(String decripcion) {
        this.descripcion = decripcion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "nombre='" + nombre + '\'' +
                ", fecha_inicio='" + fecha_inicio + '\'' +
                ", fecha_final='" + fecha_final + '\'' +
                ", decripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", video_code='" + video_code + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
