package com.huawei.ui.commonui.calendarview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.jcf;
import defpackage.nkw;
import defpackage.nky;

/* loaded from: classes6.dex */
public class HealthWeekBar extends LinearLayout {
    private static final String d = "com.huawei.ui.commonui.calendarview.HealthWeekBar";

    /* renamed from: a, reason: collision with root package name */
    private nky f8785a;
    private int e;

    public HealthWeekBar(Context context) {
        super(context);
        if (d.equals(getClass().getName())) {
            LayoutInflater.from(context).inflate(R.layout.health_calendar_view_week_bar, (ViewGroup) this, true);
        }
        nkw.c();
        this.e = getResources().getDimensionPixelSize(R.dimen._2131362850_res_0x7f0a0422);
    }

    void c(nky nkyVar) {
        this.f8785a = nkyVar;
        if (d.equalsIgnoreCase(getClass().getName())) {
            setWeekBarTextSize(this.f8785a.ah());
            setWeekBarTextColor(this.f8785a.ab());
            setBackgroundColor(this.f8785a.v());
            setPadding(this.f8785a.a(), 0, this.f8785a.b(), 0);
        }
    }

    protected void setWeekBarTextColor(int i) {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt instanceof HealthTextView) {
                ((HealthTextView) childAt).setTextColor(i);
            }
        }
    }

    protected void setWeekBarTextSize(int i) {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt instanceof HealthTextView) {
                ((HealthTextView) childAt).setTextSize(0, i);
            }
        }
    }

    protected void a(int i) {
        if (d.equalsIgnoreCase(getClass().getName())) {
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                View childAt = getChildAt(i2);
                if (childAt instanceof HealthTextView) {
                    ((HealthTextView) childAt).setText(nkw.b(getContext(), i2, i));
                    jcf.bEB_(childAt, nkw.b(i2, i), false);
                }
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        if (measuredWidth == 0) {
            return;
        }
        int paddingStart = getPaddingStart();
        int paddingEnd = getPaddingEnd();
        int childCount = getChildCount();
        int i3 = childCount - 1;
        int i4 = (((measuredWidth - paddingStart) - paddingEnd) - (this.e * childCount)) / i3;
        int i5 = (int) (i4 / 2.0f);
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
                if (i6 == 0) {
                    layoutParams.width = this.e;
                    layoutParams.setMarginEnd(i5);
                } else if (i6 == i3) {
                    layoutParams.width = this.e;
                    layoutParams.setMarginStart(i5);
                } else {
                    layoutParams.width = this.e + i4;
                }
                childAt.setLayoutParams(layoutParams);
            }
        }
        super.onMeasure(i, i2);
    }
}
