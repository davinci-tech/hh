package com.huawei.ui.commonui.calendarview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$styleable;
import defpackage.nkw;
import defpackage.nky;

/* loaded from: classes6.dex */
public class HealthCalendarLayout extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    HealthWeekViewPager f8771a;
    HealthMonthViewPager b;
    ViewGroup c;
    private int d;
    HealthCalendarView e;
    private int f;
    private float g;
    private boolean h;
    private final int i;
    private boolean j;
    private int k;
    private float l;
    private nky m;
    private float n;
    private final int o;
    private final VelocityTracker q;
    private final int r;
    private int s;
    private int t;

    public HealthCalendarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(1);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.HealthCalendarLayout);
        this.r = obtainStyledAttributes.getResourceId(R$styleable.HealthCalendarLayout_calendar_content_view_id, 0);
        this.i = obtainStyledAttributes.getInt(R$styleable.HealthCalendarLayout_default_status, 0);
        this.f = obtainStyledAttributes.getInt(R$styleable.HealthCalendarLayout_calendar_display_mode, 0);
        obtainStyledAttributes.recycle();
        this.q = VelocityTracker.obtain();
        this.o = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    final void a(nky nkyVar) {
        this.m = nkyVar;
        this.k = nkyVar.c();
        d(nkyVar.l.isAvailable() ? nkyVar.l : nkyVar.d());
        i();
    }

    private void d(HealthCalendar healthCalendar) {
        b((nkw.d(healthCalendar, this.m.z()) + healthCalendar.getDay()) - 1);
    }

    final void b(int i) {
        this.t = (((i + 7) / 7) - 1) * this.k;
    }

    final void e(int i) {
        this.t = (i - 1) * this.k;
    }

    void i() {
        ViewGroup viewGroup;
        this.s = nkw.d(this.m.f15354a, this.m) - this.k;
        if (this.f8771a.getVisibility() != 0 || (viewGroup = this.c) == null) {
            return;
        }
        viewGroup.setTranslationY(-this.s);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0035, code lost:
    
        if (r0 != 6) goto L34;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            boolean r0 = r3.h()
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            android.view.ViewGroup r0 = r3.c
            if (r0 == 0) goto L7a
            com.huawei.ui.commonui.calendarview.HealthCalendarView r0 = r3.e
            if (r0 == 0) goto L7a
            int r0 = r0.getVisibility()
            r2 = 8
            if (r0 != r2) goto L19
            goto L7a
        L19:
            int r0 = r4.getAction()
            float r1 = r4.getY()
            android.view.VelocityTracker r2 = r3.q
            r2.addMovement(r4)
            r2 = 1
            if (r0 == 0) goto L6b
            if (r0 == r2) goto L63
            r1 = 2
            if (r0 == r1) goto L5e
            r1 = 3
            if (r0 == r1) goto L4b
            r1 = 5
            if (r0 == r1) goto L38
            r1 = 6
            if (r0 == r1) goto L4b
            goto L66
        L38:
            int r0 = r4.getActionIndex()
            int r0 = r4.getPointerId(r0)
            r3.d = r0
            if (r0 != 0) goto L66
            float r0 = r4.getY(r0)
            r3.n = r0
            goto L66
        L4b:
            int r0 = r3.d
            int r0 = r3.cxr_(r4, r0)
            int r1 = r3.d
            r2 = -1
            if (r1 != r2) goto L57
            goto L66
        L57:
            float r0 = r4.getY(r0)
            r3.n = r0
            goto L66
        L5e:
            boolean r4 = r3.cxs_(r4)
            return r4
        L63:
            r3.cxt_(r4)
        L66:
            boolean r4 = super.onTouchEvent(r4)
            return r4
        L6b:
            int r0 = r4.getActionIndex()
            int r4 = r4.getPointerId(r0)
            r3.d = r4
            r3.n = r1
            r3.g = r1
            return r2
        L7a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.commonui.calendarview.HealthCalendarLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private boolean cxs_(MotionEvent motionEvent) {
        float y = motionEvent.getY();
        cxr_(motionEvent, this.d);
        if (this.d == -1) {
            this.n = y;
            this.d = 1;
        }
        float f = y - this.n;
        float f2 = 0.0f;
        if (f < 0.0f && f()) {
            this.n = y;
            motionEvent.setAction(0);
            dispatchTouchEvent(motionEvent);
            this.f8771a.setVisibility(0);
            this.b.setVisibility(4);
            if (!this.h && this.m.n != null) {
                this.m.n.onViewChange(false);
            }
            this.h = true;
            return true;
        }
        if (f <= 0.0f || this.c.getTranslationY() + f < 0.0f) {
            if (f < 0.0f) {
                float translationY = this.c.getTranslationY() + f;
                float f3 = -this.s;
                if (translationY <= f3) {
                    f2 = f3;
                }
            }
            f2 = this.c.getTranslationY() + f;
        }
        this.c.setTranslationY(f2);
        m();
        this.n = y;
        a(false);
        return super.onTouchEvent(motionEvent);
    }

    private void cxt_(MotionEvent motionEvent) {
        VelocityTracker velocityTracker = this.q;
        velocityTracker.computeCurrentVelocity(1000, this.o);
        float yVelocity = velocityTracker.getYVelocity();
        if (j() || this.c.getTranslationY() == this.s) {
            d();
            return;
        }
        if (Math.abs(yVelocity) >= 800.0f) {
            if (yVelocity < 0.0f) {
                e();
                return;
            } else {
                d();
                return;
            }
        }
        if (motionEvent.getY() - this.g > 0.0f) {
            d();
        } else {
            e();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        ViewGroup viewGroup;
        if (this.j || !h()) {
            return super.dispatchTouchEvent(motionEvent);
        }
        HealthCalendarView healthCalendarView = this.e;
        if (healthCalendarView == null || healthCalendarView.getVisibility() == 8 || (viewGroup = this.c) == null || viewGroup.getVisibility() != 0) {
            return super.dispatchTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() == 2 && motionEvent.getY() - this.n > 0.0f && f() && b()) {
            requestDisallowInterceptTouchEvent(false);
            return super.dispatchTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        ViewGroup viewGroup;
        if (this.j) {
            return true;
        }
        HealthCalendarView healthCalendarView = this.e;
        if (healthCalendarView == null || healthCalendarView.getVisibility() == 8 || (viewGroup = this.c) == null || viewGroup.getVisibility() != 0) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (!h()) {
            return false;
        }
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        float x = motionEvent.getX();
        if (action == 0) {
            this.d = motionEvent.getPointerId(motionEvent.getActionIndex());
            this.n = y;
            this.g = y;
            this.l = x;
        } else if (action == 2) {
            float f = y - this.n;
            if (f < 0.0f && f()) {
                return false;
            }
            if (f > 0.0f && f() && y >= this.m.c() + this.m.aa() && !b()) {
                return false;
            }
            if (f > 0.0f && j()) {
                return false;
            }
            if (Math.abs(f) > Math.abs(x - this.l) && ((f > 0.0f && this.c.getTranslationY() <= 0.0f) || (f < 0.0f && this.c.getTranslationY() >= (-this.s)))) {
                this.n = y;
                return true;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    private int cxr_(MotionEvent motionEvent, int i) {
        int findPointerIndex = motionEvent.findPointerIndex(i);
        if (findPointerIndex == -1) {
            this.d = -1;
        }
        return findPointerIndex;
    }

    private boolean h() {
        int i = this.f;
        return (i == 2 || i == 1) ? false : true;
    }

    private boolean f() {
        return this.c.getTranslationY() == ((float) (-this.s));
    }

    private boolean j() {
        return this.c.getTranslationY() == 0.0f;
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.c == null || this.e == null) {
            super.onMeasure(i, i2);
            return;
        }
        int aa = this.m.aa();
        int d = nkw.d(this.m.f15354a, this.m) + aa + this.m.y();
        int size = View.MeasureSpec.getSize(i2);
        if (d >= size && this.b.getHeight() > 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(d + aa + aa, 1073741824);
        } else if (d < size && this.b.getHeight() > 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(size, 1073741824);
        }
        super.onMeasure(i, i2);
        e(size, i);
    }

    private void e(int i, int i2) {
        int height;
        if (this.f == 2 || this.e.getVisibility() == 8) {
            height = this.e.getVisibility() == 8 ? 0 : this.e.getHeight();
        } else {
            i = (i - this.m.aa()) - this.k;
            height = this.m.y();
        }
        this.c.measure(i2, View.MeasureSpec.makeMeasureSpec(i - height, 1073741824));
        ViewGroup viewGroup = this.c;
        viewGroup.layout(viewGroup.getLeft(), this.c.getTop(), this.c.getRight(), this.c.getBottom());
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.b = (HealthMonthViewPager) findViewById(R.id.vp_month);
        this.f8771a = (HealthWeekViewPager) findViewById(R.id.vp_week);
        if (getChildCount() > 0 && (getChildAt(0) instanceof HealthCalendarView)) {
            this.e = (HealthCalendarView) getChildAt(0);
        }
        this.c = (ViewGroup) findViewById(this.r);
    }

    private void m() {
        c(this.c.getTranslationY());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(float f) {
        int i = this.s;
        if (i == 0) {
            return;
        }
        this.b.setTranslationY(this.t * (f / i));
    }

    public void setCanlendarViewDisplayMode(int i) {
        if (i < 0 || i > 2) {
            return;
        }
        this.f = i;
        requestLayout();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("super", super.onSaveInstanceState());
        bundle.putBoolean("isUnfolded", c());
        return bundle;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            Parcelable parcelable2 = bundle.getParcelable("super");
            final boolean z = bundle.getBoolean("isUnfolded");
            post(new Runnable() { // from class: com.huawei.ui.commonui.calendarview.HealthCalendarLayout.2
                @Override // java.lang.Runnable
                public void run() {
                    if (z) {
                        HealthCalendarLayout.this.a(0);
                    } else {
                        HealthCalendarLayout.this.d(0);
                    }
                }
            });
            super.onRestoreInstanceState(parcelable2);
        }
    }

    public final boolean c() {
        return this.b.getVisibility() == 0;
    }

    public boolean d() {
        return a(GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN);
    }

    public boolean a(int i) {
        if (this.j || this.f == 1 || this.c == null) {
            return false;
        }
        if (this.b.getVisibility() != 0) {
            this.f8771a.setVisibility(8);
            n();
            this.h = false;
            this.b.setVisibility(0);
        }
        ObjectAnimator cxq_ = cxq_(i, this.c.getTranslationY(), 0.0f);
        cxq_.addListener(new AnimatorListenerAdapter() { // from class: com.huawei.ui.commonui.calendarview.HealthCalendarLayout.5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                HealthCalendarLayout.this.j = false;
                HealthCalendarLayout.this.a(true);
                if (HealthCalendarLayout.this.m.n != null && HealthCalendarLayout.this.h) {
                    HealthCalendarLayout.this.m.n.onViewChange(true);
                }
                HealthCalendarLayout.this.h = false;
            }
        });
        cxq_.start();
        return true;
    }

    public boolean e() {
        return d(GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN);
    }

    public boolean d(int i) {
        ViewGroup viewGroup;
        if (this.j || (viewGroup = this.c) == null) {
            return false;
        }
        ObjectAnimator cxq_ = cxq_(i, viewGroup.getTranslationY(), -this.s);
        cxq_.addListener(new AnimatorListenerAdapter() { // from class: com.huawei.ui.commonui.calendarview.HealthCalendarLayout.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                HealthCalendarLayout.this.j = false;
                HealthCalendarLayout.this.g();
                HealthCalendarLayout.this.h = true;
            }
        });
        cxq_.start();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ObjectAnimator cxq_(int i, float f, float f2) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.c, "translationY", f, f2);
        ofFloat.setDuration(i);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.ui.commonui.calendarview.HealthCalendarLayout.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (valueAnimator.getAnimatedValue() instanceof Float) {
                    HealthCalendarLayout.this.c(((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
                HealthCalendarLayout.this.j = true;
            }
        });
        return ofFloat;
    }

    final void a() {
        if ((this.i == 1 || this.f == 1) && this.f != 2) {
            if (this.c == null) {
                this.f8771a.setVisibility(0);
                this.b.setVisibility(8);
                return;
            } else {
                post(new Runnable() { // from class: com.huawei.ui.commonui.calendarview.HealthCalendarLayout.1
                    @Override // java.lang.Runnable
                    public void run() {
                        HealthCalendarLayout healthCalendarLayout = HealthCalendarLayout.this;
                        ObjectAnimator cxq_ = healthCalendarLayout.cxq_(0, healthCalendarLayout.c.getTranslationY(), -HealthCalendarLayout.this.s);
                        cxq_.addListener(new AnimatorListenerAdapter() { // from class: com.huawei.ui.commonui.calendarview.HealthCalendarLayout.1.5
                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                            public void onAnimationEnd(Animator animator) {
                                super.onAnimationEnd(animator);
                                HealthCalendarLayout.this.j = false;
                                HealthCalendarLayout.this.h = true;
                                HealthCalendarLayout.this.g();
                                if (HealthCalendarLayout.this.m == null || HealthCalendarLayout.this.m.n == null) {
                                    return;
                                }
                                HealthCalendarLayout.this.m.n.onViewChange(false);
                            }
                        });
                        cxq_.start();
                    }
                });
                return;
            }
        }
        if (this.m.n == null) {
            return;
        }
        post(new Runnable() { // from class: com.huawei.ui.commonui.calendarview.HealthCalendarLayout.10
            @Override // java.lang.Runnable
            public void run() {
                HealthCalendarLayout.this.m.n.onViewChange(true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (z) {
            n();
        }
        this.f8771a.setVisibility(8);
        this.b.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        k();
        HealthWeekViewPager healthWeekViewPager = this.f8771a;
        if (healthWeekViewPager != null && healthWeekViewPager.getAdapter() != null) {
            this.f8771a.getAdapter().notifyDataSetChanged();
            this.f8771a.setVisibility(0);
        }
        this.b.setVisibility(4);
    }

    private void k() {
        if (this.f8771a.getVisibility() == 0 || this.m.n == null || this.h) {
            return;
        }
        this.m.n.onViewChange(false);
    }

    private void n() {
        nky nkyVar;
        if (this.b.getVisibility() == 0 || (nkyVar = this.m) == null || nkyVar.n == null || !this.h) {
            return;
        }
        this.m.n.onViewChange(true);
    }

    protected boolean b() {
        ViewGroup viewGroup = this.c;
        if (viewGroup instanceof RecyclerView) {
            return ((RecyclerView) viewGroup).computeVerticalScrollOffset() == 0;
        }
        if (!(viewGroup instanceof AbsListView)) {
            return viewGroup.getScrollY() == 0;
        }
        AbsListView absListView = (AbsListView) viewGroup;
        return absListView.getFirstVisiblePosition() == 0 && absListView.getChildAt(0).getTop() == 0;
    }
}
