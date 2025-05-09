package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import java.util.List;

/* loaded from: classes8.dex */
public class TrackSimpleView extends View {

    /* renamed from: a, reason: collision with root package name */
    private Bitmap f3810a;
    private boolean b;
    private boolean c;
    private float d;
    private Canvas e;
    private int f;
    private float g;
    private float h;
    private int i;
    private Paint j;
    private List<PointF> k;
    private Path l;
    private Paint m;
    private Path n;
    private float o;
    private float p;
    private int q;
    private int r;
    private int s;
    private Paint t;
    private int u;

    public TrackSimpleView(Context context) {
        super(context);
        this.h = 5.0f;
        this.i = 0;
        a();
    }

    public TrackSimpleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = 5.0f;
        this.i = 0;
        a();
    }

    private void a() {
        Paint paint = new Paint(1);
        this.j = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.j.setStrokeWidth(this.h);
        this.j.setStrokeJoin(Paint.Join.ROUND);
        Paint paint2 = new Paint(1);
        this.m = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.m.setStrokeWidth(this.h);
        Paint paint3 = new Paint(1);
        this.t = paint3;
        paint3.setStyle(Paint.Style.FILL);
        this.t.setColor(0);
        this.t.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        this.f = -1;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        b();
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
        if (this.k.isEmpty() || canvas == null) {
            return;
        }
        if (this.f3810a == null) {
            this.f3810a = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            this.e = new Canvas(this.f3810a);
            d();
        }
        canvas.drawBitmap(this.f3810a, 0.0f, 0.0f, (Paint) null);
    }

    private void d() {
        if (koq.b(this.k)) {
            LogUtil.h("Track_TrackSimpleView", "CollectionUtils.isEmpty(mPoints)");
            return;
        }
        this.m.setColor(this.f);
        this.j.setColor(this.f);
        float b = b(this.k.get(0).x);
        float e = e(this.k.get(0).y);
        float b2 = b(this.k.get(r2.size() - 1).x);
        float e2 = e(this.k.get(r3.size() - 1).y);
        this.e.drawPath(this.l, this.j);
        this.e.drawCircle(b, e, this.h * 1.5f, this.m);
        this.e.drawCircle(b, e, this.h * 1.0f, this.t);
        this.e.drawPath(this.n, this.j);
        this.e.drawCircle(b2, e2, this.h * 1.5f, this.m);
        this.e.drawCircle(b2, e2, this.h * 1.0f, this.t);
    }

    private void b() {
        List<PointF> list;
        if (getWidth() == 0 || (list = this.k) == null || list.isEmpty()) {
            return;
        }
        e();
        Path path = this.l;
        if (path == null) {
            this.l = new Path();
        } else {
            path.reset();
        }
        Path path2 = this.n;
        if (path2 == null) {
            this.n = new Path();
        } else {
            path2.reset();
        }
        c();
    }

    private void c() {
        boolean z = false;
        PointF pointF = this.k.get(0);
        float b = b(pointF.x);
        float e = e(pointF.y);
        this.l.moveTo(b, e);
        for (PointF pointF2 : this.k) {
            float b2 = b(pointF2.x);
            float e2 = e(pointF2.y);
            if (z) {
                this.n.lineTo(b2, e2);
            } else {
                float abs = Math.abs(b - b2);
                float abs2 = Math.abs(e - e2);
                if (Math.sqrt((abs * abs) + (abs2 * abs2)) > this.h * 1.5f) {
                    this.l.lineTo(b2, e2);
                    this.n.moveTo(b2, e2);
                    z = true;
                } else {
                    this.l.lineTo(b2, e2);
                }
            }
        }
    }

    private float e(float f) {
        if (this.c) {
            int i = this.i;
            if (i == 1 || i == 2 || i == 3) {
                return this.s;
            }
            return this.u + ((this.s - r4) / 2.0f);
        }
        int i2 = this.s;
        float f2 = i2;
        float f3 = i2 - this.u;
        float f4 = this.p;
        return f2 - ((f3 * (f - f4)) / (this.d - f4));
    }

    private float b(float f) {
        int i;
        if (this.b) {
            int i2 = this.i;
            if (i2 == 2) {
                i = this.q;
            } else if (i2 == 3) {
                i = this.r;
            } else {
                return this.q + ((this.r - r4) / 2.0f);
            }
            return i;
        }
        int i3 = this.q;
        float f2 = i3;
        float f3 = this.r - i3;
        float f4 = this.g;
        return f2 + ((f3 * (f - f4)) / (this.o - f4));
    }

    private void e() {
        PointF pointF = this.k.get(0);
        this.g = pointF.x;
        this.o = pointF.x;
        this.p = pointF.y;
        this.d = pointF.y;
        for (PointF pointF2 : this.k) {
            if (pointF2.x < this.g) {
                this.g = pointF2.x;
            } else if (pointF2.x > this.o) {
                this.o = pointF2.x;
            } else {
                LogUtil.a("Track_TrackSimpleView", "mLeft and mRight is normal");
            }
            if (pointF2.y < this.p) {
                this.p = pointF2.y;
            } else if (pointF2.y > this.d) {
                this.d = pointF2.y;
            } else {
                LogUtil.a("Track_TrackSimpleView", "mTop and mBottom is normal");
            }
        }
        this.b = ((double) Math.abs(this.g - this.o)) < 1.0E-6d;
        this.c = ((double) Math.abs(this.p - this.d)) < 1.0E-6d;
        float f = this.o - this.g;
        float f2 = this.d - this.p;
        if (f > f2) {
            a((f - f2) / 2.0f);
        } else {
            c((f2 - f) / 2.0f);
        }
        this.q = getPaddingLeft();
        this.r = getWidth() - getPaddingRight();
        this.u = getPaddingTop();
        this.s = getHeight() - getPaddingBottom();
    }

    private void c(float f) {
        int i = this.i;
        if (i == 2) {
            this.o += f * 2.0f;
        } else if (i == 3) {
            this.g -= f * 2.0f;
        } else {
            this.o += f;
            this.g -= f;
        }
    }

    private void a(float f) {
        int i = this.i;
        if (i == 1 || i == 2 || i == 3) {
            this.d += f * 2.0f;
        } else {
            this.d += f;
            this.p -= f;
        }
    }
}
