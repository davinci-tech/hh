package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.app.Activity;
import android.util.AttributeSet;
import com.huawei.ui.main.R$string;

/* loaded from: classes6.dex */
public abstract class AllClassfiedView extends ObserveredClassifiedView {
    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.ClassifiedView
    public ObserveredClassifiedView onCreateView() {
        return this;
    }

    public AllClassfiedView(Activity activity) {
        super(activity);
    }

    public AllClassfiedView(Activity activity, AttributeSet attributeSet) {
        super(activity, attributeSet);
    }

    public AllClassfiedView(Activity activity, AttributeSet attributeSet, int i) {
        super(activity, attributeSet, i);
    }

    public AllClassfiedView(Activity activity, AttributeSet attributeSet, int i, int i2) {
        super(activity, attributeSet, i, i2);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.ClassifiedView
    public String getClassStr() {
        return getContext().getResources().getString(R$string.IDS_hwh_motiontrack_sport_data_total);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView, com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.ClassifiedView
    public void onSelected() {
        super.onSelected();
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView
    protected JumpTableChart constructJumpTableChart() {
        return new JumpTableBarChart(getContext());
    }
}
