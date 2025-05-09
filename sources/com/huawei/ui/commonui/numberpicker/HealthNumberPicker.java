package com.huawei.ui.commonui.numberpicker;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker;
import com.huawei.uikit.phone.hwadvancednumberpicker.widget.HwAdvancedNumberPicker;

/* loaded from: classes6.dex */
public class HealthNumberPicker extends HwAdvancedNumberPicker {

    public interface OnPickedListener {
        void onValuePicked(int i, int i2);
    }

    public HealthNumberPicker(Context context) {
        super(context);
        a(context);
    }

    public HealthNumberPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        setOnColorChangeListener(new HwAdvancedNumberPicker.OnColorChangeListener() { // from class: com.huawei.ui.commonui.numberpicker.HealthNumberPicker.3
            @Override // com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker.OnColorChangeListener
            public void onColorChange(com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker hwAdvancedNumberPicker) {
                LogUtil.c("HealthNumberPicker", "onColorChange");
            }
        });
        setSelectorPaintColor(context.getResources().getColor(R$color.common_colorAccent));
        setSlaverPaintColor(context.getResources().getColor(R$color.textColorPrimary));
    }

    public void setOnValuePickedListener(final OnPickedListener onPickedListener) {
        super.setOnValueChangedListener(new HwAdvancedNumberPicker.OnValueChangeListener() { // from class: com.huawei.ui.commonui.numberpicker.HealthNumberPicker.1
            @Override // com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker.OnValueChangeListener
            public void onValueChange(com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker hwAdvancedNumberPicker, int i, int i2) {
                OnPickedListener onPickedListener2 = onPickedListener;
                if (onPickedListener2 != null) {
                    onPickedListener2.onValuePicked(i, i2);
                }
            }
        });
    }

    @Override // com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker
    public void setDisplayedValues(String[] strArr) {
        if (getValue() < getMinValue()) {
            setValue(getMinValue());
        }
        super.setDisplayedValues(strArr);
    }

    public void a(float f, float f2) {
        this.k = f;
        this.r = f2;
    }

    public void setSelectorElementAndTextGapHeight(int i, int i2) {
        this.n = i;
        this.l = i2;
    }
}
