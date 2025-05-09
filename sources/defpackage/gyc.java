package defpackage;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class gyc implements Serializable {
    private static final long serialVersionUID = -4674592258281735115L;

    /* renamed from: a, reason: collision with root package name */
    private float f13001a;
    private long b;
    private int c;
    private long d;
    private float e;

    public float b() {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(this.b - this.d);
        if (minutes == 0) {
            return 0.0f;
        }
        return this.e / minutes;
    }

    public long c() {
        return this.d;
    }

    public void e(long j) {
        this.d = j;
    }

    public long e() {
        return this.b;
    }

    public void d(long j) {
        this.b = j;
    }

    public float d() {
        return this.e;
    }

    public void d(float f) {
        this.e = f;
    }

    public float a() {
        return this.f13001a;
    }

    public void a(float f) {
        this.f13001a = f;
    }

    public int h() {
        return this.c;
    }

    public void d(int i) {
        this.c = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(this.d);
        sb.append("#");
        sb.append(this.b);
        sb.append("#");
        sb.append(this.e);
        sb.append("#");
        sb.append(this.f13001a);
        sb.append("#");
        sb.append(this.c);
        return sb.toString();
    }
}
