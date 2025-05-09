package com.huawei.ui.commonui.listviewforscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/* loaded from: classes6.dex */
public class ListViewForScroll extends ListView {
    public ListViewForScroll(Context context) {
        super(context);
    }

    public ListViewForScroll(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
