package com.huawei.ui.device.views.adddevice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.hwcommonmodel.application.BaseApplication;
import defpackage.nsn;
import java.lang.reflect.Array;

/* loaded from: classes6.dex */
public class RadarImageView extends View {
    private Paint c;
    private int f;
    private boolean g;
    private int h;
    private float[][] i;
    private int j;
    private Paint k;
    private Shader l;
    private int m;
    private Matrix n;
    private Runnable o;
    private static final float[] d = {1.0f, 0.7f, 0.4f, 0.1f};
    private static final float[] b = {0.0f, 0.96f, 0.96f, 1.0f};
    private static final float[][] e = {new float[]{10.0f, 10.0f}, new float[]{15.0f, 10.0f}, new float[]{20.0f, 15.0f}, new float[]{25.0f, 20.0f}};

    /* renamed from: a, reason: collision with root package name */
    private static final float[][] f9308a = {new float[]{20.0f, 20.0f}, new float[]{30.0f, 20.0f}, new float[]{40.0f, 30.0f}, new float[]{50.0f, 40.0f}};

    public RadarImageView(Context context) {
        super(context);
        this.i = (float[][]) Array.newInstance((Class<?>) Float.TYPE, 4, 2);
        this.m = 0;
        this.n = new Matrix();
        this.f = 0;
        this.h = 126;
        this.o = new Runnable() { // from class: com.huawei.ui.device.views.adddevice.RadarImageView.2
            @Override // java.lang.Runnable
            public void run() {
                RadarImageView.this.g();
                RadarImageView.this.invalidate();
                RadarImageView.this.i();
            }
        };
        c();
    }

    public RadarImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = (float[][]) Array.newInstance((Class<?>) Float.TYPE, 4, 2);
        this.m = 0;
        this.n = new Matrix();
        this.f = 0;
        this.h = 126;
        this.o = new Runnable() { // from class: com.huawei.ui.device.views.adddevice.RadarImageView.2
            @Override // java.lang.Runnable
            public void run() {
                RadarImageView.this.g();
                RadarImageView.this.invalidate();
                RadarImageView.this.i();
            }
        };
        c();
    }

    private void c() {
        a();
        h();
        b();
    }

    private void a() {
        if (getContext() == null) {
            this.i = e;
        } else if (nsn.v(BaseApplication.getContext())) {
            this.i = f9308a;
        } else {
            this.i = e;
        }
    }

    private void h() {
        Paint paint = new Paint();
        this.k = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.k.setAntiAlias(true);
    }

    private void b() {
        Paint paint = new Paint();
        this.c = paint;
        paint.setAntiAlias(true);
        this.c.setStyle(Paint.Style.FILL_AND_STROKE);
        if (getContext() == null) {
            this.c.setColor(-16777216);
        } else if (nsn.v(BaseApplication.getContext())) {
            this.c.setColor(352321535);
        } else {
            this.c.setColor(-16777216);
        }
    }

    public void d() {
        this.g = false;
        this.n.reset();
        Matrix matrix = this.n;
        float f = this.j;
        matrix.postRotate(-90.0f, f, f);
        invalidate();
        removeCallbacks(this.o);
    }

    public void e() {
        if (nsn.ae(BaseApplication.getContext())) {
            this.h = 136;
        }
        e(this.h);
    }

    public void e(int i) {
        this.h = i;
        if (this.g) {
            return;
        }
        this.g = true;
        this.f = 0;
        b(i);
        post(this.o);
    }

    private void b(int i) {
        Context context;
        if (this.l == null && (context = getContext()) != null) {
            int c = nsn.c(context, i);
            this.j = c;
            float f = c;
            this.l = new SweepGradient(f, f, new int[]{0, 0, 16475426, 872113442}, b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.m = (this.m + 2) % 360;
        Matrix matrix = this.n;
        float f = this.j;
        matrix.postRotate(2.0f, f, f);
    }

    private void f() {
        int i = this.f + 1;
        this.f = i;
        this.f = i % 200;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        postDelayed(this.o, 20L);
    }

    private void cUK_(Canvas canvas) {
        float f = this.j;
        float f2 = d[0];
        this.k.setShader(this.l);
        canvas.concat(this.n);
        float f3 = this.j;
        canvas.drawCircle(f3, f3, f * f2, this.k);
    }

    private void cUJ_(Canvas canvas, int i) {
        int length = d.length;
        Path path = new Path();
        int i2 = length - 1;
        for (int i3 = i2; i3 >= 0; i3--) {
            this.c.setAlpha((int) e(i3, i));
            canvas.save();
            if (i3 != i2) {
                float f = this.j;
                path.addCircle(f, f, a(i3 + 1, i), Path.Direction.CW);
                canvas.clipOutPath(path);
            }
            float f2 = this.j;
            canvas.drawCircle(f2, f2, a(i3, i), this.c);
            canvas.restore();
        }
    }

    private float a(int i, int i2) {
        float f = this.j;
        float[] fArr = d;
        float f2 = fArr[i] * f;
        return f2 + (i >= 1 ? (((f * fArr[i - 1]) - f2) / 200.0f) * i2 : 0.0f);
    }

    private float e(int i, int i2) {
        if (i >= 1) {
            float[][] fArr = this.i;
            if (i < fArr.length) {
                float[] fArr2 = fArr[i];
                float f = fArr2[0];
                return f - (((f - fArr2[1]) / 200.0f) * i2);
            }
        }
        return this.i[i][0];
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        super.onDraw(canvas);
        f();
        cUJ_(canvas, this.f);
        canvas.save();
        cUK_(canvas);
        canvas.restore();
    }

    public void c(int i) {
        d();
        this.g = true;
        this.l = null;
        this.n.reset();
        b(i);
        post(this.o);
    }
}
