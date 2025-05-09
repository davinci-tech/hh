package com.huawei.ui.commonui.view;

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
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$string;
import defpackage.koq;
import defpackage.nsn;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class RadarView extends View {

    /* renamed from: a, reason: collision with root package name */
    private int f8975a;
    private List<Integer> b;
    private float c;
    private int d;
    private int e;
    private boolean f;
    private int g;
    private boolean h;
    private Paint i;
    private int j;
    private Paint k;
    private int l;
    private float m;
    private List<c> n;
    private double[] o;
    private TextPaint p;
    private int[] q;
    private Paint r;
    private Paint s;
    private float t;
    private String[] u;
    private TextPaint v;
    private int w;
    private float y;

    public RadarView(Context context) {
        this(context, null, 0);
    }

    public RadarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RadarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = 5;
        this.f = false;
        this.j = 4;
        this.o = new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d};
        this.u = new String[]{getContext().getString(R$string.IDS_aw_version2_jump), getContext().getString(R$string.IDS_aw_version2_burst), getContext().getString(R$string.IDS_aw_version2_breakthrough), getContext().getString(R$string.IDS_aw_version2_run), getContext().getString(R$string.IDS_aw_version2_sport_intensity)};
        this.q = new int[]{0, 0, 0, 0, 0};
        this.b = new ArrayList(5);
        this.w = getResources().getColor(R$color.textColorPrimary);
        this.y = 0.0f;
        this.l = getResources().getColor(R$color.common_colorAccent);
        this.m = 0.0f;
        this.h = false;
        this.n = new ArrayList(5);
        int i2 = this.g;
        if (i2 != 0) {
            this.c = (float) (6.283185307179586d / i2);
        }
        Paint paint = new Paint();
        this.k = paint;
        paint.setColor(getResources().getColor(R$color.common_color_white));
        this.k.setAntiAlias(true);
        this.k.setStyle(Paint.Style.STROKE);
        this.k.setStrokeWidth(4.0f);
        Paint paint2 = new Paint();
        this.i = paint2;
        paint2.setColor(-2631721);
        this.i.setAntiAlias(true);
        this.i.setStyle(Paint.Style.STROKE);
        this.i.setStrokeWidth(nsn.c(context, 0.5f));
        this.i.setAlpha(getResources().getInteger(R.integer._2131623941_res_0x7f0e0005));
        TextPaint textPaint = new TextPaint();
        this.v = textPaint;
        textPaint.setColor(this.w);
        this.v.setAntiAlias(true);
        this.v.setStyle(Paint.Style.FILL);
        this.v.setTextSize(nsn.c(context, 10.0f));
        TextPaint textPaint2 = new TextPaint();
        this.p = textPaint2;
        textPaint2.setColor(this.l);
        this.p.setAntiAlias(true);
        this.p.setStyle(Paint.Style.FILL);
        this.p.setTextSize(nsn.c(context, 18.0f));
        this.p.setTypeface(Typeface.DEFAULT_BOLD);
        this.s = new Paint();
        this.s.setShader(new LinearGradient(0.0f, 0.0f, 300.0f, 300.0f, 0, getResources().getColor(R$color.common_colorAccent), Shader.TileMode.MIRROR));
        this.s.setAlpha(50);
        this.s.setStyle(Paint.Style.FILL_AND_STROKE);
        this.s.setAntiAlias(true);
        Paint paint3 = new Paint();
        this.r = paint3;
        paint3.setColor(getResources().getColor(R$color.common_colorAccent));
        this.r.setStyle(Paint.Style.STROKE);
        this.r.setStrokeWidth(nsn.c(getContext(), 3.0f));
    }

    public void setCount(int i) {
        this.g = i;
        this.c = (float) (6.283185307179586d / i);
        if (i % 2 == 0) {
            this.f = true;
        }
        invalidate();
    }

    public void setPercentOfMap(double[] dArr, String[] strArr) {
        if (dArr == null || strArr == null || dArr.length != strArr.length) {
            LogUtil.b("Track_RadarView", "input not allowed");
            return;
        }
        int length = dArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = (int) (dArr[i] * 100.0d);
        }
        this.u = (String[]) strArr.clone();
        this.o = (double[]) dArr.clone();
        this.q = (int[]) iArr.clone();
    }

    public void setAverageScore(int i) {
        this.d = i;
    }

    public void setColorList(List<Integer> list) {
        this.b = list;
    }

    public void setTextColorAndSize(int i, float f) {
        this.w = i;
        this.y = f;
    }

    public void setNumberColorAndSize(int i, float f) {
        this.l = i;
        this.m = f;
    }

    public void setIsActionRadar(boolean z) {
        this.h = z;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.t = (Math.min(i2, i) / 2.0f) * 0.7f;
        this.e = i / 2;
        this.f8975a = i2 / 2;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cMQ_(canvas);
        cMP_(canvas);
        cMS_(canvas);
        cMR_(canvas);
        setCircle(canvas);
        cMO_(canvas);
    }

    private void cMO_(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R$color.basketball_center_score_color));
        paint.setTextSize(nsn.c(getContext(), 30.0f));
        Rect rect = new Rect();
        String valueOf = String.valueOf(UnitUtil.e(this.d, 1, 0));
        paint.getTextBounds(valueOf, 0, valueOf.length(), rect);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText(valueOf, this.e - (paint.measureText(valueOf) * 0.5f), this.f8975a + ((rect.bottom - rect.top) * 0.5f), paint);
    }

    private void cMQ_(Canvas canvas) {
        float f;
        int i;
        Path path = new Path();
        float f2 = this.t;
        int i2 = this.j;
        float f3 = f2 / i2;
        while (i2 > 0) {
            float f4 = i2 * f3;
            int i3 = 0;
            if (this.f) {
                while (i3 < this.g) {
                    double d = this.e;
                    float f5 = this.c;
                    float f6 = i3;
                    double sin = Math.sin((f5 * f6) + (f5 / 2.0f));
                    float f7 = f3;
                    int i4 = i2;
                    double d2 = f4;
                    float f8 = (float) (d + (sin * d2));
                    double d3 = this.f8975a;
                    float f9 = this.c;
                    float cos = (float) (d3 - (Math.cos((f6 * f9) + (f9 / 2.0f)) * d2));
                    if (i3 == 0) {
                        path.moveTo(f8, cos);
                    } else {
                        path.lineTo(f8, cos);
                    }
                    i3++;
                    f3 = f7;
                    i2 = i4;
                }
                f = f3;
                i = i2;
            } else {
                f = f3;
                i = i2;
                while (i3 < this.g) {
                    if (i3 == 0) {
                        path.moveTo(this.e, this.f8975a - f4);
                    } else {
                        float f10 = i3;
                        double d4 = f4;
                        path.lineTo((float) (this.e + (Math.sin(this.c * f10) * d4)), (float) (this.f8975a - (Math.cos(this.c * f10) * d4)));
                    }
                    i3++;
                }
            }
            Paint paint = new Paint();
            Paint paint2 = new Paint();
            int i5 = i - 1;
            if (koq.d(this.b, i5)) {
                paint.setColor(this.b.get(i5).intValue());
            }
            paint.setStyle(Paint.Style.FILL);
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setAntiAlias(true);
            paint2.setColor(getResources().getColor(R$color.basketball_radar_line_color));
            paint2.setStrokeWidth(nsn.c(getContext(), 0.5f));
            paint2.setAlpha(getResources().getInteger(R.integer._2131623941_res_0x7f0e0005));
            paint.setAntiAlias(true);
            paint.setAlpha(getResources().getInteger(R.integer._2131623942_res_0x7f0e0006));
            path.close();
            canvas.drawPath(path, paint);
            canvas.drawPath(path, paint2);
            path.reset();
            f3 = f;
            i2 = i5;
        }
    }

    private void cMP_(Canvas canvas) {
        int i = 0;
        if (this.f) {
            while (i < this.g) {
                int i2 = this.e;
                float f = i2;
                float f2 = this.f8975a;
                double d = i2;
                float f3 = this.c;
                float f4 = i;
                float sin = (float) (d + (Math.sin((f3 * f4) + (f3 / 2.0f)) * this.t));
                double d2 = this.f8975a;
                float f5 = this.c;
                canvas.drawLine(f, f2, sin, (float) (d2 - (Math.cos((f4 * f5) + (f5 / 2.0f)) * this.t)), this.i);
                i++;
            }
            return;
        }
        while (i < this.g) {
            int i3 = this.e;
            float f6 = i;
            canvas.drawLine(i3, this.f8975a, (float) (i3 + (Math.sin(this.c * f6) * this.t)), (float) (this.f8975a - (Math.cos(this.c * f6) * this.t)), this.i);
            i++;
        }
    }

    private void cMS_(Canvas canvas) {
        this.v.setColor(this.w);
        float f = this.y;
        if (f != 0.0f) {
            this.v.setTextSize(f);
        }
        this.p.setColor(this.l);
        float f2 = this.m;
        if (f2 != 0.0f) {
            this.p.setTextSize(f2);
        }
        for (int i = 0; i < this.g; i++) {
            String str = this.u[i];
            Rect rect = new Rect();
            this.v.getTextBounds(str, 0, str.length(), rect);
            float f3 = rect.bottom - rect.top;
            float c2 = nsn.c(getContext(), 40.0f);
            String valueOf = String.valueOf(this.q[i]);
            Rect rect2 = new Rect();
            this.p.getTextBounds(valueOf, 0, valueOf.length(), rect2);
            float f4 = rect2.bottom - rect2.top;
            if (this.f) {
                cMU_(canvas, i, f3, f4);
            } else {
                cMT_(canvas, i, f3, c2, f4);
            }
        }
    }

    private void cMU_(Canvas canvas, int i, float f, float f2) {
        if (this.g < 4) {
            return;
        }
        String str = this.u[i];
        Rect rect = new Rect();
        this.v.getTextBounds(str, 0, str.length(), rect);
        float f3 = rect.right - rect.left;
        float measureText = this.p.measureText(str);
        float f4 = (f3 - f2) / 2.0f;
        double d = this.e;
        float f5 = this.c;
        float f6 = i;
        float sin = (float) (d + (Math.sin((f5 * f6) + (f5 / 2.0f)) * (this.t + 12.0f)));
        double d2 = this.f8975a;
        float f7 = this.c;
        float cos = (float) (d2 - (Math.cos((f6 * f7) + (f7 / 2.0f)) * (this.t + 12.0f)));
        if (i != 0) {
            int i2 = this.g;
            if (i != i2 - 1) {
                int i3 = i2 / 2;
                int i4 = i3 - 1;
                if (i < i4) {
                    cMX_(sin, cos, f3, measureText, canvas, i, f4);
                    return;
                }
                if (i != i4 && i != i3) {
                    if (i > i3) {
                        cMW_(sin, cos, f3, measureText, canvas, i, f4);
                        return;
                    } else {
                        LogUtil.b("Track_RadarView", "wrong mCount index");
                        return;
                    }
                }
                this.v.setTextAlign(Paint.Align.CENTER);
                this.p.setTextAlign(Paint.Align.CENTER);
                float f8 = cos + f;
                canvas.drawText(this.u[i], sin, nsn.c(getContext(), 6.0f) + f8 + f2, this.v);
                canvas.drawText(UnitUtil.e(this.q[i], 1, 0), sin, f8 + nsn.c(getContext(), 4.0f), this.p);
                return;
            }
        }
        this.v.setTextAlign(Paint.Align.CENTER);
        this.p.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(this.u[i], sin, cos - nsn.c(getContext(), 4.0f), this.v);
        canvas.drawText(UnitUtil.e(this.q[i], 1, 0), sin, (cos - f) - nsn.c(getContext(), 6.0f), this.p);
    }

    private void cMT_(Canvas canvas, int i, float f, float f2, float f3) {
        String str = this.u[i];
        float sin = (float) (this.e + (Math.sin(this.c * r3) * (this.t + 12.0f)));
        float cos = (float) (this.f8975a - (Math.cos(this.c * r3) * (this.t + 12.0f)));
        float f4 = this.c * i;
        if (f4 == 0.0f) {
            this.v.setTextAlign(Paint.Align.CENTER);
            this.p.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(this.u[i], sin, cos - nsn.c(getContext(), 0.5f), this.v);
            canvas.drawText(UnitUtil.e(this.q[i], 1, 0), sin, (cos - f) - nsn.c(getContext(), 1.0f), this.p);
            this.v.setTextAlign(Paint.Align.LEFT);
            this.p.setTextAlign(Paint.Align.LEFT);
            return;
        }
        if (f4 > 0.0f && f4 < 1.5707963267948966d) {
            this.v.setTextAlign(Paint.Align.CENTER);
            this.p.setTextAlign(Paint.Align.CENTER);
            cMV_(canvas, sin + nsn.c(getContext(), 3.0f), cos, str, true);
            canvas.drawText(UnitUtil.e(this.q[i], 1, 0), sin + nsn.c(getContext(), 3.0f) + (0.5f * f2), cos, this.p);
            this.v.setTextAlign(Paint.Align.LEFT);
            this.p.setTextAlign(Paint.Align.LEFT);
            return;
        }
        double d = f4;
        if (d >= 1.5707963267948966d && d < 3.141592653589793d) {
            this.v.setTextAlign(Paint.Align.CENTER);
            this.p.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(str, sin, cos + f + nsn.c(getContext(), 7.0f) + f3, this.v);
            canvas.drawText(UnitUtil.e(this.q[i], 1, 0), sin, cos + nsn.c(getContext(), 7.0f) + f, this.p);
            this.p.setTextAlign(Paint.Align.LEFT);
            return;
        }
        if (d >= 3.141592653589793d && d < 4.71238898038469d) {
            this.v.setTextAlign(Paint.Align.CENTER);
            this.p.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(str, sin, cos + f + nsn.c(getContext(), 7.0f) + f3, this.v);
            canvas.drawText(UnitUtil.e(this.q[i], 1, 0), sin, cos + nsn.c(getContext(), 7.0f) + f, this.p);
            return;
        }
        if (d >= 4.71238898038469d && d < 6.283185307179586d) {
            this.v.setTextAlign(Paint.Align.CENTER);
            this.p.setTextAlign(Paint.Align.CENTER);
            cMV_(canvas, sin - nsn.c(getContext(), 3.0f), cos, str, false);
            canvas.drawText(UnitUtil.e(this.q[i], 1, 0), (sin - nsn.c(getContext(), 3.0f)) - (0.5f * f2), cos, this.p);
            return;
        }
        LogUtil.b("Track_RadarView", "wrong angle branch");
    }

    private String d(String str) {
        return TextUtils.ellipsize(str, this.v, nsn.c(getContext(), 76.0f), TextUtils.TruncateAt.END).toString();
    }

    private void cMV_(Canvas canvas, float f, float f2, String str, boolean z) {
        canvas.save();
        TextPaint textPaint = new TextPaint();
        if (z) {
            textPaint.setTextAlign(Paint.Align.LEFT);
        } else {
            textPaint.setTextAlign(Paint.Align.RIGHT);
        }
        textPaint.setColor(getResources().getColor(R$color.textColorPrimary));
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(nsn.c(getContext(), 10.0f));
        textPaint.setStyle(Paint.Style.FILL);
        StaticLayout staticLayout = new StaticLayout(d(str), textPaint, nsn.c(getContext(), 76.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
        canvas.translate(f, f2);
        staticLayout.draw(canvas);
        canvas.restore();
    }

    private void cMR_(Canvas canvas) {
        float f;
        float f2;
        float f3;
        float f4;
        float f5 = 2.0f;
        if (this.h) {
            this.s.setShader(new LinearGradient(0.0f, 0.0f, 300.0f, 300.0f, getResources().getColor(R$color.action_radar_cover_color), getResources().getColor(R$color.action_radar_cover_color_deep), Shader.TileMode.MIRROR));
            this.s.setAlpha(255);
            this.r.setStrokeWidth(nsn.c(getContext(), 2.0f));
        }
        this.n.clear();
        Path path = new Path();
        float f6 = this.t / this.j;
        int i = 0;
        while (i < this.g) {
            if (i == 0) {
                int i2 = this.e;
                float f7 = i2;
                float f8 = (float) ((this.f8975a - f6) - ((this.t - f6) * this.o[i]));
                if (this.f) {
                    double d = f6;
                    f7 = (float) (i2 + (Math.sin(this.c / f5) * ((this.o[i] * (this.t - f6)) + d)));
                    f4 = f6;
                    f8 = (float) (this.f8975a - (Math.cos(this.c / f5) * ((this.o[i] * (this.t - f6)) + d)));
                } else {
                    f4 = f6;
                }
                path.moveTo(f7, f8);
                this.n.add(new c(f7, f8));
                f = f4;
            } else {
                float f9 = i;
                f = f6;
                double d2 = f;
                float sin = (float) (this.e + (Math.sin(this.c * f9) * ((this.o[i] * (this.t - r18)) + d2)));
                float cos = (float) (this.f8975a - (Math.cos(this.c * f9) * ((this.o[i] * (this.t - f)) + d2)));
                if (this.f) {
                    double d3 = this.e;
                    float f10 = this.c;
                    f3 = (float) (d3 + (Math.sin((f10 * f9) + (f10 / 2.0f)) * ((this.o[i] * (this.t - f)) + d2)));
                    double d4 = this.f8975a;
                    float f11 = this.c;
                    f2 = (float) (d4 - (Math.cos((f9 * f11) + (f11 / 2.0f)) * ((this.o[i] * (this.t - f)) + d2)));
                    path = path;
                } else {
                    f2 = cos;
                    f3 = sin;
                }
                path.lineTo(f3, f2);
                this.n.add(new c(f3, f2));
            }
            i++;
            f6 = f;
            f5 = 2.0f;
        }
        path.close();
        canvas.drawPath(path, this.s);
        canvas.drawPath(path, this.r);
    }

    private void setCircle(Canvas canvas) {
        if (this.h) {
            return;
        }
        for (int i = 0; i < this.n.size(); i++) {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(getResources().getColor(R$color.common_colorAccent));
            paint.setAlpha(255);
            canvas.drawCircle(this.n.get(i).e, this.n.get(i).b, nsn.c(getContext(), 4.5f), paint);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(getResources().getColor(R$color.basketball_radar_circle_color));
            canvas.drawCircle(this.n.get(i).e, this.n.get(i).b, nsn.c(getContext(), 2.5f), paint);
        }
    }

    private void cMX_(float f, float f2, float f3, float f4, Canvas canvas, int i, float f5) {
        this.v.setTextAlign(Paint.Align.CENTER);
        this.p.setTextAlign(Paint.Align.CENTER);
        float c2 = f + (0.5f * f3) + nsn.c(getContext(), 2.0f);
        canvas.drawText(this.u[i], c2, nsn.c(getContext(), 14.0f) + f2, this.v);
        if (f3 > f4) {
            canvas.drawText(UnitUtil.e(this.q[i], 1, 0), c2 + f5, f2, this.p);
        } else {
            canvas.drawText(UnitUtil.e(this.q[i], 1, 0), c2, f2, this.p);
        }
    }

    private void cMW_(float f, float f2, float f3, float f4, Canvas canvas, int i, float f5) {
        this.v.setTextAlign(Paint.Align.CENTER);
        this.p.setTextAlign(Paint.Align.CENTER);
        float c2 = (f - (0.5f * f3)) - nsn.c(getContext(), 2.0f);
        canvas.drawText(this.u[i], c2, nsn.c(getContext(), 14.0f) + f2, this.v);
        if (f3 > f4) {
            canvas.drawText(UnitUtil.e(this.q[i], 1, 0), c2 - f5, f2, this.p);
        } else {
            canvas.drawText(UnitUtil.e(this.q[i], 1, 0), c2, f2, this.p);
        }
    }

    static class c {
        private float b;
        private float e;

        private c(float f, float f2) {
            this.e = f;
            this.b = f2;
        }
    }
}
