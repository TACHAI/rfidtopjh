package com.laishishui.rfidtopjh.util;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @class: DateUtils
 * @Author: darren
 * @CreateDate: 2019-06-13 9:53
 * @Version 1.0
 */
public class DateUtil {

    public static final String DATENAME_FORMAT="yyyyMMddHHmmsss";
    /**这种方法会导致线程不安全*/
    public static final String DEFAULT_FORMAT="yyyy-MM-dd HH:mm:ss";

    /**
     * 锁对象
     */
    private static final Object LOCKOBJECT = new Object();

    /**
     * 存放不同的日期模板格式的sdf的Map
     * @return
     */
    private static Map<String,ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<>();

    private static SimpleDateFormat getSdf(final String pattern){
        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);

        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if(tl==null){
            synchronized (LOCKOBJECT){
                tl = sdfMap.get(pattern);
                if(tl==null){
                    // 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
                    System.out.println("put new sdf of pattern"+pattern+"to map");

                    //这里是关键，使用ThreadLocal<SimpleDateFormat>替代原来直接new Simple
                    tl = new ThreadLocal<SimpleDateFormat> () {
                        @Override
                        protected SimpleDateFormat initialValue() {
                          return new SimpleDateFormat(pattern);
                      }
                    };
                    sdfMap.put(pattern,tl);
                }
            }
        }
        return tl.get();
    }


    public static Date getNowDate() {

        Date currentTime = new Date();

        String dateString = getSdf(DATENAME_FORMAT).format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date res = getSdf(DATENAME_FORMAT).parse(dateString, pos);
        return res;
    }
    // 获得上月的日期
    public static Date getPreMouthDay(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);
        int year3 = cal.get(Calendar.YEAR);
        int month3 = cal.get(Calendar.MONTH)+1;
        int day3 = cal.get(Calendar.DAY_OF_MONTH);
        Date date3 = cal.getTime();
       return date3;
    }


    public static String dateToStr(Date date,String formStr){
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formStr);
    }

    public static Date strToDate(String date,String format)  {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            return formatter.parse(date);
        }catch (ParseException e) {

        }
         return null;
    }


    /**
     * @param endTime
     * @param startTime
     * @return  返回两个时间差  单位是分钟
     */
    //获得两个时间差
    public static int getMinutes(Date endTime,Date startTime){
        int diff=(int)Math.ceil((endTime.getTime()-startTime.getTime())/1000/60);
        return  diff;
    }

    public static int getSeconds(Date endTime,Date startTime){
        int diff=(int)Math.ceil((endTime.getTime()-startTime.getTime())/1000);
        return  diff;
    }


    public static void main(String[] args) throws ParseException {
        Date now =  new Date();

        Date start =  new Date((now.getTime() -1000000));
        //System.out.println(getMinutes(now,start));
         LocalDateTime noew   = LocalDateTime.now();
        System.out.println(noew);
    }


}
