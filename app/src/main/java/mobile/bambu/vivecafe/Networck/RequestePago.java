package mobile.bambu.vivecafe.Networck;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

import mobile.bambu.vivecafe.Interfaces.Constans;
import mobile.bambu.vivecafe.Models.Pago;
import mobile.bambu.vivecafe.Models.User;

/**
 * Created by Bambu on 11/11/2016.
 */

public class RequestePago implements Constans {

    public static void writeNewPago(Pago pago, FirebaseDatabase database ) {
        DatabaseReference db_reference = database.getReference(fb_pagos);
        String key = db_reference.push().getKey();
        pago.uuid = key;
        db_reference.child(key).setValue(pago);
    }
}
