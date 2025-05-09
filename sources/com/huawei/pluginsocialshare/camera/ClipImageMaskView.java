package com.huawei.pluginsocialshare.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/* loaded from: classes9.dex */
public class ClipImageMaskView extends View {

    /* renamed from: a, reason: collision with root package name */
    private Paint f8521a;
    private int b;
    private int e;
    private static final int d = Color.parseColor("#4D4D4D");
    private static final int c = Color.parseColor("#AA000000");

    public ClipImageMaskView(Context context) {
        this(context, null);
    }

    public ClipImageMaskView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ClipImageMaskView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 1;
        this.e = (int) TypedValue.applyDimension(1, 1, getResources().getDisplayMetrics());
        Paint paint = new Paint();
        this.f8521a = paint;
        paint.setAntiAlias(true);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        int i = (height - (width - (this.b * 2))) / 2;
        this.f8521a.setColor(c);
        this.f8521a.setStyle(Paint.Style.FILL);
        float f = height;
        canvas.drawRect(0.0f, 0.0f, this.b, f, this.f8521a);
        canvas.drawRect(width - this.b, 0.0f, width, f, this.f8521a);
        int i2 = this.b;
        float f2 = i2;
        float f3 = width - i2;
        float f4 = i;
        canvas.drawRect(f2, 0.0f, f3, f4, this.f8521a);
        float f5 = height - i;
        canvas.drawRect(this.b, f5, width - r6, f, this.f8521a);
        this.f8521a.setColor(d);
        this.f8521a.setStrokeWidth(this.e);
        this.f8521a.setStyle(Paint.Style.STROKE);
        canvas.drawRect(this.b, f4, width - r3, f5, this.f8521a);
    }

    public void setHorizontalPadding(int i) {
        this.b = i;
    }
}
