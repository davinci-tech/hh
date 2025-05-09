package defpackage;

import android.graphics.Color;
import com.amap.api.services.core.AMapException;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.ui.hrcontrol.HeartRateControlSportChart;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class lca {

    /* renamed from: a, reason: collision with root package name */
    private int f14757a;
    private HeartRateControlSportChart e;
    private List<nns> f;
    private int g;
    private static final int c = Color.parseColor("#80EB4667");
    private static final int b = Color.parseColor("#4DEB4667");
    private List<Entry> d = new ArrayList();
    private int j = AMapException.CODE_AMAP_CLIENT_ERRORCODE_MISSSING;

    public lca(HeartRateControlSportChart heartRateControlSportChart) {
        this.e = heartRateControlSportChart;
        b();
    }

    public void e(List<nns> list) {
        this.f = list;
        this.e.setIntervalAreaList(e(0));
    }

    public void a(int i) {
        this.j = i;
        e();
    }

    public void a(int i, int i2) {
        LogUtil.a("HeartRateControlSportChartHolder", "min:", Integer.valueOf(i), "max:", Integer.valueOf(i2));
        this.g = Math.max(i, 40);
        int min = Math.min(i2, HeartRateThresholdConfig.HEART_RATE_LIMIT);
        this.f14757a = min;
        if (min <= this.g) {
            this.g = 40;
            this.f14757a = HeartRateThresholdConfig.HEART_RATE_LIMIT;
            LogUtil.a("HeartRateControlSportChartHolder", "adjustChartY invalid");
        } else {
            YAxis axisLeft = this.e.getAxisLeft();
            axisLeft.setAxisMaximum(this.f14757a);
            axisLeft.setAxisMinimum(this.g);
        }
    }

    private void b() {
        YAxis axisLeft = this.e.getAxisLeft();
        axisLeft.setAxisMaximum(220.0f);
        axisLeft.setAxisMinimum(40.0f);
        axisLeft.setLabelCount(5, true);
        e();
    }

    private void e() {
        XAxis xAxis = this.e.getXAxis();
        xAxis.setAxisMinimum(0.0f);
        xAxis.setAxisMaximum(this.j);
        xAxis.setLabelCount(6, true);
    }

    public void c() {
        HeartRateControlSportChart heartRateControlSportChart = this.e;
        if (heartRateControlSportChart != null) {
            heartRateControlSportChart.a();
        }
    }

    public void b(int i, int i2) {
        if (i >= 0) {
            this.e.setIntervalAreaList(e(i));
        }
        if (i2 >= 40 && i2 <= 220) {
            this.d.add(new Entry(i, i2));
            b(i2);
        }
        this.e.setChartData(this.d);
    }

    public void b(int i) {
        if (i > this.f14757a || i < this.g) {
            LogUtil.a("HeartRateControlSportChartHolder", "judgeAndAdjustChartY:", Integer.valueOf(i));
            int i2 = this.g;
            if (i < i2) {
                a(((int) Math.floor(i / 10.0d)) * 10, this.f14757a);
            } else if (i > this.f14757a) {
                a(i2, ((int) Math.ceil(i / 10.0d)) * 10);
            } else {
                LogUtil.a("HeartRateControlSportChartHolder", "not judgeAndAdjustChartY");
            }
        }
    }

    public void a(List<Entry> list) {
        if (koq.b(list)) {
            LogUtil.h("HeartRateControlSportChartHolder", "chart rate data list is null");
            return;
        }
        int x = (int) list.get(list.size() - 1).getX();
        if (x >= 0 && x <= this.j) {
            this.e.setIntervalAreaList(e(x));
        }
        this.d.clear();
        this.d.addAll(list);
        this.e.setChartData(this.d);
    }

    private List<nns> e(int i) {
        LogUtil.a("HeartRateControlSportChartHolder", "enter getDrawIntervalArea time: ", Integer.valueOf(i));
        ArrayList arrayList = new ArrayList();
        List<nns> list = this.f;
        if (list == null || list.size() < 1) {
            LogUtil.h("HeartRateControlSportChartHolder", "mOriginAreaList is null");
            return arrayList;
        }
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            nns nnsVar = this.f.get(i2);
            float a2 = nnsVar.a();
            float b2 = nnsVar.b();
            float d = nnsVar.d();
            float c2 = nnsVar.c();
            float f = i;
            if (f > b2) {
                arrayList.add(new nns(a2, d, b2, c2, c));
            } else if (f >= a2 && f <= b2) {
                arrayList.add(new nns(a2, d, f, c2, c));
                arrayList.add(new nns(f, d, b2, c2, b));
            } else {
                arrayList.add(new nns(a2, d, b2, c2, b));
            }
        }
        return arrayList;
    }
}
