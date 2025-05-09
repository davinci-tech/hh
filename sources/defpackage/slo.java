package defpackage;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/* loaded from: classes9.dex */
public class slo extends Drawable {

    /* renamed from: a, reason: collision with root package name */
    private int f17107a;
    private int c;
    private int[] d;
    private Bitmap h;
    private Paint b = new Paint();
    private float[] e = {0.0f, 1.0f};

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.h, 0.0f, 0.0f, this.b);
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        canvas.drawCircle(getBounds().centerX(), getBounds().centerY() - this.f17107a, (getBounds().width() >> 1) - this.f17107a, paint);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        if (rect == null) {
            return;
        }
        super.onBoundsChange(rect);
        Rect rect2 = new Rect(rect);
        int i = 25 - this.c;
        rect2.inset(i, i);
        Bitmap bitmap = this.h;
        if (bitmap == null || bitmap.getWidth() != rect.width() || this.h.getHeight() != rect.height()) {
            this.h = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
        }
        Paint paint = this.b;
        float f = rect.left;
        float f2 = rect.top;
        paint.setShader(new LinearGradient(f, f2, rect.right, f2, this.d, this.e, Shader.TileMode.CLAMP));
        this.b.setMaskFilter(new BlurMaskFilter(25.0f, BlurMaskFilter.Blur.NORMAL));
        new Canvas(this.h).drawCircle(rect2.centerX(), rect2.centerY(), rect2.width() >> 1, this.b);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public slo(int[] iArr, int i, int i2) {
        this.d = iArr;
        this.c = i;
        this.f17107a = i2;
    }
}
