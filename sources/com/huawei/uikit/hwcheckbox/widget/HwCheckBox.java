package com.huawei.uikit.hwcheckbox.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import com.huawei.health.R;
import defpackage.smr;

/* loaded from: classes7.dex */
public class HwCheckBox extends CheckBox {
    public HwCheckBox(Context context) {
        this(context, null);
    }

    private static Context c(Context context, int i) {
        return smr.b(context, i, 2131952135);
    }

    public HwCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100254_res_0x7f06025e);
    }

    public HwCheckBox(Context context, AttributeSet attributeSet, int i) {
        super(c(context, i), attributeSet, i);
        setDefaultFocusHighlightEnabled(false);
    }
}
