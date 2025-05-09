package defpackage;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/* loaded from: classes7.dex */
public class slz extends Drawable {

    /* renamed from: a, reason: collision with root package name */
    private float f17119a;
    private float b;
    private float c;
    private float d;
    private Paint e;
    private int g;
    private LinearGradient k;
    private long l;
    private int j = 1728053247;
    private boolean i = true;
    private float f = 0.0f;
    private boolean h = false;
    private Runnable n = new e();

    class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            slz.this.invalidateSelf();
        }
    }

    public slz() {
        Paint paint = new Paint();
        this.e = paint;
        paint.setAntiAlias(true);
        this.e.setStyle(Paint.Style.FILL);
        this.c = 0.0f;
        this.b = 0.0f;
        b(2);
    }

    private void b(long j) {
        this.l = j;
    }

    private void f() {
        this.d = -this.b;
    }

    private boolean g() {
        return this.g == 2;
    }

    private void h() {
        this.f = (this.b + this.f17119a) / 2000.0f;
        if (this.i) {
            this.i = false;
        }
    }

    private boolean i() {
        return this.h && this.i;
    }

    private void j() {
        int i = this.j;
        int i2 = 16777215 & i;
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, this.b, 0.0f, new int[]{i2, i, i2}, new float[]{0.0f, 0.93f, 1.0f}, Shader.TileMode.CLAMP);
        this.k = linearGradient;
        this.e.setShader(linearGradient);
    }

    public boolean a() {
        return this.g != 2;
    }

    public void b() {
        f();
        b(2);
    }

    public void c() {
        if (this.g == 1) {
            return;
        }
        this.h = true;
        b(1);
    }

    public void d() {
        if (this.g == 0) {
            return;
        }
        this.h = false;
        b(System.currentTimeMillis());
        scheduleSelf(this.n, 0L);
        b(0);
    }

    public void d(int i) {
        if (this.j != i) {
            this.j = i;
            j();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (g()) {
            this.h = false;
            return;
        }
        h();
        float e2 = this.d + (this.f * e());
        if (Float.compare(e2, this.f17119a) > 0) {
            int i = (int) this.f17119a;
            if (i != 0) {
                e2 = (e2 % i) - this.b;
            }
            this.i = true;
        }
        this.d = e2;
        canvas.save();
        canvas.translate(e2, 0.0f);
        Rect bounds = getBounds();
        float f = Float.compare(this.b + e2, this.f17119a) > 0 ? this.f17119a - e2 : this.b;
        if (Float.compare(e2, 0.0f) < 0) {
            float f2 = bounds.left - e2;
            canvas.clipRect(f2, bounds.top, f2 + f, bounds.bottom);
        }
        float f3 = bounds.left;
        canvas.drawRect(f3, bounds.top, f3 + f, bounds.bottom, this.e);
        canvas.restore();
        scheduleSelf(this.n, 0L);
        if (i()) {
            this.h = false;
            b();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        this.f17119a = (this.c * i) / 10000.0f;
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(Rect rect) {
        super.setBounds(rect);
        e(rect.left, rect.right);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    private long e() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - this.l;
        b(currentTimeMillis);
        if (j < 0) {
            return 0L;
        }
        return j;
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        e(i, i3);
    }

    private void b(int i) {
        this.g = i;
    }

    private void e(float f, float f2) {
        float f3 = f2 - f;
        this.c = f3;
        float level = (f3 * getLevel()) / 10000.0f;
        this.f17119a = level;
        float f4 = this.c * 0.3f;
        this.b = f4;
        this.f = (f4 + level) / 2000.0f;
        f();
        j();
    }
}
