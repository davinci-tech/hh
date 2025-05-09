package com.huawei.uikit.hwunifiedinteract.widget;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

/* loaded from: classes7.dex */
public class HwCompoundEventDetector {
    private OnZoomEventListener b = null;
    private OnMultiSelectListener d = null;
    private View c = null;

    public interface OnMultiSelectListener {
        boolean onCancel(MotionEvent motionEvent);

        boolean onSelectContinuous(boolean z, MotionEvent motionEvent);

        boolean onSelectDiscrete(MotionEvent motionEvent);
    }

    public interface OnZoomEventListener {
        boolean onZoom(float f, MotionEvent motionEvent);
    }

    public HwCompoundEventDetector(Context context) {
    }

    public void b() {
    }

    public OnZoomEventListener c() {
        return this.b;
    }

    public boolean eim_(MotionEvent motionEvent) {
        if (!motionEvent.isFromSource(2)) {
            return false;
        }
        int action = motionEvent.getAction();
        if ((motionEvent.getMetaState() & 4096) != 0 && this.b != null && action == 8) {
            float axisValue = motionEvent.getAxisValue(10);
            if (Float.compare(axisValue, 0.0f) == 0) {
                axisValue = motionEvent.getAxisValue(9);
            }
            if (Float.compare(axisValue, 0.0f) != 0 && this.b.onZoom(axisValue, motionEvent)) {
                return true;
            }
        }
        if (action == 11 && motionEvent.getActionButton() == 1 && this.d != null) {
            if ((motionEvent.getMetaState() & 4096) != 0 && this.d.onSelectDiscrete(motionEvent)) {
                return true;
            }
            if ((motionEvent.getMetaState() & 1) != 0 && this.d.onSelectContinuous(false, motionEvent)) {
                return true;
            }
        }
        return false;
    }

    public boolean ein_(int i, KeyEvent keyEvent) {
        return false;
    }

    public void eio_(View view, OnMultiSelectListener onMultiSelectListener) {
        this.c = view;
        this.d = onMultiSelectListener;
    }

    public void eip_(View view, OnZoomEventListener onZoomEventListener) {
        this.c = view;
        this.b = onZoomEventListener;
    }
}
