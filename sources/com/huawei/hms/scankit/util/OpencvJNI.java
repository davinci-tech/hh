package com.huawei.hms.scankit.util;

import com.huawei.hms.scankit.p.m1;
import com.huawei.hms.scankit.p.o4;
import com.huawei.hms.scankit.p.r3;

/* loaded from: classes9.dex */
public class OpencvJNI {
    public static native byte[] QRCornerDetect(byte[] bArr, int i, int i2, float[] fArr);

    public static native float[] QuadFitting(float[] fArr, int i, float[] fArr2);

    public static native byte[] adaptiveBinary(byte[] bArr, int i, int i2, int i3, boolean z);

    public static native byte[] imageResize(byte[] bArr, int i, int i2, int i3, int i4);

    public static native byte[] imageRotate(byte[] bArr, int i, int i2, int i3, int i4, float f, double d);

    public static void init() {
        o4.d("OpencvJNI", "init()");
        o4.d("OpencvJNI", "start load method");
        try {
            System.loadLibrary("scannative");
            r3.A = true;
        } catch (UnsatisfiedLinkError e) {
            o4.b("OpencvJNI", e.getMessage());
            m1.a(false);
        }
    }

    public static native float[] multiBarcodeDetect(byte[] bArr, int i, int i2, int i3, boolean z);

    public static native byte[] removeMoirePattern(byte[] bArr, int i, int i2);

    public static native void setModel(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3);

    public static native byte[] sharpen(byte[] bArr, int i, int i2);
}
