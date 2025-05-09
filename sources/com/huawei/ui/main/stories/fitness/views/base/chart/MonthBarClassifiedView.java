package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.app.Activity;
import android.util.AttributeSet;

/* loaded from: classes6.dex */
public abstract class MonthBarClassifiedView extends MonthClassifiedView {
    public MonthBarClassifiedView(Activity activity) {
        super(activity);
    }

    public MonthBarClassifiedView(Activity activity, AttributeSet attributeSet) {
        super(activity, attributeSet);
    }

    public MonthBarClassifiedView(Activity activity, AttributeSet attributeSet, int i) {
        super(activity, attributeSet, i);
    }

    public MonthBarClassifiedView(Activity activity, AttributeSet attributeSet, int i, int i2) {
        super(activity, attributeSet, i, i2);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView
    protected JumpTableChart constructJumpTableChart() {
        return new JumpTableBarChart(getContext());
    }
}
