package defpackage;

import android.graphics.PointF;
import java.text.DecimalFormat;

/* loaded from: classes4.dex */
public class ffh {

    /* renamed from: a, reason: collision with root package name */
    private float f12485a;
    private float b;
    private float c;
    private int d;
    private float e;
    private float f;
    private PointF g;
    private String h;
    private String i;
    private float j;
    private String n;

    public ffh(float f, String str) {
        this(f, "", str);
    }

    private ffh(float f, String str, String str2) {
        this(0.0f, f, str, str2, -7829368);
    }

    private ffh(float f, float f2, String str, String str2, int i) {
        PointF pointF = new PointF();
        this.g = pointF;
        this.j = f2;
        this.e = f;
        float f3 = f2 - f;
        this.c = f3;
        this.f12485a = f3;
        this.n = str2;
        pointF.y = f;
        this.i = str;
        this.d = i;
        this.h = new DecimalFormat("##").format(this.f12485a);
    }

    public PointF awM_() {
        return new PointF(g(), this.g.y - this.f12485a);
    }

    public void b(float f) {
        this.f = f;
    }

    public float c() {
        return this.f12485a;
    }

    public void c(float f) {
        this.f12485a = f;
    }

    public PointF awN_() {
        return this.g;
    }

    private float g() {
        PointF pointF = this.g;
        if (pointF != null) {
            float f = pointF.x + (this.f / 2.0f);
            this.b = f;
            return f;
        }
        return this.f / 2.0f;
    }

    public int b() {
        return this.d;
    }

    public float a() {
        return this.e;
    }

    public String d() {
        return this.h;
    }

    public void c(String str) {
        this.h = str;
    }

    public String toString() {
        return "height: " + this.f12485a;
    }
}
