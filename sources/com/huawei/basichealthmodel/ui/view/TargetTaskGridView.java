package com.huawei.basichealthmodel.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/* loaded from: classes8.dex */
public class TargetTaskGridView extends GridView {
    public TargetTaskGridView(Context context) {
        super(context);
    }

    public TargetTaskGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TargetTaskGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
