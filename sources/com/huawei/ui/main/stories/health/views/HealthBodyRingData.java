package com.huawei.ui.main.stories.health.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.chart.HealthRingChart;
import com.huawei.ui.commonui.chart.HealthRingChartAdapter;
import com.huawei.ui.main.R$string;
import defpackage.nld;
import defpackage.qsj;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes7.dex */
public class HealthBodyRingData extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private Context f10265a;
    private int b;
    private HealthRingChart d;

    public HealthBodyRingData(Context context) {
        this(context, null);
        e(context);
    }

    public HealthBodyRingData(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (attributeSet == null) {
            LogUtil.h("HealthBodyRingData", "HealthBodyDetail AttributeSet or context is null");
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131099999_res_0x7f06015f});
        try {
            this.b = obtainStyledAttributes.getInteger(0, 0);
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("HealthBodyRingData", "HealthBodyRingData Resources NotFoundException");
        }
        obtainStyledAttributes.recycle();
        e(context);
    }

    private void e(Context context) {
        View inflate;
        if (context == null) {
            LogUtil.h("HealthBodyRingData", "initBodyRingView Context is null");
            return;
        }
        this.f10265a = context;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        if (layoutInflater == null) {
            LogUtil.h("HealthBodyRingData", "initBodyRingView LayoutInflater is null");
            return;
        }
        int i = this.b;
        if (i == 0) {
            inflate = layoutInflater.inflate(R.layout.health_body_ring_view_data, this);
        } else if (i == 2) {
            inflate = layoutInflater.inflate(R.layout.health_body_ring_view_data_oversea_report, this);
        } else {
            inflate = layoutInflater.inflate(R.layout.health_body_ring_view_data_report, this);
        }
        this.d = (HealthRingChart) inflate.findViewById(R.id.body_analysis_chart);
        HealthRingChartAdapter healthRingChartAdapter = new HealthRingChartAdapter(this.f10265a, new nld(true, false, true));
        healthRingChartAdapter.a(new HealthRingChartAdapter.DataFormatter() { // from class: qsl
            @Override // com.huawei.ui.commonui.chart.HealthRingChartAdapter.DataFormatter
            public final String format(nkz nkzVar) {
                String e;
                e = UnitUtil.e(nkzVar.i(), 1, 1);
                return e;
            }
        });
        this.d.setAdapter(healthRingChartAdapter);
    }

    public void setBodyCircleViewData(float[] fArr, double d, final int i) {
        HealthRingChart healthRingChart = this.d;
        if (healthRingChart == null) {
            LogUtil.h("HealthBodyRingData", "setBodyCircleViewData mBodyCircleView is null");
            return;
        }
        if (fArr.length <= 0) {
            return;
        }
        HealthRingChartAdapter adapter = healthRingChart.getAdapter();
        if (adapter != null) {
            adapter.a(new HealthRingChartAdapter.DataFormatter() { // from class: qsm
                @Override // com.huawei.ui.commonui.chart.HealthRingChartAdapter.DataFormatter
                public final String format(nkz nkzVar) {
                    String e;
                    e = UnitUtil.e(nkzVar.i(), 1, i);
                    return e;
                }
            });
        }
        this.d.c(qsj.b(this.f10265a, fArr));
        if (LanguageUtil.j(this.f10265a)) {
            this.d.setDesc(this.f10265a.getResources().getString(R$string.IDS_hw_health_show_healthdata_weight), String.valueOf(d));
        }
    }
}
