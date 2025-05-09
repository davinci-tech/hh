package com.huawei.watchface;

import android.graphics.Bitmap;
import androidx.core.view.MotionEventCompat;
import com.huawei.watchface.utils.HwLog;
import huawei.android.hwcolorpicker.HwColorPicker;

/* loaded from: classes7.dex */
public class cp {

    /* renamed from: a, reason: collision with root package name */
    private int[] f10973a;
    private int b;
    private int c;
    private int d;
    private int e;
    private float[] f;
    private float[] g;
    private float[] h;
    private float[] i;

    public cp(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        this.b = bitmap.getWidth();
        int height = bitmap.getHeight();
        this.c = height;
        this.f10973a = new int[this.b * height];
        HwLog.i("AreaAveragingUtil", "AreaAveragingUtil() bitmap : { mSrcWidth：" + this.b + ", mSrcHeight:" + this.c + "}");
        int[] iArr = this.f10973a;
        int i = this.b;
        bitmap.getPixels(iArr, 0, i, 0, 0, i, this.c);
    }

    public Bitmap a(int i, int i2) {
        this.d = i;
        this.e = i2;
        int i3 = this.b;
        this.g = new float[i3];
        this.h = new float[i3];
        this.i = new float[i3];
        this.f = new float[i3];
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        a(0, this.b, this.c, createBitmap);
        HwLog.i("AreaAveragingUtil", "getScaledBitmap() bitmap : {width:" + i + ", height:" + i2 + "}");
        return createBitmap;
    }

    private void a(int i, int i2, int i3, Bitmap bitmap) {
        int i4 = this.e;
        int i5 = i;
        int i6 = 0;
        int i7 = 0;
        while (i5 < i + i3) {
            float f = 0.0f;
            if (i6 == 0) {
                for (int i8 = 0; i8 < this.d; i8++) {
                    if (i8 < this.b) {
                        this.f[i8] = 0.0f;
                        this.g[i8] = 0.0f;
                        this.h[i8] = 0.0f;
                        this.i[i8] = 0.0f;
                    }
                }
                i6 = this.c;
            }
            int i9 = i4 < i6 ? i4 : i6;
            int i10 = i2;
            float f2 = 0.0f;
            float f3 = 0.0f;
            int i11 = this.b;
            int i12 = 0;
            int i13 = 0;
            int i14 = 0;
            float f4 = 0.0f;
            while (i12 < i10) {
                if (i13 == 0) {
                    i13 = this.d;
                    f = b(i12, i5);
                    f4 = c(i12, i5);
                    f2 = d(i12, i5);
                    f3 = e(i12, i5);
                    if (f != 255.0f) {
                        float f5 = f / 255.0f;
                        f4 *= f5;
                        f2 *= f5;
                        f3 *= f5;
                    }
                }
                int i15 = i7;
                int i16 = i13 < i11 ? i13 : i11;
                float f6 = i16 * i9;
                float[] fArr = this.f;
                fArr[i14] = fArr[i14] + (f6 * f);
                float[] fArr2 = this.g;
                fArr2[i14] = fArr2[i14] + (f6 * f4);
                float[] fArr3 = this.h;
                fArr3[i14] = fArr3[i14] + (f6 * f2);
                float[] fArr4 = this.i;
                fArr4[i14] = fArr4[i14] + (f6 * f3);
                i13 -= i16;
                if (i13 == 0) {
                    i12++;
                }
                i11 -= i16;
                if (i11 == 0) {
                    i14++;
                    i11 = this.b;
                }
                i10 = i2;
                i7 = i15;
            }
            int i17 = i7;
            i6 -= i9;
            if (i6 == 0) {
                i7 = i17;
                do {
                    a(i7, bitmap);
                    i7++;
                    i4 -= i9;
                    if (i4 < i9) {
                        break;
                    }
                } while (i9 == this.c);
            } else {
                i4 -= i9;
                i7 = i17;
            }
            if (i4 == 0) {
                i4 = this.e;
                i5++;
            }
        }
    }

    private void a(int i, Bitmap bitmap) {
        float f;
        float f2 = this.b * this.c;
        for (int i2 = 0; i2 < this.b; i2++) {
            int round = Math.round(this.f[i2] / f2);
            int i3 = 255;
            if (round <= 0) {
                f = f2;
                round = 0;
            } else if (round >= 255) {
                f = f2;
                round = 255;
            } else {
                f = this.f[i2] / 255.0f;
            }
            int round2 = Math.round(this.g[i2] / f);
            if (round2 < 0) {
                round2 = 0;
            }
            if (round2 > 255) {
                round2 = 255;
            }
            int round3 = Math.round(this.h[i2] / f);
            if (round3 < 0) {
                round3 = 0;
            }
            if (round3 > 255) {
                round3 = 255;
            }
            int round4 = Math.round(this.i[i2] / f);
            if (round4 < 0) {
                round4 = 0;
            }
            if (round4 <= 255) {
                i3 = round4;
            }
            a(bitmap, i2, i, (round << 24) + (round2 << 16) + (round3 << 8) + i3);
        }
    }

    private void a(Bitmap bitmap, int i, int i2, int i3) {
        this.f10973a[(this.b * i2) + i] = i3;
        if (i >= this.d || i2 >= this.e) {
            return;
        }
        bitmap.setPixel(i, i2, i3);
    }

    private int b(int i, int i2) {
        return (this.f10973a[(i2 * this.b) + i] & (-16777216)) >>> 24;
    }

    private int c(int i, int i2) {
        return (this.f10973a[(i2 * this.b) + i] & HwColorPicker.MASK_RESULT_STATE) >>> 16;
    }

    private int d(int i, int i2) {
        return (this.f10973a[(i2 * this.b) + i] & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >>> 8;
    }

    private int e(int i, int i2) {
        return this.f10973a[(i2 * this.b) + i] & 255;
    }
}
