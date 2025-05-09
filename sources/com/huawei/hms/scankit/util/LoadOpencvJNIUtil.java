package com.huawei.hms.scankit.util;

/* loaded from: classes9.dex */
public class LoadOpencvJNIUtil {
    private static final String TAG = "LoadOpencvJNIUtil";

    public static byte[] QRCornerDetect(byte[] bArr, int i, int i2, float[] fArr) {
        if (bArr != null) {
            return OpencvJNI.QRCornerDetect(bArr, i, i2, fArr);
        }
        return null;
    }

    public static float[] QuadFitting(float[] fArr, int i, float[] fArr2) {
        if (fArr2 != null) {
            return OpencvJNI.QuadFitting(fArr, i, fArr2);
        }
        return null;
    }

    public static byte[] adaptivebinary(byte[] bArr, int i, int i2, int i3, boolean z) {
        if (bArr == null || i < i3 || i2 < i3) {
            return null;
        }
        return OpencvJNI.adaptiveBinary(bArr, i, i2, i3, z);
    }

    public static byte[] imageResize(byte[] bArr, int i, int i2, int i3, int i4) {
        if (bArr != null) {
            return OpencvJNI.imageResize(bArr, i, i2, i3, i4);
        }
        return null;
    }

    public static byte[] imageRotate(byte[] bArr, int i, int i2, int i3, int i4, float f, double d) {
        if (bArr != null) {
            return OpencvJNI.imageRotate(bArr, i, i2, i3, i4, f, d);
        }
        return null;
    }

    public static float[] multiBarcodeDetect(byte[] bArr, int i, int i2, int i3, boolean z) {
        if (bArr != null) {
            return OpencvJNI.multiBarcodeDetect(bArr, i, i2, i3, z);
        }
        return null;
    }

    public static byte[] removeMoirePattern(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            return OpencvJNI.removeMoirePattern(bArr, i, i2);
        }
        return null;
    }

    public static void setModel(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3) {
        if (bArr != null) {
            OpencvJNI.setModel(bArr, i, bArr2, i2, bArr3, i3);
        }
    }

    public static byte[] sharpen(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            return OpencvJNI.sharpen(bArr, i, i2);
        }
        return null;
    }
}
