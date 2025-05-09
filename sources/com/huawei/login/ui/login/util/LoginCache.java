package com.huawei.login.ui.login.util;

/* loaded from: classes.dex */
public class LoginCache {
    private static volatile String sAccessToken;
    private static volatile String sCountryCode;
    private static volatile String sDeviceType;
    private static volatile String sServerToken;
    private static volatile String sSrvCountryCode;

    private LoginCache() {
    }

    public static void configServerToken(String str) {
        sServerToken = str;
    }

    public static String fetchServerToken() {
        return sServerToken;
    }

    public static void configAccessToken(String str) {
        sAccessToken = str;
    }

    public static String fetchAccessToken() {
        return sAccessToken;
    }

    public static void configDeviceType(String str) {
        sDeviceType = str;
    }

    public static String fetchDeviceType() {
        return sDeviceType;
    }

    public static void configCountryCode(String str) {
        sCountryCode = str;
    }

    public static String getCountryCode() {
        return sCountryCode;
    }

    public static void configSrvCountryCode(String str) {
        sSrvCountryCode = str;
    }

    public static String getSrvCountryCode() {
        return sSrvCountryCode;
    }
}
