package com.huawei.pluginachievement.ui.report;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/* loaded from: classes9.dex */
public class CustomGridView extends GridView {
    public CustomGridView(Context context) {
        super(context);
    }

    public CustomGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int i, int i2) {
        if (getLayoutParams().height == -2) {
            i2 = View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
    }
}
