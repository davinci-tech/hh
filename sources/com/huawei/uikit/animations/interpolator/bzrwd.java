package com.huawei.uikit.animations.interpolator;

import android.view.animation.Interpolator;

/* loaded from: classes7.dex */
public abstract class bzrwd implements Interpolator {
    private static final int c = 2;

    /* renamed from: a, reason: collision with root package name */
    private final float[] f10586a;
    private final float b;

    public bzrwd(float[] fArr) {
        this.f10586a = fArr;
        if (fArr.length > 1) {
            this.b = 1.0f / (fArr.length - 1);
        } else {
            this.b = 0.0f;
        }
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        if (Float.compare(f, 1.0f) >= 0) {
            return 1.0f;
        }
        if (Float.compare(f, 0.0f) <= 0) {
            return 0.0f;
        }
        float[] fArr = this.f10586a;
        if (fArr.length <= 1) {
            if (fArr.length > 0) {
                return fArr[0];
            }
            return 0.0f;
        }
        int length = (int) ((fArr.length - 1) * f);
        int length2 = fArr.length - 2;
        if (length >= length2) {
            length = length2;
        }
        float f2 = length;
        float f3 = this.b;
        float f4 = Float.compare(f3, 0.0f) != 0 ? (f - (f2 * f3)) / this.b : 0.0f;
        float[] fArr2 = this.f10586a;
        float f5 = fArr2[length];
        return f5 + (f4 * (fArr2[length + 1] - f5));
    }
}
