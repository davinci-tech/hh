package com.huawei.ui.commonui.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes9.dex */
public class MaxHeightViewPager extends HealthViewPager {
    public MaxHeightViewPager(Context context) {
        super(context);
    }

    public MaxHeightViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.huawei.ui.commonui.viewpager.HealthViewPager, com.huawei.uikit.hwviewpager.widget.HwViewPager, android.view.View
    public void onMeasure(int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            View childAt = getChildAt(i4);
            childAt.measure(i, View.MeasureSpec.makeMeasureSpec(0, 0));
            int measuredHeight = childAt.getMeasuredHeight();
            if (measuredHeight > i3) {
                i3 = measuredHeight;
            }
        }
        int size = View.MeasureSpec.getSize(i2);
        if (size > i3) {
            i3 = size;
        }
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
    }
}
