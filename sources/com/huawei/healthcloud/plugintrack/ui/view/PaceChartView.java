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
import defpackage.gvv;
import defpackage.gwh;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class PaceChartView extends View {
    private float aa;
    private float ad;
    private Paint b;
    private Paint c;
    private float e;
    private boolean f;
    private Paint g;
    private float h;
    private Paint i;
    private Context j;
    private float k;
    private float l;
    private float m;
    private Paint n;
    private int o;
    private float p;
    private float q;
    private float r;
    private List<Map.Entry<Integer, Float>> s;
    private float t;
    private float u;
    private float v;
    private float w;
    private Paint x;
    private float y;
    private static final int d = Color.parseColor("#7FFB6522");

    /* renamed from: a, reason: collision with root package name */
    private static final int f3784a = Color.parseColor("#FB6522");

    public PaceChartView(Context context) {
        super(context);
        this.s = new ArrayList(10);
        this.j = context;
        a();
    }

    public PaceChartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.s = new ArrayList(10);
        this.j = context;
        a();
    }

    private void a() {
        this.r = b(1, 2.5f);
        this.v = b(1, 2.0f);
        this.y = b(1, 16.0f);
        this.w = b(1, 11.0f);
        Paint paint = new Paint();
        this.n = paint;
        paint.setAntiAlias(true);
        this.n.setColor(gwh.d);
        this.n.setStrokeWidth(b(0, 1.0f));
        Paint paint2 = new Paint();
        this.g = paint2;
        paint2.setAntiAlias(true);
        this.g.setColor(gwh.c);
        this.g.setStrokeWidth(b(0, 1.0f));
        this.g.setPathEffect(new DashPathEffect(new float[]{4.0f, 2.0f, 4.0f, 2.0f}, 1.0f));
        Paint paint3 = new Paint();
        this.i = paint3;
        paint3.setAntiAlias(true);
        this.i.setColor(gwh.c);
        this.i.setTextSize(this.w);
        Paint paint4 = new Paint();
        this.c = paint4;
        paint4.setAntiAlias(true);
        this.c.setColor(d);
        this.c.setStrokeWidth(b(1, 2.0f));
        this.c.setStyle(Paint.Style.STROKE);
        Paint paint5 = new Paint();
        this.b = paint5;
        paint5.setAntiAlias(true);
        this.b.setColor(f3784a);
        this.b.setStyle(Paint.Style.FILL);
        Paint paint6 = new Paint();
        this.x = paint6;
        paint6.setAntiAlias(true);
        this.x.setColor(gwh.h);
        this.x.setTextSize(this.w);
        this.f = LanguageUtil.bc(this.j);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.ad = getDefaultSize(getSuggestedMinimumWidth(), i);
        float defaultSize = getDefaultSize(getSuggestedMinimumHeight(), i2);
        this.u = defaultSize;
        this.h = defaultSize - b(1, 13.0f);
        this.e = this.u - b(1, 27.0f);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (canvas == null) {
            LogUtil.h("Track_PaceChartView", "canvas is null");
            return;
        }
        bit_(canvas);
        bir_(canvas);
        biq_(canvas);
        bip_(canvas);
        bis_(canvas);
    }

    private void bip_(Canvas canvas) {
        List<Map.Entry<Integer, Float>> list = this.s;
        if (list == null || list.isEmpty()) {
            return;
        }
        String a2 = gvv.a(this.p);
        float measureText = this.x.measureText(a2, 0, a2.length());
        float f = this.q;
        float f2 = measureText / 2.0f;
        float f3 = f - f2;
        float f4 = 0.0f;
        if (f3 < 0.0f) {
            f3 = 0.0f;
        } else {
            float f5 = this.ad;
            if (f + f2 > f5) {
                f3 = f5 - measureText;
            }
        }
        canvas.drawText(a2, f3, this.t + this.r + this.w, this.x);
        String a3 = gvv.a(this.l);
        float measureText2 = this.x.measureText(a3, 0, a3.length());
        float f6 = this.k;
        float f7 = measureText2 / 2.0f;
        float f8 = f6 - f7;
        if (f8 >= 0.0f) {
            float f9 = this.ad;
            f4 = f6 + f7 > f9 ? f9 - measureText2 : f8;
        }
        canvas.drawText(a3, f4, (this.m - this.r) - b(1, 1.0f), this.x);
    }

    private void biq_(Canvas canvas) {
        int i;
        float f;
        List<Map.Entry<Integer, Float>> list = this.s;
        if (list == null || list.isEmpty() || (i = this.o) == 1) {
            return;
        }
        this.aa = (this.ad - (this.r * 2.0f)) / (i - 1);
        ArrayList arrayList = new ArrayList(10);
        float f2 = 0.0f;
        boolean z = false;
        for (Map.Entry<Integer, Float> entry : this.s) {
            int intValue = entry.getKey().intValue() / ExceptionCode.CRASH_EXCEPTION;
            float floatValue = entry.getValue().floatValue();
            if (this.f) {
                f = (this.ad - this.r) - ((intValue - 1) * this.aa);
            } else {
                f = this.r + ((intValue - 1) * this.aa);
            }
            float b = b(floatValue);
            arrayList.add(new PointF(f, b));
            if (floatValue == this.p && !z) {
                this.q = f;
                this.t = b;
                z = true;
            }
            if (floatValue > f2) {
                this.k = f;
                this.m = b;
                f2 = floatValue;
            }
        }
        Path path = new Path();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            PointF pointF = (PointF) arrayList.get(i2);
            if (i2 == 0) {
                path.moveTo(pointF.x, pointF.y);
            } else {
                path.lineTo(pointF.x, pointF.y);
            }
        }
        canvas.drawPath(path, this.c);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            PointF pointF2 = (PointF) it.next();
            canvas.drawCircle(pointF2.x, pointF2.y, this.r, this.b);
        }
    }

    private float b(float f) {
        if (Math.abs(this.l - this.p) < 1.0E-7d) {
            return (this.e + this.y) / 2.0f;
        }
        float f2 = this.y;
        float f3 = this.r;
        float f4 = this.l;
        return f2 + f3 + (((f4 - f) / (f4 - this.p)) * ((this.e - f2) - (f3 * 2.0f)));
    }

    private void bir_(Canvas canvas) {
        float f = (this.e + this.y) / 2.0f;
        canvas.drawLine(0.0f, f, this.ad, f, this.g);
    }

    private void bis_(Canvas canvas) {
        float intValue;
        List<Map.Entry<Integer, Float>> list = this.s;
        if (list == null || list.isEmpty() || this.o < 4) {
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        int i = this.o / 4;
        arrayList.add(1);
        for (int i2 = 1; i2 < 4; i2++) {
            arrayList.add(Integer.valueOf(i * i2));
        }
        arrayList.add(Integer.valueOf(this.o));
        float f = this.h;
        float b = b(1, 2.0f);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            String e = UnitUtil.e(num.intValue(), 1, 0);
            float measureText = this.i.measureText(e, 0, e.length());
            if (this.f) {
                intValue = (this.ad - this.r) - ((num.intValue() - 1) * this.aa);
            } else {
                intValue = this.r + ((num.intValue() - 1) * this.aa);
            }
            float f2 = intValue - (measureText / 2.0f);
            if (f2 < 0.0f) {
                f2 = 0.0f;
            } else {
                float f3 = this.ad;
                if (f2 + measureText > f3) {
                    f2 = f3 - measureText;
                } else {
                    LogUtil.a("Track_PaceChartView", "x is default");
                }
            }
            canvas.drawText(e, f2, this.w + f + b, this.i);
        }
    }

    private void bit_(Canvas canvas) {
        float f = this.v;
        canvas.drawLine(0.0f, f, this.ad, f, this.n);
        float f2 = this.h;
        canvas.drawLine(0.0f, f2, this.ad, f2, this.n);
    }

    private static float b(int i, float f) {
        return TypedValue.applyDimension(i, f, Resources.getSystem().getDisplayMetrics());
    }

    public void d() {
        this.i.setColor(gwh.b);
        this.n.setColor(gwh.e);
        this.g.setColor(gwh.b);
        this.x.setColor(gwh.r);
    }
}
