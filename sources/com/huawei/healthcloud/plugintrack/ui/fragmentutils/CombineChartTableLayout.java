package com.huawei.healthcloud.plugintrack.ui.fragmentutils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.tablewidget.HealthTableWidget;

/* loaded from: classes4.dex */
public class CombineChartTableLayout extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f3761a;
    private HealthTableWidget b;
    private HealthDivider d;

    public CombineChartTableLayout(Context context) {
        super(context);
        d(context);
    }

    public CombineChartTableLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d(context);
    }

    public CombineChartTableLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        d(context);
    }

    private void d(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.chart_combine_layout, this);
        this.f3761a = (LinearLayout) inflate.findViewById(R.id.layout_combine_chart);
        this.d = (HealthDivider) inflate.findViewById(R.id.view_div_tip);
        this.b = (HealthTableWidget) inflate.findViewById(R.id.pace_table_layout);
    }

    public LinearLayout getChartView() {
        return this.f3761a;
    }

    public HealthDivider getDiv() {
        return this.d;
    }

    public HealthTableWidget getTable() {
        return this.b;
    }
}
