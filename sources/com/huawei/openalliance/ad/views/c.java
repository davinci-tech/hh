package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import com.huawei.openalliance.ad.utils.dd;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class c extends ImageSpan {

    /* renamed from: a, reason: collision with root package name */
    int f8052a;
    int b;
    private WeakReference<Drawable> c;

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        return this.f8052a + super.getSize(paint, charSequence, i, i2, fontMetricsInt) + this.b;
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        Drawable a2 = a();
        canvas.save();
        canvas.translate(this.f8052a + f, (i3 + ((i5 - i3) / 2)) - (a2.getBounds().height() / 2));
        a2.draw(canvas);
        canvas.restore();
    }

    private void a(int i, int i2) {
        if (dd.c()) {
            this.f8052a = i2;
            this.b = i;
        } else {
            this.f8052a = i;
            this.b = i2;
        }
    }

    private Drawable a() {
        WeakReference<Drawable> weakReference = this.c;
        Drawable drawable = weakReference != null ? weakReference.get() : null;
        if (drawable != null) {
            return drawable;
        }
        Drawable drawable2 = getDrawable();
        this.c = new WeakReference<>(drawable2);
        return drawable2;
    }

    public c(Drawable drawable, int i, int i2, int i3) {
        super(drawable, i);
        a(i2, i3);
    }

    public c(Context context, Bitmap bitmap, int i, int i2, int i3) {
        super(context, bitmap, i);
        a(i2, i3);
    }
}
