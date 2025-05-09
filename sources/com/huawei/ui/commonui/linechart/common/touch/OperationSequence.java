package com.huawei.ui.commonui.linechart.common.touch;

import android.view.MotionEvent;

/* loaded from: classes6.dex */
public interface OperationSequence {
    boolean canSeqStart(MotionEvent motionEvent);

    boolean isSeqInterrupted();

    void onTouchEvent(MotionEvent motionEvent);

    void prepare();

    void release();
}
