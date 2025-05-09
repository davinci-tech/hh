package com.huawei.ui.commonui.togglebutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.uikit.phone.hwtoggle.widget.HwToggleButton;

/* loaded from: classes6.dex */
public class HealthToggleButton extends HwToggleButton {

    /* renamed from: a, reason: collision with root package name */
    private final CharSequence f8963a;
    private boolean d;
    private final CharSequence e;

    public HealthToggleButton(Context context) {
        super(context);
        this.d = false;
        this.e = getTextOn();
        this.f8963a = getTextOff();
        cGX_(context, null);
    }

    public HealthToggleButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = false;
        this.e = getTextOn();
        this.f8963a = getTextOff();
        cGX_(context, attributeSet);
    }

    public HealthToggleButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = false;
        this.e = getTextOn();
        this.f8963a = getTextOff();
        cGX_(context, attributeSet);
    }

    private void cGX_(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.HealthToggleButton);
        boolean z = obtainStyledAttributes.getBoolean(R$styleable.HealthToggleButton_showOnOffText, false);
        this.d = z;
        d(z);
        obtainStyledAttributes.recycle();
    }

    private void d(boolean z) {
        this.d = z;
        if (z) {
            setTextOn(this.e);
            setTextOff(this.f8963a);
        } else {
            setTextOn(null);
            setTextOff(null);
        }
        requestLayout();
    }
}
