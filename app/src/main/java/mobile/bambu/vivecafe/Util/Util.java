package mobile.bambu.vivecafe.Util;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import mobile.bambu.vivecafe.Models.Finca;
import mobile.bambu.vivecafe.R;

/**
 * Created by Bambu on 03/11/2016.
 */

public class Util {
    public static String notNullValueString(String value){
        if (value == null){
            return  "";
        }
        return value;
    }
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public final static boolean isValidPasword(CharSequence target) {
        if (target == null || target.length()<=6) {
            return false;
        } else {
            return true;
        }
    }
    public final static boolean isValidData(CharSequence target) {
        if (target == null || target.length()<=0) {
            return false;
        } else {
            return true;
        }
    }
    public final static boolean isValidData(EditText target) {
        if (target.getText().toString() == null || target.getText().toString().length()<=0) {
            return false;
        } else {
            return true;
        }
    }
    public static String getHoraString(Date date){
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
        return calendar.get(Calendar.HOUR_OF_DAY) + " : " +calendar.get(Calendar.MINUTE);
    }

    public static long getTimeStamp(String oldTime) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        formatter.setLenient(false);
        //   String oldTime = "2016-11-11,12:00:00";
        Date oldDate = new Date();
        oldDate = formatter.parse(oldTime);
        long oldMillis = oldDate.getTime();
        return oldMillis;
    }


    public static Date strintToDate(String incomedate){

        DateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Date date = new Date();
        try {
            date = df.parse(incomedate);
        }catch (Exception e){
            Log.e("Util","strintToDate: "+"No se puede parcear la cadena del Dia");
        }
        return date;
    }

    public static int getImageFinca( Finca finca){
        int resourse = R.drawable.imagen_ixhuatlan;
        switch (finca.getNombre()){
            case "IXHUATLAN":
                resourse =  R.drawable.imagen_ixhuatlan;
                break;
            case "PLUMA H":
                resourse =  R.drawable.imagen_pluma;
                break;
            case "SAN CRISTOBAL":
                resourse =  R.drawable.imagen_sancristobal;
                break;
            default:
                resourse = R.drawable.imagen_ixhuatlan;
                break;
        }
        return  resourse;
    }
}
