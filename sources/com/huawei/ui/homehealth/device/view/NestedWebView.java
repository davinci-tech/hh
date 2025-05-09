package com.huawei.ui.homehealth.device.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.Scroller;
import androidx.core.view.NestedScrollingChild3;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ViewCompat;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.device.util.NestScrollHelper;
import com.huawei.ui.homehealth.device.view.NestedDeviceScrollView;
import com.huawei.ui.homehealth.device.view.NestedWebView;

/* loaded from: classes6.dex */
public class NestedWebView extends WebView implements NestedScrollingChild3 {

    /* renamed from: a, reason: collision with root package name */
    private boolean f9414a;
    private int b;
    private int c;
    private final NestedScrollingChildHelper d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private NestedDeviceScrollView k;
    private Scroller l;
    private final int[] m;
    private Runnable n;
    private final int[] o;
    private VelocityTracker q;
    private int t;

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return 2;
    }

    public NestedWebView(Context context) {
        this(context, null);
    }

    public NestedWebView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NestedWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.m = new int[2];
        this.o = new int[2];
        this.f9414a = false;
        this.b = -1;
        this.h = 0;
        this.d = new NestedScrollingChildHelper(this);
        setOverScrollMode(2);
        b();
        setNestedScrollingEnabled(true);
    }

    private void b() {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.i = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f = viewConfiguration.getScaledMaximumFlingVelocity();
        this.t = viewConfiguration.getScaledTouchSlop();
        this.l = new Scroller(getContext());
        setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: ogv
            @Override // android.view.View.OnScrollChangeListener
            public final void onScrollChange(View view, int i, int i2, int i3, int i4) {
                NestedWebView.this.cZU_(view, i, i2, i3, i4);
            }
        });
    }

    public /* synthetic */ void cZU_(View view, int i, int i2, int i3, int i4) {
        NestedDeviceScrollView nestedDeviceScrollView = this.k;
        int scrollY = nestedDeviceScrollView != null ? nestedDeviceScrollView.getScrollY() : 0;
        if (this.h < scrollY) {
            this.h = scrollY;
        }
        if (scrollY >= this.h || i2 <= 0) {
            return;
        }
        setScrollY(0);
    }

    @Override // android.widget.AbsoluteLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.k = cZS_(getParent());
    }

    public void d() {
        this.h = 0;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        LogUtil.h("NestedWebView", "onTouchEvent  MotionEvent:");
        int action = motionEvent.getAction();
        if (action == 2 && this.f9414a) {
            return true;
        }
        int i = action & 255;
        if (i == 0) {
            this.c = (int) motionEvent.getY();
            this.b = motionEvent.getPointerId(0);
            c();
            this.q.clear();
            this.q.addMovement(motionEvent);
            this.l.computeScrollOffset();
            this.f9414a = !this.l.isFinished();
            startNestedScroll(2);
        } else {
            if (i != 1) {
                if (i == 2) {
                    int i2 = this.b;
                    if (i2 == -1) {
                        return this.f9414a;
                    }
                    int findPointerIndex = motionEvent.findPointerIndex(i2);
                    if (findPointerIndex == -1) {
                        LogUtil.h("NestedWebView", "Invalid pointerId=", Integer.valueOf(i2), " in onInterceptTouchEvent");
                        return this.f9414a;
                    }
                    int y = (int) motionEvent.getY(findPointerIndex);
                    if (Math.abs(y - this.c) > this.t && (getNestedScrollAxes() & 2) == 0) {
                        this.f9414a = true;
                        this.c = y;
                        c();
                        this.q.addMovement(motionEvent);
                        this.j = 0;
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                } else if (i != 3) {
                    if (i == 6) {
                        cZT_(motionEvent);
                    }
                }
            }
            this.f9414a = false;
            this.b = -1;
            j();
            stopNestedScroll();
        }
        return this.f9414a;
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.j = 0;
        }
        obtain.offsetLocation(0.0f, this.j);
        c();
        if (actionMasked == 0) {
            removeCallbacks(this.n);
            if (this.f9414a == (!this.l.isFinished()) && (parent = getParent()) != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            if (!this.l.isFinished()) {
                a();
            }
            this.b = motionEvent.getPointerId(0);
            this.c = (int) motionEvent.getY();
            startNestedScroll(2, 0);
        } else if (actionMasked == 1) {
            this.q.computeCurrentVelocity(1000, this.f);
            int yVelocity = (int) this.q.getYVelocity(this.b);
            if (Math.abs(yVelocity) > this.i) {
                if (this.g <= 0) {
                    int i = -yVelocity;
                    float f = i;
                    if (!dispatchNestedPreFling(0.0f, f)) {
                        dispatchNestedFling(0.0f, f, true);
                        if (yVelocity < 0) {
                            a(i);
                        }
                    }
                } else {
                    a(-yVelocity);
                }
            }
            this.b = -1;
            e();
        } else if (actionMasked == 2) {
            int findPointerIndex = motionEvent.findPointerIndex(this.b);
            if (findPointerIndex == -1) {
                LogUtil.h("NestedWebView", "Invalid pointerId=", Integer.valueOf(this.b), " in onTouchEvent");
            } else {
                int y = (int) motionEvent.getY(findPointerIndex);
                int i2 = this.c - y;
                if (dispatchNestedPreScroll(0, i2, this.m, this.o, 0)) {
                    i2 -= this.m[1];
                    this.j += this.o[1];
                }
                if (!this.f9414a && Math.abs(i2) > this.t) {
                    ViewParent parent2 = getParent();
                    if (parent2 != null) {
                        parent2.requestDisallowInterceptTouchEvent(true);
                    }
                    this.f9414a = true;
                    if (i2 > 0) {
                        i2 -= this.t;
                    } else {
                        i2 += this.t;
                    }
                }
                if (this.f9414a) {
                    this.c = y - this.o[1];
                    int scrollY = getScrollY();
                    if (e(i2, scrollY, computeVerticalScrollRange(), 0) && !hasNestedScrollingParent(0)) {
                        this.q.clear();
                    }
                    int scrollY2 = getScrollY() - scrollY;
                    int[] iArr = this.m;
                    iArr[1] = 0;
                    dispatchNestedScroll(0, scrollY2, 0, i2 - scrollY2, this.o, 0, iArr);
                    int i3 = this.j;
                    int i4 = this.o[1];
                    this.j = i3 + i4;
                    this.c -= i4;
                }
            }
        } else if (actionMasked == 3) {
            this.b = -1;
            e();
        } else if (actionMasked == 5) {
            int actionIndex = motionEvent.getActionIndex();
            this.b = motionEvent.getPointerId(actionIndex);
            this.c = (int) motionEvent.getY(actionIndex);
        } else if (actionMasked == 6) {
            cZT_(motionEvent);
            this.c = (int) motionEvent.getY(motionEvent.findPointerIndex(this.b));
        }
        VelocityTracker velocityTracker = this.q;
        if (velocityTracker != null) {
            velocityTracker.addMovement(obtain);
        }
        obtain.recycle();
        return super.onTouchEvent(motionEvent);
    }

    private void a() {
        this.l.abortAnimation();
        stopNestedScroll(1);
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        this.g = i2;
        super.onOverScrolled(i, i2, z, z2);
    }

    private void e() {
        this.f9414a = false;
        j();
        stopNestedScroll();
    }

    private void cZT_(MotionEvent motionEvent) {
        int action = (65280 & motionEvent.getAction()) >> 8;
        if (motionEvent.getPointerId(action) == this.b) {
            int i = action == 0 ? 1 : 0;
            this.b = motionEvent.getPointerId(i);
            this.c = (int) motionEvent.getY(i);
            VelocityTracker velocityTracker = this.q;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    public void a(int i) {
        e(i);
        this.l.fling(getScrollX(), getScrollY(), 0, i, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        startNestedScroll(2, 1);
        this.e = getScrollY();
        ViewCompat.postInvalidateOnAnimation(this);
    }

    private void e(int i) {
        if (this.k == null) {
            return;
        }
        NestScrollHelper nestScrollHelper = new NestScrollHelper(getContext());
        double c = nestScrollHelper.c(i);
        double scrollY = getScrollY();
        if (c <= scrollY || i >= 0) {
            return;
        }
        double c2 = nestScrollHelper.c(i);
        double d = nestScrollHelper.d(c - scrollY);
        double c3 = nestScrollHelper.c(d);
        Runnable b = b(this.k, (int) d);
        this.n = b;
        postDelayed(b, ((int) (c2 - c3)) - 100);
    }

    private static Runnable b(final NestedDeviceScrollView nestedDeviceScrollView, final int i) {
        return new Runnable() { // from class: ogu
            @Override // java.lang.Runnable
            public final void run() {
                NestedDeviceScrollView.this.fling(-i);
            }
        };
    }

    private NestedDeviceScrollView cZS_(ViewParent viewParent) {
        if (viewParent instanceof NestedDeviceScrollView) {
            return (NestedDeviceScrollView) viewParent;
        }
        if (viewParent == null || viewParent.getParent() == null) {
            return null;
        }
        if (viewParent.getParent() instanceof NestedDeviceScrollView) {
            return (NestedDeviceScrollView) viewParent.getParent();
        }
        return cZS_(viewParent.getParent());
    }

    private void j() {
        VelocityTracker velocityTracker = this.q;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.q = null;
        }
    }

    private void c() {
        if (this.q == null) {
            this.q = VelocityTracker.obtain();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (z) {
            j();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override // android.view.View
    protected boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        if (this.f9414a) {
            return true;
        }
        e(i2, i4, i6, i8);
        return true;
    }

    @Override // android.webkit.WebView, android.view.View
    public void computeScroll() {
        if (this.l.isFinished()) {
            return;
        }
        this.l.computeScrollOffset();
        int currY = this.l.getCurrY();
        int i = currY - this.e;
        this.e = currY;
        int[] iArr = this.m;
        iArr[1] = 0;
        dispatchNestedPreScroll(0, i, iArr, null, 1);
        int i2 = i - this.m[1];
        if (i2 != 0) {
            int scrollY = getScrollY();
            e(i2, scrollY, computeVerticalScrollRange(), 0);
            int scrollY2 = i2 - (getScrollY() - scrollY);
            int[] iArr2 = this.m;
            iArr2[1] = 0;
            dispatchNestedScroll(0, 0, 0, scrollY2, this.o, 1, iArr2);
            i2 = scrollY2 - this.m[1];
        }
        if (i2 != 0) {
            a();
        }
        if (this.l.isFinished()) {
            return;
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    private boolean e(int i, int i2, int i3, int i4) {
        int overScrollMode = getOverScrollMode();
        boolean z = true;
        boolean z2 = computeVerticalScrollRange() > computeVerticalScrollExtent();
        if (overScrollMode != 0 && (overScrollMode != 1 || !z2)) {
            i4 = 0;
        }
        int i5 = -i4;
        int i6 = i4 + i3;
        int i7 = i2 + i;
        if (i7 >= i5) {
            if (i7 > i6) {
                i5 = i6;
            } else {
                i5 = i7;
                z = false;
            }
        }
        onOverScrolled(0, i5, false, z);
        return z;
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z) {
        this.d.setNestedScrollingEnabled(z);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        return this.d.isNestedScrollingEnabled();
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean startNestedScroll(int i) {
        return startNestedScroll(i, 0);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean startNestedScroll(int i, int i2) {
        return this.d.startNestedScroll(i, i2);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void stopNestedScroll() {
        stopNestedScroll(0);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public void stopNestedScroll(int i) {
        this.d.stopNestedScroll(i);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean hasNestedScrollingParent() {
        return hasNestedScrollingParent(0);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean hasNestedScrollingParent(int i) {
        return this.d.hasNestedScrollingParent(i);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return dispatchNestedScroll(i, i2, i3, i4, iArr, 0);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        return this.d.dispatchNestedScroll(i, i2, i3, i4, iArr, i5);
    }

    @Override // androidx.core.view.NestedScrollingChild3
    public void dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        this.d.dispatchNestedScroll(i, i2, i3, i4, iArr, i5, iArr2);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        return this.d.dispatchNestedPreScroll(i, i2, iArr, iArr2, i3);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return dispatchNestedPreScroll(i, i2, iArr, iArr2, 0);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.d.dispatchNestedPreFling(f, f2);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.d.dispatchNestedFling(f, f2, false);
    }
}
