package com.huawei.hms.scankit.p;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import java.util.List;

/* loaded from: classes9.dex */
public class y5 {

    /* renamed from: a, reason: collision with root package name */
    private long f5928a = 0;
    private long b = 0;
    private int c;
    private int d;
    private int e;
    private float f;
    private Rect g;
    private b6 h;

    public y5(b6 b6Var, Rect rect, int i, float f, int[] iArr) {
        this.c = 0;
        this.d = 0;
        this.h = b6Var;
        this.g = rect;
        this.e = i;
        if (iArr != null && iArr.length >= 2) {
            this.c = iArr[0];
            this.d = iArr[1];
        }
        this.f = f;
        c();
    }

    private void a() {
        b6 b6Var = this.h;
        if (b6Var != null) {
            b6Var.a();
        }
    }

    private int b() {
        float a2 = n6.a(1.0f);
        int red = Color.red(this.c);
        int blue = Color.blue(this.c);
        return Color.rgb((int) (red + ((Color.red(this.d) - red) * a2) + 0.5f), (int) (Color.green(this.c) + ((Color.green(this.d) - r3) * a2) + 0.5f), (int) (blue + ((Color.blue(this.d) - blue) * a2) + 0.5f));
    }

    private void c() {
        Rect rect;
        a();
        this.b = 0L;
        this.f5928a = System.currentTimeMillis();
        b6 b6Var = this.h;
        if (b6Var == null || (rect = this.g) == null) {
            return;
        }
        b6Var.a(rect, this.e);
    }

    public void a(Canvas canvas, Rect rect) {
        if (this.h == null || canvas == null || rect == null) {
            return;
        }
        long currentTimeMillis = this.b + (System.currentTimeMillis() - this.f5928a);
        this.b = currentTimeMillis;
        this.h.b(currentTimeMillis);
        List<w5> c = this.h.c();
        if (c == null || c.isEmpty()) {
            return;
        }
        a(canvas, rect, c);
        this.f5928a = System.currentTimeMillis();
    }

    private void a(Canvas canvas, Rect rect, List<w5> list) {
        for (w5 w5Var : list) {
            Paint paint = new Paint();
            if (w5Var.b() == 0) {
                w5Var.b(b());
            }
            paint.setColor(w5Var.b());
            int a2 = (int) (w5Var.a() * Math.abs((rect.height() == 0 || rect.width() == 0 || ((w5Var.d() > ((float) Math.max(rect.top, rect.bottom)) ? 1 : (w5Var.d() == ((float) Math.max(rect.top, rect.bottom)) ? 0 : -1)) > 0 || (w5Var.d() > ((float) Math.min(rect.top, rect.bottom)) ? 1 : (w5Var.d() == ((float) Math.min(rect.top, rect.bottom)) ? 0 : -1)) < 0)) ? 0.0f : (rect.bottom - w5Var.d()) / rect.height()));
            if (a2 > 0) {
                paint.setAlpha(a2);
                canvas.drawCircle(w5Var.c(), w5Var.d(), w5Var.f() * this.f, paint);
            }
        }
    }
}
