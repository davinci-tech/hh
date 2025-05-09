package com.huawei.health.section.section;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$drawable;

/* loaded from: classes3.dex */
public class MeasureIndicator extends View {

    /* renamed from: a, reason: collision with root package name */
    private Drawable f2975a;
    private int b;
    private float c;
    private Paint d;
    private float e;
    private int f;
    private double g;
    private int h;
    private int i;
    private int j;
    private float l;
    private int n;
    private float o;

    public MeasureIndicator(Context context) {
        super(context);
        e(context);
    }

    public MeasureIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        e(context);
    }

    public MeasureIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        e(context);
    }

    private void e(Context context) {
        this.f2975a = ContextCompat.getDrawable(getContext(), R$drawable.health_indicator_triangle);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R.dimen._2131363094_res_0x7f0a0516);
        this.i = dimensionPixelSize2;
        this.f = dimensionPixelSize;
        this.f2975a.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize2);
        Paint paint = new Paint();
        this.d = paint;
        paint.setStrokeWidth(context.getResources().getDimensionPixelSize(R.dimen._2131363094_res_0x7f0a0516));
        this.e = this.d.getStrokeWidth() / 2.0f;
        this.d.setStrokeCap(Paint.Cap.ROUND);
        this.b = ContextCompat.getColor(context, R.color._2131296907_res_0x7f09028b);
        this.j = ContextCompat.getColor(context, R.color._2131296906_res_0x7f09028a);
        this.n = ContextCompat.getColor(context, R.color._2131296905_res_0x7f090289);
        this.h = context.getResources().getDimensionPixelSize(R.dimen._2131362945_res_0x7f0a0481);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int strokeWidth = (int) (this.i + this.h + this.d.getStrokeWidth());
        if (mode != 1073741824) {
            size2 = strokeWidth;
        }
        float f = size;
        this.c = f / 3.0f;
        this.l = (2.0f * f) / 3.0f;
        this.o = f;
        setMeasuredDimension(size, size2);
    }

    public void setIndicatorLevel(double d) {
        this.g = d;
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getWidth() <= 0 || getHeight() <= 0) {
            return;
        }
        float height = getHeight() / 2.0f;
        float strokeWidth = this.d.getStrokeWidth() / 2.0f;
        canvas.save();
        canvas.translate(0.0f, strokeWidth + height);
        this.d.setColor(this.b);
        float f = this.e;
        canvas.drawLine(f, 0.0f, this.c - f, 0.0f, this.d);
        this.d.setColor(this.j);
        float f2 = this.c;
        float f3 = this.e;
        canvas.drawLine(f2 + f3, 0.0f, this.l - f3, 0.0f, this.d);
        this.d.setColor(this.n);
        float f4 = this.l;
        float f5 = this.e;
        canvas.drawLine(f4 + f5, 0.0f, this.o - f5, 0.0f, this.d);
        canvas.restore();
        canvas.save();
        canvas.rotate(180.0f, this.f / 2.0f, this.i / 2.0f);
        canvas.translate(-(d(getWidth()) - (this.f / 2.0f)), -((height - this.i) - this.h));
        this.f2975a.draw(canvas);
        canvas.restore();
    }

    private float d(int i) {
        double d;
        double d2 = this.g;
        float f = i;
        double d3 = f / 3.0f;
        if (d2 < 8.0d) {
            if (d2 < 0.0d) {
                d2 = 0.0d;
            }
            d = (d3 * (d2 - 0.0d)) / 8.0d;
            d3 = 0.0d;
        } else if (d2 < 8.0d || d2 > 10.0d) {
            if (d2 > 15.0d) {
                d2 = 15.0d;
            }
            d = (d3 * (d2 - 10.0d)) / 5.0d;
            d3 = (f * 2.0f) / 3.0f;
        } else {
            d = ((d2 - 8.0d) * d3) / 2.0d;
        }
        float f2 = (float) (d3 + d);
        float f3 = this.f / 2.0f;
        if (f2 - f3 < 0.0f) {
            f2 = f3;
        }
        return f3 + f2 > f ? f - f3 : f2;
    }
}
