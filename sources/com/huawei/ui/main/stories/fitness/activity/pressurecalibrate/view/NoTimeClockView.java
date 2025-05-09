package com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import com.huawei.hwcommonmodel.application.BaseApplication;
import defpackage.nsn;

/* loaded from: classes6.dex */
public class NoTimeClockView extends View {

    /* renamed from: a, reason: collision with root package name */
    private final int f9877a;
    private final int c;
    private final int e;

    public NoTimeClockView(Context context) {
        super(context);
        int c = nsn.c(BaseApplication.getContext(), 120.0f);
        this.f9877a = c;
        this.c = c;
        this.e = c;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(b(i), b(i2));
    }

    private int b(int i) {
        int c = nsn.c(BaseApplication.getContext(), 240.0f);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        return mode == 1073741824 ? size : mode == Integer.MIN_VALUE ? Math.min(c, size) : c;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        dty_(canvas);
        canvas.save();
        invalidate();
    }

    private void dty_(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.argb(255, 204, 204, 204));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(nsn.c(BaseApplication.getContext(), 1.0f));
        canvas.translate(this.f9877a, this.c);
        for (int i = 0; i < 180; i++) {
            canvas.drawLine(0.0f, this.e - nsn.c(BaseApplication.getContext(), 8.0f), 0.0f, this.e, paint);
            canvas.rotate(2.0f, 0.0f, 0.0f);
        }
    }
}
