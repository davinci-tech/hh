package com.huawei.ui.commonui.bannerlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsy;

/* loaded from: classes6.dex */
public class HealthBannerLayout extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8764a;
    private String b;
    private String c;
    private HealthTextView d;
    private HealthTextView e;
    private String j;

    public HealthBannerLayout(Context context) {
        this(context, null);
    }

    public HealthBannerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public HealthBannerLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, -1);
    }

    public HealthBannerLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.sub_title);
        if (obtainStyledAttributes != null) {
            this.j = obtainStyledAttributes.getString(R$styleable.banner_bannerTitle);
            this.b = obtainStyledAttributes.getString(R$styleable.banner_leftButtonText);
            this.c = obtainStyledAttributes.getString(R$styleable.banner_rightButtonText);
            obtainStyledAttributes.recycle();
        }
        d(context);
    }

    private void d(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_health_banner, this);
        this.f8764a = (HealthTextView) inflate.findViewById(R.id.banner_title);
        this.d = (HealthTextView) inflate.findViewById(R.id.banner_left_button);
        this.e = (HealthTextView) inflate.findViewById(R.id.banner_right_button);
        nsy.cMr_(this.f8764a, this.j);
        nsy.cMr_(this.d, this.b);
        nsy.cMr_(this.e, this.c);
    }

    public void setTitle(String str) {
        nsy.cMr_(this.f8764a, str);
    }

    public void setLeftButtonText(String str) {
        nsy.cMr_(this.d, str);
    }

    public void setRightButtonText(String str) {
        nsy.cMr_(this.e, str);
    }

    public void setLeftButtonClickListener(View.OnClickListener onClickListener) {
        nsy.cMn_(this.d, onClickListener);
    }

    public void setRightButtonClickListener(View.OnClickListener onClickListener) {
        nsy.cMn_(this.e, onClickListener);
    }
}
