package com.huawei.watchface;

import android.graphics.Point;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public class av {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f10910a = new Object();
    private static volatile av b;
    private Point c = new Point();
    private Point d = new Point();
    private float e = 0.0f;
    private int f = 233;

    private av() {
    }

    public static av a() {
        if (b == null) {
            synchronized (f10910a) {
                if (b == null) {
                    b = new av();
                }
            }
        }
        return b;
    }

    private void d() {
        if (Float.compare(Math.abs(this.e), 6.2831855f) < 0) {
            double sin = (float) (this.f * Math.sin(this.e * 2.0f));
            this.c.x = this.d.x + ((int) (Math.cos(this.e) * sin));
            this.c.y = this.d.y + ((int) (sin * Math.sin(this.e)));
        }
    }

    public Point b() {
        return this.c;
    }

    public float c() {
        return 90.0f - ((float) Math.toDegrees(this.e));
    }

    public void a(int i) {
        b(i);
        d();
        HwLog.i("KaleidoscopeRectUtil", "initWatchFaceIdPoint srcBitmapSize: " + i);
    }

    private void b(int i) {
        int i2 = i / 2;
        this.d.x = i2;
        this.d.y = i2;
        this.f = i2;
        this.e = 0.0f;
    }
}
