package com.huawei.ui.main.stories.fitness.views.coresleep;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.constraintlayout.widget.ConstraintLayout;

/* loaded from: classes6.dex */
public class SleepBootMaskingLayerView extends ConstraintLayout {
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public SleepBootMaskingLayerView(Context context) {
        super(context, null);
    }

    public SleepBootMaskingLayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public SleepBootMaskingLayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
