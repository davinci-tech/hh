package com.huawei.health.suggestion.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class RoundView extends View {

    /* renamed from: a, reason: collision with root package name */
    private int f3424a;
    private Paint b;
    private float c;
    private List<Integer> d;
    private float e;
    private float f;
    private float g;
    private List<Integer> h;
    private Path i;
    private Paint j;

    public RoundView(Context context) {
        super(context);
        this.d = new ArrayList(16);
        this.h = new ArrayList(16);
        this.f = 50.0f;
        this.g = 0.0f;
        b();
    }

    public RoundView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new ArrayList(16);
        this.h = new ArrayList(16);
        this.f = 50.0f;
        this.g = 0.0f;
        b();
    }

    public RoundView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new ArrayList(16);
        this.h = new ArrayList(16);
        this.f = 50.0f;
        this.g = 0.0f;
        b();
    }

    private void b() {
        Paint paint = new Paint();
        this.j = paint;
        paint.setAntiAlias(true);
        this.j.setStyle(Paint.Style.STROKE);
        this.j.setStrokeWidth(this.f);
        Paint paint2 = new Paint();
        this.b = paint2;
        paint2.setAntiAlias(true);
        this.b.setStyle(Paint.Style.FILL);
        this.b.setStrokeWidth(0.0f);
        this.i = new Path();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        this.e = size / 2.0f;
        this.c = size2 / 2.0f;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f;
        if (canvas == null) {
            return;
        }
        this.j.setStrokeWidth(this.f);
        float f2 = this.e;
        float f3 = this.c;
        float f4 = this.f / 2.0f;
        float f5 = (f2 - f4) - 8.0f;
        float f6 = this.g;
        int i = 0;
        while (i < this.h.size()) {
            float intValue = f6 - ((this.h.get(i).intValue() * 360.0f) / this.f3424a);
            int intValue2 = this.d.get(i).intValue();
            c(f2, f3, f5, intValue, f6);
            aMp_(canvas, intValue2, this.j);
            double d = f5;
            double d2 = (f6 * 3.141592653589793d) / 180.0d;
            int i2 = i;
            double d3 = f3;
            float f7 = f5;
            double d4 = f2;
            c((float) ((Math.cos(d2) * d) + d4), (float) ((Math.sin(d2) * d) + d3), f4, f6, f6 + 180.0f);
            aMp_(canvas, intValue2, this.b);
            if (i2 == this.h.size() - 1) {
                int intValue3 = this.d.get(0).intValue();
                double d5 = (intValue * 3.141592653589793d) / 180.0d;
                f = intValue;
                c((float) (d4 + (Math.cos(d5) * d)), (float) (d3 + (Math.sin(d5) * d)), f4, f, intValue + 180.0f);
                aMp_(canvas, intValue3, this.b);
            } else {
                f = intValue;
            }
            i = i2 + 1;
            f6 = f;
            f5 = f7;
        }
    }

    public void setStrokeWidth(float f) {
        this.f = f;
    }

    private void c(float f, float f2, float f3, float f4, float f5) {
        RectF rectF = new RectF(f - f3, f2 - f3, f + f3, f3 + f2);
        this.i.reset();
        this.i.moveTo(this.e, f2);
        this.i.addArc(rectF, f4, f5 - f4);
    }

    private void aMp_(Canvas canvas, int i, Paint paint) {
        if (canvas == null) {
            return;
        }
        paint.setColor(i);
        canvas.drawPath(this.i, paint);
    }
}
