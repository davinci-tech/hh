package com.huawei.ui.commonui.functionsetcard.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.ui.commonui.R$styleable;

/* loaded from: classes6.dex */
public class RatioRelativeLayout extends RelativeLayout {
    private int c;
    private int d;

    public RatioRelativeLayout(Context context) {
        this(context, null);
    }

    public RatioRelativeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RatioRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 0;
        this.c = 0;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.RatioRelativeLayout, i, 0);
        this.c = obtainStyledAttributes.getInt(R$styleable.RatioRelativeLayout_width_weight, 0);
        this.d = obtainStyledAttributes.getInt(R$styleable.RatioRelativeLayout_height_weight, 0);
        obtainStyledAttributes.recycle();
    }

    public void setWeight(int i, int i2) {
        this.c = i;
        this.d = i2;
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.d == 0 || this.c == 0) {
            super.onMeasure(i, i2);
            return;
        }
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE && mode2 == Integer.MIN_VALUE) {
            int i3 = this.d;
            int i4 = this.c;
            int i5 = size * i3;
            int i6 = size2 * i4;
            if (i5 >= i6) {
                size2 = i5 / i4;
            } else {
                size = i6 / i3;
            }
        } else if (mode == 1073741824 && mode2 == 1073741824) {
            int i7 = this.d;
            int i8 = this.c;
            int i9 = size * i7;
            int i10 = size2 * i8;
            if (i9 <= i10) {
                size2 = i9 / i8;
            } else {
                size = i10 / i7;
            }
        } else if (mode == 1073741824) {
            size2 = Math.max((this.d * size) / this.c, getMinimumHeight());
        } else if (mode2 == 1073741824) {
            size = Math.max((this.c * size2) / this.d, getMinimumWidth());
        } else {
            size = 0;
            size2 = 0;
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
    }
}
