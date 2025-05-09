package com.huawei.health.suggestion.ui.fitness.module;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.customview.widget.ViewDragHelper;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;

/* loaded from: classes4.dex */
public class SlideDownLayout extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private OnSlidingListener f3186a;
    private int b;
    private ViewDragHelper c;
    private Point d;
    private View e;

    private void c(Context context) {
    }

    public SlideDownLayout(Context context) {
        super(context);
        c(context);
    }

    public SlideDownLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c(context);
    }

    public SlideDownLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        c(context);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.e = getChildAt(0);
        this.c = ViewDragHelper.create(this, new ViewDragHelper.Callback() { // from class: com.huawei.health.suggestion.ui.fitness.module.SlideDownLayout.1
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public boolean tryCaptureView(View view, int i) {
                return true;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionHorizontal(View view, int i, int i2) {
                return SlideDownLayout.this.e.getLeft();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionVertical(View view, int i, int i2) {
                if (i > 0) {
                    if (SlideDownLayout.this.f3186a != null) {
                        SlideDownLayout.this.f3186a.onSliding(i);
                    }
                    return i;
                }
                return SlideDownLayout.this.e.getTop();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewReleased(View view, float f, float f2) {
                if (Math.abs(SlideDownLayout.this.e.getTop() - SlideDownLayout.this.d.y) < SlideDownLayout.this.b / 3) {
                    SlideDownLayout.this.c.settleCapturedViewAt(SlideDownLayout.this.d.x, SlideDownLayout.this.d.y);
                } else {
                    SlideDownLayout.this.c.settleCapturedViewAt(SlideDownLayout.this.d.x, SlideDownLayout.this.b);
                }
                SlideDownLayout.this.postInvalidate();
            }
        });
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.b = i2;
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.d = new Point(this.e.getLeft(), this.e.getTop());
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            return this.c.shouldInterceptTouchEvent(motionEvent);
        } catch (Exception e) {
            LogUtil.b("Suggestion_SlideDownLayout", LogAnonymous.b((Throwable) e));
            return false;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            this.c.processTouchEvent(motionEvent);
            return true;
        } catch (Exception e) {
            LogUtil.b("Suggestion_SlideDownLayout", LogAnonymous.b((Throwable) e));
            return false;
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.c.continueSettling(true)) {
            postInvalidate();
            OnSlidingListener onSlidingListener = this.f3186a;
            if (onSlidingListener != null) {
                onSlidingListener.onSliding(this.e.getTop());
                if (this.e.getTop() == this.b) {
                    this.f3186a.onSlidingFinish(true);
                } else if (this.e.getTop() == 0) {
                    this.f3186a.onSlidingFinish(false);
                }
            }
        }
    }

    public void setOnSlidingListener(OnSlidingListener onSlidingListener) {
        this.f3186a = onSlidingListener;
    }
}
