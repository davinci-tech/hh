package com.huawei.ui.homehealth.runcard.operation.operationpositions;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/* loaded from: classes9.dex */
public class ExpandListViewInScrollView extends ListView {
    public ExpandListViewInScrollView(Context context) {
        super(context);
    }

    public ExpandListViewInScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ExpandListViewInScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
