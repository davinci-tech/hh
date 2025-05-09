package com.huawei.health.device.ui.measure.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/* loaded from: classes8.dex */
public class ScrollViewForListView extends ListView {
    public ScrollViewForListView(Context context) {
        super(context);
    }

    public ScrollViewForListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ScrollViewForListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
