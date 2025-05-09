package com.huawei.openalliance.ad.views.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes5.dex */
public class WrapContentHeightGalleryView extends e {
    @Override // com.huawei.openalliance.ad.views.viewpager.e, android.view.View
    protected void onMeasure(int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            View childAt = getChildAt(i4);
            childAt.measure(i, View.MeasureSpec.makeMeasureSpec(0, 0));
            i3 = Math.max(childAt.getMeasuredHeight(), i3);
        }
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
    }

    public WrapContentHeightGalleryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public WrapContentHeightGalleryView(Context context) {
        super(context);
    }
}
