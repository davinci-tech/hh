package defpackage;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import java.text.DecimalFormat;

/* loaded from: classes4.dex */
public class fqv implements Cloneable {

    /* renamed from: a, reason: collision with root package name */
    private float f12613a;
    private Path b;
    private float c;
    private int d;
    private int e;
    private boolean f;
    private float g;
    private float h;
    private float i;
    private boolean j;
    private Path k;
    private float l;
    private String m;
    private String n;
    private int o;
    private float p;
    private Path q;
    private String r;
    private float s;
    private PointF t;
    private float v;
    private float y;

    public fqv(float f, String str, int i) {
        this(0.0f, f, str);
        this.d = i;
    }

    public fqv(float f, float f2, String str) {
        this(f, f2, str, -7829368);
    }

    public fqv(float f, float f2, String str, int i) {
        this(f, f2, "", str, -7829368);
    }

    public fqv(float f, float f2, String str, String str2, int i) {
        this.o = 0;
        this.t = new PointF();
        this.p = 0.0f;
        this.q = new Path();
        this.b = new Path();
        this.k = new Path();
        this.y = f2;
        this.g = f;
        this.c = f2 - f;
        this.t.y = f;
        this.n = str2;
        this.r = str;
        this.e = i;
        this.m = new DecimalFormat("##").format(this.c);
    }

    public Path aEl_() {
        if (this.c > 0.0f) {
            this.b = new Path();
            this.j = aEh_(this.b, this.c, this.j, aEm_(this.c), aEi_(this.c));
        }
        return this.b;
    }

    public RectF aEp_() {
        return new RectF(this.t.x, this.t.y - this.p, this.t.x + this.v, this.t.y);
    }

    public Path aEo_() {
        if (this.p > 0.0f) {
            this.q = new Path();
            this.f = aEh_(this.q, this.p, this.f, aEm_(this.p), aEi_(this.p));
        }
        return this.q;
    }

    public RectF aEm_(float f) {
        return new RectF(this.t.x, (this.t.y - f) + (this.v / 2.0f), this.t.x + this.v, this.t.y);
    }

    public RectF aEi_(float f) {
        return new RectF(this.t.x, this.t.y - f, this.t.x + this.v, (this.t.y - f) + this.v);
    }

    public Path aEk_() {
        float f = this.c - this.p;
        if (f > 0.0f) {
            Path path = new Path();
            this.k = path;
            aEh_(path, f, false, new RectF(this.t.x, (this.t.y - this.c) + (this.v / 2.0f), this.t.x + this.v, this.t.y - this.p), aEi_(this.c));
        }
        return this.k;
    }

    private boolean aEh_(Path path, float f, boolean z, RectF rectF, RectF rectF2) {
        if (f > this.v / 2.0f) {
            Path path2 = new Path();
            path2.moveTo(rectF.left, rectF.top);
            path2.lineTo(rectF.left, rectF.bottom);
            path2.lineTo(rectF.right, rectF.bottom);
            path2.lineTo(rectF.right, rectF.top);
            path.addPath(path2);
            path.addArc(rectF2, 180.0f, 180.0f);
            return true;
        }
        rectF2.bottom -= this.v - (f * 2.0f);
        path.addArc(rectF2, 180.0f, 180.0f);
        path.close();
        return true;
    }

    public PointF aEj_() {
        return new PointF(r(), this.t.y - this.c);
    }

    public PointF aEn_() {
        return new PointF(r(), this.t.y - this.p);
    }

    public float x() {
        return this.v;
    }

    public fqv a(float f) {
        this.v = f;
        return this;
    }

    public float n() {
        return this.c;
    }

    public float q() {
        float f = this.c;
        float f2 = this.p;
        return f > f2 ? f : f2;
    }

    public fqv b(float f) {
        this.j = false;
        this.f = false;
        this.p *= f;
        this.c *= f;
        return this;
    }

    public PointF aEq_() {
        return this.t;
    }

    public float r() {
        PointF pointF = this.t;
        if (pointF != null) {
            this.i = pointF.x + (this.v / 2.0f);
        }
        return this.i;
    }

    public fqv b(int i) {
        this.e = i;
        return this;
    }

    public float s() {
        return this.h;
    }

    public fqv j(float f) {
        this.h = f;
        return this;
    }

    public String c() {
        return this.n;
    }

    public float y() {
        return this.y;
    }

    public fqv i(float f) {
        this.y = f;
        this.c = f - this.g;
        this.m = new DecimalFormat("##.#").format(this.y);
        return this;
    }

    public float a() {
        return this.g;
    }

    public int b() {
        return this.d;
    }

    public String j() {
        return this.m;
    }

    public fqv c(String str) {
        this.m = str;
        return this;
    }

    public float e() {
        return this.c - this.p;
    }

    public float f() {
        return this.p;
    }

    public fqv h(float f) {
        this.s = f;
        this.p = f;
        return this;
    }

    public float o() {
        return this.c;
    }

    public fqv d(float f) {
        this.f12613a = f;
        this.c = f;
        return this;
    }

    public float p() {
        return this.l;
    }

    public void g(float f) {
        this.l = f;
    }

    public int h() {
        return this.o;
    }

    public void a(int i) {
        this.o = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object clone() {
        /*
            r5 = this;
            r0 = 0
            java.lang.Object r1 = super.clone()     // Catch: java.lang.CloneNotSupportedException -> L21
            boolean r1 = r1 instanceof defpackage.fqv     // Catch: java.lang.CloneNotSupportedException -> L21
            if (r1 == 0) goto L33
            java.lang.Object r1 = super.clone()     // Catch: java.lang.CloneNotSupportedException -> L21
            fqv r1 = (defpackage.fqv) r1     // Catch: java.lang.CloneNotSupportedException -> L21
            android.graphics.PointF r0 = new android.graphics.PointF     // Catch: java.lang.CloneNotSupportedException -> L1f
            android.graphics.PointF r2 = r5.t     // Catch: java.lang.CloneNotSupportedException -> L1f
            float r2 = r2.x     // Catch: java.lang.CloneNotSupportedException -> L1f
            android.graphics.PointF r3 = r5.t     // Catch: java.lang.CloneNotSupportedException -> L1f
            float r3 = r3.y     // Catch: java.lang.CloneNotSupportedException -> L1f
            r0.<init>(r2, r3)     // Catch: java.lang.CloneNotSupportedException -> L1f
            r1.t = r0     // Catch: java.lang.CloneNotSupportedException -> L1f
            goto L32
        L1f:
            r0 = move-exception
            goto L25
        L21:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L25:
            java.lang.String r0 = health.compact.a.LogAnonymous.b(r0)
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r2 = "Suggestion_SugExcel"
            com.huawei.hwlogsmodel.LogUtil.b(r2, r0)
        L32:
            r0 = r1
        L33:
            if (r0 != 0) goto L4f
            fqv r0 = new fqv
            float r1 = r5.y
            java.lang.String r2 = r5.n
            int r3 = r5.d
            r0.<init>(r1, r2, r3)
            android.graphics.PointF r1 = new android.graphics.PointF
            android.graphics.PointF r2 = r5.t
            float r2 = r2.x
            android.graphics.PointF r3 = r5.t
            float r3 = r3.y
            r1.<init>(r2, r3)
            r0.t = r1
        L4f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fqv.clone():java.lang.Object");
    }
}
