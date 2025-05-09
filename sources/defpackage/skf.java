package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.Log;

/* loaded from: classes7.dex */
public class skf extends BitmapDrawable {

    /* renamed from: a, reason: collision with root package name */
    private float f17086a;
    private float b;
    private float c;
    private int d;
    private int e;
    private float f;
    private float g;
    private float h;
    private Handler i;
    private Paint j;
    private Runnable n;

    class e implements Runnable {
        float c = 0.0f;

        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            float f = this.c + 0.125f;
            this.c = f;
            if (f > 1.0f) {
                this.c = f - 1.0f;
            }
            skf.this.c(this.c);
            skf.this.i.postDelayed(this, skf.this.d);
        }
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (canvas == null) {
            Log.e("HwLowFrameLoadingDrawable", "draw error: canvas is null.");
            return;
        }
        this.j.setColor(this.e);
        dYL_(canvas);
        if (this.f17086a > 1.0f) {
            this.f17086a = 0.0f;
        }
        canvas.save();
        for (int i = 0; i < 8; i++) {
            int i2 = (int) (this.f17086a / 0.125f);
            int i3 = i2 + 3;
            if (i3 > 8) {
                if (i >= i2 || i < i2 - 5) {
                    this.j.setColor(-13421773);
                } else {
                    this.j.setColor(-7697782);
                }
            } else if (i < i2 || i >= i3) {
                this.j.setColor(-7697782);
            } else {
                this.j.setColor(-13421773);
            }
            canvas.drawCircle(this.g, this.h, this.c, this.j);
            canvas.rotate(45.0f, this.b, this.f);
        }
        canvas.restore();
    }

    public skf(Resources resources, int i, int i2) {
        super(resources, Bitmap.createBitmap(Math.min(i, 250), Math.min(i, 250), Bitmap.Config.ARGB_8888));
        this.f17086a = 0.0f;
        this.i = new Handler();
        this.n = new e();
        this.d = i2;
        a();
    }

    private void dYL_(Canvas canvas) {
        float dYJ_ = dYJ_(canvas);
        this.c = 0.125f * dYJ_;
        this.g = this.b;
        this.h = this.f - (dYJ_ * 0.85f);
    }

    private void a() {
        Paint paint = new Paint();
        this.j = paint;
        this.b = 0.0f;
        this.f = 0.0f;
        this.e = -7697782;
        paint.setColor(-7697782);
        this.j.setAntiAlias(true);
        c(0.0f);
        this.i.postDelayed(this.n, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(float f) {
        this.f17086a = f;
        invalidateSelf();
    }

    private float dYJ_(Canvas canvas) {
        this.b = canvas.getWidth() * 0.5f;
        float height = canvas.getHeight() * 0.5f;
        this.f = height;
        return Math.min(this.b, height);
    }
}
