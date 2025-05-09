package com.huawei.health.suggestion.ui.fitness.module;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.customview.widget.ViewDragHelper;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nrr;
import health.compact.a.LanguageUtil;

/* loaded from: classes8.dex */
public class LockScreenView extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private OnSlidingListener f3180a;
    private int b;
    private Point c;
    private ViewDragHelper d;
    private HealthTextView e;

    public LockScreenView(Context context) {
        super(context);
        e(context);
    }

    public LockScreenView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        e(context);
    }

    public LockScreenView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        e(context);
    }

    private void e(Context context) {
        HealthTextView healthTextView = new HealthTextView(context);
        this.e = healthTextView;
        healthTextView.setLockText(true);
        addView(this.e);
        if (!(this.e.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            LogUtil.h("Suggestion_LockScreenView", "mDragView.getLayoutParams() not instanceof LayoutParams");
            return;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.e.getLayoutParams();
        layoutParams.addRule(14);
        layoutParams.addRule(12);
        int dimension = (int) getResources().getDimension(LanguageUtil.h(context) ? R.dimen._2131365028_res_0x7f0a0ca4 : R.dimen._2131363083_res_0x7f0a050b);
        layoutParams.bottomMargin = dimension;
        this.e.setText(getResources().getString(R.string._2130848388_res_0x7f022a84));
        this.e.setPadding(nrr.e(context, 200.0f), dimension, nrr.e(context, 200.0f), (int) getResources().getDimension(R.dimen._2131365027_res_0x7f0a0ca3));
        this.e.setTextSize(0, getResources().getDimension(R.dimen._2131365029_res_0x7f0a0ca5));
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.d = ViewDragHelper.create(this, new ViewDragHelper.Callback() { // from class: com.huawei.health.suggestion.ui.fitness.module.LockScreenView.4
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public boolean tryCaptureView(View view, int i) {
                return true;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionHorizontal(View view, int i, int i2) {
                int paddingLeft = LockScreenView.this.getPaddingLeft();
                int paddingLeft2 = LockScreenView.this.e.getPaddingLeft();
                int width = LockScreenView.this.getWidth();
                int width2 = LockScreenView.this.e.getWidth();
                int min = Math.min(Math.max(i, (paddingLeft - paddingLeft2) + 10), ((width - width2) + LockScreenView.this.e.getPaddingRight()) - 10);
                LockScreenView.this.setAlpha(1.0f - Math.abs(((min - r5.c.x) * 1.0f) / LockScreenView.this.c.x));
                return min;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionVertical(View view, int i, int i2) {
                return LockScreenView.this.e.getTop();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewReleased(View view, float f, float f2) {
                LogUtil.a("LockScreenView", "--Math.abs(mDragView.getLeft() - mAutoBackPoint.x): ", Integer.valueOf(Math.abs(LockScreenView.this.e.getLeft() - LockScreenView.this.c.x)), "--", Integer.valueOf((LockScreenView.this.b / 4) - ((LockScreenView.this.e.getWidth() - (LockScreenView.this.e.getPaddingLeft() * 2)) / 2)));
                if (Math.abs(LockScreenView.this.e.getLeft() - LockScreenView.this.c.x) < (LockScreenView.this.b / 4) - ((LockScreenView.this.e.getWidth() - (LockScreenView.this.e.getPaddingLeft() * 2)) / 2)) {
                    LockScreenView.this.d.settleCapturedViewAt(LockScreenView.this.c.x, LockScreenView.this.c.y);
                } else if (LockScreenView.this.c.x < LockScreenView.this.e.getLeft()) {
                    ViewDragHelper viewDragHelper = LockScreenView.this.d;
                    int i = LockScreenView.this.b;
                    int width = LockScreenView.this.e.getWidth();
                    viewDragHelper.settleCapturedViewAt((i - width) + LockScreenView.this.e.getPaddingLeft(), LockScreenView.this.c.y);
                } else {
                    LockScreenView.this.d.settleCapturedViewAt(-LockScreenView.this.e.getPaddingLeft(), LockScreenView.this.c.y);
                }
                LockScreenView.this.postInvalidate();
            }
        });
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.b = i;
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        Point point = new Point(this.e.getLeft(), this.e.getTop());
        this.c = point;
        LogUtil.a("LockScreenView", "mAutoBackPoint.x:", Integer.valueOf(point.x));
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.d.shouldInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.d.processTouchEvent(motionEvent);
        return true;
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.d.continueSettling(true)) {
            postInvalidate();
            int paddingLeft = getPaddingLeft();
            int paddingLeft2 = this.e.getPaddingLeft();
            int width = getWidth();
            int width2 = this.e.getWidth();
            setAlpha(1.0f - Math.abs(((Math.min(Math.max(this.e.getLeft(), paddingLeft - paddingLeft2), (width - width2) + this.e.getPaddingRight()) - this.c.x) * 1.0f) / this.c.x));
            OnSlidingListener onSlidingListener = this.f3180a;
            if (onSlidingListener != null) {
                onSlidingListener.onSliding(this.e.getLeft());
                if (this.e.getLeft() == (this.b - this.e.getWidth()) + this.e.getPaddingLeft() || this.e.getLeft() == (-this.e.getPaddingLeft())) {
                    this.f3180a.onSlidingFinish(true);
                }
            }
        }
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        if (i == 0) {
            setAlpha(1.0f);
        }
    }
}
