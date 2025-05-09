package com.huawei.ui.commonui.scrollview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.TranslateAnimation;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.ui.commonui.utils.StatusBarClickedListener;
import com.huawei.uikit.hwscrollview.widget.HwScrollView;
import defpackage.koq;
import defpackage.nqh;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class HealthScrollView extends HwScrollView {

    /* renamed from: a, reason: collision with root package name */
    private boolean f8943a;
    private boolean aa;
    private boolean ab;
    private boolean ac;
    private boolean ad;
    private boolean ae;
    private int af;
    private float ag;
    private Rect ah;
    private int ai;
    private ScrollChangeOtherListener aj;
    private float ak;
    private List<ScrollChangeToBoundaryListener> al;
    private float am;
    private ScrollViewListener an;
    private e ao;
    private StatusBarClickedListener ap;
    private int ar;
    private boolean as;
    private Context b;
    private int c;
    private View d;
    private int e;
    private float f;
    private float g;
    private nqh h;
    private int i;
    private int j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

    public interface ScrollChangeOtherListener {
        void scrollChangeOtherListener();
    }

    public interface ScrollChangeToBoundaryListener {
        public static final int STATUS_DOWN = 0;
        public static final int STATUS_UP = 1;
        public static final int STATUS_UP_AT_TOP = 2;

        void onScrollStateChange(int i);

        void onScrollToChangeAlpha(float f);

        void onScrolledToBottom();

        void onScrolledTop();
    }

    public HealthScrollView(Context context) {
        super(context);
        this.f = 0.0f;
        this.g = 0.0f;
        this.u = false;
        this.as = false;
        this.w = true;
        this.aa = false;
        this.o = false;
        this.al = new ArrayList();
        this.ao = new e(this);
        this.f8943a = false;
        this.x = false;
        this.ab = false;
        this.q = true;
        this.m = true;
        this.t = false;
        this.h = null;
        this.l = false;
        this.y = false;
        this.ac = true;
        this.ae = false;
        this.ah = new Rect();
        this.k = false;
        this.n = false;
        this.v = false;
        this.r = false;
        this.z = false;
        this.s = false;
        cFJ_(context, null);
    }

    public HealthScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = 0.0f;
        this.g = 0.0f;
        this.u = false;
        this.as = false;
        this.w = true;
        this.aa = false;
        this.o = false;
        this.al = new ArrayList();
        this.ao = new e(this);
        this.f8943a = false;
        this.x = false;
        this.ab = false;
        this.q = true;
        this.m = true;
        this.t = false;
        this.h = null;
        this.l = false;
        this.y = false;
        this.ac = true;
        this.ae = false;
        this.ah = new Rect();
        this.k = false;
        this.n = false;
        this.v = false;
        this.r = false;
        this.z = false;
        this.s = false;
        cFJ_(context, attributeSet);
    }

    public HealthScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = 0.0f;
        this.g = 0.0f;
        this.u = false;
        this.as = false;
        this.w = true;
        this.aa = false;
        this.o = false;
        this.al = new ArrayList();
        this.ao = new e(this);
        this.f8943a = false;
        this.x = false;
        this.ab = false;
        this.q = true;
        this.m = true;
        this.t = false;
        this.h = null;
        this.l = false;
        this.y = false;
        this.ac = true;
        this.ae = false;
        this.ah = new Rect();
        this.k = false;
        this.n = false;
        this.v = false;
        this.r = false;
        this.z = false;
        this.s = false;
        cFJ_(context, attributeSet);
    }

    private void cFJ_(Context context, AttributeSet attributeSet) {
        this.b = context;
        if (attributeSet == null) {
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.HealthScrollView);
        this.aa = obtainStyledAttributes.getBoolean(R$styleable.HealthScrollView_sportScrollView, false);
        this.x = obtainStyledAttributes.getBoolean(R$styleable.HealthScrollView_pullDownRebound, false);
        this.ab = obtainStyledAttributes.getBoolean(R$styleable.HealthScrollView_statusBarRebound, false);
        this.q = obtainStyledAttributes.getBoolean(R$styleable.HealthScrollView_interceptEvent, true);
        this.ad = obtainStyledAttributes.getBoolean(R$styleable.HealthScrollView_scrollEnable, false);
        this.m = obtainStyledAttributes.getBoolean(R$styleable.HealthScrollView_autoScroll, true);
        this.t = obtainStyledAttributes.getBoolean(R$styleable.HealthScrollView_longPress, false);
        this.y = obtainStyledAttributes.getBoolean(R$styleable.HealthScrollView_ratioHeight, false);
        this.ae = obtainStyledAttributes.getBoolean(R$styleable.HealthScrollView_supportRebound, false);
        obtainStyledAttributes.recycle();
        if (this.t) {
            this.h = new nqh(context, new GestureDetector.SimpleOnGestureListener() { // from class: com.huawei.ui.commonui.scrollview.HealthScrollView.2
                @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
                public void onLongPress(MotionEvent motionEvent) {
                    HealthScrollView.this.l = true;
                }
            });
        }
        if (this.ae) {
            this.j = getPaddingTop();
            this.c = getPaddingBottom();
            this.i = getPaddingRight();
            this.e = getPaddingLeft();
        }
        g();
        this.ar = ViewConfiguration.get(this.b).getScaledTouchSlop();
    }

    public void setOverScrollable(boolean z) {
        this.w = z;
    }

    private int getMaxScrollY() {
        return getChildAt(0).getMeasuredHeight() - getHeight();
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (!this.w) {
            int i5 = i2 - i4;
            if (i5 < 0 && getScrollY() <= 0) {
                setScrollY(0);
                return;
            } else if (i5 > 0 && getScrollY() >= getMaxScrollY()) {
                setScrollY(getMaxScrollY());
            }
        }
        if (this.ae) {
            if (getScrollY() > 0) {
                setPadding(this.e, this.j, this.i, this.c);
            } else {
                setPadding(this.e, 0, this.i, this.c);
            }
        }
        if (this.x && i2 - i4 < 0 && getScrollY() <= 0) {
            setScrollY(0);
        }
        ScrollViewListener scrollViewListener = this.an;
        if (scrollViewListener != null && (this.x || this.s)) {
            scrollViewListener.onScrollChanged(this, i, i2, i3, i4);
        }
        if (koq.c(this.al)) {
            for (ScrollChangeToBoundaryListener scrollChangeToBoundaryListener : this.al) {
                if (getScrollY() >= 100) {
                    scrollChangeToBoundaryListener.onScrolledToBottom();
                } else {
                    scrollChangeToBoundaryListener.onScrolledTop();
                }
                int c = nsn.c(BaseApplication.getContext(), 110.0f);
                if (c != 0) {
                    float f = c;
                    scrollChangeToBoundaryListener.onScrollToChangeAlpha(((float) getScrollY()) / f <= 1.0f ? getScrollY() / f : 1.0f);
                }
            }
        }
        ScrollChangeOtherListener scrollChangeOtherListener = this.aj;
        if (scrollChangeOtherListener != null) {
            scrollChangeOtherListener.scrollChangeOtherListener();
        }
    }

    public void setScrollChangeOtherListener(ScrollChangeOtherListener scrollChangeOtherListener) {
        this.aj = scrollChangeOtherListener;
    }

    @Override // android.widget.ScrollView
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        if (this.m) {
            return super.computeScrollDeltaToGetChildRectOnScreen(rect);
        }
        return 0;
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.y) {
            Context context = this.b;
            if (context instanceof Activity) {
                Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                defaultDisplay.getMetrics(displayMetrics);
                super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(displayMetrics.heightPixels / 3, Integer.MIN_VALUE));
                return;
            }
        }
        super.onMeasure(i, i2);
    }

    public void setCanRebound(boolean z) {
        this.r = z;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!this.ae || getChildCount() <= 0) {
            return;
        }
        this.d = getChildAt(0);
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        try {
            super.onLayout(z, i, i2, i3, i4);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("HealthScrollView", "super onLayout exception");
        }
        View view = this.d;
        if (view == null) {
            return;
        }
        this.ah.set(view.getLeft(), this.d.getTop(), this.d.getRight(), this.d.getBottom());
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z = this.ae;
        if (z && this.d == null) {
            return super.dispatchTouchEvent(motionEvent);
        }
        if (!this.r) {
            return super.dispatchTouchEvent(motionEvent);
        }
        if (z) {
            cFH_(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private void cFH_(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.k = b();
            this.n = d();
            this.ak = motionEvent.getY();
        } else if (action != 1) {
            if (action != 2) {
                return;
            }
            cFI_(motionEvent);
        } else if (this.v) {
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, this.d.getTop(), this.ah.top);
            translateAnimation.setDuration(300L);
            this.d.startAnimation(translateAnimation);
            this.d.layout(this.ah.left, this.ah.top, this.ah.right, this.ah.bottom);
            this.k = false;
            this.n = false;
            this.v = false;
        }
    }

    private boolean cFI_(MotionEvent motionEvent) {
        if (!this.k && !this.n) {
            this.ak = motionEvent.getY();
            this.k = b();
            this.n = d();
            return true;
        }
        int y = (int) (motionEvent.getY() - this.ak);
        boolean z = this.k;
        boolean z2 = z && y > 0;
        boolean z3 = this.n;
        boolean z4 = z3 && y < 0;
        boolean z5 = z && z3;
        if (z2 || z4 || z5) {
            int i = (int) (y * 0.5f);
            this.d.layout(this.ah.left, this.ah.top + i, this.ah.right, this.ah.bottom + i);
            this.v = true;
        }
        return false;
    }

    public boolean b() {
        return getScrollY() == 0 || this.d.getHeight() < getHeight() + getScrollY();
    }

    public boolean d() {
        return this.d.getHeight() <= getHeight() + getScrollY();
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        if (scrollViewListener == null) {
            return;
        }
        this.an = scrollViewListener;
    }

    @Override // com.huawei.uikit.hwscrollview.widget.HwScrollView, android.widget.ScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        nqh nqhVar;
        if (motionEvent == null) {
            return false;
        }
        if (this.t && (nqhVar = this.h) != null) {
            nqhVar.cFF_(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 2) {
                if (this.t && this.l) {
                    return false;
                }
            } else {
                this.l = false;
            }
        } else if (!this.q) {
            this.z = super.onInterceptTouchEvent(motionEvent);
        }
        if (!this.q) {
            return this.z;
        }
        if (this.p || this.ae) {
            return false;
        }
        if (this.u || this.as) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int action2 = motionEvent.getAction();
            if (action2 == 0) {
                this.f = x;
                this.g = y;
            } else {
                if (action2 == 2) {
                    return a(Math.abs(x - this.f), Math.abs(y - this.g));
                }
                LogUtil.h("HealthScrollView", "onInterceptTouchEvent default break");
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void setScrollViewVerticalDirectionEvent(boolean z) {
        this.as = z;
    }

    public void setScrollOnlyVertical(boolean z) {
        this.u = z;
    }

    @Override // com.huawei.uikit.hwscrollview.widget.HwScrollView, android.view.View
    public boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        if (this.aa) {
            return super.overScrollBy(i, i2, i3, i4, i5, i6, i7, this.ai, z);
        }
        return super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
    }

    public void d(ScrollChangeToBoundaryListener scrollChangeToBoundaryListener) {
        this.al.add(scrollChangeToBoundaryListener);
    }

    public void c() {
        this.al.clear();
    }

    public void setIsScrollEnable(boolean z) {
        this.ad = z;
    }

    @Override // com.huawei.uikit.hwscrollview.widget.HwScrollView, android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        if (this.ae || this.ad) {
            return true;
        }
        if (this.f8943a) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.ag = motionEvent.getRawX();
                this.am = motionEvent.getRawY();
                this.o = true;
            } else if (action != 1) {
                if (action == 2 && koq.c(this.al)) {
                    for (ScrollChangeToBoundaryListener scrollChangeToBoundaryListener : this.al) {
                        if (scrollChangeToBoundaryListener != null && (Math.abs(motionEvent.getRawX() - this.ag) > 10.0f || Math.abs(motionEvent.getRawY() - this.am) > 10.0f)) {
                            scrollChangeToBoundaryListener.onScrollStateChange(0);
                        }
                    }
                }
            } else {
                this.o = false;
                this.af = getScrollY();
                this.ao.sendEmptyMessageDelayed(1, 150L);
            }
        }
        try {
            return super.onTouchEvent(motionEvent);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("HealthScrollView", "super onTouchEvent error");
            return false;
        }
    }

    static class e extends BaseHandler<HealthScrollView> {
        e(HealthScrollView healthScrollView) {
            super(healthScrollView);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cFK_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(HealthScrollView healthScrollView, Message message) {
            if (message == null) {
                LogUtil.b("HealthScrollView", "TouchHandler message is null");
                return;
            }
            int i = message.what;
            if (i != 1) {
                if (i == 2 && koq.c(healthScrollView.al)) {
                    for (ScrollChangeToBoundaryListener scrollChangeToBoundaryListener : healthScrollView.al) {
                        if (healthScrollView.getScrollY() == 0) {
                            scrollChangeToBoundaryListener.onScrollStateChange(2);
                        } else {
                            scrollChangeToBoundaryListener.onScrollStateChange(1);
                        }
                    }
                    return;
                }
                return;
            }
            int scrollY = healthScrollView.getScrollY();
            if (healthScrollView.o) {
                return;
            }
            if (scrollY != healthScrollView.af) {
                healthScrollView.af = scrollY;
                sendEmptyMessageDelayed(1, 150L);
            } else {
                sendEmptyMessage(2);
            }
        }
    }

    public void e(boolean z) {
        this.f8943a = z;
    }

    private void g() {
        this.ai = (int) (this.b.getResources().getDisplayMetrics().density * 200.0f);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchWindowFocusChanged(boolean z) {
        if (this.ab) {
            super.dispatchWindowFocusChanged(z);
            setScrollTopEnable(z);
        }
    }

    public void setScrollTopEnable(boolean z) {
        this.ac = z;
    }

    public void e() {
        LogUtil.a("HealthScrollView", "enter::scrollToTop, mIsScrollTopEnable", Boolean.valueOf(this.ac));
        if (!this.ac || getScrollY() == 0) {
            return;
        }
        smoothScrollTo(0, 0);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogUtil.a("HealthScrollView", "onAttachedToWindow");
        if (this.ab && this.ap == null && this.b != null) {
            this.ap = new StatusBarClickedListener() { // from class: com.huawei.ui.commonui.scrollview.HealthScrollView.5
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    HealthScrollView.this.e();
                }
            };
        }
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        StatusBarClickedListener statusBarClickedListener;
        super.onDetachedFromWindow();
        LogUtil.a("HealthScrollView", "onDetachedFromWindow");
        if (!this.ab || (statusBarClickedListener = this.ap) == null) {
            return;
        }
        statusBarClickedListener.unregister();
        this.ap = null;
    }

    public void setScrollViewIntercepts(boolean z) {
        this.p = z;
    }

    public void setIsForceScrollListener(boolean z) {
        this.s = z;
    }

    private boolean a(float f, float f2) {
        return f <= f2 && f2 > f && f2 > ((float) this.ar);
    }
}
