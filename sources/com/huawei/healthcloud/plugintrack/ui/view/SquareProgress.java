package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;
import health.compact.a.LanguageUtil;

/* loaded from: classes4.dex */
public class SquareProgress extends View {

    /* renamed from: a, reason: collision with root package name */
    private int f3801a;
    private boolean b;
    private float c;
    private int d;
    private Paint e;
    private float f;
    private Paint g;
    private PointF h;
    private int i;
    private int j;
    private int k;
    private Paint l;
    private float m;
    private int n;
    private float o;
    private int t;

    public SquareProgress(Context context) {
        this(context, null);
        b();
    }

    public SquareProgress(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SquareProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = 100;
        this.d = 30;
        this.b = false;
        bjA_(context, attributeSet, i);
        b();
    }

    private void bjA_(Context context, AttributeSet attributeSet, int i) {
        if (attributeSet == null) {
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100012_res_0x7f06016c, R.attr._2131100013_res_0x7f06016d, R.attr._2131100014_res_0x7f06016e, R.attr._2131100828_res_0x7f06049c, R.attr._2131100962_res_0x7f060522, R.attr._2131100965_res_0x7f060525, R.attr._2131100966_res_0x7f060526, R.attr._2131100970_res_0x7f06052a}, i, R.style.DefaultHealthSquareProgress);
        try {
            this.n = obtainStyledAttributes.getColor(5, 0);
            this.k = obtainStyledAttributes.getColor(6, 0);
            this.f3801a = obtainStyledAttributes.getColor(0, 0);
            this.c = obtainStyledAttributes.getDimension(1, 10.0f);
            this.o = obtainStyledAttributes.getDimension(7, 8.0f);
            this.b = obtainStyledAttributes.getBoolean(2, false);
            this.i = obtainStyledAttributes.getInt(3, 100);
            this.d = obtainStyledAttributes.getInt(4, 0);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private void b() {
        Paint paint = new Paint();
        this.l = paint;
        paint.setAntiAlias(true);
        this.l.setStyle(Paint.Style.STROKE);
        this.l.setStrokeWidth(this.o);
        this.l.setColor(this.k);
        Paint paint2 = new Paint();
        this.e = paint2;
        paint2.setAntiAlias(true);
        this.e.setColor(this.n);
        this.e.setStyle(Paint.Style.STROKE);
        this.e.setStrokeWidth(this.o);
        Paint paint3 = new Paint();
        this.g = paint3;
        paint3.setAntiAlias(true);
        this.g.setStyle(Paint.Style.FILL);
        this.g.setColor(this.f3801a);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.t = a(i);
        int d = d(i2);
        this.j = d;
        setMeasuredDimension(this.t, d);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        boolean bc = LanguageUtil.bc(getContext());
        this.m = ((this.t - getPaddingRight()) - getPaddingLeft()) - this.o;
        float paddingTop = (this.j - getPaddingTop()) - getPaddingBottom();
        float f = this.o;
        this.f = paddingTop - f;
        float f2 = f / 2.0f;
        float f3 = this.m;
        if (bc) {
            f3 = -f3;
        }
        bjz_(canvas, bc, f2, f3, bc ? -f2 : f2);
        if (this.b) {
            canvas.drawCircle(this.h.x, this.h.y, this.c * 0.5f, this.g);
        }
    }

    private void bjz_(Canvas canvas, boolean z, float f, float f2, float f3) {
        PointF pointF = new PointF(z ? this.t - getPaddingRight() : getPaddingLeft(), getPaddingTop() + f);
        PointF pointF2 = new PointF(pointF.x + f2, pointF.y);
        PointF pointF3 = new PointF(pointF2.x + f3, pointF2.y - f);
        PointF pointF4 = new PointF(pointF3.x, pointF3.y + this.f);
        PointF pointF5 = new PointF(pointF4.x + f3, pointF4.y + f);
        PointF pointF6 = new PointF(pointF5.x - f2, pointF5.y);
        PointF pointF7 = new PointF(pointF6.x - f3, pointF5.y - f);
        bjy_(canvas, new PointF[]{pointF, pointF2, pointF3, pointF4, pointF5, pointF6, pointF7, new PointF(pointF7.x, pointF7.y - this.f)});
        float f4 = (this.m + this.f) * 2.0f;
        Path path = new Path();
        float f5 = this.d;
        float f6 = this.i;
        float f7 = f5 / f6;
        if (f7 > 0.0f) {
            this.h = new PointF(pointF.x + (z ? ((-f4) * f5) / f6 : (f5 * f4) / f6), pointF.y);
            path.moveTo(pointF.x, pointF.y);
            float f8 = this.m;
            if (f7 > f8 / f4) {
                this.h = new PointF(pointF3.x, pointF3.y + (((this.d * f4) / this.i) - f8));
                path.lineTo(pointF2.x, pointF2.y);
                path.moveTo(pointF3.x, pointF3.y);
            }
            float f9 = this.f;
            float f10 = this.m;
            if (f7 > (f9 + f10) / f4) {
                float f11 = (((this.d * f4) / this.i) - f9) - f10;
                if (z) {
                    f11 = -f11;
                }
                this.h = new PointF(pointF5.x - f11, pointF5.y);
                path.lineTo(pointF4.x, pointF4.y);
                path.moveTo(pointF5.x, pointF5.y);
            }
            float f12 = this.m;
            float f13 = this.f;
            float f14 = f12 * 2.0f;
            if (f7 > (f14 + f13) / f4) {
                this.h = new PointF(pointF7.x, pointF7.y - Math.min((((f4 * this.d) / this.i) - f13) - f14, f13));
                path.lineTo(pointF6.x, pointF6.y);
                path.moveTo(pointF7.x, pointF7.y);
            }
            path.lineTo(this.h.x, this.h.y);
        }
        path.close();
        canvas.drawPath(path, this.l);
    }

    private void bjy_(Canvas canvas, PointF[] pointFArr) {
        Path path = new Path();
        for (int i = 0; i * 2 < pointFArr.length; i += 2) {
            path.moveTo(pointFArr[i].x, pointFArr[i].y);
            int i2 = i + 1;
            path.lineTo(pointFArr[i2].x, pointFArr[i2].y);
        }
        path.close();
        canvas.drawPath(path, this.e);
    }

    private int a(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        if (mode == 1073741824) {
            return size;
        }
        int suggestedMinimumWidth = getSuggestedMinimumWidth() + paddingLeft + paddingRight;
        return mode == Integer.MIN_VALUE ? Math.max(suggestedMinimumWidth, size) : suggestedMinimumWidth;
    }

    public void setProgress(int i) {
        this.d = i;
        invalidate();
    }

    private int d(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int paddingBottom = getPaddingBottom();
        int paddingTop = getPaddingTop();
        if (mode == 1073741824) {
            return size;
        }
        int suggestedMinimumHeight = getSuggestedMinimumHeight() + paddingBottom + paddingTop;
        return mode == Integer.MIN_VALUE ? Math.max(suggestedMinimumHeight, size) : suggestedMinimumHeight;
    }
}
