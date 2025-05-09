package com.huawei.health.trusport;

/* loaded from: classes4.dex */
public class Coach {
    public static native int[] runningPerformancePrediction(double d);

    static {
        System.loadLibrary("racepredict");
    }
}
