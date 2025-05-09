package com.huawei.ui.commonui.linechart.view.dashboard;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.view.dashboard.DashboardRingView;
import defpackage.nrr;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.text.DecimalFormat;
import java.util.List;

/* loaded from: classes6.dex */
public class BloodSugarDashboardView extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8902a;
    private Context b;
    private HealthTextView c;
    private float d;
    private List<DashboardRingView.b> e;
    private float f;
    private HealthTextView g;
    private float h;
    private DashboardRingView i;
    private LinearLayout j;

    public BloodSugarDashboardView(Context context) {
        this(context, null);
    }

    public BloodSugarDashboardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BloodSugarDashboardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = 33.0f;
        this.h = 1.0f;
        this.d = 1.0f;
        c(context);
    }

    public void setCurrentValue(float f) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        HealthTextView healthTextView = this.c;
        if (healthTextView != null) {
            healthTextView.setText(decimalFormat.format(f));
        }
        this.d = f;
    }

    public void setCurrentValueText(String str) {
        HealthTextView healthTextView = this.c;
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
    }

    public void setCurrentValueTextUnit(String str) {
        if (this.c != null) {
            this.f8902a.setText(str);
        }
    }

    public void setStatusText(String str, int i) {
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.tv_unit);
        if (this.g != null) {
            if (nsn.r()) {
                this.g.setTextSize(1, 28.0f);
                healthTextView.setTextSize(1, 24.0f);
            } else {
                this.g.setTextSize(1, 14.0f);
                healthTextView.setTextSize(1, 12.0f);
            }
            this.g.setText(str);
            this.g.setTextColor(i);
        }
    }

    public void setRingAreas(float f, float f2, List<DashboardRingView.b> list) {
        if (f > f2) {
            Log.e("BloodSugarDashboardView", "the minValue and maxValue is invalid");
            return;
        }
        this.h = f;
        this.f = f2;
        this.e = list;
    }

    public void c() {
        DashboardRingView dashboardRingView = this.i;
        if (dashboardRingView == null) {
            LogUtil.h("BloodSugarDashboardView", "the mRingView is null, return;");
            return;
        }
        dashboardRingView.setMinValue(this.h);
        this.i.setMaxValue(this.f);
        this.i.setRingArcAreaList(this.e);
        this.i.setCurrentValue(this.d);
        this.i.a();
    }

    private void c(Context context) {
        this.b = context;
        inflate(context, R.layout.layout_blood_sugar_dashbord, this);
        this.c = (HealthTextView) findViewById(R.id.tv_current_value);
        this.f8902a = (HealthTextView) findViewById(R.id.tv_unit);
        this.j = (LinearLayout) findViewById(R.id.current_value_container);
        this.g = (HealthTextView) findViewById(R.id.tv_status);
        DashboardRingView dashboardRingView = (DashboardRingView) findViewById(R.id.dashboard_ring_view);
        this.i = dashboardRingView;
        dashboardRingView.setMinValue(1.0f);
        this.i.setMaxValue(33.0f);
        this.c.setTypeface(Typeface.createFromAsset(BaseApplication.e().getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf"));
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (!LanguageUtil.m(this.b) || this.g.getMeasureWidth() > this.i.getRadius()) {
            int size = View.MeasureSpec.getSize(i);
            int size2 = View.MeasureSpec.getSize(i2);
            int measuredHeight = this.g.getMeasuredHeight() / 2;
            int e = nrr.e(this.b, r1.getResources().getDimensionPixelSize(R.dimen._2131365060_res_0x7f0a0cc4));
            setMeasuredDimension(size, size2 + e + measuredHeight);
            ViewGroup.LayoutParams layoutParams = this.g.getLayoutParams();
            if (layoutParams instanceof FrameLayout.LayoutParams) {
                FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
                layoutParams2.setMargins(0, measuredHeight + e, 0, 0);
                this.g.setLayoutParams(layoutParams2);
            }
            ViewGroup.LayoutParams layoutParams3 = this.j.getLayoutParams();
            if (layoutParams3 instanceof FrameLayout.LayoutParams) {
                FrameLayout.LayoutParams layoutParams4 = (FrameLayout.LayoutParams) layoutParams3;
                layoutParams4.setMargins(0, -e, 0, 0);
                this.j.setLayoutParams(layoutParams4);
            }
        }
    }
}
