package com.huawei.uikit.phone.hwimageview.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.uikit.phone.hwunifiedinteract.widget.HwCompoundEventDetector;

/* loaded from: classes9.dex */
public class HwImageView extends com.huawei.uikit.hwimageview.widget.HwImageView {
    public HwImageView(Context context) {
        this(context, null);
    }

    public HwImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.uikit.hwimageview.widget.HwImageView
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public HwCompoundEventDetector c() {
        return new HwCompoundEventDetector(getContext());
    }

    public HwImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
