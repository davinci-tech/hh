package com.huawei.ui.commonui.linechart.view.dashboard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$styleable;
import java.util.List;

/* loaded from: classes6.dex */
public class DashboardRingView extends View {

    /* renamed from: a, reason: collision with root package name */
    private int f8903a;
    private int b;
    private float e;
    private float f;
    private float g;
    private Paint h;
    private int i;
    private Xfermode j;
    private int k;
    private Path l;
    private List<b> m;
    private int n;
    private RectF o;
    private float r;
    private int s;
    private static final double d = Math.sqrt(3.0d);
    private static final int c = Color.argb(25, 0, 0, 0);

    public DashboardRingView(Context context) {
        this(context, null);
    }

    public DashboardRingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DashboardRingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.n = 50;
        this.k = c;
        this.i = 100;
        this.f = 100.0f;
        this.g = 0.0f;
        this.r = 8.333334f;
        this.f8903a = -16777216;
        cDn_(context, attributeSet, i, 0);
    }

    public void setRingArcAreaList(List<b> list) {
        this.m = list;
    }

    public void setMaxValue(float f) {
        this.f = f;
    }

    public void setMinValue(float f) {
        this.g = f;
    }

    public void setCurrentValue(float f) {
        this.e = f;
    }

    public void a() {
        postInvalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            this.s = size;
            this.i = (int) ((size - this.n) / 2.0f);
        } else if (mode == Integer.MIN_VALUE) {
            this.s = (int) ((this.i * 2.0f) + this.n);
        }
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode2 == 1073741824) {
            this.b = size2;
            this.i = Math.min((int) ((size2 - this.n) / 2.0f), this.i);
        } else if (mode2 == Integer.MIN_VALUE) {
            this.b = (int) ((this.i * 2.0f) + this.n);
        }
        setMeasuredDimension(this.s, this.b);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f = this.i;
        float f2 = (this.s / 2.0f) - f;
        float f3 = (this.b / 2.0f) - f;
        float f4 = f * 2.0f;
        this.o.set(f2, f3, f4 + f2, f4 + f3);
        cDi_(canvas, this.k, 120.0f, 420.0f);
        cDk_(canvas);
        cDl_(canvas);
    }

    int getRadius() {
        return this.i;
    }

    private void cDn_(Context context, AttributeSet attributeSet, int i, int i2) {
        setLayerType(1, null);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.DashboardRingView, i, i2);
        if (obtainStyledAttributes.hasValue(R$styleable.DashboardRingView_ringWidth)) {
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R$styleable.DashboardRingView_ringWidth, 50);
            this.n = dimensionPixelSize;
            this.r = dimensionPixelSize * 0.16666667f;
        }
        if (obtainStyledAttributes.hasValue(R$styleable.DashboardRingView_ringBackgroundColor)) {
            this.k = obtainStyledAttributes.getColor(R$styleable.DashboardRingView_ringBackgroundColor, c);
        }
        if (obtainStyledAttributes.hasValue(R$styleable.DashboardRingView_circleRadius)) {
            this.i = obtainStyledAttributes.getDimensionPixelSize(R$styleable.DashboardRingView_circleRadius, 100);
        }
        if (obtainStyledAttributes.hasValue(R$styleable.DashboardRingView_maxValue)) {
            this.f = obtainStyledAttributes.getFloat(R$styleable.DashboardRingView_maxValue, 100.0f);
        }
        if (obtainStyledAttributes.hasValue(R$styleable.DashboardRingView_minValue)) {
            this.g = obtainStyledAttributes.getFloat(R$styleable.DashboardRingView_minValue, 0.0f);
        }
        if (obtainStyledAttributes.hasValue(R$styleable.DashboardRingView_markColor)) {
            this.f8903a = obtainStyledAttributes.getColor(R$styleable.DashboardRingView_markColor, -16777216);
        }
        obtainStyledAttributes.recycle();
        this.h = new Paint();
        this.o = new RectF();
        this.e = this.g;
        this.j = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    }

    private void cDl_(Canvas canvas) {
        float b2 = b(this.e);
        float f = this.s / 2.0f;
        float f2 = this.b / 2.0f;
        canvas.save();
        canvas.rotate(b2, f, f2);
        this.h.reset();
        this.h.setAntiAlias(true);
        this.h.setStyle(Paint.Style.FILL_AND_STROKE);
        this.h.setColor(-1);
        this.h.setStrokeCap(Paint.Cap.ROUND);
        this.h.setStrokeJoin(Paint.Join.ROUND);
        float f3 = this.n;
        float f4 = this.r;
        Path cDm_ = cDm_(f3 - f4, f4, f, f2);
        Paint paint = this.h;
        float f5 = this.r;
        paint.setStrokeWidth((2.0f * f5) + f5);
        this.h.setXfermode(this.j);
        canvas.drawPath(cDm_, this.h);
        this.h.reset();
        this.h.setAntiAlias(true);
        this.h.setStyle(Paint.Style.FILL_AND_STROKE);
        this.h.setColor(this.f8903a);
        this.h.setStrokeCap(Paint.Cap.ROUND);
        this.h.setStrokeJoin(Paint.Join.ROUND);
        this.h.setStrokeWidth(this.r);
        canvas.drawPath(cDm_, this.h);
        canvas.restore();
    }

    private Path cDm_(float f, float f2, float f3, float f4) {
        Path path = this.l;
        if (path == null) {
            this.l = new Path();
        } else {
            path.reset();
        }
        float f5 = (f3 + this.i) - f2;
        float f6 = f / 2.0f;
        float f7 = (float) (f5 - (f6 * d));
        this.l.moveTo(f5, f4);
        this.l.lineTo(f7, f4 + f6);
        this.l.lineTo(f7, f4 - f6);
        this.l.close();
        return this.l;
    }

    private void cDk_(Canvas canvas) {
        List<b> list = this.m;
        if (list == null || list.isEmpty()) {
            LogUtil.h("DashboardRingView", "mRingArcAreaList is null or empty");
            return;
        }
        b bVar = null;
        for (b bVar2 : this.m) {
            if ((this.e < bVar2.c() || this.e >= bVar2.d() || bVar != null) && !(this.e >= this.f && b(bVar2.d(), this.f) && bVar == null)) {
                cDi_(canvas, bVar2.a(), b(bVar2.c()), b(bVar2.d()));
            } else {
                bVar = bVar2;
            }
        }
        if (bVar != null) {
            float b2 = b(bVar.c());
            float b3 = b(bVar.d());
            cDh_(-1, this.n + (this.r * 2.0f), this.j);
            canvas.drawArc(this.o, b2, b3 - b2, false, this.h);
            cDi_(canvas, bVar.a(), b2, b3);
        }
    }

    private float b(float f) {
        float f2 = this.g;
        if (f <= f2) {
            return 120.0f;
        }
        float f3 = this.f;
        if (f >= f3) {
            return 420.0f;
        }
        return (((f - f2) / (f3 - f2)) * 300.0f) + 120.0f;
    }

    private void cDi_(Canvas canvas, int i, float f, float f2) {
        cDj_(canvas, i, this.n, f, f2);
    }

    private void cDj_(Canvas canvas, int i, float f, float f2, float f3) {
        cDh_(i, f, null);
        canvas.drawArc(this.o, f2, f3 - f2, false, this.h);
    }

    private void cDh_(int i, float f, Xfermode xfermode) {
        this.h.reset();
        this.h.setAntiAlias(true);
        this.h.setStyle(Paint.Style.STROKE);
        this.h.setStrokeWidth(f);
        this.h.setStrokeCap(Paint.Cap.ROUND);
        this.h.setColor(i);
        this.h.setXfermode(xfermode);
    }

    private boolean b(float f, float f2) {
        return Math.abs(f - f2) < 1.0E-6f;
    }

    public static class b {
        private final float b;
        private final float c;
        private final int d;

        public b(float f, float f2, int i) {
            this.c = f;
            this.b = f2;
            this.d = i;
        }

        public float c() {
            return this.c;
        }

        public float d() {
            return this.b;
        }

        public int a() {
            return this.d;
        }
    }
}
