package com.huawei.health.suggestion.ui.fitness.module;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.Shader;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import defpackage.ffy;
import defpackage.fnp;
import defpackage.fqv;
import defpackage.koq;
import defpackage.moe;
import defpackage.nrr;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.UnitUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes4.dex */
public class SugChart extends View {

    /* renamed from: a, reason: collision with root package name */
    private int f3187a;
    private int aa;
    private Paint ab;
    private OnHelperGestureListener ac;
    private float ad;
    private int ae;
    private List<Path> af;
    private int ag;
    private Paint ah;
    private Path ai;
    private int aj;
    private float ak;
    private int[] al;
    private int am;
    private Paint an;
    private Paint ao;
    private int ap;
    private fqv aq;
    private Paint ar;
    private int as;
    private float at;
    private float au;
    private Paint av;
    private int aw;
    private int ax;
    private LinearGradient b;
    private float c;
    private Paint d;
    private float e;
    private float f;
    private Context g;
    private int h;
    private int i;
    private float j;
    private Paint k;
    private Paint l;
    private List<Path> m;
    private PointF n;
    private int o;
    private fqv p;
    private int q;
    private List<fqv> r;
    private Paint s;
    private float t;
    private float u;
    private boolean v;
    private boolean w;
    private int x;
    private boolean y;
    private int z;

    public interface OnHelperGestureListener {
        void scrollX(boolean z);

        void scrollY(boolean z);
    }

    public SugChart(Context context) {
        super(context);
        this.j = 0.0f;
        this.aj = -1;
        this.au = 15.0f;
        this.ap = Color.parseColor("#556A73");
        this.as = -1;
        this.v = false;
        this.x = Color.parseColor("#ffe9d1ba");
        this.w = false;
        this.ak = 1.0f;
        this.ae = 5;
        this.ag = -15823;
        this.n = new PointF(0.0f, 0.0f);
        this.r = new ArrayList();
        this.z = -12303292;
        this.h = 2;
        this.f = 0.0f;
        this.ai = new Path();
        this.af = new ArrayList();
        this.m = new ArrayList();
        this.s = new Paint(1);
        this.ar = new Paint(1);
        this.ah = new Paint(1);
        this.av = new Paint(1);
        this.ao = new Paint(1);
        this.an = new Paint(1);
        this.d = new Paint(1);
        this.ab = new Paint(1);
        this.l = new Paint(1);
        this.k = new Paint(1);
        this.d.setTextAlign(Paint.Align.CENTER);
        this.ab.setStrokeWidth(2.0f);
        this.ab.setStyle(Paint.Style.STROKE);
        this.l.setStrokeWidth(2.0f);
        this.l.setStyle(Paint.Style.STROKE);
        this.k.setStyle(Paint.Style.STROKE);
        this.av.setTextAlign(Paint.Align.CENTER);
        a(context);
    }

    public SugChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = 0.0f;
        this.aj = -1;
        this.au = 15.0f;
        this.ap = Color.parseColor("#556A73");
        this.as = -1;
        this.v = false;
        this.x = Color.parseColor("#ffe9d1ba");
        this.w = false;
        this.ak = 1.0f;
        this.ae = 5;
        this.ag = -15823;
        this.n = new PointF(0.0f, 0.0f);
        this.r = new ArrayList();
        this.z = -12303292;
        this.h = 2;
        this.f = 0.0f;
        this.ai = new Path();
        this.af = new ArrayList();
        this.m = new ArrayList();
        this.s = new Paint(1);
        this.ar = new Paint(1);
        this.ah = new Paint(1);
        this.av = new Paint(1);
        this.ao = new Paint(1);
        this.an = new Paint(1);
        this.d = new Paint(1);
        this.ab = new Paint(1);
        this.l = new Paint(1);
        this.k = new Paint(1);
        this.d.setTextAlign(Paint.Align.CENTER);
        this.ab.setStrokeWidth(2.0f);
        this.ab.setStyle(Paint.Style.STROKE);
        this.l.setStrokeWidth(2.0f);
        this.l.setStyle(Paint.Style.STROKE);
        this.k.setStyle(Paint.Style.STROKE);
        this.av.setTextAlign(Paint.Align.CENTER);
        a(context);
    }

    public SugChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = 0.0f;
        this.aj = -1;
        this.au = 15.0f;
        this.ap = Color.parseColor("#556A73");
        this.as = -1;
        this.v = false;
        this.x = Color.parseColor("#ffe9d1ba");
        this.w = false;
        this.ak = 1.0f;
        this.ae = 5;
        this.ag = -15823;
        this.n = new PointF(0.0f, 0.0f);
        this.r = new ArrayList();
        this.z = -12303292;
        this.h = 2;
        this.f = 0.0f;
        this.ai = new Path();
        this.af = new ArrayList();
        this.m = new ArrayList();
        this.s = new Paint(1);
        this.ar = new Paint(1);
        this.ah = new Paint(1);
        this.av = new Paint(1);
        this.ao = new Paint(1);
        this.an = new Paint(1);
        this.d = new Paint(1);
        this.ab = new Paint(1);
        this.l = new Paint(1);
        this.k = new Paint(1);
        this.d.setTextAlign(Paint.Align.CENTER);
        this.ab.setStrokeWidth(2.0f);
        this.ab.setStyle(Paint.Style.STROKE);
        this.l.setStrokeWidth(2.0f);
        this.l.setStyle(Paint.Style.STROKE);
        this.k.setStyle(Paint.Style.STROKE);
        this.av.setTextAlign(Paint.Align.CENTER);
        a(context);
    }

    private void a(Context context) {
        this.g = context;
        d();
        this.ap = context.getResources().getColor(R$color.textColorTertiary);
        this.as = context.getResources().getColor(R$color.textColorPrimaryInverse);
        this.x = context.getResources().getColor(R$color.textColorPrimaryInverse);
        this.z = context.getResources().getColor(R$color.colorTertiary);
        this.aw = ViewConfiguration.get(context).getScaledTouchSlop();
        this.av.setTextSize(this.au);
        this.ao.setColor(this.ap);
        this.d.setTextSize(this.e);
        this.d.setColor(this.f3187a);
        this.ar.setColor(context.getResources().getColor(R$color.textColorPrimary));
        this.ar.setAlpha(10);
    }

    private void d() {
        this.f = nrr.e(getContext(), 36.0f);
        this.u = nrr.e(getContext(), 20.0f);
        this.at = nrr.e(getContext(), 3.0f);
        this.au = nrr.e(getContext(), 12.0f);
        this.e = nrr.e(getContext(), 11.0f);
        this.ad = nrr.e(getContext(), 3.0f);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.j = i2 - getResources().getDimension(R.dimen._2131364965_res_0x7f0a0c65);
        if (this.h == 2) {
            this.at *= 2.0f;
        }
        float f = getContext().getResources().getDisplayMetrics().density;
        if (Math.abs(f) > 1.0E-6f) {
            this.ae = nrr.e(getContext(), getContext().getResources().getDimensionPixelOffset(R.dimen._2131364635_res_0x7f0a0b1b) / f);
        }
        this.ax = i;
        if (this.r.size() <= 0 || i <= 0) {
            return;
        }
        c();
        g();
    }

    private void c() {
        if (this.v) {
            return;
        }
        int i = this.q;
        if (i > 0 && this.h == 1) {
            int i2 = this.ax - (this.ae * 2);
            float f = (i2 - ((i + 1) * 2)) / i;
            this.f = f;
            this.u = (i2 - (f * this.r.size())) / this.r.size();
            return;
        }
        this.u = 2.0f;
        this.f = ((this.ax - (this.ae * 2)) - ((this.r.size() - 1) * 2.0f)) / this.r.size();
    }

    private void g() {
        if (b()) {
            this.y = false;
            e();
            for (int i = 0; i < this.r.size(); i++) {
                fqv fqvVar = this.r.get(i);
                PointF aEq_ = fqvVar.aEq_();
                aEb_(i, aEq_);
                aEq_.y = (this.j - this.c) - fqvVar.a();
                fqvVar.b(this.z).b(this.t).a(this.f);
            }
            j();
        }
    }

    private void j() {
        fqv fqvVar = this.aq;
        if (fqvVar != null) {
            float f = fqvVar.aEn_().y;
            float f2 = this.aq.aEq_().y;
            int[] iArr = this.al;
            this.b = new LinearGradient(0.0f, f, 0.0f, f2, iArr[0], iArr[1], Shader.TileMode.CLAMP);
        }
    }

    private void aEb_(int i, PointF pointF) {
        if (this.q > 0 && this.h == 1) {
            float f = i;
            pointF.x = this.ae + (this.u * (0.5f + f)) + (this.f * f);
        } else {
            float f2 = i;
            pointF.x = this.ae + (this.u * f2) + (this.f * f2);
        }
    }

    private boolean b() {
        return this.j > 0.0f && this.y;
    }

    private void e() {
        fqv fqvVar;
        if (this.t != 0.0f || (fqvVar = this.p) == null) {
            return;
        }
        float e = e((float) Math.ceil(fqvVar.q()));
        if (e != 0.0f) {
            this.t = ((((this.j - this.c) - fnp.c()) - nrr.e(getContext(), 4.0f)) - this.ad) / e;
        }
        float f = this.t;
        if (f != 0.0f) {
            a(((this.j - this.c) - 2.0f) / f);
        }
        int i = this.aa;
        if (i != 0) {
            this.t = ((this.j - this.c) - 2.0f) / i;
        }
    }

    private void a(float f) {
        if (this.p.h() == 0) {
            this.aa = ffy.d((float) Math.ceil(f));
        } else {
            this.aa = ffy.c(f);
        }
    }

    private float e(float f) {
        if (this.p.h() == 1) {
            return (this.p.q() / 1000.0f) * 1000.0f;
        }
        if (this.p.h() == 2) {
            return this.p.q();
        }
        return this.p.h() == 3 ? this.p.q() : f;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        super.onDraw(canvas);
        try {
            if (koq.c(this.r)) {
                aDP_(canvas);
            }
            aDR_(canvas);
            aDU_(canvas);
        } catch (RuntimeException e) {
            LogUtil.b("Suggestion_SugChart", "onDraw RuntimeException : ", LogAnonymous.b((Throwable) e));
        } catch (Exception e2) {
            LogUtil.b("Suggestion_SugChart", "onDraw Exception : ", LogAnonymous.b((Throwable) e2));
        }
    }

    private void aDP_(Canvas canvas) {
        if (this.h == 1) {
            aDX_(canvas);
        } else {
            aDY_(canvas);
        }
    }

    private void aDU_(Canvas canvas) {
        if (this.p == null) {
            return;
        }
        float j = j(this.ae);
        Paint.FontMetricsInt fontMetricsInt = this.d.getFontMetricsInt();
        float f = fontMetricsInt.descent - fontMetricsInt.ascent;
        int c = ffy.c(this.aa / 1000.0f);
        if (fnp.c(this.p)) {
            c = this.aa;
        }
        int i = c;
        float aDQ_ = aDQ_(canvas, j, f, i);
        float f2 = aDQ_ + ((this.j - aDQ_) / 2.0f);
        canvas.drawLine(this.ae, f2, this.ax - r1, f2, this.k);
        aDT_(canvas, j, f, i, f2);
    }

    private float aDQ_(Canvas canvas, float f, float f2, int i) {
        String e;
        if (this.p.h() == 2) {
            e = new SimpleDateFormat(getResources().getString(R.string._2130851569_res_0x7f0236f1), Locale.getDefault()).format(Integer.valueOf(i * 1000));
        } else {
            e = UnitUtil.e(i, 1, 0);
        }
        canvas.drawLine(this.ae, 2.0f, this.ax - r0, 2.0f, this.k);
        canvas.drawText(e, f, f2 + 2.0f, this.d);
        return 2.0f;
    }

    private void aDT_(Canvas canvas, float f, float f2, int i, float f3) {
        String e;
        if (this.p.h() == 2) {
            e = new SimpleDateFormat(getResources().getString(R.string._2130851569_res_0x7f0236f1), Locale.getDefault()).format(Integer.valueOf((i / 2) * 1000));
        } else if (this.p.h() == 0) {
            e = UnitUtil.e(moe.a(i / 2.0f), 1, 0);
        } else {
            e = UnitUtil.e(i / 2.0f, 1, 0);
        }
        canvas.drawText(e, f, f3 + f2, this.d);
    }

    private float j(float f) {
        if (LanguageUtil.bc(getContext().getApplicationContext())) {
            this.d.setTextAlign(Paint.Align.LEFT);
            return f;
        }
        this.d.setTextAlign(Paint.Align.RIGHT);
        return this.ax - this.ae;
    }

    private void aDX_(Canvas canvas) {
        for (int i = 0; i < this.r.size(); i++) {
            fqv fqvVar = this.r.get(i);
            g(i);
            aDV_(canvas, fqvVar);
            aDM_(canvas, fqvVar);
        }
        aEf_(canvas);
    }

    private void g(int i) {
        if (this.al != null) {
            this.s.setShader(this.b);
        } else {
            setExcelPaintColor(i);
        }
    }

    private void setExcelPaintColor(int i) {
        if (i != this.aj) {
            this.s.setColor(this.z);
        } else {
            this.s.setColor(this.g.getResources().getColor(R$color.common_colorAccent));
        }
    }

    private void aDV_(Canvas canvas, fqv fqvVar) {
        if (fqvVar.e() > 0.0f) {
            this.ah.setColor(this.ag);
            canvas.drawRect(fqvVar.aEp_(), this.s);
            canvas.drawPath(fqvVar.aEk_(), this.ah);
        } else {
            canvas.drawPath(fqvVar.aEl_(), this.s);
            canvas.drawPath(fqvVar.aEo_(), this.ar);
        }
    }

    private void aDM_(Canvas canvas, fqv fqvVar) {
        this.d.setTextAlign(Paint.Align.CENTER);
        if (fqvVar != null) {
            this.d.setColor(this.f3187a);
            PointF aEj_ = fqvVar.aEj_();
            if (TextUtils.isEmpty(fqvVar.c())) {
                return;
            }
            String c = fqvVar.c();
            aDZ_(canvas, fqvVar, aEj_, this.d.measureText(c, 0, c.length()));
        }
    }

    private boolean aDN_(Canvas canvas, Path path, int i) {
        fqv fqvVar;
        if (path == null) {
            fqvVar = null;
        } else {
            if (koq.b(this.r, i) || (fqvVar = this.r.get(i)) == null) {
                return true;
            }
            aEa_(path, i, aEe_(path, i, fqvVar));
        }
        aDM_(canvas, fqvVar);
        return false;
    }

    private void aDZ_(Canvas canvas, fqv fqvVar, PointF pointF, float f) {
        float f2 = f / 2.0f;
        if (pointF.x - f2 < 0.0f) {
            canvas.drawText(fqvVar.c(), f2, this.j + nrr.e(getContext(), 3.0f) + this.au, this.d);
            return;
        }
        if (pointF.x + f2 > this.ax) {
            canvas.drawText(fqvVar.c(), this.ax - f2, this.j + nrr.e(getContext(), 3.0f) + this.au, this.d);
            return;
        }
        canvas.drawText(fqvVar.c(), pointF.x, this.j + nrr.e(getContext(), 3.0f) + this.au, this.d);
    }

    private void aDW_(Canvas canvas, fqv fqvVar) {
        if (fqvVar.n() <= 0.0f) {
            return;
        }
        this.av.setColor(this.as);
        this.ao.setColor(this.am);
        fnp.aBs_(canvas, fqvVar, new fnp.c(this.ad, this.ax, this.ao, this.av));
    }

    private void aDY_(Canvas canvas) {
        this.ai.reset();
        h();
        if (aDK_(canvas)) {
            return;
        }
        aDJ_(canvas);
        aDO_(canvas);
        if (this.ad > 0.0f) {
            aDS_(canvas);
        }
        aEf_(canvas);
        a();
    }

    private void aDS_(Canvas canvas) {
        List<fqv> list = this.r;
        if (list == null) {
            LogUtil.b("Suggestion_SugChart", "Excel null.");
            return;
        }
        for (fqv fqvVar : list) {
            if (fqvVar != null && fqvVar.n() > 0.0f) {
                PointF aEj_ = fqvVar.aEj_();
                this.an.setColor(this.x);
                canvas.drawCircle(aEj_.x, aEj_.y, this.ad, this.an);
                Paint paint = new Paint(1);
                paint.setColor(-1);
                canvas.drawCircle(aEj_.x, aEj_.y, this.ad / 2.5f, paint);
            }
        }
    }

    private void a() {
        if (this.m.size() > 0) {
            this.ak = (this.ak + 1.0f) % 50.0f;
            postInvalidateDelayed(50L);
        }
    }

    private void aEf_(Canvas canvas) {
        int i = this.aj;
        if (i > -1) {
            if (koq.d(this.r, i)) {
                aDW_(canvas, this.r.get(this.aj));
            }
        } else if (koq.d(this.r, this.p.b())) {
            aDW_(canvas, this.r.get(this.p.b()));
        }
    }

    private void aDO_(Canvas canvas) {
        for (Path path : this.m) {
            this.l.setPathEffect(new DashPathEffect(new float[]{8.0f, 5.0f}, this.ak));
            canvas.drawPath(path, this.l);
        }
    }

    private void h() {
        this.ab.setColor(this.x);
        this.l.setColor(this.o);
    }

    private void aDJ_(Canvas canvas) {
        this.w = false;
        this.m.clear();
        for (int i = 0; i < this.af.size(); i++) {
            Path path = this.af.get(i);
            PathMeasure pathMeasure = new PathMeasure(path, false);
            float length = pathMeasure.getLength();
            float[] fArr = new float[2];
            pathMeasure.getPosTan(0.0f, fArr, null);
            float[] fArr2 = new float[2];
            pathMeasure.getPosTan(length, fArr2, null);
            if (length > 0.001f) {
                canvas.drawPath(path, this.ab);
                path.lineTo(fArr2[0], this.j);
                path.lineTo(fArr[0], this.j);
                path.close();
                Paint paint = this.s;
                float f = this.j;
                int[] iArr = this.al;
                paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, f, iArr[0], iArr[1], Shader.TileMode.CLAMP));
                canvas.drawPath(path, this.s);
            } else {
                fArr2[0] = fArr[0];
                fArr2[1] = fArr[1];
            }
            if (i < this.af.size() - 1) {
                Path path2 = new Path();
                path2.moveTo(fArr2[0], fArr2[1]);
                new PathMeasure(this.af.get(i + 1), false).getPosTan(0.0f, fArr, null);
                path2.lineTo(fArr[0], fArr[1]);
                this.m.add(path2);
            }
        }
    }

    private boolean aDK_(Canvas canvas) {
        this.af.clear();
        Path path = null;
        for (int i = 0; i < this.r.size(); i++) {
            if (!this.w) {
                path = new Path();
            }
            if (aDN_(canvas, path, i)) {
                return true;
            }
        }
        return false;
    }

    private void aEa_(Path path, int i, PointF pointF) {
        if (i == this.r.size() - 1 && this.w) {
            aEg_(path, i, pointF, new PathMeasure(path, false));
            this.af.add(path);
        }
    }

    private void aEg_(Path path, int i, PointF pointF, PathMeasure pathMeasure) {
        if (i <= 0 || pathMeasure.getLength() >= 0.001f) {
            return;
        }
        path.lineTo(pointF.x, pointF.y + 0.001f);
    }

    private PointF aEe_(Path path, int i, fqv fqvVar) {
        PointF aEj_ = fqvVar.aEj_();
        if (fqvVar.n() > 0.0f) {
            aEd_(path, aEj_);
        } else {
            aEc_(path, i);
        }
        return aEj_;
    }

    private void aEc_(Path path, int i) {
        if (!path.isEmpty()) {
            PathMeasure pathMeasure = new PathMeasure(path, false);
            if (i > 0 && pathMeasure.getLength() < 0.001f) {
                PointF aEj_ = this.r.get(i - 1).aEj_();
                path.lineTo(aEj_.x, aEj_.y + 0.001f);
            }
            this.af.add(path);
        }
        this.w = false;
    }

    private void aEd_(Path path, PointF pointF) {
        if (!this.w) {
            path.moveTo(pointF.x, pointF.y);
            this.w = true;
        } else {
            path.lineTo(pointF.x, pointF.y);
        }
    }

    private void aDR_(Canvas canvas) {
        this.k.setColor(this.i);
        this.k.setStrokeWidth(2.0f);
        int i = this.ae;
        float f = this.j;
        canvas.drawLine(i, f, this.ax - i, f, this.k);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        if (this.r.size() > 0) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.n.x = motionEvent.getX();
                this.n.y = motionEvent.getY();
            } else if (action == 1) {
                fnp.aBv_(motionEvent, this.n, this.aw, this.ac);
                this.aj = aDL_(new PointF(motionEvent.getX(), motionEvent.getY()));
                invalidate();
            }
        }
        return true;
    }

    public void c(OnHelperGestureListener onHelperGestureListener) {
        this.ac = onHelperGestureListener;
    }

    private int aDL_(PointF pointF) {
        for (int i = 0; i < this.r.size(); i++) {
            fqv fqvVar = this.r.get(i);
            if (fnp.aBu_(pointF, fqvVar, fqvVar.aEq_())) {
                return i;
            }
        }
        return -1;
    }

    public void d(List<fqv> list) {
        if (koq.b(list)) {
            return;
        }
        this.t = 0.0f;
        this.y = true;
        this.w = false;
        this.aj = 0;
        this.r.clear();
        this.p = list.get(0);
        this.aq = list.get(0);
        c(list);
        if (this.ax > 0) {
            c();
            g();
        }
        postInvalidate();
    }

    private void c(List<fqv> list) {
        e(list);
        this.aj = fnp.a(list);
        b(list);
    }

    private void b(List<fqv> list) {
        for (int i = 0; i < list.size(); i++) {
            fqv fqvVar = list.get(i);
            fqvVar.a(this.f);
            PointF aEq_ = fqvVar.aEq_();
            if (aEq_ != null) {
                aEq_.x = (this.u * (i + 1)) + (this.f * i);
            }
            fqvVar.b(this.z);
            this.r.add(fqvVar);
        }
    }

    private void e(List<fqv> list) {
        for (fqv fqvVar : list) {
            if (ffy.c(this.p, this.aq)) {
                this.p = fnp.a(fqvVar, this.p);
                this.aq = fnp.c(fqvVar, this.aq);
            }
        }
    }

    public void f(int i) {
        this.z = i;
        this.x = i;
    }

    public void d(int... iArr) {
        Paint paint = this.ab;
        float f = this.j;
        int[] iArr2 = this.al;
        paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, f, iArr2[0], iArr2[1], Shader.TileMode.CLAMP));
    }

    public void d(int i) {
        this.h = i;
    }

    public void d(float f) {
        this.e = f;
        this.d.setTextSize(f);
        g();
    }

    public void a(int i) {
        this.f3187a = i;
        this.d.setColor(i);
    }

    public void b(int... iArr) {
        this.al = iArr;
    }

    public void b(int i) {
        this.v = false;
        this.q = i;
    }

    public void c(float f) {
        this.ad = f;
    }

    public void b(float f) {
        this.ab.setStrokeWidth(f);
        this.l.setStrokeWidth(f);
    }

    public void i(int i) {
        this.am = i;
    }

    public void h(int i) {
        this.ag = i;
    }

    public void c(int i) {
        this.o = i;
        this.l.setColor(i);
    }

    public void e(int i) {
        this.i = i;
        this.k.setColor(i);
    }
}
