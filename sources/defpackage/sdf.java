package defpackage;

import java.util.Date;

/* loaded from: classes9.dex */
public class sdf {

    /* renamed from: a, reason: collision with root package name */
    private long f17031a;
    private int c;
    private int e;

    public void d(long j) {
        this.f17031a = j;
    }

    public int c() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public void c(int i) {
        this.e = i;
    }

    public String toString() {
        return "stressEndTime = " + jec.c(new Date(this.f17031a * 1000)) + " stressScore = " + this.e + " stressGrade = " + this.e;
    }
}
