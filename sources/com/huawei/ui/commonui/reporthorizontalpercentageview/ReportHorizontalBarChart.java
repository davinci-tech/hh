package com.huawei.ui.commonui.reporthorizontalpercentageview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes8.dex */
public class ReportHorizontalBarChart extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private Context f8937a;
    private DottedPercentageView b;
    private HealthTextView c;
    private float d;
    private float e;
    private HealthTextView h;

    public ReportHorizontalBarChart(Context context) {
        this(context, null);
    }

    public ReportHorizontalBarChart(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ReportHorizontalBarChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        cFi_(context, attributeSet, i, 0);
    }

    public void setExerciseTime(String str) {
        this.h.setText(str);
    }

    public void setWeekDate(String str) {
        this.c.setText(str);
    }

    public void setMaxValue(float f, float f2) {
        this.d = f;
        this.e = f2;
        this.b.setData((int) f, (int) f2);
    }

    private void cFi_(Context context, AttributeSet attributeSet, int i, int i2) {
        if (context == null) {
            LogUtil.h("ReportHorizontalBarChart", "initView Context is null");
            return;
        }
        this.f8937a = context;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        if (layoutInflater == null) {
            LogUtil.h("ReportHorizontalBarChart", "initView LayoutInflater is null");
            return;
        }
        View inflate = layoutInflater.inflate(R.layout.report_horizontal_bar_chart, (ViewGroup) this, false);
        addView(inflate);
        cFj_(inflate);
    }

    private void cFj_(View view) {
        this.b = (DottedPercentageView) view.findViewById(R.id.report_weekly_distribution_percentage_view);
        this.c = (HealthTextView) view.findViewById(R.id.report_weekly_distribution_date);
        this.h = (HealthTextView) view.findViewById(R.id.report_weekly_distribution_time);
    }
}
