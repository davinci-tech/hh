package com.huawei.uikit.phone.hwseekbar.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.uikit.phone.hwunifiedinteract.widget.HwGenericEventDetector;

/* loaded from: classes7.dex */
public class HwSeekBar extends com.huawei.uikit.hwseekbar.widget.HwSeekBar {
    public HwSeekBar(Context context) {
        super(context);
    }

    public HwSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.uikit.hwseekbar.widget.HwSeekBar
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public HwGenericEventDetector createGenericEventDetector() {
        return new HwGenericEventDetector(getContext());
    }

    public HwSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
