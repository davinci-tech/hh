package com.huawei.openalliance.ad.views.linkscroll;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.WebView;
import android.widget.Scroller;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public class e extends WebView implements com.huawei.openalliance.ad.views.linkscroll.a {

    /* renamed from: a, reason: collision with root package name */
    private int f8115a;
    private final int[] b;
    private final int[] c;
    private int d;
    private c e;
    private VelocityTracker f;
    private int g;
    private int h;
    private RectF i;
    private Paint j;
    private float[] k;
    private int l;
    private int m;
    private boolean n;
    private Scroller o;
    private a p;
    private int q;

    public interface a {
        void a(View view);
    }

    private boolean c(int i) {
        return i == 5 || i == 1 || i == 3;
    }

    public void setSupportWebViewRadius(boolean z) {
        this.n = z;
    }

    public void setScrollListener(a aVar) {
        this.p = aVar;
    }

    public void setRadiusArray(float[] fArr) {
        this.k = fArr;
    }

    public void setLinkScrollEnabled(boolean z) {
        c cVar = this.e;
        if (cVar != null) {
            cVar.a(z);
        }
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent obtain;
        int a2;
        int y;
        try {
            obtain = MotionEvent.obtain(motionEvent);
            a2 = a(motionEvent);
            if (a2 == 0) {
                this.d = 0;
                b();
            }
            y = (int) motionEvent.getY();
            motionEvent.offsetLocation(0.0f, this.d);
        } catch (Throwable th) {
            ho.c("LinkScrollWebView", "onTouch error: %s", th.getClass().getSimpleName());
        }
        if (a2 == 0) {
            this.q = getScrollY();
            this.f8115a = y;
            b(2);
            c();
            this.f.addMovement(motionEvent);
            return super.onTouchEvent(motionEvent);
        }
        boolean z = true;
        if (a2 == 2) {
            d();
            this.f.addMovement(motionEvent);
            int i = this.f8115a - y;
            int scrollY = getScrollY();
            if (a(0, i, this.c, this.b)) {
                i -= this.c[1];
                obtain.offsetLocation(0.0f, this.b[1]);
                this.d += this.b[1];
            }
            this.f8115a = y - this.b[1];
            int max = Math.max(0, scrollY + i);
            int i2 = i - (max - scrollY);
            if (a(0, max - i2, 0, i2, this.b)) {
                int i3 = this.f8115a;
                int i4 = this.b[1];
                this.f8115a = i3 - i4;
                obtain.offsetLocation(0.0f, i4);
                this.d += this.b[1];
            }
            if (this.c[1] == 0 && this.b[1] == 0) {
                obtain.recycle();
                return super.onTouchEvent(obtain);
            }
        } else if (c(a2)) {
            if (this.q != getScrollY()) {
                z = false;
            }
            d();
            this.f.addMovement(motionEvent);
            this.f.computeCurrentVelocity(1000, this.g);
            int yVelocity = (int) this.f.getYVelocity();
            e();
            if (Math.abs(yVelocity) > this.h) {
                a(-yVelocity, z);
            }
            a();
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.l = i;
        this.m = i2;
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        a aVar;
        super.onScrollChanged(i, i2, i3, i4);
        if (canScrollVertically(-1) || (aVar = this.p) == null) {
            return;
        }
        aVar.a(this);
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.n) {
            Path path = new Path();
            path.setFillType(Path.FillType.INVERSE_WINDING);
            this.i.set(getScrollX(), getScrollY(), getScrollX() + this.l, getScrollY() + this.m);
            path.addRoundRect(this.i, this.k, Path.Direction.CW);
            canvas.drawPath(path, this.j);
        }
    }

    @Override // android.webkit.WebView
    public void destroy() {
        super.destroy();
        this.e = null;
        this.p = null;
        this.o = null;
    }

    @Override // android.webkit.WebView, android.view.View
    public void computeScroll() {
        Scroller scroller = this.o;
        if (scroller == null || !scroller.computeScrollOffset()) {
            return;
        }
        scrollTo(0, this.o.getCurrY());
        invalidate();
    }

    public boolean b(int i) {
        c cVar = this.e;
        if (cVar != null) {
            return cVar.a(i);
        }
        return false;
    }

    public boolean a(int i, int i2, int[] iArr, int[] iArr2) {
        c cVar = this.e;
        if (cVar != null) {
            return cVar.a(i, i2, iArr, iArr2);
        }
        return false;
    }

    public boolean a(int i, int i2, int i3, int i4, int[] iArr) {
        c cVar = this.e;
        if (cVar != null) {
            return cVar.a(i, i2, i3, i4, iArr);
        }
        return false;
    }

    public boolean a(float f, float f2) {
        c cVar = this.e;
        if (cVar != null) {
            return cVar.a(f, f2);
        }
        return false;
    }

    public void a(int i) {
        ho.a("LinkScrollWebView", "flingScroll");
        Scroller scroller = this.o;
        if (scroller != null) {
            scroller.fling(0, getScrollY(), 0, i, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        invalidate();
    }

    public void a() {
        c cVar = this.e;
        if (cVar != null) {
            cVar.c();
        }
    }

    int a(MotionEvent motionEvent) {
        return motionEvent.getAction() & 255;
    }

    private void e() {
        VelocityTracker velocityTracker = this.f;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f = null;
        }
    }

    private void d() {
        if (this.f == null) {
            this.f = VelocityTracker.obtain();
        }
    }

    private void c() {
        VelocityTracker velocityTracker = this.f;
        if (velocityTracker == null) {
            this.f = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
    }

    private void b() {
        ho.a("LinkScrollWebView", "abortFlingScroll");
        Scroller scroller = this.o;
        if (scroller != null) {
            scroller.abortAnimation();
        }
    }

    private void a(Context context) {
        this.e = new c(this);
        setLinkScrollEnabled(true);
        this.o = new Scroller(getContext());
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.g = viewConfiguration.getScaledMaximumFlingVelocity();
        this.h = viewConfiguration.getScaledMinimumFlingVelocity();
        this.q = getScrollY();
        this.i = new RectF();
        Paint paint = new Paint();
        this.j = paint;
        paint.setStyle(Paint.Style.FILL);
        this.j.setAntiAlias(true);
        this.j.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    private void a(int i, boolean z) {
        if (z) {
            ho.b("LinkScrollWebView", "webview inner scroll");
        }
        if (a(0.0f, i) || z) {
            return;
        }
        a(i);
    }

    public e(Context context) {
        super(context);
        this.b = new int[2];
        this.c = new int[2];
        this.q = -1;
        a(context);
    }
}
