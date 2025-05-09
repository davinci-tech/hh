package com.huawei.health.marketing.views.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.ui.commonui.viewpager.HealthViewPager;

/* loaded from: classes8.dex */
public class DialogViewPager extends HealthViewPager {
    public DialogViewPager(Context context) {
        super(context);
    }

    public DialogViewPager(Context context, AttributeSet attributeSet) {
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
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
    }
}
