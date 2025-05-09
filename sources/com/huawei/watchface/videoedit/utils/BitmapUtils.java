package com.huawei.watchface.videoedit.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.graphics.Matrix;
import android.graphics.Paint;
import java.math.BigDecimal;
import java.math.RoundingMode;

/* loaded from: classes9.dex */
public final class BitmapUtils {
    private static final int CONST_EIGHT = 8;

    private BitmapUtils() {
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int i, boolean z) {
        if (i == 0 || bitmap == null) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        if (z && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    private static ColorSpace getColorSpace(Bitmap bitmap) {
        ColorSpace colorSpace = bitmap.getColorSpace();
        return colorSpace == null ? ColorSpace.get(ColorSpace.Named.SRGB) : colorSpace;
    }

    private static Bitmap.Config getConfig(Bitmap bitmap) {
        Bitmap.Config config = bitmap.getConfig();
        return config == null ? Bitmap.Config.ARGB_8888 : config;
    }

    private static Bitmap resizeBitmapByScale(Bitmap bitmap, float f, boolean z) {
        int round = Math.round(bitmap.getWidth() * f);
        int round2 = Math.round(bitmap.getHeight() * f);
        if (round == bitmap.getWidth() && round2 == bitmap.getHeight()) {
            return bitmap;
        }
        Bitmap createBitmap = Bitmap.createBitmap(round, round2, getConfig(bitmap), true, getColorSpace(bitmap));
        Canvas canvas = new Canvas(createBitmap);
        canvas.scale(f, f);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint(6));
        if (z) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static int computeSampleSizeLarger(float f) {
        int i;
        try {
            i = BigDecimal.valueOf(1.0d).divide(BigDecimal.valueOf(f), RoundingMode.FLOOR).intValue();
        } catch (ArithmeticException unused) {
            i = 0;
        }
        if (i <= 1) {
            return 1;
        }
        return i <= 8 ? Utils.prevPowerOf2(i) : (i / 8) * 8;
    }

    public static float getFullScreenNailScale(float f, float f2, float f3, float f4) {
        return Utils.getMax(Utils.getMin(f3 / f, f4 / f2), Utils.getMin(f3 / f2, f4 / f));
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0086 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap loadImage(java.lang.String r7, float r8) {
        /*
            Method dump skipped, instructions count: 246
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.videoedit.utils.BitmapUtils.loadImage(java.lang.String, float):android.graphics.Bitmap");
    }
}
