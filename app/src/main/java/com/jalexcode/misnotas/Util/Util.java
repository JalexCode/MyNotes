package com.jalexcode.misnotas.Util;

import android.annotation.SuppressLint;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class Util {
    public static void Util(){}

    public static GregorianCalendar long2Date(long timestamp){
        GregorianCalendar date = new GregorianCalendar();
        date.setTimeInMillis(timestamp);
        return date;
    }
}
