package com.huawei.treadmill;

/* loaded from: classes6.dex */
public class JniTest {
    public native int getCurrentStepSource();

    public native int initAlg(int[] iArr);

    public native int[] processAlg(int[] iArr, int[] iArr2);

    public native void stopAlg();

    static {
        System.loadLibrary("native-lib");
    }
}
