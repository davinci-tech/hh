package defpackage;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

/* loaded from: classes9.dex */
public class smt extends BitmapDrawable {

    /* renamed from: a, reason: collision with root package name */
    private float f17124a;
    private int b;
    private int c;
    private int d;
    private int e;
    private float g;

    public void b(int i, int i2) {
        this.c = i;
        this.d = i2;
    }

    public void d(int i, int i2) {
        this.b = i;
        this.e = i2;
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (canvas == null) {
            Log.e("AnimDrawable", "draw: canvas is null");
            return;
        }
        canvas.save();
        canvas.clipRect(this.b, this.e, canvas.getWidth(), canvas.getHeight());
        canvas.translate(this.c + this.b, this.d + this.e);
        canvas.scale(this.f17124a, this.g);
        super.draw(canvas);
        canvas.restore();
    }
}
