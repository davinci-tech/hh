package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;

/* loaded from: classes8.dex */
public class LineProgressBar extends View {

    /* renamed from: a, reason: collision with root package name */
    private Paint f3782a;
    private int b;
    private float c;
    private float d;
    private int e;
    private Paint f;
    private int g;
    private float i;
    private int j;

    public LineProgressBar(Context context) {
        this(context, null);
    }

    public LineProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = null;
        this.f3782a = null;
        big_(context, attributeSet);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        bif_(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        b();
    }

    public void setMaxProgress(float f) {
        if (f < 0.0f) {
            this.i = 0.0f;
        } else {
            this.i = f;
        }
    }

    public void setProgress(float f) {
        if (f < 0.0f) {
            this.c = 0.0f;
        } else {
            float f2 = this.i;
            if (f > f2) {
                this.c = f2;
            } else {
                this.c = f;
            }
        }
        this.d = this.c / this.i;
        invalidate();
    }

    private void big_(Context context, AttributeSet attributeSet) {
        if (context == null || attributeSet == null) {
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131099754_res_0x7f06006a, R.attr._2131100142_res_0x7f0601ee, R.attr._2131100895_res_0x7f0604df, R.attr._2131101311_res_0x7f06067f});
        try {
            this.b = obtainStyledAttributes.getColor(0, ContextCompat.getColor(context, R.color._2131297884_res_0x7f09065c));
            this.g = obtainStyledAttributes.getColor(1, ContextCompat.getColor(context, R.color._2131297884_res_0x7f09065c));
            this.i = obtainStyledAttributes.getColor(3, 100);
            this.c = obtainStyledAttributes.getColor(2, 0);
            obtainStyledAttributes.recycle();
            Paint paint = new Paint();
            this.f = paint;
            paint.setAntiAlias(true);
            this.f.setStyle(Paint.Style.STROKE);
            this.f.setStrokeWidth(16.0f);
            this.f.setStrokeCap(Paint.Cap.ROUND);
            this.f.setStrokeJoin(Paint.Join.ROUND);
            Paint paint2 = new Paint();
            this.f3782a = paint2;
            paint2.setAntiAlias(true);
            this.f3782a.setStyle(Paint.Style.STROKE);
            this.f3782a.setStrokeWidth(16.0f);
            this.f3782a.setStrokeCap(Paint.Cap.ROUND);
            this.f3782a.setStrokeJoin(Paint.Join.ROUND);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    private void b() {
        this.j = getWidth();
        this.e = getHeight();
    }

    private void bif_(Canvas canvas) {
        this.f3782a.setColor(this.b);
        float f = this.e / 2.0f;
        canvas.drawLine(10.0f, f, this.j - 10, f, this.f3782a);
        if (this.d != 0.0f) {
            this.f.setColor(this.g);
            float f2 = this.e / 2.0f;
            canvas.drawLine(10.0f, f2, ((this.j - 20) * this.d) + 10.0f, f2, this.f);
        }
    }
}
