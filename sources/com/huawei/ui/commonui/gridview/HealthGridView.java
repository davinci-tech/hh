package com.huawei.ui.commonui.gridview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/* loaded from: classes6.dex */
public class HealthGridView extends GridView {
    private void a() {
    }

    public HealthGridView(Context context) {
        super(context);
        a();
    }

    public HealthGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
