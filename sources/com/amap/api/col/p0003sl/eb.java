package com.amap.api.col.p0003sl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/* loaded from: classes2.dex */
public final class eb extends View {

    /* renamed from: a, reason: collision with root package name */
    public static final int f986a = Color.argb(255, 235, 235, 235);
    public static final int b = Color.argb(255, 21, 21, 21);
    private Paint c;

    public eb(Context context) {
        super(context);
        Paint paint = new Paint();
        this.c = paint;
        paint.setAntiAlias(true);
        this.c.setColor(f986a);
    }

    public final void a(int i) {
        Paint paint = this.c;
        if (paint != null) {
            paint.setColor(i);
            try {
                postInvalidate();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @Override // android.view.View
    protected final void onDraw(Canvas canvas) {
        canvas.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.c);
    }

    public final void a() {
        setVisibility(8);
    }
}
