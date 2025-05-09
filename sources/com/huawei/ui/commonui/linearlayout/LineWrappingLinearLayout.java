package com.huawei.ui.commonui.linearlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/* loaded from: classes6.dex */
public class LineWrappingLinearLayout extends LinearLayout {
    private int d;

    public LineWrappingLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = 0;
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int size = View.MeasureSpec.getSize(i);
        boolean z = false;
        if (size > this.d && a()) {
            setStacked(false);
        }
        this.d = size;
        if (a() || View.MeasureSpec.getMode(i) != 1073741824) {
            i3 = i;
        } else {
            i3 = View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
            z = true;
        }
        super.onMeasure(i3, i2);
        if (!a() && (getMeasuredWidthAndState() & (-16777216)) == 16777216) {
            setStacked(true);
        } else if (!z) {
            return;
        }
        super.onMeasure(i, i2);
    }

    private void setStacked(boolean z) {
        if (z == a()) {
            return;
        }
        setOrientation(z ? 1 : 0);
    }

    public boolean a() {
        return getOrientation() == 1;
    }
}
