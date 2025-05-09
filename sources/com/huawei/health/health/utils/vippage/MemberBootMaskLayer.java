package com.huawei.health.health.utils.vippage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/* loaded from: classes8.dex */
public class MemberBootMaskLayer extends RelativeLayout {
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public MemberBootMaskLayer(Context context) {
        super(context, null);
    }

    public MemberBootMaskLayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public MemberBootMaskLayer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
