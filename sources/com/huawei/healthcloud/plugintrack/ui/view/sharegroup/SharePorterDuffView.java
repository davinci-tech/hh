package com.huawei.healthcloud.plugintrack.ui.view.sharegroup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes8.dex */
public class SharePorterDuffView extends View {

    /* renamed from: a, reason: collision with root package name */
    private Bitmap f3815a;
    private Paint b;
    private final PorterDuff.Mode c;
    private Typeface d;
    private Bitmap e;
    private PorterDuffXfermode f;
    private int g;
    private int h;
    private int i;
    private String j;

    public SharePorterDuffView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        PorterDuff.Mode mode = PorterDuff.Mode.DST_IN;
        this.c = mode;
        this.b = new Paint(1);
        this.f = new PorterDuffXfermode(mode);
    }

    private void b() {
        Drawable drawable = new Drawable() { // from class: com.huawei.healthcloud.plugintrack.ui.view.sharegroup.SharePorterDuffView.2
            @Override // android.graphics.drawable.Drawable
            public void draw(Canvas canvas) {
            }

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
        };
        this.f3815a = Bitmap.createBitmap(this.i, this.g, Bitmap.Config.ALPHA_8);
        TextPaint textPaint = new TextPaint();
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(this.h);
        textPaint.setColor(-16777216);
        textPaint.setTypeface(this.d);
        Canvas canvas = new Canvas(this.f3815a);
        drawable.setBounds(0, 0, this.i, this.g);
        drawable.draw(canvas);
        canvas.drawBitmap(this.f3815a, 0.0f, 0.0f, new Paint());
        canvas.drawText(this.j, this.f3815a.getWidth() / 2.0f, ((this.f3815a.getHeight() - textPaint.getFontMetrics().top) - textPaint.getFontMetrics().bottom) / 2.0f, textPaint);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.g = getHeight();
        this.i = getWidth();
        b();
        canvas.drawColor(-16777216);
        int saveLayer = canvas.saveLayer(0.0f, 0.0f, this.i, this.g, null, 31);
        canvas.drawBitmap(this.e, 0.0f, 0.0f, this.b);
        this.b.setXfermode(this.f);
        canvas.drawBitmap(this.f3815a, 0.0f, 0.0f, this.b);
        this.b.setXfermode(null);
        canvas.restoreToCount(saveLayer);
    }
}
