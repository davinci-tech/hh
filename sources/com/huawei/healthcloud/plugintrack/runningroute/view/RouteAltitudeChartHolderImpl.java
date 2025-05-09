package com.huawei.healthcloud.plugintrack.runningroute.view;

import android.content.Context;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.runningroute.data.RouterAltitude;
import com.huawei.healthcloud.plugintrack.runningroute.view.RouteAltitudeChartHolderImpl;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.view.trackview.TrackLineChartHolder;
import defpackage.kom;
import defpackage.koq;
import defpackage.nqp;
import defpackage.ntl;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class RouteAltitudeChartHolderImpl extends RouteAltitudeChartHolderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private HwHealthLineDataSet f3574a;
    private float b;
    private float c;
    private List<RouterAltitude> d;
    private int e;
    private double f;
    private float i;
    private List<TrackLineChartHolder.Mode> j;

    public static /* synthetic */ boolean e(int i, int i2, int i3) {
        return true;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireWeightDataInterval() {
        return 0;
    }

    public RouteAltitudeChartHolderImpl(Context context) {
        super(context);
        this.d = new ArrayList();
        this.j = new ArrayList(16);
        this.c = Float.MIN_VALUE;
        this.i = Float.MAX_VALUE;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<kom> acquireWeightData() {
        return new ArrayList();
    }

    public void e(List<RouterAltitude> list) {
        this.d.clear();
        this.d.addAll(list);
        e();
    }

    private void e() {
        float f;
        if (koq.c(this.d)) {
            f = Math.max(this.b, this.d.get(r1.size() - 1).getDistance());
        } else {
            f = 0.0f;
        }
        this.b = f;
    }

    private List<HwHealthBaseEntry> d() {
        ArrayList arrayList = new ArrayList(16);
        this.c = -2.1474836E9f;
        this.i = 2.1474836E9f;
        this.f = 0.0d;
        this.e = 0;
        for (int i = 0; i < this.d.size(); i++) {
            RouterAltitude routerAltitude = this.d.get(i);
            if (routerAltitude != null) {
                float distance = routerAltitude.getDistance();
                float altitude = routerAltitude.getAltitude();
                arrayList.add(new HwHealthBaseEntry(distance, altitude));
                this.c = Math.max(this.c, altitude);
                this.i = Math.min(this.i, altitude);
                this.f += altitude;
                this.e++;
            }
        }
        return arrayList;
    }

    public void c(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, HwHealthBaseCombinedChart.TimeValueMode timeValueMode) {
        hwHealthBaseCombinedChart.setLogEnabled(true);
        this.f3574a = new HwHealthLineDataSet(BaseApplication.getContext(), d(), this.mContext.getResources().getString(R$string.IDS_hwh_motiontrack_alti), this.mContext.getResources().getString(R$string.IDS_hwh_motiontrack_alti), this.mContext.getResources().getString(R$string.IDS_route_alt_chart_x_label), 1);
        hwHealthBaseCombinedChart.setTimeValueMode(timeValueMode);
        this.f3574a.setColor(ContextCompat.getColor(this.mContext, R.color._2131296501_res_0x7f0900f5));
        this.f3574a.b(ContextCompat.getColor(this.mContext, R.color._2131296501_res_0x7f0900f5), ContextCompat.getColor(this.mContext, R.color._2131296666_res_0x7f09019a), true);
        this.f3574a.a(new HwHealthLineDataSet.LineLinkerFilter() { // from class: gzt
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.LineLinkerFilter
            public final boolean drawLine(int i, int i2, int i3) {
                return RouteAltitudeChartHolderImpl.e(i, i2, i3);
            }
        });
        TrackLineChartHolder.Mode b = ntl.b(hwHealthBaseCombinedChart);
        if (b != TrackLineChartHolder.Mode.MODE_NONE) {
            HwHealthLineDataSet hwHealthLineDataSet = this.f3574a;
            float f = this.c;
            int i = this.e;
            ntl.d(new TrackLineChartHolder.d(hwHealthBaseCombinedChart, hwHealthLineDataSet, b, f, i > 0 ? (float) (this.f / i) : 0.0f, this.i, false, true, false));
        }
        ntl.e(this.f3574a);
        new nqp().initStyle(hwHealthBaseCombinedChart, this.f3574a);
        ntl.a(hwHealthBaseCombinedChart, this.f3574a, this.j);
        hwHealthBaseCombinedChart.refresh();
    }

    public void d(HwHealthBaseCombinedChart hwHealthBaseCombinedChart) {
        if (hwHealthBaseCombinedChart == null) {
            return;
        }
        hwHealthBaseCombinedChart.clear();
        hwHealthBaseCombinedChart.refresh();
    }
}
