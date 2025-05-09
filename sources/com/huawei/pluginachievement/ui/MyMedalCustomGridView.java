package com.huawei.pluginachievement.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/* loaded from: classes9.dex */
public class MyMedalCustomGridView extends GridView {
    public MyMedalCustomGridView(Context context) {
        super(context);
    }

    public MyMedalCustomGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyMedalCustomGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
