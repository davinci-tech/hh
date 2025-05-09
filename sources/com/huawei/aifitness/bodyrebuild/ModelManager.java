package com.huawei.aifitness.bodyrebuild;

import android.util.Log;

/* loaded from: classes2.dex */
public class ModelManager {
    public static native short AlgCloseAR();

    public static native short AlgDataCollect(float[] fArr, int i, short s);

    public static native int AlgGetResultAR();

    public static native short AlgInitAR();

    static {
        try {
            System.loadLibrary("neck");
        } catch (UnsatisfiedLinkError e) {
            Log.e("CameraFeature", "failed to load native library: " + e.getMessage());
        }
    }
}
