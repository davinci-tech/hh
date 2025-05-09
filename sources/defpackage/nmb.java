package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;

/* loaded from: classes6.dex */
public class nmb {
    private nmg e;
    private nmc h;
    private nly j;
    private Paint o = new Paint();
    private Paint c = new Paint();
    private int k = 40;
    private int l = HeartRateThresholdConfig.HEART_RATE_LIMIT;
    private int m = 252;
    private int b = 49;
    private int d = 89;
    private int i = -16777216;
    private float g = 0.0f;

    /* renamed from: a, reason: collision with root package name */
    private Paint f15381a = new Paint();
    private Paint f = new Paint();

    public nmb(nmg nmgVar, nly nlyVar, nmc nmcVar) {
        this.e = nmgVar;
        this.j = nlyVar;
        this.h = nmcVar;
        this.o.setColor(Color.argb(255, this.m, this.b, this.d));
        this.o.setStrokeWidth(nme.a(1.0f));
        this.o.setStrokeCap(Paint.Cap.ROUND);
        this.o.setAntiAlias(true);
        this.f15381a.setColor(-1);
        this.f.setColor(Color.argb(255, this.m, this.b, this.d));
        this.f.setAntiAlias(true);
        this.f.setStyle(Paint.Style.FILL);
        this.f.setShadowLayer(nme.a(4.25f), 0.0f, 0.0f, Color.argb(255, this.m, this.b, this.d));
    }

    public void e(int i, int i2) {
        this.k = i;
        this.l = i2;
    }

    public void d(int i) {
        this.i = i;
        this.o.setColor(i);
        this.f.setColor(i);
        this.f.setShadowLayer(nme.a(4.25f), 0.0f, 0.0f, i);
    }

    public void cAj_(Canvas canvas, Bitmap bitmap) {
        float[] b = this.h.b();
        RectF rectF = new RectF(0.0f, this.l, 0.0f, this.k);
        if (b.length >= 2) {
            rectF = new RectF(b[0], this.l, b[b.length - 2], this.k);
        }
        this.j.b(b);
        this.j.cAf_(rectF);
        Canvas canvas2 = new Canvas(bitmap);
        canvas2.save();
        canvas2.clipRect(rectF);
        canvas2.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas2.restore();
        cAi_(b, canvas2);
        canvas2.drawLines(b, this.o);
        RectF[] cAg_ = this.h.cAg_();
        for (RectF rectF2 : cAg_) {
            this.j.cAf_(rectF2);
        }
        RectF cAl_ = this.e.cAl_();
        float f = cAl_.left;
        for (RectF rectF3 : cAg_) {
            canvas.drawBitmap(bitmap, new Rect((int) rectF3.left, (int) rectF3.top, (int) rectF3.right, (int) rectF3.bottom), new RectF(f, cAl_.top, rectF3.width() + f, cAl_.top + this.e.c()), this.c);
            f += rectF3.width();
        }
        cAh_(b, cAl_, canvas);
    }

    private void cAi_(float[] fArr, Canvas canvas) {
        if (fArr.length >= 4) {
            Path path = new Path();
            path.moveTo(fArr[0], fArr[1]);
            int i = 4;
            while (i < fArr.length) {
                float f = fArr[i];
                i++;
                path.lineTo(f, fArr[i]);
            }
            if (fArr.length > 2) {
                path.lineTo(fArr[fArr.length - 2], fArr[fArr.length - 1]);
            }
            RectF cAl_ = this.e.cAl_();
            path.lineTo(fArr[fArr.length - 2], cAl_.bottom);
            path.lineTo(fArr[0], cAl_.bottom);
            path.lineTo(fArr[0], fArr[1]);
            float[] fArr2 = {0.0f, this.k, 0.0f, this.l};
            this.j.b(fArr2);
            int save = canvas.save();
            canvas.clipPath(path);
            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{nrn.c(this.i, 0.8f), nrn.c(this.i, 0.6f), nrn.c(this.i, 0.4f)});
            gradientDrawable.setBounds(new Rect((int) Math.floor(fArr[0]), (int) fArr2[3], (int) Math.ceil(fArr[fArr.length - 2]), (int) fArr2[1]));
            gradientDrawable.draw(canvas);
            canvas.restoreToCount(save);
        }
    }

    private void cAh_(float[] fArr, RectF rectF, Canvas canvas) {
        if (fArr.length >= 2) {
            float f = rectF.left + fArr[fArr.length - 2];
            float f2 = this.g;
            if (f < f2) {
                f = f2;
            }
            if (f > rectF.right) {
                f = rectF.right;
            }
            this.g = f;
            canvas.save();
            Path path = new Path();
            path.addCircle(f, rectF.top + fArr[fArr.length - 1], nme.a(5.5f), Path.Direction.CCW);
            canvas.clipPath(path);
            canvas.drawCircle(f, rectF.top + fArr[fArr.length - 1], nme.a(2.75f), this.f);
            canvas.restore();
        }
    }
}
