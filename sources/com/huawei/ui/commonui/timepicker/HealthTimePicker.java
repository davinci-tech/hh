package com.huawei.ui.commonui.timepicker;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.ui.commonui.R$color;
import com.huawei.uikit.hwtimepicker.widget.HwTimePicker;

/* loaded from: classes6.dex */
public class HealthTimePicker extends HwTimePicker {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HealthTimePicker(Context context) {
        this(context, null);
        d(context);
    }

    public HealthTimePicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        d(context);
    }

    public HealthTimePicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        d(context);
    }

    private void d(Context context) {
        setSpinnersSelectorPaintColor(context.getResources().getColor(R$color.common_colorAccent));
        setIsMinuteIntervalFiveMinute(false);
    }

    public void setTime(int i, int i2) {
        d(0, 0, 0, i, i2);
    }
}
