package com.huawei.health.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/* loaded from: classes8.dex */
public class MemberGuideMaskLayer extends RelativeLayout {
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public MemberGuideMaskLayer(Context context) {
        super(context, null);
    }

    public MemberGuideMaskLayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public MemberGuideMaskLayer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
