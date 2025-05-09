package com.huawei.ui.commonui.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$color;

/* loaded from: classes6.dex */
public class GradientCircle extends View {

    /* renamed from: a, reason: collision with root package name */
    private Paint f8844a;
    private float b;
    private float c;
    private RectF d;
    private Paint e;

    public GradientCircle(Context context) {
        super(context);
        b();
    }

    public GradientCircle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public GradientCircle(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    private void b() {
        setLayerType(2, null);
        final float dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131363183_res_0x7f0a056f);
        post(new Runnable() { // from class: com.huawei.ui.commonui.indicator.GradientCircle.5
            @Override // java.lang.Runnable
            public void run() {
                int measuredWidth = GradientCircle.this.getMeasuredWidth();
                float f = dimensionPixelSize / 2.0f;
                float f2 = measuredWidth;
                float f3 = f2 - f;
                GradientCircle.this.d = new RectF(f, f, f3, f3);
                GradientCircle.this.f8844a = new Paint();
                float f4 = f2 / 2.0f;
                SweepGradient sweepGradient = new SweepGradient(f4, f4, new int[]{ContextCompat.getColor(GradientCircle.this.getContext(), R$color.gradient_circle_low_color), ContextCompat.getColor(GradientCircle.this.getContext(), R$color.gradient_circle_medium_color), ContextCompat.getColor(GradientCircle.this.getContext(), R$color.gradient_circle_high_color), ContextCompat.getColor(GradientCircle.this.getContext(), R$color.gradient_circle_low_color)}, new float[]{0.0f, 0.40833333f, 0.81666666f, 0.95f});
                Matrix matrix = new Matrix();
                matrix.setRotate(123.0f, f4, f4);
                sweepGradient.setLocalMatrix(matrix);
                GradientCircle.this.f8844a.setShader(sweepGradient);
                GradientCircle.this.f8844a.setAntiAlias(true);
                GradientCircle.this.f8844a.setStrokeCap(Paint.Cap.ROUND);
                GradientCircle.this.f8844a.setStyle(Paint.Style.STROKE);
                GradientCircle.this.f8844a.setStrokeWidth(dimensionPixelSize);
                GradientCircle.this.invalidate();
            }
        });
        Paint paint = new Paint();
        this.e = paint;
        paint.setColor(ContextCompat.getColor(getContext(), R$color.gradient_circle_bg));
        this.e.setAntiAlias(true);
        this.e.setStrokeCap(Paint.Cap.ROUND);
        this.e.setStyle(Paint.Style.STROKE);
        this.e.setStrokeWidth(dimensionPixelSize);
    }

    public void c(float f, float f2) {
        this.b = f;
        this.c = f2;
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.d == null) {
            return;
        }
        canvas.save();
        canvas.drawArc(this.d, 123.0f, 294.0f, false, this.e);
        canvas.drawArc(this.d, 123.0f, Math.min(this.b / this.c, 1.0f) * 294.0f, false, this.f8844a);
        canvas.restore();
    }
}
