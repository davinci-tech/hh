package com.huawei.health.suggestion.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.R$color;
import defpackage.nrr;

/* loaded from: classes4.dex */
public class BrightnessOrVolumeProgress extends View {

    /* renamed from: a, reason: collision with root package name */
    private float f3415a;
    private Paint b;
    private int c;
    private float d;
    private PointF e;
    private float h;
    private RectF i;
    private int j;

    public BrightnessOrVolumeProgress(Context context) {
        super(context);
        this.f3415a = 2.0f;
        this.d = 1.0f;
        this.h = 0.5f;
        this.e = new PointF();
        b(context);
    }

    public BrightnessOrVolumeProgress(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3415a = 2.0f;
        this.d = 1.0f;
        this.h = 0.5f;
        this.e = new PointF();
        b(context);
    }

    public BrightnessOrVolumeProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3415a = 2.0f;
        this.d = 1.0f;
        this.h = 0.5f;
        this.e = new PointF();
        b(context);
    }

    private void b(Context context) {
        if (context == null) {
            context = BaseApplication.getContext();
        }
        this.c = ContextCompat.getColor(context, R$color.common_white_30alpha);
        this.j = ContextCompat.getColor(context, R$color.emui_white);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.b = new Paint(1);
        this.f3415a = nrr.e(getContext(), this.f3415a);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        float min = (Math.min(i, i2) * 0.5f) - (this.f3415a * 0.5f);
        this.e = new PointF(i * 0.5f, i2 * 0.5f);
        this.i = new RectF(this.e.x - min, this.e.y - min, this.e.x + min, this.e.y + min);
        this.b.setStrokeWidth(this.f3415a);
        this.b.setStyle(Paint.Style.STROKE);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.b.setColor(this.c);
        canvas.drawArc(this.i, 0.0f, 360.0f, false, this.b);
        this.b.setColor(this.j);
        canvas.drawArc(this.i, -90.0f, ((this.h * 360.0f) / this.d) * 1.0f, false, this.b);
    }

    public void setMax(float f) {
        this.d = f;
        postInvalidate();
    }

    public void setProgress(float f) {
        this.h = f;
        postInvalidate();
    }
}
