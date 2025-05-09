package com.huawei.watchface;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.HwLog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class ax extends aw {
    private List<Point> l;
    private Bitmap m;
    private Canvas n;
    private Bitmap o;
    private Canvas p;
    private int q;
    private int r;

    public ax(Bitmap bitmap) {
        super(bitmap);
        this.l = new ArrayList();
        this.f /= 3.0f;
        this.i = (int) Math.ceil(this.f);
        this.q = (int) (this.f * 2.0f);
        this.r = (int) Math.ceil(this.i * b);
        this.j = new RectF(0.0f, 0.0f, this.i, this.i);
        this.m = Bitmap.createBitmap(this.r, this.i, Bitmap.Config.ARGB_8888);
        int i = this.q;
        this.o = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        this.n = new Canvas(this.m);
        this.p = new Canvas(this.o);
        c();
    }

    @Override // com.huawei.watchface.aw
    public void a(Point point) {
        if (point == null) {
            HwLog.e("HoneycombKaleidoscopeView", "trajectoryPoint is null.");
            return;
        }
        super.a(point);
        if (ArrayUtils.isEmpty(this.l)) {
            c();
        }
        Bitmap a2 = a(this.e, this.n, this.m, this.p, this.o);
        this.h.reset();
        this.h.setAntiAlias(true);
        this.d.drawColor(0, PorterDuff.Mode.CLEAR);
        this.d.setBitmap(this.c);
        float f = this.q / 2.0f;
        for (Point point2 : this.l) {
            this.d.drawBitmap(a2, point2.x - f, point2.y - f, this.h);
        }
    }

    private void c() {
        int i = (int) (this.f + (this.f * f10911a));
        int i2 = ((int) ((this.f * 2.0f) * b)) - 2;
        int i3 = i2 / 2;
        int i4 = 0;
        int i5 = 0;
        while (i4 - this.f < 233.0f) {
            for (int i6 = i5 % 2 != 0 ? i3 : 0; i6 - i3 < 233; i6 += i2) {
                if (i6 <= 233 || i4 <= i3) {
                    int i7 = i6 + 233;
                    int i8 = i4 + 233;
                    this.l.add(new Point(i7, i8));
                    if (i6 != 0) {
                        this.l.add(new Point(233 - i6, i8));
                    }
                    if (i4 != 0) {
                        this.l.add(new Point(i7, 233 - i4));
                    }
                    if (i6 != 0 && i4 != 0) {
                        this.l.add(new Point(233 - i6, 233 - i4));
                    }
                }
            }
            i4 += i - 2;
            i5++;
        }
    }
}
