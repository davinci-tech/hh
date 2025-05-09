package com.huawei.ui.commonui.reportchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class PercentageView extends View {

    /* renamed from: a, reason: collision with root package name */
    private float f8933a;
    private float b;
    private List<a> c;
    private Paint d;
    private float e;

    public PercentageView(Context context) {
        super(context);
        d(context);
    }

    public PercentageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d(context);
    }

    public PercentageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        d(context);
    }

    private void d(Context context) {
        setWillNotDraw(false);
        this.f8933a = context.getResources().getDisplayMetrics().widthPixels - a(context, 40.0f);
        Paint paint = new Paint();
        this.d = paint;
        paint.setAntiAlias(true);
    }

    private float a(Context context, float f) {
        return (f * context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), i), getDefaultSize(getSuggestedMinimumHeight(), i2));
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.f8933a = i;
        this.e = i2;
        super.onSizeChanged(i, i2, i3, i4);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.c != null) {
            int i = 0;
            canvas.drawColor(0);
            int size = this.c.size();
            float f = 0.0f;
            while (i < size) {
                this.d.setColor(this.c.get(i).d);
                float f2 = ((this.c.get(i).c / this.b) * this.f8933a) + f;
                if (i == 0) {
                    cFf_(canvas, size, f, f2);
                } else if (i == size - 1) {
                    RectF rectF = new RectF(f, 0.0f, f2, this.e);
                    float f3 = this.e / 2.0f;
                    canvas.drawRoundRect(rectF, f3, f3, this.d);
                    rectF.left = f;
                    rectF.right = f2 - 15.0f;
                    canvas.drawRoundRect(rectF, 0.0f, 0.0f, this.d);
                } else {
                    canvas.drawRoundRect(new RectF(f, 0.0f, f2 - 2.0f, this.e), 0.0f, 0.0f, this.d);
                }
                i++;
                f = f2;
            }
        }
    }

    private void cFf_(Canvas canvas, int i, float f, float f2) {
        float f3 = f2 - 2.0f;
        RectF rectF = new RectF(f, 0.0f, f3, this.e);
        float f4 = this.e / 2.0f;
        canvas.drawRoundRect(rectF, f4, f4, this.d);
        rectF.left = f + 15.0f;
        rectF.right = f3;
        if (i != 1) {
            canvas.drawRoundRect(rectF, 0.0f, 0.0f, this.d);
        }
    }

    public void setData(List<a> list) {
        this.c = list;
        this.b = 0.0f;
        Iterator<a> it = list.iterator();
        while (it.hasNext()) {
            this.b += it.next().c;
        }
        invalidate();
    }

    public static class a {
        int c;
        int d;

        public a() {
        }

        public a(int i, int i2) {
            this.c = i;
            this.d = i2;
        }
    }
}
