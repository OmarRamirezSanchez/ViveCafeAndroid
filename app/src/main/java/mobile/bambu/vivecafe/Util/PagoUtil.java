package mobile.bambu.vivecafe.Util;

import android.util.Log;

import java.util.ArrayList;

import mobile.bambu.vivecafe.Models.Pago;

/**
 * Created by Bambu on 15/11/2016.
 */

public class PagoUtil {
    public static void updatePagoById(Pago pagoNew, ArrayList<Pago> pagos){

        try {
            for( Pago pago : pagos){
                if (pagoNew.uuid == pago.uuid){
                    pago.updateByPago(pagoNew);
                }
            }
        }catch (Exception e){
            Log.e("ExpenseUtil","updateExpenseById"+e.toString());
        }
    }
    public static void deletPagoByUUID(Pago pagoNew,ArrayList<Pago> pagos) {

        for(Pago aux_pago:pagos){
            if(aux_pago.uuid.equals(pagoNew.uuid)){
                pagos.remove(aux_pago);
                break;
            }
        }
    }
}
