package com.huawei.uikit.phone.hwswiperefreshlayout.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.uikit.phone.hwunifiedinteract.widget.HwGenericEventDetector;

/* loaded from: classes7.dex */
public class HwSwipeRefreshLayout extends com.huawei.uikit.hwswiperefreshlayout.widget.HwSwipeRefreshLayout {
    public HwSwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public HwSwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.uikit.hwswiperefreshlayout.widget.HwSwipeRefreshLayout
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public HwGenericEventDetector d() {
        return new HwGenericEventDetector(getContext());
    }

    public HwSwipeRefreshLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
