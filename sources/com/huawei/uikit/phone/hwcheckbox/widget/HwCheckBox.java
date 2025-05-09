package com.huawei.uikit.phone.hwcheckbox.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.huawei.health.R;
import defpackage.smp;

/* loaded from: classes7.dex */
public class HwCheckBox extends com.huawei.uikit.hwcheckbox.widget.HwCheckBox {
    private boolean d;
    private boolean e;

    public HwCheckBox(Context context) {
        this(context, null);
    }

    protected boolean getIsNeedAuxiliaryHelper() {
        return Float.compare(smp.a(getContext()), 1.75f) >= 0 && this.e;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.d) {
            setGravity(8388659);
        } else {
            setGravity(8388627);
        }
        super.onDraw(canvas);
    }

    public HwCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100254_res_0x7f06025e);
    }

    public HwCheckBox(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100189_res_0x7f06021d, R.attr._2131100191_res_0x7f06021f, R.attr._2131100338_res_0x7f0602b2}, i, 0);
        this.d = obtainStyledAttributes.getBoolean(0, false);
        this.e = obtainStyledAttributes.getBoolean(1, true);
        obtainStyledAttributes.recycle();
    }
}
