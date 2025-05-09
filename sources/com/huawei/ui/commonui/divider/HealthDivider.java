package com.huawei.ui.commonui.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$styleable;

/* loaded from: classes6.dex */
public class HealthDivider extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private View f8825a;
    private View b;
    private int c;

    public HealthDivider(Context context) {
        this(context, null);
    }

    public HealthDivider(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes;
        this.c = 0;
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.health_common_divider_layout, this);
        if (attributeSet != null && (obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.health_divider)) != null) {
            this.c = obtainStyledAttributes.getInteger(R$styleable.health_divider_dividerType, 0);
            obtainStyledAttributes.recycle();
        }
        if (this.c == 1) {
            this.b = inflate.findViewById(R.id.health_divider_splitter);
            this.f8825a = inflate.findViewById(R.id.health_divider_line);
            this.b.setVisibility(0);
            this.f8825a.setVisibility(8);
        }
    }

    public void setDividerBackground(int i) {
        View view = this.f8825a;
        if (view != null) {
            view.setBackgroundColor(i);
        }
        View view2 = this.b;
        if (view2 != null) {
            view2.setBackgroundColor(i);
        }
    }
}
