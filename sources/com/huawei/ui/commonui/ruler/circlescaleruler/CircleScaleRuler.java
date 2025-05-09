package com.huawei.ui.commonui.ruler.circlescaleruler;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$styleable;
import defpackage.nqg;
import defpackage.nsf;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class CircleScaleRuler extends View {

    /* renamed from: a, reason: collision with root package name */
    private Drawable f8940a;
    private float aa;
    private float ab;
    private float ac;
    private float ad;
    private int ae;
    private int af;
    private float ag;
    private float ah;
    private float ai;
    private float aj;
    private float ak;
    private final List<Integer> al;
    private Paint am;
    private Paint an;
    private float ao;
    private int ap;
    private int aq;
    private float ar;
    private float as;
    private float at;
    private final List<String> au;
    private Rect av;
    private Path aw;
    private Paint ax;
    private RectF ay;
    private float az;
    private float b;
    private float ba;
    private float bb;
    private float bc;
    private int bd;
    private Paint be;
    private float bf;
    private float bg;
    private RectF bh;
    private String bi;
    private float bj;
    private float bk;
    private int bl;
    private Paint bm;
    private float bn;
    private Path bo;
    private RectF bp;
    private Rect bq;
    private float br;
    private float bv;
    private float bw;
    private float c;
    private Drawable d;
    private float e;
    private boolean f;
    private boolean g;
    private float h;
    private float i;
    private float j;
    private Paint k;
    private float l;
    private float m;
    private int n;
    private PaintFlagsDrawFilter o;
    private float p;
    private float q;
    private int r;
    private int s;
    private int[] t;
    private float u;
    private float[] v;
    private Paint w;
    private float x;
    private RectF y;
    private int z;

    private float c(float f, float f2) {
        return (float) (((f / 2.0f) * 180.0f) / (f2 * 3.141592653589793d));
    }

    public CircleScaleRuler(Context context) {
        super(context);
        this.au = new ArrayList();
        this.al = new ArrayList();
        this.bw = 1.0f;
        cFs_(context, null);
    }

    public CircleScaleRuler(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.au = new ArrayList();
        this.al = new ArrayList();
        this.bw = 1.0f;
        cFs_(context, attributeSet);
    }

    public CircleScaleRuler(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.au = new ArrayList();
        this.al = new ArrayList();
        this.bw = 1.0f;
        cFs_(context, attributeSet);
    }

    private void cFs_(Context context, AttributeSet attributeSet) {
        cFx_(context, attributeSet);
        Paint paint = new Paint();
        this.k = paint;
        cFu_(paint, this.m, this.n);
        Paint paint2 = new Paint();
        this.w = paint2;
        cFt_(paint2, this.ab * this.bw);
        this.y = new RectF();
        Paint paint3 = new Paint();
        this.ax = paint3;
        cFw_(paint3, this.bc, this.ap);
        this.ay = new RectF();
        this.av = new Rect();
        this.aw = new Path();
        Paint paint4 = new Paint();
        this.am = paint4;
        cFu_(paint4, this.az, this.af);
        Paint paint5 = new Paint();
        this.an = paint5;
        cFu_(paint5, this.aj, this.ae);
        Paint paint6 = new Paint();
        this.be = paint6;
        cFu_(paint6, this.bv * this.bw, this.bd);
        this.bh = new RectF();
        Paint paint7 = new Paint();
        this.bm = paint7;
        cFw_(paint7, this.br, this.bl);
        this.bp = new RectF();
        this.bq = new Rect();
        this.bo = new Path();
        this.aa = this.q - this.ac;
        this.bb = this.ao - this.ba;
        this.ar = this.ah - this.as;
        this.bk = this.bf - this.bj;
        this.o = new PaintFlagsDrawFilter(0, 3);
        this.t = new int[]{this.z, this.s, this.r};
        this.v = new float[]{this.ad, this.p, this.u};
    }

    private void cFx_(Context context, AttributeSet attributeSet) {
        if (context == null) {
            ReleaseLogUtil.a("CircleScaleRuler", "initTypedArray context is null attributeSet", attributeSet);
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CircleScaleRuler);
        if (obtainStyledAttributes == null) {
            ReleaseLogUtil.a("CircleScaleRuler", "initTypedArray typedArray is null context", context, " attributeSet ", attributeSet);
            return;
        }
        this.f = obtainStyledAttributes.getBoolean(R$styleable.CircleScaleRuler_isMini, true);
        this.n = obtainStyledAttributes.getColor(R$styleable.CircleScaleRuler_miniCursorColor, nsf.c(R$color.circle_scale_mini_cursor));
        this.m = obtainStyledAttributes.getDimension(R$styleable.CircleScaleRuler_miniCursorWidth, nsf.a(R.dimen._2131363003_res_0x7f0a04bb));
        this.l = obtainStyledAttributes.getDimension(R$styleable.CircleScaleRuler_miniCursorHeight, nsf.a(R.dimen._2131362869_res_0x7f0a0435));
        this.f8940a = obtainStyledAttributes.getDrawable(R$styleable.CircleScaleRuler_background);
        this.d = obtainStyledAttributes.getDrawable(R$styleable.CircleScaleRuler_cursor);
        this.c = obtainStyledAttributes.getFloat(R$styleable.CircleScaleRuler_cursorAngle, 0.0f);
        this.i = obtainStyledAttributes.getDimension(R$styleable.CircleScaleRuler_cursorRadius, nsf.a(R.dimen._2131363089_res_0x7f0a0511));
        this.ac = obtainStyledAttributes.getFloat(R$styleable.CircleScaleRuler_outRingStartAngle, 195.0f);
        this.q = obtainStyledAttributes.getFloat(R$styleable.CircleScaleRuler_outRingEndAngle, 345.0f);
        this.x = obtainStyledAttributes.getDimension(R$styleable.CircleScaleRuler_outRingRadius, nsf.a(R.dimen._2131362868_res_0x7f0a0434));
        this.ab = obtainStyledAttributes.getDimension(R$styleable.CircleScaleRuler_outRingWidth, nsf.a(R.dimen._2131363122_res_0x7f0a0532));
        this.z = obtainStyledAttributes.getColor(R$styleable.CircleScaleRuler_outRingStartColor, nsf.c(R$color.circle_scale_ring_start));
        this.r = obtainStyledAttributes.getColor(R$styleable.CircleScaleRuler_outRingEndColor, nsf.c(R$color.circle_scale_ring_end));
        this.s = obtainStyledAttributes.getColor(R$styleable.CircleScaleRuler_outRingColor, nsf.c(R$color.circle_scale_ring_center));
        this.ad = obtainStyledAttributes.getDimension(R$styleable.CircleScaleRuler_outRingStartColorProportion, 0.0f);
        this.u = obtainStyledAttributes.getDimension(R$styleable.CircleScaleRuler_outRingEndColorProportion, 1.0f);
        this.p = obtainStyledAttributes.getDimension(R$styleable.CircleScaleRuler_outRingColorProportion, 0.5f);
        this.ba = obtainStyledAttributes.getFloat(R$styleable.CircleScaleRuler_rulerTextStartAngle, 200.0f);
        this.ao = obtainStyledAttributes.getFloat(R$styleable.CircleScaleRuler_rulerTextEndAngle, 340.0f);
        this.at = obtainStyledAttributes.getDimension(R$styleable.CircleScaleRuler_rulerTextRadius, nsf.a(R.dimen._2131363129_res_0x7f0a0539));
        this.bc = obtainStyledAttributes.getDimension(R$styleable.CircleScaleRuler_rulerTextSize, nsf.a(R.dimen._2131362869_res_0x7f0a0435));
        this.ap = obtainStyledAttributes.getColor(R$styleable.CircleScaleRuler_rulerTextColor, nsf.c(R$color.circle_scale_ruler_text));
        this.aq = obtainStyledAttributes.getInt(R$styleable.CircleScaleRuler_rulerCount, 35);
        this.as = obtainStyledAttributes.getFloat(R$styleable.CircleScaleRuler_rulerStartAngle, 195.625f);
        this.ah = obtainStyledAttributes.getFloat(R$styleable.CircleScaleRuler_rulerEndAngle, 344.375f);
        this.ak = obtainStyledAttributes.getDimension(R$styleable.CircleScaleRuler_rulerRadius, nsf.a(R.dimen._2131363116_res_0x7f0a052c));
        this.ag = obtainStyledAttributes.getDimension(R$styleable.CircleScaleRuler_rulerHeight, nsf.a(R.dimen._2131363062_res_0x7f0a04f6));
        this.az = obtainStyledAttributes.getDimension(R$styleable.CircleScaleRuler_rulerWidth, nsf.a(R.dimen._2131362858_res_0x7f0a042a));
        this.af = obtainStyledAttributes.getColor(R$styleable.CircleScaleRuler_rulerColor, nsf.c(R$color.circle_scale_ruler));
        this.ai = obtainStyledAttributes.getDimension(R$styleable.CircleScaleRuler_rulerHighlightHeight, nsf.a(R.dimen._2131363062_res_0x7f0a04f6));
        this.aj = obtainStyledAttributes.getDimension(R$styleable.CircleScaleRuler_rulerHighlightWidth, nsf.a(R.dimen._2131362859_res_0x7f0a042b));
        this.ae = obtainStyledAttributes.getColor(R$styleable.CircleScaleRuler_rulerHighlightColor, nsf.c(R$color.circle_scale_ruler_highlight));
        this.g = obtainStyledAttributes.getBoolean(R$styleable.CircleScaleRuler_isScopeEdge, false);
        this.bj = obtainStyledAttributes.getFloat(R$styleable.CircleScaleRuler_scopeStartAngle, 270.0f);
        this.bf = obtainStyledAttributes.getFloat(R$styleable.CircleScaleRuler_scopeEndAngle, 270.0f);
        this.bg = obtainStyledAttributes.getDimension(R$styleable.CircleScaleRuler_scopeRadius, nsf.a(R.dimen._2131363118_res_0x7f0a052e));
        this.bv = obtainStyledAttributes.getDimension(R$styleable.CircleScaleRuler_scopeWidth, nsf.a(R.dimen._2131362869_res_0x7f0a0435));
        this.bd = obtainStyledAttributes.getColor(R$styleable.CircleScaleRuler_scopeColor, nsf.c(R$color.circle_scale_scope));
        this.bn = obtainStyledAttributes.getDimension(R$styleable.CircleScaleRuler_scopeTextRadius, nsf.a(R.dimen._2131363092_res_0x7f0a0514));
        this.br = obtainStyledAttributes.getDimension(R$styleable.CircleScaleRuler_scopeTextSize, nsf.a(R.dimen._2131362869_res_0x7f0a0435));
        this.bl = obtainStyledAttributes.getColor(R$styleable.CircleScaleRuler_scopeTextColor, nsf.c(R$color.circle_scale_scope_text));
        this.bi = obtainStyledAttributes.getString(R$styleable.CircleScaleRuler_scopeText);
        obtainStyledAttributes.recycle();
    }

    private void cFv_(Paint paint, Paint.Style style) {
        paint.setAntiAlias(true);
        paint.setStyle(style);
    }

    private void cFt_(Paint paint, float f) {
        cFv_(paint, Paint.Style.STROKE);
        paint.setStrokeWidth(f);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    private void cFu_(Paint paint, float f, int i) {
        cFt_(paint, f);
        paint.setColor(i);
    }

    private void cFw_(Paint paint, float f, int i) {
        cFv_(paint, Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(f);
        paint.setColor(i);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.h = getMeasuredWidth();
        this.j = getMeasuredHeight();
        canvas.setDrawFilter(this.o);
        if (this.f) {
            float f = this.h / 2.0f;
            cFn_(canvas, f, this.j);
            cFm_(canvas, f, this.j);
            return;
        }
        if (this.f8940a == null) {
            ReleaseLogUtil.a("CircleScaleRuler", "onDraw mBackground is null");
            return;
        }
        cFk_(canvas);
        Rect bounds = this.f8940a.getBounds();
        if (bounds == null) {
            ReleaseLogUtil.a("CircleScaleRuler", "onDraw rect is null");
            return;
        }
        this.b = bounds.left + (bounds.width() / 2.0f);
        float f2 = bounds.bottom;
        this.e = f2;
        cFn_(canvas, this.b, f2);
        cFp_(canvas);
        cFo_(canvas);
        cFq_(canvas);
        cFr_(canvas);
        cFl_(canvas);
    }

    private void cFm_(Canvas canvas, float f, float f2) {
        double radians = Math.toRadians(this.c + 270.0f);
        float f3 = this.l / 2.0f;
        float f4 = this.x * this.bw;
        double d = f;
        double d2 = f4 - f3;
        double d3 = f4 + f3;
        double d4 = f2;
        canvas.drawLine((float) ((Math.cos(radians) * d2) + d), (float) ((Math.sin(radians) * d2) + d4), (float) (d + (Math.cos(radians) * d3)), (float) (d4 + (Math.sin(radians) * d3)), this.k);
        canvas.save();
    }

    private void cFk_(Canvas canvas) {
        float intrinsicWidth = this.f8940a.getIntrinsicWidth();
        float intrinsicHeight = this.f8940a.getIntrinsicHeight();
        if (intrinsicWidth <= 0.0f || intrinsicHeight <= 0.0f) {
            ReleaseLogUtil.a("CircleScaleRuler", "drawBackground intrinsicWidth ", Float.valueOf(intrinsicWidth), " intrinsicHeight ", Float.valueOf(intrinsicHeight));
            return;
        }
        float min = Math.min(this.h / intrinsicWidth, this.j / intrinsicHeight);
        float f = (intrinsicWidth * min) / 2.0f;
        float f2 = (intrinsicHeight * min) / 2.0f;
        float f3 = this.h / 2.0f;
        float f4 = this.j / 2.0f;
        this.f8940a.setBounds((int) (f3 - f), (int) (f4 - f2), (int) (f3 + f), (int) (f4 + f2));
        this.f8940a.draw(canvas);
        canvas.save();
    }

    private void cFn_(Canvas canvas, float f, float f2) {
        float f3 = this.x * this.bw;
        this.y.left = f - f3;
        this.y.top = f2 - f3;
        this.y.right = f + f3;
        this.y.bottom = f2 + f3;
        this.w.setShader(new LinearGradient(this.y.left, this.y.bottom, this.y.right, this.y.bottom, this.t, this.v, Shader.TileMode.CLAMP));
        canvas.drawArc(this.y, this.ac, this.aa, false, this.w);
        canvas.save();
    }

    private void cFp_(Canvas canvas) {
        this.ay.left = this.b - this.at;
        this.ay.top = this.e - this.at;
        this.ay.right = this.b + this.at;
        this.ay.bottom = this.e + this.at;
        int size = this.au.size();
        for (int i = 0; i < size; i++) {
            String str = this.au.get(i);
            if (TextUtils.isEmpty(str)) {
                ReleaseLogUtil.a("CircleScaleRuler", "drawRulerText text ", str);
            } else {
                this.ax.getTextBounds(str, 0, str.length(), this.av);
                float c = (this.ba + (i * (this.bb / (size - 1)))) - c(this.av.width(), this.at - this.av.height());
                this.aw.reset();
                this.aw.addArc(this.ay, c, 360.0f - c);
                canvas.drawTextOnPath(str, this.aw, 0.0f, 0.0f, this.ax);
            }
        }
        canvas.save();
    }

    private void cFo_(Canvas canvas) {
        float f;
        float f2;
        float f3;
        int i;
        float f4;
        CircleScaleRuler circleScaleRuler;
        CircleScaleRuler circleScaleRuler2 = this;
        float f5 = circleScaleRuler2.ak;
        float f6 = circleScaleRuler2.bw;
        float f7 = f5 * f6;
        float f8 = circleScaleRuler2.ag;
        float f9 = circleScaleRuler2.ai;
        float f10 = circleScaleRuler2.ar / (circleScaleRuler2.aq - 1);
        int i2 = 0;
        while (i2 < circleScaleRuler2.aq) {
            double radians = Math.toRadians(circleScaleRuler2.as + (i2 * f10));
            double d = f7;
            double cos = circleScaleRuler2.b + (Math.cos(radians) * d);
            double sin = circleScaleRuler2.e + (Math.sin(radians) * d);
            if (circleScaleRuler2.al.contains(Integer.valueOf(i2))) {
                f3 = f9;
                i = i2;
                double d2 = (f9 * f6) + f7;
                f4 = f10;
                f = f7;
                f2 = f6;
                canvas.drawLine((float) cos, (float) sin, (float) (circleScaleRuler2.b + (Math.cos(radians) * d2)), (float) (circleScaleRuler2.e + (Math.sin(radians) * d2)), circleScaleRuler2.an);
                circleScaleRuler = circleScaleRuler2;
            } else {
                f = f7;
                f2 = f6;
                f3 = f9;
                i = i2;
                f4 = f10;
                double d3 = (f8 * f2) + f;
                circleScaleRuler = this;
                canvas.drawLine((float) cos, (float) sin, (float) ((Math.cos(radians) * d3) + circleScaleRuler2.b), (float) (circleScaleRuler2.e + (Math.sin(radians) * d3)), circleScaleRuler.am);
            }
            i2 = i + 1;
            circleScaleRuler2 = circleScaleRuler;
            f9 = f3;
            f10 = f4;
            f6 = f2;
            f7 = f;
        }
        canvas.save();
    }

    private void cFq_(Canvas canvas) {
        float f = this.bk;
        if (f <= 0.0f) {
            ReleaseLogUtil.a("CircleScaleRuler", "drawScope mScopeSweepAngle ", Float.valueOf(f));
            return;
        }
        float f2 = this.bg * this.bw;
        this.bh.left = this.b - f2;
        this.bh.top = this.e - f2;
        this.bh.right = this.b + f2;
        this.bh.bottom = this.e + f2;
        float c = this.g ? c(this.bv * this.bw, f2) : 0.0f;
        canvas.drawArc(this.bh, this.bj + c, this.bk - (c * 2.0f), false, this.be);
        canvas.save();
    }

    private void cFr_(Canvas canvas) {
        if (TextUtils.isEmpty(this.bi)) {
            ReleaseLogUtil.a("CircleScaleRuler", "drawScopeText mScopeText ", this.bi);
            return;
        }
        this.bp.left = this.b - this.bn;
        this.bp.top = this.e - this.bn;
        this.bp.right = this.b + this.bn;
        this.bp.bottom = this.e + this.bn;
        Paint paint = this.bm;
        String str = this.bi;
        paint.getTextBounds(str, 0, str.length(), this.bq);
        float c = (this.bj + (this.bk / 2.0f)) - c(this.bq.width(), this.bn - this.bq.height());
        this.bo.reset();
        this.bo.addArc(this.bp, c, 360.0f - c);
        canvas.drawTextOnPath(this.bi, this.bo, 0.0f, 0.0f, this.bm);
        canvas.save();
    }

    private void cFl_(Canvas canvas) {
        Drawable drawable = this.d;
        if (drawable == null) {
            ReleaseLogUtil.a("CircleScaleRuler", "drawCursor mCursor is null");
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = this.d.getIntrinsicHeight();
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            ReleaseLogUtil.a("CircleScaleRuler", "drawCursor intrinsicWidth ", Integer.valueOf(intrinsicWidth), " intrinsicHeight ", Integer.valueOf(intrinsicHeight));
            return;
        }
        this.d.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        float f = intrinsicWidth / 2.0f;
        float f2 = intrinsicHeight;
        canvas.translate(this.b - f, this.e - f2);
        canvas.rotate(this.c, f, f2);
        canvas.translate(0.0f, -(this.i * this.bw));
        this.d.draw(canvas);
        canvas.restore();
        canvas.save();
    }

    public void setCircleScaleRulerData(nqg nqgVar) {
        if (nqgVar == null) {
            ReleaseLogUtil.a("CircleScaleRuler", "setCircleScaleRulerData circleScaleRulerData is null");
            return;
        }
        float m = nqgVar.m();
        if (m > 0.0f) {
            this.bw = m;
            cFt_(this.w, this.ab * m);
            cFu_(this.be, this.bv * this.bw, this.bd);
        }
        boolean k = nqgVar.k();
        this.f = k;
        if (k) {
            int c = nsf.c(nqgVar.c());
            this.n = c;
            cFu_(this.k, this.m, c);
        } else {
            this.bj = nqgVar.o();
            float f = nqgVar.f();
            this.bf = f;
            this.bk = f - this.bj;
            this.bi = nqgVar.n();
            this.au.clear();
            this.au.addAll(nqgVar.h());
            this.al.clear();
            this.al.addAll(nqgVar.j());
            Drawable cFy_ = nqgVar.cFy_();
            if (cFy_ == null) {
                ReleaseLogUtil.a("CircleScaleRuler", "setCircleScaleRulerData background is null");
            } else {
                this.f8940a = cFy_;
            }
            Drawable cFz_ = nqgVar.cFz_();
            if (cFz_ == null) {
                ReleaseLogUtil.a("CircleScaleRuler", "setCircleScaleRulerData cursor is null");
            } else {
                this.d = cFz_;
            }
        }
        this.z = nsf.c(nqgVar.i());
        this.s = nsf.c(nqgVar.b());
        int c2 = nsf.c(nqgVar.g());
        this.r = c2;
        this.t = new int[]{this.z, this.s, c2};
        this.c = nqgVar.a();
        invalidate();
    }
}
