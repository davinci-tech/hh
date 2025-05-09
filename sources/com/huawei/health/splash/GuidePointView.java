package com.huawei.health.splash;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import defpackage.nsn;

/* loaded from: classes4.dex */
public class GuidePointView extends View {

    /* renamed from: a, reason: collision with root package name */
    private int f2987a;
    private int b;
    private Context c;
    private int d;
    private int e;

    public GuidePointView(Context context, int i, int i2) {
        this(context, (AttributeSet) null, 0);
        this.c = context;
        this.d = i;
        this.f2987a = i2;
    }

    public GuidePointView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        this.c = context;
    }

    public GuidePointView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 3;
        this.f2987a = 3;
        this.c = context;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        awG_(canvas);
    }

    private void awG_(Canvas canvas) {
        Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < this.d; i++) {
            if (i == this.f2987a) {
                paint.setColor(16475426);
                paint.setAlpha(255);
            } else {
                paint.setColor(637534208);
                paint.setAlpha(127);
            }
            Point point = new Point(((this.e / 2) - ((int) ((nsn.c(this.c, 12.0f) * this.d) / 2.0f))) + nsn.c(this.c, 3.0f) + (nsn.c(this.c, 12.0f) * i), this.b / 2);
            canvas.drawCircle(point.x, point.y, nsn.c(this.c, 6.0f) / 2.0f, paint);
        }
    }

    public void setSelected(int i) {
        if (i > this.d) {
            return;
        }
        this.f2987a = i;
        invalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.e = getMeasuredWidth();
        this.b = getMeasuredHeight();
    }
}
