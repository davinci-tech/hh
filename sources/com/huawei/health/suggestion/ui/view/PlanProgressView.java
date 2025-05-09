package com.huawei.health.suggestion.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.ffy;
import defpackage.ffz;
import defpackage.moe;
import defpackage.moi;
import defpackage.nsn;
import health.compact.a.UnitUtil;

/* loaded from: classes8.dex */
public class PlanProgressView extends LinearLayout {
    private static final Float c = Float.valueOf(24.0f);

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f3422a;
    private float b;
    private float d;
    private HealthProgressBar e;
    private int f;
    private HealthTextView i;
    private int j;

    public PlanProgressView(Context context) {
        this(context, null);
    }

    public PlanProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d(context);
    }

    private void d(Context context) {
        View.inflate(context, R.layout.sug_plan_progress, this);
        this.e = (HealthProgressBar) findViewById(R.id.sug_plan_progress);
        this.i = (HealthTextView) findViewById(R.id.sug_txt_plan_progress);
        this.f3422a = (HealthTextView) findViewById(R.id.sug_txt_plan_total);
        this.j = R.string._2130837535_res_0x7f02001f;
        LogUtil.c("Suggestion_PlanProgressView", "mProgressBar:", String.valueOf(this.e), ",mTxtProgress:", String.valueOf(this.i), ",mTxtMax:", String.valueOf(this.f3422a));
        if (nsn.s()) {
            HealthTextView healthTextView = this.f3422a;
            Float f = c;
            healthTextView.setTextSize(1, f.floatValue());
            this.i.setTextSize(1, f.floatValue());
        }
    }

    private void e() {
        if (this.f == 0) {
            String j = moe.j(this.b);
            String j2 = moe.j(this.d);
            this.f3422a.setText(ffy.b(getUnitResId(), (int) this.b, j));
            this.i.setText(j2);
        } else {
            String i = moe.i(this.b);
            String i2 = moe.i(this.d);
            this.f3422a.setText(moi.e(getContext(), getUnitResId(), i));
            this.i.setText(i2);
        }
        this.e.setMax((int) this.b);
        this.e.setProgress((int) this.d);
    }

    public void setMax(float f) {
        this.b = (float) c(f);
        e();
    }

    public float getMax() {
        return this.b;
    }

    public void setProgress(float f) {
        this.d = (float) c(f);
        e();
    }

    public int getUnitResId() {
        if (this.f == 0) {
            this.j = ffz.c();
        } else {
            this.j = R.string._2130837535_res_0x7f02001f;
        }
        return this.j;
    }

    private double c(double d) {
        return (UnitUtil.h() && this.f == 0) ? UnitUtil.e(d, 3) : d;
    }

    public void setType(int i) {
        this.f = i;
        e();
    }
}
