package com.example.mac.oncqupthands.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mac on 2018/5/25.
 */

public class TimeUtil {
    public static String FormatTime(String time1,String time2){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = dateFormat.parse(time1);
            Date d2 = dateFormat.parse(time2);
            long diff = d1.getTime() - d2.getTime();
            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
            long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("还有").append(days).append("天").append(hours).append("小时").append(minutes).append("分").append("消失");
            return stringBuffer.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
