package defpackage;

import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.huawei.uikit.animations.drawable.HwEclipseClipDrawable;

/* loaded from: classes7.dex */
public class skc extends HwEclipseClipDrawable {

    /* renamed from: a, reason: collision with root package name */
    private float f17085a;
    private RectF b;
    private Path c;
    private float d;
    private RectF e;
    private float g;
    private Rect h;

    public skc(Drawable drawable, int i, int i2) {
        super(drawable, i, i2);
        this.c = new Path();
        this.e = new RectF();
        this.b = new RectF();
        a();
    }

    private void a() {
        Rect bounds = getBounds();
        this.h = bounds;
        b(bounds.left, bounds.top, r1 + bounds.height(), this.h.bottom);
        this.g = d(this.h.height());
    }

    private void a(float f) {
        this.c.reset();
        this.c.addArc(this.e, 90.0f, 180.0f);
        Rect rect = this.h;
        float f2 = rect.left;
        float f3 = this.g;
        float width = rect.width();
        Rect rect2 = this.h;
        this.c.addRect(f2 + f3, rect2.top, (width * f) + rect2.left, rect2.bottom, Path.Direction.CCW);
    }

    private void c(float f) {
        this.f17085a = f;
    }

    private float d(float f) {
        return f / 2.0f;
    }

    private void e(float f) {
        this.c.reset();
        this.c.addArc(this.e, 90.0f, 180.0f);
        float e = Float.compare(e(), 0.0f) != 0 ? (f / e()) * this.g : 0.0f;
        Rect rect = this.h;
        float height = rect.left + rect.height();
        RectF rectF = this.b;
        Rect rect2 = this.h;
        rectF.set(rect2.left + e, rect2.top, height - e, rect2.bottom);
        this.c.addArc(this.b, 270.0f, -180.0f);
    }

    private void i(float f) {
        this.c.reset();
        this.c.addArc(this.e, 90.0f, 180.0f);
        float f2 = this.h.right - this.g;
        if (Float.compare(e(), b()) != 0) {
            Path path = this.c;
            Rect rect = this.h;
            path.addRect(this.g + rect.left, rect.top, f2, rect.bottom, Path.Direction.CCW);
        }
        float b = Float.compare(e(), 0.0f) != 0 ? ((f - b()) / e()) * this.g : 0.0f;
        RectF rectF = this.b;
        Rect rect2 = this.h;
        rectF.set(f2 - b, rect2.top, f2 + b, rect2.bottom);
        this.c.addArc(this.b, 270.0f, 180.0f);
    }

    protected float b() {
        return this.d;
    }

    void b(float f, float f2, float f3, float f4) {
        this.e.set(f, f2, f3, f4);
    }

    protected float e() {
        return this.f17085a;
    }

    @Override // com.huawei.uikit.animations.drawable.HwEclipseClipDrawable
    public Path getClipPath(int i) {
        float f = i / 10000.0f;
        if (Float.compare(f, e()) < 0) {
            e(f);
        } else if (Float.compare(f, b()) < 0) {
            a(f);
        } else {
            i(f);
        }
        return this.c;
    }

    @Override // com.huawei.uikit.animations.drawable.HwEclipseClipDrawable, android.graphics.drawable.Drawable
    public void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        a(i, i2, i3, i4);
        int i5 = i3 - i;
        if (i5 != 0) {
            c(this.g / i5);
            b(1.0f - e());
        }
    }

    void a(int i, int i2, int i3, int i4) {
        this.h.set(i, i2, i3, i4);
        b(i, i2, i + r4, i4);
        this.g = d(i4 - i2);
    }

    private void b(float f) {
        this.d = f;
    }
}
