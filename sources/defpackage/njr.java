package defpackage;

import android.graphics.LinearGradient;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Shader;

/* loaded from: classes6.dex */
public class njr {
    private LinearGradient ac;
    private LinearGradient e;
    private LinearGradient f;
    private LinearGradient i;
    private LinearGradient l;
    private LinearGradient p;
    private LinearGradient q;
    private PointF u;
    private Path x;
    private LinearGradient z;
    private float w = 437.0f;
    private float y = 501.0f;
    private int m = 868255472;
    private int g = 864320759;
    private int s = 872267365;
    private int o = 872255062;
    private int ab = 862505936;
    private int v = 860537305;

    /* renamed from: a, reason: collision with root package name */
    private int f15337a = 866117244;
    private int b = 862374758;
    private int j = 1301911961;
    private int n = -4159760;
    private int k = -8094473;
    private int r = -147867;
    private int t = -160170;
    private int ad = -9909296;
    private int aa = -11877927;
    private int h = -6297988;
    private int d = -10040474;
    private PointF ai = new PointF(218.5f, -21.381f);
    private PointF c = new PointF(218.5f, 492.83408f);

    public njr() {
        Path path = new Path();
        this.x = path;
        path.moveTo(63.997f, 64.143f);
        this.x.cubicTo(149.327f, -21.381f, 287.673f, -21.381f, 373.003f, 64.143f);
        this.x.cubicTo(458.332f, 149.666f, 458.332f, 288.328f, 373.003f, 373.851f);
        this.x.cubicTo(287.673f, 459.375f, 234.885f, 485.212f, 228.763f, 489.662f);
        this.x.cubicTo(222.642f, 494.113f, 214.358f, 494.113f, 208.237f, 489.662f);
        this.x.cubicTo(202.115f, 485.212f, 149.327f, 459.375f, 63.997f, 373.851f);
        this.x.cubicTo(-21.332f, 288.328f, -21.332f, 149.666f, 63.997f, 64.143f);
        this.x.close();
        this.f = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, this.m, this.g, Shader.TileMode.CLAMP);
        this.p = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, this.s, this.o, Shader.TileMode.CLAMP);
        this.z = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, this.ab, this.v, Shader.TileMode.CLAMP);
        this.e = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, this.f15337a, this.b, Shader.TileMode.CLAMP);
        this.l = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, this.n, this.k, Shader.TileMode.CLAMP);
        this.q = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, this.r, this.t, Shader.TileMode.CLAMP);
        this.ac = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, this.ad, this.aa, Shader.TileMode.CLAMP);
        this.i = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, this.h, this.d, Shader.TileMode.CLAMP);
        this.u = new PointF(this.w / 2.0f, this.y);
    }

    public float i() {
        return this.w;
    }

    public float h() {
        return this.y;
    }

    public Path cvq_() {
        return this.x;
    }

    public PointF cvr_() {
        return this.u;
    }

    public LinearGradient cvn_() {
        return this.l;
    }

    public LinearGradient cvp_() {
        return this.q;
    }

    public LinearGradient cvt_() {
        return this.ac;
    }

    public LinearGradient cvm_() {
        return this.f;
    }

    public LinearGradient cvo_() {
        return this.p;
    }

    public LinearGradient cvs_() {
        return this.z;
    }

    public LinearGradient cvk_() {
        return this.e;
    }

    public LinearGradient cvl_() {
        return this.i;
    }

    public int m() {
        return this.j;
    }

    public void h(int i) {
        this.n = i;
        this.l = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, i, this.k, Shader.TileMode.CLAMP);
    }

    public void a(int i) {
        this.m = i;
        this.f = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, i, this.g, Shader.TileMode.CLAMP);
    }

    public void b(int i) {
        this.k = i;
        this.l = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, this.n, i, Shader.TileMode.CLAMP);
    }

    public void e(int i) {
        this.g = i;
        this.f = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, this.m, i, Shader.TileMode.CLAMP);
    }

    public void g(int i) {
        this.r = i;
        this.q = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, i, this.t, Shader.TileMode.CLAMP);
    }

    public void j(int i) {
        this.s = i;
        this.p = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, i, this.o, Shader.TileMode.CLAMP);
    }

    public void f(int i) {
        this.o = i;
        this.p = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, this.s, i, Shader.TileMode.CLAMP);
    }

    public void i(int i) {
        this.t = i;
        this.q = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, this.r, i, Shader.TileMode.CLAMP);
    }

    public void o(int i) {
        this.ad = i;
        this.ac = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, i, this.aa, Shader.TileMode.CLAMP);
    }

    public void m(int i) {
        this.ab = i;
        this.z = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, i, this.v, Shader.TileMode.CLAMP);
    }

    public void n(int i) {
        this.aa = i;
        this.ac = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, this.ad, i, Shader.TileMode.CLAMP);
    }

    public void l(int i) {
        this.v = i;
        this.z = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, this.ab, i, Shader.TileMode.CLAMP);
    }

    public void d(int i) {
        this.h = i;
        this.i = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, i, this.d, Shader.TileMode.CLAMP);
    }

    public void c(int i) {
        this.d = i;
        this.i = new LinearGradient(this.ai.x, this.ai.y, this.c.x, this.c.y, this.h, i, Shader.TileMode.CLAMP);
    }
}
