package com.xshq.spring.utils;

/* loaded from: classes7.dex */
public class CouStru {
    public static native int getACBodyAge(int i, int i2, double d, double d2, double d3, int i3, boolean z);

    public static native double getACBodyBone(int i, double d);

    public static native double getACBodyBoneMuscle(int i, double d, double d2);

    public static native double getACBodyFat(int i, int i2, double d, double d2, double d3);

    public static native int getACBodyFigure(int i, int i2, double d, double d2, double d3, boolean z);

    public static native int getACBodyHealth(int i, double d);

    public static native double getACBodyHydro(int i, double d, double d2, double d3);

    public static native double getACBodyMuscle(int i, double d);

    public static native double getACBodyProtein(double d, double d2, double d3);

    public static native int getACBodyVisceralFat(int i, int i2, double d, double d2, double d3, boolean z);

    public static native int getBodyBMR(int i, int i2, double d, double d2);

    public static native double getDCBodyFat(int i, int i2, double d, double d2, double d3);

    public static native double getDCBodyHydro(int i, double d);

    static {
        System.loadLibrary("countjni");
    }
}
