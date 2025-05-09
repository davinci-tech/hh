package com.huawei.ui.commonui.swiperefreshlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.huawei.uikit.phone.hwswiperefreshlayout.widget.HwSwipeRefreshLayout;

/* loaded from: classes6.dex */
public class HealthSwipeRefreshLayout extends HwSwipeRefreshLayout {
    private boolean c;

    public HealthSwipeRefreshLayout(Context context) {
        super(context);
        this.c = false;
    }

    public HealthSwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = false;
    }

    public HealthSwipeRefreshLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = false;
    }

    @Override // com.huawei.uikit.hwswiperefreshlayout.widget.HwSwipeRefreshLayout, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.c) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void setInterceptTouchEvent(boolean z) {
        this.c = z;
    }
}
