package com.huawei.health.ui.notification.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/* loaded from: classes.dex */
public class CircleProcessUtil {

    /* renamed from: a, reason: collision with root package name */
    private int f3484a;
    private Paint b;
    private int c;
    private Context d;
    private RectF e;
    private Paint g;

    public CircleProcessUtil(Context context) {
        this.d = context;
    }

    private void a() {
        this.c = e(this.d, 44.0f);
        int e = e(this.d, 8.0f);
        this.f3484a = e;
        int i = e / 2;
        float f = i;
        float f2 = this.c - i;
        this.e = new RectF(f, f, f2, f2);
        int argb = Color.argb(40, 255, 255, 255);
        Paint paint = new Paint();
        this.b = paint;
        paint.setAntiAlias(true);
        this.b.setStyle(Paint.Style.STROKE);
        this.b.setColor(argb);
        this.b.setStrokeWidth(this.f3484a);
        this.b.setFilterBitmap(false);
        Paint paint2 = new Paint();
        this.g = paint2;
        paint2.setAntiAlias(true);
        this.g.setStyle(Paint.Style.STROKE);
        this.g.setStrokeJoin(Paint.Join.ROUND);
        this.g.setStrokeCap(Paint.Cap.ROUND);
        this.g.setColor(-1);
        this.g.setStrokeWidth(this.f3484a);
    }

    public Bitmap aOu_(float f) {
        a();
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        int i = this.c;
        Bitmap createBitmap = Bitmap.createBitmap(i, i, config);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawArc(this.e, 0.0f, 360.0f, false, this.b);
        canvas.drawArc(this.e, -90.0f, f * 360.0f, false, this.g);
        return createBitmap;
    }

    public static int e(Context context, float f) {
        if (context == null) {
            return 0;
        }
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
