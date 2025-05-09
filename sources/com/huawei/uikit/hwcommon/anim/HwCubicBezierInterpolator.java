package com.huawei.uikit.hwcommon.anim;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.animation.Interpolator;
import com.huawei.health.R;

/* loaded from: classes7.dex */
public class HwCubicBezierInterpolator implements Interpolator {
    float b;
    float c;
    float d;
    float e;

    public HwCubicBezierInterpolator(float f, float f2, float f3, float f4) {
        this.b = f;
        this.c = f2;
        this.d = f3;
        this.e = f4;
    }

    private float d(float f) {
        float f2 = 1.0f - f;
        float f3 = 3.0f * f2;
        return (f2 * f3 * f * this.b) + (f3 * f * f * this.d) + (f * f * f);
    }

    private float ebz_(TypedValue typedValue) {
        if (typedValue == null) {
            return 1.0f;
        }
        int i = typedValue.type;
        if (i == 6) {
            return TypedValue.complexToFloat(typedValue.data);
        }
        if (i == 4) {
            return typedValue.getFloat();
        }
        if (i < 16 || i > 31) {
            return 1.0f;
        }
        return typedValue.data;
    }

    protected float c(float f) {
        float f2 = 1.0f - f;
        float f3 = 3.0f * f2;
        return (f2 * f3 * f * this.c) + (f3 * f * f * this.e) + (f * f * f);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        return c(e(f) * 2.5E-4f);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HwCubicBezierInterpolator  mControlPoint1x = ");
        stringBuffer.append(this.b);
        stringBuffer.append(", mControlPoint1y = ").append(this.c);
        stringBuffer.append(", mControlPoint2x = ").append(this.d);
        stringBuffer.append(", mControlPoint2y = ").append(this.e);
        return stringBuffer.toString();
    }

    public HwCubicBezierInterpolator(Context context, AttributeSet attributeSet) {
        this(context.getResources(), context.getTheme(), attributeSet);
    }

    public HwCubicBezierInterpolator(Resources resources, Resources.Theme theme, AttributeSet attributeSet) {
        TypedArray obtainAttributes;
        this.b = 0.0f;
        this.c = 0.0f;
        this.d = 0.0f;
        this.e = 0.0f;
        if (theme != null) {
            obtainAttributes = theme.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100344_res_0x7f0602b8, R.attr._2131100345_res_0x7f0602b9, R.attr._2131100586_res_0x7f0603aa, R.attr._2131100587_res_0x7f0603ab}, 0, 0);
        } else {
            obtainAttributes = resources.obtainAttributes(attributeSet, new int[]{R.attr._2131100344_res_0x7f0602b8, R.attr._2131100345_res_0x7f0602b9, R.attr._2131100586_res_0x7f0603aa, R.attr._2131100587_res_0x7f0603ab});
        }
        this.b = ebz_(obtainAttributes.peekValue(0));
        this.c = ebz_(obtainAttributes.peekValue(1));
        this.d = ebz_(obtainAttributes.peekValue(2));
        this.e = ebz_(obtainAttributes.peekValue(3));
        obtainAttributes.recycle();
    }

    long e(float f) {
        long j = 0;
        long j2 = 4000;
        while (j <= j2) {
            long j3 = (j + j2) >>> 1;
            float d = d(j3 * 2.5E-4f);
            if (d < f) {
                j = j3 + 1;
            } else {
                if (d <= f) {
                    return j3;
                }
                j2 = j3 - 1;
            }
        }
        return j;
    }
}
