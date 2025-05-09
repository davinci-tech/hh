package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hidatamanager.util.LogUtils;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.hjy;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class SkippingPerformanceRadarView extends View {
    private String aa;
    private float ad;
    private Canvas b;
    private float d;
    private Paint f;
    private int g;
    private final Context h;
    private int i;
    private float[] j;
    private Paint k;
    private final List<PointF> l;
    private float m;
    private String[] n;
    private Paint o;
    private Paint p;
    private Paint q;
    private float[] r;
    private float s;
    private Paint t;
    private TextPaint u;
    private TextPaint v;
    private String w;
    private String[] x;
    private float y;
    private static final int c = nsn.c(BaseApplication.e(), 0.5f);

    /* renamed from: a, reason: collision with root package name */
    private static final int f3793a = nsn.c(BaseApplication.e(), 2.0f);
    private static final int[] e = {R.string._2130843711_res_0x7f02183f, R.string._2130847667_res_0x7f0227b3, R.string._2130847674_res_0x7f0227ba, R.string._2130847672_res_0x7f0227b8, R.string._2130847669_res_0x7f0227b5};

    public SkippingPerformanceRadarView(Context context) {
        this(context, null, 0);
    }

    public SkippingPerformanceRadarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SkippingPerformanceRadarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = new ArrayList(5);
        this.y = 0.0f;
        this.m = 0.0f;
        this.h = BaseApplication.e();
        g();
        i();
    }

    private void g() {
        Paint paint = new Paint();
        this.q = paint;
        paint.setColor(ContextCompat.getColor(this.h, R.color._2131296997_res_0x7f0902e5));
        this.q.setAntiAlias(true);
        this.q.setStyle(Paint.Style.FILL);
        this.q.setStrokeWidth(nsn.c(this.h, 4.0f));
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{20.0f, 20.0f}, 0.0f);
        this.q.setPathEffect(dashPathEffect);
        Paint paint2 = new Paint();
        this.k = paint2;
        paint2.setColor(ContextCompat.getColor(this.h, R.color._2131296498_res_0x7f0900f2));
        this.k.setAntiAlias(true);
        this.k.setStyle(Paint.Style.STROKE);
        this.k.setStrokeWidth(c);
        this.k.setPathEffect(dashPathEffect);
        this.k.setAlpha(getResources().getInteger(R.integer._2131623941_res_0x7f0e0005));
        this.u = bjl_(R.color._2131299241_res_0x7f090ba9, 12.0f, false);
        this.v = bjl_(R.color._2131299238_res_0x7f090ba6, 18.0f, true);
        this.p = bji_(R.color._2131296927_res_0x7f09029f);
        this.t = bjj_(R.color._2131296927_res_0x7f09029f);
        this.f = bji_(R.color._2131299366_res_0x7f090c26);
        this.o = bjj_(R.color._2131299366_res_0x7f090c26);
    }

    private TextPaint bjl_(int i, float f, boolean z) {
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(ContextCompat.getColor(this.h, i));
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(nsn.c(this.h, f));
        if (z) {
            textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        }
        return textPaint;
    }

    private Paint bji_(int i) {
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(this.h, i));
        paint.setAlpha(50);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        return paint;
    }

    private Paint bjj_(int i) {
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(this.h, i));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(nsn.c(this.h, 4.0f));
        paint.setAntiAlias(true);
        return paint;
    }

    private void i() {
        this.d = 1.2566371f;
    }

    public void setData(String[] strArr, float[] fArr, boolean z) {
        if (strArr == null || fArr == null || strArr.length != fArr.length) {
            LogUtil.b("PerformanceRadarView", "input not allowed");
        } else if (z) {
            this.n = (String[]) strArr.clone();
            this.j = (float[]) fArr.clone();
        } else {
            this.x = (String[]) strArr.clone();
            this.r = (float[]) fArr.clone();
        }
    }

    public void setTextSize(float f) {
        this.y = f;
    }

    public void setNumberSize(float f) {
        this.m = f;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        float min = (Math.min(i2, i) / 2.0f) * 0.7f;
        this.s = min;
        int i5 = i / 2;
        float c2 = (i5 - min) - nsn.c(this.h, 12.0f);
        this.ad = c2;
        if (c2 <= 0.0f) {
            c2 = nsn.c(this.h, 56.0f);
        }
        this.ad = c2;
        this.g = i5;
        this.i = i2 / 2;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.b = canvas;
        b();
        e();
        h();
        a();
    }

    private void b() {
        float sin;
        float f;
        float cos;
        int i;
        int i2;
        this.b.save();
        Path path = new Path();
        Path path2 = new Path();
        float f2 = this.s / 4.0f;
        this.l.clear();
        int i3 = 0;
        while (true) {
            int i4 = 4;
            if (i3 <= 4) {
                float f3 = i3 * f2;
                int i5 = 0;
                while (i5 < 5) {
                    if (i5 == 0) {
                        sin = this.g;
                        cos = this.i - f3;
                        float f4 = c;
                        path.moveTo(sin, cos);
                        path2.moveTo(sin, f4 + cos);
                        i = i3;
                        i2 = i4;
                        f = f3;
                    } else {
                        float f5 = i5;
                        double d = f3;
                        sin = (float) (this.g + (Math.sin(this.d * f5) * d));
                        f = f3;
                        cos = (float) (this.i - (Math.cos(this.d * f5) * d));
                        double d2 = f - c;
                        path2.lineTo((float) (this.g + (Math.sin(this.d * f5) * d2)), (float) (this.i - (Math.cos(this.d * f5) * d2)));
                        path.lineTo(sin, cos);
                        i = i3;
                        i2 = 4;
                    }
                    if (i == i2) {
                        this.l.add(new PointF(sin, cos));
                    }
                    i5++;
                    i4 = i2;
                    f3 = f;
                    i3 = i;
                }
                path.close();
                this.b.clipOutPath(path2);
                this.b.drawPath(path, this.k);
                path.reset();
                i3++;
            } else {
                this.b.restore();
                return;
            }
        }
    }

    private void e() {
        for (int i = 0; i < 5; i++) {
            this.b.drawLine(this.l.get(i).x, this.l.get(i).y, this.g, this.i, this.k);
        }
    }

    private void h() {
        float f = this.y;
        if (f != 0.0f) {
            this.u.setTextSize(f);
        }
        float f2 = this.m;
        if (f2 != 0.0f) {
            this.v.setTextSize(f2);
        }
        int a2 = c() ? hjy.a(this.j) : -1;
        if (d()) {
            a2 = hjy.a(this.r);
        }
        for (int i = 0; i < 5; i++) {
            String string = this.h.getString(e[i]);
            this.aa = string;
            Rect bjk_ = bjk_(string, this.u);
            if (c()) {
                this.w = this.n[i];
            } else if (d()) {
                this.w = this.x[i];
            } else {
                this.w = "0";
            }
            Rect bjk_2 = bjk_(this.w, this.v);
            if (a2 == i) {
                this.v.setColor(ContextCompat.getColor(getContext(), R.color._2131296651_res_0x7f09018b));
            } else {
                this.v.setColor(ContextCompat.getColor(getContext(), R.color._2131299236_res_0x7f090ba4));
            }
            b(i, bjk_.width(), bjk_.height(), bjk_2.width(), bjk_2.height());
        }
    }

    private void b(int i, int i2, int i3, int i4, int i5) {
        float sin = (float) (this.g + (Math.sin(this.d * r4) * (this.s + nsn.c(this.h, 12.0f))));
        float cos = (float) (this.i - (Math.cos(this.d * r4) * (this.s + nsn.c(this.h, 12.0f))));
        float f = this.d * i;
        if (f == 0.0f) {
            this.u.setTextAlign(Paint.Align.CENTER);
            this.v.setTextAlign(Paint.Align.CENTER);
            float f2 = f3793a;
            this.b.drawText(this.aa, sin, cos, this.u);
            this.b.drawText(this.w, sin, (cos - i3) - f2, this.v);
            return;
        }
        if (f > 0.0f && f < 1.5707963267948966d) {
            this.u.setTextAlign(Paint.Align.LEFT);
            this.v.setTextAlign(Paint.Align.LEFT);
            if (this.v.measureText(this.w) > this.ad) {
                setAutoScoreTextSize(18);
            }
            TextPaint textPaint = this.v;
            int c2 = nsn.c(this.h, 70.0f);
            int i6 = f3793a;
            bjh_(textPaint, c2, sin, (cos - i5) - (i6 * 3), this.w);
            this.v.setTextSize(nsn.c(this.h, 18.0f));
            bjh_(this.u, (int) this.ad, sin, cos + i6, this.aa);
            return;
        }
        c(i, sin, cos, i3, i5);
    }

    private void setAutoScoreTextSize(int i) {
        do {
            i--;
            if (i <= 0) {
                return;
            } else {
                this.v.setTextSize(nsn.c(this.h, i));
            }
        } while (this.v.measureText(this.w) > this.ad);
    }

    private void c(int i, float f, float f2, int i2, int i3) {
        double d = this.d * i;
        if (d >= 1.5707963267948966d && d < 3.141592653589793d) {
            this.u.setTextAlign(Paint.Align.CENTER);
            this.v.setTextAlign(Paint.Align.CENTER);
            float f3 = f2 + i3;
            this.b.drawText(this.w, f, f3, this.v);
            this.b.drawText(this.aa, f, f3 + i2 + (f3793a * 2), this.u);
            return;
        }
        if (d >= 3.141592653589793d && d < 4.71238898038469d) {
            this.u.setTextAlign(Paint.Align.CENTER);
            this.v.setTextAlign(Paint.Align.CENTER);
            float f4 = f2 + i3;
            this.b.drawText(this.w, f, f4, this.v);
            this.b.drawText(this.aa, f, f4 + i2 + (f3793a * 2), this.u);
            return;
        }
        if (d >= 4.71238898038469d && d < 6.283185307179586d) {
            this.u.setTextAlign(Paint.Align.RIGHT);
            this.v.setTextAlign(Paint.Align.RIGHT);
            if (this.v.measureText(this.w) > this.ad) {
                setAutoScoreTextSize(18);
            }
            TextPaint textPaint = this.v;
            int c2 = nsn.c(this.h, 70.0f);
            float f5 = f3793a;
            bjh_(textPaint, c2, f - f5, (f2 - i3) - (r7 * 3), this.w);
            this.v.setTextSize(nsn.c(this.h, 18.0f));
            bjh_(this.u, (int) this.ad, f, f2 + f5, this.aa);
            return;
        }
        LogUtils.e("PerformanceRadarView", "wrong angle branch");
    }

    private void bjh_(TextPaint textPaint, int i, float f, float f2, String str) {
        Layout.Alignment alignment;
        if (LanguageUtil.bc(this.h) && !nsn.c(str)) {
            alignment = Layout.Alignment.ALIGN_OPPOSITE;
        } else {
            alignment = Layout.Alignment.ALIGN_NORMAL;
        }
        this.b.save();
        StaticLayout staticLayout = new StaticLayout(str, textPaint, i, alignment, 1.0f, 0.0f, true);
        this.b.translate(f, f2);
        staticLayout.draw(this.b);
        this.b.restore();
    }

    public boolean c() {
        float[] fArr;
        float[] fArr2 = this.j;
        return fArr2 != null && fArr2.length > 0 && ((fArr = this.r) == null || fArr.length == 0);
    }

    public boolean d() {
        float[] fArr;
        float[] fArr2 = this.j;
        return fArr2 != null && fArr2.length > 0 && (fArr = this.r) != null && fArr.length > 0;
    }

    public Pair<String[], float[]> getHistoryData() {
        return new Pair<>(this.n, this.j);
    }

    public Pair<String[], float[]> getData() {
        return new Pair<>(this.x, this.r);
    }

    private void a() {
        if (c()) {
            bjg_(this.j, this.f, this.o);
        } else {
            if (d()) {
                this.o.setStrokeWidth(f3793a);
                bjg_(this.j, null, this.o);
                bjg_(this.r, this.p, this.t);
                return;
            }
            LogUtil.h("PerformanceRadarView", "Do not draw region.");
        }
    }

    private void bjg_(float[] fArr, Paint paint, Paint paint2) {
        Path path = new Path();
        float f = this.s / 4.0f;
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                path.moveTo(this.g, (this.i - f) - ((this.s - f) * fArr[i]));
            } else {
                float f2 = i;
                path.lineTo((float) (this.g + (Math.sin(this.d * f2) * ((fArr[i] * (this.s - f)) + f))), (float) (this.i - (Math.cos(this.d * f2) * ((fArr[i] * (this.s - f)) + f))));
            }
        }
        path.close();
        if (paint != null) {
            this.b.drawPath(path, paint);
        }
        if (paint2 != null) {
            this.b.drawPath(path, paint2);
        }
    }

    private Rect bjk_(String str, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect;
    }
}
