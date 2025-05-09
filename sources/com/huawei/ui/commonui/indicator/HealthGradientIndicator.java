package com.huawei.ui.commonui.indicator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes6.dex */
public class HealthGradientIndicator extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8846a;
    private HealthTextView b;
    private float c;
    private float d;
    private GradientCircle e;
    private HealthTextView h;

    public HealthGradientIndicator(Context context) {
        super(context);
        a(context);
    }

    public HealthGradientIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public HealthGradientIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.health_gradient_indicator_layout, this);
        this.b = (HealthTextView) inflate.findViewById(R.id.gradient_name_text);
        this.f8846a = (HealthTextView) inflate.findViewById(R.id.gradient_data_text);
        this.h = (HealthTextView) inflate.findViewById(R.id.gradient_unit_text);
        this.e = (GradientCircle) inflate.findViewById(R.id.gradient_circle);
    }

    public HealthGradientIndicator b(float f) {
        this.d = f;
        return this;
    }

    public HealthGradientIndicator d(String str) {
        this.b.setText(str);
        return this;
    }

    public HealthGradientIndicator b(String str) {
        this.h.setText(str);
        return this;
    }

    public HealthGradientIndicator d(float f, String str) {
        this.c = f;
        this.f8846a.setText(str);
        return this;
    }

    public void e() {
        this.e.c(this.c, this.d);
    }
}
