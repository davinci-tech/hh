package com.huawei.health.suggestion.ui.fitness.module;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.nrr;

/* loaded from: classes4.dex */
public class TimeProgress extends View {

    /* renamed from: a, reason: collision with root package name */
    private float f3190a;
    private float b;
    private PointF c;
    private Paint d;
    private float e;
    private int f;
    private int g;
    private RectF j;

    public TimeProgress(Context context) {
        super(context);
        this.f3190a = 3.0f;
        this.c = new PointF();
        this.e = -1.0f;
        c();
    }

    public TimeProgress(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3190a = 3.0f;
        this.c = new PointF();
        this.e = -1.0f;
        c();
    }

    public TimeProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3190a = 3.0f;
        this.c = new PointF();
        this.e = -1.0f;
        c();
    }

    private void c() {
        this.g = getResources().getColor(R.color._2131299177_res_0x7f090b69);
        this.f = getResources().getColor(R.color._2131299177_res_0x7f090b69);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.d = new Paint(1);
        this.f3190a = nrr.e(getContext(), this.f3190a);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        float min = ((Math.min(i, i2) * 1.0f) / 2.0f) - (this.f3190a / 2.0f);
        this.c = new PointF((i * 1.0f) / 2.0f, (i2 * 1.0f) / 2.0f);
        this.j = new RectF(this.c.x - min, this.c.y - min, this.c.x + min, this.c.y + min);
        this.d.setStrokeWidth(this.f3190a);
        this.d.setStyle(Paint.Style.STROKE);
        this.d.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.d.setColor(this.g);
        float f = this.b;
        if (f == 0.0f) {
            LogUtil.h("Suggestion_TimeProgress", "onDraw mMax == 0");
        } else {
            canvas.drawArc(this.j, -90.0f, 1.0f * (((this.e * 360.0f) * 1.0f) / f), false, this.d);
        }
    }

    public TimeProgress b(float f) {
        this.b = f;
        postInvalidate();
        return this;
    }

    public TimeProgress d(float f) {
        this.e = f;
        this.g = this.f;
        postInvalidate();
        return this;
    }

    public float getMax() {
        return this.b;
    }
}
