package com.huawei.ui.commonui.horizontalscrollview;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.uikit.phone.hwhorizontalscrollview.widget.HwHorizontalScrollView;

/* loaded from: classes6.dex */
public class HealthHorizontalScrollView extends HwHorizontalScrollView {
    private OnHorizontalScrollListener d;

    public interface OnHorizontalScrollListener {
        void onHorizontalScrolled(HealthHorizontalScrollView healthHorizontalScrollView, int i, int i2, int i3, int i4);
    }

    public HealthHorizontalScrollView(Context context) {
        super(context);
    }

    public HealthHorizontalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HealthHorizontalScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        OnHorizontalScrollListener onHorizontalScrollListener = this.d;
        if (onHorizontalScrollListener != null) {
            onHorizontalScrollListener.onHorizontalScrolled(this, i, i2, i3, i4);
        }
    }

    public void setOnHorizontalScrollListener(OnHorizontalScrollListener onHorizontalScrollListener) {
        this.d = onHorizontalScrollListener;
    }
}
