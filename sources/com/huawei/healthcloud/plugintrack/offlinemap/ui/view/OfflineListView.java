package com.huawei.healthcloud.plugintrack.offlinemap.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/* loaded from: classes4.dex */
public class OfflineListView extends ListView {
    public OfflineListView(Context context) {
        super(context);
    }

    public OfflineListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public OfflineListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
