package com.huawei.hihealthkit.data.type;

/* loaded from: classes.dex */
public class HiHealthDataType {

    public enum Category {
        POINT,
        SET,
        SESSION,
        SEQUENCE,
        STAT,
        REALTIME,
        USERINFO,
        BUSINESS,
        UNKNOWN
    }

    public static Category a(int i) {
        if (i < 1) {
            return Category.UNKNOWN;
        }
        if (i < 10000) {
            return Category.POINT;
        }
        if (i < 20000) {
            return Category.SET;
        }
        if (i < 30000) {
            return Category.SESSION;
        }
        if (i < 40000) {
            return Category.SEQUENCE;
        }
        if (i < 50000) {
            return Category.POINT;
        }
        if (i < 60000) {
            return Category.REALTIME;
        }
        if (i < 70000) {
            return Category.BUSINESS;
        }
        return Category.UNKNOWN;
    }
}
