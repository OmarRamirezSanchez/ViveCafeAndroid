package mobile.bambu.vivecafe.Interfaces;

/**
 * Created by Bambu on 03/11/2016.
 */

public interface Constans {

    /**
     *
     */

    public static final String DEVELOPER_KEY = "AIzaSyD7JKnC4MIIOJyLyDVqmwuHFi7l0EQsnhU";
    public static final String YOUTUBE_VIDEO_CODE = "YOUTUBE_VIDEO_CODE";

    public static final String NO_SE_ENCONTRO = "";
    /**
     * Terry States
     * 0 = Disponible
     * 1 = Ocupado
     * 2 = Adquirido
     * 3 = Vendido
     */
    public static final int STATE_TERRAIN_DISPONIBLE = 0;
    public static final int STATE_TERRAIN_OCUPADO = 1;
    public static final int STATE_TERRAIN_ADQUIRIDO = 2;
    public static final int STATE_TERRAIN_VENDIDO = 3;

    public static final String LATITUD = "latitude";
    public static final String LONGITUD = "longitude";

    /**
     * FireBase Constance Routes
     */
    public static final String fb_userRoot = "Users";
    public static final String fb_fincas = "Fincas";
    public static final String fb_fincas_eventos = "Eventos";
    public static final String fb_userInfo = "UserInformation";

    public static final String fb_pagos = "Pagos";
    public static final String fb_terrainsDefault = "Fincas/Ixhuacan/Terrenos";
    /**
     * Cafe Keys
     */
    public static final String cafe_type_arabigo = "Arabigo";
    public static final String cafe_type_robusto = "Robusto";
    public static final String cafe_type_colombiano= "Colombiano";
    /**
     * Key Models for Intents
     */
    public static final String KEY_FINCA = "KEY_FINCA";
    public static final String KEY_TERRENO = "KEY_TERRENO";

    public static final String USER_LOGIN = "USER_LOGIN";

    public static final String USER_MODEL_JSON = "USER_MODEL_JSON";

    public static final String KEY_UUID = "KEY_UUID";
    public static final String KEY_MEMBRECIA = "KEY_MEMBRECIA";
    public static final String KEY_CAFE = "KEY_TYPO_CAFE";


    public static final String BAT_RESPONSE = "BAT_RESPONSE";
    public static final String OK_RESPONSE = "GOOD RESPONSE";

    public static final String ACCOUNT_ZOOPIN_KEY = "4LXso5ijjl6C2oBCdyY5kvHKKNYITDKq";



    public static String user_key_mail = "email";
    public static String user_key_name = "name";
    public static String user_key_last_name= "last_name";
    public static String user_key_phone= "phone";
    public static String user_key_password= "password";
    public static String user_key_uuid= "uuid";
    public static String user_key_direccion= "direccion";
    public static String user_key_card_number= "card_number";

    public static String terreno_name = "nombre";
    public static String terreno_uid = "uuid";
    public static String terreno_uid_finca = "uuid_finca";
    public static String terreno_state = "state";
    public static String terreno_uids_eventos = "eventos";
    public static String terreno_delimitacion = "delimitacion";


    public static String evento_key_nombre= "nombre";
    public static String evento_key_fecha_inicio= "fecha_inicio";
    public static String evento_key_fecha_final= "fecha_final";
    public static String evento_key_decripcion= "descripcion";
    public static String evento_key_estado= "estado";
    public static String evento_key_uuid = "uuid";
    public static String evento_key_video_code = "video_code";

    public static String pago_key_uuid = "uuid";
    public static String pago_key_name= "name";
    public static String pago_key_uid_cliente = "uid_cliente";
    public static String pago_key_uid_membrecia = "uid_membrecia";
    public static String pago_key_uid_cafe = "uid_cafe";
    public static String pago_key_monto = "monto";
    public static String pago_key_terreno = "terreno";
    public static String pago_key_fecha_inicio = "fecha_inicio ";
    public static String pago_key_estado = "estado";
    public static String pago_key_cafe= "cafe";
    public static String pago_key_direccion= "direccion";

    public static String cafe_key_nombre= "nombre";
    public static String cafe_key_typo= "typo";
    public static String cafe_key_nivel_cafeina= "nivel_cafeina";
    public static String cafe_key_decripcion= "decripcion";
    public static String cafe_key_tipo_tostado= "tipo_tostado";
    public static String cafe_key_uuid = "uuid";
    public static String cafe_key_tipo_molido = "tipo_molido";

    public static String direccion_key_nombre = "nombre";
    public static String direccion_key_direccion_uno = "direccion_uno";
    public static String direccion_key_direccion_dos = "direccion_key_direccion_dos";
    public static String direccion_key_ciudad = "ciudad";
    public static String direccion_key_estado = "estado";
    public static String direccion_key_codigo_postal = "codigo_postal";

}
