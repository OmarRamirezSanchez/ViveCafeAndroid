package mobile.bambu.vivecafe.Util;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import mobile.bambu.vivecafe.Models.Pago;
import mobile.bambu.vivecafe.Models.Terreno;

/**
 * Created by Bambu on 08/11/2016.
 */

public class TerrenoUtil {

    public static LatLng[] terreno_1 = {
            new LatLng(19.043348f,-96.964450f),
            new LatLng(19.043135f, -96.963238f),
            new LatLng(19.042454248058515,-96.96331534534693),
            new LatLng(19.04256644082944,-96.9648492336273)};


    public static LatLng[] terreno_2 = {
            new LatLng (19.03807391679322,-96.96560259908438),
            new LatLng (19.037539876751385,-96.964946128428),
            new LatLng (19.038608272053597,-96.96443181484936),
            new LatLng (19.0388161820579,-96.9652609527111)};
    public static LatLng[] terreno_3 = {
            new LatLng (19.03824949990952,-96.96623358875512),
            new LatLng (19.038990179712012,-96.96588288992645),
            new LatLng (19.039179707003893,-96.96655578911304),
            new LatLng (19.03844917001469,-96.96695309132338)};
    public static LatLng[] terreno_4 = {
            new LatLng(19.03824949990952,-96.96623358875512),
            new LatLng(19.03807391679322,-96.96560259908438),
            new LatLng(19.0388161820579,-96.9652609527111),
            new LatLng(19.038990179712012,-96.96588288992645)};
    public static LatLng[] terreno_5 = {
            new LatLng(19.03939015177017,-96.96492332965134),
            new LatLng(19.039294120531896,-96.96410525590181),
            new LatLng(19.038608272053597,-96.96443181484936),
            new LatLng(19.0388161820579,-96.9652609527111)};
    public static LatLng[] terreno_6 = {
            new LatLng(19.039533406187388,-96.96552213281393),
            new LatLng(19.03939015177017,-96.96492332965134),
            new LatLng(19.0388161820579,-96.9652609527111),
            new LatLng(19.038990179712012,-96.96588288992645)};
    public static LatLng[] terreno_7 = {
            new LatLng(19.03956541656017,-96.96555733680727),
            new LatLng(19.03981706224618,-96.96623560041189),
            new LatLng(19.039179707003893,-96.96655578911304),
            new LatLng(19.038990179712012,-96.96588288992645)};
    public static LatLng[] terreno_8 = {
            new LatLng(19.03939015177017,-96.96492332965134),
            new LatLng(19.0388161820579,-96.9652609527111),
            new LatLng(19.038990179712012,-96.96588288992645),
            new LatLng(19.03956541656017,-96.96555733680727)};
    public static LatLng[] terreno_9 = {
            new LatLng(19.03939015177017,-96.96492332965134),
            new LatLng(19.040192945394224,-96.9643734768033),
            new LatLng(19.040047789945493,-96.96356277912855),
            new LatLng(19.03950012807051,-96.96359496563674),
            new LatLng(19.03956034561045,-96.96394968777895),
            new LatLng(19.039294120531896,-96.96410525590181)};
    public static LatLng[] terreno_10 = { new LatLng(19.040192945394224,-96.9643734768033),
            new LatLng(19.03939015177017,-96.96492332965134),
            new LatLng(19.03956541656017,-96.96555733680727),
            new LatLng(19.040430962023464,-96.96504268795252)};
    public static LatLng[] terreno_11 = { new LatLng(19.040192945394224,-96.9643734768033),
            new LatLng(19.03939015177017,-96.96492332965134),
            new LatLng(19.03956541656017,-96.96555733680727),
            new LatLng(19.040430962023464,-96.96504268795252)};
    public static LatLng[] terreno_12 = { new LatLng(19.040730463155956,-96.96578029543161),
            new LatLng(19.040430962023464,-96.96504268795252),
            new LatLng(19.03956541656017,-96.96555733680727),
            new LatLng(19.03981706224618,-96.96623560041189)};
    public static LatLng[] terreno_13 = {
            new LatLng(19.040430962023464,-96.96504268795252),
            new LatLng(19.041339605591237,-96.96460582315922),
            new LatLng(19.041243575480596,-96.96404792368412),
            new LatLng(19.040192945394224,-96.9643734768033)};
    public static LatLng[] terreno_14 = {
            new LatLng(19.040430962023464,-96.96504268795252),
            new LatLng(19.040730463155956,-96.96578029543161),
            new LatLng(19.041532616437678,-96.96537159383298),
            new LatLng(19.041339605591237,-96.96460582315922)};
    public static LatLng[] terreno_15 = {new LatLng(19.040047789945493,-96.96356277912855),
            new LatLng(19.040192945394224,-96.9643734768033),
            new LatLng(19.041243575480596,-96.96404792368412),
            new LatLng(19.041190964901382,-96.96348097175358)};
    public static LatLng[] terreno_16 = { new LatLng(19.041339605591237,-96.96460582315922),
            new LatLng(19.042049846367977,-96.9643624126911),
            new LatLng(19.041933216230298,-96.96337804198265),
            new LatLng(19.04117480140683,-96.96344174444674),
            new LatLng(19.041243575480596,-96.96404792368412)};
    public static LatLng[] terreno_17 = {
            new LatLng(19.042194683136834,-96.96503028273582),
            new LatLng(19.042049846367977,-96.9643624126911),
            new LatLng(19.041339605591237,-96.96460582315922),
            new LatLng(19.041532616437678,-96.96537159383298)};
    public static LatLng[] terreno_18 = {            new LatLng(19.042049846367977,-96.9643624126911),
            new LatLng(19.042194683136834,-96.96503028273582),
            new LatLng(19.04256644082944,-96.9648492336273),
            new LatLng(19.042454248058515,-96.96331534534693),
            new LatLng(19.041933216230298,-96.96337804198265)};

    public static boolean terrenoIsPayment(Terreno terreno, ArrayList<Pago> pagos){
        Log.e("TerrenoUtil","Pagos : "+pagos.size());
        for (Pago pago:pagos){
            Log.e("TerrenoUtil","UI Terreno : "+terreno.uuid + " vs "+ pago.uid_terreno);
            if(pago.uid_terreno.equals(terreno.uuid)){
                return true;
            }
        }
        return false;
    }

    public static Terreno terrenoByShapeID(String id,ArrayList<Terreno> terrenos){
        for (Terreno terreno:terrenos){

            if (id.equals(terreno.id_Polygono)){
                return terreno;
            }
        }
        return null;
    }
}
