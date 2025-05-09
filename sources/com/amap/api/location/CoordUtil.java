package com.amap.api.location;

/* loaded from: classes8.dex */
public class CoordUtil {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f1396a = false;

    public static native int convertToGcj(double[] dArr, double[] dArr2);

    public static boolean isLoadedSo() {
        return f1396a;
    }

    public static void setLoadedSo(boolean z) {
        f1396a = z;
    }
}
