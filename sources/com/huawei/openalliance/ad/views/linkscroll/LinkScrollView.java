package com.huawei.openalliance.ad.views.linkscroll;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.Scroller;
import com.huawei.health.R;
import com.huawei.openalliance.ad.activity.PPSActivity;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.hj;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.views.linkscroll.e;

/* loaded from: classes9.dex */
public class LinkScrollView extends LinearLayout implements b {

    /* renamed from: a, reason: collision with root package name */
    private View f8112a;
    private View b;
    private int c;
    private OverScroller d;
    private int e;
    private int f;
    private Scroller g;
    private View h;
    private int i;
    private final boolean j;
    private int k;
    private int l;
    private boolean m;
    private hj n;
    private View o;
    private e.a p;

    @Override // com.huawei.openalliance.ad.views.linkscroll.b
    public void a(View view) {
    }

    @Override // com.huawei.openalliance.ad.views.linkscroll.b
    public void a(View view, int i, int i2, int i3, int i4) {
    }

    @Override // com.huawei.openalliance.ad.views.linkscroll.b
    public boolean a(View view, View view2, int i) {
        return true;
    }

    public int getLinkScrollAxes() {
        return 0;
    }

    public void setWebView(View view) {
        this.h = view;
        if (view instanceof e) {
            ((e) view).setScrollListener(this.p);
        }
    }

    public void setBarHeight(int i) {
        this.k = i;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.c = this.f8112a.getMeasuredHeight();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f8112a.measure(i, View.MeasureSpec.makeMeasureSpec(0, 0));
        ViewGroup.LayoutParams layoutParams = this.b.getLayoutParams();
        layoutParams.height = getMeasuredHeight();
        if (!this.j) {
            layoutParams.height -= this.k;
            if (this.m) {
                layoutParams.height -= this.l;
            }
        }
        this.b.setLayoutParams(layoutParams);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.f8112a = findViewById(R.id.linked_native_view);
        this.b = findViewById(R.id.linked_pps_web_view);
        this.f8112a.setOverScrollMode(2);
        this.b.setOverScrollMode(2);
        setOverScrollMode(2);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.d.computeScrollOffset()) {
            int currY = this.d.getCurrY();
            if (currY < 0) {
                currY = 0;
            }
            int i = this.c;
            if (currY > i) {
                currY = i;
            }
            scrollTo(0, currY);
            int scrollY = getScrollY();
            if (b() && scrollY == this.c) {
                int currVelocity = (int) this.d.getCurrVelocity();
                ho.b("LinkScrollView", "webView fling");
                this.d.abortAnimation();
                View view = this.h;
                if (view instanceof e) {
                    ((e) view).a(currVelocity);
                }
                b(currVelocity);
            }
            invalidate();
        }
    }

    @Override // com.huawei.openalliance.ad.views.linkscroll.b
    public void b(View view, View view2, int i) {
        c();
    }

    @Override // com.huawei.openalliance.ad.views.linkscroll.b
    public boolean a(View view, float f, float f2) {
        int scrollY = getScrollY();
        this.i = f2 > 0.0f ? 1 : -1;
        if (scrollY == this.c) {
            b(f2);
            return false;
        }
        a(f2);
        return true;
    }

    @Override // com.huawei.openalliance.ad.views.linkscroll.b
    public void a(View view, int i, int i2, int[] iArr) {
        if (b()) {
            if (a(i2) || a(view, i2)) {
                if (i2 < 0) {
                    int scrollY = getScrollY();
                    if (i2 + scrollY < 0) {
                        i2 = -scrollY;
                    }
                }
                scrollBy(0, i2);
                iArr[1] = i2;
            }
        }
    }

    public void a(int i, boolean z) {
        this.l = i;
        this.m = z;
        measure(getMeasuredWidth(), getMeasuredHeight());
    }

    public void a() {
        try {
            View view = this.o;
            if (view == null || this.n == null) {
                return;
            }
            view.getViewTreeObserver().removeOnGlobalLayoutListener(this.n);
            this.n = null;
            this.o = null;
        } catch (Throwable th) {
            ho.c("LinkScrollView", "remove listen err: %s", th.getClass().getSimpleName());
        }
    }

    private void c() {
        this.i = 0;
        this.g.abortAnimation();
        this.d.abortAnimation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b() {
        int measuredHeight = this.f8112a.getMeasuredHeight();
        this.c = measuredHeight;
        return measuredHeight > 0;
    }

    private void b(float f) {
        this.g.fling(0, 0, 0, (int) f, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        invalidate();
    }

    private boolean a(View view, int i) {
        if (i >= 0 || getScrollY() < 0) {
            return false;
        }
        return !view.canScrollVertically(-1);
    }

    private boolean a(int i) {
        int measuredHeight = this.f8112a.getMeasuredHeight();
        this.c = measuredHeight;
        return measuredHeight > 0 && i > 0 && getScrollY() < this.c;
    }

    private void a(Context context) {
        try {
            if (this.j) {
                ho.b("LinkScrollView", "inner device, no need to listen input.");
                return;
            }
            Activity z = dd.z(context);
            if (!(z instanceof PPSActivity)) {
                ho.c("LinkScrollView", "activity is invalid.");
                return;
            }
            View decorView = z.getWindow().getDecorView();
            this.o = decorView;
            this.n = new hj(this, decorView);
            this.o.getViewTreeObserver().addOnGlobalLayoutListener(this.n);
        } catch (Throwable th) {
            ho.c("LinkScrollView", "listen input err: %s", th.getClass().getSimpleName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(float f) {
        if (Math.abs(f) > this.f) {
            this.i = f > 0.0f ? 1 : -1;
            this.d.fling(0, getScrollY(), 1, (int) f, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
            invalidate();
        }
    }

    public LinkScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = 0;
        this.p = new e.a() { // from class: com.huawei.openalliance.ad.views.linkscroll.LinkScrollView.1
            @Override // com.huawei.openalliance.ad.views.linkscroll.e.a
            public void a(View view) {
                if (view != null && LinkScrollView.this.b()) {
                    if (LinkScrollView.this.i != -1) {
                        ho.a("LinkScrollView", "fling orientation invalid, parent can not fling.");
                        return;
                    }
                    if (LinkScrollView.this.c == LinkScrollView.this.getScrollY() && LinkScrollView.this.g.computeScrollOffset()) {
                        float currVelocity = LinkScrollView.this.g.getCurrVelocity();
                        if (currVelocity >= 0.0f) {
                            currVelocity = -currVelocity;
                        }
                        LinkScrollView.this.g.abortAnimation();
                        LinkScrollView.this.a(currVelocity);
                    }
                }
            }
        };
        setOrientation(1);
        this.d = new OverScroller(context);
        this.g = new Scroller(context);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.e = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f = viewConfiguration.getScaledMinimumFlingVelocity();
        this.j = bz.b(context);
        a(context);
    }
}
