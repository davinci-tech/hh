package com.huawei.hwstressmgr;

import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class PressureCalibrate {

    /* renamed from: a, reason: collision with root package name */
    private static PressureCalibrate f6410a;

    public native float[] libPressureCalibrate(float[] fArr, int i, int i2, int[] iArr, int[] iArr2);

    static {
        try {
            System.loadLibrary("JanusStressJni");
            LogUtil.a("PressureCalibrate", "load .so success");
        } catch (UnsatisfiedLinkError e) {
            LogUtil.b("PressureCalibrate", "load .so fail" + e.getMessage());
        }
    }

    private PressureCalibrate() {
    }

    public static PressureCalibrate c() {
        PressureCalibrate pressureCalibrate;
        synchronized (PressureCalibrate.class) {
            if (f6410a == null) {
                f6410a = new PressureCalibrate();
            }
            pressureCalibrate = f6410a;
        }
        return pressureCalibrate;
    }
}
