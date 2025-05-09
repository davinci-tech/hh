package defpackage;

import android.graphics.RectF;
import java.lang.reflect.Array;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class slg {

    /* renamed from: a, reason: collision with root package name */
    private int f17101a;
    private int b;
    private boolean c;
    private int d;
    private int e;
    private float f;
    private float[] g;
    private int h;
    private float i;
    private float j;
    private float k;
    private float l;
    private int m;
    private float n;
    private int o;
    private float[][] r;
    private boolean t;
    private float[][] v;
    private float[][] w;
    private float[][] x;
    private ConcurrentHashMap<Integer, Float> y;
    private int q = -1;
    private RectF p = new RectF();
    private RectF s = new RectF();

    public boolean a() {
        return this.t;
    }

    public int aa() {
        return this.e;
    }

    public float ab() {
        return this.l;
    }

    public int ad() {
        return this.d;
    }

    public int b() {
        return this.m;
    }

    public boolean c() {
        return this.c;
    }

    public int d() {
        return this.q;
    }

    public void d(float[] fArr) {
        this.g = fArr;
    }

    public ConcurrentHashMap<Integer, Float> e() {
        return this.y;
    }

    public void ecG_(RectF rectF) {
        if (rectF == null) {
            return;
        }
        if (this.p == null) {
            this.p = new RectF();
        }
        RectF rectF2 = this.p;
        rectF2.left = rectF.left;
        rectF2.top = rectF.top;
        rectF2.right = rectF.right;
        rectF2.bottom = rectF.bottom;
    }

    public RectF ecH_() {
        return this.s;
    }

    public RectF ecI_() {
        return this.p;
    }

    public slg f() {
        slg slgVar = new slg();
        slgVar.p(aa());
        slgVar.t(ad());
        slgVar.d(g());
        slgVar.d(i());
        slgVar.c(l());
        slgVar.m(ab());
        slgVar.q(d());
        slgVar.d(c());
        slgVar.r(z());
        slgVar.i(u());
        slgVar.e(o());
        slgVar.s(b());
        slgVar.n(y());
        slgVar.e(k());
        slgVar.d(m());
        slgVar.a(w());
        slgVar.b(n());
        slgVar.g(x());
        if (ecI_() != null && slgVar.ecI_() != null) {
            RectF ecI_ = slgVar.ecI_();
            RectF ecI_2 = ecI_();
            ecI_.left = ecI_2.left;
            ecI_.top = ecI_2.top;
            ecI_.right = ecI_2.right;
            ecI_.bottom = ecI_2.bottom;
        }
        if (ecH_() != null && slgVar.ecH_() != null) {
            RectF ecH_ = slgVar.ecH_();
            RectF ecH_2 = ecH_();
            ecH_.left = ecH_2.left;
            ecH_.top = ecH_2.top;
            ecH_.right = ecH_2.right;
            ecH_.bottom = ecH_2.bottom;
        }
        return slgVar;
    }

    public void g(float f) {
        this.k = f;
    }

    public float[] g() {
        return this.g;
    }

    public void h(float f) {
        ac();
        if (this.c) {
            this.s.left = f;
        } else {
            this.s.right = f;
        }
    }

    public float i() {
        return this.j;
    }

    public void i(float f) {
        this.n = f;
    }

    public boolean j() {
        ConcurrentHashMap<Integer, Float> concurrentHashMap = this.y;
        return concurrentHashMap == null || concurrentHashMap.size() == 0;
    }

    public void k(int i) {
        this.b = i;
    }

    public float l() {
        return this.f;
    }

    public void m(float f) {
        this.l = f;
    }

    public void n(int i) {
        this.o = i;
    }

    public float o() {
        return this.i;
    }

    public void o(float f) {
        ac();
        this.s.bottom = f;
    }

    public void o(int i) {
        this.h = i;
    }

    public void p(int i) {
        this.e = i;
    }

    public void r(int i) {
        if (i > 0) {
            this.f17101a = i;
        }
    }

    public void t(int i) {
        this.d = i;
    }

    public int z() {
        return this.f17101a;
    }

    public void c(float f) {
        this.f = f;
    }

    public void e(float f) {
        this.i = f;
    }

    public void ecF_(RectF rectF) {
        this.s = rectF;
    }

    public void f(float f) {
        ac();
        if (this.c) {
            this.s.right = f;
        } else {
            this.s.left = f;
        }
    }

    public void j(float f) {
        ac();
        this.s.top = f;
    }

    public void m(int i) {
        ConcurrentHashMap<Integer, Float> concurrentHashMap = this.y;
        if (concurrentHashMap != null) {
            concurrentHashMap.remove(Integer.valueOf(i));
        }
    }

    public float[][] m() {
        return this.w;
    }

    public float[][] n() {
        return this.v;
    }

    public float p() {
        RectF rectF = this.s;
        if (rectF != null) {
            return this.c ? rectF.left : rectF.right;
        }
        return 0.0f;
    }

    public float q() {
        RectF rectF = this.s;
        if (rectF != null) {
            return this.c ? rectF.right : rectF.left;
        }
        return 0.0f;
    }

    public void q(int i) {
        this.q = i;
    }

    public void s(int i) {
        this.m = i;
    }

    public float t() {
        RectF rectF = this.s;
        if (rectF != null) {
            return rectF.top;
        }
        return 0.0f;
    }

    public float u() {
        return this.n;
    }

    public float[][] w() {
        return this.x;
    }

    public float x() {
        return this.k;
    }

    public int y() {
        return this.o;
    }

    public void a(float[][] fArr) {
        this.x = fArr;
    }

    public float[] b(boolean z, int i) {
        float b;
        float[] fArr = new float[this.f17101a];
        for (int i2 = 0; i2 < this.f17101a; i2++) {
            if (z) {
                b = d(i2, i);
            } else {
                b = b(i2, i);
            }
            fArr[i2] = b;
        }
        return fArr;
    }

    public void c(float f, float f2, float f3, float f4) {
        f(f);
        j(f2);
        h(f3);
        o(f4);
    }

    public float f(int i) {
        int y = y(i);
        if (d(this.v, y)) {
            return 0.0f;
        }
        return this.v[y][this.c ? 1 : 0];
    }

    public float[] i(int i) {
        float[] fArr = new float[this.f17101a];
        for (int i2 = 0; i2 < this.f17101a; i2++) {
            fArr[i2] = d(i2, i);
        }
        return fArr;
    }

    public float r() {
        RectF rectF = this.s;
        if (rectF != null) {
            return rectF.bottom;
        }
        return 0.0f;
    }

    public float b(int i) {
        int y = y(i);
        if (d(this.x, y)) {
            return 0.0f;
        }
        return this.x[y][2];
    }

    public float g(int i) {
        int y = y(i);
        if (d(this.v, y)) {
            return 0.0f;
        }
        return this.v[y][!this.c ? 1 : 0];
    }

    public float[] l(int i) {
        float[] fArr = new float[this.f17101a];
        for (int i2 = 0; i2 < this.f17101a; i2++) {
            fArr[i2] = b(i2, i);
        }
        return fArr;
    }

    private int y(int i) {
        return this.c ? (this.f17101a - 1) - i : i;
    }

    public float[][] k() {
        return this.r;
    }

    public float d(int i) {
        int y = y(i);
        if (d(this.x, y)) {
            return 0.0f;
        }
        return this.x[y][!this.c ? 1 : 0];
    }

    public void e(float[][] fArr) {
        this.r = fArr;
    }

    public float h(int i) {
        int y = y(i);
        if (d(this.x, y)) {
            return 0.0f;
        }
        return this.x[y][this.c ? 1 : 0];
    }

    public float j(int i) {
        int y = y(i);
        if (d(this.v, y)) {
            return 0.0f;
        }
        return this.v[y][2];
    }

    public boolean a(boolean z, int i, float[] fArr) {
        if (this.r != null && this.w != null && fArr != null && fArr.length == this.f17101a) {
            for (int i2 = 0; i2 < this.f17101a; i2++) {
                if (i != i2) {
                    if (Math.abs((z ? d(i2, i) : b(i2, i)) - fArr[i2]) > 1.0f) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void a(float f, float f2, float f3, float f4) {
        if (this.p == null) {
            this.p = new RectF();
        }
        RectF rectF = this.p;
        rectF.left = f;
        rectF.top = f2;
        rectF.right = f3;
        rectF.bottom = f4;
    }

    public float e(boolean z, int i) {
        return z ? h(i) : f(i);
    }

    public void d(int i, float f) {
        float[] fArr = this.g;
        if (fArr == null || i < 0 || i >= fArr.length) {
            return;
        }
        fArr[i] = f;
    }

    public void a(boolean z) {
        this.t = z;
    }

    public void d(float[][] fArr) {
        this.w = fArr;
    }

    public boolean c(boolean z, int i, float f, float f2) {
        float e = e(z, i);
        float a2 = a(z, i);
        float abs = Math.abs(e - f);
        float abs2 = Math.abs(a2 - f2);
        float abs3 = Math.abs(a2 - e) / 8.0f;
        return (Float.compare(abs, abs3) < 0) && (Float.compare(abs2, abs3) < 0);
    }

    public void h() {
        ConcurrentHashMap<Integer, Float> concurrentHashMap = this.y;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
    }

    public void b(float f) {
        int i = this.f17101a;
        float[][] fArr = (float[][]) Array.newInstance((Class<?>) Float.TYPE, i, 3);
        this.w = fArr;
        float[][] fArr2 = (float[][]) Array.newInstance((Class<?>) Float.TYPE, i, 3);
        this.v = fArr2;
        float[] fArr3 = fArr2[0];
        fArr3[0] = f;
        float f2 = this.k;
        fArr3[1] = f + f2;
        float f3 = (f2 / 2.0f) + f;
        fArr3[2] = f3;
        float[] fArr4 = fArr[0];
        fArr4[1] = (this.i / 2.0f) + f;
        fArr4[0] = f3;
        fArr4[2] = f3;
        int i2 = 1;
        while (true) {
            int i3 = this.f17101a;
            if (i2 >= i3) {
                return;
            }
            float[][] fArr5 = this.v;
            float[] fArr6 = fArr5[i2];
            int i4 = i2 - 1;
            float[] fArr7 = fArr5[i4];
            float f4 = fArr7[0];
            float f5 = this.m;
            float f6 = this.i;
            fArr6[0] = f4 + f5 + f6;
            fArr6[1] = fArr7[1] + f5 + f6;
            fArr6[2] = fArr7[2] + f5 + f6;
            float[][] fArr8 = this.w;
            float[] fArr9 = fArr8[i2];
            fArr9[1] = fArr8[i4][1] + f5 + f6;
            float f7 = (r10 * i2) + f;
            float f8 = this.k;
            float f9 = (i2 * f6) + f7 + (f8 / 2.0f);
            fArr9[0] = f9;
            fArr9[2] = f7 + f8 + (f6 / 2.0f) + (i4 * f6);
            if (i2 == i3 - 1) {
                fArr9[1] = f9;
            }
            i2++;
        }
    }

    public void b(int i, float f) {
        if (this.y == null) {
            this.y = new ConcurrentHashMap<>();
        }
        this.y.put(Integer.valueOf(i), Float.valueOf(f));
    }

    public void a(float f) {
        int i = this.f17101a;
        float[][] fArr = (float[][]) Array.newInstance((Class<?>) Float.TYPE, i, 3);
        this.r = fArr;
        float[][] fArr2 = (float[][]) Array.newInstance((Class<?>) Float.TYPE, i, 3);
        this.x = fArr2;
        float[] fArr3 = fArr2[0];
        fArr3[0] = f;
        float f2 = this.n;
        float f3 = f + f2;
        fArr3[1] = f3;
        fArr3[2] = (f3 + f) / 2.0f;
        float[] fArr4 = fArr[0];
        float f4 = this.f;
        fArr4[1] = f + f4;
        float f5 = (f2 / 2.0f) + f;
        fArr4[0] = f5;
        fArr4[2] = f5;
        float f6 = f4 * 2.0f;
        int i2 = 1;
        while (true) {
            int i3 = this.f17101a;
            if (i2 >= i3) {
                return;
            }
            float[][] fArr5 = this.x;
            float[] fArr6 = fArr5[i2];
            int i4 = i2 - 1;
            float[] fArr7 = fArr5[i4];
            float f7 = fArr7[0];
            float f8 = this.o;
            fArr6[0] = f7 + f8 + f6;
            fArr6[1] = fArr7[1] + f8 + f6;
            fArr6[2] = fArr7[2] + f8 + f6;
            float[][] fArr8 = this.r;
            float[] fArr9 = fArr8[i2];
            fArr9[1] = fArr8[i4][1] + f8 + f6;
            float f9 = (r11 * i2) + f;
            float f10 = this.n;
            float f11 = (i2 * f6) + f9 + (f10 / 2.0f);
            fArr9[0] = f11;
            fArr9[2] = f9 + f10 + this.f + (i4 * f6);
            if (i2 == i3 - 1) {
                fArr9[1] = f11;
            }
            i2++;
        }
    }

    public void d(boolean z) {
        this.c = z;
    }

    public void d(float f) {
        this.j = f;
    }

    public void b(float[][] fArr) {
        this.v = fArr;
    }

    public float e(int i) {
        int y = y(i);
        if (d(this.w, y)) {
            return 0.0f;
        }
        return this.w[y][this.c ? (char) 2 : (char) 1];
    }

    public float a(boolean z, int i) {
        return z ? d(i) : g(i);
    }

    public float b(int i, int i2) {
        int y = y(i);
        if (d(this.w, y)) {
            return 0.0f;
        }
        if (i > i2) {
            return this.w[y][this.c ? (char) 1 : (char) 2];
        }
        if (i < i2) {
            return this.w[y][this.c ? (char) 2 : (char) 1];
        }
        return this.w[y][0];
    }

    public float[] c(boolean z, int i) {
        return z ? i(i) : l(i);
    }

    public float a(int i) {
        int y = y(i);
        if (d(this.w, y)) {
            return 0.0f;
        }
        return this.w[y][this.c ? (char) 1 : (char) 2];
    }

    public float d(int i, int i2) {
        int y = y(i);
        if (d(this.r, y)) {
            return 0.0f;
        }
        if (i > i2) {
            return this.r[y][this.c ? (char) 1 : (char) 2];
        }
        if (i < i2) {
            return this.r[y][this.c ? (char) 2 : (char) 1];
        }
        return this.r[y][0];
    }

    public float d(boolean z, int i) {
        return z ? b(i) : j(i);
    }

    public float c(int i) {
        int y = y(i);
        if (d(this.w, y)) {
            return 0.0f;
        }
        return this.w[y][0];
    }

    public float b(boolean z) {
        if (z) {
            return (this.n / 2.0f) - this.f;
        }
        return (this.k - this.i) / 2.0f;
    }

    private boolean d(float[][] fArr, int i) {
        return fArr == null || i < 0 || i >= fArr.length;
    }

    private void ac() {
        if (this.s == null) {
            this.s = new RectF();
        }
    }
}
