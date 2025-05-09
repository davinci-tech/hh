package com.huawei.ui.openservice.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/* loaded from: classes7.dex */
public class ScrollGridView extends GridView {
    private static final int DEFAULT_SIZE = 2;

    public ScrollGridView(Context context) {
        super(context);
    }

    public ScrollGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ScrollGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
