package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.app.Activity;
import android.util.AttributeSet;

/* loaded from: classes6.dex */
public abstract class DayLineHorizontalClassifiedView extends DayClassifiedView {
    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView
    protected boolean isHorizontal() {
        return true;
    }

    public DayLineHorizontalClassifiedView(Activity activity) {
        super(activity);
    }

    public DayLineHorizontalClassifiedView(Activity activity, AttributeSet attributeSet) {
        super(activity, attributeSet);
    }

    public DayLineHorizontalClassifiedView(Activity activity, AttributeSet attributeSet, int i) {
        super(activity, attributeSet, i);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView
    protected JumpTableChart constructJumpTableChart() {
        return new HorizontalJumpTableChart(getContext());
    }
}
