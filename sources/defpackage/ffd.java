package defpackage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class ffd {

    /* renamed from: a, reason: collision with root package name */
    private float f12480a;
    private String b;
    private float c;
    private float d;
    private Map<String, Double> e;
    private int f;
    private int g;
    private String h;
    private int i;
    private float j;
    private float k;

    public ffd(String str, int i, float f) {
        this(str, i, f, 1, 1);
    }

    public ffd(String str, int i, float f, int i2, int i3) {
        this.i = -1;
        this.k = -1.0f;
        this.f12480a = 0.0f;
        this.j = 0.0f;
        this.c = 0.0f;
        this.d = 0.0f;
        this.e = new HashMap();
        this.g = i2;
        this.h = str;
        this.i = i;
        this.k = f;
        this.f = i3;
    }

    public void a(float f) {
        this.j = f;
    }

    public void d(float f) {
        this.c = f;
        if (m()) {
            this.f12480a = (this.c - this.j) / this.k;
        } else {
            this.f12480a = 0.0f;
        }
    }

    public void e(float f) {
        this.c = f;
        if (m()) {
            this.f12480a = Math.round((this.c - this.j) / 1000.0f) / (TimeUnit.MILLISECONDS.toSeconds((long) this.k) * 1.0f);
        } else {
            this.f12480a = 0.0f;
        }
    }

    public void b(float f, int i) {
        this.c = f;
        if (i == 4) {
            if (f >= this.k) {
                this.f12480a = 1.0f;
                return;
            } else {
                this.f12480a = 0.0f;
                return;
            }
        }
        if (f <= this.k && f > 0.0f) {
            this.f12480a = 1.0f;
        } else {
            this.f12480a = 0.0f;
        }
    }

    public void b(float f) {
        if (m()) {
            this.d = this.k - f;
        }
    }

    private boolean m() {
        float f = this.j;
        return f >= 0.0f && this.c >= f && this.k > 0.0f;
    }

    public float e() {
        return this.d;
    }

    public float a() {
        return this.f12480a;
    }

    public int f() {
        return this.i;
    }

    public float i() {
        return this.k;
    }

    public float d() {
        return this.j;
    }

    public String h() {
        return this.h;
    }

    public int c() {
        return this.g;
    }

    public float j() {
        return this.c;
    }

    public int g() {
        return this.f;
    }

    public void a(String str) {
        this.b = str;
    }

    public String b() {
        return this.b;
    }

    public Map<String, Double> n() {
        return this.e;
    }

    public void b(Map<String, Double> map) {
        this.e.putAll(map);
    }
}
