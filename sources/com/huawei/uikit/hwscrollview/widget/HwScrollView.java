package com.huawei.uikit.hwscrollview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.OverScroller;
import android.widget.ScrollView;
import com.huawei.health.R;
import com.huawei.uikit.hwunifiedinteract.widget.HwGenericEventDetector;
import defpackage.skw;
import defpackage.smr;

/* loaded from: classes7.dex */
public class HwScrollView extends ScrollView {

    /* renamed from: a, reason: collision with root package name */
    private HwGenericEventDetector f10737a;
    private boolean b;
    private boolean c;
    private skw d;
    private OverScroller e;
    private int f;
    private int g;
    private boolean h;

    class c implements HwGenericEventDetector.OnScrollListener {
        c() {
        }

        @Override // com.huawei.uikit.hwunifiedinteract.widget.HwGenericEventDetector.OnScrollListener
        public boolean onScrollBy(float f, float f2, MotionEvent motionEvent) {
            if (Float.compare(f2, 0.0f) == 0) {
                return false;
            }
            HwScrollView.this.a(-f2);
            return true;
        }
    }

    public HwScrollView(Context context) {
        this(context, null);
    }

    private void b(Context context, AttributeSet attributeSet, int i) {
        if (attributeSet == null) {
            Log.w("HwScrollView", "Attribute set is null");
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100520_res_0x7f060368}, i, 0);
        int i2 = obtainStyledAttributes.getInt(0, 1);
        obtainStyledAttributes.recycle();
        HwGenericEventDetector a2 = a();
        this.f10737a = a2;
        if (a2 != null) {
            a2.a(i2);
            this.f10737a.eit_(this, j());
        }
    }

    private boolean c() {
        return getScrollY() >= getScrollRange();
    }

    private boolean e() {
        return getScrollY() < 0;
    }

    private void g() {
        skw skwVar = this.d;
        if (skwVar != null && !skwVar.h()) {
            ViewParent parent = getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            this.d.d();
        }
        if (this.e.isFinished()) {
            return;
        }
        this.e.abortAnimation();
    }

    protected HwGenericEventDetector a() {
        return new HwGenericEventDetector(getContext());
    }

    @Override // android.widget.ScrollView, android.view.View
    public void computeScroll() {
        skw skwVar = this.d;
        if (skwVar != null && skwVar.c()) {
            d();
        } else if (this.e.computeScrollOffset()) {
            b();
        } else {
            super.computeScroll();
        }
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        HwGenericEventDetector hwGenericEventDetector;
        if (motionEvent == null) {
            return false;
        }
        if (this.b && (hwGenericEventDetector = this.f10737a) != null && hwGenericEventDetector.eir_(motionEvent)) {
            return true;
        }
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    protected boolean f() {
        return this.d.d(getScrollY(), 0, getScrollRange());
    }

    @Override // android.widget.ScrollView
    public void fling(int i) {
        if (getChildCount() <= 0 || !this.d.h()) {
            return;
        }
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        this.e.fling(0, getScrollY(), 0, i, 0, 0, -height, getScrollRange() + height, 0, 0);
        postInvalidateOnAnimation();
    }

    protected int getScrollRange() {
        if (getChildCount() <= 0) {
            return 0;
        }
        View childAt = getChildAt(0);
        int height = childAt != null ? childAt.getHeight() - ((getHeight() - getPaddingTop()) - getPaddingBottom()) : 0;
        if (height < 0) {
            return 0;
        }
        return height;
    }

    public float getSensitivity() {
        HwGenericEventDetector hwGenericEventDetector = this.f10737a;
        if (hwGenericEventDetector != null) {
            return hwGenericEventDetector.b();
        }
        return 1.0f;
    }

    protected HwGenericEventDetector.OnScrollListener j() {
        return new c();
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        HwGenericEventDetector hwGenericEventDetector = this.f10737a;
        if (hwGenericEventDetector != null && this.b && hwGenericEventDetector.eis_(motionEvent)) {
            return true;
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0016, code lost:
    
        if (r1 != 3) goto L28;
     */
    @Override // android.widget.ScrollView, android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            r0 = 0
            if (r6 != 0) goto L4
            return r0
        L4:
            int r1 = r6.getAction()
            float r2 = r6.getY()
            int r2 = (int) r2
            r3 = 1
            if (r1 == 0) goto L4c
            if (r1 == r3) goto L40
            r4 = 2
            if (r1 == r4) goto L19
            r2 = 3
            if (r1 == r2) goto L40
            goto L53
        L19:
            int r0 = r5.f
            int r0 = r2 - r0
            int r0 = java.lang.Math.abs(r0)
            boolean r1 = r5.h
            if (r1 != 0) goto L53
            int r1 = r5.g
            if (r0 <= r1) goto L53
            r5.h = r3
            int r0 = r5.f
            if (r2 >= r0) goto L33
            int r0 = r0 - r1
            r5.f = r0
            goto L36
        L33:
            int r0 = r0 + r1
            r5.f = r0
        L36:
            android.view.ViewParent r0 = r5.getParent()
            if (r0 == 0) goto L53
            r0.requestDisallowInterceptTouchEvent(r3)
            goto L53
        L40:
            r5.h = r0
            boolean r0 = r5.f()
            if (r0 == 0) goto L53
            r5.postInvalidateOnAnimation()
            goto L53
        L4c:
            r5.h = r0
            r5.f = r2
            r5.g()
        L53:
            boolean r0 = r5.h
            if (r0 == 0) goto L5c
            boolean r0 = r5.c
            if (r0 == 0) goto L5c
            return r3
        L5c:
            boolean r6 = super.onInterceptTouchEvent(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwscrollview.widget.HwScrollView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0011, code lost:
    
        if (r0 != 3) goto L21;
     */
    @Override // android.widget.ScrollView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r14) {
        /*
            r13 = this;
            if (r14 != 0) goto L4
            r14 = 0
            return r14
        L4:
            int r0 = r14.getAction()
            if (r0 == 0) goto L43
            r1 = 1
            if (r0 == r1) goto L39
            r1 = 2
            if (r0 == r1) goto L14
            r1 = 3
            if (r0 == r1) goto L39
            goto L46
        L14:
            float r0 = r14.getY()
            int r0 = (int) r0
            int r1 = r13.f
            boolean r2 = r13.h
            if (r2 == 0) goto L46
            r4 = 0
            int r5 = r1 - r0
            r6 = 0
            int r7 = r13.getScrollY()
            r8 = 0
            int r9 = r13.getScrollRange()
            r10 = 0
            int r11 = r13.getHeight()
            r12 = 1
            r3 = r13
            r3.overScrollBy(r4, r5, r6, r7, r8, r9, r10, r11, r12)
            r13.f = r0
            goto L46
        L39:
            boolean r0 = r13.f()
            if (r0 == 0) goto L46
            r13.postInvalidateOnAnimation()
            goto L46
        L43:
            r13.g()
        L46:
            boolean r14 = super.onTouchEvent(r14)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwscrollview.widget.HwScrollView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        int c2 = (e() || c()) ? c(i2, i4) : i2;
        invalidate();
        return super.overScrollBy(i, c2, i3, i4, i5, i6, i7, i8, z);
    }

    public void setEnforceableOverScrollEnabled(boolean z) {
        this.c = z;
    }

    public void setExtendScrollEnabled(boolean z) {
        this.b = z;
    }

    public void setSensitivity(float f) {
        HwGenericEventDetector hwGenericEventDetector = this.f10737a;
        if (hwGenericEventDetector != null) {
            hwGenericEventDetector.e(f);
        }
    }

    public HwScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100488_res_0x7f060348);
    }

    private static Context a(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwScrollView);
    }

    public HwScrollView(Context context, AttributeSet attributeSet, int i) {
        super(a(context, i), attributeSet, i);
        this.b = true;
        this.c = true;
        this.h = false;
        egV_(super.getContext(), attributeSet, i);
    }

    private void egV_(Context context, AttributeSet attributeSet, int i) {
        b(context, attributeSet, i);
        this.d = new skw();
        this.e = new OverScroller(context);
        this.g = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(float f) {
        scrollTo(getScrollX(), getScrollY() + ((int) f));
    }

    private void d() {
        if (this.d == null) {
            return;
        }
        int scrollY = getScrollY();
        int b = this.d.b();
        if (scrollY != b) {
            overScrollBy(0, b - scrollY, 0, scrollY, 0, getScrollRange(), 0, getHeight() / 2, false);
            onScrollChanged(getScrollX(), getScrollY(), 0, scrollY);
        }
        if (awakenScrollBars()) {
            return;
        }
        postInvalidateOnAnimation();
    }

    private void b() {
        skw skwVar;
        int scrollY = getScrollY();
        int currY = this.e.getCurrY();
        if (scrollY != currY) {
            int scrollRange = getScrollRange();
            int overScrollMode = getOverScrollMode();
            boolean z = overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0);
            overScrollBy(0, currY - scrollY, 0, scrollY, 0, scrollRange, getHeight(), 0, false);
            onScrollChanged(0, getScrollY(), 0, scrollY);
            if (z && (skwVar = this.d) != null) {
                if (currY < 0 && scrollY >= 0) {
                    skwVar.b(-this.e.getCurrVelocity(), -1, 0);
                    this.e.abortAnimation();
                } else if (currY > scrollRange && scrollY <= scrollRange) {
                    skwVar.b(this.e.getCurrVelocity(), scrollRange + 1, scrollRange);
                    this.e.abortAnimation();
                }
            }
        }
        if (awakenScrollBars()) {
            return;
        }
        postInvalidateOnAnimation();
    }

    private int c(int i, int i2) {
        if (c()) {
            i2 -= getScrollRange();
        }
        return this.d.b(getHeight(), i, i2);
    }
}
