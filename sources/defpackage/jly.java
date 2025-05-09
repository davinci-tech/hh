package defpackage;

import android.graphics.Bitmap;
import androidx.core.view.MotionEventCompat;
import com.huawei.hwlogsmodel.LogUtil;
import huawei.android.hwcolorpicker.HwColorPicker;

/* loaded from: classes5.dex */
public class jly {

    /* renamed from: a, reason: collision with root package name */
    private int f13950a;
    private float[] b;
    private int c;
    private int[] d;
    private float[] e;
    private int f;
    private float[] g;
    private int h;
    private float[] i;

    public jly(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        this.f = bitmap.getWidth();
        int height = bitmap.getHeight();
        this.h = height;
        int i = this.f;
        this.d = new int[height * i];
        LogUtil.a("AreaAveragingUtil", "bitmap : { mSrcWidth：", Integer.valueOf(i), ", mSrcHeight:", Integer.valueOf(this.h), "}");
        int[] iArr = this.d;
        int i2 = this.f;
        bitmap.getPixels(iArr, 0, i2, 0, 0, i2, this.h);
    }

    public Bitmap bIi_(int i, int i2) {
        this.c = i;
        this.f13950a = i2;
        int i3 = this.f;
        this.g = new float[i3];
        this.i = new float[i3];
        this.e = new float[i3];
        this.b = new float[i3];
        if (i <= 0 || i2 <= 0) {
            LogUtil.h("AreaAveragingUtil", "getScaledBitmap, IllegalArgumentException: width and height must be > 0.");
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        bIf_(0, this.f, this.h, createBitmap);
        LogUtil.a("AreaAveragingUtil", "bitmap : {width:", Integer.valueOf(i), ", height:", Integer.valueOf(i2), "}");
        return createBitmap;
    }

    private void bIf_(int i, int i2, int i3, Bitmap bitmap) {
        int i4 = this.f13950a;
        int i5 = i;
        int i6 = 0;
        int i7 = 0;
        while (i5 < i + i3) {
            if (i6 == 0) {
                for (int i8 = 0; i8 < this.c; i8++) {
                    this.b[i8] = 0.0f;
                    this.g[i8] = 0.0f;
                    this.i[i8] = 0.0f;
                    this.e[i8] = 0.0f;
                }
                i6 = this.h;
            }
            int i9 = i4 < i6 ? i4 : i6;
            b(i2, i5, i9);
            i6 -= i9;
            if (i6 == 0) {
                do {
                    bIg_(i7, bitmap);
                    i7++;
                    i4 -= i9;
                    if (i4 < i9) {
                        break;
                    }
                } while (i9 == this.h);
            } else {
                i4 -= i9;
            }
            if (i4 == 0) {
                i4 = this.f13950a;
                i5++;
            }
        }
    }

    private void b(int i, int i2, int i3) {
        int i4 = this.f;
        int i5 = 0;
        int i6 = 0;
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        int i7 = 0;
        while (i5 < i) {
            if (i7 == 0) {
                i7 = this.c;
                f = a(i5, i2);
                f2 = d(i5, i2);
                f3 = e(i5, i2);
                f4 = c(i5, i2);
                if (f != 255.0f) {
                    float f5 = f / 255.0f;
                    f2 *= f5;
                    f3 *= f5;
                    f4 *= f5;
                }
            }
            int i8 = i7 < i4 ? i7 : i4;
            float f6 = i8 * i3;
            float[] fArr = this.b;
            fArr[i6] = fArr[i6] + (f6 * f);
            float[] fArr2 = this.g;
            fArr2[i6] = fArr2[i6] + (f6 * f2);
            float[] fArr3 = this.i;
            fArr3[i6] = fArr3[i6] + (f6 * f3);
            float[] fArr4 = this.e;
            fArr4[i6] = fArr4[i6] + (f6 * f4);
            i7 -= i8;
            if (i7 == 0) {
                i5++;
            }
            i4 -= i8;
            if (i4 == 0) {
                i6++;
                i4 = this.f;
            }
        }
    }

    private void bIg_(int i, Bitmap bitmap) {
        float f;
        float f2 = this.f * this.h;
        for (int i2 = 0; i2 < this.f; i2++) {
            int round = Math.round(this.b[i2] / f2);
            int i3 = 255;
            if (round <= 0) {
                f = f2;
                round = 0;
            } else if (round >= 255) {
                f = f2;
                round = 255;
            } else {
                f = this.b[i2] / 255.0f;
            }
            int round2 = Math.round(this.g[i2] / f);
            if (round2 < 0) {
                round2 = 0;
            }
            if (round2 > 255) {
                round2 = 255;
            }
            int round3 = Math.round(this.i[i2] / f);
            if (round3 < 0) {
                round3 = 0;
            }
            if (round3 > 255) {
                round3 = 255;
            }
            int round4 = Math.round(this.e[i2] / f);
            if (round4 < 0) {
                round4 = 0;
            }
            if (round4 <= 255) {
                i3 = round4;
            }
            bIh_(bitmap, i2, i, (round << 24) + (round2 << 16) + (round3 << 8) + i3);
        }
    }

    private void bIh_(Bitmap bitmap, int i, int i2, int i3) {
        this.d[(this.f * i2) + i] = i3;
        if (i >= this.c || i2 >= this.f13950a) {
            return;
        }
        bitmap.setPixel(i, i2, i3);
    }

    private int a(int i, int i2) {
        return (this.d[(i2 * this.f) + i] & (-16777216)) >>> 24;
    }

    private int d(int i, int i2) {
        return (this.d[(i2 * this.f) + i] & HwColorPicker.MASK_RESULT_STATE) >>> 16;
    }

    private int e(int i, int i2) {
        return (this.d[(i2 * this.f) + i] & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >>> 8;
    }

    private int c(int i, int i2) {
        return this.d[(i2 * this.f) + i] & 255;
    }
}
