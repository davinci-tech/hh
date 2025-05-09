package com.huawei.healthcloud.plugintrack.trackanimation.dynamiccurve;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.healthcloud.plugintrack.trackanimation.dynamiccurve.auxiliary.CurveColorControl;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.ReTrackSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.hal;
import defpackage.hao;
import defpackage.har;
import defpackage.nsn;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class DynamicCurve extends View {
    private static final float[] b = {0.0f, 0.15f, 1.0f};

    /* renamed from: a, reason: collision with root package name */
    private Paint f3585a;
    private int[] aa;
    private ArrayList<LatLong> ab;
    private int[] ac;
    private int ad;
    private int ae;
    private double af;
    private int ag;
    private double ah;
    private Paint c;
    private Paint d;
    private Path e;
    private float f;
    private int g;
    private Paint h;
    private int i;
    private Paint j;
    private double k;
    private double l;
    private double m;
    private double n;
    private float[] o;
    private double p;
    private Paint q;
    private Path r;
    private Paint s;
    private int[] t;
    private boolean u;
    private int v;
    private int w;
    private boolean x;
    private CurveColorControl y;
    private int[] z;

    public DynamicCurve(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.ab = null;
        this.o = null;
        this.r = null;
        this.q = null;
        this.s = null;
        this.e = null;
        this.c = null;
        this.f3585a = null;
        this.j = null;
        this.d = null;
        this.h = null;
        this.ae = 0;
        this.ag = 0;
        this.v = 0;
        this.g = 0;
        this.ad = 0;
        this.i = 0;
        this.w = 0;
        this.f = 0.0f;
        this.y = null;
        this.u = true;
        this.x = false;
        this.v = nsn.c(context, 1.0f);
        this.g = nsn.c(context, 3.5f);
        this.ad = nsn.c(context, 4.5f);
    }

    public DynamicCurve e(ReTrackSimplify reTrackSimplify, ArrayList<LatLong> arrayList) {
        if (reTrackSimplify == null || arrayList == null) {
            LogUtil.h("Track_DynamicCurve", "ReTrackData is null");
            return this;
        }
        this.ab = arrayList;
        this.k = ((Double) reTrackSimplify.getMaxMultiplexField().second).doubleValue();
        this.m = ((Double) reTrackSimplify.getMinMultiplexField().second).doubleValue();
        switch (reTrackSimplify.getCurveType()) {
            case 32:
                this.y = new hao();
                break;
            case 33:
                this.y = new hal();
                break;
            case 34:
                this.y = new har();
                break;
            case 35:
                this.y = new hao();
                break;
            default:
                this.y = new hao();
                break;
        }
        this.t = this.y.getInitFillingColor();
        this.aa = this.y.getRunCurveColor();
        this.z = this.y.getRunFillingColor();
        this.ac = this.y.getRunLineColor();
        this.u = true;
        invalidate();
        return this;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.ae = View.MeasureSpec.getSize(i);
        int size = View.MeasureSpec.getSize(i2);
        this.ag = size;
        this.p = 0.0d;
        float f = size;
        this.ah = this.ad + r4;
        this.l = 0.5f * f;
        this.f = f;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            this.ae = getWidth();
            int height = getHeight();
            this.ag = height;
            this.p = 0.0d;
            float f = height;
            this.ah = this.ad + r0;
            this.l = 0.5f * f;
            this.f = f;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.ab == null) {
            LogUtil.h("Track_DynamicCurve", "ReTrack Data is null");
            return;
        }
        d();
        a();
        aYh_(canvas);
        e(this.i);
        aYf_(canvas);
        aYg_(canvas);
    }

    private void d() {
        double d = this.k;
        double d2 = this.m;
        if (d - d2 < 1.0E-6d) {
            this.m = d2 - 10.0d;
            this.k = d + 10.0d;
        }
        this.af = (this.k - this.m) / this.l;
        this.n = (this.ae - this.p) / (this.ab.size() - 1);
        int i = this.i;
        if (i == 0) {
            this.f = this.ag;
        }
        float multiplexField = (float) (this.ah - ((this.ab.get(i).getMultiplexField() - this.m) / this.af));
        float f = this.f;
        if (multiplexField >= f) {
            multiplexField = f;
        }
        this.f = multiplexField;
    }

    private void aYf_(Canvas canvas) {
        if (this.i > 0) {
            this.c.setShader(new LinearGradient(0.0f, 0.0f, this.ae, 0.0f, this.aa, b, Shader.TileMode.CLAMP));
            canvas.drawLines(this.o, 0, this.i * 4, this.c);
            int i = this.i;
            int i2 = i * 4;
            int i3 = i2 + 1;
            if (i == this.ab.size() - 1) {
                int i4 = this.i * 4;
                i2 = i4 - 2;
                i3 = i4 - 1;
            }
            if (this.x) {
                return;
            }
            this.j.setShader(new LinearGradient(0.0f, this.o[i3], 0.0f, this.ag, this.ac, (float[]) null, Shader.TileMode.CLAMP));
            float[] fArr = this.o;
            float f = fArr[i2];
            canvas.drawLine(f, fArr[i3], f, this.ag, this.j);
            float[] fArr2 = this.o;
            canvas.drawCircle(fArr2[i2], fArr2[i3], this.ad, this.h);
            float[] fArr3 = this.o;
            canvas.drawCircle(fArr3[i2], fArr3[i3], this.g, this.d);
        }
    }

    private void aYg_(Canvas canvas) {
        if (this.i > 0) {
            Path path = new Path();
            path.addPath(this.e);
            path.lineTo((float) ((this.i * this.n) + this.p), this.ag);
            path.lineTo((float) this.p, this.ag);
            path.lineTo((float) this.p, (float) (this.ah - ((this.ab.get(0).getMultiplexField() - this.m) / this.af)));
            this.f3585a.setShader(new LinearGradient(0.0f, this.f, 0.0f, this.ag, this.z, (float[]) null, Shader.TileMode.CLAMP));
            canvas.drawPath(path, this.f3585a);
        }
    }

    private void e(int i) {
        double d = i;
        double d2 = this.n;
        double d3 = this.p;
        if (i == 0) {
            Path path = new Path();
            this.e = path;
            path.moveTo((float) ((d * d2) + d3), (float) (this.ah - ((this.ab.get(i).getMultiplexField() - this.m) / this.af)));
        } else {
            int i2 = this.w;
            while (true) {
                i2++;
                if (i2 > i) {
                    break;
                }
                this.e.lineTo((float) ((i2 * this.n) + this.p), (float) (this.ah - ((this.ab.get(i2).getMultiplexField() - this.m) / this.af)));
            }
        }
        this.w = i;
    }

    private void a() {
        if (this.u) {
            this.u = false;
            Paint paint = new Paint();
            this.q = paint;
            paint.setStyle(Paint.Style.STROKE);
            this.q.setStrokeWidth(this.v);
            this.q.setAntiAlias(true);
            this.q.setPathEffect(new CornerPathEffect(10.0f));
            this.q.setColor(this.y.getInitCurveColor());
            Paint paint2 = new Paint();
            this.s = paint2;
            paint2.setStyle(Paint.Style.FILL);
            this.s.setPathEffect(new CornerPathEffect(10.0f));
            this.s.setAntiAlias(true);
            this.s.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, getHeight(), this.t, (float[]) null, Shader.TileMode.CLAMP));
            Paint paint3 = new Paint();
            this.c = paint3;
            paint3.setStyle(Paint.Style.STROKE);
            this.c.setStrokeWidth(this.v);
            this.c.setAntiAlias(true);
            this.c.setPathEffect(new CornerPathEffect(10.0f));
            Paint paint4 = new Paint();
            this.f3585a = paint4;
            paint4.setStyle(Paint.Style.FILL);
            this.f3585a.setAntiAlias(true);
            this.f3585a.setPathEffect(new CornerPathEffect(10.0f));
            Paint paint5 = new Paint();
            this.j = paint5;
            paint5.setStyle(Paint.Style.STROKE);
            this.j.setStrokeWidth(this.v);
            this.j.setAntiAlias(true);
            Paint paint6 = new Paint();
            this.d = paint6;
            paint6.setStyle(Paint.Style.FILL);
            this.d.setAntiAlias(true);
            this.d.setColor(this.y.getRunCircleColor());
            Paint paint7 = new Paint();
            this.h = paint7;
            paint7.setStyle(Paint.Style.FILL);
            this.h.setAntiAlias(true);
            this.h.setColor(this.y.getRunRingColor());
        }
    }

    private void aYh_(Canvas canvas) {
        if (this.r == null || this.o == null) {
            this.r = new Path();
            this.o = new float[(this.ab.size() - 1) * 4];
            int i = 0;
            for (int i2 = 0; i2 < this.ab.size(); i2++) {
                float f = (float) ((i2 * this.n) + this.p);
                float multiplexField = (float) (this.ah - ((this.ab.get(i2).getMultiplexField() - this.m) / this.af));
                if (i2 == 0) {
                    this.r.moveTo(f, multiplexField);
                    float[] fArr = this.o;
                    int i3 = i + 1;
                    fArr[i] = f;
                    i += 2;
                    fArr[i3] = multiplexField;
                } else if (i2 == this.ab.size() - 1) {
                    this.r.lineTo(f, multiplexField);
                    float[] fArr2 = this.o;
                    int i4 = i + 1;
                    fArr2[i] = f;
                    i += 2;
                    fArr2[i4] = multiplexField;
                } else {
                    this.r.lineTo(f, multiplexField);
                    float[] fArr3 = this.o;
                    fArr3[i] = f;
                    fArr3[i + 1] = multiplexField;
                    int i5 = i + 3;
                    fArr3[i + 2] = f;
                    i += 4;
                    fArr3[i5] = multiplexField;
                }
            }
        }
        canvas.drawLines(this.o, this.q);
        Path path = new Path();
        path.addPath(this.r);
        path.lineTo((float) (((this.ab.size() - 1) * this.n) + this.p), this.ag);
        path.lineTo((float) this.p, this.ag);
        path.lineTo((float) this.p, (float) (this.ah - ((this.ab.get(0).getMultiplexField() - this.m) / this.af)));
        canvas.drawPath(path, this.s);
    }

    public void c(int i) {
        if (i >= this.ab.size() - 1) {
            this.i = this.ab.size() - 1;
            this.x = true;
        } else {
            this.i = i;
        }
        postInvalidate();
    }

    public void b() {
        this.i = 0;
        this.w = 0;
        this.x = false;
        postInvalidate();
    }
}
