package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import defpackage.nsn;

/* loaded from: classes6.dex */
public class StepDoubleViewDataObserverView extends ScrollChartObserverView {

    /* renamed from: a, reason: collision with root package name */
    private ScrollChartObserverView f9951a;
    private ScrollChartObserverView b;
    private LinearLayout c;
    private LinearLayout d;
    private LinearLayout e;
    private LinearLayout j;

    public StepDoubleViewDataObserverView(Context context, ObserveredClassifiedView observeredClassifiedView) {
        super(context, observeredClassifiedView, null, null);
        a();
    }

    private void a() {
        inflate(getContext(), R.layout.double_data_step_transfer_view, this);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.observer_view_horizontal_container);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.observer_view_vertical_container);
        if (nsn.r()) {
            linearLayout.setVisibility(8);
            linearLayout2.setVisibility(0);
            this.j = (LinearLayout) findViewById(R.id.observer_view_first_place_vertical);
            this.c = (LinearLayout) findViewById(R.id.observer_view_second_place_vertical);
        } else {
            linearLayout.setVisibility(0);
            linearLayout2.setVisibility(8);
            this.j = (LinearLayout) findViewById(R.id.observer_view_first_place_horizontal);
            this.c = (LinearLayout) findViewById(R.id.observer_view_second_place_horizontal);
        }
        this.e = (LinearLayout) findViewById(R.id.day_distance_description);
        this.d = (LinearLayout) findViewById(R.id.day_calorie_description);
    }

    public void dvB_(ScrollChartObserverView scrollChartObserverView, ScrollChartObserverView scrollChartObserverView2, RelativeLayout relativeLayout, RelativeLayout relativeLayout2) {
        this.b = scrollChartObserverView;
        this.f9951a = scrollChartObserverView2;
        this.j.addView(scrollChartObserverView);
        this.c.addView(scrollChartObserverView2);
        this.d.addView(relativeLayout);
        this.e.addView(relativeLayout2);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView
    public void onRangeShow(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, int i, int i2) {
        this.b.onRangeShow(hwHealthBaseScrollBarLineChart, i, i2);
        this.f9951a.onRangeShow(hwHealthBaseScrollBarLineChart, i, i2);
    }
}
