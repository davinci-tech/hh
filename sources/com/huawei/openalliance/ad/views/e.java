package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/* loaded from: classes9.dex */
public class e extends ImageView {

    /* renamed from: a, reason: collision with root package name */
    a f8057a;

    static class a extends Drawable {

        /* renamed from: a, reason: collision with root package name */
        private static final int f8058a = Color.parseColor("#007DFF");
        private final Paint b;
        private final Paint c;
        private int d;

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            canvas.drawRect(getBounds(), this.b);
            float f = this.d / 100.0f;
            if (getLayoutDirection() == 1) {
                canvas.rotate(180.0f, (getBounds().right / 2) - (getBounds().left / 2), (getBounds().bottom / 2) - (getBounds().top / 2));
            }
            canvas.drawRect(getBounds().left, getBounds().top, getBounds().right * f, getBounds().bottom, this.c);
        }

        public void a(int i) {
            if (i > 100) {
                i = 100;
            }
            if (i >= this.d) {
                this.d = i;
            }
            invalidateSelf();
        }

        public void a() {
            this.d = 0;
        }

        public a(Context context) {
            Paint paint = new Paint();
            this.b = paint;
            paint.setColor(-16777216);
            paint.setAlpha(26);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            Paint paint2 = new Paint();
            this.c = paint2;
            paint2.setColor(f8058a);
            paint2.setAlpha(255);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setAntiAlias(true);
        }
    }

    public void setProgress(int i) {
        a aVar = this.f8057a;
        if (aVar != null) {
            aVar.a(i);
        }
    }

    public void a() {
        a aVar = this.f8057a;
        if (aVar != null) {
            aVar.a();
        }
    }

    public e(Context context) {
        super(context);
        a aVar = new a(context);
        this.f8057a = aVar;
        setImageDrawable(aVar);
    }
}
