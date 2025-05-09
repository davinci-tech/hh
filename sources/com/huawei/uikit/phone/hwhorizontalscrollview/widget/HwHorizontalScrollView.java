package com.huawei.uikit.phone.hwhorizontalscrollview.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.uikit.phone.hwunifiedinteract.widget.HwGenericEventDetector;

/* loaded from: classes7.dex */
public class HwHorizontalScrollView extends com.huawei.uikit.hwhorizontalscrollview.widget.HwHorizontalScrollView {
    public HwHorizontalScrollView(Context context) {
        this(context, null);
    }

    public HwHorizontalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.uikit.hwhorizontalscrollview.widget.HwHorizontalScrollView
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public HwGenericEventDetector c() {
        return new HwGenericEventDetector(getContext());
    }

    public HwHorizontalScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
