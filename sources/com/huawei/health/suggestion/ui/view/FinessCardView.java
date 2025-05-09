package com.huawei.health.suggestion.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;

/* loaded from: classes8.dex */
public class FinessCardView extends HealthCardView {

    /* renamed from: a, reason: collision with root package name */
    private HealthColumnSystem f3418a;
    private float b;
    private float c;
    private boolean d;
    private Context e;

    public FinessCardView(Context context) {
        this(context, null);
    }

    public FinessCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FinessCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100089_res_0x7f0601b9, R.attr._2131100090_res_0x7f0601ba, R.attr._2131100787_res_0x7f060473});
        this.c = obtainStyledAttributes.getFloat(1, 21.0f);
        this.b = obtainStyledAttributes.getFloat(0, 9.0f);
        this.d = obtainStyledAttributes.getBoolean(2, false);
        obtainStyledAttributes.recycle();
    }

    private void c(Context context) {
        HealthColumnSystem healthColumnSystem = this.f3418a;
        if (healthColumnSystem == null) {
            this.f3418a = new HealthColumnSystem(this.e, 1);
        } else {
            healthColumnSystem.e(context);
        }
    }

    @Override // androidx.cardview.widget.CardView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        c(this.e);
        int d = ((int) this.f3418a.d(4)) + (((this.f3418a.c() * 2) - (this.e.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e) * 2)) / (this.f3418a.f() / 4));
        int i3 = (int) ((d * 9.0f) / 21.0f);
        LogUtil.h("FinessCardView", "onMeasure : ", "itemDeviceWidth : ", Integer.valueOf(d), " height : ", Integer.valueOf(i3));
        if (this.d) {
            d = (int) ((this.c * i3) / this.b);
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(d, 1073741824), View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
    }
}
