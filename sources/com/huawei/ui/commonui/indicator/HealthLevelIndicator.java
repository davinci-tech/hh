package com.huawei.ui.commonui.indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nmm;
import defpackage.nsk;
import defpackage.nsn;
import java.util.List;

/* loaded from: classes6.dex */
public class HealthLevelIndicator extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private boolean f8847a;
    private IndicatorCircle b;
    private HealthTextView c;
    private HealthTextView d;
    private LinearLayout e;
    private HealthTextView f;
    private HealthTextView j;

    public HealthLevelIndicator(Context context) {
        super(context);
        this.f8847a = true;
        c();
    }

    public HealthLevelIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8847a = true;
        cAX_(context, attributeSet);
        c();
    }

    public HealthLevelIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8847a = true;
        cAX_(context, attributeSet);
        c();
    }

    private void cAX_(Context context, AttributeSet attributeSet) {
        if (context == null || attributeSet == null) {
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.HealthLevelIndicator);
        this.f8847a = obtainStyledAttributes.getBoolean(R$styleable.HealthLevelIndicator_isInitIndicator, true);
        obtainStyledAttributes.recycle();
    }

    private void c() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.health_level_indicator_layout, this);
        IndicatorCircle indicatorCircle = (IndicatorCircle) inflate.findViewById(R.id.health_indicator_circle);
        this.b = indicatorCircle;
        indicatorCircle.d(this, this.f8847a);
        this.d = (HealthTextView) inflate.findViewById(R.id.level_name_text);
        this.c = (HealthTextView) inflate.findViewById(R.id.level_name_text_three);
        if (nsn.r()) {
            this.d.setVisibility(8);
            this.c.setVisibility(0);
            e(this.c, 20.0f);
        } else {
            this.c.setVisibility(8);
            this.d.setVisibility(0);
        }
        this.e = (LinearLayout) inflate.findViewById(R.id.health_indicator_level_layout);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.level_value_text);
        this.j = healthTextView;
        e(healthTextView, 70.0f);
        this.j.setTypeface(nsk.cKN_());
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.level_unit_text);
        this.f = healthTextView2;
        e(healthTextView2, 13.0f);
    }

    public void setAllLevelsData(List<nmm> list) {
        setAllLevelsData(list, null);
    }

    public void setAllLevelsData(List<nmm> list, int[] iArr) {
        if (list != null && iArr != null && list.size() != iArr.length) {
            throw new IllegalArgumentException("levelSize dose not equal to ratioDeltaSize! Please make sure deltaAngles and data have same size when calling setAllLevelsData()");
        }
        try {
            this.b.setData(list, iArr);
        } catch (CloneNotSupportedException e) {
            Log.e("HealthLevelIndicator", "CloneNotSupportedException happened: " + e.getMessage() + ", cause : " + e.getCause());
        }
    }

    public void setLevel(float f, String str) {
        this.b.setLevel(f);
        this.j.setText(str);
    }

    public void setLevel(float f, SpannableString spannableString) {
        this.b.setLevel(f);
        this.j.setText(spannableString);
    }

    void d(String str, int i) {
        this.d.setText(str);
        this.d.setTextColor(i);
        this.c.setText(str);
        this.c.setTextColor(i);
    }

    public void setLevelUnit(String str) {
        this.f.setText(str);
    }

    public void setLevelUnit(SpannableString spannableString) {
        this.f.setText(spannableString);
    }

    private void e(HealthTextView healthTextView, float f) {
        if (nsn.r()) {
            healthTextView.setTextSize(1, f);
        }
    }

    public void d(int i) {
        if (this.e.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.e.getLayoutParams();
            layoutParams.topMargin = i;
            this.e.setLayoutParams(layoutParams);
        }
    }
}
