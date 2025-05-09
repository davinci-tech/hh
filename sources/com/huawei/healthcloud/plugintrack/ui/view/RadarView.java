package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import defpackage.nsn;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class RadarView extends View {

    /* renamed from: a, reason: collision with root package name */
    private int f3785a;
    private float b;
    private int c;
    private int d;
    private List<Integer> e;
    private Paint f;
    private Paint g;
    private int h;
    private double[] i;
    private int j;
    private Paint k;
    private Paint l;
    private TextPaint m;
    private List<c> n;
    private float o;
    private int p;
    private TextPaint r;
    private String[] s;
    private int[] t;

    public RadarView(Context context) {
        this(context, null, 0);
    }

    public RadarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setAverageScore(int i) {
        this.f3785a = i;
    }

    public void setPercentOfMap(int[] iArr) {
        if (iArr == null) {
            LogUtil.b("Track_RadarView", "input not allowed");
            return;
        }
        int length = iArr.length;
        double[] dArr = new double[length];
        for (int i = 0; i < iArr.length; i++) {
            dArr[i] = iArr[i] / 100.0d;
        }
        this.t = (int[]) iArr.clone();
        this.i = dArr;
        this.j = length;
    }

    public void setColorList(List<Integer> list) {
        this.e = list;
    }

    public RadarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = 5;
        this.h = 4;
        this.i = new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d};
        this.s = new String[]{getContext().getString(R.string._2130843152_res_0x7f021610), getContext().getString(R.string._2130843153_res_0x7f021611), getContext().getString(R.string._2130843154_res_0x7f021612), getContext().getString(R.string._2130843155_res_0x7f021613), getContext().getString(R.string._2130843156_res_0x7f021614)};
        this.t = new int[]{0, 0, 0, 0, 0};
        this.e = new ArrayList(5);
        this.n = new ArrayList(5);
        this.p = 0;
        int i2 = this.j;
        if (i2 != 0) {
            this.b = (float) (6.283185307179586d / i2);
        }
        Paint paint = new Paint();
        this.g = paint;
        paint.setColor(getResources().getColor(R.color._2131298161_res_0x7f090771));
        this.g.setAntiAlias(true);
        this.g.setStyle(Paint.Style.STROKE);
        this.g.setStrokeWidth(4.0f);
        Paint paint2 = new Paint();
        this.f = paint2;
        paint2.setColor(-2631721);
        this.f.setAntiAlias(true);
        this.f.setStyle(Paint.Style.STROKE);
        this.f.setStrokeWidth(nsn.c(context, 0.5f));
        this.f.setAlpha(getResources().getInteger(R.integer._2131623941_res_0x7f0e0005));
        TextPaint textPaint = new TextPaint();
        this.r = textPaint;
        textPaint.setColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        this.r.setAntiAlias(true);
        this.r.setStyle(Paint.Style.FILL);
        this.r.setTextSize(nsn.c(context, 10.0f));
        TextPaint textPaint2 = new TextPaint();
        this.m = textPaint2;
        textPaint2.setColor(getResources().getColor(R.color._2131296927_res_0x7f09029f));
        this.m.setAntiAlias(true);
        this.m.setStyle(Paint.Style.FILL);
        this.m.setTextSize(nsn.c(context, 18.0f));
        this.m.setTypeface(Typeface.DEFAULT_BOLD);
        this.k = new Paint();
        this.k.setShader(new LinearGradient(0.0f, 0.0f, 300.0f, 300.0f, 0, getResources().getColor(R.color._2131296927_res_0x7f09029f), Shader.TileMode.MIRROR));
        this.k.setAlpha(50);
        this.k.setStyle(Paint.Style.FILL_AND_STROKE);
        this.k.setAntiAlias(true);
        Paint paint3 = new Paint();
        this.l = paint3;
        paint3.setColor(getResources().getColor(R.color._2131296927_res_0x7f09029f));
        this.l.setStyle(Paint.Style.STROKE);
        this.l.setStrokeWidth(nsn.c(getContext(), 3.0f));
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.o = (Math.min(i2, i) / 2.0f) * 0.7f;
        this.c = i / 2;
        this.d = i2 / 2;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        biy_(canvas);
        biw_(canvas);
        biA_(canvas);
        biz_(canvas);
        setCircle(canvas);
        biu_(canvas);
    }

    private void biu_(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color._2131296492_res_0x7f0900ec));
        paint.setTextSize(nsn.c(getContext(), 30.0f));
        Rect rect = new Rect();
        String valueOf = String.valueOf(UnitUtil.e(this.f3785a, 1, 0));
        paint.getTextBounds(valueOf, 0, valueOf.length(), rect);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText(valueOf, this.c - (paint.measureText(valueOf) * 0.5f), this.d + ((rect.bottom - rect.top) * 0.5f), paint);
    }

    private void biy_(Canvas canvas) {
        Path path = new Path();
        float f = this.o;
        int i = this.h;
        float f2 = f / i;
        while (i > 0) {
            float f3 = i * f2;
            for (int i2 = 0; i2 < this.j; i2++) {
                if (i2 == 0) {
                    path.moveTo(this.c, this.d - f3);
                } else {
                    float f4 = i2;
                    double d = f3;
                    path.lineTo((float) (this.c + (Math.sin(this.b * f4) * d)), (float) (this.d - (Math.cos(this.b * f4) * d)));
                }
            }
            path.lineTo(this.c, this.d - f3);
            Paint paint = new Paint();
            Paint paint2 = new Paint();
            int i3 = i - 1;
            if (koq.d(this.e, i3)) {
                paint.setColor(this.e.get(i3).intValue());
            }
            paint.setStyle(Paint.Style.FILL);
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setAntiAlias(true);
            paint2.setColor(getResources().getColor(R.color._2131296498_res_0x7f0900f2));
            paint2.setStrokeWidth(nsn.c(getContext(), 0.5f));
            paint2.setAlpha(getResources().getInteger(R.integer._2131623941_res_0x7f0e0005));
            paint.setAntiAlias(true);
            paint.setAlpha(getResources().getInteger(R.integer._2131623942_res_0x7f0e0006));
            path.close();
            canvas.drawPath(path, paint);
            canvas.drawPath(path, paint2);
            path.reset();
            i--;
        }
    }

    private void biw_(Canvas canvas) {
        for (int i = 0; i < this.j; i++) {
            int i2 = this.c;
            float f = i;
            canvas.drawLine(i2, this.d, (float) (i2 + (Math.sin(this.b * f) * this.o)), (float) (this.d - (Math.cos(this.b * f) * this.o)), this.f);
        }
    }

    private void biA_(Canvas canvas) {
        for (int i = 0; i < this.j; i++) {
            String str = this.s[i];
            Rect rect = new Rect();
            this.r.getTextBounds(str, 0, str.length(), rect);
            String valueOf = String.valueOf(this.t[i]);
            Rect rect2 = new Rect();
            this.m.getTextBounds(valueOf, 0, valueOf.length(), rect2);
            bix_(canvas, i, str, rect, rect2);
        }
    }

    private void bix_(Canvas canvas, int i, String str, Rect rect, Rect rect2) {
        float f = rect.bottom - rect.top;
        float f2 = i;
        float sin = (float) (this.c + (Math.sin(this.b * f2) * (this.o + 12.0f)));
        float cos = (float) (this.d - (Math.cos(this.b * f2) * (this.o + 12.0f)));
        if (this.b * f2 == 0.0f) {
            this.r.setTextAlign(Paint.Align.CENTER);
            this.m.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(this.s[i], sin, cos - nsn.c(getContext(), 0.5f), this.r);
            canvas.drawText(UnitUtil.e(this.t[i], 1, 0), sin, (cos - f) - nsn.c(getContext(), 1.0f), this.m);
            this.r.setTextAlign(Paint.Align.LEFT);
            this.m.setTextAlign(Paint.Align.LEFT);
            return;
        }
        float c2 = nsn.c(getContext(), 40.0f);
        float f3 = this.b * f2;
        if (f3 > 0.0f && f3 < 1.5707963267948966d) {
            this.r.setTextAlign(Paint.Align.CENTER);
            this.m.setTextAlign(Paint.Align.CENTER);
            biB_(canvas, sin + nsn.c(getContext(), 3.0f), cos, str, true);
            canvas.drawText(UnitUtil.e(this.t[i], 1, 0), sin + nsn.c(getContext(), 3.0f) + (c2 * 0.5f), cos, this.m);
            this.r.setTextAlign(Paint.Align.LEFT);
            this.m.setTextAlign(Paint.Align.LEFT);
            return;
        }
        biv_(canvas, i, str, f, rect2.bottom - rect2.top);
    }

    private void biv_(Canvas canvas, int i, String str, float f, float f2) {
        float sin = (float) (this.c + (Math.sin(this.b * r3) * (this.o + 12.0f)));
        float cos = (float) (this.d - (Math.cos(this.b * r3) * (this.o + 12.0f)));
        double d = this.b * i;
        if (d >= 1.5707963267948966d && d < 3.141592653589793d) {
            this.r.setTextAlign(Paint.Align.CENTER);
            this.m.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(str, sin, cos + f + nsn.c(getContext(), 7.0f) + f2, this.r);
            canvas.drawText(UnitUtil.e(this.t[i], 1, 0), sin, cos + nsn.c(getContext(), 7.0f) + f, this.m);
            this.m.setTextAlign(Paint.Align.LEFT);
            return;
        }
        if (d >= 3.141592653589793d && d < 4.71238898038469d) {
            this.r.setTextAlign(Paint.Align.CENTER);
            this.m.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(str, sin, cos + f + nsn.c(getContext(), 7.0f) + f2, this.r);
            canvas.drawText(UnitUtil.e(this.t[i], 1, 0), sin, cos + nsn.c(getContext(), 7.0f) + f, this.m);
            return;
        }
        if (d < 4.71238898038469d || d >= 6.283185307179586d) {
            return;
        }
        this.r.setTextAlign(Paint.Align.CENTER);
        this.m.setTextAlign(Paint.Align.CENTER);
        biB_(canvas, sin - nsn.c(getContext(), 3.0f), cos, str, false);
        canvas.drawText(UnitUtil.e(this.t[i], 1, 0), (sin - nsn.c(getContext(), 3.0f)) - (nsn.c(getContext(), 40.0f) * 0.5f), cos, this.m);
    }

    private String d(String str) {
        return TextUtils.ellipsize(str, this.r, nsn.c(getContext(), 228.0f), TextUtils.TruncateAt.END).toString();
    }

    private void biB_(Canvas canvas, float f, float f2, String str, boolean z) {
        int min;
        TextPaint textPaint = new TextPaint();
        if (z) {
            textPaint.setTextAlign(Paint.Align.LEFT);
            min = Math.min(nsn.c(getContext(), 76.0f), Math.abs(this.p - ((int) f)));
        } else {
            textPaint.setTextAlign(Paint.Align.RIGHT);
            min = Math.min(nsn.c(getContext(), 76.0f), (int) f);
        }
        textPaint.setColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(nsn.c(getContext(), 10.0f));
        textPaint.setStyle(Paint.Style.FILL);
        StaticLayout staticLayout = new StaticLayout(d(str), textPaint, min, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
        canvas.save();
        canvas.translate(f, f2);
        staticLayout.draw(canvas);
        canvas.restore();
    }

    private void biz_(Canvas canvas) {
        this.n.clear();
        Path path = new Path();
        float f = this.o / this.h;
        for (int i = 0; i < this.j; i++) {
            if (i == 0) {
                path.moveTo(this.c, (float) ((this.d - f) - ((this.o - f) * this.i[i])));
                this.n.add(new c(this.c, (float) ((this.d - f) - ((this.o - f) * this.i[i]))));
            } else {
                float f2 = i;
                double d = f;
                float sin = (float) (this.c + (Math.sin(this.b * f2) * ((this.i[i] * (this.o - f)) + d)));
                float cos = (float) (this.d - (Math.cos(this.b * f2) * ((this.i[i] * (this.o - f)) + d)));
                path.lineTo(sin, cos);
                this.n.add(new c(sin, cos));
            }
        }
        path.close();
        canvas.drawPath(path, this.k);
        canvas.drawPath(path, this.l);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.p = View.MeasureSpec.getSize(i);
    }

    private void setCircle(Canvas canvas) {
        for (int i = 0; i < this.n.size(); i++) {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(getResources().getColor(R.color._2131296927_res_0x7f09029f));
            paint.setAlpha(255);
            canvas.drawCircle(this.n.get(i).d, this.n.get(i).b, nsn.c(getContext(), 4.5f), paint);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(getResources().getColor(R.color._2131296493_res_0x7f0900ed));
            canvas.drawCircle(this.n.get(i).d, this.n.get(i).b, nsn.c(getContext(), 2.5f), paint);
        }
    }

    static class c {
        private float b;
        private float d;

        private c(float f, float f2) {
            this.d = f;
            this.b = f2;
        }
    }
}
