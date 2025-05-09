package com.huawei.ui.commonui.line;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$styleable;

/* loaded from: classes6.dex */
public class HealthLine extends View {

    /* renamed from: a, reason: collision with root package name */
    private PointF f8850a;
    private int b;
    private float c;
    private PointF d;
    private int e;
    private Paint f;
    private PointF g;
    private int h;
    private float i;
    private float j;
    private int m;

    public HealthLine(Context context) {
        this(context, null);
        e();
    }

    public HealthLine(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = new PointF();
        this.d = new PointF();
        this.f8850a = new PointF();
        if (attributeSet == null) {
            LogUtil.h("CommonUI_HealthLine", "HealthLine AttributeSet is null");
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.health_line);
        try {
            this.h = obtainStyledAttributes.getInteger(R$styleable.health_line_line_type, 0);
            this.b = obtainStyledAttributes.getColor(R$styleable.health_line_line_color, -1);
            this.i = obtainStyledAttributes.getDimension(R$styleable.health_line_line_height, 50.0f);
            this.j = obtainStyledAttributes.getDimension(R$styleable.health_line_line_stroke_width, 1.0f);
            this.c = obtainStyledAttributes.getFloat(R$styleable.health_line_conner_angle, 0.0f);
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("CommonUI_HealthLine", "Resources NotFoundException.");
        }
        obtainStyledAttributes.recycle();
        e();
    }

    private void e() {
        Paint paint = new Paint();
        this.f = paint;
        paint.setDither(true);
        this.f.setAntiAlias(true);
        this.f.setStrokeWidth(this.j);
        this.f.setStyle(Paint.Style.STROKE);
        d();
    }

    private void d() {
        if (this.b == -1) {
            this.b = -7829368;
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        this.m = View.MeasureSpec.getSize(i);
        this.e = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE && mode2 == Integer.MIN_VALUE) {
            float f = this.i;
            this.m = (int) (f * 3.0f);
            this.e = (int) f;
        } else if (mode == Integer.MIN_VALUE) {
            this.m = (int) (this.i * 3.0f);
        } else if (mode2 == Integer.MIN_VALUE) {
            this.e = (int) this.i;
        } else {
            LogUtil.c("CommonUI_HealthLine", "onMeasure Width and Height not wrap_content");
        }
        int i3 = this.m;
        if (i3 <= 0 && this.e <= 0) {
            float f2 = this.i;
            this.m = (int) (3.0f * f2);
            this.e = (int) f2;
        } else if (i3 <= 0) {
            this.m = (int) (this.i * 3.0f);
        } else if (this.e <= 0) {
            this.e = (int) this.i;
        } else {
            LogUtil.c("CommonUI_HealthLine", "onMeasure Width and Height not zero");
        }
        if (this.h == 0) {
            setMeasuredDimension(this.m, (int) this.j);
        } else {
            setMeasuredDimension(this.m, this.e + (((int) this.j) / 2));
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f.setColor(this.b);
        int i = this.h;
        if (i == 0) {
            float f = this.j / 2.0f;
            canvas.drawLine(0.0f, f, this.m, f, this.f);
            return;
        }
        if (i == 1) {
            this.g.set(0.0f, this.j / 2.0f);
            this.d.set(this.m - this.e, this.j / 2.0f);
            this.f8850a.set(this.m - (this.j / 2.0f), this.e);
            cBl_(this.g, this.d, this.f8850a, canvas);
            return;
        }
        if (i == 2) {
            this.g.set(0.0f, this.e);
            PointF pointF = this.d;
            int i2 = this.m;
            pointF.set(i2 - r3, this.e - (this.j / 2.0f));
            PointF pointF2 = this.f8850a;
            float f2 = this.m;
            float f3 = this.j / 2.0f;
            pointF2.set(f2 - f3, f3);
            cBl_(this.g, this.d, this.f8850a, canvas);
            return;
        }
        if (i == 3) {
            this.g.set(this.j / 2.0f, this.e);
            this.d.set(this.e, this.j / 2.0f);
            this.f8850a.set(this.m, this.j / 2.0f);
            cBl_(this.g, this.d, this.f8850a, canvas);
            return;
        }
        if (i != 4) {
            return;
        }
        PointF pointF3 = this.g;
        float f4 = this.j / 2.0f;
        pointF3.set(f4, f4);
        PointF pointF4 = this.d;
        float f5 = this.e;
        pointF4.set(f5, f5 - (this.j / 2.0f));
        this.f8850a.set(this.m, this.e);
        cBl_(this.g, this.d, this.f8850a, canvas);
    }

    private void cBl_(PointF pointF, PointF pointF2, PointF pointF3, Canvas canvas) {
        Path path = new Path();
        this.f.setPathEffect(new CornerPathEffect(this.c));
        path.moveTo(pointF.x, pointF.y);
        path.lineTo(pointF2.x, pointF2.y);
        path.lineTo(pointF3.x, pointF3.y);
        canvas.drawPath(path, this.f);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }
}
