package com.huawei.pluginsocialshare.view.sharewatermark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.hjd;
import defpackage.koq;
import java.util.List;

/* loaded from: classes6.dex */
public class TrackSimpleView extends View {

    /* renamed from: a, reason: collision with root package name */
    private double f8551a;
    private boolean b;
    private Canvas c;
    private int d;
    private Bitmap e;
    private int f;
    private boolean g;
    private int h;
    private Paint i;
    private double j;
    private Path k;
    private List<hjd> l;
    private Path m;
    private double n;
    private float o;
    private double p;
    private int q;
    private int r;
    private Paint s;
    private Paint t;
    private int w;
    private int y;

    public TrackSimpleView(Context context) {
        super(context);
        this.o = 5.0f;
        this.h = 0;
        b();
    }

    public TrackSimpleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.o = 5.0f;
        this.h = 0;
        b();
    }

    void a(int i) {
        this.f = i;
        if (i != this.d && this.e != null) {
            this.c.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.s);
            e();
            invalidate();
        }
        this.d = this.f;
    }

    void e(float f) {
        this.o = f;
        this.i.setStrokeWidth(f);
        this.t.setStrokeWidth(this.o);
    }

    private void b() {
        Paint paint = new Paint(1);
        this.i = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.i.setStrokeWidth(this.o);
        this.i.setStrokeJoin(Paint.Join.ROUND);
        this.i.setAntiAlias(true);
        this.i.setPathEffect(new CornerPathEffect(15.0f));
        Paint paint2 = new Paint(1);
        this.t = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.t.setStrokeWidth(this.o);
        Paint paint3 = new Paint(1);
        this.s = paint3;
        paint3.setStyle(Paint.Style.FILL);
        this.s.setColor(0);
        this.s.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        this.f = -1;
        this.d = -1;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        d();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i) == 1073741824) {
            super.onMeasure(i, i);
        } else {
            super.onMeasure(i, i2);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (koq.b(this.l) || canvas == null) {
            return;
        }
        if (this.e == null) {
            this.e = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            this.c = new Canvas(this.e);
            e();
        }
        canvas.drawBitmap(this.e, 0.0f, 0.0f, (Paint) null);
    }

    private void e() {
        if (koq.c(this.l)) {
            this.t.setColor(this.f);
            this.i.setColor(this.f);
            float c = c(this.l.get(0).d);
            float b = b(this.l.get(0).b);
            float c2 = c(this.l.get(r2.size() - 1).d);
            float b2 = b(this.l.get(r3.size() - 1).b);
            this.c.drawPath(this.m, this.i);
            this.c.drawCircle(c, b, this.o * 1.5f, this.t);
            this.c.drawCircle(c, b, this.o * 1.0f, this.s);
            this.c.drawPath(this.k, this.i);
            this.c.drawCircle(c2, b2, this.o * 1.5f, this.t);
            this.c.drawCircle(c2, b2, this.o * 1.0f, this.s);
        }
    }

    private void d() {
        List<hjd> list;
        if (getWidth() == 0 || (list = this.l) == null || list.isEmpty()) {
            return;
        }
        a();
        Path path = this.m;
        if (path == null) {
            this.m = new Path();
        } else {
            path.reset();
        }
        Path path2 = this.k;
        if (path2 == null) {
            this.k = new Path();
        } else {
            path2.reset();
        }
        c();
    }

    private void c() {
        boolean z = false;
        hjd hjdVar = this.l.get(0);
        float c = c(hjdVar.d);
        float b = b(hjdVar.b);
        this.m.moveTo(c, b);
        for (hjd hjdVar2 : this.l) {
            float c2 = c(hjdVar2.d);
            float b2 = b(hjdVar2.b);
            if (z) {
                this.k.lineTo(c2, b2);
            } else {
                float abs = Math.abs(c - c2);
                float abs2 = Math.abs(b - b2);
                if (Math.sqrt((abs * abs) + (abs2 * abs2)) > this.o * 1.5f) {
                    this.m.lineTo(c2, b2);
                    this.k.moveTo(c2, b2);
                    z = true;
                } else {
                    this.m.lineTo(c2, b2);
                }
            }
        }
    }

    private float b(double d) {
        if (this.g) {
            int i = this.h;
            if (i == 1 || i == 2 || i == 3) {
                return this.r;
            }
            return this.w + ((this.r - r8) / 2.0f);
        }
        int i2 = this.r;
        double d2 = i2;
        double d3 = i2 - this.w;
        double d4 = this.p;
        return (float) (d2 - ((d3 * (d - d4)) / (this.f8551a - d4)));
    }

    private float c(double d) {
        if (this.b) {
            int i = this.h;
            if (i == 2) {
                return this.q;
            }
            if (i == 3) {
                return this.y;
            }
            return this.q + ((this.y - r8) / 2.0f);
        }
        int i2 = this.q;
        double d2 = this.y - i2;
        double d3 = this.j;
        return (float) (i2 + ((d2 * (d - d3)) / (this.n - d3)));
    }

    private void a() {
        hjd hjdVar = this.l.get(0);
        this.j = hjdVar.d;
        this.n = hjdVar.d;
        this.p = hjdVar.b;
        this.f8551a = hjdVar.b;
        for (hjd hjdVar2 : this.l) {
            if (hjdVar2.d < this.j) {
                this.j = hjdVar2.d;
            } else if (hjdVar2.d > this.n) {
                this.n = hjdVar2.d;
            } else {
                LogUtil.a("Track_TrackSimpleView", "mLeft and mRight is normal");
            }
            if (hjdVar2.b < this.p) {
                this.p = hjdVar2.b;
            } else if (hjdVar2.b > this.f8551a) {
                this.f8551a = hjdVar2.b;
            } else {
                LogUtil.a("Track_TrackSimpleView", "mTop and mBottom is normal");
            }
        }
        this.b = Math.abs(this.j - this.n) < 1.0E-6d;
        this.g = Math.abs(this.p - this.f8551a) < 1.0E-6d;
        double d = this.n - this.j;
        double d2 = this.f8551a - this.p;
        if (d > d2) {
            c((float) ((d - d2) / 2.0d));
        } else {
            b((float) ((d2 - d) / 2.0d));
        }
        this.q = getPaddingLeft();
        this.y = getWidth() - getPaddingRight();
        this.w = getPaddingTop();
        this.r = getHeight() - getPaddingBottom();
    }

    private void b(float f) {
        int i = this.h;
        if (i == 2) {
            this.n += f * 2.0f;
        } else {
            if (i == 3) {
                this.j -= f * 2.0f;
                return;
            }
            double d = f;
            this.n += d;
            this.j -= d;
        }
    }

    private void c(float f) {
        int i = this.h;
        if (i == 1 || i == 2 || i == 3) {
            this.f8551a += f * 2.0f;
            return;
        }
        double d = f;
        this.f8551a += d;
        this.p -= d;
    }

    private void d(List<hjd> list) {
        if (koq.b(list)) {
            return;
        }
        this.l = list;
        d();
        invalidate();
    }

    void b(List<hjd> list, int i) {
        this.h = i;
        d(list);
    }
}
