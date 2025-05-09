package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder;
import defpackage.nsn;
import health.compact.a.util.LogUtil;

/* loaded from: classes6.dex */
public class DoubleViewDataObserverView extends ScrollChartObserverView {

    /* renamed from: a, reason: collision with root package name */
    protected boolean f9937a;
    protected LinearLayout b;
    protected ScrollChartObserverView c;
    protected d d;
    protected ScrollChartObserverView e;
    protected LinearLayout g;

    public DoubleViewDataObserverView(Context context, ObserveredClassifiedView observeredClassifiedView) {
        super(context, observeredClassifiedView, null, null);
        this.f9937a = false;
        this.d = null;
        e();
    }

    private void e() {
        inflate(getContext(), R.layout.double_view_data_observer_view, this);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.observer_view_horizontal_container);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.observer_view_vertical_container);
        if (nsn.r()) {
            linearLayout.setVisibility(8);
            linearLayout2.setVisibility(0);
            this.b = (LinearLayout) findViewById(R.id.observer_view_first_place_vertical);
            this.g = (LinearLayout) findViewById(R.id.observer_view_second_place_vertical);
            return;
        }
        linearLayout.setVisibility(0);
        linearLayout2.setVisibility(8);
        this.b = (LinearLayout) findViewById(R.id.observer_view_first_place_horizontal);
        this.g = (LinearLayout) findViewById(R.id.observer_view_second_place_horizontal);
    }

    public void d(ScrollChartObserverView scrollChartObserverView, ScrollChartObserverView scrollChartObserverView2, boolean z) {
        this.c = scrollChartObserverView;
        this.e = scrollChartObserverView2;
        this.b.addView(scrollChartObserverView);
        this.g.addView(scrollChartObserverView2);
        e(z);
    }

    public void d(ScrollChartObserverView scrollChartObserverView, ScrollChartObserverView scrollChartObserverView2) {
        d(scrollChartObserverView, scrollChartObserverView2, false);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView
    public void onRangeShow(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, int i, int i2) {
        this.c.onRangeShow(hwHealthBaseScrollBarLineChart, i, i2);
        this.e.onRangeShow(hwHealthBaseScrollBarLineChart, i, i2);
    }

    protected class d {
        protected boolean d = false;
        protected HwHealthBaseBarLineDataSet e = null;
        protected HwHealthChartHolder.b c = null;
        protected View.OnClickListener b = new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.DoubleViewDataObserverView.d.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view == DoubleViewDataObserverView.this.g && !d.this.a()) {
                    d.this.g();
                    d.this.e(false, true);
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    d.this.h();
                    d.this.e(true, false);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        };

        protected d() {
        }

        protected void e(boolean z, boolean z2) {
            if (z == z2) {
                LogUtil.c("DoubleViewDataObserverView", "Can not focus two item in the same time");
            }
        }

        protected void h() {
            b();
            dvy_(DoubleViewDataObserverView.this.c);
        }

        protected void g() {
            e();
            dvy_(DoubleViewDataObserverView.this.e);
        }

        protected boolean a() {
            return this.d;
        }

        protected void e() {
            if (this.d || this.e == null) {
                LogUtil.c("DoubleViewDataObserverView", "addSuperLayer failed,current has SuperLayer!!!");
                return;
            }
            DoubleViewDataObserverView.this.mHost.addDataLayer(this.e, this.c);
            f();
            this.d = true;
        }

        protected void b() {
            if (!this.d || this.e == null) {
                return;
            }
            DoubleViewDataObserverView.this.mHost.removeDataLayer(this.e);
            c();
            this.d = false;
        }

        public void dvy_(View view) {
            DoubleViewDataObserverView.this.c.setBackgroundResource(R.drawable._2131431368_res_0x7f0b0fc8);
            DoubleViewDataObserverView.this.e.setBackgroundResource(R.drawable._2131431368_res_0x7f0b0fc8);
            view.setBackgroundResource(R.drawable._2131431367_res_0x7f0b0fc7);
            i();
        }

        protected void d() {
            DoubleViewDataObserverView.this.b.setOnClickListener(this.b);
            DoubleViewDataObserverView.this.g.setOnClickListener(this.b);
            dvy_(DoubleViewDataObserverView.this.c);
            e(true, false);
            HwHealthChartHolder.b bVar = new HwHealthChartHolder.b();
            bVar.e(DoubleViewDataObserverView.this.mHost.getStepDataType());
            bVar.e(HwHealthChartHolder.LAYER_ID_REST_HR);
            this.c = bVar;
            this.e = DoubleViewDataObserverView.this.mHost.fakeDataLayer(bVar);
            c();
        }

        protected void c() {
            IChartLayerHolder acquireChartLayerHolder = DoubleViewDataObserverView.this.mHost.acquireChartLayerHolder();
            if (!(acquireChartLayerHolder instanceof HwHealthScrollChartHolder)) {
                LogUtil.c("DoubleViewDataObserverView", "not support scrollable,init focus now only support scrollable chart!!!");
            } else {
                DoubleViewDataObserverView.this.mHost.manageDataSetAsProxy(this.e, ((HwHealthScrollChartHolder) acquireChartLayerHolder).acquireStorageHelper(), DoubleViewDataObserverView.this.mHost.getStepDataType(), this.c);
            }
        }

        protected void f() {
            DoubleViewDataObserverView.this.mHost.unManageDataSetAsProxy(this.e);
        }

        private void i() {
            if (this.d) {
                DoubleViewDataObserverView.this.b.setClickable(true);
                DoubleViewDataObserverView.this.g.setClickable(false);
            } else {
                DoubleViewDataObserverView.this.b.setClickable(false);
                DoubleViewDataObserverView.this.g.setClickable(true);
            }
        }
    }

    private void e(boolean z) {
        this.f9937a = z;
    }

    private void d() {
        if (this.f9937a) {
            d dVar = new d();
            this.d = dVar;
            dVar.d();
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView
    public void initChartLinkage() {
        d();
    }
}
