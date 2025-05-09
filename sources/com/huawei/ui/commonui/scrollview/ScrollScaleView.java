package com.huawei.ui.commonui.scrollview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.Scroller;
import androidx.core.view.GestureDetectorCompat;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import defpackage.koq;
import defpackage.mld;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class ScrollScaleView extends View {

    /* renamed from: a, reason: collision with root package name */
    private static final int f8945a = Color.rgb(251, 101, 34);
    private Paint aa;
    private int ab;
    private float ac;
    private int ad;
    private Scroller ae;
    private int af;
    private int ah;
    private int ai;
    private Context b;
    private int c;
    protected int d;
    private int e;
    private int f;
    private List<String> g;
    private GestureDetectorCompat h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private int p;
    private int q;
    private float r;
    private boolean s;
    private float t;
    private int u;
    private int v;
    private int w;
    private OnSelectedListener x;
    private int y;
    private int z;

    public interface OnSelectedListener {
        void onSelected(List<String> list, int i);
    }

    public ScrollScaleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScrollScaleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.m = true;
        this.ad = 24;
        int i2 = f8945a;
        this.ah = i2;
        this.c = i2;
        this.af = 10;
        this.k = true;
        this.i = true;
        this.s = true;
        this.w = -1;
        this.ab = 10;
        this.l = true;
        this.b = context;
        this.f = context.getResources().getColor(R$color.scale_unselected_color);
        Paint paint = new Paint(1);
        this.aa = paint;
        paint.setStyle(Paint.Style.FILL);
        this.aa.setColor(-16777216);
        this.h = new GestureDetectorCompat(getContext(), new b());
        this.ae = new Scroller(getContext());
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < 40; i3++) {
            arrayList.add(Integer.toString(i3));
        }
        b(arrayList, 5, 40);
        this.i = false;
        this.j = true;
    }

    public void setCustomStartColor(int i) {
        this.c = i;
        this.ah = i;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i = this.af / 2;
        int i2 = this.ai / 2;
        for (int i3 = 1; i3 <= i + 1 && i3 <= i2; i3++) {
            int i4 = this.d;
            int i5 = i4 - i3;
            int i6 = i5 < 0 ? (this.ai + i4) - i3 : i5;
            if (this.i) {
                cFM_(canvas, i6, -i3, 0, true);
            } else if (i5 >= 0) {
                cFM_(canvas, i6, -i3, 0, true);
            }
            int i7 = this.d;
            int i8 = this.ai;
            int i9 = i7 + i3;
            int i10 = i9 >= i8 ? i9 - i8 : i9;
            if (this.i) {
                cFM_(canvas, i10, i3, 0, true);
            } else if (i9 < i8) {
                cFM_(canvas, i10, i3, 0, true);
            }
        }
        int i11 = this.d;
        if (i11 - 3 > 0) {
            cFM_(canvas, i11, -3, 1, false);
        }
        int i12 = this.d;
        if (i12 - 2 > 0) {
            cFM_(canvas, i12, -2, 2, false);
        }
        int i13 = this.d;
        if (i13 - 1 > 0) {
            cFM_(canvas, i13, -1, 3, false);
        }
        cFM_(canvas, this.d, 0, 4, true);
        int i14 = this.d;
        if (i14 + 1 < this.ai) {
            cFM_(canvas, i14, 1, 3, false);
        }
        int i15 = this.d;
        if (i15 + 2 < this.ai) {
            cFM_(canvas, i15, 2, 2, false);
        }
        int i16 = this.d;
        if (i16 + 3 < this.ai) {
            cFM_(canvas, i16, 3, 1, false);
        }
    }

    public void setNoScroll(boolean z) {
        this.s = z;
        if (z) {
            int i = this.c;
            if (i == 0) {
                i = this.b.getResources().getColor(R$color.scale_selected_color);
            }
            this.ah = i;
        } else {
            this.ah = -7829368;
        }
        invalidate();
    }

    private void cFM_(Canvas canvas, int i, int i2, int i3, boolean z) {
        this.aa.setTextAlign(Paint.Align.CENTER);
        this.aa.setTextSize(this.ad);
        int i4 = this.e;
        int i5 = this.q;
        float f = i4 + (i2 * i5) + (i5 / 2);
        float f2 = this.ac;
        c(i2);
        cFO_(canvas, f + f2, i3, i, z);
    }

    private void cFO_(Canvas canvas, float f, int i, int i2, boolean z) {
        if (i == 0) {
            cFN_(canvas, f);
        } else if (i == 1) {
            cFL_(canvas, f, 5, 32);
        } else if (i == 2) {
            cFL_(canvas, f, 6, 30);
        } else if (i == 3) {
            cFL_(canvas, f, 7, 28);
        } else if (i == 4) {
            cFL_(canvas, f, 10, 25);
        }
        if (i2 % this.ab == 0 && z) {
            Context context = BaseApplication.getContext();
            this.aa.setTextSize(mld.d(context, 11.0f));
            int i3 = this.ab;
            if (i3 <= 0) {
                return;
            }
            int i4 = i2 / i3;
            if (this.l && koq.d(this.g, i4)) {
                canvas.drawText(this.g.get(i4), f, mld.d(context, 23.0f), this.aa);
            }
        }
    }

    private void cFN_(Canvas canvas, float f) {
        Context context = BaseApplication.getContext();
        int d = mld.d(context, 1.0f);
        Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(d);
        paint.setColor(this.f);
        int i = (int) f;
        int i2 = d / 2;
        Point point = new Point(i, mld.d(context, 33.0f) + i2);
        Point point2 = new Point(i, mld.d(context, 43.0f) - i2);
        canvas.drawLine(point.x, point.y, point2.x, point2.y, paint);
    }

    private void cFL_(Canvas canvas, float f, int i, int i2) {
        Context context = BaseApplication.getContext();
        Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.ah);
        paint.setStrokeWidth(i);
        int i3 = i / 2;
        int i4 = (int) f;
        Point point = new Point(i4, mld.d(context, i2) + i3);
        Point point2 = new Point(i4, mld.d(context, 43.0f) - i3);
        float f2 = i3;
        canvas.drawCircle(point.x, point.y, f2, paint);
        canvas.drawCircle(point2.x, point2.y, f2, paint);
        canvas.drawLine(point.x, point.y, point2.x, point2.y, paint);
    }

    private void c(int i) {
        int i2 = this.f;
        if (i == -1 || i == 1) {
            if ((i != -1 || this.ac >= 0.0f) && (i != 1 || this.ac <= 0.0f)) {
                i2 = e(this.ah, this.f, (this.q - Math.abs(this.ac)) / this.q);
            }
        } else if (i == 0) {
            i2 = e(this.ah, this.f, Math.abs(this.ac) / this.q);
        }
        this.aa.setColor(i2);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.z = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        this.v = measuredHeight;
        int i3 = this.af;
        this.p = measuredHeight / i3;
        int i4 = this.z / i3;
        this.q = i4;
        this.e = (i3 / 2) * i4;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        this.h.onTouchEvent(motionEvent);
        if (!this.s) {
            return false;
        }
        boolean z = this.l;
        this.l = true;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            cFP_(motionEvent, !z);
        } else if (actionMasked == 1) {
            if (this.m) {
                this.r = motionEvent.getX();
            } else {
                this.t = motionEvent.getY();
            }
            c();
        } else if (actionMasked == 2) {
            if (this.m) {
                if (Math.abs(motionEvent.getX() - this.r) < 0.1f) {
                    return true;
                }
                this.ac += motionEvent.getX() - this.r;
                this.r = motionEvent.getX();
            } else {
                if (Math.abs(motionEvent.getY() - this.t) < 0.1f) {
                    return true;
                }
                this.ac += motionEvent.getY() - this.t;
                this.t = motionEvent.getY();
            }
            b();
            invalidate();
        }
        return true;
    }

    private void cFP_(MotionEvent motionEvent, boolean z) {
        ViewParent parent;
        if (this.j && (parent = getParent()) != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        e();
        if (this.m) {
            this.r = motionEvent.getX();
        } else {
            this.t = motionEvent.getY();
        }
        if (z) {
            this.w = -1;
            d();
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.ae.computeScrollOffset()) {
            if (this.m) {
                this.ac = (this.ac + this.ae.getCurrX()) - this.y;
                this.y = this.ae.getCurrX();
            } else {
                this.ac = (this.ac + this.ae.getCurrY()) - this.u;
                this.u = this.ae.getCurrY();
            }
            b();
            invalidate();
            return;
        }
        if (this.o) {
            this.o = false;
            c();
        } else if (this.n) {
            this.n = false;
            d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.o = false;
        this.n = false;
        this.ae.abortAnimation();
    }

    private void b() {
        int i;
        if (this.m) {
            i = this.q;
        } else {
            i = this.p;
        }
        float f = this.ac;
        float f2 = i;
        if (f >= f2) {
            int i2 = this.d - 1;
            this.d = i2;
            if (i2 < 0) {
                if (this.i) {
                    this.d = this.ai - 1;
                    this.ac = 0.0f;
                } else {
                    this.d = 0;
                    this.ac = f2;
                    a();
                }
            } else {
                this.ac = 0.0f;
            }
        } else {
            float f3 = -i;
            if (f <= f3) {
                int i3 = this.d + 1;
                this.d = i3;
                int i4 = this.ai;
                if (i3 >= i4) {
                    if (this.i) {
                        this.d = 0;
                        this.ac = 0.0f;
                    } else {
                        this.d = i4 - 1;
                        this.ac = f3;
                        a();
                    }
                } else {
                    this.ac = 0.0f;
                }
            }
        }
        d();
    }

    private void a() {
        if (this.o) {
            this.ae.forceFinished(true);
        }
        if (this.n) {
            b(this.ac, 0);
        }
    }

    private void c() {
        int i;
        if (!this.ae.isFinished() || this.o) {
            return;
        }
        e();
        if (this.m) {
            i = this.q;
        } else {
            i = this.p;
        }
        LogUtil.a("ScrollScaleView", "value", Integer.toString(i));
        float f = this.ac;
        if (f > 0.0f) {
            if (f < ((float) (i / 2.0d))) {
                b(f, 0);
                return;
            } else {
                b(f, i);
                return;
            }
        }
        if ((-f) < ((float) (i / 2.0d))) {
            b(f, 0);
        } else {
            b(f, -i);
        }
    }

    private void b(float f, int i) {
        if (this.m) {
            int i2 = (int) f;
            this.y = i2;
            this.n = true;
            this.ae.startScroll(i2, 0, 0, 0);
            this.ae.setFinalX(i);
            invalidate();
            return;
        }
        int i3 = (int) f;
        this.u = i3;
        this.n = true;
        this.ae.startScroll(0, i3, 0, 0);
        this.ae.setFinalY(i);
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(float f, float f2) {
        if (this.m) {
            int i = (int) f;
            this.y = i;
            this.o = true;
            int i2 = this.q;
            this.ae.fling(i, 0, (int) f2, 0, i2 * (-1000), i2 * 1000, 0, 0);
            invalidate();
            return;
        }
        int i3 = (int) f;
        this.u = i3;
        this.o = true;
        int i4 = this.p;
        this.ae.fling(0, i3, 0, (int) f2, 0, 0, i4 * (-10), i4 * 10);
        invalidate();
    }

    private void d() {
        int i = this.w;
        int i2 = this.d;
        if (i == i2) {
            return;
        }
        this.w = i2;
        if (this.x != null) {
            if (LanguageUtil.bc(this.b)) {
                this.x.onSelected(this.g, ((this.g.size() - 1) * this.ab) - this.d);
                return;
            }
            this.x.onSelected(this.g, this.d);
        }
    }

    /* loaded from: classes9.dex */
    class b extends GestureDetector.SimpleOnGestureListener {
        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        private b() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (!ScrollScaleView.this.k) {
                return true;
            }
            ScrollScaleView.this.e();
            if (ScrollScaleView.this.m) {
                ScrollScaleView scrollScaleView = ScrollScaleView.this;
                scrollScaleView.b(scrollScaleView.ac, f);
                return true;
            }
            ScrollScaleView scrollScaleView2 = ScrollScaleView.this;
            scrollScaleView2.b(scrollScaleView2.ac, f2);
            return true;
        }
    }

    private void b(List<String> list, int i, int i2) {
        if (list == null) {
            this.g = new ArrayList();
        }
        LogUtil.a("ScrollScaleView", "ratio = ", Integer.valueOf(i), "; avaliablecount=", Integer.valueOf(i2));
        if (!LanguageUtil.bc(this.b)) {
            LogUtil.a("ScrollScaleView", "is not RT language");
            this.g = list;
        } else if (list != null) {
            this.g = b(list);
        }
        this.ab = i;
        List<String> list2 = this.g;
        if (list2 == null || list2.size() == 0) {
            this.g = new ArrayList();
            this.ai = 0;
        } else {
            this.ai = ((this.g.size() - 1) * i) + 1;
        }
        LogUtil.a("ScrollScaleView", "mSumScale==", Integer.valueOf(this.ai));
        this.af = i2;
        if (i2 < 0) {
            this.af = 0;
        }
        invalidate();
    }

    public void setData(List<String> list, int i, int i2) {
        if (list == null) {
            this.g = new ArrayList();
        }
        if (!LanguageUtil.bc(this.b)) {
            this.g = list;
        } else if (list != null) {
            this.g = b(list);
        }
        this.ab = i;
        List<String> list2 = this.g;
        if (list2 == null || list2.size() == 0) {
            this.g = new ArrayList();
            this.ai = 0;
        } else {
            this.ai = ((this.g.size() - 1) * i) + 1;
        }
        this.af = i2;
        if (i2 < 0) {
            this.af = 0;
        }
        invalidate();
    }

    private List<String> b(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (int size = list.size() - 1; size >= 0; size--) {
            arrayList.add(list.get(size));
        }
        return arrayList;
    }

    public void setSelectedPosition(int i) {
        if (LanguageUtil.bc(this.b)) {
            i = ((this.g.size() - 1) * this.ab) - i;
        }
        if (i < 0 || i > this.ai - 1 || i == this.d) {
            return;
        }
        this.d = i;
        invalidate();
        d();
    }

    public void setOnSelectedListener(OnSelectedListener onSelectedListener) {
        this.x = onSelectedListener;
    }

    private int e(int i, int i2, float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        return Color.argb(Math.round(Color.alpha(i) + ((Color.alpha(i2) - Color.alpha(i)) * f)), Math.round(Color.red(i) + ((Color.red(i2) - Color.red(i)) * f)), Math.round(Color.green(i) + ((Color.green(i2) - Color.green(i)) * f)), Math.round(Color.blue(i) + ((Color.blue(i2) - Color.blue(i)) * f)));
    }

    public void setIsShowText(boolean z) {
        this.l = z;
    }

    public boolean getIsShowText() {
        return this.l;
    }

    public int getSumScale() {
        return this.ai;
    }
}
