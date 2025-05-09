package com.huawei.ui.main.stories.fitness.views.heartrate;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.fitness.views.base.chart.IScrollChartOuterObserver;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IFocusObserverItem;

/* loaded from: classes6.dex */
public abstract class ScrollChartHorizontalObserverHRView extends LinearLayout implements IScrollChartOuterObserver {
    private View mDivider;
    protected ObserveredClassifiedView mHost;
    private HealthTextView mTitle;

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.IScrollChartOuterObserver
    public IFocusObserverItem acquireFocusItem() {
        return null;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.IScrollChartOuterObserver
    public void initChartLinkage() {
    }

    public ScrollChartHorizontalObserverHRView(Context context, ObserveredClassifiedView observeredClassifiedView, String str) {
        super(context);
        this.mHost = observeredClassifiedView;
        initView(str);
    }

    private void initView(String str) {
        inflate(getContext(), R.layout.view_heart_rate_horizontal_observer, this);
        this.mDivider = findViewById(R.id.divider);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.view_title);
        this.mTitle = healthTextView;
        healthTextView.setText(str);
    }

    public void goneDivider() {
        this.mDivider.setVisibility(8);
    }

    public HealthTextView getTitle() {
        return this.mTitle;
    }
}
