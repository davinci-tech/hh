package com.huawei.hianalytics.dc;

/* loaded from: classes4.dex */
public class StateKeeper {
    public static volatile boolean isInit = false;
    public static volatile boolean isMetricInit = false;

    public static void setMetricInit(boolean z) {
        isMetricInit = z;
    }

    public static void setInit(boolean z) {
        isInit = z;
    }

    public static boolean isMetricInit() {
        return isMetricInit;
    }

    public static boolean isInit() {
        return isInit;
    }
}
