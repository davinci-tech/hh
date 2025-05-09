package com.huawei.phoneservice.feedbackcommon.utils;

import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public class FaqRegexMatches {
    private static final String REGEX_EMAIL = "^[A-Za-z0-9]+([-_.][A-Za-z0-9]+)*@([A-Za-z0-9]+[-.])+[A-Za-z0-9]{2,9}$";
    public static final String REGEX_IP = "(?:(?:(?:25[0-5]|2[0-4]\\d|(?:(?:1\\d{2})|(?:[1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|(?:(?:1\\d{2})|(?:[1-9]?\\d))))";
    private static final String REGEX_PHONE_NUMBER = "^\\d{5,15}$";

    public static boolean isMobile(String str) {
        return Pattern.matches(REGEX_PHONE_NUMBER, str);
    }

    public static boolean isEmail(String str) {
        return Pattern.matches(REGEX_EMAIL, str);
    }

    public static String encryptIp(String str) {
        return Pattern.compile(REGEX_IP).matcher(str).replaceAll("********");
    }
}
