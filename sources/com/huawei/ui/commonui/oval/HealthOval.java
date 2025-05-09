package com.huawei.ui.commonui.oval;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$styleable;

/* loaded from: classes6.dex */
public class HealthOval extends View {

    /* renamed from: a, reason: collision with root package name */
    private Paint f8916a;
    private int b;
    private int c;
    private int d;
    private RectF e;
    private int f;
    private float g;
    private int i;

    public HealthOval(Context context) {
        this(context, null);
        e();
    }

    public HealthOval(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (attributeSet == null) {
            LogUtil.h("CommonUI_HealthOval", "HealthOval AttributeSet is null");
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.health_oval);
        this.i = obtainStyledAttributes.getInteger(R$styleable.health_oval_oval_type, 0);
        this.b = obtainStyledAttributes.getColor(R$styleable.health_oval_oval_fill_color, -1);
        this.c = obtainStyledAttributes.getColor(R$styleable.health_oval_oval_stroke_color, -1);
        this.g = obtainStyledAttributes.getDimension(R$styleable.health_oval_oval_stroke_width, 1.0f);
        obtainStyledAttributes.recycle();
        e();
    }

    private void e() {
        this.e = new RectF();
        Paint paint = new Paint();
        this.f8916a = paint;
        paint.setDither(true);
        this.f8916a.setAntiAlias(true);
        this.f8916a.setStrokeWidth(this.g);
        b();
    }

    private void b() {
        int i = this.b;
        if (i == -1 && this.c == -1) {
            this.b = -7829368;
            this.c = -7829368;
        } else if (i == -1) {
            this.b = this.c;
        } else if (this.c == -1) {
            this.c = i;
        } else {
            LogUtil.c("CommonUI_HealthOval", "initColor FillColor and StrokeColor has value");
        }
    }

    private void cEa_(Canvas canvas) {
        this.f8916a.setColor(this.b);
        this.f8916a.setStyle(Paint.Style.FILL);
        this.e.set(0.0f, 0.0f, this.f, this.d);
        canvas.drawOval(this.e, this.f8916a);
    }

    private void cEb_(Canvas canvas) {
        this.f8916a.setColor(this.c);
        this.f8916a.setStyle(Paint.Style.STROKE);
        RectF rectF = this.e;
        float f = this.g / 2.0f;
        rectF.set(f, f, this.f - f, this.d - f);
        canvas.drawOval(this.e, this.f8916a);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        this.f = View.MeasureSpec.getSize(i);
        int size = View.MeasureSpec.getSize(i2);
        this.d = size;
        if (mode == Integer.MIN_VALUE && mode2 == Integer.MIN_VALUE) {
            this.f = 100;
            this.d = 100;
        } else if (mode == Integer.MIN_VALUE) {
            this.f = size;
        } else if (mode2 == Integer.MIN_VALUE) {
            this.d = this.f;
        }
        int i3 = this.f;
        if (i3 <= 0 && this.d <= 0) {
            this.f = 100;
            this.d = 100;
        } else if (i3 <= 0) {
            this.f = this.d;
        } else if (this.d <= 0) {
            this.d = i3;
        }
        setMeasuredDimension(this.f, this.d);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = this.i;
        if (i == 0) {
            cEa_(canvas);
            cEb_(canvas);
        } else if (i == 1) {
            cEb_(canvas);
        } else {
            if (i != 2) {
                return;
            }
            cEa_(canvas);
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    public void setFillColor(int i) {
        this.b = i;
    }
}
