package com.github.mikephil.charting.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import com.github.mikephil.charting.animation.Easing;

/* loaded from: classes2.dex */
public class ChartAnimator {
    private ValueAnimator.AnimatorUpdateListener mListener;
    protected float mPhaseY = 1.0f;
    protected float mPhaseX = 1.0f;

    public ChartAnimator() {
    }

    public ChartAnimator(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.mListener = animatorUpdateListener;
    }

    private ObjectAnimator xAnimator(int i, Easing.EasingFunction easingFunction) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseX", 0.0f, 1.0f);
        ofFloat.setInterpolator(easingFunction);
        ofFloat.setDuration(i);
        return ofFloat;
    }

    private ObjectAnimator yAnimator(int i, Easing.EasingFunction easingFunction) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseY", 0.0f, 1.0f);
        ofFloat.setInterpolator(easingFunction);
        ofFloat.setDuration(i);
        return ofFloat;
    }

    public void animateX(int i) {
        animateX(i, Easing.Linear);
    }

    public void animateX(int i, Easing.EasingFunction easingFunction) {
        ObjectAnimator xAnimator = xAnimator(i, easingFunction);
        xAnimator.addUpdateListener(this.mListener);
        xAnimator.start();
    }

    public void animateXY(int i, int i2) {
        animateXY(i, i2, Easing.Linear, Easing.Linear);
    }

    public void animateXY(int i, int i2, Easing.EasingFunction easingFunction) {
        ObjectAnimator xAnimator = xAnimator(i, easingFunction);
        ObjectAnimator yAnimator = yAnimator(i2, easingFunction);
        if (i > i2) {
            xAnimator.addUpdateListener(this.mListener);
        } else {
            yAnimator.addUpdateListener(this.mListener);
        }
        xAnimator.start();
        yAnimator.start();
    }

    public void animateXY(int i, int i2, Easing.EasingFunction easingFunction, Easing.EasingFunction easingFunction2) {
        ObjectAnimator xAnimator = xAnimator(i, easingFunction);
        ObjectAnimator yAnimator = yAnimator(i2, easingFunction2);
        if (i > i2) {
            xAnimator.addUpdateListener(this.mListener);
        } else {
            yAnimator.addUpdateListener(this.mListener);
        }
        xAnimator.start();
        yAnimator.start();
    }

    public void animateY(int i) {
        animateY(i, Easing.Linear);
    }

    public void animateY(int i, Easing.EasingFunction easingFunction) {
        ObjectAnimator yAnimator = yAnimator(i, easingFunction);
        yAnimator.addUpdateListener(this.mListener);
        yAnimator.start();
    }

    public float getPhaseY() {
        return this.mPhaseY;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x000b, code lost:
    
        if (r3 < 0.0f) goto L4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setPhaseY(float r3) {
        /*
            r2 = this;
            r0 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 <= 0) goto L8
        L6:
            r3 = r0
            goto Le
        L8:
            r0 = 0
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 >= 0) goto Le
            goto L6
        Le:
            r2.mPhaseY = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.animation.ChartAnimator.setPhaseY(float):void");
    }

    public float getPhaseX() {
        return this.mPhaseX;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x000b, code lost:
    
        if (r3 < 0.0f) goto L4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setPhaseX(float r3) {
        /*
            r2 = this;
            r0 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 <= 0) goto L8
        L6:
            r3 = r0
            goto Le
        L8:
            r0 = 0
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 >= 0) goto Le
            goto L6
        Le:
            r2.mPhaseX = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.animation.ChartAnimator.setPhaseX(float):void");
    }
}
