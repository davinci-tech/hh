package com.huawei.health.sport.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import com.huawei.health.R;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import defpackage.nrr;

/* loaded from: classes8.dex */
public class FitnessCardView extends HealthCardView {

    /* renamed from: a, reason: collision with root package name */
    private Context f3001a;
    private HealthColumnSystem b;
    private float c;
    private Pair<Integer, Integer> d;
    private boolean e;
    private float j;

    public FitnessCardView(Context context) {
        this(context, null);
    }

    public FitnessCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FitnessCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = BaseActivity.getSafeRegionWidth();
        this.f3001a = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100089_res_0x7f0601b9, R.attr._2131100090_res_0x7f0601ba, R.attr._2131100787_res_0x7f060473});
        this.j = obtainStyledAttributes.getFloat(1, 21.0f);
        this.c = obtainStyledAttributes.getFloat(0, 9.0f);
        this.e = obtainStyledAttributes.getBoolean(2, false);
        obtainStyledAttributes.recycle();
    }

    private void d(Context context) {
        HealthColumnSystem healthColumnSystem = this.b;
        if (healthColumnSystem == null) {
            this.b = new HealthColumnSystem(this.f3001a, 1);
        } else {
            healthColumnSystem.e(context);
        }
    }

    @Override // androidx.cardview.widget.CardView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int dimension;
        d(this.f3001a);
        int f = this.b.f() / 4;
        if (f == 0) {
            f = 1;
        }
        if (f > 1) {
            dimension = (int) ((((this.f3001a.getResources().getDisplayMetrics().widthPixels - this.f3001a.getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e)) - this.f3001a.getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d)) - ((f - 1) * nrr.b(this.f3001a))) / f);
        } else {
            dimension = (int) ((((this.f3001a.getResources().getDisplayMetrics().widthPixels - this.f3001a.getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e)) - this.f3001a.getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d)) - ((Integer) this.d.first).intValue()) - ((Integer) this.d.second).intValue());
        }
        int i3 = (int) ((dimension * 9.0f) / 21.0f);
        if (this.e) {
            dimension = (int) ((this.j * i3) / this.c);
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(dimension, 1073741824), View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
    }

    public void setWidthWeight(float f) {
        this.j = f;
    }

    public void setHeightWeight(float f) {
        this.c = f;
    }

    public void setIsRatioWidth(boolean z) {
        this.e = z;
    }
}
