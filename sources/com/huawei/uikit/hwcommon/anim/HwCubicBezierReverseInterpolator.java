package com.huawei.uikit.hwcommon.anim;

import android.content.Context;
import android.util.AttributeSet;

/* loaded from: classes9.dex */
public class HwCubicBezierReverseInterpolator extends HwCubicBezierInterpolator {
    @Override // com.huawei.uikit.hwcommon.anim.HwCubicBezierInterpolator, android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        return 1.0f - c(e(1.0f - f) * 2.5E-4f);
    }

    public HwCubicBezierReverseInterpolator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
