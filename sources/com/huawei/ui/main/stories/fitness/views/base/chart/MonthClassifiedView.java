package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.app.Activity;
import android.util.AttributeSet;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.interactors.ClassifiedViewsBIMarker;

/* loaded from: classes6.dex */
public abstract class MonthClassifiedView extends ObserveredClassifiedView {
    private Activity mActivity;

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.ClassifiedView
    public ObserveredClassifiedView onCreateView() {
        return this;
    }

    public MonthClassifiedView(Activity activity) {
        super(activity);
        this.mActivity = activity;
    }

    public MonthClassifiedView(Activity activity, AttributeSet attributeSet) {
        super(activity, attributeSet);
        this.mActivity = activity;
    }

    public MonthClassifiedView(Activity activity, AttributeSet attributeSet, int i) {
        super(activity, attributeSet, i);
        this.mActivity = activity;
    }

    public MonthClassifiedView(Activity activity, AttributeSet attributeSet, int i, int i2) {
        super(activity, attributeSet, i, i2);
        this.mActivity = activity;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.ClassifiedView
    public String getClassStr() {
        return getContext().getResources().getString(R$string.IDS_fitness_detail_radio_button_tab_month);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView, com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.ClassifiedView
    public void onSelected() {
        super.onSelected();
        ClassifiedViewsBIMarker.duw_(this.mActivity, ClassifiedViewsBIMarker.Type.MonthView);
    }
}
