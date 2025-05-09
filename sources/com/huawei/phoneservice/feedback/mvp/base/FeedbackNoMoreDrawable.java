package com.huawei.phoneservice.feedback.mvp.base;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import com.huawei.health.R;
import com.huawei.phoneservice.faq.base.util.t;
import com.huawei.phoneservice.feedback.mvp.base.FeedbackFootOverScrollListView;

/* loaded from: classes5.dex */
public class FeedbackNoMoreDrawable extends Drawable implements FeedbackFootOverScrollListView.a {

    /* renamed from: a, reason: collision with root package name */
    private float f8266a;
    private TextPaint b;
    private Context c;
    private String d;
    private Paint e;
    private float f;
    private int g;
    private float h;
    private Drawable i;
    private float j;
    private float k;
    private float l;
    private float m;
    private float n;
    private float o;
    private float p;
    private float q;
    private float r;
    private float s;
    private float t;
    private int u;
    private boolean x;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.e.setColorFilter(colorFilter);
        this.b.setColorFilter(colorFilter);
        this.i.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.e.setAlpha(i);
        this.b.setAlpha(i);
        this.i.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return (int) this.f8266a;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.f8266a;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        c(bounds.width());
        float max = Math.max(this.u, bounds.top);
        canvas.save();
        bounds.top = (int) max;
        canvas.clipRect(bounds);
        canvas.drawColor(this.g);
        canvas.translate(0.0f, max);
        float f = this.n;
        float f2 = this.m;
        float f3 = this.o;
        canvas.drawLine(f - f2, f3, f, f3, this.e);
        float f4 = this.q;
        float f5 = this.t;
        canvas.drawLine(f4, f5, f4 + this.m, f5, this.e);
        canvas.translate(this.s, this.r);
        this.i.draw(canvas);
        canvas.translate(-this.s, -this.r);
        StaticLayout staticLayout = new StaticLayout(this.d, this.b, (int) this.j, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        float height = (this.f8266a / 2.0f) - (staticLayout.getHeight() / 2.0f);
        canvas.translate(this.p, height);
        staticLayout.draw(canvas);
        canvas.translate(-this.p, -height);
        canvas.translate(0.0f, -max);
        canvas.restore();
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.FeedbackFootOverScrollListView.a
    public void a(int i, int i2, int i3, int i4) {
        Resources resources;
        int i5;
        if (i2 > 0) {
            this.u = i2;
        }
        if (this.c.getResources().getConfiguration().orientation == 1) {
            resources = this.c.getResources();
            i5 = R.dimen._2131362791_res_0x7f0a03e7;
        } else {
            resources = this.c.getResources();
            i5 = R.dimen._2131362792_res_0x7f0a03e8;
        }
        this.m = resources.getDimension(i5);
    }

    private void c(int i) {
        float f = this.k;
        float f2 = this.h;
        float f3 = this.j;
        float f4 = i / 2.0f;
        float f5 = ((f2 + f) + f3) / 2.0f;
        float f6 = this.f;
        float f7 = (f4 - f5) - f6;
        this.n = f7;
        float f8 = this.f8266a / 2.0f;
        float f9 = f8 - (this.l / 2.0f);
        this.o = f9;
        float f10 = f4 + f5 + f6;
        this.q = f10;
        this.t = f9;
        if (this.x) {
            this.s = (f10 - f6) - f;
            this.p = f7 + f6;
        } else {
            this.s = f7 + f6;
            this.p = (f10 - f6) - f3;
        }
        this.r = f8 - (f / 2.0f);
    }

    public FeedbackNoMoreDrawable(Context context) {
        this.c = context;
        this.l = context.getResources().getDimension(R.dimen._2131362790_res_0x7f0a03e6);
        Paint paint = new Paint(1);
        this.e = paint;
        paint.setStrokeWidth(this.l);
        this.e.setColor(context.getResources().getColor(R.color.feedback_sdk_list_item_hint_text_color_normal));
        TextPaint textPaint = new TextPaint();
        this.b = textPaint;
        textPaint.setColor(context.getResources().getColor(R.color.feedback_sdk_label_assist_text_color_normal));
        this.b.setAntiAlias(true);
        this.b.setTextSize(context.getResources().getDimension(R.dimen._2131362793_res_0x7f0a03e9));
        this.f8266a = context.getResources().getDimension(R.dimen._2131362789_res_0x7f0a03e5) + (context.getResources().getDimension(R.dimen._2131362788_res_0x7f0a03e4) * 2.0f);
        this.g = context.getResources().getColor(R.color.feedback_sdk_grid_divider_color);
        this.i = context.getResources().getDrawable(R.drawable.feedback_ic_smile_gray);
        float dimension = context.getResources().getDimension(R.dimen._2131362789_res_0x7f0a03e5);
        this.k = dimension;
        int i = (int) dimension;
        this.i.setBounds(0, 0, i, i);
        this.f = context.getResources().getDimension(R.dimen._2131362787_res_0x7f0a03e3);
        this.h = context.getResources().getDimension(R.dimen._2131362794_res_0x7f0a03ea);
        this.m = context.getResources().getDimension(R.dimen._2131362791_res_0x7f0a03e7);
        String string = context.getResources().getString(R.string._2130850893_res_0x7f02344d);
        this.d = string;
        this.j = this.b.measureText(string);
        this.x = t.c(context);
        this.u = com.huawei.phoneservice.faq.base.util.b.c(context);
    }
}
