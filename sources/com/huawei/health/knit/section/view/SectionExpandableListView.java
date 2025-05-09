package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListView;

/* loaded from: classes3.dex */
public class SectionExpandableListView extends ExpandableListView {
    public SectionExpandableListView(Context context) {
        super(context);
    }

    public SectionExpandableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SectionExpandableListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
