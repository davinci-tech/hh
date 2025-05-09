package com.huawei.up.utils;

/* loaded from: classes7.dex */
public class NSPException extends Exception {
    public static final int EXP_NET_ERROR = 6;
    public static final String EXP_NET_ERROR_STR = "Service unavailable.6";
    public static final int EXP_SERVICE_UNAVAILABLE = 2;
    public static final int EXP_UNKNOWN_ERROR = 1;
    private int exceptionCode;

    public NSPException(int i, String str) {
        super(i + " " + str);
        this.exceptionCode = i;
    }

    public NSPException(int i, String str, Exception exc) {
        super(i + " " + str, exc);
        this.exceptionCode = i;
    }

    public int getCode() {
        return this.exceptionCode;
    }
}
