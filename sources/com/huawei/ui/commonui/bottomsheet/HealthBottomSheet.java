package com.huawei.ui.commonui.bottomsheet;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet;
import health.compact.a.util.LogUtil;

/* loaded from: classes6.dex */
public class HealthBottomSheet extends HwBottomSheet {
    private boolean d;

    public HealthBottomSheet(Context context) {
        super(context);
        this.d = true;
    }

    public HealthBottomSheet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = true;
    }

    public HealthBottomSheet(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = true;
    }

    @Override // com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet, android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!a(motionEvent.getY()) || this.d) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    private boolean a(float f) {
        View indicateView = getIndicateView();
        if (indicateView == null) {
            LogUtil.d("HealthBottomSheet", "indicateView is null");
            return false;
        }
        int[] iArr = new int[2];
        indicateView.getLocationOnScreen(iArr);
        int i = iArr[1];
        return f >= ((float) i) && f < ((float) (i + indicateView.getHeight()));
    }

    public void setIndicateViewClickable(boolean z) {
        getIndicateView().setClickable(z);
    }

    public void setIsOnTop(boolean z) {
        this.d = z;
    }
}
