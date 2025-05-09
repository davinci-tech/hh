package com.huawei.ui.commonui.chart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.chart.HealthRingChartAdapter;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nkz;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class HealthRingChart extends LinearLayout implements HealthRingChartAdapter.DataListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthRingChartAdapter f8786a;
    private HealthChart b;
    private HealthTextView c;
    private HealthTextView d;
    private final Context e;
    private LinearLayout g;
    private final List<HealthRingChartAdapter.b> h;

    public HealthRingChart(Context context) {
        super(context);
        this.h = new ArrayList();
        this.e = context;
        b();
    }

    public HealthRingChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = new ArrayList();
        this.e = context;
        b();
    }

    private void b() {
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.health_ring_chart_layout, this);
        this.b = (HealthChart) inflate.findViewById(R.id.ring_chart);
        this.g = (LinearLayout) inflate.findViewById(R.id.ring_chart_items_container);
        this.d = (HealthTextView) inflate.findViewById(R.id.ring_chart_desc);
        this.c = (HealthTextView) inflate.findViewById(R.id.ring_chart_desc_oneline);
        if (nsn.r()) {
            this.b.getLayoutParams().height = (int) this.e.getResources().getDimension(R.dimen._2131362879_res_0x7f0a043f);
            this.b.getLayoutParams().width = (int) this.e.getResources().getDimension(R.dimen._2131362879_res_0x7f0a043f);
            this.d.setTextSize(1, 16.0f);
            setThreeTextFont(this.c);
        }
    }

    private void setThreeTextFont(HealthTextView healthTextView) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131362973_res_0x7f0a049d);
        healthTextView.setAutoTextSize(0, dimensionPixelSize);
        healthTextView.setAutoTextInfo(dimensionPixelSize, 1, 0);
    }

    public void setAdapter(HealthRingChartAdapter healthRingChartAdapter) {
        this.f8786a = healthRingChartAdapter;
        healthRingChartAdapter.a(this);
        e();
    }

    public HealthRingChartAdapter getAdapter() {
        return this.f8786a;
    }

    public void c(List<nkz> list) {
        HealthRingChartAdapter healthRingChartAdapter = this.f8786a;
        if (healthRingChartAdapter == null) {
            LogUtil.b("HealthRingChart", "updateData failed, cause adapter not set!");
        } else {
            healthRingChartAdapter.c(list);
        }
    }

    public void d(List<Integer> list) {
        HealthRingChartAdapter healthRingChartAdapter = this.f8786a;
        if (healthRingChartAdapter == null) {
            LogUtil.b("HealthRingChart", "updateValues failed, cause adapter not set!");
        } else {
            healthRingChartAdapter.e(list);
        }
    }

    public void setDesc(String str) {
        this.c.setVisibility(8);
        this.d.setVisibility(0);
        if (str.length() >= 4) {
            int length = str.length();
            int i = length / 2;
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < length; i2++) {
                sb.append(str.charAt(i2));
                if (i2 == i - 1) {
                    sb.append(System.lineSeparator());
                }
            }
            this.d.setText(sb);
            return;
        }
        this.d.setText(str);
    }

    public void c(String str) {
        this.d.setVisibility(8);
        this.c.setVisibility(0);
        this.c.setText(str);
    }

    public void setDesc(String str, String str2) {
        this.c.setVisibility(8);
        this.d.setVisibility(0);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(System.lineSeparator());
        sb.append(str2);
        this.d.setText(sb);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        float f = 2.1474836E9f;
        float f2 = 2.1474836E9f;
        float f3 = 2.1474836E9f;
        for (int i = 0; i < this.h.size(); i++) {
            HealthRingChartAdapter.b bVar = this.h.get(i);
            float textSize = bVar.b.getTextSize();
            if (textSize < f3) {
                f3 = textSize;
            }
            float textSize2 = bVar.d.getTextSize();
            if (textSize2 < f2) {
                f2 = textSize2;
            }
            float textSize3 = bVar.e.getTextSize();
            if (textSize3 < f) {
                f = textSize3;
            }
        }
        e(f, f3, f2);
    }

    private void e(float f, float f2, float f3) {
        for (HealthRingChartAdapter.b bVar : this.h) {
            if (bVar != null) {
                if (bVar.e != null) {
                    bVar.e.setAutoTextSize(0, f);
                }
                if (bVar.b != null) {
                    bVar.b.setAutoTextSize(0, f2);
                }
                if (bVar.d != null) {
                    bVar.d.setAutoTextSize(0, f3);
                }
            }
        }
    }

    private void e() {
        if (this.g == null) {
            LogUtil.b("HealthRingChart", "ring chart items container not found, refresh data failed!");
            return;
        }
        HealthRingChartAdapter healthRingChartAdapter = this.f8786a;
        if (healthRingChartAdapter == null) {
            LogUtil.b("HealthRingChart", "ring chart adapter not found, refresh data failed!");
            return;
        }
        int e = healthRingChartAdapter.e();
        int childCount = this.g.getChildCount();
        for (int i = 0; i < e; i++) {
            if (i >= childCount) {
                this.h.add(this.f8786a.cxE_(this.g));
            }
            this.f8786a.b(this.h.get(i), i);
        }
        if (childCount > e) {
            this.g.removeViews(e, childCount - e);
            this.h.subList(e, childCount).clear();
        }
        if (this.b != null) {
            int[] b = this.f8786a.b();
            this.b.setChartData(b != null ? c(b) : 0, b, this.f8786a.d(), this.f8786a.c());
        }
        this.g.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.commonui.chart.HealthRingChart.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (!HealthRingChart.this.f8786a.a()) {
                    HealthRingChart.this.d();
                }
                HealthRingChart.this.g.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private static int c(int[] iArr) {
        int i = 0;
        for (int i2 : iArr) {
            i += i2;
        }
        return i;
    }

    @Override // com.huawei.ui.commonui.chart.HealthRingChartAdapter.DataListener
    public void onDataUpdate() {
        e();
    }
}
