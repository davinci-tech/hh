package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.app.Activity;
import android.util.AttributeSet;

/* loaded from: classes6.dex */
public abstract class DayBarClassifiedView extends DayClassifiedView {
    public DayBarClassifiedView(Activity activity) {
        super(activity);
    }

    public DayBarClassifiedView(Activity activity, AttributeSet attributeSet) {
        super(activity, attributeSet);
    }

    public DayBarClassifiedView(Activity activity, AttributeSet attributeSet, int i) {
        super(activity, attributeSet, i);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView
    protected JumpTableChart constructJumpTableChart() {
        return new JumpTableBarChart(getContext());
    }
}
