package com.lihuanyu.informationquerysystem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by skyADMIN on 16/4/8.
 */
public class SchoolDateUtil {
    public long getTime(){
        Date currentTime = new Date();
        long current = currentTime.getTime();
        long time = 0;
        long begin = 0;
        try {
            SimpleDateFormat beginTime = new SimpleDateFormat("yyyy-MM-dd");
            begin = beginTime.parse("2016-02-28").getTime();
            time = current- begin;
        }catch (ParseException e){
            e.printStackTrace();
        }
        return time;
    }

    public int getWeek(){
        int week =0;
        double days = Math.ceil(getTime()/86400000);
        week = (int) Math.ceil(days/7);//第几周
        return week;
    }

    public int getDay(){
        int day =0;
        double days = Math.ceil(getTime()/86400000);
        switch ((int)Math.ceil(days)%7){
            case 0: day = 7;break;
            case 1: day = 1;break;
            case 2: day = 2;break;
            case 3: day = 3;break;
            case 4: day = 4;break;
            case 5: day = 5;break;
            case 6: day = 6;break;
        }//得到当天是星期几
        return day;
    }
}