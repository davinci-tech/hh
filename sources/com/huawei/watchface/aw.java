package com.huawei.watchface;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Shader;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import java.util.Objects;

/* loaded from: classes7.dex */
public class aw {

    /* renamed from: a, reason: collision with root package name */
    protected static final double f10911a = Math.sin(Math.toRadians(30.100000381469727d));
    protected static final double b = Math.cos(Math.toRadians(30.100000381469727d));
    private static final float[] l = {1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] m = {-1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    protected int g;
    protected int i;
    private Bitmap o;
    private BitmapShader s;
    protected Path e = new Path();
    protected float f = 233.0f;
    protected Paint h = new Paint(1);
    protected RectF j = new RectF();
    protected float k = 0.0f;
    private RectF n = new RectF();
    private Point p = new Point(233, 233);
    private boolean q = true;
    private Matrix r = new Matrix();
    private float t = 0.0f;
    private float u = 0.0f;
    private float v = 0.0f;
    protected Bitmap c = Bitmap.createBitmap(466, 466, Bitmap.Config.ARGB_8888);
    protected Canvas d = new Canvas(this.c);

    public aw(Bitmap bitmap) {
        this.o = (Bitmap) Objects.requireNonNull(bitmap);
        this.s = new BitmapShader(this.o, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        this.g = Math.min(bitmap.getHeight(), bitmap.getWidth());
        RectF rectF = new RectF(0.0f, 0.0f, 466.0f, 466.0f);
        this.e.reset();
        this.e.addArc(rectF, 0.0f, 360.0f);
        this.e.lineTo(233.0f, 233.0f);
        this.d.clipPath(this.e);
        av.a().a(this.g);
    }

    public void a() {
        Point b2 = av.a().b();
        this.k = av.a().c();
        if (!this.q && this.p.equals(b2)) {
            HwLog.d("BasePatternKaleidoscopeView", "don't need to update.");
            return;
        }
        if (this.o == null) {
            HwLog.w("BasePatternKaleidoscopeView", "updateDestBitmap:mSrcBitmap is null.");
            return;
        }
        this.q = false;
        this.p.x = b2.x;
        this.p.y = b2.y;
        a(this.p);
    }

    public void a(Point point) {
        this.j.top = point.y - this.i;
        this.j.left = point.x - this.i;
        this.j.bottom = point.y + this.i;
        this.j.right = point.x + this.i;
        this.e.reset();
        this.e.addArc(this.j, this.k, 30.1f);
        this.e.lineTo(point.x, point.y);
    }

    protected void a(Bitmap bitmap, Canvas canvas, Paint paint, int i) {
        if (bitmap == null || canvas == null || paint == null) {
            HwLog.e("BasePatternKaleidoscopeView", "matrixSymmetricX params is null");
            return;
        }
        this.r.reset();
        this.r.setValues(l);
        float f = i;
        this.r.postTranslate(0.0f, f);
        canvas.save();
        canvas.clipRect(0.0f, 0.0f, f, f / 2.0f);
        canvas.drawBitmap(bitmap, this.r, paint);
        canvas.restore();
    }

    protected void b(Bitmap bitmap, Canvas canvas, Paint paint, int i) {
        if (bitmap == null || canvas == null || paint == null) {
            HwLog.e("BasePatternKaleidoscopeView", "matrixSymmetricY params is null");
            return;
        }
        this.r.reset();
        this.r.setValues(m);
        float f = i;
        this.r.postTranslate(f, 0.0f);
        canvas.save();
        canvas.clipRect(0.0f, 0.0f, f / 2.0f, f);
        canvas.drawBitmap(bitmap, this.r, paint);
        canvas.restore();
    }

    private void a(Bitmap bitmap, Canvas canvas, Paint paint, int i, int i2) {
        if (bitmap == null || canvas == null || paint == null) {
            HwLog.e("BasePatternKaleidoscopeView", "matrixRotateBitmap params is null");
            return;
        }
        float f = i;
        float f2 = f / 2.0f;
        this.t = f2;
        this.u = f2 - 1.0f;
        this.v = (i - i2) / 2.0f;
        this.n.set(-2.0f, -1.0f, f, i + 1);
        for (int i3 = -60; i3 <= 60; i3 += 60) {
            canvas.save();
            float f3 = this.t;
            canvas.rotate(i3, f3, f3);
            this.e.reset();
            this.e.addArc(this.n, -30.1f, 60.2f);
            this.e.lineTo(this.u, this.t);
            canvas.clipPath(this.e);
            canvas.drawBitmap(bitmap, this.u, this.v, paint);
            canvas.restore();
        }
    }

    protected Bitmap a(Path path, Canvas canvas, Bitmap bitmap, Canvas canvas2, Bitmap bitmap2) {
        if (CommonUtils.a("BasePatternKaleidoscopeView", path, canvas, bitmap, bitmap2, canvas2)) {
            HwLog.e("BasePatternKaleidoscopeView", "getInterceptedAndSpliceBitmapByPath params is null");
            return this.o;
        }
        this.h.reset();
        this.h.setAntiAlias(true);
        a(path, canvas, bitmap, bitmap.getHeight());
        canvas2.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas2.setBitmap(bitmap2);
        a(bitmap, canvas2, this.h, bitmap2.getHeight(), bitmap.getHeight());
        b(bitmap2, canvas2, this.h, bitmap2.getHeight());
        return bitmap2;
    }

    private void a(Path path, Canvas canvas, Bitmap bitmap, int i) {
        this.h.reset();
        this.h.setAntiAlias(true);
        this.h.setShader(this.s);
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.setBitmap(bitmap);
        canvas.save();
        float f = i / 2.0f;
        canvas.rotate(-this.k, 0.0f, f);
        canvas.translate(-this.p.x, f - this.p.y);
        canvas.drawPath(path, this.h);
        canvas.restore();
        a(bitmap, canvas, this.h, i);
    }

    public Bitmap b() {
        return this.c;
    }
}
