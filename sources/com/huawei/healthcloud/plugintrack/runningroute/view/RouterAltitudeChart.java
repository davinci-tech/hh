package com.huawei.healthcloud.plugintrack.runningroute.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.util.Pair;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.runningroute.data.RouterAltitude;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.anchor.Layout;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthTrackCombinedChart;
import defpackage.koq;
import defpackage.nsn;
import health.compact.a.UnitUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class RouterAltitudeChart extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private RouteAltitudeChartHolderImpl f3576a;
    private HwHealthTrackCombinedChart b;
    private HealthTextView c;
    private boolean d;
    private HealthTextView e;

    public RouterAltitudeChart(Context context) {
        super(context);
        this.e = null;
        this.c = null;
        this.b = null;
        this.d = UnitUtil.h();
        e();
    }

    public RouterAltitudeChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = null;
        this.c = null;
        this.b = null;
        this.d = UnitUtil.h();
        e();
    }

    public RouterAltitudeChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = null;
        this.c = null;
        this.b = null;
        this.d = UnitUtil.h();
        e();
    }

    private void e() {
        String string;
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_track_chart_viewholder, (ViewGroup) this, true);
        this.e = (HealthTextView) findViewById(R.id.text_track_detail_left_value);
        ((HealthTextView) findViewById(R.id.text_curve_title)).setText(R.string._2130846061_res_0x7f02216d);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.text_curve_title_unit);
        Context e = BaseApplication.e();
        if (this.d) {
            string = e.getResources().getString(R.string._2130839866_res_0x7f02093a, e.getResources().getString(R.string._2130841417_res_0x7f020f49));
        } else {
            string = e.getResources().getString(R.string._2130839866_res_0x7f02093a, e.getResources().getString(R.string._2130841568_res_0x7f020fe0));
        }
        healthTextView.setText(string);
        ((HealthTextView) findViewById(R.id.text_track_detail_left_title)).setText(R.string._2130842888_res_0x7f021508);
        ((HealthTextView) findViewById(R.id.text_track_detail_right_title)).setText(R.string._2130842887_res_0x7f021507);
        this.c = (HealthTextView) findViewById(R.id.text_track_detail_right_value);
        ((HealthTextView) findViewById(R.id.text_curve_detail)).setVisibility(8);
        HwHealthTrackCombinedChart hwHealthTrackCombinedChart = (HwHealthTrackCombinedChart) findViewById(R.id.combined_chart);
        this.b = hwHealthTrackCombinedChart;
        hwHealthTrackCombinedChart.e((HwHealthBaseCombinedChart.DataParser) null);
        this.f3576a = new RouteAltitudeChartHolderImpl(getContext());
        Layout acquireLayout = this.b.acquireLayout();
        if (acquireLayout != null) {
            acquireLayout.c(0.0f, 0.0f);
            acquireLayout.b(0.0f);
        }
        aXt_(inflate);
    }

    private void aXt_(View view) {
        if (nsn.l()) {
            boolean ag = nsn.ag(getContext());
            LogUtil.a("RunningRouterLineChart", "inflateView, isTahitiModel: ", Boolean.valueOf(ag));
            ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.heart_rate_right_data);
            if (viewGroup != null && (viewGroup.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewGroup.getLayoutParams();
                if (ag) {
                    layoutParams.setMarginStart(nsn.c(getContext(), 12.0f));
                } else {
                    layoutParams.setMarginStart(nsn.c(getContext(), 34.0f));
                }
                viewGroup.setLayoutParams(layoutParams);
                return;
            }
            LogUtil.h("RunningRouterLineChart", "inflateView, viewGroup is null, ", "or layoutParams isn't the instance of LinearLauout.LayoutParams");
        }
    }

    public void d(List<RouterAltitude> list) {
        boolean z;
        if (koq.b(list)) {
            LogUtil.a("RunningRouterLineChart", "dataList is empty");
            return;
        }
        if (this.d) {
            for (RouterAltitude routerAltitude : list) {
                routerAltitude.setDistance((float) UnitUtil.e(routerAltitude.getDistance(), 3));
                routerAltitude.setAltitude((float) UnitUtil.e(routerAltitude.getAltitude(), 1));
            }
        }
        if (list.get(list.size() - 1).getDistance() <= 1000.0f) {
            z = true;
        } else {
            for (RouterAltitude routerAltitude2 : list) {
                routerAltitude2.setDistance(routerAltitude2.getDistance() / 1000.0f);
            }
            z = false;
        }
        this.b.getXAxis().setAxisMaximum(list.get(list.size() - 1).getDistance());
        this.f3576a.e(list);
        this.f3576a.c(this.b, z ? HwHealthBaseCombinedChart.TimeValueMode.METERS : HwHealthBaseCombinedChart.TimeValueMode.KILOMETERS);
        Pair<Float, Float> b = b(list);
        if (b.first != null) {
            this.e.setText(UnitUtil.e(r0.floatValue(), 1, 0));
        }
        if (b.second != null) {
            this.c.setText(UnitUtil.e(r7.floatValue(), 1, 0));
        }
    }

    public void a() {
        if (this.f3576a == null) {
            LogUtil.a("RunningRouterLineChart", "rmDataLayer mChartHolder is null");
        }
        this.f3576a.d(this.b);
    }

    private Pair<Float, Float> b(List<RouterAltitude> list) {
        float f = Float.MIN_VALUE;
        float f2 = Float.MAX_VALUE;
        for (RouterAltitude routerAltitude : list) {
            if (routerAltitude != null) {
                float altitude = routerAltitude.getAltitude();
                f = Math.max(f, altitude);
                f2 = Math.min(f2, altitude);
            }
        }
        return new Pair<>(Float.valueOf(f2), Float.valueOf(f));
    }

    public void setIsTouchEventConsumption(boolean z) {
        HwHealthTrackCombinedChart hwHealthTrackCombinedChart = this.b;
        if (hwHealthTrackCombinedChart != null) {
            hwHealthTrackCombinedChart.setIsTouchEventConsumption(z);
        }
    }
}
