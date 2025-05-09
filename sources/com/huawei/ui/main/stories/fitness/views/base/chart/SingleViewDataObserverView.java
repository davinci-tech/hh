package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.content.Context;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;

/* loaded from: classes6.dex */
public class SingleViewDataObserverView extends ScrollChartObserverView {
    private ScrollChartObserverView c;
    private LinearLayout d;

    public SingleViewDataObserverView(Context context) {
        super(context, null, null, null);
        c();
    }

    private void c() {
        inflate(getContext(), R.layout.single_view_data_observer_view, this);
        this.d = (LinearLayout) findViewById(R.id.observer_view_place);
    }

    public void b(ScrollChartObserverView scrollChartObserverView) {
        this.c = scrollChartObserverView;
        this.d.addView(scrollChartObserverView);
    }

    public ScrollChartObserverView b() {
        return this.c;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView
    public void onRangeShow(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, int i, int i2) {
        this.c.onRangeShow(hwHealthBaseScrollBarLineChart, i, i2);
    }
}
