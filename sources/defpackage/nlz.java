package defpackage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/* loaded from: classes6.dex */
class nlz {

    /* renamed from: a, reason: collision with root package name */
    private nmg f15379a;
    private Path c;
    private Paint d = new Paint();
    private Paint e = new Paint();

    nlz(nmg nmgVar) {
        this.f15379a = nmgVar;
        this.d.setColor(Color.argb(51, 255, 255, 255));
        this.e.setColor(Color.argb(25, 255, 255, 255));
        this.e.setStyle(Paint.Style.STROKE);
        this.e.setPathEffect(new DashPathEffect(new float[]{10.0f, 10.0f}, 0.0f));
        float a2 = nme.a(0.25f);
        this.d.setStrokeWidth(a2 < 1.0f ? 1.0f : a2);
        this.c = new Path();
    }

    public void cAk_(Canvas canvas) {
        float[] fArr = new float[5];
        RectF cAl_ = this.f15379a.cAl_();
        for (int i = 0; i < 5; i++) {
            fArr[i] = cAl_.top + (((cAl_.bottom - cAl_.top) / 4) * i);
        }
        for (int i2 = 0; i2 < 5; i2++) {
            if (i2 == 0 || 4 == i2) {
                canvas.drawLine(cAl_.left, fArr[i2], cAl_.right, fArr[i2], this.d);
            } else {
                this.c.reset();
                this.c.moveTo(cAl_.left, fArr[i2]);
                this.c.lineTo(cAl_.right, fArr[i2]);
                canvas.drawPath(this.c, this.e);
            }
        }
    }
}
