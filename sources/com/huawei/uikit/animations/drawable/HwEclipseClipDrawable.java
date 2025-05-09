package com.huawei.uikit.animations.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

/* loaded from: classes7.dex */
public abstract class HwEclipseClipDrawable extends ClipDrawable {
    private static final String g = "HwEclipseClipDrawable";
    private static final int h = 10000;

    /* renamed from: a, reason: collision with root package name */
    private Drawable f10570a;
    private Paint b;
    private Bitmap c;
    private Bitmap d;
    private Canvas e;
    private PorterDuffXfermode f;

    public HwEclipseClipDrawable(Drawable drawable, int i, int i2) {
        super(drawable, i, i2);
        a(drawable);
    }

    private void a(Drawable drawable) {
        Paint paint = new Paint();
        this.b = paint;
        paint.setAntiAlias(true);
        this.b.setColor(-16711936);
        this.f10570a = drawable;
        setPorterDuffMode(PorterDuff.Mode.SRC_IN);
    }

    private void b() {
        Canvas canvas;
        d();
        Path clipPath = getClipPath(getLevel());
        if (clipPath == null || (canvas = this.e) == null) {
            Log.e(g, "getClipPath fail.");
        } else {
            canvas.drawPath(clipPath, this.b);
        }
    }

    private void c() {
        Rect bounds = getBounds();
        if (bounds.width() <= 0 || bounds.height() <= 0) {
            return;
        }
        Bitmap bitmap = this.c;
        if (bitmap != null) {
            bitmap.recycle();
        }
        this.c = Bitmap.createBitmap(bounds.width(), bounds.height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(this.c);
        this.f10570a.setBounds(bounds);
        int level = this.f10570a.getLevel();
        this.f10570a.setLevel(10000);
        this.f10570a.draw(canvas);
        this.f10570a.setLevel(level);
    }

    private void d() {
        Rect bounds = getBounds();
        if (bounds.width() <= 0 || bounds.height() <= 0) {
            return;
        }
        Bitmap bitmap = this.d;
        if (bitmap != null) {
            bitmap.recycle();
        }
        this.d = Bitmap.createBitmap(bounds.width(), bounds.height(), Bitmap.Config.ARGB_8888);
        this.e = new Canvas(this.d);
    }

    @Override // android.graphics.drawable.ClipDrawable, android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        a();
        b();
        if (this.c == null || this.d == null) {
            return;
        }
        Rect bounds = getBounds();
        int saveLayer = canvas.saveLayer(0.0f, 0.0f, bounds.width(), bounds.height(), null);
        canvas.drawBitmap(this.d, 0.0f, 0.0f, this.b);
        this.b.setXfermode(this.f);
        canvas.drawBitmap(this.c, 0.0f, 0.0f, this.b);
        this.b.setXfermode(null);
        canvas.restoreToCount(saveLayer);
    }

    protected abstract Path getClipPath(int i);

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        this.f10570a.setBounds(i, i2, i3, i4);
        if (this.c != null) {
            c();
        }
        if (this.d != null) {
            d();
            Path clipPath = getClipPath(getLevel());
            if (clipPath != null) {
                this.e.drawPath(clipPath, this.b);
            } else {
                Log.e(g, "getClipPath fail.");
            }
        }
    }

    public void setEclipseInsideColor(int i) {
        this.b.setColor(i);
    }

    public void setPorterDuffMode(PorterDuff.Mode mode) {
        this.f = new PorterDuffXfermode(mode);
    }

    private void a() {
        if (this.c != null) {
            return;
        }
        c();
    }
}
