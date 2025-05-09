package com.huawei.ui.commonui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/* loaded from: classes6.dex */
public class ShareSquareLayout extends RelativeLayout {
    public ShareSquareLayout(Context context) {
        super(context);
    }

    public ShareSquareLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i) == 1073741824) {
            i2 = i;
        }
        super.onMeasure(i, i2);
    }
}
