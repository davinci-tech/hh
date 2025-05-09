package com.huawei.watchface.utils;

import android.graphics.Bitmap;
import android.graphics.Rect;

/* loaded from: classes9.dex */
public class ColorPickerUtil {
    public static native int processNewBitmap(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7);

    static {
        System.loadLibrary("colorPicker-lib");
    }

    public static int a(Bitmap bitmap, Rect rect) {
        if (bitmap == null || rect == null) {
            return -1;
        }
        HwLog.i("ColorPickerUtil", "Rect left:" + rect.left + "Rect top:" + rect.top + "Rect right:" + rect.right + "Rect bottom:" + rect.bottom);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int rowBytes = bitmap.getRowBytes();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        byte[] a2 = a(iArr);
        if (ArrayUtils.a(a2)) {
            return -1;
        }
        int processNewBitmap = processNewBitmap(a2, width, height, rowBytes * height, rect.left, rect.top, rect.width(), rect.height());
        HwLog.i("ColorPickerUtil", "processNewBitmap hex==" + CommonUtils.a(processNewBitmap));
        return processNewBitmap;
    }

    public static byte[] a(int[] iArr) {
        if (iArr == null) {
            return new byte[0];
        }
        byte[] bArr = new byte[iArr.length * 4];
        for (int i = 0; i < iArr.length; i++) {
            int i2 = i * 4;
            int i3 = iArr[i];
            bArr[i2] = (byte) (i3 & 255);
            bArr[i2 + 1] = (byte) ((i3 >> 8) & 255);
            bArr[i2 + 2] = (byte) ((i3 >> 16) & 255);
            bArr[i2 + 3] = (byte) ((i3 >> 24) & 255);
        }
        return bArr;
    }
}
