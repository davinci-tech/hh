package com.huawei.uikit.hwradiobutton.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;
import com.huawei.health.R;
import defpackage.smr;

/* loaded from: classes7.dex */
public class HwRadioButton extends RadioButton {
    public HwRadioButton(Context context) {
        this(context, null);
    }

    private static Context c(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwRadioButton);
    }

    public HwRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100477_res_0x7f06033d);
    }

    public HwRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(c(context, i), attributeSet, i);
        setDefaultFocusHighlightEnabled(false);
    }
}
