package com.huawei.uikit.hwunifiedinteract.widget;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/* loaded from: classes7.dex */
public class HwGenericEventDetector {
    private float f;
    private int i;
    private float j;
    private View m;
    private OnChangePageListener e = null;

    /* renamed from: a, reason: collision with root package name */
    private OnChangeProgressListener f10771a = null;
    private OnScrollListener b = null;
    private float c = -1.0f;
    private float d = -1.0f;
    private float g = 0.0f;
    private float h = 0.0f;
    private float n = 1.0f;

    public interface OnChangePageListener {
        boolean onChangePage(float f, MotionEvent motionEvent);
    }

    public interface OnChangeProgressListener {
        boolean onChangeProgress(int i, int i2, MotionEvent motionEvent);
    }

    /* loaded from: classes9.dex */
    public interface OnChangeProgressListener2 extends OnChangeProgressListener {
        boolean onBegin();

        boolean onEnd(float f);
    }

    public interface OnScrollListener {
        boolean onScrollBy(float f, float f2, MotionEvent motionEvent);
    }

    /* loaded from: classes9.dex */
    public interface OnScrollListener2 extends OnScrollListener {
        boolean onBegin();

        boolean onEnd(float f);
    }

    public HwGenericEventDetector(Context context) {
        this.f = 0.0f;
        this.j = 0.0f;
        this.i = 0;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f = viewConfiguration.getScaledHorizontalScrollFactor();
        this.j = viewConfiguration.getScaledVerticalScrollFactor();
        this.i = viewConfiguration.getScaledTouchSlop();
    }

    private boolean eiq_(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        float f = this.c;
        float f2 = this.g;
        if (f - f2 > x || x > f + f2) {
            return true;
        }
        float f3 = this.d;
        return f3 - f2 > y || y > f3 + f2;
    }

    public float a() {
        return 0.0f;
    }

    public void a(int i) {
        if (i == 0) {
            this.n = 1.0f;
        } else if (i == 2) {
            this.n = 0.6f;
        } else {
            this.n = 1.0f;
        }
    }

    public void a(OnChangeProgressListener onChangeProgressListener) {
        this.f10771a = onChangeProgressListener;
    }

    public float b() {
        return this.n;
    }

    public void b(OnChangePageListener onChangePageListener) {
        this.e = onChangePageListener;
    }

    public void b(HwRotaryConverter hwRotaryConverter) {
        Log.d("HwGenericEventDetector", "Implement setRotaryConverter in the watch package.");
    }

    public float c() {
        return this.f * this.n;
    }

    public float d() {
        return this.j * this.n;
    }

    public void e(float f) {
        this.n = f;
    }

    public boolean eir_(MotionEvent motionEvent) {
        if (this.b == null) {
            return false;
        }
        if (Float.compare(this.c, -1.0f) == 0 && Float.compare(this.d, -1.0f) == 0) {
            return false;
        }
        if (!eiq_(motionEvent)) {
            return eis_(motionEvent);
        }
        this.c = -1.0f;
        this.d = -1.0f;
        return false;
    }

    public boolean eis_(MotionEvent motionEvent) {
        if (motionEvent.isFromSource(2) && motionEvent.getAction() == 8) {
            float axisValue = motionEvent.getAxisValue(10);
            float axisValue2 = motionEvent.getAxisValue(9);
            if (Float.compare(axisValue, 0.0f) == 0 && Float.compare(axisValue2, 0.0f) == 0) {
                return false;
            }
            float f = Float.compare(axisValue, 0.0f) == 0 ? -axisValue2 : axisValue;
            OnChangePageListener onChangePageListener = this.e;
            if (onChangePageListener != null && onChangePageListener.onChangePage(f, motionEvent)) {
                return true;
            }
            OnChangeProgressListener onChangeProgressListener = this.f10771a;
            if (onChangeProgressListener != null && onChangeProgressListener.onChangeProgress((int) (-axisValue), (int) axisValue2, motionEvent)) {
                return true;
            }
            if (this.b != null) {
                if (this.b.onScrollBy(Math.round(axisValue * c()), Math.round((-axisValue2) * d()), motionEvent)) {
                    if (this.c < 0.0f || this.d < 0.0f) {
                        this.c = motionEvent.getX();
                        this.d = motionEvent.getY();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public void eit_(View view, OnScrollListener onScrollListener) {
        this.b = onScrollListener;
        this.m = view;
    }
}
