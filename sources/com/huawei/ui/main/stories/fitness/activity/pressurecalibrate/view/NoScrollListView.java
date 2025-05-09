package com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import defpackage.ptt;

/* loaded from: classes6.dex */
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

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    public void onMeasure(int i, int i2) {
        ptt.d().h(true);
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }

    @Override // android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ptt.d().h(false);
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if ((motionEvent.getActionMasked() & 255) == 2) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
