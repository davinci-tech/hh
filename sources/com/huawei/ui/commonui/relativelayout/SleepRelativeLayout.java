package com.huawei.ui.commonui.relativelayout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/* loaded from: classes9.dex */
public class SleepRelativeLayout extends RelativeLayout {
    OnCallback b;

    public interface OnCallback {
        void onLayout(boolean z, int i, int i2, int i3, int i4);
    }

    public SleepRelativeLayout(Context context) {
        super(context);
    }

    public SleepRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SleepRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        OnCallback onCallback = this.b;
        if (onCallback != null) {
            onCallback.onLayout(z, i, i2, i3, i4);
        }
    }
}
