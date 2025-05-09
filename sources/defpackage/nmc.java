package defpackage;

import android.graphics.RectF;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;

/* loaded from: classes6.dex */
public class nmc {
    private float[] b;
    private int c;
    private int d;
    private int g = 40;
    private int h = HeartRateThresholdConfig.HEART_RATE_LIMIT;

    /* renamed from: a, reason: collision with root package name */
    private int f15382a = 0;
    private int e = 0;
    private int j = 0;

    public nmc(int i, int i2) {
        this.d = i2;
        this.b = new float[i2];
        this.c = i;
    }

    public void a(int i, int i2) {
        this.g = i;
        this.h = i2;
    }

    public void a(float f) {
        float[] fArr = this.b;
        int i = this.e;
        fArr[i] = f;
        int i2 = i + 1;
        this.e = i2;
        int i3 = this.d;
        int i4 = i2 % i3;
        this.e = i4;
        int i5 = this.f15382a;
        int i6 = i4 < i5 ? (i4 + i3) - i5 : i4 - i5;
        int i7 = this.c;
        if (i6 > i7) {
            int i8 = i5 + 1;
            this.f15382a = i8;
            int i9 = i8 % i3;
            this.f15382a = i9;
            if (((i4 + i3) - this.j) % i3 > i7) {
                this.j = i9;
            }
        }
    }

    public float[] b() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = this.e;
        int i6 = this.j;
        if (i5 == i6) {
            i = 0;
        } else {
            if (i5 < i6) {
                i5 += this.d;
            }
            i = (i5 - i6) - 1;
        }
        float[] fArr = new float[i * 4];
        int i7 = 0;
        while (true) {
            int i8 = this.e;
            if (i6 == i8 || (i4 = (i2 = i6 + 1) % (i3 = this.d)) == i8) {
                break;
            }
            fArr[i7] = i6;
            float[] fArr2 = this.b;
            fArr[i7 + 1] = fArr2[i6];
            fArr[i7 + 2] = i2;
            if (i2 == i3) {
                fArr[i7 + 3] = fArr2[0];
            } else {
                fArr[i7 + 3] = fArr2[i2];
            }
            i7 += 4;
            i6 = i4;
        }
        return fArr;
    }

    public RectF[] cAg_() {
        int i = this.e;
        int i2 = this.f15382a;
        if (i == i2) {
            return new RectF[0];
        }
        if (i > i2) {
            return new RectF[]{new RectF(this.f15382a, this.h, this.e - 1, this.g)};
        }
        if (i == 0) {
            return new RectF[]{new RectF(this.f15382a, this.h, this.d - 1, this.g)};
        }
        if (i == 1) {
            return new RectF[]{new RectF(this.f15382a, this.h, this.d, this.g)};
        }
        return new RectF[]{new RectF(this.f15382a, this.h, this.d, this.g), new RectF(0.0f, this.h, this.e - 1, this.g)};
    }

    public void a() {
        int i = this.e;
        if (i == this.f15382a) {
            this.j = i;
        } else {
            this.j = ((i + r1) - 1) % this.d;
        }
    }
}
