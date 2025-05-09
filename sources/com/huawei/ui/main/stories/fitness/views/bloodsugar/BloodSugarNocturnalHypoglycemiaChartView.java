package com.huawei.ui.main.stories.fitness.views.bloodsugar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.health.R;
import defpackage.ggl;
import defpackage.nrr;
import defpackage.qad;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.util.LogUtil;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class BloodSugarNocturnalHypoglycemiaChartView extends View {

    /* renamed from: a, reason: collision with root package name */
    protected Context f9964a;
    private Paint aa;
    protected float ab;
    private int ac;
    private int ad;
    private Paint ae;
    private int af;
    private Paint ag;
    private long ah;
    private int ai;
    private int aj;
    private List<qad> ak;
    private Paint al;
    private LoadingListener am;
    private boolean an;
    private int ao;
    private int ap;
    private float aq;
    private List<qad> ar;
    private Paint as;
    private int at;
    private int au;
    private int ax;
    protected float b;
    protected float c;
    protected Canvas d;
    protected float e;
    protected long f;
    protected float g;
    protected int h;
    protected float i;
    protected Paint j;
    protected float k;
    protected float l;
    protected float m;
    protected float n;
    protected float o;
    protected float p;
    protected long q;
    protected float r;
    protected float s;
    protected Paint t;
    protected float u;
    protected List<qad> v;
    protected float w;
    protected Paint x;
    protected float y;
    private Map<Long, Float> z;

    public interface LoadingListener {
        void onCompleted();
    }

    public BloodSugarNocturnalHypoglycemiaChartView(Context context) {
        this(context, null);
    }

    public BloodSugarNocturnalHypoglycemiaChartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = 0.0f;
        this.b = 0.0f;
        this.c = 0.0f;
        this.h = -249511;
        this.i = 0.0f;
        this.r = 8.0f;
        this.y = 15.0f;
        this.k = 52.0f;
        this.o = 16.0f;
        this.w = 10.0f;
        this.ab = 0.0f;
        this.u = 10.0f;
        this.l = 0.0f;
        this.p = 0.0f;
        this.v = new ArrayList();
        this.s = 0.0f;
        this.g = 0.0f;
        this.m = 0.0f;
        this.n = 0.0f;
        this.q = 0L;
        this.f = 0L;
        this.ac = -2566442;
        this.ao = -12728884;
        this.au = -12728884;
        this.ap = 1711276032;
        this.ax = 1711276032;
        this.ad = -504465;
        this.at = 16527705;
        this.aq = 0.0f;
        this.af = 5;
        this.ah = -1L;
        this.aj = 0;
        this.ai = 0;
        this.an = true;
        this.ak = new ArrayList();
        this.ar = new ArrayList();
        this.z = new LinkedHashMap(16);
        this.f9964a = context;
        h();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        b();
        this.d = canvas;
        canvas.save();
        if (LanguageUtil.bc(getContext())) {
            this.d.scale(-1.0f, 1.0f, getWidth() / 2, getHeight() / 2);
        }
        c();
        d();
        a();
        d();
        this.d.restore();
        e();
        LoadingListener loadingListener = this.am;
        if (loadingListener != null) {
            loadingListener.onCompleted();
        }
    }

    private void b() {
        this.ai = 0;
        this.aj = 0;
        this.v.clear();
        this.ak.clear();
        this.ar.clear();
    }

    protected void e() {
        float width = getWidth();
        for (qad qadVar : this.v) {
            if (LanguageUtil.bc(getContext())) {
                this.d.drawText(qadVar.b(), (width - qadVar.a()) - this.y, qadVar.c(), this.x);
            } else {
                this.d.drawText(qadVar.b(), qadVar.a(), qadVar.c(), this.x);
            }
        }
        float[][] fArr = (float[][]) Array.newInstance((Class<?>) Float.TYPE, this.ar.size(), 2);
        for (int i = 0; i < this.ar.size(); i++) {
            qad qadVar2 = this.ar.get(i);
            String e = UnitUtil.e(CommonUtil.a(qadVar2.b()), 1, 1);
            this.t.getTextBounds(e, 0, e.length(), new Rect());
            fArr[i][0] = qadVar2.c() - (r6.bottom - r6.top);
            fArr[i][1] = qadVar2.c();
            if (LanguageUtil.bc(getContext())) {
                this.d.drawText(e, (width - qadVar2.a()) - this.i, qadVar2.c(), this.t);
            } else {
                this.d.drawText(e, qadVar2.a() - this.i, qadVar2.c(), this.t);
            }
        }
        e(fArr, width);
    }

    protected void e(float[][] fArr, float f) {
        int i;
        int i2 = 0;
        for (qad qadVar : this.ak) {
            if (i2 != 1 || this.an) {
                i2++;
                Rect rect = new Rect();
                this.x.getTextBounds(qadVar.b(), 0, qadVar.b().length(), rect);
                float f2 = rect.bottom - rect.top;
                float c = qadVar.c();
                float f3 = c - f2;
                int length = fArr.length;
                while (true) {
                    if (i < length) {
                        float[] fArr2 = fArr[i];
                        float f4 = fArr2[0];
                        i = ((f3 < f4 || f3 > fArr2[1]) && (c < f4 || c > fArr2[1])) ? i + 1 : 0;
                    } else {
                        String e = UnitUtil.e(CommonUtil.a(qadVar.b()), 1, 1);
                        if (LanguageUtil.bc(getContext())) {
                            this.d.drawText(e, (f - qadVar.a()) - this.i, qadVar.c(), this.x);
                        } else {
                            this.d.drawText(e, qadVar.a() - this.i, qadVar.c(), this.x);
                        }
                    }
                }
            } else {
                i2++;
            }
        }
    }

    private void h() {
        this.ax = ContextCompat.getColor(this.f9964a, R.color._2131299244_res_0x7f090bac);
        this.ac = ContextCompat.getColor(this.f9964a, R.color._2131299244_res_0x7f090bac);
        this.ap = ContextCompat.getColor(this.f9964a, R.color._2131299244_res_0x7f090bac);
        this.y = nrr.e(this.f9964a, this.y);
        Paint paint = new Paint(1);
        this.j = paint;
        paint.setAntiAlias(true);
        this.j.setStyle(Paint.Style.STROKE);
        this.j.setStrokeWidth(1.0f);
        this.j.setColor(this.ac);
        this.j.setPathEffect(new DashPathEffect(new float[]{10.0f, 5.0f}, 0.0f));
        Paint paint2 = new Paint();
        this.as = paint2;
        paint2.setColor(this.ao);
        this.as.setStyle(Paint.Style.FILL);
        this.as.setAntiAlias(true);
        this.as.setStrokeCap(Paint.Cap.ROUND);
        this.as.setStrokeWidth(1.0f);
        Paint paint3 = new Paint();
        this.t = paint3;
        paint3.setStrokeWidth(4.0f);
        this.t.setTextSize(Utils.convertDpToPixel(this.u));
        this.t.setFlags(1);
        this.t.setColor(this.au);
        this.t.setStyle(Paint.Style.FILL);
        Paint paint4 = new Paint();
        this.al = paint4;
        paint4.setStrokeWidth(4.0f);
        this.al.setTextSize(Utils.convertDpToPixel(this.u));
        this.al.setFlags(1);
        this.al.setColor(this.ap);
        this.al.setStyle(Paint.Style.FILL);
        Paint paint5 = new Paint();
        this.x = paint5;
        paint5.setStrokeWidth(2.0f);
        this.x.setTextSize(Utils.convertDpToPixel(this.u));
        this.x.setFlags(1);
        this.x.setColor(this.ax);
        this.x.setStyle(Paint.Style.FILL);
        Paint paint6 = new Paint(1);
        this.ag = paint6;
        paint6.setAntiAlias(true);
        this.ag.setStyle(Paint.Style.FILL);
        this.ag.setColor(this.h);
        Paint paint7 = new Paint();
        this.aa = paint7;
        paint7.setAntiAlias(true);
        this.aa.setStyle(Paint.Style.FILL);
        this.aa.setColor(this.ad);
        Paint paint8 = new Paint(1);
        this.ae = paint8;
        paint8.setAntiAlias(true);
        this.ae.setStyle(Paint.Style.STROKE);
        this.ae.setStrokeWidth(2.0f);
        this.ae.setColor(this.ad);
        this.ae.setPathEffect(new DashPathEffect(new float[]{10.0f, 0.0f}, 0.0f));
    }

    protected void c() {
        this.s = ((getHeight() - this.o) - this.k) / 6.0f;
        float ceil = (float) (Math.ceil((double) this.c) % 2.0d == 0.0d ? Math.ceil(this.c) : Math.ceil(this.c) + 1.0d);
        this.e = ceil;
        int i = (int) this.b;
        if (i % 2 != 0) {
            i--;
        }
        float f = i;
        this.b = f;
        this.g = (ceil - f) / 5.0f;
        Path path = new Path();
        float width = getWidth();
        float f2 = 0.0f;
        for (int i2 = 1; i2 <= 6; i2++) {
            if (i2 == 6) {
                Canvas canvas = this.d;
                float f3 = this.i;
                f2 = this.s * i2;
                canvas.drawLine(f3, f2, width - f3, f2, this.j);
            } else {
                float f4 = i2;
                path.moveTo(this.i, this.s * f4);
                path.lineTo(width - this.i, this.s * f4);
            }
            if (i2 == 1) {
                this.p = this.s * i2;
            }
            if (i2 == 6) {
                this.l = this.s * i2;
            }
            this.ak.add(new qad(String.valueOf(this.e - (this.g * (i2 - 1))), (this.i + width) - this.y, (this.s * i2) - this.r));
        }
        this.d.drawPath(path, this.j);
        this.ab = (width - (this.i * 2.0f)) - (this.y * 3.0f);
        a(f2 + this.u + nrr.e(this.f9964a, this.w));
    }

    protected void a(float f) {
        this.m = this.i + (this.y / 2.0f);
        this.v.add(new qad(ggl.a(new Date(this.q), "HH:mm"), this.i, f));
        this.n = this.i + this.ab + (this.y / 2.0f);
        this.v.add(new qad(ggl.a(new Date(this.f), "HH:mm"), this.i + this.ab, f));
        long j = this.q;
        if (j % 3600000 != 0) {
            j = ((j / 3600000) + 1) * 3600000;
        }
        long j2 = this.f;
        if (j2 % 3600000 != 0) {
            j2 = (j2 / 3600000) * 3600000;
        }
        long j3 = j2 - j;
        int i = (int) (j3 / 1800000);
        int i2 = 5;
        while (true) {
            if (i2 < 2) {
                i2 = 1;
                break;
            } else if (i % i2 == 0) {
                break;
            } else {
                i2--;
            }
        }
        long j4 = j3 / i2;
        if (this.f <= this.q) {
            LogUtil.c("BloodSugarNocturnalHypoglycemiaChartView", "drawX mEndTime <= mStartTime, time interval error!");
            return;
        }
        float a2 = (float) UnitUtil.a(this.ab / ((float) UnitUtil.a((r13 - r7) / 60000.0d, 2)), 2);
        float a3 = (float) UnitUtil.a((j - this.q) / 60000.0d, 2);
        float a4 = (float) UnitUtil.a(j3 / 60000.0d, 2);
        float a5 = (float) UnitUtil.a(a2 / i2, 2);
        int i3 = 1;
        while (i3 < i2) {
            this.v.add(new qad(ggl.a(new Date((i3 * j4) + j), "HH:mm"), this.i + (a3 * a2) + (i3 * a4 * a5), f));
            i3++;
            j = j;
        }
    }

    private void d() {
        float f;
        float f2 = this.e;
        float f3 = this.b;
        float f4 = f2 - f3;
        if (f4 == 0.0f) {
            LogUtil.c("BloodSugarNocturnalHypoglycemiaChartView", "drawStandardLine error!");
            return;
        }
        float f5 = this.l;
        float f6 = f5 - ((this.c - f3) * ((f5 - this.p) / f4));
        this.aq = f6;
        this.d.drawLine(this.i, f6, getWidth() - this.i, this.aq, this.as);
        float f7 = this.e;
        if (f7 != 0.0f) {
            float f8 = this.s;
            if (f8 != 0.0f) {
                float f9 = this.g;
                if (f9 != 0.0f) {
                    float f10 = this.c;
                    if (f9 > (f7 - f10) * 2.0f) {
                        float f11 = this.aq;
                        float f12 = this.u;
                        f = f11 + f12;
                        if (f8 - (f11 - this.p) > f12 * 2.0f) {
                            this.an = true;
                        } else {
                            this.an = false;
                        }
                    } else {
                        float f13 = this.aq;
                        float f14 = this.r;
                        f = f13 - f14;
                        if (f8 - (f13 - this.p) > f14 + this.u) {
                            this.an = true;
                        } else {
                            this.an = false;
                        }
                    }
                    this.ar.add(new qad(String.valueOf(f10), (this.i + getWidth()) - this.y, f));
                    return;
                }
            }
        }
        LogUtil.c("BloodSugarNocturnalHypoglycemiaChartView", "drawStandardLine error!");
    }

    private void a() {
        if (this.z.isEmpty()) {
            LogUtil.c("BloodSugarNocturnalHypoglycemiaChartView", "drawData data is empty!");
            return;
        }
        long j = this.ah;
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        for (Map.Entry<Long, Float> entry : this.z.entrySet()) {
            float floatValue = entry.getValue().floatValue();
            long longValue = entry.getKey().longValue();
            if (j != this.ah && longValue - j > 2100000) {
                if (linkedHashMap.size() > 0) {
                    b(linkedHashMap);
                }
                linkedHashMap.clear();
            }
            if (floatValue <= this.c) {
                linkedHashMap.put(Long.valueOf(longValue), Float.valueOf(floatValue));
            } else {
                if (linkedHashMap.size() > 0) {
                    b(linkedHashMap);
                }
                linkedHashMap.clear();
            }
            j = longValue;
        }
        if (linkedHashMap.size() > 0) {
            if (linkedHashMap.size() > 0) {
                b(linkedHashMap);
            }
            linkedHashMap.clear();
        }
    }

    private void b(Map<Long, Float> map) {
        if (map.isEmpty()) {
            LogUtil.c("BloodSugarNocturnalHypoglycemiaChartView", "drawClosedSection hypoglycemia data is empty!");
            return;
        }
        this.aj++;
        long j = this.ah;
        float f = this.e;
        int i = 0;
        long j2 = j;
        float f2 = f;
        for (Map.Entry<Long, Float> entry : map.entrySet()) {
            float floatValue = entry.getValue().floatValue();
            long longValue = entry.getKey().longValue();
            if (i == 0) {
                j2 = longValue;
            }
            if (i == map.size() - 1) {
                j = longValue;
            }
            if (floatValue < f2) {
                f2 = floatValue;
            }
            i++;
        }
        long d = d(j2);
        long e = e(j);
        if (d == this.ah) {
            d = j2;
        }
        long b = d == j2 ? j2 : b(Long.valueOf(d), j2);
        if (e == this.ah) {
            e = j;
        }
        if (e != j) {
            j = b(Long.valueOf(j), e);
        }
        long j3 = j;
        this.ai += (int) (j3 - b);
        b(b, j3, j2, map, f2);
    }

    private void b(long j, long j2, long j3, Map<Long, Float> map, float f) {
        float c = c(f);
        float f2 = this.aq;
        this.ag.setShader(new LinearGradient(0.0f, f2, 0.0f, c + f2, new int[]{this.at, this.h}, (float[]) null, Shader.TileMode.REPEAT));
        Path path = new Path();
        path.moveTo(c(j), this.aq);
        path.lineTo(c(j3), c(this.z.get(Long.valueOf(j3)).floatValue()));
        Path path2 = new Path();
        path2.moveTo(c(j), this.aq);
        if (j != j3) {
            path2.lineTo(c(j3), c(this.z.get(Long.valueOf(j3)).floatValue()));
        } else {
            path2.moveTo(c(j), c(this.z.get(Long.valueOf(j3)).floatValue()));
        }
        long j4 = this.ah;
        Iterator<Long> it = map.keySet().iterator();
        while (it.hasNext()) {
            long longValue = it.next().longValue();
            if (j4 != this.ah) {
                path.lineTo(c(longValue), c(this.z.get(Long.valueOf(longValue)).floatValue()));
                if (j4 != longValue) {
                    path2.lineTo(c(longValue), c(this.z.get(Long.valueOf(longValue)).floatValue()));
                }
            }
            j4 = longValue;
        }
        path.lineTo(c(j2), this.aq);
        path.lineTo(c(j), this.aq);
        path.close();
        if (j4 != j2) {
            path2.lineTo(c(j2), this.aq);
        }
        this.d.drawPath(path, this.ag);
        if (map.size() == 1 && j == j2) {
            path.moveTo(c(j3) - this.af, this.aq);
            path.lineTo(c(j3) - this.af, c(this.z.get(Long.valueOf(j3)).floatValue()));
            path.lineTo(c(j3) + this.af, c(this.z.get(Long.valueOf(j3)).floatValue()));
            path.lineTo(c(j3) + this.af, this.aq);
            path.close();
            this.d.drawPath(path, this.ag);
            this.d.drawCircle(c(j3), c(this.z.get(Long.valueOf(j3)).floatValue()), this.af, this.aa);
            return;
        }
        this.d.drawPath(path2, this.ae);
    }

    protected float c(long j) {
        if (this.f <= this.q) {
            LogUtil.c("BloodSugarNocturnalHypoglycemiaChartView", "timeToCoordinate mEndTime <= mStartTime, time interval error!");
            return this.ah;
        }
        float f = this.n;
        float f2 = this.m;
        return (((f - f2) * (j - r2)) / (r0 - r2)) + f2;
    }

    protected float c(float f) {
        float f2 = this.e;
        float f3 = this.b;
        if (f2 <= f3) {
            LogUtil.c("BloodSugarNocturnalHypoglycemiaChartView", "valueToCoordinate mBloodSugarMaxValueChart <= mBloodSugarMinValue, error!");
            return this.ah;
        }
        float f4 = this.l;
        return f4 - (((f4 - this.p) * (f - f3)) / (f2 - f3));
    }

    private long b(Long l, long j) {
        long longValue = l.longValue();
        long j2 = this.ah;
        if (longValue == j2 || j == j2) {
            return j2;
        }
        float floatValue = this.z.get(l).floatValue();
        float floatValue2 = this.z.get(Long.valueOf(j)).floatValue();
        if (floatValue == floatValue2) {
            return this.ah;
        }
        return ((long) (((floatValue - this.c) * (j - l.longValue())) / (floatValue - floatValue2))) + l.longValue();
    }

    private long d(long j) {
        long j2 = this.ah;
        Iterator<Long> it = this.z.keySet().iterator();
        while (it.hasNext()) {
            long longValue = it.next().longValue();
            if (longValue == j) {
                break;
            }
            j2 = longValue;
        }
        return j - j2 < 2100000 ? j2 : this.ah;
    }

    private long e(long j) {
        long j2 = this.ah;
        Iterator<Long> it = this.z.keySet().iterator();
        long j3 = j2;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Long next = it.next();
            if (next.longValue() == j) {
                j3 = next.longValue();
            } else if (j3 != this.ah) {
                if (next.longValue() - j3 < 2100000) {
                    j2 = next.longValue();
                }
            }
        }
        return j2 - j < 2100000 ? j2 : this.ah;
    }

    public void setStartTime(long j) {
        this.q = j;
    }

    public void setEndTime(long j) {
        this.f = j;
    }

    public long getStartTime() {
        return this.q;
    }

    public long getEndTime() {
        return this.f;
    }

    public void setData(Map<Long, Float> map) {
        if (map == null || map.isEmpty()) {
            LogUtil.c("BloodSugarNocturnalHypoglycemiaChartView", "setData data is empty");
            return;
        }
        if (this.f <= this.q) {
            LogUtil.c("BloodSugarNocturnalHypoglycemiaChartView", "setData mEndTime <= mStartTime, time interval error!");
            return;
        }
        this.z.clear();
        if (map.size() > 0) {
            this.z.putAll(map);
        }
        this.b = e(this.z);
        invalidate();
    }

    public void setBloodSugarStandardValue(float f) {
        this.c = f;
    }

    private float e(Map<Long, Float> map) {
        Iterator<Map.Entry<Long, Float>> it = map.entrySet().iterator();
        float f = 0.0f;
        while (it.hasNext()) {
            float floatValue = it.next().getValue().floatValue();
            if (f > floatValue) {
                f = floatValue;
            }
        }
        return f;
    }

    public void setLoadingListener(LoadingListener loadingListener) {
        this.am = loadingListener;
    }

    public int getHypoglycemiaNum() {
        return this.aj;
    }

    public int getHypoglycemiaAverageDuration() {
        if (this.aj == 0) {
            return 0;
        }
        LogUtil.d("BloodSugarNocturnalHypoglycemiaChartView", "setStatisticsData mHypoglycemiaDuration:", Integer.valueOf(this.ai));
        return Math.round(((this.ai / 1000.0f) / 60.0f) / this.aj);
    }
}
