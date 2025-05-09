package com.huawei.health.device.ui.measure.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/* loaded from: classes3.dex */
public class NoScrollListView extends ListView {
    public NoScrollListView(Context context) {
        super(context);
    }

    public NoScrollListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NoScrollListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        if (motionEvent.getAction() == 2) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
