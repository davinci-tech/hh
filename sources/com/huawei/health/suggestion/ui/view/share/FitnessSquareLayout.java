package com.huawei.health.suggestion.ui.view.share;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/* loaded from: classes8.dex */
public class FitnessSquareLayout extends RelativeLayout {
    public FitnessSquareLayout(Context context) {
        super(context);
    }

    public FitnessSquareLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i) == 1073741824) {
            super.onMeasure(i, i);
        } else {
            super.onMeasure(i, i2);
        }
    }
}
