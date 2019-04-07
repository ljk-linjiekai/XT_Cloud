package com.xintu.common.utils;

import java.text.SimpleDateFormat;

public class NumberId {
    /**
     * 20位末尾的数字id
     */
    public static Long Guid = 100L;

    public static Long getNumberId() {
        NumberId.Guid += 1;
        long now = System.currentTimeMillis();
        //获取4位年份数字
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        //获取时间戳
        String time = dateFormat.format(now);
        String info = now + "";
        //获取三位随机数
        //int ran=(int) ((Math.random()*9+1)*100);
        //要是一段时间内的数据连过大会有重复的情况，所以做以下修改
        Long ran = 0L;
        if (NumberId.Guid > 999L) {
            NumberId.Guid = 100L;
        }
        ran = NumberId.Guid;
        String s =   time+ info.substring(2, info.length()) + ran;
        return Long.valueOf(s) ;

    }
}
