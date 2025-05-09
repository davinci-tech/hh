package com.huawei.health.sport.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ffh;
import defpackage.koq;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class SugChart extends View {

    /* renamed from: a, reason: collision with root package name */
    private float f3003a;
    private boolean aa;
    private Path ab;
    private boolean ac;
    private float ad;
    private float ae;
    private int af;
    private List<PointF> ag;
    private Path ah;
    private Paint ai;
    private Paint aj;
    private float ak;
    private float al;
    private float am;
    private int an;
    private Paint ao;
    private float[] ap;
    private int[] aq;
    private Paint ar;
    private RectF as;
    private Paint at;
    private ffh au;
    private float av;
    private ffh aw;
    private float ax;
    private int ay;
    private float az;
    private int b;
    private int ba;
    private int bb;
    private int bc;
    private float bd;
    private int be;
    private float bf;
    private int bg;
    private float bh;
    private float bj;
    private float c;
    private Paint d;
    private Paint e;
    private PointF f;
    private float g;
    private Paint h;
    private Paint i;
    private float j;
    private int k;
    private Context l;
    private float m;
    private int n;
    private Paint o;
    private int p;
    private List<ffh> q;
    private int r;
    private float[] s;
    private float t;
    private float u;
    private int v;
    private float w;
    private float x;
    private Paint y;
    private boolean z;

    public SugChart(Context context) {
        super(context);
        this.bj = 0.0f;
        this.ax = 15.0f;
        this.b = Color.parseColor("#556A73");
        this.af = Color.parseColor("#FFE9D1BA");
        this.s = new float[6];
        this.am = 10.0f;
        this.ah = new Path();
        this.ab = new Path();
        this.al = 1.0f;
        this.ag = new ArrayList(10);
        this.ad = 2.1474836E9f;
        this.ap = new float[2];
        this.bb = 0;
        this.k = Color.parseColor("#AFAFB0");
        this.r = 0;
        this.p = 0;
        this.q = new ArrayList(10);
        this.n = 2;
        this.g = 2.0f;
        d(context);
    }

    public SugChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.bj = 0.0f;
        this.ax = 15.0f;
        this.b = Color.parseColor("#556A73");
        this.af = Color.parseColor("#FFE9D1BA");
        this.s = new float[6];
        this.am = 10.0f;
        this.ah = new Path();
        this.ab = new Path();
        this.al = 1.0f;
        this.ag = new ArrayList(10);
        this.ad = 2.1474836E9f;
        this.ap = new float[2];
        this.bb = 0;
        this.k = Color.parseColor("#AFAFB0");
        this.r = 0;
        this.p = 0;
        this.q = new ArrayList(10);
        this.n = 2;
        this.g = 2.0f;
        d(context);
    }

    public SugChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.bj = 0.0f;
        this.ax = 15.0f;
        this.b = Color.parseColor("#556A73");
        this.af = Color.parseColor("#FFE9D1BA");
        this.s = new float[6];
        this.am = 10.0f;
        this.ah = new Path();
        this.ab = new Path();
        this.al = 1.0f;
        this.ag = new ArrayList(10);
        this.ad = 2.1474836E9f;
        this.ap = new float[2];
        this.bb = 0;
        this.k = Color.parseColor("#AFAFB0");
        this.r = 0;
        this.p = 0;
        this.q = new ArrayList(10);
        this.n = 2;
        this.g = 2.0f;
        d(context);
    }

    public void setType(int i) {
        this.bb = i;
    }

    private float i(float f) {
        if (this.ad == 220.0f) {
            return getDrawBaseLine() - ((f - this.bd) * this.u);
        }
        return this.bg + this.ae + getPaddingTop() + ((this.bf - f) * this.u);
    }

    private void g() {
        Paint paint = new Paint(1);
        this.at = paint;
        paint.setTextAlign(Paint.Align.CENTER);
        Paint paint2 = new Paint(1);
        this.e = paint2;
        paint2.setColor(this.b);
        this.e.setTextAlign(Paint.Align.CENTER);
        Paint paint3 = new Paint(1);
        this.ai = paint3;
        paint3.setStrokeWidth(this.ae);
        this.ai.setStyle(Paint.Style.STROKE);
        Paint paint4 = new Paint(1);
        this.o = paint4;
        paint4.setStyle(Paint.Style.STROKE);
        Paint paint5 = new Paint(1);
        this.d = paint5;
        paint5.setStrokeWidth(0.25f);
        this.d.setStyle(Paint.Style.STROKE);
        this.d.setColor(this.b);
        this.y = new Paint(1);
        Paint paint6 = new Paint(1);
        this.aj = paint6;
        paint6.setStyle(Paint.Style.STROKE);
        Paint paint7 = new Paint(1);
        this.h = paint7;
        paint7.setColor(-1);
        this.h.setTextAlign(Paint.Align.CENTER);
        this.i = new Paint(1);
    }

    private void d(Context context) {
        if (context == null) {
            return;
        }
        this.l = context;
        a();
        this.an = a(3.0f);
        this.at.setTextSize(this.ax);
        this.aj.setStrokeWidth(this.am);
        this.as = new RectF(0.0f, 0.0f, this.ay, this.bc);
        this.y.setStrokeWidth(a(1.0f));
        this.v = -1;
        this.h.setTextSize(a(12.0f));
        this.e.setTextSize(this.c);
        g();
    }

    private void a() {
        this.x = a(20.0f);
        this.ax = e(12.0f);
        this.c = e(12.0f);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.bc = i2;
        if (this.ac) {
            this.bj = (i2 - this.c) - this.an;
        } else {
            this.bj = i2 - Math.abs(this.c * 2.0f);
        }
        this.ay = i;
        this.f = new PointF(i / 2.0f, i2 / 2.0f);
        if (this.q.size() > 0) {
            b(i);
            j();
        }
    }

    private void b(int i) {
        if (this.aa) {
            String format = new DecimalFormat("##").format(this.bf);
            this.e.getTextBounds(format, 0, format.length(), new Rect());
            this.bg = getPaddingTop();
        } else {
            this.bg = getPaddingTop();
        }
        this.ak = getPaddingStart();
        if (this.n != 2 || this.q.size() <= 1) {
            return;
        }
        this.x = (((i - this.ak) - getPaddingRight()) - (this.g * this.q.size())) / (this.q.size() - 1);
    }

    private int b(float f) {
        return (((int) (f + 1.0f)) / 2) * 2;
    }

    private void j() {
        if (this.q.size() == 0) {
            return;
        }
        o();
        if (this.bj > 0.0f) {
            this.ah.reset();
            k();
            c();
            if (this.n == 2) {
                h();
                m();
            }
        }
    }

    private void m() {
        if (this.ag.size() > 0) {
            this.ab.lineTo(this.ag.get(r1.size() - 1).x, this.bj);
            this.ab.lineTo(this.ag.get(0).x, this.bj);
            this.ab.close();
        }
    }

    private void k() {
        if (this.ad == 220.0f) {
            b();
        } else {
            d();
        }
    }

    private float getDrawBaseLine() {
        return (this.bj - getCalloutHeight()) - a(4.0f);
    }

    private void b() {
        if (this.au.c() == this.aw.c()) {
            this.bd = 0.0f;
            this.m = this.au.c() + this.aw.c();
            this.u = ((((getDrawBaseLine() - this.bg) - getCalloutHeight()) - a(4.0f)) - (this.ae / 2.0f)) / this.m;
        } else {
            this.bd = this.aw.c();
            float drawBaseLine = getDrawBaseLine();
            float f = this.bg;
            float calloutHeight = getCalloutHeight();
            this.u = ((((drawBaseLine - f) - calloutHeight) - a(4.0f)) - (this.ae / 2.0f)) / (this.au.c() - this.aw.c());
        }
        e();
    }

    private void e() {
        int length = this.aq.length * 2;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = this.aq[i / 2];
        }
        this.aq = iArr;
        float[] fArr = new float[length];
        this.ap = fArr;
        b(fArr);
    }

    private void d() {
        if (!this.z) {
            float f = this.bj;
            float f2 = this.bg;
            float calloutHeight = ((((f - f2) - getCalloutHeight()) - a(4.0f)) - (this.ae / 2.0f)) / (this.au.c() - this.bd);
            this.u = calloutHeight;
            this.bf = this.bf < ((float) b((this.bj - this.bg) / calloutHeight)) ? b(r1) : this.bf;
        }
        this.u = (((this.bj - this.bg) - this.ae) - getPaddingTop()) / this.bf;
    }

    private void c() {
        for (int i = 0; i < this.q.size(); i++) {
            ffh ffhVar = this.q.get(i);
            ffhVar.c((ffhVar.c() - this.bd) * this.u);
            ffhVar.b(this.g);
            PointF awN_ = ffhVar.awN_();
            if (!LanguageUtil.bc(getContext())) {
                float f = i;
                awN_.x = getPaddingStart() + (this.x * f) + (this.g * f);
            } else {
                float f2 = i;
                awN_.x = getPaddingEnd() + (this.x * f2) + (this.g * f2);
            }
            if (this.ad == 220.0f) {
                awN_.y = getDrawBaseLine() - ffhVar.a();
            } else {
                awN_.y = (this.bj - this.f3003a) - ffhVar.a();
            }
        }
    }

    private void h() {
        this.ag.clear();
        for (ffh ffhVar : this.q) {
            if (a(ffhVar)) {
                this.ag.add(ffhVar.awM_());
            }
        }
        for (int i = 0; i < this.ag.size(); i++) {
            if (this.ag.get(i).y >= 0.0f) {
                PointF pointF = this.ag.get(i);
                if (i == 0) {
                    this.ah.moveTo(pointF.x, pointF.y);
                    this.ab.moveTo(pointF.x, pointF.y);
                }
                if (i < this.ag.size() - 1) {
                    PointF pointF2 = this.ag.get(i + 1);
                    float f = (pointF.x + pointF2.x) / 2.0f;
                    this.ah.cubicTo(f, pointF.y, f, pointF2.y, pointF2.x, pointF2.y);
                    this.ab.cubicTo(f, pointF.y, f, pointF2.y, pointF2.x, pointF2.y);
                }
            }
        }
    }

    private boolean a(ffh ffhVar) {
        return ffhVar.c() <= this.ad * this.u && ffhVar.c() >= 0.0f;
    }

    private void o() {
        if (this.n == 3) {
            float paddingTop = this.am > ((float) ((this.bc / 2) - getPaddingTop())) ? (this.bc / 2) - getPaddingTop() : this.am;
            this.am = paddingTop;
            this.aj.setStrokeWidth(paddingTop);
            float f = this.w;
            float f2 = this.am / 10.0f;
            if (f < f2) {
                f = f2;
            }
            this.w = f;
            this.j = ((this.bc / 2) - getPaddingTop()) - (this.am / 2.0f);
            this.as.set(this.f.x - this.j, this.f.y - this.j, this.f.x + this.j, this.f.y + this.j);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        super.onDraw(canvas);
        List<ffh> list = this.q;
        if (list != null && list.size() > 0) {
            int i = this.n;
            if (i == 3) {
                axp_(canvas);
            } else if (i == 2) {
                axq_(canvas);
            } else {
                LogUtil.a("Track_SugChart", "don't deal such type");
            }
        }
        axh_(canvas);
    }

    private void axq_(Canvas canvas) {
        if (this.ag.size() == 1) {
            this.ai.setColor(this.af);
            canvas.drawPoint(this.ag.get(0).x, this.ag.get(0).y, this.ai);
            if (this.bb == 0) {
                axk_(canvas, this.au, true, Color.parseColor("#EC8900"));
            }
        } else {
            axo_(canvas);
        }
        axl_(canvas);
        if (this.aa) {
            axr_(canvas);
        }
    }

    private void axr_(Canvas canvas) {
        float f = (this.bf - this.bd) / this.bh;
        float f2 = this.u;
        float paddingStart = getPaddingStart();
        float paddingEnd = this.ay - getPaddingEnd();
        float g = g(getPaddingStart());
        if (this.ad == 220.0f) {
            if (this.ac) {
                return;
            }
            canvas.drawLine(paddingStart, 2.0f, paddingEnd, 2.0f, this.d);
            canvas.drawLine(paddingStart, i(0.0f), paddingEnd, i(0.0f), this.d);
            canvas.drawText(new DecimalFormat("#").format(0L), g, i(0.0f) - a(4.0f), this.e);
            return;
        }
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(Float.valueOf(paddingStart));
        arrayList.add(Float.valueOf(paddingEnd));
        axm_(canvas, f, f * f2, g, arrayList);
    }

    private float g(float f) {
        if (!LanguageUtil.bc(getContext())) {
            float paddingEnd = this.ay - getPaddingEnd();
            this.e.setTextAlign(Paint.Align.RIGHT);
            return paddingEnd;
        }
        this.e.setTextAlign(Paint.Align.LEFT);
        return f;
    }

    private void axm_(Canvas canvas, float f, float f2, float f3, List<Float> list) {
        if (list == null || list.size() < 2) {
            LogUtil.h("Track_SugChart", "line's length is invalid");
            return;
        }
        int i = 0;
        float floatValue = list.get(0).floatValue();
        float floatValue2 = list.get(1).floatValue();
        while (true) {
            float f4 = i;
            if (f4 > this.bh) {
                return;
            }
            float f5 = this.bj - (f2 * f4);
            if (i > 0) {
                canvas.drawText(new DecimalFormat("#").format(this.bd + (f4 * f)), f3, a(4.0f) + f5 + axs_(this.e), this.e);
                Path path = new Path();
                path.moveTo(floatValue, f5);
                if (koq.b(this.q) && this.q.size() > 0) {
                    path.lineTo(floatValue2, f5);
                }
                this.d.setPathEffect(getPathDashEffect());
                canvas.drawPath(path, this.d);
            }
            i++;
        }
    }

    private DashPathEffect getPathDashEffect() {
        return new DashPathEffect(new float[]{4.0f, 4.0f}, this.al);
    }

    private void axp_(Canvas canvas) {
        this.av = 0.0f;
        for (ffh ffhVar : this.q) {
            this.aj.setColor(ffhVar.b());
            float c = (ffhVar.c() / (this.t * this.u)) * 360.0f;
            canvas.drawArc(this.as, this.av, c, false, this.aj);
            this.av += c;
        }
        this.y.setStrokeWidth(this.w);
        this.y.setColor(this.v);
        canvas.save();
        Iterator<ffh> it = this.q.iterator();
        while (it.hasNext()) {
            float c2 = it.next().c() / (this.t * this.u);
            canvas.drawLine(((this.f.x + this.j) - (this.am / 2.0f)) - 1.0f, this.f.y, this.f.x + this.j + (this.am / 2.0f) + 1.0f, this.f.y, this.y);
            canvas.rotate(c2 * 360.0f, this.f.x, this.f.y);
        }
        canvas.restore();
    }

    public void c(float f) {
        this.an = a(f);
    }

    private void axh_(Canvas canvas) {
        float f = this.bj;
        float f2 = this.an;
        float f3 = this.ac ? this.c : this.ax;
        this.e.setTextAlign(Paint.Align.LEFT);
        String valueOf = String.valueOf((int) this.az);
        float paddingRight = ((this.ay - this.ak) - getPaddingRight()) - this.e.measureText(valueOf, 0, valueOf.length());
        for (int i = 0; i < this.be + 1; i++) {
            String e = UnitUtil.e(this.ba * i, 1, 0);
            float measureText = this.e.measureText(e, 0, e.length());
            float f4 = this.ak + (((this.ba * i) / this.az) * paddingRight);
            if (LanguageUtil.bc(getContext())) {
                f4 = (this.ay - getPaddingStart()) - (((this.ba * i) / this.az) * paddingRight);
                this.e.setTextAlign(Paint.Align.RIGHT);
            }
            float f5 = (measureText / 2.0f) + f4;
            float f6 = this.ay;
            if (f5 > f6) {
                f4 = f6 - measureText;
            }
            canvas.drawText(e, f4, f + f2 + f3, this.e);
        }
    }

    private void axo_(Canvas canvas) {
        if (this.r != 0 && this.p != 0) {
            axi_(canvas);
        }
        axw_(this.ai);
        canvas.drawPath(this.ah, this.ai);
        axj_(canvas);
    }

    private void axj_(Canvas canvas) {
        if (this.h == null || !i() || this.aq == null) {
            return;
        }
        axk_(canvas, this.au, true, Color.parseColor("#EC8900"));
        ffh ffhVar = this.aw;
        int[] iArr = this.aq;
        axk_(canvas, ffhVar, false, iArr[iArr.length - 1]);
    }

    private boolean i() {
        return (this.au == null || this.aw == null) ? false : true;
    }

    private void axk_(Canvas canvas, ffh ffhVar, boolean z, int i) {
        float f;
        float f2;
        if (this.ac) {
            PointF awM_ = ffhVar.awM_();
            canvas.drawCircle(awM_.x, awM_.y, this.ae, this.ao);
            return;
        }
        float a2 = a(2.0f) + (this.ae / 2.0f);
        this.i.setColor(i);
        if (ffhVar.c() < 0.0f) {
            return;
        }
        PointF awM_2 = ffhVar.awM_();
        String valueOf = String.valueOf(ffhVar.d());
        Path path = new Path();
        float a3 = a(21.0f);
        float a4 = a(6.0f);
        float a5 = a(8.0f);
        float a6 = a(4.0f);
        if (z) {
            f = a6;
            f2 = 4.0f;
            axu_(a2, awM_2, path, a5, f);
        } else {
            f = a6;
            f2 = 4.0f;
            axt_(a2, awM_2, path, a5, f);
        }
        canvas.drawPath(path, this.i);
        float measureText = this.h.measureText(valueOf) / 2.0f;
        RectF rectF = new RectF((awM_2.x - measureText) - a4, ((awM_2.y - a2) - f) - a3, awM_2.x + measureText + a4, (awM_2.y - a2) - f);
        if (!z) {
            rectF.offset(0.0f, this.ae + getCalloutHeight() + a(f2) + a(2.0f));
        }
        axn_(canvas, awM_2, valueOf, rectF);
    }

    private void axn_(Canvas canvas, PointF pointF, String str, RectF rectF) {
        float axv_ = axv_(pointF, rectF, rectF.right - this.ay, pointF.x, 1.0f);
        canvas.drawRoundRect(rectF, rectF.height() / 2.0f, rectF.height() / 2.0f, this.i);
        canvas.drawText(str, axv_, (rectF.bottom - (rectF.height() / 2.0f)) + (axs_(this.h) / 2.0f), this.h);
    }

    private float axv_(PointF pointF, RectF rectF, float f, float f2, float f3) {
        if (f > 0.0f) {
            rectF.right = (rectF.right - f) - f3;
            rectF.left = (rectF.left - f) - f3;
            return (pointF.x - f) - f3;
        }
        if (rectF.left < 0.0f) {
            rectF.right = (rectF.right - rectF.left) + f3;
            float f4 = (pointF.x - rectF.left) + f3;
            rectF.left = f3;
            return f4;
        }
        LogUtil.a("Track_SugChart", "limitInside error");
        return f2;
    }

    private void axu_(float f, PointF pointF, Path path, float f2, float f3) {
        path.moveTo(pointF.x, pointF.y - f);
        float f4 = f2 / 2.0f;
        path.lineTo(pointF.x - f4, ((pointF.y - f) - f3) - 1.0f);
        path.lineTo(pointF.x + f4, ((pointF.y - f) - f3) - 1.0f);
        path.close();
    }

    private void axt_(float f, PointF pointF, Path path, float f2, float f3) {
        path.moveTo(pointF.x, pointF.y + f);
        float f4 = f2 / 2.0f;
        path.lineTo(pointF.x - f4, pointF.y + f + f3 + 1.0f);
        path.lineTo(pointF.x + f4, pointF.y + f + f3 + 1.0f);
        path.close();
    }

    private float axs_(Paint paint) {
        return (-paint.ascent()) - paint.descent();
    }

    private float getCalloutHeight() {
        if (this.ac) {
            return 0.0f;
        }
        return a(21.0f) + a(2.0f) + a(4.0f);
    }

    private void axi_(Canvas canvas) {
        Paint paint = new Paint();
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, this.bj, this.p, this.r, Shader.TileMode.MIRROR);
        paint.setStyle(Paint.Style.FILL);
        paint.setShader(linearGradient);
        canvas.drawPath(this.ab, paint);
    }

    private void axw_(Paint paint) {
        if (f()) {
            paint.setShader(new LinearGradient(0.0f, i(getTopZone()), 0.0f, i(getBottomZone()), this.aq, this.ap, Shader.TileMode.CLAMP));
        } else {
            this.ai.setColor(this.af);
        }
    }

    private float getBottomZone() {
        return this.s[r0.length - 1] - 10.0f;
    }

    private float getTopZone() {
        return this.s[0];
    }

    private void b(float[] fArr) {
        float bottomZone = getBottomZone();
        float topZone = getTopZone();
        float f = topZone - bottomZone;
        int i = 0;
        int i2 = 0;
        while (true) {
            float[] fArr2 = this.s;
            if (i >= fArr2.length) {
                return;
            }
            int i3 = i2 + 1;
            fArr[i2] = ((topZone - fArr2[i]) / f) + 1.0E-4f;
            if (i < fArr2.length - 1) {
                i2 += 2;
                fArr[i3] = (topZone - fArr2[i + 1]) / f;
            } else {
                i2 += 2;
                fArr[i3] = 1.0f;
            }
            i++;
        }
    }

    private boolean f() {
        int[] iArr = this.aq;
        return iArr != null && iArr.length > 1 && this.s.length == 6;
    }

    private void axl_(Canvas canvas) {
        this.o.setColor(this.k);
        this.o.setStrokeWidth(2.0f);
        canvas.drawLine(getPaddingStart(), this.bj, this.ay - getPaddingEnd(), this.bj, this.o);
    }

    public void c(List<ffh> list) {
        String format = String.format("%%s %s", getResources().getString(R$string.IDS_main_watch_heart_rate_unit_string));
        this.q.clear();
        this.t = 0.0f;
        if (list == null || list.size() <= 0) {
            return;
        }
        if (LanguageUtil.bc(getContext())) {
            Collections.reverse(list);
        }
        d(list, format);
        this.q.addAll(list);
        if (!this.aa && this.bf == 0.0f) {
            this.bf = list.get(0).c();
        }
        for (ffh ffhVar : list) {
            if (!this.aa || this.n == 3) {
                this.bf = this.bf > ffhVar.c() ? this.bf : ffhVar.c();
            }
            this.t += ffhVar.c() - this.bd;
        }
        int i = this.ay;
        if (i > 0) {
            b(i);
            j();
        }
        postInvalidate();
    }

    private void d(List<ffh> list, String str) {
        ffh ffhVar = (ffh) Collections.max(list, new Comparator<ffh>() { // from class: com.huawei.health.sport.view.SugChart.1
            @Override // java.util.Comparator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public int compare(ffh ffhVar2, ffh ffhVar3) {
                return Float.compare(SugChart.this.f(ffhVar2.c()), SugChart.this.f(ffhVar3.c()));
            }
        });
        this.au = ffhVar;
        this.m = ffhVar.c();
        this.au.c(String.format(str, new DecimalFormat("#").format(this.au.c())));
        ffh ffhVar2 = (ffh) Collections.min(list, new Comparator<ffh>() { // from class: com.huawei.health.sport.view.SugChart.5
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(ffh ffhVar3, ffh ffhVar4) {
                return Float.compare(SugChart.this.h(ffhVar3.c()), SugChart.this.h(ffhVar4.c()));
            }
        });
        this.aw = ffhVar2;
        ffhVar2.c(String.format(str, new DecimalFormat("#").format(this.aw.c())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float f(float f) {
        if (f > this.ad) {
            return 0.0f;
        }
        return f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float h(float f) {
        return f <= 0.0f ? this.ad : f;
    }

    public void c(int i) {
        this.n = i;
    }

    public float getTextSize() {
        return this.ax;
    }

    public void setTextSize(float f) {
        float e = e(f);
        this.ax = e;
        this.bj = 2.0f * e;
        this.at.setTextSize(e);
    }

    public void d(float f) {
        this.c = f;
        this.e.setTextSize(f);
        this.bj = this.bc - Math.abs(this.c * 2.0f);
        if (this.ay > 0) {
            j();
        }
    }

    public void e(int i, int i2) {
        this.b = i;
        this.e.setColor(i);
        this.d.setColor(this.b);
        this.k = i2;
    }

    public void setLineWidth(float f) {
        this.ae = f;
        this.ai.setStrokeWidth(f);
    }

    public void setLineColor(int i) {
        this.af = i;
    }

    public void setYAxisValues(int i, int i2, int i3) {
        if (i > i2) {
            return;
        }
        this.aa = true;
        this.bf = i2;
        this.bd = i;
        this.bh = i3;
    }

    public void setAbove(float f) {
        this.f3003a = f;
    }

    public int a(float f) {
        Context context = this.l;
        if (context == null) {
            LogUtil.h("Track_SugChart", "dip2px mContext is null.");
            return 0;
        }
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public int e(float f) {
        Context context = this.l;
        if (context == null) {
            LogUtil.h("Track_SugChart", "sp2px mContext is null.");
            return 0;
        }
        return (int) ((f * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public void setXNums(int i, int i2, float f) {
        this.be = i;
        this.ba = i2;
        this.az = f;
    }

    public void a(boolean z) {
        this.z = z;
    }

    public void d(int i) {
        this.ad = i;
    }

    public void c(long j) {
        if (j > 0) {
            this.ac = true;
            Paint paint = new Paint(1);
            this.ar = paint;
            paint.setStyle(Paint.Style.FILL);
            Paint paint2 = new Paint(1);
            this.ao = paint2;
            paint2.setStyle(Paint.Style.STROKE);
            this.ao.setColor(-16777216);
        }
    }
}
