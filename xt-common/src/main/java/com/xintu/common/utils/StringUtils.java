package com.xintu.common.utils;

public class StringUtils extends org.apache.commons.lang3.StringUtils {


    public static boolean isNotBlankOrNull(String cs) {
        return org.apache.commons.lang3.StringUtils.isNotBlank(cs) && !"null".equalsIgnoreCase(cs);
    }
}
