package com.huawei.phoneservice.feedback.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/* loaded from: classes9.dex */
public class FeedbackGridView extends GridView {
    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }

    public FeedbackGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public FeedbackGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FeedbackGridView(Context context) {
        super(context);
    }
}
