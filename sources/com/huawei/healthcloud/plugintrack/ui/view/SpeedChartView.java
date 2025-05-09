package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.huawei.hms.framework.common.ExceptionCode;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gwh;
import defpackage.koq;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class SpeedChartView extends View {

    /* renamed from: a, reason: collision with root package name */
    private static final int f3795a = Color.parseColor("#FB6522");
    private float b;
    private float c;
    private Paint d;
    private Paint e;
    private Context f;
    private Paint g;
    private float h;
    private boolean i;
    private float j;
    private int k;
    private float l;
    private Paint m;
    private Paint n;
    private float o;
    private float p;
    private List<Map.Entry<Integer, Float>> q;
    private float r;
    private Paint s;
    private float t;
    private float u;
    private float w;
    private float x;
    private float y;

    private float a(float f) {
        return 3600.0f / f;
    }

    public SpeedChartView(Context context) {
        super(context);
        this.f = context;
        a();
    }

    public SpeedChartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = context;
        a();
    }

    private void a() {
        if (this.f == null) {
            return;
        }
        this.c = d(0, 1.0f);
        this.r = d(1, 2.0f);
        this.w = d(1, 2.0f);
        this.t = d(1, 16.0f);
        this.p = d(1, 11.0f);
        this.i = LanguageUtil.bc(this.f);
        Paint paint = new Paint();
        this.g = paint;
        paint.setAntiAlias(true);
        this.g.setStyle(Paint.Style.STROKE);
        this.g.setColor(gwh.c);
        this.g.setStrokeWidth(this.c);
        this.g.setPathEffect(new DashPathEffect(new float[]{4.0f, 2.0f, 4.0f, 2.0f}, 1.0f));
        Paint paint2 = new Paint();
        this.m = paint2;
        paint2.setAntiAlias(true);
        this.m.setColor(gwh.d);
        this.m.setStrokeWidth(this.c);
        Paint paint3 = new Paint();
        this.d = paint3;
        paint3.setAntiAlias(true);
        Paint paint4 = this.d;
        int i = f3795a;
        paint4.setColor(i);
        this.d.setStrokeWidth(d(1, 3.0f));
        this.d.setStyle(Paint.Style.STROKE);
        Paint paint5 = new Paint();
        this.n = paint5;
        paint5.setAntiAlias(true);
        this.n.setColor(gwh.c);
        this.n.setTextSize(this.p);
        Paint paint6 = new Paint();
        this.e = paint6;
        paint6.setAntiAlias(true);
        this.e.setColor(i);
        this.e.setStyle(Paint.Style.FILL);
        Paint paint7 = new Paint();
        this.s = paint7;
        paint7.setAntiAlias(true);
        this.s.setColor(gwh.h);
        this.s.setTextSize(this.p);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (canvas == null) {
            LogUtil.b("Track_SpeedChartView", "canvas is null");
            return;
        }
        bjr_(canvas);
        bjp_(canvas);
        bjo_(canvas);
        bjq_(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.u = getDefaultSize(getSuggestedMinimumWidth(), i);
        float defaultSize = getDefaultSize(getSuggestedMinimumHeight(), i2);
        this.x = defaultSize;
        this.h = defaultSize - d(1, 13.0f);
        this.j = this.x - d(1, 27.0f);
    }

    private void bjo_(Canvas canvas) {
        int i;
        float f;
        if (koq.b(this.q) || (i = this.k) < 1) {
            return;
        }
        if (i >= 4) {
            this.y = (this.u - (this.r * 2.0f)) / (i - 1);
        } else {
            this.y = (this.u - (this.r * 2.0f)) / 3.0f;
        }
        ArrayList<PointF> arrayList = new ArrayList<>(10);
        for (Map.Entry<Integer, Float> entry : this.q) {
            int intValue = entry.getKey().intValue() / ExceptionCode.CRASH_EXCEPTION;
            float floatValue = entry.getValue().floatValue();
            if (floatValue != 0.0f) {
                float a2 = a(floatValue);
                if (this.i) {
                    f = (this.u - this.r) - ((intValue - 1) * this.y);
                } else {
                    f = this.r + ((intValue - 1) * this.y);
                }
                arrayList.add(new PointF(f, d(a2)));
            }
        }
        bjn_(canvas, arrayList);
    }

    private void bjn_(Canvas canvas, ArrayList<PointF> arrayList) {
        int i = 0;
        if (arrayList.size() == 1) {
            canvas.drawCircle(arrayList.get(0).x, arrayList.get(0).y, this.r, this.e);
            return;
        }
        Path path = new Path();
        while (i < arrayList.size() - 1) {
            PointF pointF = arrayList.get(i);
            if (i == 0) {
                path.moveTo(pointF.x, pointF.y);
            } else {
                path.lineTo(pointF.x, pointF.y);
            }
            if (i == 0) {
                path.moveTo(pointF.x, pointF.y);
            }
            i++;
            PointF pointF2 = arrayList.get(i);
            float f = (pointF.x + pointF2.x) / 2.0f;
            path.cubicTo(f, pointF.y, f, pointF2.y, pointF2.x, pointF2.y);
        }
        canvas.drawPath(path, this.d);
    }

    private void bjp_(Canvas canvas) {
        float f;
        float f2 = (this.j + this.t) / 2.0f;
        Path path = new Path();
        path.moveTo(0.0f, f2);
        path.lineTo(this.u, f2);
        canvas.drawPath(path, this.g);
        float f3 = this.b;
        if (f3 != 0.0f) {
            String e = UnitUtil.e(f3, 1, 2);
            float measureText = this.n.measureText(e, 0, e.length());
            if (this.i) {
                f = this.r;
            } else {
                f = (this.u - this.r) - measureText;
            }
            canvas.drawText(e, f, f2 + this.p + (this.c * 2.0f), this.n);
        }
    }

    private float d(float f) {
        if (Math.abs(this.o - this.l) < 1.0E-6d) {
            return (this.j + this.t) / 2.0f;
        }
        float f2 = this.t;
        float f3 = this.o;
        return f2 + (((f3 - f) / (f3 - this.l)) * (this.j - f2));
    }

    private void bjq_(Canvas canvas) {
        float intValue;
        if (koq.b(this.q) || this.k < 1) {
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        int i = this.k;
        if (i >= 8) {
            int i2 = i / 4;
            if (i2 != 1) {
                arrayList.add(1);
            }
            for (int i3 = 1; i3 < 4; i3++) {
                arrayList.add(Integer.valueOf(i2 * i3));
            }
            arrayList.add(Integer.valueOf(this.k));
        } else if (i >= 4) {
            for (int i4 = 1; i4 <= this.k; i4++) {
                arrayList.add(Integer.valueOf(i4));
            }
        } else {
            for (int i5 = 1; i5 <= 4; i5++) {
                arrayList.add(Integer.valueOf(i5));
            }
        }
        float f = this.h;
        float d = d(1, 2.0f);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            String e = UnitUtil.e(num.intValue(), 1, 0);
            float measureText = this.n.measureText(e, 0, e.length());
            if (this.i) {
                intValue = (this.u - this.r) - ((num.intValue() - 1) * this.y);
            } else {
                intValue = this.r + ((num.intValue() - 1) * this.y);
            }
            float f2 = intValue - (measureText / 2.0f);
            if (f2 < 0.0f) {
                f2 = 0.0f;
            } else {
                float f3 = this.u;
                if (f2 + measureText > f3) {
                    f2 = f3 - measureText;
                } else {
                    LogUtil.a("Track_SpeedChartView", "textX is default");
                }
            }
            canvas.drawText(e, f2, this.p + f + d, this.n);
        }
    }

    private void bjr_(Canvas canvas) {
        float f = this.w;
        canvas.drawLine(0.0f, f, this.u, f, this.m);
        float f2 = this.h;
        canvas.drawLine(0.0f, f2, this.u, f2, this.m);
    }

    public void e() {
        this.n.setColor(gwh.b);
        this.m.setColor(gwh.e);
        this.g.setColor(gwh.b);
        this.s.setColor(gwh.r);
    }

    private static float d(int i, float f) {
        return TypedValue.applyDimension(i, f, Resources.getSystem().getDisplayMetrics());
    }
}
