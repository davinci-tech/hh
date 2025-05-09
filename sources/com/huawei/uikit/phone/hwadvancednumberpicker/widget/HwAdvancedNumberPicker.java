package com.huawei.uikit.phone.hwadvancednumberpicker.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.uikit.phone.hwunifiedinteract.widget.HwGenericEventDetector;

/* loaded from: classes7.dex */
public class HwAdvancedNumberPicker extends com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker {
    public HwAdvancedNumberPicker(Context context) {
        this(context, null);
    }

    public HwAdvancedNumberPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public HwGenericEventDetector e() {
        return new HwGenericEventDetector(getContext());
    }

    public HwAdvancedNumberPicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
