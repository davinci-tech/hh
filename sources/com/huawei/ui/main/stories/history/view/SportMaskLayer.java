package com.huawei.ui.main.stories.history.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/* loaded from: classes9.dex */
public class SportMaskLayer extends RelativeLayout {
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public SportMaskLayer(Context context) {
        super(context, null);
    }

    public SportMaskLayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public SportMaskLayer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
