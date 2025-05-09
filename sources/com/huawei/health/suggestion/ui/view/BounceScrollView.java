package com.huawei.health.suggestion.ui.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes4.dex */
public class BounceScrollView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private float f3414a;
    private boolean b;
    private boolean c;
    private ViewGroup d;
    private boolean e;
    private float f;
    private float i;
    private Rect j;

    public BounceScrollView(Context context) {
        super(context);
        this.j = new Rect();
        this.e = false;
        this.c = false;
        this.b = false;
        e();
    }

    public BounceScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = new Rect();
        this.e = false;
        this.c = false;
        this.b = false;
        e();
    }

    private void e() {
        setOverScrollMode(2);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() <= 0 || !(getChildAt(0) instanceof ViewGroup)) {
            return;
        }
        this.d = (ViewGroup) getChildAt(0);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        ViewGroup viewGroup = this.d;
        if (viewGroup == null) {
            return;
        }
        this.j.set(viewGroup.getLeft(), this.d.getTop(), this.d.getRight(), this.d.getBottom());
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0035, code lost:
    
        if (r0 != 3) goto L33;
     */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            android.view.ViewGroup r0 = r5.d
            if (r0 != 0) goto L9
            boolean r6 = super.dispatchTouchEvent(r6)
            return r6
        L9:
            r5.a()
            float r0 = r6.getX()
            android.graphics.Rect r1 = r5.j
            int r1 = r1.right
            float r1 = (float) r1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            r1 = 1
            if (r0 >= 0) goto Lb1
            float r0 = r6.getX()
            android.graphics.Rect r2 = r5.j
            int r2 = r2.left
            float r2 = (float) r2
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 > 0) goto L29
            goto Lb1
        L29:
            int r0 = r6.getAction()
            if (r0 == 0) goto L8e
            if (r0 == r1) goto L86
            r2 = 2
            if (r0 == r2) goto L38
            r2 = 3
            if (r0 == r2) goto L86
            goto Lac
        L38:
            boolean r0 = r5.e
            if (r0 != 0) goto L53
            boolean r0 = r5.c
            if (r0 != 0) goto L53
            float r0 = r6.getX()
            r5.f = r0
            boolean r0 = r5.c()
            r5.e = r0
            boolean r0 = r5.b()
            r5.c = r0
            goto Lac
        L53:
            boolean r0 = r5.aMl_(r6)
            if (r0 == 0) goto Lac
            float r6 = r5.f3414a
            float r0 = r5.f
            float r6 = r6 - r0
            int r6 = (int) r6
            float r6 = (float) r6
            r0 = 1050253722(0x3e99999a, float:0.3)
            float r6 = r6 * r0
            int r6 = (int) r6
            android.view.ViewGroup r0 = r5.d
            android.graphics.Rect r2 = r5.j
            int r2 = r2.left
            int r2 = r2 + r6
            android.graphics.Rect r3 = r5.j
            int r3 = r3.top
            android.graphics.Rect r4 = r5.j
            int r4 = r4.right
            int r4 = r4 + r6
            android.graphics.Rect r6 = r5.j
            int r6 = r6.bottom
            r0.layout(r2, r3, r4, r6)
            r5.b = r1
            android.view.ViewParent r6 = r5.getParent()
            r6.requestDisallowInterceptTouchEvent(r1)
            return r1
        L86:
            boolean r0 = r5.b
            if (r0 == 0) goto Lac
            r5.d()
            return r1
        L8e:
            boolean r0 = r5.c()
            r5.e = r0
            boolean r0 = r5.b()
            r5.c = r0
            float r0 = r6.getX()
            r5.f = r0
            float r0 = r6.getX()
            r5.f3414a = r0
            float r0 = r6.getY()
            r5.i = r0
        Lac:
            boolean r6 = super.dispatchTouchEvent(r6)
            return r6
        Lb1:
            boolean r6 = r5.b
            if (r6 == 0) goto Lb8
            r5.d()
        Lb8:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.ui.view.BounceScrollView.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    private boolean aMl_(MotionEvent motionEvent) {
        boolean z = false;
        if (motionEvent == null) {
            return false;
        }
        float x = motionEvent.getX();
        int y = (int) motionEvent.getY();
        int i = (int) (x - this.f);
        int abs = (int) Math.abs(x - this.f3414a);
        int abs2 = (int) Math.abs(y - this.i);
        boolean z2 = this.e;
        boolean z3 = true;
        if (!z2 && i > 0) {
            z = true;
        }
        boolean z4 = this.c;
        if (!z4 && i < 0) {
            z = true;
        }
        if (!z4 && !z2 && abs > abs2) {
            z = true;
        }
        if (this.d.getLeft() <= this.j.left && this.d.getRight() >= this.j.right) {
            z3 = z;
        }
        this.f3414a = motionEvent.getX();
        this.i = motionEvent.getY();
        return z3;
    }

    private void d() {
        if (this.b) {
            TranslateAnimation translateAnimation = new TranslateAnimation(this.d.getLeft(), this.j.left, 0.0f, 0.0f);
            translateAnimation.setDuration(300L);
            translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
            this.d.layout(this.j.left, this.j.top, this.j.right, this.j.bottom);
            this.d.startAnimation(translateAnimation);
            this.e = false;
            this.c = false;
            this.b = false;
        }
    }

    private boolean c() {
        ViewGroup viewGroup = this.d;
        if (viewGroup != null) {
            return ViewCompat.canScrollHorizontally(viewGroup, -1);
        }
        return true;
    }

    private boolean b() {
        ViewGroup viewGroup = this.d;
        if (viewGroup != null) {
            return ViewCompat.canScrollHorizontally(viewGroup, 1);
        }
        return true;
    }

    private void a() {
        ViewParent viewParent = this;
        while (!(viewParent instanceof ViewPager)) {
            if (viewParent == null) {
                return;
            } else {
                viewParent = viewParent.getParent();
            }
        }
        viewParent.requestDisallowInterceptTouchEvent(true);
    }
}
