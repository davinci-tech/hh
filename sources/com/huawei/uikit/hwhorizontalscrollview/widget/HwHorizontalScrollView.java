package com.huawei.uikit.hwhorizontalscrollview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.HorizontalScrollView;
import android.widget.OverScroller;
import com.huawei.health.R;
import com.huawei.uikit.hwunifiedinteract.widget.HwGenericEventDetector;
import defpackage.skw;
import defpackage.slc;
import defpackage.smr;
import java.lang.reflect.Method;

/* loaded from: classes7.dex */
public class HwHorizontalScrollView extends HorizontalScrollView {
    private skw b;
    private HwGenericEventDetector c;
    private boolean d;
    private OverScroller e;

    class d implements HwGenericEventDetector.OnScrollListener {
        d() {
        }

        @Override // com.huawei.uikit.hwunifiedinteract.widget.HwGenericEventDetector.OnScrollListener
        public boolean onScrollBy(float f, float f2, MotionEvent motionEvent) {
            return HwHorizontalScrollView.this.a(f, f2);
        }
    }

    public HwHorizontalScrollView(Context context) {
        this(context, null);
    }

    private void edE_(Context context, AttributeSet attributeSet, int i) {
        if (attributeSet == null) {
            Log.w("HwHorizontalScrollView", "Attribute set is null");
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100520_res_0x7f060368}, i, 0);
        int i2 = obtainStyledAttributes.getInt(0, 1);
        obtainStyledAttributes.recycle();
        HwGenericEventDetector c = c();
        this.c = c;
        if (c != null) {
            c.a(i2);
            this.c.eit_(this, d());
        }
    }

    private void g() {
        skw skwVar = this.b;
        if (skwVar != null && !skwVar.h()) {
            ViewParent parent = getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            this.b.d();
        }
        if (this.e.isFinished()) {
            return;
        }
        this.e.abortAnimation();
    }

    private int getScrollRange() {
        int width;
        if (getChildCount() <= 0 || (width = getChildAt(0).getWidth() - ((getWidth() - getPaddingEnd()) - getPaddingStart())) < 0) {
            return 0;
        }
        return width;
    }

    private boolean i() {
        return getScrollX() < 0;
    }

    private boolean j() {
        return getScrollRange() <= getScrollX();
    }

    private void setValueFromPlume(Context context) {
        Method b = slc.b("getBoolean", new Class[]{Context.class, View.class, String.class, Boolean.TYPE}, "huawei.android.widget.HwPlume");
        if (b == null) {
            return;
        }
        Object c = slc.c((Object) null, b, new Object[]{context, this, "listScrollEnabled", true});
        if (c instanceof Boolean) {
            setExtendScrollEnabled(((Boolean) c).booleanValue());
        }
    }

    public boolean b() {
        return this.b.d(getScrollX(), 0, getScrollRange());
    }

    protected HwGenericEventDetector c() {
        return new HwGenericEventDetector(getContext());
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void computeScroll() {
        skw skwVar = this.b;
        if (skwVar != null && skwVar.c()) {
            e();
        } else if (this.e.computeScrollOffset()) {
            a();
        } else {
            super.computeScroll();
        }
    }

    protected HwGenericEventDetector.OnScrollListener d() {
        return new d();
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if (!this.d) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        HwGenericEventDetector hwGenericEventDetector = this.c;
        if (hwGenericEventDetector == null || !hwGenericEventDetector.eir_(motionEvent)) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.HorizontalScrollView
    public void fling(int i) {
        if (getChildCount() <= 0 || !this.b.h()) {
            return;
        }
        int width = (getWidth() - getPaddingEnd()) - getPaddingStart();
        this.e.fling(getScrollX(), 0, i, 0, -width, getScrollRange() + width, 0, 0, 0, 0);
        postInvalidateOnAnimation();
    }

    public OverScroller getOverScroller() {
        return this.e;
    }

    public float getSensitivity() {
        HwGenericEventDetector hwGenericEventDetector = this.c;
        if (hwGenericEventDetector != null) {
            return hwGenericEventDetector.b();
        }
        return 1.0f;
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (!this.d) {
            return super.onGenericMotionEvent(motionEvent);
        }
        HwGenericEventDetector hwGenericEventDetector = this.c;
        if (hwGenericEventDetector == null || !hwGenericEventDetector.eis_(motionEvent)) {
            return super.onGenericMotionEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        edD_(motionEvent);
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        edD_(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    protected boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        int c = ((i() || j()) && z) ? c(i, i3) : i;
        int width = getWidth();
        invalidate();
        return super.overScrollBy(c, i2, i3, i4, i5, i6, width, i8, z);
    }

    public void setExtendScrollEnabled(boolean z) {
        this.d = z;
    }

    public void setSensitivity(float f) {
        HwGenericEventDetector hwGenericEventDetector = this.c;
        if (hwGenericEventDetector != null) {
            hwGenericEventDetector.e(f);
        }
    }

    public HwHorizontalScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100354_res_0x7f0602c2);
    }

    private void edC_(Context context, AttributeSet attributeSet, int i) {
        edE_(context, attributeSet, i);
        this.b = new skw();
        this.e = new OverScroller(context);
        setValueFromPlume(context);
    }

    public HwHorizontalScrollView(Context context, AttributeSet attributeSet, int i) {
        super(b(context, i), attributeSet, i);
        this.d = true;
        edC_(super.getContext(), attributeSet, i);
    }

    private static Context b(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwHorizontalScrollView);
    }

    private void edD_(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0) {
            if ((action == 1 || action == 3) && b()) {
                postInvalidateOnAnimation();
                return;
            }
            return;
        }
        g();
    }

    private void e() {
        if (this.b == null) {
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int b = this.b.b();
        if (scrollX != b) {
            overScrollBy(b - scrollX, 0, scrollX, scrollY, getScrollRange(), 0, getWidth(), 0, false);
            onScrollChanged(getScrollX(), getScrollY(), scrollX, scrollY);
        }
        if (awakenScrollBars()) {
            return;
        }
        postInvalidateOnAnimation();
    }

    private void a() {
        skw skwVar;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.e.getCurrX();
        int currY = this.e.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            int scrollRange = getScrollRange();
            int overScrollMode = getOverScrollMode();
            boolean z = overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0);
            overScrollBy(currX - scrollX, currY - scrollY, scrollX, scrollY, scrollRange, 0, getWidth(), 0, false);
            onScrollChanged(getScrollX(), getScrollY(), scrollX, scrollY);
            if (z && (skwVar = this.b) != null) {
                if (currX < 0 && scrollX >= 0) {
                    skwVar.b(-this.e.getCurrVelocity(), -1, 0);
                    this.e.abortAnimation();
                } else if (currX > scrollRange && scrollX <= scrollRange) {
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
        if (j()) {
            i2 -= getScrollRange();
        }
        return this.b.b(getWidth(), i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(float f, float f2) {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        if (Float.compare(f, 0.0f) != 0) {
            scrollTo(scrollX + ((int) f), scrollY);
            return true;
        }
        scrollTo(scrollX + ((int) f2), scrollY);
        return true;
    }
}
